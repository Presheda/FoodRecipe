package com.precious.foodrecipe.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.precious.foodrecipe.Adapter.RecipeListAdapter;
import com.precious.foodrecipe.R;
import com.precious.foodrecipe.databinding.ActivityMainBinding;
import com.precious.foodrecipe.model.Constants;
import com.precious.foodrecipe.model.ConstantsVariables;
import com.precious.foodrecipe.model.EndlessRecyclerViewScrollListener;
import com.precious.foodrecipe.model.RecipeMain;
import com.precious.foodrecipe.services.RecipeService;
import com.precious.foodrecipe.services.RecipeServiceBuilder;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipeListAdapter.CustomitemClickListener {

    public static final String RECIPE_KEY = "RECIPE_KEY";
    public static final String CLICKED_ITEM = "CLICKED_ITEM";
    public static final String EXTRA_PET_TRANSITION_NAME = "EXTRA_PET_TRANSITION_NAME";
    ActivityMainBinding mBinding;
    private RecipeListAdapter mListAdapter;
    private Constants.AnimType mType;
    private AlertDialog mDialog;
    private EndlessRecyclerViewScrollListener scrollListener;


    private RecipeMain mRecipeMain;
    private GridLayoutManager mLayoutManager;
    private String mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        mType = (Constants.AnimType) getIntent().getSerializableExtra(Constants.KEY_TYPE);


        setupToolbars();

        loadBackground();

        setupWindowAnimation();

        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                int toRang = view.getAdapter().getItemCount();
                mBinding.loadMore.setVisibility(View.VISIBLE);
                requery(mTitle, toRang, true);

            }
        };

        mBinding.recipeRecyclerView.addOnScrollListener(scrollListener);


    }

    private void setupWindowAnimation() {

        Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        enterTransition.setDuration(1000);
        getWindow().setEnterTransition(enterTransition);


        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);

//
//        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
//        transition.setDuration(1000);
//
//        getWindow().setEnterTransition(transition);
    }

    private void setupToolbars() {


        mTitle = getIntent().getStringExtra(LandingPageActivity.SEARCHED_ITEM);

        mBinding.toolbarMain.setTitle(mTitle);

        setSupportActionBar(mBinding.toolbarMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Fetching recipe")
                .setCancelable(false)
                .build();

    }


    private void loadBackground() {

        RecipeMain recipeMain = (RecipeMain) getIntent().getParcelableExtra(MainActivity.RECIPE_KEY);

        if (recipeMain != null) {
            setupRecyclerView(recipeMain);
        }
    }

    private void setupRecyclerView(RecipeMain recipeMain) {
        mListAdapter = new RecipeListAdapter(MainActivity.this, recipeMain.getHits(), MainActivity.this);
        mLayoutManager = new GridLayoutManager(MainActivity.this, 2);


        mBinding.recipeRecyclerView.setLayoutManager(mLayoutManager);
        mBinding.recipeRecyclerView.setAdapter(mListAdapter);
        mBinding.setHitList(recipeMain.getHits());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = (MenuItem) menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                hideKeyboard(getApplicationContext());


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.show();
                    }
                }, 1000);




                requery(s, 0, false);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void onClick(RecipeMain.Recipe itemRecipe, ImageView imageView) {
        final Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RECIPE_KEY, itemRecipe);
        intent.putExtra(EXTRA_PET_TRANSITION_NAME, ViewCompat.getTransitionName(imageView));
        final ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView,
                ViewCompat.getTransitionName(imageView));
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(intent, activityOptions.toBundle());
            }
        };
        handler.postDelayed(runnable, 300);
    }

    @Override
    public boolean onSupportNavigateUp() {

        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.RIGHT);
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
        finish();

        return true;
    }

    @Override
    public void onBackPressed() {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.RIGHT);
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
        finish();
        super.onBackPressed();
    }


    private void requery(final String s, int fromRange, final boolean loadMore) {


        RecipeService recipeService = RecipeServiceBuilder.buidService(RecipeService.class);
        Call<RecipeMain> request = recipeService.searchRecicpe(
                s,
                ConstantsVariables.APP_ID,
                ConstantsVariables.APP_KEY,
                fromRange,
                fromRange + 20


        );


        final Snackbar snackbar = Snackbar.make(mBinding.coordinatorLayout,
                "Unable to fetch data please check network and try again", Snackbar.LENGTH_LONG);


        request.enqueue(new Callback<RecipeMain>() {
            @Override
            public void onResponse(Call<RecipeMain> call, Response<RecipeMain> response) {

                mDialog.cancel();

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        mRecipeMain = response.body();

                        if (loadMore) {

                            int size = mRecipeMain.getHits().size();
                            for (int i = 0; i < size; i++) {
                                mListAdapter.addHitsItem(mRecipeMain.getHits().get(i));
                            }

                            mBinding.loadMore.setVisibility(View.GONE);
                            scrollListener.resetState();
                        } else {

                            setupRecyclerView(mRecipeMain);


                        }
                    }
                }

                else {
                    snackbar.show();
                }

            }

            @Override
            public void onFailure(Call<RecipeMain> call, Throwable t) {
                mBinding.loadMore.setVisibility(View.GONE);
                mDialog.cancel();
                snackbar.show();

            }
        });
    }


    public void hideKeyboard(Context context) {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}

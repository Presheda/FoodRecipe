package com.precious.foodrecipe.app;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.IdlingResource;
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

import com.precious.foodrecipe.IdlingResource.FoodRecipeIdlingResource;
import com.precious.foodrecipe.paging.RecipeListAdapter;
import com.precious.foodrecipe.R;
import com.precious.foodrecipe.databinding.ActivityMainBinding;
import com.precious.foodrecipe.model.Constants;
import com.precious.foodrecipe.model.ConstantsVariables;
import com.precious.foodrecipe.model.EndlessRecyclerViewScrollListener;
import com.precious.foodrecipe.model.FoodExecutor;
import com.precious.foodrecipe.model.LoadBackgound;
import com.precious.foodrecipe.model.RecipeMain;
import com.precious.foodrecipe.paging.RecipeViewModel;
import com.precious.foodrecipe.paging.RecipeViewModelFactory;
import com.precious.foodrecipe.services.RecipeService;
import com.precious.foodrecipe.services.RecipeServiceBuilder;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipeListAdapter.CustomitemClickListener,
        RecipeListAdapter.CustomAdapterIsEmpty {

    public static final String RECIPE_KEY = "RECIPE_KEY";
    public static final String EXTRA_PET_TRANSITION_NAME = "EXTRA_PET_TRANSITION_NAME";
    ActivityMainBinding mBinding;
    private Constants.AnimType mType;
    private RecipeMain mRecipeMain;
    private GridLayoutManager mLayoutManager;
    private String mTitle;
    private RecipeListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        mType = (Constants.AnimType) getIntent().getSerializableExtra(Constants.KEY_TYPE);

        LandingPageActivity.mIdlingResource.setIdleState(false);


        setupToolbars();

        setupRecyclerView();

        loadBackground();

        setupWindowAnimation();

        mBinding.errorLoadingRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.errorLayout.setVisibility(View.GONE);
                requery(String.valueOf(getSupportActionBar().getTitle()), true);
            }
        });



//
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()
//                .penaltyLog()
//                .build()
//        );


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


    }


    private void loadBackground() {

        final String searchedItem = getIntent().getStringExtra(LandingPageActivity.SEARCHED_ITEM);

        if (searchedItem != null) {
            requery(searchedItem, false);

        }

    }

    private void setupRecyclerView() {

        mLayoutManager = new GridLayoutManager(this, 2);
        mBinding.recipeRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecipeListAdapter(this, this, this);
        mBinding.recipeRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = (MenuItem) menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                hideKeyboard(getApplicationContext());


                getSupportActionBar().setTitle(query);


                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        requery(query, true);
                    }
                };

                Handler handler = new Handler();

                handler.postDelayed(r, 2000);


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


    private void requery(final String query, final boolean newLoad) {

        LandingPageActivity.mIdlingResource.setIdleState(false);

        mBinding.errorLayout.setVisibility(View.GONE);
        mBinding.shimmerLayout.setVisibility(View.VISIBLE);
        mBinding.shimmerLayout.startShimmer();

        if(newLoad){
            mBinding.recipeRecyclerView.setVisibility(View.GONE);
            mBinding.shimmerLayout.setVisibility(View.VISIBLE);
            mBinding.shimmerLayout.startShimmer();
        }
        RecipeViewModelFactory factory = new RecipeViewModelFactory(query);
        RecipeViewModel viewModel = ViewModelProviders.of(this, factory).get(RecipeViewModel.class);
        viewModel.getRecipePagedList(query).observe(this, new Observer<PagedList<RecipeMain.Hits>>() {
            @Override
            public void onChanged(@Nullable PagedList<RecipeMain.Hits> hits) {
                if(hits != null){
                    LandingPageActivity.mIdlingResource.setIdleState(true);
                    mAdapter.submitList(hits);
                    mBinding.shimmerLayout.stopShimmer();
                    mBinding.shimmerLayout.setVisibility(View.GONE);
                    if(hits.size() != 0){
                        mBinding.errorLayout.setVisibility(View.GONE);
                        mBinding.recipeRecyclerView.setVisibility(View.VISIBLE);
                    } else {
                        mBinding.errorLayout.setVisibility(View.VISIBLE);
                        mBinding.recipeRecyclerView.setVisibility(View.GONE);
                    }
                }
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


    @Override
    protected void onResume() {
        super.onResume();
      //  mBinding.shimmerLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBinding.shimmerLayout.stopShimmer();
    }

    @Override
    public void isEmpty(boolean isEmpty) {

        if(!isEmpty){
            mBinding.recipeRecyclerView.setVisibility(View.VISIBLE);
            mBinding.shimmerLayout.setVisibility(View.GONE);
        }
    }
}


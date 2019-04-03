package com.precious.foodrecipe.app;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.precious.foodrecipe.R;
import com.precious.foodrecipe.databinding.ActivityLandingPageBinding;
import com.precious.foodrecipe.model.ConstantsVariables;
import com.precious.foodrecipe.model.RecipeMain;
import com.precious.foodrecipe.services.RecipeService;
import com.precious.foodrecipe.services.RecipeServiceBuilder;

import java.net.ConnectException;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String SHEARED_SEARCH_VIEW = "shearedSearchView";
    public static final String SEARCHED_ITEM = "SEARCHED_ITEM";
    ActivityLandingPageBinding mLandingPageBinding;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLandingPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_landing_page);

        setupToolbar();
        setupSearch();

        setupTransition();


    }

    private void setupTransition() {

//        Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
//        enterTransition.setDuration(500);
//        getWindow().setExitTransition(enterTransition);

        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        transition.setDuration(500);

        getWindow().setEnterTransition(transition);

        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.RIGHT);
        slide.setDuration(1000);
        getWindow().setReenterTransition(slide);

        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
    }

    private void setupToolbar() {
        setSupportActionBar(mLandingPageBinding.toolbar);
        mLandingPageBinding.navView.setNavigationItemSelectedListener(this);
        mLandingPageBinding.searchView.setSubmitButtonEnabled(true);
        ActionBarDrawerToggle mDrawerToogle = new ActionBarDrawerToggle(this, mLandingPageBinding.drawerLayout,
                mLandingPageBinding.toolbar,
                R.string.drawer_open,
                R.string.drawer_close

        );

        mLandingPageBinding.drawerLayout.addDrawerListener(mDrawerToogle);

        mDrawerToogle.syncState();

        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Fetching recipe")
                .setCancelable(false)
                .build();

        mLandingPageBinding.navView.getMenu().getItem(0).setChecked(true);
    }


    public void setupSearch() {


        mLandingPageBinding.searchView.onActionViewExpanded();

        mLandingPageBinding.searchView.setIconifiedByDefault(false);
        mLandingPageBinding.searchView.setQueryHint("Search recipe");

        if (!mLandingPageBinding.searchView.isFocused()) {
            mLandingPageBinding.searchView.clearFocus();
        }


        mLandingPageBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {

                hideKeyboard(getApplicationContext());

                mDialog.show();

                loadBackground(s);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 1000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 1500);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }


    private void loadBackground(final String s) {


        RecipeService recipeService = RecipeServiceBuilder.buidService(RecipeService.class);

        Call<RecipeMain> request = recipeService.searchRecicpe(
                s,
                ConstantsVariables.APP_ID,
                ConstantsVariables.APP_KEY,
                0,
                20


        );

        request.request().header("close");

        final Snackbar snackbar = Snackbar.make(mLandingPageBinding.coordinatorLayout,
                "Unable to fetch data please check network and try again", Snackbar.LENGTH_LONG);

        request.enqueue(new Callback<RecipeMain>() {
            @Override
            public void onResponse(Call<RecipeMain> call, Response<RecipeMain> response) {
                mDialog.cancel();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        final Intent i = new Intent(LandingPageActivity.this, MainActivity.class);
                        i.putExtra(MainActivity.RECIPE_KEY, response.body());
                        i.putExtra(SEARCHED_ITEM, s);
                        final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LandingPageActivity.this);
                        startActivity(i, options.toBundle());



                    }
                } else {
                    String snackBarMessage = "Api Limits exceeded or Server Error";

                    showSnackbar(snackBarMessage);
                }

            }

            @Override
            public void onFailure(Call<RecipeMain> call, Throwable t) {
                mDialog.cancel();
                String snackBarMessag="";

                if(t instanceof ConnectException){
                    snackBarMessag = "Unable to connect please check internet";
                }

                showSnackbar(snackBarMessag);


            }
        });
    }

    private void showSnackbar(String snackBarMessage) {
        Snackbar.make(mLandingPageBinding.coordinatorLayout, snackBarMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setChecked(true);


        int id = menuItem.getItemId();

        hideDrawer();

        switch (id) {
            case R.id.action_recipe_search:
                break;

            case R.id.action_about:
                break;

        }

        return true;
    }

    public void hideDrawer() {

        mLandingPageBinding.drawerLayout.closeDrawer(Gravity.START);
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

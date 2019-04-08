package com.precious.foodrecipe.model;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.Snackbar;

import com.precious.foodrecipe.app.LandingPageActivity;
import com.precious.foodrecipe.app.MainActivity;
import com.precious.foodrecipe.services.RecipeService;
import com.precious.foodrecipe.services.RecipeServiceBuilder;

import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadBackgound {

   public interface LoadBackgoundCallBack{

       void LoadisFinished(Response<RecipeMain> response, boolean loadMore);

   }


   public static LoadBackgoundCallBack mBackgoundCallBack;


    public static void loadBackground(final String s, LoadBackgoundCallBack loadBackgoundCallBack, int fromRange, final boolean loadMore) {

        mBackgoundCallBack = loadBackgoundCallBack;

       // mIdlingResource.setIdleState(false);

        RecipeService recipeService = RecipeServiceBuilder.buidService(RecipeService.class);

        Call<RecipeMain> request = recipeService.searchRecicpe(
                s,
                ConstantsVariables.APP_ID,
                ConstantsVariables.APP_KEY,
                fromRange,
                fromRange + 20


        );

        request.request().header("close");

        request.enqueue(new Callback<RecipeMain>() {
            @Override
            public void onResponse(Call<RecipeMain> call, Response<RecipeMain> response) {

                mBackgoundCallBack.LoadisFinished(response, loadMore);


            }

            @Override
            public void onFailure(Call<RecipeMain> call, Throwable t) {

                mBackgoundCallBack.LoadisFinished(null, true);

            }
        });
    }
}

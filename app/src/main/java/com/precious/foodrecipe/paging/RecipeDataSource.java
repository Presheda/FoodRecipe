package com.precious.foodrecipe.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.precious.foodrecipe.model.ConstantsVariables;
import com.precious.foodrecipe.model.RecipeMain;
import com.precious.foodrecipe.services.RecipeService;
import com.precious.foodrecipe.services.RecipeServiceBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDataSource extends PageKeyedDataSource<Integer, RecipeMain.Hits> {

    private int fromRange = 0;
    private int toRange = 20;
    private String query = "";


    public RecipeDataSource(String query) {
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, RecipeMain.Hits> callback) {


        RecipeService recipeService = RecipeServiceBuilder.buidService(RecipeService.class);

        Call<RecipeMain> request = recipeService.searchRecicpe(
                query,
                ConstantsVariables.APP_ID,
                ConstantsVariables.APP_KEY,
                fromRange,
                toRange
        );


        try {

            Response<RecipeMain> response = request.execute();
            if (response.code() == 200 && response.body() != null) {
                fromRange = fromRange + 20;
                toRange = toRange + 20;
                callback.onResult(response.body().getHits(), null, fromRange);
            }

        } catch (IOException e) {

        }

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer,
            RecipeMain.Hits> callback) {


        RecipeService recipeService = RecipeServiceBuilder.buidService(RecipeService.class);

        Call<RecipeMain> request = recipeService.searchRecicpe(
                query,
                ConstantsVariables.APP_ID,
                ConstantsVariables.APP_KEY,
                params.key,
                toRange
        );


        try {
            Response<RecipeMain> response = request.execute();
            if (response.code() == 200 && response.body() != null) {
                if (fromRange > 20) {
                    fromRange = -20;
                    toRange = -20;
                }
                callback.onResult(response.body().getHits(), fromRange);
            }
        } catch (IOException e) {
        }

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, RecipeMain.Hits> callback) {


        RecipeService recipeService = RecipeServiceBuilder.buidService(RecipeService.class);

        Call<RecipeMain> request = recipeService.searchRecicpe(
                query,
                ConstantsVariables.APP_ID,
                ConstantsVariables.APP_KEY,
                params.key,
                toRange
        );

        try {
            Response<RecipeMain> response = request.execute();
            if (response.code() == 200 && response.body() != null) {
                fromRange = fromRange + 20;
                toRange = toRange + 20;
                callback.onResult(response.body().getHits(), fromRange);
            }
        } catch (IOException e){


        }



    }
}

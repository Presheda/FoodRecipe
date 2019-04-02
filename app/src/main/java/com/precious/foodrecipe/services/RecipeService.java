package com.precious.foodrecipe.services;


import com.precious.foodrecipe.model.RecipeMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeService {

    @GET("search")
    Call<RecipeMain> searchRecicpe(@Query("q")String q, @Query("app_id")String app_id, @Query("app_key")String app_key,
                                                @Query("from")int from, @Query("to")int to

                                     );
}

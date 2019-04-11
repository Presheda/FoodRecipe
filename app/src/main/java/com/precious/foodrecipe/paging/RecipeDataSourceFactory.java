package com.precious.foodrecipe.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.precious.foodrecipe.model.RecipeMain;

public class RecipeDataSourceFactory extends DataSource.Factory<Integer, RecipeMain.Hits> {


    private MutableLiveData<PageKeyedDataSource<Integer, RecipeMain.Hits>> recipeDataSource =
            new MutableLiveData<>();

    public String query = "";


    public RecipeDataSourceFactory(String query) {

        this.query = query;

    }

    @Override
    public DataSource<Integer, RecipeMain.Hits> create() {

        RecipeDataSource dataSource = new RecipeDataSource(query);


        recipeDataSource.postValue(dataSource);

        return dataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, RecipeMain.Hits>> getRecipeDataSource() {
        return recipeDataSource;
    }

}

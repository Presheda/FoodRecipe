package com.precious.foodrecipe.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.precious.foodrecipe.model.RecipeMain;

public class RecipeViewModel extends ViewModel{


   public LiveData<PagedList<RecipeMain.Hits>> recipePagedList;
   public LiveData<PageKeyedDataSource<Integer, RecipeMain.Hits>> liveDataSource;
   public String query;

    public RecipeViewModel(String query) {
        this.query = query;

        initiate(query);

    }

    private void initiate(String query) {
        RecipeDataSourceFactory itemDataSourceFactory = new RecipeDataSourceFactory(query);
        liveDataSource = itemDataSourceFactory.getRecipeDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(20)
                        .build();

        recipePagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }


    public LiveData<PagedList<RecipeMain.Hits>> getRecipePagedList(String query) {

        initiate(query);

        return recipePagedList;
    }


}

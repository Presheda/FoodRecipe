package com.precious.foodrecipe.paging;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class RecipeViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    String query;

    public RecipeViewModelFactory(String query) {
        this.query = query;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RecipeViewModel(query);
    }
}

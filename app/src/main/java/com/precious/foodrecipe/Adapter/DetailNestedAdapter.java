package com.precious.foodrecipe.Adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.precious.foodrecipe.model.RecipeMain;

public class DetailNestedAdapter {

    @BindingAdapter("setHealtLabel")
    public static void setHealthLabels(RecyclerView recyclerView, RecipeMain.Recipe recipe){
        if(recipe == null){
           return;
        }

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager == null){
            layoutManager = new LinearLayoutManager(recyclerView.getContext());
            recyclerView.setLayoutManager(layoutManager);
        }

        IngridientAdapter ingridientAdapter = (IngridientAdapter) recyclerView.getAdapter();
        if(ingridientAdapter == null){
            ingridientAdapter = new IngridientAdapter(recyclerView.getContext(), recipe.getHealthLabels());
        }
        ingridientAdapter.setStringList(recipe.getHealthLabels());
        recyclerView.setAdapter(ingridientAdapter);
    }


    @BindingAdapter("setIngridientLabel")
    public static void setIngridients(RecyclerView recyclerView, RecipeMain.Recipe recipe){
        if(recipe == null){
            return;
        }


        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager == null){
            layoutManager = new LinearLayoutManager(recyclerView.getContext());
            recyclerView.setLayoutManager(layoutManager);
        }

        IngridientAdapter ingridientAdapter = (IngridientAdapter) recyclerView.getAdapter();
        if(ingridientAdapter == null){
            ingridientAdapter = new IngridientAdapter(recyclerView.getContext(), recipe.getIngredientLines());
        }
        ingridientAdapter.setStringList(recipe.getIngredientLines());
        recyclerView.setAdapter(ingridientAdapter);
    }

    @BindingAdapter("setcalories")
    public static void setCalories(TextView textView, RecipeMain.Recipe recipe){
        if(recipe ==  null){
            return;
        }
        String calories = String.valueOf((int)recipe.getCalories());

        textView.setText(calories);
    }


    @BindingAdapter("setRecipeTime")
    public static void setTime(TextView textView, RecipeMain.Recipe recipe){
        if(recipe ==  null){
            return;
        }
        String calories = String.valueOf((int)recipe.getTotalTime());

        textView.setText(calories);
    }


    @BindingAdapter("setWeight")
    public static void setWeight(TextView textView, RecipeMain.Recipe recipe){
        if(recipe ==  null){
            return;
        }
        String calories = String.valueOf((int)recipe.getTotalWeight());

        textView.setText(calories);
    }
}

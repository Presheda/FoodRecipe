package com.precious.foodrecipe.BindingAdapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.precious.foodrecipe.Adapter.RecipeListAdapter;
import com.precious.foodrecipe.model.RecipeMain;

import java.util.List;

public class MainBindingAdapter {


    @BindingAdapter("setupRecycler")
    public static void setupRecyclerView(RecyclerView recyclerView, List<RecipeMain.Hits> mHitsList){

        if(mHitsList == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager == null){
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        }

        RecipeListAdapter adapter = (RecipeListAdapter) recyclerView.getAdapter();
        if(adapter ==  null){
            adapter = new RecipeListAdapter(recyclerView.getContext(), mHitsList,
                    (RecipeListAdapter.CustomitemClickListener) recyclerView.getContext());
        } else {
            adapter.setRecipe(mHitsList);
        }


    }
}

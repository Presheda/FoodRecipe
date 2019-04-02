package com.precious.foodrecipe.Adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.precious.foodrecipe.BR;
import com.precious.foodrecipe.R;
import com.precious.foodrecipe.app.MainActivity;
import com.precious.foodrecipe.app.RecipeDetailActivity;
import com.precious.foodrecipe.databinding.RecipeItemListBinding;
import com.precious.foodrecipe.model.RecipeMain;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    Context context;
    List<RecipeMain.Hits> mHitsList;
    boolean isLoading = false;

    public RecipeListAdapter(Context context, List<RecipeMain.Hits> hitsList, CustomitemClickListener customitemClickListener) {
        this.context = context;
        mHitsList = hitsList;
        mCustomitemClickListener = customitemClickListener;
    }

    CustomitemClickListener mCustomitemClickListener;

    public interface CustomitemClickListener {
        void onClick(RecipeMain.Recipe itemRecipe, ImageView imageView);
    }



    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RecipeItemListBinding listBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recipe_item_list,
                viewGroup, false);

        return new RecipeViewHolder(listBinding.getRoot());



    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder recipeViewHolder, int i) {

        final RecipeMain.Recipe recipe = mHitsList.get(i).getRecipe();

        recipeViewHolder.mItemListBinding.setRecipe(recipe);
        recipeViewHolder.mItemListBinding.setVariable(BR.recipe, recipe);

        String recipeName = recipeViewHolder.mItemListBinding.recipeName.getText().toString();

        ViewCompat.setTransitionName(recipeViewHolder.mItemListBinding.recipeImage, recipeName);

        recipeViewHolder.mItemListBinding.recipeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCustomitemClickListener.onClick(recipeViewHolder.mItemListBinding.getRecipe(),
                        recipeViewHolder.mItemListBinding.recipeImage);
            }
        });


        recipeViewHolder.mItemListBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {

        if(mHitsList != null){
            return mHitsList.size();
        }

        return 0;

    }

    public void setRecipe(List<RecipeMain.Hits> mHitsList) {

        this.mHitsList = mHitsList;

        notifyDataSetChanged();

    }

    public void addHitsItem(RecipeMain.Hits hits){

        mHitsList.add(hits);
        notifyItemInserted(mHitsList.size()-1);
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder  {

        RecipeItemListBinding mItemListBinding;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemListBinding = DataBindingUtil.getBinding(itemView);

        }

    }

}

package com.precious.foodrecipe.paging;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.precious.foodrecipe.BR;
import com.precious.foodrecipe.R;
import com.precious.foodrecipe.databinding.RecipeItemListBinding;
import com.precious.foodrecipe.model.RecipeMain;

public class RecipeListAdapter extends PagedListAdapter<RecipeMain.Hits, RecipeListAdapter.RecipeViewHolder> {
    Context context;
    boolean isLoading = false;

//    public RecipeListAdapter(Context context, List<RecipeMain.Hits> hitsList, CustomitemClickListener customitemClickListener) {
//        this.context = context;
//        mHitsList = hitsList;
//        mCustomitemClickListener = customitemClickListener;
//    }

    CustomitemClickListener mCustomitemClickListener;

    CustomAdapterIsEmpty mCustomAdapterIsEmpty;

    public RecipeListAdapter(Context context, CustomitemClickListener customitemClickListener,
                             CustomAdapterIsEmpty customAdapterIsEmpty
                             ) {
        super(DIFF_CALLBACK);
        this.context = context;
        mCustomitemClickListener = customitemClickListener;
        mCustomAdapterIsEmpty = customAdapterIsEmpty;
    }

    public interface CustomitemClickListener {
        void onClick(RecipeMain.Recipe itemRecipe, ImageView imageView);
    }

    public interface CustomAdapterIsEmpty {
        void isEmpty(boolean isEmpty);
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

        final RecipeMain.Recipe recipe = getItem(i).getRecipe();

        if (recipe != null) {
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

    }

    private static DiffUtil.ItemCallback<RecipeMain.Hits> DIFF_CALLBACK = new DiffUtil.ItemCallback<RecipeMain.Hits>() {
        @Override
        public boolean areItemsTheSame(@NonNull RecipeMain.Hits hits, @NonNull RecipeMain.Hits t1) {
            return hits == t1;
        }

        @Override
        public boolean areContentsTheSame(@NonNull RecipeMain.Hits hits, @NonNull RecipeMain.Hits t1) {
            return hits.getRecipe().equals(t1.getRecipe());
        }
    };

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        RecipeItemListBinding mItemListBinding;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemListBinding = DataBindingUtil.getBinding(itemView);
        }

    }

    public void checkIfEmpty() {
        if (getItemCount() > 0) {
            mCustomAdapterIsEmpty.isEmpty(false);
        }
    }

}

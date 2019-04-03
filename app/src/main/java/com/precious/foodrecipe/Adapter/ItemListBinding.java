package com.precious.foodrecipe.Adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.precious.foodrecipe.R;
import com.precious.foodrecipe.model.RecipeMain;

public class ItemListBinding {



    @BindingAdapter("setRecipeEnergy")
    public static void setRecipeEnergy(TextView textView, RecipeMain.Recipe recipe){

        if( recipe == null){
            return;
        }

        int calories = (int) recipe.getCalories();
         textView.setText(String.valueOf(calories) + " kcal");
    }

    @BindingAdapter("setRecipeIngredients")
    public static void setRecipeIngredients(TextView textView, RecipeMain.Recipe recipe){
        if( recipe == null){
            return;
        }
        textView.setText(String.valueOf(recipe.getIngredientLines().size()) + " Ingredients");


    }

    @BindingAdapter("setimageUrl")
    public static void setimageUrl( final ImageView imageView, final RecipeMain.Recipe recipe){
        if(recipe == null){
            return;
        }

       final Context context  = imageView.getContext();

       final RequestOptions options  = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);


       Runnable runnable = new Runnable() {
           @Override
           public void run() {

               Glide.with(context)
                       .setDefaultRequestOptions(options)
                       .load(recipe.getImage())
                       .into(imageView);
           }
       };

       runnable.run();

    }
}

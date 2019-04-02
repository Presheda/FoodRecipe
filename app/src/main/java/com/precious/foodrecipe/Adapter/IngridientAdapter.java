package com.precious.foodrecipe.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.precious.foodrecipe.BR;
import com.precious.foodrecipe.R;
import com.precious.foodrecipe.databinding.IngredientsListBinding;

import java.util.List;

public class IngridientAdapter extends RecyclerView.Adapter<IngridientAdapter.IngridientViewHolder> {
    Context mContext;

    public List<String> getStringList() {
        return mStringList;
    }

    public void setStringList(List<String> stringList) {
        mStringList = stringList;
        notifyDataSetChanged();
    }

    List<String> mStringList;


    public IngridientAdapter(Context context, List<String> stringList) {
        mContext = context;
        mStringList = stringList;
    }

    @NonNull
    @Override
    public IngridientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        IngredientsListBinding mListBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.ingredients_list,
                viewGroup, false );

        return new IngridientViewHolder(mListBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull IngridientViewHolder ingridientViewHolder, int i) {

        ingridientViewHolder.mListBinding.setTitle(mStringList.get(i));

        ingridientViewHolder.mListBinding.setVariable(BR.title, mStringList.get(i));

        ingridientViewHolder.mListBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        if(mStringList != null){
            return mStringList.size();
        }

        return 0;
    }

    class IngridientViewHolder extends RecyclerView.ViewHolder {

        IngredientsListBinding mListBinding;

        public IngridientViewHolder(@NonNull View itemView) {
            super(itemView);
            mListBinding = DataBindingUtil.getBinding(itemView);
        }
    }
}

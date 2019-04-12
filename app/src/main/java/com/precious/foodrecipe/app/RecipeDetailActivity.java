package com.precious.foodrecipe.app;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.precious.foodrecipe.Adapter.HealthLabelAdapter;
import com.precious.foodrecipe.Adapter.IngridientAdapter;
import com.precious.foodrecipe.R;
import com.precious.foodrecipe.databinding.ActivityRecipeDetailBinding;
import com.precious.foodrecipe.model.RecipeMain;

public class RecipeDetailActivity extends AppCompatActivity {

    ActivityRecipeDetailBinding mBinding;
    private RecipeMain.Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);

        mRecipe = (RecipeMain.Recipe) getIntent().getParcelableExtra(MainActivity.RECIPE_KEY);

        Bundle extras = getIntent().getExtras();

        if(mRecipe != null){
            mBinding.setRecipe(mRecipe);

        }

        setUpRecyclerViews();
        setupAnimation();


        mBinding.imageViewDetail.setTransitionName(extras.getString(MainActivity.EXTRA_PET_TRANSITION_NAME));
        RequestOptions options  = new RequestOptions()
                .placeholder(R.drawable.glide_background)
                .error(R.drawable.glide_background);

        Glide.with(this)
                .setDefaultRequestOptions(options)
                .load(mRecipe.getImage())
                .into(mBinding.imageViewDetail);

        setupToolBar();


        visitSource();
//
//
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()
//                .penaltyLog()
//                .build()
//        );

    }

    private void visitSource() {
        mBinding.detailNested.viewSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mRecipe.getUrl();
                Uri webPage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }

            }
        });
    }

    private void setupToolBar() {
        mBinding.collapsingToolbar.setTitle(mRecipe.getLabel());
        setSupportActionBar(mBinding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void setupAnimation() {
//
//        Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
//
//        enterTransition.setDuration(1000);

        Slide slide = new Slide();
        slide.setDuration(1000);
        slide.setSlideEdge(Gravity.LEFT);

        getWindow().setExitTransition(slide);

        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);

    }

    private void setUpRecyclerViews() {
        IngridientAdapter ingridientAdapter = new IngridientAdapter(this, mRecipe.getIngredientLines());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mBinding.detailNested.ingridientsRecycler.setLayoutManager(layoutManager);
        mBinding.detailNested.ingridientsRecycler.setAdapter(ingridientAdapter);

        HealthLabelAdapter healthLabelAdapter = new HealthLabelAdapter(this, mRecipe.getHealthLabels());
        layoutManager = new LinearLayoutManager(this);
        mBinding.detailNested.healthLabel.setLayoutManager(layoutManager);
        mBinding.detailNested.healthLabel.setAdapter(healthLabelAdapter);


        mBinding.detailNested.setSecondrecipe(mRecipe);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finishAfterTransition();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
    }
}

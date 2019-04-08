package com.precious.foodrecipe.model;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FoodExecutor  {

    private static Object mObject = new Object();

    public FoodExecutor(Executor mainExecutor) {
        this.mainExecutor = mainExecutor;
    }

    private Executor mainExecutor;
    private static FoodExecutor mInstance;




    public static FoodExecutor getInstance(){
        if(mInstance == null){
            synchronized (mObject){
                mInstance = new FoodExecutor(Executors.newFixedThreadPool(3));
            }
        }
        return mInstance;
    }

    public Executor getMainExecutor() {
        return mainExecutor;
    }
}

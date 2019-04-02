package com.precious.foodrecipe.services;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeServiceBuilder {

    private static final String BASEURL = "https://api.edamam.com/";

    //create logger
    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);


    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder()
                   .connectTimeout(60, TimeUnit.SECONDS)

                    .retryOnConnectionFailure(false)
            .addInterceptor(logger);

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder().baseUrl(BASEURL).
                    addConverterFactory(GsonConverterFactory.create()).client(okHttp.build());


    private static Retrofit sRetrofit = sBuilder.build();


    public static <S> S buidService(Class<S> serviceType) {
        return sRetrofit.create(serviceType);
    }


}

package com.company.newsx;

import android.content.Context;

import com.company.newsx.models.NewsAPIResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}

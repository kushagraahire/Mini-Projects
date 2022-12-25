package com.company.newsx.Interfaces;

import com.company.newsx.models.NewsAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInfoAPI {
    @GET("/v2/top-headlines")
    Call<NewsAPIResponse> callHeadlines (
            @Query("country") String country,
            @Query ("category") String category,
            @Query("q") String query,
            @Query("apiKey") String api_key
    );
}

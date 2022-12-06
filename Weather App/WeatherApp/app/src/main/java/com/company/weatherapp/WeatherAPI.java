package com.company.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("data/2.5/weather?appid=6b80aba131813c2bd9949236dd679b57&units=metric")
    Call<OpenWeatherMap> getWeatherWithLocation(@Query("lat") double lat ,@Query("lon") double lon);

    @GET("data/2.5/weather?appid=6b80aba131813c2bd9949236dd679b57&units=metric")
    Call<OpenWeatherMap> getWeatherWithCityName(@Query("q") String name);
}

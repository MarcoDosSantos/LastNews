package com.example.recyclerviewudemy.utils;

import com.example.recyclerviewudemy.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsRetrofitService {

    @GET("top-headlines")
    Call<Response> getArticles(@Query("country") String country, @Query("apiKey") String apiKey);
}

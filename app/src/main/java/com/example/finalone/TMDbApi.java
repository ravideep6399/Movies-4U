package com.example.finalone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbApi {
    @GET("movie/popular")
    Call<MovieNetworkResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieNetworkResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}

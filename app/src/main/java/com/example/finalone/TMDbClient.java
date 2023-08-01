package com.example.finalone;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TMDbClient {
    private static final Object LOCK = new Object();
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static  TMDbApi sTMDbApi;
    private static  TMDbClient sInstance;

    private TMDbClient(){

    }

    public static TMDbClient getInstance(){
        if(sInstance == null||sTMDbApi == null){
            synchronized (LOCK){
                //building Okhttp Client
                OkHttpClient HttpClient = new OkHttpClient.Builder().build();
                //build retrofit Instance
                Retrofit.Builder builder =new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(HttpClient)
                        .addConverterFactory(GsonConverterFactory.create());
                // creating Api from retrofit Instance
                sInstance = new TMDbClient();
                sTMDbApi = builder.build().create(TMDbApi.class);
            }
        }
        return sInstance;
    }
    public LiveData<List<Movie>> getPopularMovies(String apiKey){
        final MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
        sTMDbApi.getPopularMovies(apiKey).enqueue(new Callback<MovieNetworkResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieNetworkResponse> call, @NonNull Response<MovieNetworkResponse> response) {
                if(response.isSuccessful()||response.body()!=null){
                    assert response.body() != null;
                    mutableLiveData.postValue(response.body().getMovies());
                    Log.d("test",mutableLiveData.toString());
                }else{
                    Log.d("failure", response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieNetworkResponse> call, @NonNull Throwable t) {
                Log.getStackTraceString(t);
            }
        });
        return mutableLiveData;
    }

    public  LiveData<List<Movie>> getTopRatedMovies(String apiKey){
        final MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
        sTMDbApi.getTopRatedMovies(apiKey).enqueue(new Callback<MovieNetworkResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieNetworkResponse> call, @NonNull Response<MovieNetworkResponse> response) {
                if(response.isSuccessful()||response.body()!=null){
                    assert response.body() != null;
                    mutableLiveData.postValue(response.body().getMovies());
                }else{
                    Log.d("failure", response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieNetworkResponse> call, @NonNull Throwable t) {
                Log.getStackTraceString(t);
            }
        });
        return mutableLiveData;
    }
}

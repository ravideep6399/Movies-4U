package com.example.finalone;

import com.google.gson.annotations.SerializedName;

public class Movie {
    public static final String BASE_IMG_URL = "https://image.tmdb.org/t/p";
    public static final String BASE_POSTER_LARGE_URL = BASE_IMG_URL+"/w342";

    @SerializedName("title")  String title;
    @SerializedName("vote_average") float votes;
    @SerializedName("poster_path") String posterPath;

    public String getLargePosterUrl(){return BASE_POSTER_LARGE_URL+posterPath;}

    public Movie(String title, float votes, String posterPath) {
        this.title = title;
        this.votes = votes;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVotes() {
        return votes;
    }

    public void setVotes(float votes) {
        this.votes = votes;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}

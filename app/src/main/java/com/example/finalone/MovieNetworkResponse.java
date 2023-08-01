package com.example.finalone;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieNetworkResponse {
    @SerializedName("page") Integer page;
    @SerializedName("total_results") Integer totalResults;
    @SerializedName("total_pages") Integer totalPages;
    @SerializedName("results")
    List<Movie> movies;

    public MovieNetworkResponse(Integer page, Integer totalResults, Integer totalPages, List<Movie> movies) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.movies = movies;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

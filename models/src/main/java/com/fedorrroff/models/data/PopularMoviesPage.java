package com.fedorrroff.models.data;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PopularMoviesPage {

    @SerializedName("page")
    private Integer page;

    @SerializedName("total_MovieItems")
    private Integer totalMovieItems;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("results")
    private List<MovieItem> movieItems = null;

    public PopularMoviesPage() {
    }

    public PopularMoviesPage(Integer page, Integer totalMovieItems, Integer totalPages, List<MovieItem> movies) {
        super();
        this.page = page;
        this.totalMovieItems = totalMovieItems;
        this.totalPages = totalPages;
        this.movieItems = movies;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalMovieItems() {
        return totalMovieItems;
    }

    public void setTotalMovieItems(Integer totalMovieItems) {
        this.totalMovieItems = totalMovieItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieItem> getMovieItems() {
        return movieItems;
    }

    public void setMovieItems(List<MovieItem> movieItems) {
        this.movieItems = movieItems;
    }

}

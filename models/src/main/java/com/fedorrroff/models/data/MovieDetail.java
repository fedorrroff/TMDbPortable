
package com.fedorrroff.models.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieDetail {

    @SerializedName("id")
    private Integer id;

    @SerializedName("results")
    private List<MovieTrailer> results = null;

    public MovieDetail() {
    }

    public MovieDetail(Integer id, List<MovieTrailer> results) {
        super();
        this.id = id;
        this.results = results;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieTrailer> getResults() {
        return results;
    }

    public void setResults(List<MovieTrailer> results) {
        this.results = results;
    }

}

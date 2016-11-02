package com.example.models.moviesapp;

/**
 * Created by welcome on 11/2/2016.
 */

public class MovieModel {

    private String poster_path;
    private String overview;
    private String  release_date;
    private String original_title;

    public Integer getVote_average() {
        return vote_average;
    }

    public void setVote_average(Integer vote_average) {
        this.vote_average = vote_average;
    }

    private Integer vote_average;



    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }



}






package com.nanodegree.arghya.popularmovies.app;

/**
 * Created by arghyadip on 21/9/16.
 */

public class MovieDetails {

    public String movieID="";
    public String movieTitle = "";
    public String posterThumbnail = "";
    public String movieOverview = "";
    public String userRating = "";
    public String releaseDate = "";


    public void MovieDetails(String id,String title, String thumbnail, String overview, String rating, String date) {

        this.movieID = id;
        this.movieTitle = title;
        this.posterThumbnail = thumbnail;
        this.movieOverview = overview;
        this.userRating = rating;
        this.releaseDate = date;

    }


}

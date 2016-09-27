package com.nanodegree.arghya.popularmovies.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;

import static java.lang.System.in;

/**
 * Created by arghyadip on 26/9/16.
 */

public class ParcelableMovieDetails implements Parcelable {


    public String movieID = "";
    public String movieTitle = "";
    public String posterThumbnail = "";
    public String movieOverview = "";
    public String userRating = "";
    public String releaseDate = "";
    public String backDrop = "";

    ParcelableMovieDetails(String id, String title, String poster, String overview, String rating,
                           String date, String backdrop) {

        this.movieID = id;
        this.movieTitle = title;
        this.posterThumbnail = poster;
        this.movieOverview = overview;
        this.userRating = rating;
        this.releaseDate = date;
        this.backDrop = backdrop;

    }

    ParcelableMovieDetails(Parcel parcel) {

        this.movieID = parcel.readString();
        this.movieTitle = parcel.readString();
        this.posterThumbnail = parcel.readString();
        this.movieOverview = parcel.readString();
        this.userRating = parcel.readString();
        this.releaseDate = parcel.readString();
        this.backDrop = parcel.readString();
    }


    public static final Parcelable.Creator<ParcelableMovieDetails> CREATOR = new Parcelable.Creator<ParcelableMovieDetails>() {

        @Override
        public ParcelableMovieDetails createFromParcel(Parcel source) {
            return new ParcelableMovieDetails(source);
        }

        @Override
        public ParcelableMovieDetails[] newArray(int size) {
            return new ParcelableMovieDetails[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(movieID);
        dest.writeString(movieTitle);
        dest.writeString(posterThumbnail);
        dest.writeString(movieOverview);
        dest.writeString(userRating);
        dest.writeString(releaseDate);
        dest.writeString(backDrop);

    }

}

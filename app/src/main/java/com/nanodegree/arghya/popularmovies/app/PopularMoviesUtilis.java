package com.nanodegree.arghya.popularmovies.app;

import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by arghyadip on 21/9/16.
 */

public class PopularMoviesUtilis {

    static final String POPULAR_MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie/";
    //static final String SORT_ORDER_POPULAR = "popular";
    //static final String SORT_ORDER_TOP_RATED = "top_rated";
    static final String API_KEY = "api_key";

    static final String POPULAR_MOVIES_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";

    public static URL getMovieURL(String sortOrder) throws MalformedURLException {

        String BASE_URL = POPULAR_MOVIES_BASE_URL + sortOrder;
        Uri mUriMovies = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY, BuildConfig.POPULAR_MOVIES_API_KEY)
                .build();

        URL mUrl = new URL(mUriMovies.toString());

        Log.d("URL","URL = "+ mUrl.toString());
        return mUrl;

    }

    public static URL getPosterURL(String posterPath) throws MalformedURLException {

        Uri mUriMovies = Uri.parse(POPULAR_MOVIES_POSTER_BASE_URL+"/"+posterPath).buildUpon().build();

        URL mUrl = new URL(mUriMovies.toString());

        Log.d("URL","URL Poster = " + mUrl.toString());
        return mUrl;

    }

}

package com.nanodegree.arghya.popularmovies.app;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by arghyadip on 19/9/16.
 */

public class PopularMovieFragment extends android.support.v4.app.Fragment {

    //Using v4 fragment library since minSdkVersion is 10, and Fragments were introduced
    // in Android 11

    public PopularMovieFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View parentView = inflater.inflate(R.layout.popular_movie_fragment,container,false);
        GridView gridView = (GridView) parentView.findViewById(R.id.movie_grid);

        Toast.makeText(getActivity(),"Yes!",Toast.LENGTH_SHORT).show();
        return parentView;
    }

}

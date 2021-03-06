package com.nanodegree.arghya.popularmovies.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by arghyadip on 21/9/16.
 */

public class PopularMovieAdapter extends ArrayAdapter {

    Context mContext;
    ArrayList<MovieDetails> movieList;


    public PopularMovieAdapter(Context context, int resource, ArrayList<MovieDetails> movieList) {
        super(context, 0, movieList);

        this.mContext = context;
        this.movieList = movieList;

    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        URL mPosterURL = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.image_view, parent, false);
        }

        imageView = (ImageView) convertView.findViewById(R.id.poster_image);
        if (movieList.size() == 0) {
            return null;
        }

        //Get Image URL
        try {

            mPosterURL = PopularMoviesUtilis.getPosterURL(movieList.get(position).posterThumbnail);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Picasso.with(mContext).load(mPosterURL.toString()).fit().into(imageView);


        return imageView;
    }

}

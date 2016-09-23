package com.nanodegree.arghya.popularmovies.app;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.print.PrintHelper.ORIENTATION_LANDSCAPE;
import static android.support.v4.print.PrintHelper.ORIENTATION_PORTRAIT;
import static com.nanodegree.arghya.popularmovies.app.PopularMoviesUtilis.getPosterURL;

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
            imageView = new ImageView(mContext);


            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int imageHeight = 560, imageWidth = 370;
            if (((Activity) getContext()).getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {

                imageHeight = displayMetrics.heightPixels / 2;
                imageWidth = displayMetrics.widthPixels / 2;
            }else {

                imageHeight = displayMetrics.heightPixels;
                imageWidth = displayMetrics.widthPixels / 4;

            }



            /*imageView.setLayoutParams(new GridView.LayoutParams(370, 560));*/
            imageView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageHeight - 80));
            //imageView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageHeight - 80));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        if (movieList.size() == 0) {
            return null;
        }

        //Get Image URL
        try {

            mPosterURL = PopularMoviesUtilis.getPosterURL(movieList.get(position).posterThumbnail);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Picasso.with(mContext).load(mPosterURL.toString()).into(imageView);


        return imageView;
    }

}

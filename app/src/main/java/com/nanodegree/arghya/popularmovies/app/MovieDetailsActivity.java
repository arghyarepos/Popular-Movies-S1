package com.nanodegree.arghya.popularmovies.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

public class MovieDetailsActivity extends AppCompatActivity {

    //final String LOG_TAG = MovieDetailsActivity.class.getSimpleName();
    private ImageView mPosterImageView, mBackImage;
    private TextView mTitleTxt, mReleaseDateTxt, mRatingTxt, mOverviewTxt;
    URL mURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mPosterImageView = (ImageView) findViewById(R.id.poster_image);
        mBackImage = (ImageView) findViewById(R.id.background_image);
        mTitleTxt = (TextView) findViewById(R.id.title);
        mReleaseDateTxt = (TextView) findViewById(R.id.release_date);
        mRatingTxt = (TextView) findViewById(R.id.rating);
        mOverviewTxt = (TextView) findViewById(R.id.plot_synopsis);

        ParcelableMovieDetails mD = getIntent().getParcelableExtra(PopularMovieFragment.PARCEL_KEY);
        if (mD != null) {

            try {

                mURL = PopularMoviesUtilis.getPosterURL(mD.posterThumbnail);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            getSupportActionBar().setTitle(mD.movieTitle);
            Picasso.with(this).load(mURL.toString()).fit().into(mPosterImageView);
            mTitleTxt.setText(mD.movieTitle);
            mReleaseDateTxt.setText(getString(R.string.string_release_date) + ": " + mD.releaseDate);
            mRatingTxt.setText(mD.userRating + "/10");
            mOverviewTxt.setText(mD.movieOverview);


            try {

                mURL = PopularMoviesUtilis.getPosterURL(mD.backDrop);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Picasso.with(getApplicationContext()).load(mURL.toString()).fit().into(mBackImage);

        }

    }

}

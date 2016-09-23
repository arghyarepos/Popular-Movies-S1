package com.nanodegree.arghya.popularmovies.app;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.os.Build.VERSION_CODES.M;

public class PopularMovieMainActivity extends AppCompatActivity {

    private static final String MOVIE_FRAGMENT_TAG = PopularMovieFragment.class.getSimpleName();
    private  PopularMovieFragment mMovieFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie_main);

        FragmentManager mFM = getSupportFragmentManager();
        mMovieFragment = (PopularMovieFragment) mFM.findFragmentByTag(MOVIE_FRAGMENT_TAG);

        if (mMovieFragment == null) {
            mMovieFragment = new PopularMovieFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mMovieFragment,MOVIE_FRAGMENT_TAG).commit();
        }
    }
}

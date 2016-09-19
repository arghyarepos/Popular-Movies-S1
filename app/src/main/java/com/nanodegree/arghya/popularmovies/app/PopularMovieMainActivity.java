package com.nanodegree.arghya.popularmovies.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PopularMovieMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PopularMovieFragment()).commit();
        }
    }
}

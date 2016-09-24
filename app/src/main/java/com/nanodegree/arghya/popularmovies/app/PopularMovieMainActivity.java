package com.nanodegree.arghya.popularmovies.app;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class PopularMovieMainActivity extends AppCompatActivity {

    private static final String MOVIE_FRAGMENT_TAG = PopularMovieFragment.class.getSimpleName();
    private  PopularMovieFragment mMovieFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie_main);

        Toolbar mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);

        FragmentManager mFM = getSupportFragmentManager();
        mMovieFragment = (PopularMovieFragment) mFM.findFragmentByTag(MOVIE_FRAGMENT_TAG);

        if (mMovieFragment == null) {
            mMovieFragment = new PopularMovieFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mMovieFragment,MOVIE_FRAGMENT_TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mMenuInflator = getMenuInflater();
        mMenuInflator.inflate(R.menu.menu_settings,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.sort_order:
                //Launching settings activity/Fragment based of version.
                Toast.makeText(this, "Sort", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,SettingsActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

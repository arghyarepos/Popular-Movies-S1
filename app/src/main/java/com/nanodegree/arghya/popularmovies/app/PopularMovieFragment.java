package com.nanodegree.arghya.popularmovies.app;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arghyadip on 19/9/16.
 */

public class PopularMovieFragment extends android.support.v4.app.Fragment {

    //Using v4 fragment library since minSdkVersion is 10, and Fragments were introduced
    // in Android 11

    PopularMovieAdapter mAdapter;
    ArrayList<MovieDetails> mList;
    GridView gridView;

    public PopularMovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View parentView = inflater.inflate(R.layout.popular_movie_fragment, container, false);
        gridView = (GridView) parentView.findViewById(R.id.movie_grid);




        Toast.makeText(getActivity(), "Yes!", Toast.LENGTH_SHORT).show();


        return parentView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPopularMovies();
        setRetainInstance(true); //Retain instances
    }

    @Override
    public void onAttach(Context context) {

        //Since   setRetainInstance(true) - onAttach() , onActivityCreated(), onDetach() will be called.

         super.onAttach(context);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mList != null) {
            mAdapter = new PopularMovieAdapter(getActivity(), 0, mList);
            gridView.setAdapter(mAdapter);
        }

    }



    private void getPopularMovies() {

        FetchMovieListsTask fetchMovieTask = new FetchMovieListsTask();
        fetchMovieTask.execute(PopularMoviesUtilis.SORT_ORDER_POPULAR);

    }

    public class FetchMovieListsTask extends AsyncTask<String, Void, ArrayList<MovieDetails>> {

        private final String LOG_TAG = FetchMovieListsTask.class.getSimpleName();
        private String mJsonResponse = "";

        private ArrayList<MovieDetails> getMovieList(String jsonResponse) throws JSONException {

            //This method is parsing the JSON response and creating a list of movies.

            ArrayList<MovieDetails> mMovieList = new ArrayList<>();

            final String MOVIE_ID_TAG = "id";
            final String MOVIE_RESULT_TAG = "results";
            final String MOVIE_TITLE_TAG = "original_title";
            final String MOVIE_POSTER_TAG = "poster_path";
            final String MOVIE_OVERVIEW_TAG = "overview";
            final String MOVIE_RATING_TAG = "vote_average";
            final String MOVIE_RELEASE_DATE_TAG = "release_date";

            //Getting JSON array
            JSONObject mMovieJsonObject = new JSONObject(jsonResponse);
            JSONArray mMovieArray = mMovieJsonObject.getJSONArray(MOVIE_RESULT_TAG);

            //Reading movies and creating a list
            for (int i = 0; i < mMovieArray.length(); i++) {

                JSONObject mEachMovieObject = mMovieArray.getJSONObject(i);

                //Populate the Arraylist
                MovieDetails mDetails = new MovieDetails();

                mDetails.movieID = mEachMovieObject.getString(MOVIE_ID_TAG);
                mDetails.movieTitle = mEachMovieObject.getString(MOVIE_TITLE_TAG);
                mDetails.releaseDate = mEachMovieObject.getString(MOVIE_RELEASE_DATE_TAG);
                mDetails.movieOverview = mEachMovieObject.getString(MOVIE_OVERVIEW_TAG);
                mDetails.posterThumbnail = mEachMovieObject.getString(MOVIE_POSTER_TAG).toString().split("/")[1];

                Log.d(LOG_TAG, "List poster = " + i + " " + mDetails.posterThumbnail);
                mDetails.userRating = mEachMovieObject.getString(MOVIE_RATING_TAG);

                //Adding each element
                mMovieList.add(mDetails);


                //Setting these values as null.
                mDetails = null;
                mEachMovieObject = null;
            }


            Log.d(LOG_TAG, "List Length = " + mMovieArray.length());


            return mMovieList;

        }

        @Override
        protected ArrayList<MovieDetails> doInBackground(String... params) {

            HttpURLConnection mHttpUrlConnection = null;
            BufferedReader mBufferedReader = null;
            URL mUrl = null;

            try {

                try {
                    mUrl = PopularMoviesUtilis.getMovieURL(params[0]);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                //Creating HttpUrlConnection object and creating connection.
                mHttpUrlConnection = (HttpURLConnection) mUrl.openConnection();
                mHttpUrlConnection.setRequestMethod("GET");
                mHttpUrlConnection.connect();

                //Reading input string
                InputStream mInputString = mHttpUrlConnection.getInputStream();
                StringBuffer mBuffer = new StringBuffer();

                if (mInputString == null) {
                    return null;
                }

                mBufferedReader = new BufferedReader(new InputStreamReader(mInputString));

                String responseLine = "";

                while ((responseLine = mBufferedReader.readLine()) != null) {
                    mBuffer.append(responseLine + "\n");
                }

                if (mBuffer.length() == 0) {
                    return null;
                }

                mJsonResponse = mBuffer.toString();

                Log.d(LOG_TAG, "Resposne = " + mJsonResponse);


            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (mHttpUrlConnection != null) {
                    mHttpUrlConnection.disconnect();
                }

                if (mBufferedReader != null) {
                    try {
                        mBufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                return getMovieList(mJsonResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(ArrayList<MovieDetails> eachMovie) {



            if (eachMovie != null && eachMovie.size() > 0) {
                mList = eachMovie; //Getting the list

                if(mAdapter!= null) {
                    mAdapter.clear();
                }
               /* for (MovieDetails movie : eachMovie) {
                    mAdapter.add(movie);
                }*/

                if (mList != null) {
                    mAdapter = new PopularMovieAdapter(getActivity(), 0, mList);
                }

                gridView.setAdapter(mAdapter);
            }




        }
    }

}

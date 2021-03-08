package com.example.mymoviebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements  MovieFragment.OnMovieSelected{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void movieSelected(Movie movie) {
        //implement this methot when we implements ..
        Log.d("Main activity", movie.getName());

    }
}
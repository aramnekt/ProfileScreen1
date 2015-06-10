package com.example.prokarma.myapplication.widget;

import android.app.Activity;
import android.os.Bundle;

import com.example.prokarma.myapplication.R;


public class DummyActivity extends Activity {

    private static final String[] GENRES = new String[]{
            "Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
            "Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact_view);

    }
}
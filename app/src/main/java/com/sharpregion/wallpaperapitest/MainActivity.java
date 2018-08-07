package com.sharpregion.wallpaperapitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.wallpaper_button).setOnClickListener(v -> Wallpaper.apply(this, this::finish));
    }
}

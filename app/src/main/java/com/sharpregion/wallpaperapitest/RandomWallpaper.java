package com.sharpregion.wallpaperapitest;

import android.app.Activity;
import android.os.Bundle;

public class RandomWallpaper extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Wallpaper.apply(this, this::finish);
    }
}

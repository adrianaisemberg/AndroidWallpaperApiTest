package com.sharpregion.wallpaperapitest;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.io.IOException;
import java.util.Random;

public class Wallpaper {
    public static void apply(Context context, Runnable done) {
        new Thread(() -> applyRandomWallpaper(context, done)).start();
    }

    private static void applyRandomWallpaper(Context context, Runnable done) {
        DisplayMetrics displayMetric = Resources.getSystem().getDisplayMetrics();
        int height = displayMetric.heightPixels;
        int width = displayMetric.widthPixels * 2; // support parallax
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(255, getRandomColor(), getRandomColor(), getRandomColor()));
        paint.setStrokeWidth(5);

        canvas.drawRGB(0, 0, 0);

        int gridSize = 100;

        for (int y = 0; y <= height; y += gridSize) {
            canvas.drawLine(0, y, width, y, paint);
        }

        for (int x = 0; x <= width; x += gridSize) {
            canvas.drawLine(x, 0, x, height, paint);
        }

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK | WallpaperManager.FLAG_SYSTEM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (done != null) {
            done.run();
        }
    }

    private static int getRandomColor() {
        return new Random().nextInt(156) + 100; // pick a bright RGB value
    }
}

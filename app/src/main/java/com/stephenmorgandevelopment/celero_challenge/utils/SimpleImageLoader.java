package com.stephenmorgandevelopment.celero_challenge.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleImageLoader extends Thread {
    private static final String TAG = SimpleImageLoader.class.getSimpleName();
    private URL url;
    private ImageView imageView;
    private Activity activity;

    public SimpleImageLoader(Activity activity,ImageView imageView, String url) {
        this.activity = activity;
        this.imageView = imageView;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException mue) {
            Log.d(TAG, "Error parsing url.");
            mue.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            if(url != null) {
                final Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                if(bitmap != null) {
                    activity.runOnUiThread(() -> {
                        if(imageView != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        } catch (IOException ioe) {
            Log.d(TAG, "Error reading input stream.");
            ioe.printStackTrace();
        }
    }
}

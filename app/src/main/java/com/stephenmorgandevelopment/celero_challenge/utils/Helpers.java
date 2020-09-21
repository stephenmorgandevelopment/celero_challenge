package com.stephenmorgandevelopment.celero_challenge.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;

public class Helpers {
    private static Context applicationContext;
    private static File cacheDir;
    private static float screenDensity;

    public static void init(Application app) {
        setApplicationContext(app);
        setCacheDir(app.getCacheDir());
        setScreenDensity(app.getResources().getDisplayMetrics().density);
    }

    public static boolean hasInternet() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isConnectedOrConnecting();
            }
        }

        return false;
    }

    private static void setApplicationContext(Application application) {
        applicationContext = application.getApplicationContext();
    }

    public static Context getApplicationContext() {
        return applicationContext;
    }

    private static void setCacheDir(File dir) {
        cacheDir = dir;
    }

    public static File getCacheDir() {
        return cacheDir;
    }

    public static void setScreenDensity(float dens) {
        screenDensity = dens;
    }

    public static float getScreenDensity() {
        return screenDensity;
    }
}

package com.stephenmorgandevelopment.celero_challenge.utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static HttpClient httpInstance;

    private static Cache cache;
    private static OkHttpClient okClient;
    private static Retrofit retrofitClient;

    private HttpClient() {
        if(cache == null) {
            cache = new okhttp3.Cache(new File(Helpers.getCacheDir(), "http_cache"), 10485760);
        }

        okClient = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        retrofitClient = new Retrofit.Builder()
                .baseUrl("https://hulet.tech/")
                //.client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static OkHttpClient getOkClient() {
        if(okClient == null) {
            httpInstance = new HttpClient();
        }

        return okClient;
    }

    public static Retrofit getRetrofitClient() {
        if(retrofitClient == null) {
            httpInstance = new HttpClient();
        }

        return retrofitClient;
    }

}

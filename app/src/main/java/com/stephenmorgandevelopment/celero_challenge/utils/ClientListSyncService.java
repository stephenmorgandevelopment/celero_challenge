package com.stephenmorgandevelopment.celero_challenge.utils;

import com.stephenmorgandevelopment.celero_challenge.models.Client;

import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClientListSyncService {
    @GET("{listId}")
    Call<List<Client>>  pullClientList(@Path("listId") String listId);

}

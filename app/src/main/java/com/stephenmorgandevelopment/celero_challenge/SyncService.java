package com.stephenmorgandevelopment.celero_challenge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.stephenmorgandevelopment.celero_challenge.database.WorkDatabase;
import com.stephenmorgandevelopment.celero_challenge.models.Client;
import com.stephenmorgandevelopment.celero_challenge.utils.ClientListSyncService;
import com.stephenmorgandevelopment.celero_challenge.utils.Helpers;
import com.stephenmorgandevelopment.celero_challenge.utils.HttpClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncService extends JobIntentService {
    public static final String TAG = SyncService.class.getSimpleName();
    public static final int JOB_ID = 5001;

    private static volatile boolean working = false;
    private static final String listId = "celerocustomers.json";

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (Helpers.hasInternet()) {
            working = true;

            ClientListSyncService service = HttpClient.getRetrofitClient().create(ClientListSyncService.class);
            Call<List<Client>> call = service.pullClientList(listId);

            call.enqueue(new Callback<List<Client>>() {
                @Override
                public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                    WorkDatabase.getInstance().wipeTable();
                    WorkDatabase.getInstance().addClientList(response.body());
                    working = false;
                }

                @Override
                public void onFailure(Call<List<Client>> call, Throwable t) {
                    Log.d(TAG, "Retrofit call failed.");
                    t.printStackTrace();
                    working = false;
                }
            });
        } else {
            Log.d(TAG, "No internet.  Cannot sync client list.");
        }
    }

    @Override
    public boolean onStopCurrentWork() {
        working = false;

        return super.onStopCurrentWork();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        working = false;
    }

    public static void enqueueWork(Context context, Intent work) {
        working = true;
        enqueueWork(context, SyncService.class, JOB_ID, work);
    }

    public static boolean isWorking() {
        return working;
    }
}

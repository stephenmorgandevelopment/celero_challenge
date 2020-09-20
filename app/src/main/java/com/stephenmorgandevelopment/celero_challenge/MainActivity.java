package com.stephenmorgandevelopment.celero_challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stephenmorgandevelopment.celero_challenge.database.WorkDatabase;
import com.stephenmorgandevelopment.celero_challenge.models.SimpleClient;
import com.stephenmorgandevelopment.celero_challenge.utils.CurrentWorkAdapter;
import com.stephenmorgandevelopment.celero_challenge.utils.Helpers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView syncingDialog;
    private ProgressDialog progressDialog;

    private ListView clientListView;
    private CurrentWorkAdapter adapter;

    private SyncMonitor syncMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Helpers.init(MainActivity.this.getApplication());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clientListView = findViewById(R.id.client_listview);
        syncingDialog = findViewById(R.id.syncing_dialog);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(WorkDatabase.hasDatabase() && WorkDatabase.getInstance().hasData()) {
            //TODO Show listview from cached data.
            populateList();

        } else if(!SyncService.isWorking()){
            if(Helpers.hasInternet()) {
                syncingDialog.setVisibility(View.VISIBLE);
                //displayDialog();

                syncMonitor = new SyncMonitor();
                syncMonitor.start();

                Intent syncIntent = new Intent();
                SyncService.enqueueWork(MainActivity.this, syncIntent);

            } else {
                Toast.makeText(MainActivity.this, "Sync error:  Must have internet to sync.", Toast.LENGTH_LONG).show();
            }
        } else {
            if(Helpers.hasInternet()) {
                syncingDialog.setVisibility(View.VISIBLE);
                //displayDialog();

                syncMonitor = new SyncMonitor();
                syncMonitor.start();

            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (toolbar.getMenu().findItem(R.id.refreshMenuBtn) == null) {
            getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(Helpers.hasInternet()) {
            syncingDialog.setVisibility(View.VISIBLE);
            //displayDialog();

            syncMonitor = new SyncMonitor();
            syncMonitor.start();

            Intent syncIntent = new Intent();
            SyncService.enqueueWork(MainActivity.this, syncIntent);

        } else {
            Toast.makeText(MainActivity.this, "Sync error:  Must have internet to sync.", Toast.LENGTH_LONG).show();
        }


        return super.onOptionsItemSelected(item);
    }

    public void populateList() {
        //TODO Create and display listview form database.
        if(adapter == null) {
            adapter = new CurrentWorkAdapter(MainActivity.this);
            clientListView.setAdapter(adapter);
            clientListView.setOnItemClickListener(clientClickedListener);
        }

        if(WorkDatabase.hasDatabase()) {
            adapter.setCurrentWorkList(WorkDatabase.getInstance().getSimpleClients());
            adapter.notifyDataSetChanged();
        }

    }

    AdapterView.OnItemClickListener clientClickedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent detailsIntent = new Intent(MainActivity.this, ClientDetailView.class);
            detailsIntent.putExtra(ClientDetailView.IDENTIFIER_TAG, id);

            startActivity(detailsIntent);
        }
    };

    public void displayDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage(getString(R.string.syncing_dialog));
        progressDialog.show();
    }

    public void dismissDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private class SyncMonitor extends Thread {


        @Override
        public void run() {
            while(SyncService.isWorking()) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ie) {
                    Log.d("SyncMonitor", "Thead interrupted while syncing.");
                }
            }

            runOnUiThread(() -> {
                syncingDialog.setVisibility(View.GONE);
                populateList();
            });
        }
    }
}

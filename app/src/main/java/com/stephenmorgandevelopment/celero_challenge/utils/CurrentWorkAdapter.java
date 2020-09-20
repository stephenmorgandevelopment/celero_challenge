package com.stephenmorgandevelopment.celero_challenge.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.stephenmorgandevelopment.celero_challenge.R;
import com.stephenmorgandevelopment.celero_challenge.models.SimpleClient;

import java.util.ArrayList;
import java.util.List;

public class CurrentWorkAdapter extends BaseAdapter {
    private List<SimpleClient> currentWorkList;
    private LayoutInflater layoutInflater;
    private ConstraintLayout.LayoutParams layoutParams;


    public CurrentWorkAdapter(Context context) {
        currentWorkList = new ArrayList<>();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int layoutHeight = (int) Helpers.getScreenDensity() * 100;
        layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, layoutHeight);
    }

    public void setCurrentWorkList(List<SimpleClient> workList) {
        currentWorkList.clear();
        currentWorkList.addAll(workList);
    }

    @Override
    public int getCount() {
        return currentWorkList.size();
    }

    @Override
    public SimpleClient getItem(int position) {
        return currentWorkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return currentWorkList.get(position).getIdentifier();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.client_list_view, null);

        TextView clientName = view.findViewById(R.id.clients_name);
        TextView serviceReason = view.findViewById(R.id.service_reason);

        clientName.setText(currentWorkList.get(position).getName());
        serviceReason.setText(currentWorkList.get(position).getReason());

        return view;
    }
}

package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view.*;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.ViewHolder> {

    private Context context;
    private DialogsViewImpl myview;
    private List<DialogsData> data;

    public DialogsAdapter(List<DialogsData> data, DialogsViewImpl myview, Context context) {
        this.myview = myview;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle_dialog, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DialogsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemview) {
            super(itemview);
        }
    }
}

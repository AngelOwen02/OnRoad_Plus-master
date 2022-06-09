package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.toglesTracking.adaptergroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;

import java.util.ArrayList;
import java.util.List;

public class togglesGroupsAdapter extends RecyclerView.Adapter<togglesGroupsAdapter.ViewHolder> {

private List<String> groupList;
private Context context;
public togglesGroupsAdapter(List<String> vehiclesList, Context context)
        {
        this.groupList=vehiclesList;
        this.context=context;

        }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_tracking, parent, false);
        return new togglesGroupsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        groupList=UnitsInteractorImpl.dataofvehiclesgroups;
        holder.unitTitle.setText(groupList.get(position));
        Glide.with(context).load(R.drawable.car).into(holder.unitImage);
        holder.unitSwicth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                   /* firstLoginFalse();
                    unitSelected();
                    UnitDB.updateCheckedStatus(vehicle.getVehicleName(),true);*/
                }else{
                    /*UnitDB.updateCheckedStatus(vehicle.getVehicleName(),false);*/
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return UnitsInteractorImpl.dataofvehiclesgroups.size();
    }

    public void setFilter(List<String> vehicles) {
        this.groupList = new ArrayList<>();
        this.groupList.addAll(vehicles);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView unitImage;
        TextView unitTitle;
        Switch unitSwicth;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unitImage = itemView.findViewById(R.id.unit_tracking_img);
            unitTitle = itemView.findViewById(R.id.unit_tracking_title);
            unitSwicth = itemView.findViewById(R.id.unit_tracking_swicth);
        }
    }

















}

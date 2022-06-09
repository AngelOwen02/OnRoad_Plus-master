package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.toglesTracking.adapterunits;

import android.content.Context;
import android.util.Log;
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
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

public class tooglesUnitsAdapter extends RecyclerView.Adapter<tooglesUnitsAdapter.ViewHolder> {

    private List<String> vehiclesList;
    private List<String> photos;
    private Context context;
    private static List<String> stringList = new ArrayList<>();
    public tooglesUnitsAdapter(List<String> vehiclesList, Context context)
    {
        this.vehiclesList=vehiclesList;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_tracking, parent, false);
        return new tooglesUnitsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        vehiclesList=UnitsInteractorImpl.dataofvehicles;

        final String vehicle = vehiclesList.get(position);
        if (vehicle.equals(String.valueOf(holder.unitSwicth.isActivated()))){
            holder.unitSwicth.setChecked(true);
        }else {
            holder.unitSwicth.setChecked(false);
        }
        photos=UnitsInteractorImpl.dataphotoofvehicles;
        holder.unitTitle.setText(vehiclesList.get(position));



        if (photos.get(position)== null) {
            Glide.with(context).load(R.drawable.sedan).into(holder.unitImage);
        } else if (photos.get(position).toString().equals("null")) {
            Glide.with(context).load(R.drawable.sedan).into(holder.unitImage);
        } else if (photos.get(position).toString().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(R.drawable.sedan).into(holder.unitImage);
        } else {
            Glide.with(context).load(photos.get(position).toString()).into(holder.unitImage);
        }

        holder.unitSwicth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    stringList.add(vehicle);
                   /* firstLoginFalse();
                    unitSelected();
                    UnitDB.updateCheckedStatus(vehicle.getVehicleName(),true);*/
                }else{

                    stringList.remove(vehicle);
                    /*UnitDB.updateCheckedStatus(vehicle.getVehicleName(),false);*/
                }
                Log.e("toggles",""+stringList);
            }
        });

    }

    @Override
    public int getItemCount() {
        return UnitsInteractorImpl.dataofvehicles.size();
    }
    public void setFilter(List<String> vehicles) {
        this.vehiclesList = new ArrayList<>();
        this.vehiclesList.addAll(vehicles);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
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

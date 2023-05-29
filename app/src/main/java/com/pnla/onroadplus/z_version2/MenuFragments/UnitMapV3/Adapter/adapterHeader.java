package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;

public class adapterHeader  extends RecyclerView.Adapter<adapterHeader.ViewHolder> {

    private String vehicleName,vehicleImageURL,vehicleCurrentSpeed,vehicleTimeTravel,vehicleKmTravel,vehicleGeoreference;
    private Context context;

    public adapterHeader(String vehicleName, String vehicleImageURL, String vehicleCurrentSpeed, String vehicleTimeTravel, String vehicleKmTravel, String vehicleGeoreference, Context context) {
    this.vehicleName=vehicleName;
    this.vehicleImageURL=vehicleImageURL;
    this.vehicleCurrentSpeed=vehicleCurrentSpeed;
    this.vehicleTimeTravel=vehicleTimeTravel;
    this.vehicleKmTravel=vehicleKmTravel;
    this.vehicleGeoreference=vehicleGeoreference;
    this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headervehicle, parent, false);
        return new adapterHeader.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterHeader.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.vehicleName.setText(vehicleName);
        holder.lasincoming.setText("1 min"); //TODO aqui va el calculod de sendTima
        holder.vehicleCurrentSpeed.setText(vehicleCurrentSpeed);
        holder.vehicleTimeTravel.setText(vehicleTimeTravel);
        holder.vehicleKmTravel.setText(vehicleKmTravel);
        holder.vehicleGeoreference.setText(vehicleGeoreference);

        if (vehicleImageURL != null ) {
            Glide.with(context)
                    .load(vehicleImageURL)
                    .into(holder.vheader);
        }

    }

    @Override
    public int getItemCount()    {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintDateMapV2;
        private TextView vehicleName,vehicleCurrentSpeed,vehicleTimeTravel,vehicleKmTravel,vehicleGeoreference,lasincoming;
        private ImageView vheader,semaforo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            semaforo=itemView.findViewById(R.id.semaforo);
            vheader=itemView.findViewById(R.id.vheader);
            vehicleName=itemView.findViewById(R.id.txt_vehicle_header_name);
            vehicleCurrentSpeed=itemView.findViewById(R.id.txt_vehicle_header_speed);
            vehicleTimeTravel=itemView.findViewById(R.id.txt_vehicle_header_hour);
            vehicleKmTravel=itemView.findViewById(R.id.txt_vehicle_header_km);
            vehicleGeoreference =itemView.findViewById(R.id.txt_vehicle_header_adress);

            lasincoming=itemView.findViewById(R.id.txt_vehicle_header_last_send_time);
        }

    }

}

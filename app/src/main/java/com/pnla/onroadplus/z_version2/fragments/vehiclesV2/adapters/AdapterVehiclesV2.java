package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.adapters;

import android.content.Context;
import android.graphics.Typeface;
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
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterVehiclesV2 extends RecyclerView.Adapter<AdapterVehiclesV2.ViewHolder> {

    private List<VehicleV2> vehicles;
    private Typeface latoBoldTypeface;
    private Context context;
    private VehicleAndGroupSelected listener = null;

    public AdapterVehiclesV2(List<VehicleV2> vehicles, Context context) {
        this.vehicles = vehicles;
        this.context = context;
        this.latoBoldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle_v2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txvVehicleName.setTypeface(latoBoldTypeface);

        //Si no cuenta con imagen, colocamos una por default
        if (vehicles.get(position).getVehicleImage().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage);
        } else {
            Glide.with(context).load(vehicles.get(position).getVehicleImage()).into(holder.imgvVehicleImage);
        }

        //set image Selected or unselected
        if (vehicles.get(position).isSelected()) {
            //holder.imgvSelected.setVisibility(View.VISIBLE);
            //Glide.with(context).load(R.drawable.icon_eye).into(holder.imgvSelectedUnselected);
            holder.imgvSelected.setVisibility(View.GONE);
            Glide.with(context).load(R.drawable.eye_selected).into(holder.imgvSelectedUnselected);
        } else {
            holder.imgvSelected.setVisibility(View.VISIBLE);
            Glide.with(context).load(R.drawable.icon_eye).into(holder.imgvSelectedUnselected);
            //holder.imgvSelected.setVisibility(View.GONE);
            //Glide.with(context).load(R.drawable.eye_selected).into(holder.imgvSelectedUnselected);
        }

        //set vehicleName
        holder.txvVehicleName.setText(vehicles.get(position).getVehicleName());

        //vehicle selected click
        holder.constraintVehicleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.vehicleSelected(v, vehicles.get(position).getPositionItem(), position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public void changedData(int position, boolean newState) {
        vehicles.get(position).setSelected(newState);
        notifyDataSetChanged();
    }

    public void setFilter(List<VehicleV2> vehicles) {
        this.vehicles = new ArrayList<>();
        this.vehicles.addAll(vehicles);
        notifyDataSetChanged();
    }

    public void setVehicleAndGroupSelectedListener(VehicleAndGroupSelected listener) {
        this.listener = listener;
    }

    public interface VehicleAndGroupSelected {
        void vehicleSelected(View view, int positionListComplete, int positionFilterList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgvSelected, imgvSelectedUnselected;
        private CircleImageView imgvVehicleImage;
        private ConstraintLayout constraintVehicleItem;
        private TextView txvVehicleName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgvSelected = itemView.findViewById(R.id.imgvSelected);
            imgvSelectedUnselected = itemView.findViewById(R.id.imgvSelectedUnselected);
            imgvVehicleImage = itemView.findViewById(R.id.imgvVehicleImage);
            constraintVehicleItem = itemView.findViewById(R.id.constraintVehicleItem);
            txvVehicleName = itemView.findViewById(R.id.txvVehicleName);
        }
    }

}
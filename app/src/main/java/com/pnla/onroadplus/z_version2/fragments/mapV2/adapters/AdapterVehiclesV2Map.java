package com.pnla.onroadplus.z_version2.fragments.mapV2.adapters;

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
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterVehiclesV2Map extends RecyclerView.Adapter<AdapterVehiclesV2Map.ViewHolder> {

    private List<VehicleV2Map> vehicles;
    private Typeface latoRegularTypeface, latoBoldTypeface;
    private Context context;
    private OnClickVehiclesV2MapListener listener;

    public AdapterVehiclesV2Map(List<VehicleV2Map> vehicles, Context context) {
        this.vehicles = vehicles;
        this.context = context;
        this.latoRegularTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Regular.ttf");
        this.latoBoldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle_v2_map, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        /**Set fonts*/
        holder.txvVehicleName.setTypeface(latoBoldTypeface);
        holder.txvVehicleAddress.setTypeface(latoRegularTypeface);
        holder.txvVehicleSpeed.setTypeface(latoBoldTypeface);
        holder.txvVehicleHour.setTypeface(latoBoldTypeface);
        holder.txvVehicleKilometers.setTypeface(latoBoldTypeface);

        /**SetData*/
        holder.txvVehicleName.setText(vehicles.get(position).getVehicleName());
        holder.txvVehicleSpeed.setText(vehicles.get(position).getCurrentSpeed() + "");
        holder.txvVehicleHour.setText(vehicles.get(position).getTimeTravel());
        holder.txvVehicleAddress.setText(vehicles.get(position).getGeoreference());
        holder.txvVehicleKilometers.setText(vehicles.get(position).getKmTravel() + "");
        holder.txvVehicleHourAndSpeed.setText(vehicles.get(position).getTimeElapsed() + " | " + vehicles.get(position).getCurrentSpeed() + " km/h");

        /**Status*/
        if (vehicles.get(position).getVehicleSwitch() == 1) {
            holder.txvVehicleHourAndSpeed.setBackgroundResource(R.drawable.textview_background_speed_green);
            holder.imgvVehicleImage.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGreen));
        } else if (vehicles.get(position).getVehicleSwitch() == 2) {
            holder.txvVehicleHourAndSpeed.setBackgroundResource(R.drawable.textview_background_speed_orange);
            holder.imgvVehicleImage.setBorderColor(context.getResources().getColor(R.color.colorBorderCarOrange));
        } else if (vehicles.get(position).getVehicleSwitch() == 3) {
            holder.txvVehicleHourAndSpeed.setBackgroundResource(R.drawable.textview_background_speed_red);
            holder.imgvVehicleImage.setBorderColor(context.getResources().getColor(R.color.colorBorderCarRed));
        } else {
            holder.txvVehicleHourAndSpeed.setBackgroundResource(R.drawable.textview_background_speed_gray);
            holder.imgvVehicleImage.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGray));
        }

        /**VehicleImage*/
        if (!vehicles.get(position).getVehicleImage().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(vehicles.get(position).getVehicleImage()).into(holder.imgvVehicleImage);
        } else {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage);
        }

        /**SpeedImage*/
        Glide.with(context).load(R.drawable.maximum_speed).into(holder.imgvSpeed);

        /**HourImage*/
        Glide.with(context).load(R.drawable.icon_hour).into(holder.imgvHour);

        /**KilometersImage*/
        Glide.with(context).load(R.drawable.distance).into(holder.imgvKilometers);

        /**OnClick*/
        holder.constraintVehicleV2Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickVehiclesV2Map(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public void setOnClickVehiclesV2MapListener(OnClickVehiclesV2MapListener onClickVehiclesV2MapListener) {
        this.listener = onClickVehiclesV2MapListener;
    }

    public interface OnClickVehiclesV2MapListener {
        void onClickVehiclesV2Map(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgvVehicleImage;
        private TextView txvVehicleName, txvVehicleHourAndSpeed, txvVehicleAddress, txvVehicleSpeed, txvVehicleHour, txvVehicleKilometers;
        private ImageView imgvSpeed, imgvHour, imgvKilometers;
        private ConstraintLayout constraintVehicleV2Map;

        public ViewHolder(View itemView) {
            super(itemView);
            imgvVehicleImage = itemView.findViewById(R.id.imgvVehicleImage);
            txvVehicleName = itemView.findViewById(R.id.txvVehicleName);
            txvVehicleHourAndSpeed = itemView.findViewById(R.id.txvVehicleHourAndSpeed);
            txvVehicleAddress = itemView.findViewById(R.id.txvVehicleAddress);
            txvVehicleSpeed = itemView.findViewById(R.id.txvVehicleSpeed);
            txvVehicleHour = itemView.findViewById(R.id.txvVehicleHour);
            txvVehicleKilometers = itemView.findViewById(R.id.txvVehicleKilometers);
            imgvSpeed = itemView.findViewById(R.id.imgvSpeed);
            imgvHour = itemView.findViewById(R.id.imgvHour);
            imgvKilometers = itemView.findViewById(R.id.imgvKilometers);
            constraintVehicleV2Map = itemView.findViewById(R.id.constraintVehicleV2Map);
        }

    }

}
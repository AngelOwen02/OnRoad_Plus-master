package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.utils.MapV2Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {


    private String vehicleName;
    private List<TripV2> tripList;
    private Context context;
    private TripAdapter.OnClickTripListener listener;


    public TripAdapter(String vehicleName, List<TripV2> tripList, Context context) {
        this.vehicleName = vehicleName;
        this.tripList = tripList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip, parent, false);
        return new TripAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Glide.with(context).load(tripList.get(position).getUrlMapImage()).into(holder.imgTrip);
        holder.txtVehicleName.setText(vehicleName);

        try {
            holder.txtTripDescription.setText(tripList.get(position).getDescriptionTrip());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTripDateString = tripList.get(position).getStartTrip();
            Date hour = df.parse(startTripDateString);
            df.applyPattern("hh:mm:ss");
            String hourStartTrip = df.format(hour);
            holder.txtTripTimeStart.setText(hourStartTrip);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTripDateString = tripList.get(position).getEndTrip();
            Date hourEnd = simpleDateFormat.parse(endTripDateString);
            simpleDateFormat.applyPattern("hh:mm:ss");
            String hourEndTrip = simpleDateFormat.format(hourEnd);

            if (tripList.size() > 1) {
                if (position == tripList.size() - 1) {
                    //SUPERIOR
                    String realTime = MapV2Utils.getTimeValue(tripList.get(position).getStartTrip(), tripList.get(position).getEndTrip());
                    holder.txtTripTimeEnd.setText(hourEndTrip + "    ( " + realTime + " )");
                    //INFERIOR
                    holder.txtTripHour.setText(hourEndTrip + "");
                } else {
                    //SUPERIOR
                    String realTime = MapV2Utils.getTimeValue(tripList.get(position).getStartTrip(), tripList.get(position).getEndTrip());
                    holder.txtTripTimeEnd.setText(hourEndTrip + "    ( " + realTime + " )");
                    //INFERIOR
                    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String startTripDateString2 = tripList.get(position + 1).getStartTrip();
                    Date hour2 = df2.parse(startTripDateString2);
                    df2.applyPattern("hh:mm:ss");
                    String hourStartTrip2 = df2.format(hour2);
                    realTime = MapV2Utils.getTimeValue(tripList.get(position).getEndTrip(), tripList.get(position + 1).getStartTrip());
                    holder.txtTripHour.setText(hourEndTrip + "     " + hourStartTrip2 + "     ( " + realTime + " )");
                }
            } else {
                //SUPERIOR
                String realTime = MapV2Utils.getTimeValue(tripList.get(position).getStartTrip(), tripList.get(position).getEndTrip());
                holder.txtTripTimeEnd.setText(hourEndTrip + "    ( " + realTime + " )");
                //INFERIOR
                holder.txtTripHour.setText(hourEndTrip + "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.imgTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickGoogleImage(v, position);
                }
            }
        });

        holder.finalPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickFinalPosition(v, position);
                }
            }
        });


    }

    public void setOnClickTripListener(TripAdapter.OnClickTripListener listener) {
        this.listener = listener;
    }

    public interface OnClickTripListener {
        void onClickGoogleImage(View view, int position);

        void onClickFinalPosition(View view, int position);
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVehicleName;
        TextView txtTripTimeStart;
        TextView txtTripTimeEnd;
        TextView txtTripDescription;
        TextView txtTripHour;
        ImageView imgTrip;
        ConstraintLayout finalPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVehicleName = itemView.findViewById(R.id.txt_trip_item_name);
            txtTripTimeStart = itemView.findViewById(R.id.txt_trip_item_time_start);
            txtTripTimeEnd = itemView.findViewById(R.id.txt_trip_item_time_end);
            txtTripDescription = itemView.findViewById(R.id.txt_trip_item_description);
            txtTripHour = itemView.findViewById(R.id.txt_trip_item_hour);
            imgTrip = itemView.findViewById(R.id.img_trip_item);
            finalPosition = itemView.findViewById(R.id.constrain_layout_final_position);
        }
    }

    public void clearRecyclerView() {
        final int size = tripList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                tripList.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }
}

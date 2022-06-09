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
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.utils.MapV2Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterTripsV2 extends RecyclerView.Adapter<AdapterTripsV2.ViewHolder> {

    private List<TripV2> trips;
    private Context context;
    private Typeface latoRegular, latoBold;
    private OnClickTripListener listener;

    public AdapterTripsV2(List<TripV2> trips, Context context) {
        this.trips = trips;
        this.context = context;
        this.latoBold = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");
        this.latoRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Regular.ttf");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip_v2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txvTripKm.setTypeface(latoBold);
        holder.txvTripTimeStart.setTypeface(latoRegular);
        holder.txvTripTimeEnd.setTypeface(latoRegular);
        holder.txvDescriptionTrip.setTypeface(latoBold);
        holder.txvHourDescriptionTrip.setTypeface(latoRegular);

        Glide.with(context).load(R.drawable.trip).into(holder.imgvEvent);
        Glide.with(context).load(R.drawable.map).into(holder.imgvDescription);
        Glide.with(context).load(R.drawable.icon_date).into(holder.imgvTripTimeStart);
        Glide.with(context).load(R.drawable.map).into(holder.imgvTripTimeEnd);
        Glide.with(context).load(trips.get(position).getUrlMapImage()).into(holder.imgvTrip);

        try {
            String km = String.format("%.2f", trips.get(position).getKm());

            holder.txvTripKm.setText(context.getString(R.string.textTrip) + " " + km + " km");
            holder.txvDescriptionTrip.setText(trips.get(position).getDescriptionTrip());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTripDateString = trips.get(position).getStartTrip();
            Date hour = df.parse(startTripDateString);
            df.applyPattern("hh:mm:ss");
            String hourStartTrip = df.format(hour);
            holder.txvTripTimeStart.setText(hourStartTrip);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTripDateString = trips.get(position).getEndTrip();
            Date hourEnd = simpleDateFormat.parse(endTripDateString);
            simpleDateFormat.applyPattern("hh:mm:ss");
            String hourEndTrip = simpleDateFormat.format(hourEnd);

            if (trips.size() > 1) {
                if (position == trips.size() - 1) {
                    //SUPERIOR
                    String realTime = MapV2Utils.getTimeValue(trips.get(position).getStartTrip(), trips.get(position).getEndTrip());
                    holder.txvTripTimeEnd.setText(hourEndTrip + "    ( " + realTime + " )");
                    //INFERIOR
                    holder.txvHourDescriptionTrip.setText(hourEndTrip + "");
                } else {
                    //SUPERIOR
                    String realTime = MapV2Utils.getTimeValue(trips.get(position).getStartTrip(), trips.get(position).getEndTrip());
                    holder.txvTripTimeEnd.setText(hourEndTrip + "    ( " + realTime + " )");
                    //INFERIOR
                    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String startTripDateString2 = trips.get(position + 1).getStartTrip();
                    Date hour2 = df2.parse(startTripDateString2);
                    df2.applyPattern("hh:mm:ss");
                    String hourStartTrip2 = df2.format(hour2);
                    realTime = MapV2Utils.getTimeValue(trips.get(position).getEndTrip(), trips.get(position + 1).getStartTrip());
                    holder.txvHourDescriptionTrip.setText(hourEndTrip + "     " + hourStartTrip2 + "     ( " + realTime + " )");
                }
            } else {
                //SUPERIOR
                String realTime = MapV2Utils.getTimeValue(trips.get(position).getStartTrip(), trips.get(position).getEndTrip());
                holder.txvTripTimeEnd.setText(hourEndTrip + "    ( " + realTime + " )");
                //INFERIOR
                holder.txvHourDescriptionTrip.setText(hourEndTrip + "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.imgvTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickGoogleImage(v, position);
                }
            }
        });

        holder.constraintFinalPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickFinalPosition(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public void clearRecyclerView() {
        final int size = trips.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                trips.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }

    public void setOnClickTripListener(OnClickTripListener listener) {
        this.listener = listener;
    }

    public interface OnClickTripListener {
        void onClickGoogleImage(View view, int position);

        void onClickFinalPosition(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgvEvent, imgvDescription, imgvTrip, imgvTripTimeStart, imgvTripTimeEnd;
        private TextView txvTripKm, txvTripTimeStart, txvTripTimeEnd, txvDescriptionTrip, txvHourDescriptionTrip;
        private ConstraintLayout constraintFinalPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgvEvent = itemView.findViewById(R.id.imgvEvent);
            imgvDescription = itemView.findViewById(R.id.imgvDescription);
            imgvTrip = itemView.findViewById(R.id.imgvTrip);
            imgvTripTimeStart = itemView.findViewById(R.id.imgvTripTimeStart);
            imgvTripTimeEnd = itemView.findViewById(R.id.imgvTripTimeEnd);

            txvTripKm = itemView.findViewById(R.id.txvTripKm);
            txvTripTimeStart = itemView.findViewById(R.id.txvTripTimeStart);
            txvTripTimeEnd = itemView.findViewById(R.id.txvTripTimeEnd);
            txvDescriptionTrip = itemView.findViewById(R.id.txvDescriptionTrip);
            txvHourDescriptionTrip = itemView.findViewById(R.id.txvHourDescriptionTrip);

            constraintFinalPosition = itemView.findViewById(R.id.constraintFinalPosition);
        }

    }

}

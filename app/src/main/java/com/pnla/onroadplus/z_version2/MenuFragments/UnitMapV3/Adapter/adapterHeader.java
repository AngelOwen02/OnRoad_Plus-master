package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;

import java.util.Calendar;

public class adapterHeader  extends RecyclerView.Adapter<adapterHeader.ViewHolder> {

    private String vehicleName,vehicleImageURL,vehicleCurrentSpeed,vehicleTimeTravel,vehicleKmTravel,vehicleGeoreference,sendTime;
    private Integer vehicleSwitch;
    private Context context;

    public adapterHeader(String vehicleName, String vehicleImageURL, String vehicleCurrentSpeed, String vehicleTimeTravel, String vehicleKmTravel, String vehicleGeoreference, int vehicleSwitch, String sendTime, Context context) {
    this.vehicleName=vehicleName;
    this.vehicleImageURL=vehicleImageURL;
    this.vehicleCurrentSpeed=vehicleCurrentSpeed;
    this.vehicleTimeTravel=vehicleTimeTravel;
    this.vehicleKmTravel=vehicleKmTravel;
    this.vehicleGeoreference=vehicleGeoreference;
    this.vehicleSwitch=vehicleSwitch;
    this.sendTime=sendTime;
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

        holder.vehicleCurrentSpeed.setText(vehicleCurrentSpeed+ " km/hr");
        holder.vehicleTimeTravel.setText(vehicleTimeTravel);
        holder.vehicleKmTravel.setText(vehicleKmTravel+" km");
        holder.vehicleGeoreference.setText(vehicleGeoreference);

        if (vehicleImageURL != null ) {
            Glide.with(context)
                    .load(vehicleImageURL)
                    .into(holder.vheader);
        }else{
            Glide.with(context)
                    .load(R.drawable.sedan)
                    .into(holder.vheader);
        }

        if(vehicleSwitch!=null) {
            if (vehicleSwitch == 1) {
                Drawable draw = context.getResources().getDrawable(R.drawable.shape_circle_gren);
                holder.semaforo.setImageDrawable(draw);
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarGreen));
            } else if (vehicleSwitch == 2) {
                Drawable draw = context.getResources().getDrawable(R.drawable.shape_circle_orange);
                holder.semaforo.setImageDrawable(draw);
                holder. cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarOrange));
            } else if (vehicleSwitch == 3) {
                Drawable draw = context.getResources().getDrawable(R.drawable.shape_circle_red);
                holder.semaforo.setImageDrawable(draw);
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarRed));
            } else if (vehicleSwitch == 4) {
                Drawable draw = context.getResources().getDrawable(R.drawable.shape_circle_black);
                holder.semaforo.setImageDrawable(draw);
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlack));
            } else if (vehicleSwitch == 0) {
                Drawable draw = context.getResources().getDrawable(R.drawable.shape_circle_gray);
                holder.semaforo.setImageDrawable(draw);
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorGray));
            } else {
                Drawable draw = context.getResources().getDrawable(R.drawable.shape_circle_white);
                holder.semaforo.setImageDrawable(draw);
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }
        holder.txtLastSendTime.setText("1 min"); //TODO aqui va el calculod de sendTima
        sentimeCalulate(holder,sendTime);
    }

    public void setSwitch(Integer nvehicleSwitch, Double speed, String vehicleTimeTravel, Double getKmTravel, String address) {
        this.vehicleSwitch=nvehicleSwitch;
        this.vehicleCurrentSpeed= String.valueOf( speed);
        this.vehicleTimeTravel=vehicleTimeTravel;
        this.vehicleKmTravel=String.valueOf(getKmTravel);
        this.vehicleGeoreference=address;
        notifyDataSetChanged();
    }

     private  void sentimeCalulate(adapterHeader.ViewHolder holder ,String sendtime){
         if (sendTime != null) {
             Calendar calendar = Calendar.getInstance();
             String[] sendTimeformat = sendTime.split(" ");
             String date = sendTimeformat[0];
             String time = sendTimeformat[1];

             String[] dateFormat = date.split("-");
             String year = dateFormat[0];
             String month = dateFormat[1];
             String day = dateFormat[2];

             int deviceDay = calendar.get(Calendar.DAY_OF_MONTH);
             int deviceMonth = calendar.get(Calendar.MONTH);
             int deviceYear = calendar.get(Calendar.YEAR);


             int deviceMonthDF = deviceMonth + 1;
             int dayResult = deviceDay - Integer.valueOf(day);
             int monthResult = deviceMonthDF - Integer.valueOf(month);
             int yearResult = deviceYear - Integer.valueOf(year);


             String[] timeformat = time.split(":");
             String hour = timeformat[0];
             String minute = timeformat[1];
             String second = timeformat[2];

             int deviceHour = calendar.get(Calendar.HOUR_OF_DAY);
             int deviceMinutes = calendar.get(Calendar.MINUTE);
             int deviceSeconds = calendar.get(Calendar.SECOND);

             int wsHour = Integer.valueOf(hour);
             int wsMinute = Integer.valueOf(minute);
             int wsSecond = Integer.valueOf(second);

             int hourResult = deviceHour - wsHour;
             int minuteResult = deviceMinutes - wsMinute;

             if (yearResult == 0) {
                 if (monthResult == 0) {
                     if (dayResult == 0) {
                         if (hourResult == 0) {
                             if (minuteResult <= 9) {
                                 holder.txtLastSendTime.setText("0" + minuteResult + " min");
                             } else {
                                 holder.txtLastSendTime.setText(minuteResult + " min");
                             }
                         } else {
                             if (hourResult <= 1) {
                                 holder.txtLastSendTime.setText(hourResult + " hr");
                             } else {
                                 holder.txtLastSendTime.setText(hourResult + " hrs");
                             }
                         }
                     } else {
                         if (dayResult == 1) {
                             holder.txtLastSendTime.setText(dayResult + " día");
                         } else {
                             holder.txtLastSendTime.setText(dayResult + " días");
                         }
                     }
                 }else {
                     if (monthResult == 1){
                         holder.txtLastSendTime.setText(monthResult + " mes");
                     }else {
                         holder.txtLastSendTime.setText(monthResult + " meses");
                     }
                 }
             }

         } else {
             holder.txtLastSendTime.setText("Sin datos");

         }
     }
    @Override
    public int getItemCount()    {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintDateMapV2;
        private CardView cardViewUnitContainer;
        private TextView vehicleName,vehicleCurrentSpeed,vehicleTimeTravel,vehicleKmTravel,vehicleGeoreference,txtLastSendTime;
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

            txtLastSendTime=itemView.findViewById(R.id.txt_vehicle_header_last_send_time);
            cardViewUnitContainer=itemView.findViewById(R.id.cardview_vehicle_header_time_container);
        }

    }

}

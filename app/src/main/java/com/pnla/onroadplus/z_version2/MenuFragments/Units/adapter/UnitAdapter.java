package com.pnla.onroadplus.z_version2.MenuFragments.Units.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.UnitMapContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder> {

    private List<Unit> unitList;
    private List<Group> groupList;
    private Context context;
    private UnitAdapter.OnClickVehiclesMapListener listener;
    private Handler handler;
    public static  int lastitem;
    public List<String> textaddres= new ArrayList<>();


    public UnitAdapter(List<Unit> unitList, Context context) {
        this.context = context;
        this.unitList = unitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_unit, parent, false);
        return new UnitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        holder.itemView.findFocus().getScrollBarSize();
        String sendTime = unitList.get(position).getSendTime();
           // Log.e("bloquesdeunides",""+holder.getAdapterPosition() +"   LP "+unitList.get(position).getCveVehicle());//+holder.getLayoutPosition()+"  ");
        lastitem=holder.getAdapterPosition();
        Log.e("bloquesdeunides",""+lastitem);
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
            Log.e("debugthesendtime",""+unitList.get(position).getVehicleName()+": "+sendTime+" Y:"+yearResult+"  M:"+monthResult+"  D:"+dayResult+"  H:"+hourResult+" M:"+minuteResult);
            if (yearResult == 0) {
                if (monthResult == 0) {
                    if (dayResult == 0) {
                        if (hourResult == 0) {
                            if (minuteResult <= 14) {
                                holder.txtLastSendTime.setText("0" + minuteResult + " min");
                            } else {
                                holder.txtLastSendTime.setText(minuteResult + " min");
                            }
                        } else {
                            if (hourResult <= 6) {
                                holder.txtLastSendTime.setText(hourResult + " hr");
                            } else {
                                holder.txtLastSendTime.setText(hourResult + " hrs");
                            }
                        }
                    } else {
                        if (dayResult >= -1) {
                            holder.txtLastSendTime.setText(dayResult + " día");
                        } else {
                            holder.txtLastSendTime.setText(dayResult + " días");
                        }
                    }
                }else{
                         if  (monthResult >= 1) {
                               holder.txtLastSendTime.setText(monthResult + " M");
                          } else {
                               holder.txtLastSendTime.setText(monthResult + " M");
                             }
                    }
            }

        } else {
            holder.txtLastSendTime.setText("----");

        }

        /**SetData*/

        if (unitList.get(position).getVehicleName() != null){
            if (unitList.get(position).getVehicleName().equals("")){
                holder.txtUnitName.setText("----");
            } else {
                holder.txtUnitName.setText(unitList.get(position).getVehicleName());
            }
        }else {
            holder.txtUnitName.setText("----");
        }

        holder.txtUnitMaxSpeed.setText(unitList.get(position).getCurrentSpeed() + "km/h");


        if (unitList.get(position).getTimeTravel() != null){
            if (unitList.get(position).getTimeTravel().equals("")){
                holder.txtHour.setText("--");
            } else {
                holder.txtHour.setText(unitList.get(position).getTimeTravel());
                //holder.txtHour.setText(unitList.get(position).getSendTime());
            }
        } else {
            holder.txtHour.setText("--");
        }

        if(textaddres!=null)
        {
           // Log.e("bloquesdeunides","list String :   "+textaddres.get(position));

          //  if(textaddres.get(position).equals(""))
         //   {
           //     holder.txtUnitGeoReference.setText("---- ---- ---- ----");
         //   }
     //       else{
            //    holder.txtUnitGeoReference.setText();
                for(String name:textaddres )
                {
                 if(name.contains(unitList.get(position).getVehicleName()))

                    {
                        String[] parts1 = name.split(":::");
                        String part1 = parts1[0];
                        String part2 = parts1[1];
                        holder.txtUnitGeoReference.setText(part2);      /**puedes vrificar que las direcciones coincidan con los items introduciendo part 1 en el campo part2*/
                    }
                }

          //  }
        }else
        {
            holder.txtUnitGeoReference.setText("---- ---- ---- ----");
        }
    /*    if (unitList.get(position).getGeoreference() != null){
            if (unitList.get(position).getGeoreference().equals("")){
                holder.txtUnitGeoReference.setText("---- ---- ---- ----");
            } else {
                holder.txtUnitGeoReference.setText(unitList.get(position).getGeoreference());
            }
        }else {
            holder.txtUnitGeoReference.setText("---- ---- ---- ----");
        }*/

        DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
        decimalFormat.format(unitList.get(position).getKmTravel());
        String decimalUnitKMTravel = decimalFormat.format(unitList.get(position).getKmTravel()) + "km";
        holder.txtUnitKmTravel.setText(decimalUnitKMTravel);

        /**Status*/
        if (unitList.get(position).getVehicleSwitch() == 1) {
            holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarGreen));
            holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGreen));
        } else if (unitList.get(position).getVehicleSwitch() == 2) {
            holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarOrange));
            holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBorderCarOrange));
        } else if (unitList.get(position).getVehicleSwitch() == 3) {
            holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarRed));
            holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBorderCarRed));
        }else if (unitList.get(position).getVehicleSwitch() == 4) {
            holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlack));
            holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBlack));
        } else if (unitList.get(position).getVehicleSwitch()== 0){
            holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarGray));
            holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGray));
        }

        if (unitList.get(position).getVehicleImage() == null) {
            Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.imgUnitCircle);
        } else if (unitList.get(position).getVehicleImage().equals("string")) {
            Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.imgUnitCircle);
        } else if (unitList.get(position).getVehicleImage().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.imgUnitCircle);
        } else {
            Glide.with(context).load(unitList.get(position).getVehicleImage()) .thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.imgUnitCircle);
        }


        /**MaxSpeedImage*/
        Glide.with(context).load(R.drawable.ic_item_unit_max).into(holder.imgMaxSpeed);

        /**DistanceImage*/
        Glide.with(context).load(R.drawable.ic_item_unit_distance).into(holder.imgDistance);

        /**ShapeImage*/
        Glide.with(context).load(R.drawable.ic_item_unit_shape).into(holder.imgShape);

        /**OnClick*/
//        holder.llMainContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, UnitMapContainer.class);
//                context.startActivity(intent);
//
//            }
//        });

        holder.llMainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle vehicleBundle = new Bundle();

                vehicleBundle.putInt("vehicleCve", unitList.get(position).getCveVehicle());
                vehicleBundle.putInt("vehicleSwitch", unitList.get(position).getVehicleSwitch());
                vehicleBundle.putString("vehicleName", unitList.get(position).getVehicleName());
                vehicleBundle.putString("vehicleImage", unitList.get(position).getVehicleImage());
                vehicleBundle.putString("vehicleSendTime", unitList.get(position).getSendTime());
                vehicleBundle.putString("vehicleDescBrand", unitList.get(position).getDescBrand());
                vehicleBundle.putString("vehicleDescModel", unitList.get(position).getDescModel());
                vehicleBundle.putString("vehicleYear", unitList.get(position).getVehicleYear());
                vehicleBundle.putString("vehicleVin", unitList.get(position).getVehicleVin());
                vehicleBundle.putString("vehiclePlate", unitList.get(position).getVehiclePlate());
                vehicleBundle.putString("vehicleGeoreference", unitList.get(position).getGeoreference());
                vehicleBundle.putString("vehicleTimeTravel", unitList.get(position).getTimeTravel());
                vehicleBundle.putString("vehicleTimeElapsed", unitList.get(position).getTimeElapsed());
                vehicleBundle.putDouble("vehicleLatitude", unitList.get(position).getLatitude());
                vehicleBundle.putDouble("vehicleLongitude", unitList.get(position).getLongitude());
                vehicleBundle.putString("vehicleMileage", unitList.get(position).getVehicleName());
                vehicleBundle.putDouble("vehicleKmTravel", unitList.get(position).getKmTravel());
                vehicleBundle.putDouble("vehicleCurrentSpeed", unitList.get(position).getCurrentSpeed());
                vehicleBundle.putDouble("vehicleMaxSpeed", unitList.get(position).getMaxSpeed());

                Intent intent = new Intent(context, UnitMapContainer.class);
                intent.putExtras(vehicleBundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //return groupList.size();
        return unitList.size();
    }
    public int getadapterviewsize() {
        return lastitem;
    }

    public void getAdress(List<String> from) {
        textaddres=from;
    }

    public int getGroupcCount(){
        return groupList.size();
    }

    public void setOnClickVehiclesMapListener(UnitAdapter.OnClickVehiclesMapListener onClickVehiclesMapListener) {
        this.listener = onClickVehiclesMapListener;
    }

    public interface OnClickVehiclesMapListener {
        void onClickVehiclesMap(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgUnitCircle;
        CardView cardViewUnitContainer;
        TextView txtUnitName;
        TextView txtUnitMaxSpeed;
        TextView txtUnitGeoReference;
        TextView txtUnitKmTravel;
        TextView txtLastSendTime;
        TextView txtHour;
        ImageView imgMaxSpeed;
        ImageView imgShape;
        ImageView imgDistance;
        LinearLayout llMainContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            llMainContainer = itemView.findViewById(R.id.ll_main_unit_item_container);
            imgUnitCircle = itemView.findViewById(R.id.img_unit);
            cardViewUnitContainer = itemView.findViewById(R.id.cardview_unit_container);

            txtUnitName = itemView.findViewById(R.id.txt_unit_name);
            txtUnitMaxSpeed = itemView.findViewById(R.id.txt_unit_max_speed);
            txtUnitGeoReference = itemView.findViewById(R.id.txt_unit_geo_reference);
            txtUnitKmTravel = itemView.findViewById(R.id.txt_unit_km_travel);
            txtLastSendTime = itemView.findViewById(R.id.txt_last_send_time);
            txtHour = itemView.findViewById(R.id.txt_unit_hour);


            imgMaxSpeed = itemView.findViewById(R.id.img_unit_item_max_speed);
            imgShape = itemView.findViewById(R.id.img_unit_item_shape);
            imgDistance = itemView.findViewById(R.id.img_unit_item_distance);

        }
    }

    public void setFilter(List<Unit> unitList) {
        this.unitList = new ArrayList<>();
        this.unitList.addAll(unitList);
        notifyDataSetChanged();
    }
}

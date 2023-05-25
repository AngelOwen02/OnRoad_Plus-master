package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class adapterUnitsV3 extends RecyclerView.Adapter<adapterUnitsV3.ViewHolder> {
    private Context context;
    private List<dataresponseUnitsV3> data;

    public adapterUnitsV3(List<dataresponseUnitsV3> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterUnitsV3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_unit, parent, false);//item_carrito
        return new adapterUnitsV3.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterUnitsV3.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        if (data.get(position).getOutVehicleImage() == null) {
            Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.unitImage);
        } else if (data.get(position).getOutVehicleImage().equals("string")) {
            Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.unitImage);
        } else if (data.get(position).getOutVehicleImage().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.unitImage);
        } else {
            Glide.with(context).load(data.get(position).getOutVehicleImage()) .thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.unitImage);
        }
//        Glide.with(context).load(R.drawable.ic_onraodmapdeailplay).into(holder.imgMaxSpeed);
//        Glide.with(context).load(R.drawable.ic_onraodmapdeailtime).into(holder.imgShape);
//        Glide.with(context).load(R.drawable.ic_onraodmapdeailroute).into(holder.imgDistance);


        holder.name.setText(data.get(position).getOutVehicleName());
        holder.speed.setText(data.get(position).getOutSpeed()+ " Km/hr");
        holder.lastUpdate.setText(data.get(position).getOutSendTime());
        holder.odometer.setText(data.get(position).getOutMileage()+" Km");
        holder.georeference.setText(data.get(position).getLocation());

//        if (data.get(position).getOutVehicleStatus()!=null) {
//          if (data.get(position).getOutVehicleStatus() == 1) {
//                //    holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarGreen));
//                holder.border.setImageDrawable(context.getDrawable(R.drawable.ic_union_green));
//            } else if (data.get(position).getOutVehicleStatus() == 2) {
//                //          holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarOrange));
//                holder.border.setImageDrawable(context.getDrawable(R.drawable.ic_union_yellow));
//            } else if (data.get(position).getOutVehicleStatus() == 3) {
//                //       holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarRed));
//                holder.border.setImageDrawable(context.getDrawable(R.drawable.ic_union_red));
//            }else if (data.get(position).getOutVehicleStatus() == 4) {
//                //      holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlack));
//                holder.border.setImageDrawable(context.getDrawable(R.drawable.ic_union_black));
//            } else if (data.get(position).getOutVehicleStatus()== 0){
//                //     holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarGray));
//                holder.border.setImageDrawable(context.getDrawable(R.drawable.ic_union_grey));
//            }
//        }else{
//            holder.border.setImageDrawable(context.getDrawable(R.drawable.ic_union_alfa));
//        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setNewData(List<dataresponseUnitsV3> data) {
        this.data = new ArrayList<>();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setFilter(List<dataresponseUnitsV3> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,speed,lastUpdate,odometer,georeference,lastMessage;
        ImageView unitImage, border;
        ImageView imgMaxSpeed, imgDistance,imgShape;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txt_unit_name);

            unitImage= itemView.findViewById(R.id.img_unit);
           // border=itemView.findViewById(R.id.img_unit2);

            imgMaxSpeed = itemView.findViewById(R.id.img_unit_item_max_speed);
            imgShape = itemView.findViewById(R.id.img_unit_item_shape);
            imgDistance = itemView.findViewById(R.id.img_unit_item_distance);

            speed= itemView.findViewById(R.id. txt_unit_max_speed);
            lastUpdate= itemView.findViewById(R.id.txt_unit_hour);
            odometer= itemView.findViewById(R.id.txt_unit_km_travel);
            georeference= itemView.findViewById(R.id.txt_unit_geo_reference);
            lastMessage=itemView.findViewById(R.id.txt_last_send_time);



        }
    }
}

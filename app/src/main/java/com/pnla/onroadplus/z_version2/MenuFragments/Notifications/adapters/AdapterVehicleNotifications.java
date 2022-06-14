package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.Vehicles;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.clases.CommonVehicleNotifications;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterVehicleNotifications extends RecyclerView.Adapter<AdapterVehicleNotifications.ViewHolder> {

    private static final String TAG2="Count";
    private List<Vehicles> vehicles;
    private Context context;
    private Typeface latoBold, latoRegular;
    private OnClickVehicleListener listener;
    private int realPosition = 0;
    private int positionOnClick = 0;
    private  String notificationsNum;
    private List<String> notificationNumList;
    public AdapterVehicleNotifications(List<Vehicles> vehicles, Context context) {
        this.vehicles = vehicles;
        this.context = context;
        latoRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Regular.ttf");
        latoBold = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle_notification_v2, parent, false);
        return new ViewHolder(view);
    }

    @Override///tamaños de fuente click
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.txvVehicleName.setText(vehicles.get(position).getVehicleName());
        String currentVehicleName = holder.txvVehicleName.getText().toString();
        if (positionOnClick == position) {

            if (CommonVehicleNotifications.nameVehicle == null) {
                holder.viewSelected.setVisibility(View.VISIBLE);
                holder.txvVehicleName.setTypeface(latoBold);
                holder.txvVehicleName.setTextSize(18);
            } else {
                if (currentVehicleName.equals(CommonVehicleNotifications.nameVehicle)) {
                    holder.viewSelected.setVisibility(View.VISIBLE);
                    holder.txvVehicleName.setTypeface(latoBold);
                    holder.txvVehicleName.setTextSize(18);
                } else {
                    holder.viewSelected.setVisibility(View.GONE);
                    holder.txvVehicleName.setTypeface(latoRegular);
                    holder.txvVehicleName.setTextSize(17);// tamaño deseleccionado
                }
            }
        } else {
            if (realPosition == position) {
                if (holder.txvVehicleName.getText().toString().equals(CommonVehicleNotifications.nameVehicle)) {
                    holder.viewSelected.setVisibility(View.VISIBLE);
                    holder.txvVehicleName.setTypeface(latoBold);
                    holder.txvVehicleName.setTextSize(18);
                } else {
                    if (holder.txvVehicleName.getText().toString().equals(CommonVehicleNotifications.nameVehicle)) {
                        holder.viewSelected.setVisibility(View.VISIBLE);
                        holder.txvVehicleName.setTypeface(latoBold);
                        holder.txvVehicleName.setTextSize(18);
                    } else {
                        holder.viewSelected.setVisibility(View.GONE);
                        holder.txvVehicleName.setTypeface(latoRegular);
                        holder.txvVehicleName.setTextSize(17);
                    }
                }
            } else {
                holder.viewSelected.setVisibility(View.GONE);
                holder.txvVehicleName.setTypeface(latoRegular);
                holder.txvVehicleName.setTextSize(17);
            }
        }

        if (vehicles.get(position).getVehicleImage() == null) {

            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage1);
        }
        else if (vehicles.get(position).getVehicleImage().equals("string")) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage1);
        } else if (vehicles.get(position).getVehicleImage().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage1);
        }
        else {
            Glide.with(context).load(vehicles.get(position).getVehicleImage()).into(holder.imgvVehicleImage1);

        }

        //Sentencia que estaba antes con el bug
        /**if(vehicles.size()==notificationNumList.size())
        {
            holder.txvVehicleNumberitems.setText(notificationNumList.get(position)+" Notificaciones");
        }*/

        //solo deje la sentencia de obtener las notificaciones
        holder.txvVehicleNumberitems.setText(notificationNumList.get(position)+" Notificaciones");
/*
        if(vehicles.get(position).getPositionItem()==positionOnClick) {

            if(notificationsNum==null||notificationsNum=="") {
                holder.txvVehicleNumberitems.setText("");
            }
            else{
                holder.txvVehicleNumberitems.setText(notificationsNum+" "+
                        "Notificaciones");
                //Toast.makeText(context, notificationsNum, Toast.LENGTH_SHORT).show();
            }

        }else{
            holder.txvVehicleNumberitems.setText("Notificaciones");
        }




*/

        holder.constraintVehicleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {


                    AdapterVehicleNotifications.this.realPosition = realPosition;
                    positionOnClick = position;
                    CommonVehicleNotifications.currentItem = vehicles.get(position);
                    CommonVehicleNotifications.realPosition = CommonVehicleNotifications.currentItem.getPositionItem();  //getRealPosition();
                    CommonVehicleNotifications.nameVehicle = CommonVehicleNotifications.currentItem.getVehicleName();//getNameVehicle();
                    listener.onClickVehicle(v, CommonVehicleNotifications.currentItem.getCveVehicle(), CommonVehicleNotifications.currentItem.getVehicleName(), CommonVehicleNotifications.currentItem.getVehicleImage());
                    notifyDataSetChanged();
                    listener.onClickHide(v);
                    Toast.makeText(v.getContext(), "AQUIIII", Toast.LENGTH_LONG).show();
                }

            }
        });

        holder.imageButtonhideunitsnotifications.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View imgv) {
                if (listener != null) {

                    AdapterVehicleNotifications.this.realPosition = realPosition;
                    positionOnClick = position;
                    CommonVehicleNotifications.currentItem = vehicles.get(position);
                    CommonVehicleNotifications.realPosition = CommonVehicleNotifications.currentItem.getPositionItem();  //getRealPosition();
                    CommonVehicleNotifications.nameVehicle = CommonVehicleNotifications.currentItem.getVehicleName();//getNameVehicle();
                    listener.onClickVehicle(imgv, CommonVehicleNotifications.currentItem.getCveVehicle(), CommonVehicleNotifications.currentItem.getVehicleName(), CommonVehicleNotifications.currentItem.getVehicleImage());
                    notifyDataSetChanged();
                    listener.onClickHide(imgv);

                    //listener.onClickHide(imgv);
                }
            }
        });



    }

    public String getNotification() {
        return notificationsNum;
    }

    public void holderText( String notificationsnumber){

        notificationsNum=notificationsnumber;

    }
    public void holderTextList(List<String> notificationsCount)
    {
        notificationNumList=notificationsCount;
        Log.i(TAG2,""+notificationNumList);
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public void setFilter(List<Vehicles> listaNotas) {
        this.vehicles = new ArrayList<>();
        this.vehicles.addAll(listaNotas);
        notifyDataSetChanged();
    }

    public void setOnClickVehicleListener(OnClickVehicleListener listener) {
        this.listener = listener;

    }

    public interface OnClickVehicleListener {
        void onClickVehicle(View v, int vehicleCve, String vehicleName, String vehicleImage);
        void onClickHide(View imgv);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintVehicleNotification;
        private CircleImageView imgvVehicleImage1;
        private TextView txvVehicleName;

        private TextView txvVehicleNumberitems;
        private View viewSelected;

        private View imageButtonhideunitsnotifications;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvVehicleName = itemView.findViewById(R.id.txvVehicleName);
            viewSelected = itemView.findViewById(R.id.viewSelected);
            constraintVehicleNotification = itemView.findViewById(R.id.constraintVehicleNotification);


            //imagen y boton agregados para version 2
            imageButtonhideunitsnotifications=itemView.findViewById(R.id.imageButtonhideunitsnotifications1);
            imgvVehicleImage1 = itemView.findViewById(R.id.imgvVehicleImage1);

            txvVehicleNumberitems= itemView.findViewById(R.id.txvVehicleNumberitems);
        }
    }

}

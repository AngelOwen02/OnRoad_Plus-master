package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsOnroad;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterNotifications extends RecyclerView.Adapter<AdapterNotifications.ViewHolder> {
    private Context context;
    private List<NotificationsOnroad> notifications;
    private Typeface latoBold, latoRegular;
    private OnClickNotificationListener listener;

    public AdapterNotifications(Context context, List<NotificationsOnroad> notifications) {
        this.context = context;
        this.notifications = notifications;
        latoRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Regular.ttf");
        latoBold = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification_v2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final NotificationsOnroad notification = notifications.get(position);
        holder.txvVehicleName.setTypeface(latoBold);
        holder.txvNotificationName.setTypeface(latoRegular);
        holder.txvDateNotification.setTypeface(latoRegular);

        holder.txvVehicleName.setText(notifications.get(position).getVehicleName());
        holder.txvNotificationName.setText(notifications.get(position).getNotification());
        holder.txvDateNotification.setText(notifications.get(position).getDateNotification());
        //Glide.with(context).load(R.drawable.pikachu).into(holder.imgvVehicleImage);

        //Log.e("doitonce", "" + integerList);
        if (notifications.get(position).getImageVehicle() == null) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage);
        }
        else if (notifications.get(position).getImageVehicle().equals("string")) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage);
        } else if (notifications.get(position).getImageVehicle().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage);
        }
        else {
            Glide.with(context).load(notifications.get(position).getImageVehicle()).into(holder.imgvVehicleImage);
        }

        //Imagen del carro
        /**if (notifications.get(position).getImageVehicle() != null) {
            Glide.with(context).load(notifications.get(position).getImageVehicle()).into(holder.imgvVehicleImage);
        } else {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgvVehicleImage);
        }*/

        holder.constraintNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickNotification(notifications.get(position));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
    public void setFilter(List<NotificationsOnroad> listaNotas) {//metodo de filtro de notificacion
        this.notifications = new ArrayList<>();
        this.notifications.addAll(listaNotas);
        notifyDataSetChanged();
    }
    public void clearNotifications() {
        int size = notifications.size();
        notifications.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void setOnClickNotificationListener(OnClickNotificationListener listener) {
        this.listener = listener;
    }

    public interface OnClickNotificationListener {
        void onClickNotification(NotificationsOnroad notification);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgvVehicleImage;
        private TextView txvVehicleName;
        private TextView txvNotificationName;
        private TextView txvDateNotification;
        private ConstraintLayout constraintNotification;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgvVehicleImage = itemView.findViewById(R.id.imgvVehicleImage);
            txvVehicleName = itemView.findViewById(R.id.txvVehicleName);
            txvNotificationName = itemView.findViewById(R.id.txvNotificationName);
            txvDateNotification = itemView.findViewById(R.id.txvDateNotification);
            constraintNotification = itemView.findViewById(R.id.constraintNotification);
        }
    }
}

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
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.ArrayList;
import java.util.List;

public class AdapterGroupsV2 extends RecyclerView.Adapter<AdapterGroupsV2.ViewHolder> {

    private List<GroupV2> groups;
    private Typeface latoRegularTypeface, latoBoldTypeface;
    private Context context;

    private GroupSelected listener;

    public AdapterGroupsV2(List<GroupV2> groups, Context context) {
        this.groups = groups;
        this.context = context;
        this.latoBoldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");
        this.latoRegularTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Regular.ttf");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group_v2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txvNameGroup.setTypeface(latoBoldTypeface);
        holder.txvNumberCarsGroup.setTypeface(latoRegularTypeface);
        holder.txvNameGroup.setText(groups.get(position).getVehicle_group());

        //validamos el numero de coches
        List<VehicleV2> vehicleList = groups.get(position).getVehicles();
        if (vehicleList != null) {
            int numberVehicles = vehicleList.size();
            if (numberVehicles == 1) {
                holder.txvNumberCarsGroup.setText("" + numberVehicles + " " + context.getString(R.string.textVehicle));
            } else {
                holder.txvNumberCarsGroup.setText("" + numberVehicles + " " + context.getString(R.string.textVehicles));
            }
        } else {
            holder.txvNumberCarsGroup.setText("" + context.getString(R.string.textWithoutVehicles));
        }

        //Imagen del carro
        Glide.with(context).load(R.drawable.car).into(holder.imgvGroup);

        //Imagen de item seleccionado
        if (groups.get(position).isSelected()) {
            holder.imgvSelectedGroup.setVisibility(View.GONE);
            Glide.with(context).load(R.drawable.eye_selected).into(holder.imgvOverEyeGroup);

            //holder.imgvSelectedGroup.setVisibility(View.VISIBLE);
            //Glide.with(context).load(R.drawable.icon_eye).into(holder.imgvOverEyeGroup);


        } else {
            holder.imgvSelectedGroup.setVisibility(View.VISIBLE);
            Glide.with(context).load(R.drawable.icon_eye).into(holder.imgvOverEyeGroup);

            //holder.imgvSelectedGroup.setVisibility(View.GONE);
            //Glide.with(context).load(R.drawable.eye_selected).into(holder.imgvOverEyeGroup);


        }

        //onClickListenerDelItem
        holder.constraintGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.groupSelected(v, groups.get(position).getPositionItem(), position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void changedData(int position, boolean newState) {
        groups.get(position).setSelected(newState);
        notifyDataSetChanged();
    }

    public void setFilter(List<GroupV2> groups) {
        this.groups = new ArrayList<>();
        this.groups.addAll(groups);
        notifyDataSetChanged();
    }

    public void setGroupSelectedListener(GroupSelected listener) {
        this.listener = listener;
    }

    public interface GroupSelected {
        void groupSelected(View view, int positionListComplete, int positionFilterList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txvNameGroup, txvNumberCarsGroup;
        private ImageView imgvGroup;
        private ImageView imgvSelectedGroup;
        private ImageView imgvOverEyeGroup;
        private ConstraintLayout constraintGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            txvNameGroup = itemView.findViewById(R.id.txvNameGroup);
            txvNumberCarsGroup = itemView.findViewById(R.id.txvNumberCarsGroup);
            imgvGroup = itemView.findViewById(R.id.imgvGroup);
            imgvSelectedGroup = itemView.findViewById(R.id.imgvSelectedGroup);
            imgvOverEyeGroup = itemView.findViewById(R.id.imgvOverEyeGroup);
            constraintGroup = itemView.findViewById(R.id.constraintGroup);
        }
    }

}
package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;

import java.util.List;

public class AdapterDatesV3 extends RecyclerView.Adapter<AdapterDatesV3.ViewHolder> {

private List<DateV2> dates;
private int positionOnClick = 0;
private OnDateClickListener listener;

public AdapterDatesV3(List<DateV2> dates, Context context) {
        this.dates = dates;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_map_v2_, parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txvDateDetailCar.setText(dates.get(position).getDate());

        if (positionOnClick == position) {
        holder.txvDateDetailCar.setTextSize(14);
        holder.txvDateDetailCar.setTextColor(Color.BLACK);

        } else {
        holder.txvDateDetailCar.setTextSize(12);
        holder.txvDateDetailCar.setTextColor(Color.GRAY);

        }

        holder.constraintDateMapV2.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if (listener != null) {
        positionOnClick = position;
        listener.onDate(dates.get(position).getDate(), position);
        notifyDataSetChanged();
        }
        }
        });

        }

@Override
public int getItemCount()    {
        return dates.size();
        }

public void setPosition1Clicked() {//TODO obtine la primera fecha
        positionOnClick = 0;
        listener.onDate(dates.get(0).getDate(), 0);
        notifyDataSetChanged();
        }

public void setOnClickDateListener(OnDateClickListener listener) {
        this.listener = listener;
        }

public interface OnDateClickListener {
    void onDate(String date, int position);
}

public static class ViewHolder extends RecyclerView.ViewHolder {
    private ConstraintLayout constraintDateMapV2;
    private TextView txvDateDetailCar;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txvDateDetailCar = itemView.findViewById(R.id.txvDateDetailCar);
        constraintDateMapV2 = itemView.findViewById(R.id.constraintDateMapV2);
    }

}

}

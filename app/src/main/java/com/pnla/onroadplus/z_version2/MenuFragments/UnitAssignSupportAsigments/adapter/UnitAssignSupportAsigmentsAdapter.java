package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.adapter.UnitAssignSupportAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.SingleUnitSupportData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitData;

import java.util.ArrayList;
import java.util.List;

public class UnitAssignSupportAsigmentsAdapter extends RecyclerView.Adapter<UnitAssignSupportAsigmentsAdapter.ViewHolder> implements View.OnClickListener{

    private List<SingleSupportUnitData> data;
    private Context context;
    public List<String> textaddres= new ArrayList<>();

    public UnitAssignSupportAsigmentsAdapter(List<SingleSupportUnitData> data, Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.unit_support_cl, parent, false);
        return new UnitAssignSupportAsigmentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){
        //Set Data

        //Diferencia del vehiculo (En Porcentaje)
        String PercentHelp = new String(String.valueOf(data.get(position).getPercentToHelp()));
        PercentHelp.equals(data.get(position).getPercentToHelp());
        holder.unitPercent.setText(PercentHelp + "%");

        //Ubicacion
        holder.unitGeo.setText(data.get(position).getGeoReference());
    }

    @Override
    public int getItemCount(){
        return  data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //TextViews, etc
        TextView unitPercent;
        TextView unitGeo;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //Referencias a la vista xml
            unitPercent = itemView.findViewById(R.id.txt_percent_single_rv);
            unitGeo = itemView.findViewById(R.id.txt_unit_geo_reference_single_rv);
        }
    }

    @Override
    public void onClick(View view){
        Toast.makeText(view.getContext(), "Si funciona", Toast.LENGTH_SHORT).show();
    }

}
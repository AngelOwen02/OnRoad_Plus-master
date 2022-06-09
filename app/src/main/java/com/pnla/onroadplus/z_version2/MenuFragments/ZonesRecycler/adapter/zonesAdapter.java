package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.view.zonesFragment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.view.zoneSupervisorAsignment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.view.driversAsingment;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.View.zoneAsignViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedData1;
import com.pnla.onroadplus.z_version2.retrofit.PersistenceUtilities;

import java.util.ArrayList;
import java.util.List;

public class zonesAdapter  extends RecyclerView.Adapter<zonesAdapter.ViewHolder>{

    public static String cveLayerZoneName;
    private Context context;
    private List<String> geoZonesFake=new ArrayList<>();
    private List<visitedData1> visitedPoints;
    private List<visitedData1> mirrorList;
    public static String Provide = "";
    public static String zoneName="";
    public static String supervisorName;
    public  static Integer supervisorCve;
    public static  int zonePosition;
    public static  int cveLayerZone;


    public int positionVolante;


    public zonesAdapter(List<visitedData1> visitedPoints,Context context)
    {
        this.visitedPoints=visitedPoints;
        this.context=context;
        mirrorList=visitedPoints;
   //     Log.e("asignaciondeConductores", ""+ zonesRecyclerView.volante);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.geocercas_toggles, parent, false);
        return new zonesAdapter.ViewHolder(view);
    }
/**
 *                 Log.e("visited","NOMBRE: "+dataVisited[i].getDescLayer()+"  PORCENTAGE: "+dataVisited[i].getPercentComplete()
 *                                 +"%  ("+dataVisited[i].getPointsVisited()+"/"+dataVisited[i].getPointsNotVisited()+")"+'\n') ;// '\n'*/
    @Override
    public void onBindViewHolder(@NonNull final zonesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        if (PersistenceUtilities.getSharedPreferences(context).contains(Provide+visitedPoints.get(position).getDescLayer())) {
            holder.unitSwicth.setChecked(PersistenceUtilities.getSharedPreferences(context).getBoolean(Provide + visitedPoints.get(position).getDescLayer(), false));
            if(holder.unitSwicth.isChecked())
            {
                if(zonesFragment.pointInZone.contains(visitedPoints.get(position).getCveLayer()))
                {

                }
                else
                {
                    zonesFragment.pointInZone.add(visitedPoints.get(position).getCveLayer());
                }
            }

        }else {
            PersistenceUtilities.CreateorEdit(context, Provide + visitedPoints.get(position).getDescLayer(), false);
            if(!holder.unitSwicth.isChecked())
            {
                if(zonesFragment.pointInZone.contains(visitedPoints.get(position).getCveLayer()))
                {
                    zonesFragment.pointInZone.remove(visitedPoints.get(position).getCveLayer());
                }
            }
        }

                holder.geoZonesName.setText(visitedPoints.get(position).getDescLayer());//+" #" +visitedPoints.get(position).getCveLayer());
                holder.percentage.setText(visitedPoints.get(position).getPercentComplete());
                holder.pointsvisited.setText(visitedPoints.get(position).getPointsVisited());
                holder.pointsnotvisited .setText(visitedPoints.get(position).getPointsNotVisited());
//                if(visitedPoints!=null)
//                {
//                    Log.e("myvolante",""+visitedPoints.get(position).getCveLayer());
//                    if(zonesRecyclerInteractorImpl.adapterCheck.contains(visitedPoints.get(position).getCveLayer()))
//                    {
//                        Log.e("myvolante","aqui si esta");
//                        if(zonesRecyclerInteractorImpl.adapterCheck.contains("0]"))
//                        {
//                            //Glide.with(context).load(R.drawable.ic_asignarvehiculodisable).into( holder.vehicle);
//                        }else
//                        {
//                          //  Glide.with(context).load(R.drawable.ic_asignarvehiculo).into( holder.vehicle);
//                        }
//                    }
//                }
//

//        if(zonesRecyclerView.volante.get(position).contains(visitedPoints.get(position).getCveLayer()))
//        {
//
//            positionVolante=zonesRecyclerView.volante.indexOf(visitedPoints.get(position).getCveLayer());
//            Log.e("asignaciondeConductores", ""+positionVolante);
//            if(zonesRecyclerView.volante.get(positionVolante).contains(":0"))
//            {
//         //       Glide.with(context).load(R.drawable.ic_asignarvehiculodisable).into( holder.vehicle);
//            }else
//            {
//         //       Glide.with(context).load(R.drawable.ic_asignarvehiculo).into( holder.vehicle);
//            }
//
//        }

        if(visitedPoints.get(position).getColorVolante().get(position)!=null)
        {
           // Log.e("asignaciondeConductores", ""+visitedPoints.get(position).getColorVolante().get(position));
            if(visitedPoints.get(position).getColorVolante().get(position).contains(":0"))
            {
                Glide.with(context).load(R.drawable.ic_asignarvehiculodisable).into( holder.vehicle);
            }else
            {
                Glide.with(context).load(R.drawable.ic_asignarvehiculo).into( holder.vehicle);
            }
        }

        if(visitedPoints.get(position).getCve_employee()!=0)
        {
            Glide.with(context).load(R.drawable.ic_profileon).into(holder.supervisor);
        }else if(visitedPoints.get(position).getCve_employee()==0)
        {
            Glide.with(context).load(R.drawable.ic_profileoff).into(holder.supervisor);
        }
        else
        {
            Glide.with(context).load(R.drawable.ic_profileoff).into(holder.supervisor);
        }

      // Glide.with(context).load(R.drawable.ic_asignarvehiculo).into( holder.vehicle);

                holder.unitSwicth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked)
                        {
                            PersistenceUtilities.CreateorEdit(context,Provide+visitedPoints.get(position).getDescLayer(),true);
                            holder.unitSwicth.setChecked(true);
                            if(zonesFragment.pointInZone.contains(visitedPoints.get(position).getCveLayer()))
                            {

                            }
                            else
                            {
                                zonesFragment.pointInZone.add(visitedPoints.get(position).getCveLayer());
                            }
                        }else{
                            PersistenceUtilities.CreateorEdit(context,Provide+visitedPoints.get(position).getDescLayer(),false);
                            holder.unitSwicth.setChecked(false);

                            if(zonesFragment.pointInZone.contains(visitedPoints.get(position).getCveLayer())) {

                                zonesFragment.pointInZone.remove(visitedPoints.get(position).getCveLayer());
                            }else{ }
                        }
                    }
                });

                holder.vehicle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mirrorList.contains( visitedPoints.get(position)))
                        {

                            zonePosition=mirrorList.indexOf(visitedPoints.get(position));
                        }

                        Log.e("tripulantes","position :  "+zonePosition);
                        zoneName=visitedPoints.get(position).getDescLayer();//nombre de la zona
                        cveLayerZone=Integer.valueOf(visitedPoints.get(position).getCveLayer());
                        cveLayerZoneName=visitedPoints.get(position).getDescLayer();
                        Log.e("tripulantes","cveLayerZone :  "+cveLayerZone);/**esto te da la zona a consultar*/

                        /**este fkujo es el nuevo  vehiculo , conductores y tripulantes*/
                        Intent intent=new Intent(context, zoneAsignViewImpl.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//no se incluye este comando debido a que queremos el onback presed con el activity anterior | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        /**este fkujo es el anterior solo de vehiculo y conductores*/
//                        Intent intent=new Intent(context, driversAsingment.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
                    }
                });
        holder.supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mirrorList.contains( visitedPoints.get(position)))
                {

                    zonePosition=mirrorList.indexOf(visitedPoints.get(position));
                }

                Log.e("tripulantes","position :  "+zonePosition);
                zoneName=visitedPoints.get(position).getDescLayer();//nombre de la zona
                cveLayerZone=Integer.valueOf(visitedPoints.get(position).getCveLayer());
                cveLayerZoneName=visitedPoints.get(position).getDescLayer();
                supervisorName=visitedPoints.get(position).getSupervisor_name();
                supervisorCve=visitedPoints.get(position).getCve_employee();
                Log.e("tripulantes","cveLayerZone :  "+cveLayerZone);/**esto te da la zona a consultar*/

                /**este fkujo es el nuevo  vehiculo , conductores y tripulantes*/
                Intent intent=new Intent(context, zoneSupervisorAsignment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//no se incluye este comando debido a que queremos el onback presed con el activity anterior | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                /**este fkujo es el anterior solo de vehiculo y conductores*/
//                        Intent intent=new Intent(context, driversAsingment.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return visitedPoints.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView geoZonesName,percentage,pointsvisited,pointsnotvisited;
        Switch unitSwicth;
        ImageView vehicle ,supervisor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            geoZonesName= itemView.findViewById(R.id.gecercaText);
            percentage= itemView.findViewById(R.id.percentage);
            pointsvisited= itemView.findViewById(R.id.pointsvisited);
            pointsnotvisited= itemView.findViewById(R.id.pointsnotvisited);

            vehicle=itemView.findViewById(R.id.vehicleAsignation);
            supervisor=itemView.findViewById(R.id.userZoneAsigment);
            unitSwicth = itemView.findViewById(R.id.geocercaSwitch);
        }
    }

    public void setFilter(List<visitedData1> visitedDataList){
        this.visitedPoints = new ArrayList<>();
        this.visitedPoints.addAll(visitedDataList);
        notifyDataSetChanged();
    }
}

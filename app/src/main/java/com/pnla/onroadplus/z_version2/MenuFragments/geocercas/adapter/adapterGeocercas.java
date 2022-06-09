package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.Data;
import com.pnla.onroadplus.z_version2.TrackingMapFragment;
import com.pnla.onroadplus.z_version2.retrofit.PersistenceUtilities;

import java.util.ArrayList;
import java.util.List;

public class adapterGeocercas extends RecyclerView.Adapter<adapterGeocercas.ViewHolder> {

    private Context context;
    private List<Data> data;
    private List<String> geocercasFake=new ArrayList<>();
    public   List<Boolean> togglesList=new ArrayList<>();/**esta lista la inicializamos en la view*/
    public static List<Boolean> toggleBackup=new ArrayList<>();/**esta lista */
    public static String Provide = "";

    public adapterGeocercas(List<Data> data,Context context)
    {

        this.data=data;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.geocercas_toggles, parent, false);
        return new adapterGeocercas.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        // geocercasFake=geoCercasMainContainer.geocercas;
        final Data geocercas = data.get(position);

        //holder.unitSwicth.setChecked(false);

        holder.geCercaName.setText(data.get(position).getGeofenceName());

        if (PersistenceUtilities.getSharedPreferences(context).contains(Provide+data.get(position).getGeofenceName())){
            holder.unitSwicth.setChecked(PersistenceUtilities.getSharedPreferences(context).getBoolean(Provide+data.get(position).getGeofenceName(),false));

            if(holder.unitSwicth.isChecked()) {
                if(!TrackingMapFragment.cveGeofence.contains(data.get(position).getCveGeofence())) {
                    TrackingMapFragment.cveGeofence.add(data.get(position).getCveGeofence());
                    TrackingMapFragment.ratioR.add(data.get(position).getGeofenceRadius());
                    TrackingMapFragment.typeS.add(data.get(position).getGeofenceType());
                    TrackingMapFragment.pointsLAT.add(data.get(position).getLats());//  pointsLAT.add(Collections.singletonList(data.get(ubicacion).getLats() ));
                    TrackingMapFragment.pointsLON.add(data.get(position).getLngs());//   pointsLON.add(Collections.singletonList(data.get(ubicacion).getLngs() ));
                    TrackingMapFragment.colorARGB1.add(data.get(position).getGeofenceColor());
                }
            }

        } else {
            PersistenceUtilities.CreateorEdit(context,Provide+data.get(position).getGeofenceName(),false);
            if(!holder.unitSwicth.isChecked()) {
                if(TrackingMapFragment.cveGeofence.contains(data.get(position).getCveGeofence())) {
                    TrackingMapFragment.cveGeofence.remove(data.get(position).getCveGeofence());
                    TrackingMapFragment.ratioR.remove(data.get(position).getGeofenceRadius());
                    TrackingMapFragment.typeS.remove(data.get(position).getGeofenceType());
                    TrackingMapFragment.pointsLAT.remove(data.get(position).getLats());//  pointsLAT.add(Collections.singletonList(data.get(ubicacion).getLats() ));
                    TrackingMapFragment.pointsLON.remove(data.get(position).getLngs());//   pointsLON.add(Collections.singletonList(data.get(ubicacion).getLngs() ));
                    TrackingMapFragment.colorARGB1.remove(data.get(position).getGeofenceColor());
                }
            }
        }

        holder.unitSwicth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    PersistenceUtilities.CreateorEdit(context,Provide+data.get(position).getGeofenceName(),true);
                    holder.unitSwicth.setChecked(true);
                    togglesList.set(position,true);
                    Log.e("toogleStatus","Checket "+togglesList);
                    //holder.unitSwicth.setChecked(togglesList.get(position).booleanValue());
                    if(togglesList.get(position)==true)
                    {
                        if(TrackingMapFragment.cveGeofence.contains(data.get(position).getCveGeofence()))
                        {

                        }
                        else
                        {

                            TrackingMapFragment.cveGeofence.add(data.get(position).getCveGeofence());
                            TrackingMapFragment.ratioR.add(data.get(position).getGeofenceRadius());
                            TrackingMapFragment.typeS.add(data.get(position).getGeofenceType());
                            TrackingMapFragment.colorARGB1.add(data.get(position).getGeofenceColor());
                        }
                        if(TrackingMapFragment.pointsLAT.contains(data.get(position).getLats()))
                        {

                        }
                        else
                        {
                            TrackingMapFragment.pointsLAT.add(data.get(position).getLats());//  pointsLAT.add(Collections.singletonList(data.get(ubicacion).getLats() ));
                            TrackingMapFragment.pointsLON.add(data.get(position).getLngs());//   pointsLON.add(Collections.singletonList(data.get(ubicacion).getLngs() ));
                        }

                       /* if(TrackingMapFragment.colorARGB1.contains(data.get(position).getGeofenceColor()))
                        {
                           TrackingMapFragment.colorARGB1.add(data.get(position).getGeofenceColor());
                        }else {
                            TrackingMapFragment.colorARGB1.add(data.get(position).getGeofenceColor());
                        }*/




                    }


                   // Log.e("geoPoints","Checket "+pointsLAT+'\n'+pointsLON );
                  /*  if(togglesList.size()==toggleBackup.size());

                    {
                        toggleBackup=togglesList;
                    }*/
                }
                else
                {
                    PersistenceUtilities.CreateorEdit(context,Provide+data.get(position).getGeofenceName(),false);
                    holder.unitSwicth.setChecked(false);
                    togglesList.set(position,false);

                    Log.e("toogleStatus","FALSE "+togglesList);
                    //holder.unitSwicth.setChecked(togglesList.get(position).booleanValue());
                    if(togglesList.get(position)==false)
                    {
                        if(TrackingMapFragment.cveGeofence.contains(data.get(position).getCveGeofence()))
                        {
                            TrackingMapFragment.cveGeofence.remove(data.get(position).getCveGeofence());
                            TrackingMapFragment.ratioR.remove(data.get(position).getGeofenceRadius());
                            TrackingMapFragment.typeS.remove(data.get(position).getGeofenceType());
                            TrackingMapFragment.colorARGB1.remove(data.get(position).getGeofenceColor());
                        }
                        else
                        {

                        }
                        if(TrackingMapFragment.pointsLAT.contains(data.get(position).getLats()))
                        {
                            TrackingMapFragment.pointsLAT.remove( data.get(position).getLats());//  pointsLAT.add(Collections.singletonList(data.get(ubicacion).getLats() ));
                            TrackingMapFragment.pointsLON.remove(data.get(position).getLngs());//   pointsLON.add(Collections.singletonList(data.get(ubicacion).getLngs() ));
                        }
                        else
                        {

                        }
                    /*    if(TrackingMapFragment.colorARGB1.contains(data.get(position).getGeofenceColor()))
                        {
                            TrackingMapFragment.colorARGB1.remove(data.get(position).getGeofenceColor());
                        }else {

                        }*/

                    }
                    if(TrackingMapFragment.pointsLAT.contains("I"+data.get(position).getLats()+"f"))
                    {
                        TrackingMapFragment.pointsLAT.remove( "I"+data.get(position).getLats()+"f");//  pointsLAT.add(Collections.singletonList(data.get(ubicacion).getLats() ));
                        TrackingMapFragment.pointsLON.remove("I"+data.get(position).getLngs()+"f");//   pointsLON.add(Collections.singletonList(data.get(ubicacion).getLngs() ));
                    }
                    else
                    {

                    }


                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();/**data size*/
    }

    public void booleanList(List<Boolean> mytoogles) {
        togglesList=mytoogles;
        // toggleBackup=mytoogles;
        //notifyDataSetChanged();



    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView geCercaName;
        Switch unitSwicth;
        ConstraintLayout fieldPercentage;
        ImageView volante;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            geCercaName= itemView.findViewById(R.id.gecercaText);
            unitSwicth = itemView.findViewById(R.id.geocercaSwitch);
            fieldPercentage=itemView.findViewById(R.id.percentagefields);
            volante=itemView.findViewById(R.id.vehicleAsignation);
            fieldPercentage.setVisibility(View.GONE);
            volante.setVisibility(View.GONE);
        }
    }

    public void setFilter(List<Data> unitList) {
        this.data = new ArrayList<>();
        this.data.addAll(unitList);
        notifyDataSetChanged();
    }

}

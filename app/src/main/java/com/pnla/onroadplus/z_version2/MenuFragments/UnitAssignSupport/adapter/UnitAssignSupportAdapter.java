package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.Guardar;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.SingleUnitSupportData;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.view.UnitAssignSupportAsigmentsViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.UnitMapContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.adapter.UnitAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.SplashScreenActivity;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UnitAssignSupportAdapter extends RecyclerView.Adapter<UnitAssignSupportAdapter.ViewHolder> {

    private List<SupportUnitData> data;
    private Context context;
    public List<String> textaddres= new ArrayList<>();
    private List<String> data2;
    private UnitAssignSupportViewImpl mview;

    public UnitAssignSupportAdapter(UnitAssignSupportViewImpl mview, List<SupportUnitData> data, Context context) {
        this.data = data;
        this.context = context;
        this.mview=mview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_support, parent, false);
        return new UnitAssignSupportAdapter.ViewHolder(view);
    }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            /**SetData*/
            mview.savePositionRV(position);
            //Esto es del nombre del vehiculo
            if (data.get(position).getVehicle_Name() != null){
                if (data.get(position).getVehicle_Name().equals("")){
                    holder.unitTitle.setText("----");
                } else {
                    holder.unitTitle.setText(data.get(position).getVehicle_Name());
                }
            }else {
                holder.unitTitle.setText("----");
            }

            //Ruta del vehiculo
            holder.unitRute.setText(data.get(position).getDesc_Layer());

            //Porcentaje completado del vehiculo
            String PercentComplete = new String(String.valueOf(data.get(position).getPercent_Complete()));
            PercentComplete.equals(data.get(position).getPercent_Complete());
            holder.unitRealPercent.setText(PercentComplete + "%");

            //Porcentaje Objetivo del vehiculo
            String ControlPoint = new String(String.valueOf(data.get(position).getControl_Point()));
            ControlPoint.equals(data.get(position).getControl_Point());
            holder.unitObjPercent.setText(ControlPoint + "%");

            //Diferencia del vehiculo (En Porcentaje)
            String PercentHelp = new String(String.valueOf(data.get(position).getPercentToHelp()));
            PercentHelp.equals(data.get(position).getPercentToHelp());
            holder.unitDifference.setText(PercentHelp + "%");

            //Estatus actual del vehiculo
            String Status = new String(String.valueOf(data.get(position).getStatus()));
            Status.equals(data.get(position).getStatus());
            //holder.unitStatus.setText(Status);

            //Para que aparezca el texto dependiendo el resultado del Json
            if(data.get(position).getStatus() == 1){
                holder.unitStatus.setText("A tiempo");
                holder.unitStatus.setTextColor(ContextCompat.getColor(context,R.color.colorOrangeYellow));
                holder.unitDifference.setTextColor(ContextCompat.getColor(context,R.color.colorOrangeYellow));
                //holder.unitImage.setImageResource(R.drawable.ic_camion_azul);
                holder.dotColor.setImageResource(R.drawable.yellow_dot);

            } else if (data.get(position).getStatus() == 2){
                holder.unitStatus.setText("Avanzado");
                holder.unitStatus.setTextColor(ContextCompat.getColor(context,R.color.green));
                holder.unitDifference.setTextColor(ContextCompat.getColor(context,R.color.green));
                //holder.unitImage.setImageResource(R.drawable.ic_camion_azul);
                holder.dotColor.setImageResource(R.drawable.green_dot);
            } else if (data.get(position).getStatus() == 3){
                holder.unitStatus.setText("Sin Estatus");
                holder.unitStatus.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
                holder.unitDifference.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
                //holder.unitImage.setImageResource(R.drawable.ic_camion_gris);
                holder.dotColor.setImageResource(R.drawable.black_dot);
            } else if (data.get(position).getStatus() == 0){
                holder.unitStatus.setText("Atrasado");
                holder.unitStatus.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                holder.unitDifference.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                //holder.unitImage.setImageResource(R.drawable.ic_camion_azul);
                holder.dotColor.setImageResource(R.drawable.red_dot);
            }

            //holder.unitGeoExample.setText(data.get(position).getGeoReference());

            //Para que aparezca el icono del camion dependiendo el Json
            //Azul: Ya tiene al menos un apoyo asignado
            //help_state= 1
            if(data.get(position).getHelp_State() == 1){
                holder.unitImage.setImageResource(R.drawable.ic_camion_azul);
            }
            //Verde: No necesita apoyo la ruta, manda mensaje de “La ruta no requiere apoyo”
            //help_state= 2
            else if (data.get(position).getHelp_State() == 2){
                holder.unitImage.setImageResource(R.drawable.ic_camion_verde_check);
            }
            //Gris: Necesita apoyo la ruta y no hay apoyo asignado
            //help_state= 0
            else if (data.get(position).getHelp_State() == 0){
                holder.unitImage.setImageResource(R.drawable.ic_camion_gris_2);
            }

            //Al darle clic a la unidad en el RecyclerView
            holder.ll_main_unit_item_assign_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("helpstate" , "" + data.get(position).getHelp_State());
                }
            });

            //Al dar clic en la imagen del camion
            holder.unitImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.get(position).getStatus() == 0){
                        //Esto es para el color rojo
                        Bundle vehicleBundle = new Bundle();
                        vehicleBundle.putString("url_image", data.get(position).getUrl_Image());
                        vehicleBundle.putInt("cve_layer", data.get(position).getCveLayer());
                        vehicleBundle.putString("cve_vehicle", data.get(position).getCve_Vehicle());
                        vehicleBundle.putString("vehicle_name", data.get(position).getVehicle_Name());
                        vehicleBundle.putString("desc_layer", data.get(position).getDesc_Layer());
                        vehicleBundle.putInt("percent_complete", data.get(position).getPercent_Complete());
                        vehicleBundle.putInt("control_point", data.get(position).getControl_Point());
                        vehicleBundle.putInt("percent_to_help", data.get(position).getPercentToHelp());
                        vehicleBundle.putInt("status", data.get(position).getStatus());
                        vehicleBundle.putInt("help_state", data.get(position).getHelp_State());
                        vehicleBundle.putString("georeference", data.get(position).getGeoReference());

                        //Aqui mandamos los datos del Bundle que creamos
                        Intent intent = new Intent(context, UnitAssignSupportAsigmentsViewImpl.class);
                        intent.putExtras(vehicleBundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);

                    } else if(data.get(position).getStatus() == 1){
                        //Esto es para unidades color amarillo
                        Bundle vehicleBundle = new Bundle();
                        vehicleBundle.putString("url_image", data.get(position).getUrl_Image());
                        vehicleBundle.putInt("cve_layer", data.get(position).getCveLayer());
                        vehicleBundle.putString("cve_vehicle", data.get(position).getCve_Vehicle());
                        vehicleBundle.putString("vehicle_name", data.get(position).getVehicle_Name());
                        vehicleBundle.putString("desc_layer", data.get(position).getDesc_Layer());
                        vehicleBundle.putInt("percent_complete", data.get(position).getPercent_Complete());
                        vehicleBundle.putInt("control_point", data.get(position).getControl_Point());
                        vehicleBundle.putInt("percent_to_help", data.get(position).getPercentToHelp());
                        vehicleBundle.putInt("status", data.get(position).getStatus());
                        vehicleBundle.putInt("help_state", data.get(position).getHelp_State());
                        vehicleBundle.putString("georeference", data.get(position).getGeoReference());

                        //Aqui mandamos los datos del Bundle que creamos
                        Intent intent = new Intent(context, UnitAssignSupportAsigmentsViewImpl.class);
                         intent.putExtras(vehicleBundle);
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                         context.startActivity(intent);

                    } else if (data.get(position).getStatus() == 2){
                        //Esto es para unidades color verde
                        Toast.makeText(context, "La ruta no requiere apoyo", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void getAdress(List<String> from) {
        textaddres=from;
    }

    public void setFilter(List<SupportUnitData> unitList) {
        this.data = new ArrayList<>();
        this.data.addAll(unitList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView unitImage;
        ImageView dotColor;
        TextView unitTitle;
        TextView unitRute;
        TextView unitRealPercent;
        TextView unitObjPercent;
        TextView unitDifference;
        TextView unitStatus;
        TextView unitGeoExample;
        LinearLayout ll_main_unit_item_assign_container;
        ConstraintLayout alfashadow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unitImage = itemView.findViewById(R.id.unit_tracking_img2);
            dotColor = itemView.findViewById(R.id.img_dot_color);
            unitTitle = itemView.findViewById(R.id.unit_tracking_title2);
            unitRute = itemView.findViewById(R.id.txt_unit_rute);
            unitRealPercent = itemView.findViewById(R.id.txt_unit_real);
            unitObjPercent = itemView.findViewById(R.id.txt_unit_objective);
            unitDifference = itemView.findViewById(R.id.txt_unit_difference);
            unitStatus = itemView.findViewById(R.id.txt_unit_status);
            ll_main_unit_item_assign_container = itemView.findViewById(R.id.ll_main_unit_item_assign_container);
            alfashadow = itemView.findViewById(R.id.alfashadow);
        }
    }
}
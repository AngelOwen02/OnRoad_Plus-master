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

    //private List<Unit> unitList;
    private List<SupportUnitData> data;
    //private List<SingleUnitSupportData> singleData;
    //private List<Group> groupList;
    private Context context;
    //private UnitAdapter.OnClickVehiclesMapListener listener;
    //private Handler handler;
    //public static  int lastitem;
    public List<String> textaddres= new ArrayList<>();

    //public UnitAssignSupportAdapter(List<Unit> unitList, Context context) {
    public UnitAssignSupportAdapter(List<SupportUnitData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //final View view = LayoutInflater.from(context).inflate(R.layout.item_unit, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.unit_support, parent, false);
        return new UnitAssignSupportAdapter.ViewHolder(view);
    }

    /**@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Unit vehicle = vehicleList.get(position);
        //Log.e("unitsthaticansaw3",""+ UnitDB.getUnitListActive());
        //Log.e("unitsthaticansaw3",""+ integerList);
        if(!UnitDB.getUnitListActive().isEmpty()) {
            Log.e("unitsfirstscreen","size db"+ UnitDB.getUnitListActive().size());
            for(int i=0;i<UnitDB.getUnitListActive().size();i++)
            {

                Log.e("unitsfirstscreen",""+ i);
                integerList.add(UnitDB.getUnitListActive().get(i).getCveVehicle());// integerList.add(UnitDB.getUnitListActive().get(i).getCveVehicle());
            }
            SplashScreenActivity.datapersistance=UnitDB.getUnitListActive();
        }
        /**if (integerList.contains(Integer.valueOf( vehicle.getCveVehicle()))) {
            holder.unitSwicth.setChecked(true);
            UnitDB.updateCheckedStatus(vehicle.getVehicleName(),true);
        } else {
            holder.unitSwicth.setChecked(false);
            UnitDB.updateCheckedStatus(vehicle.getVehicleName(),false);

        }*/


        //Log.e("unitsthaticansaw",""+integerList);

        /**Log.e("doitonce", "" + integerList);
        if (vehicleList.get(position).getVehicleImage() == null) {
            Glide.with(context).load(R.drawable.sedan).into(holder.unitImage);
        } else if (vehicleList.get(position).getVehicleImage().equals("string")) {
            Glide.with(context).load(R.drawable.sedan).into(holder.unitImage);
        } else if (vehicleList.get(position).getVehicleImage().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(R.drawable.sedan).into(holder.unitImage);
        } else {
            Glide.with(context).load(vehicleList.get(position).getVehicleImage()).into(holder.unitImage);
        }

        //Aqui es donde mostramos los datos en la vista
        //Manera corta
        //holder.unitTitle.setText(vehicle.getVehicleName());

        //Para revisar que el nombre si exista y lo colocamos (Manera larga)
        if (vehicleList.get(position).getVehicleName() != null){
            if (vehicleList.get(position).getVehicleName().equals("")){
                holder.unitTitle.setText("----");
            } else {
                holder.unitTitle.setText(vehicleList.get(position).getVehicleName());
            }
        }else {
            holder.unitTitle.setText("----");
        }

        //holder.unitRute.setText("4.01");
        holder.unitRute.setText(vehicle.getDescModel());

        holder.unitRealPercent.setText("35%");
        holder.unitObjPercent.setText("75%");
        holder.unitDifference.setText("-5%");
        holder.unitDifference.setTextColor(ContextCompat.getColor(context,R.color.colorOrangeYellow));
        //holder.unitDifference.setTextColor(Color.YELLOW);
        holder.unitStatus.setText("A tiempo");
        //Aqui va el color
        holder.unitStatus.setTextColor(ContextCompat.getColor(context,R.color.colorOrangeYellow));
        //holder.unitStatus.setTextColor(0xffa400);

        /**if (vehicleList.get(position).getGeoreference().equals("")||vehicleList.get(position).getGeoreference()==null){
            holder.unitGeoExample.setText("---- ---- ---- ----");

        }else {
            holder.unitGeoExample.setText(vehicle.getGeoreference());
        }*/

        /**if (vehicleList.get(position).getGeoreference() != null){
            if (vehicleList.get(position).getGeoreference().equals("")){
                holder.unitGeoExample.setText("----");
            } else {
                holder.unitGeoExample.setText(vehicleList.get(position).getGeoreference());
            }
        }else {
            holder.unitGeoExample.setText("----");
        }

        //holder.unitGeoExample.setText(vehicle.getDescBrand());
        //holder.unitGeoExample.setText(vehicle.getGeoreference()+ " si");
        //holder.unitGeoExample.setText(vehicle.getGeoreference() + " si");
        //holder.unitGeoExample.setText("Ejemploooooooooooooooooo");

        //Esto servira para el texto, imagenes y lo que tenga que cambiar el color
        if (vehicleList.get(position).getDescBrand().equals("Kenworth")) {
            holder.unitGeoExample.setTextColor(ContextCompat.getColor(context,R.color.colorOrangeYellow));
            //cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBorderCarGreen));
            //imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGreen));
        } else if (vehicleList.get(position).getDescBrand().equals("Nissan")) {
            holder.unitGeoExample.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
            //cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBorderCarOrange));
            //imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarOrange));
        } else if (vehicleList.get(position).getDescBrand().equals("Otra")) {
            holder.unitGeoExample.setTextColor(ContextCompat.getColor(context,R.color.green));
            //cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBorderCarRed));
            //imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarRed));
        } else if (vehicleList.get(position).getDescBrand().equals("Mitsubishi")) {
            holder.unitGeoExample.setTextColor(ContextCompat.getColor(context,R.color.colorBorderCarBlue));
            //cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBlack));
            //imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBlack));
        } else {
            //cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBorderCarGray));
            //imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGray));
            holder.unitGeoExample.setTextColor(ContextCompat.getColor(context,R.color.colorBlack));
        }
    }*/

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        holder.itemView.findFocus().getScrollBarSize();
            //String sendTime = unitList.get(position).getSendTime();
            // Log.e("bloquesdeunides",""+holder.getAdapterPosition() +"   LP "+unitList.get(position).getCveVehicle());//+holder.getLayoutPosition()+"  ");
            //lastitem=holder.getAdapterPosition();
            //Log.e("bloquesdeunides",""+lastitem);

            /**SetData*/

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

            //Para que aparezca el color dependiendo el Porcentaje de Diferencia
            /**if (data.get(position).getPercent_Complete() >= 0 && data.get(position).getPercent_Complete() <= 100){
                //Toast.makeText(context, "Esta dentro del rango", Toast.LENGTH_LONG).show();
                if(data.get(position).getPercent_Complete() <= 4){
                    //Aqui ingresamos el texto que nos llega del EndPoint
                    holder.unitDifference.setText(PercentHelp + "%");
                } else if(data.get(position).getPercent_Complete() <= 98) {
                    //Aqui ingresamos el texto que nos llega del EndPoint
                    holder.unitDifference.setText(PercentHelp + "%");
                    holder.unitDifference.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                    //holder.unitImage.setImageResource(R.drawable.ic_camion_verde);
                }
            }*/

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

                //Esto es para desactivar los campos cuando esten bien los datos del EndPoint
                /**holder.ll_main_unit_item_assign_container.setEnabled(false);
                holder.unitImage.setEnabled(false);
                holder.alfashadow.setVisibility(View.VISIBLE);*/

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

            //if (unitList.get(position).getVehicleImage() == null) {
            //    Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.unitImage);
            //} else if (unitList.get(position).getVehicleImage().equals("string")) {
            //    Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.unitImage);
            //} else if (unitList.get(position).getVehicleImage().equals(GeneralConstantsV2.NO_IMAGE)) {
            //    Glide.with(context).load(R.drawable.sedan).thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.unitImage);
            //} else {
            //    Glide.with(context).load(unitList.get(position).getVehicleImage()) .thumbnail(/*sizeMultiplier=*/ 0.05f).into(holder.unitImage);
            //}

            /**OnClick*/
//        holder.llMainContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, UnitMapContainer.class);
//                context.startActivity(intent);
//
//            }
//        });

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


                    //Esto es para mandarlo directo
                    /**Bundle vehicleBundle = new Bundle();
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
                    context.startActivity(intent);*/
                }
            });
        }


    /**@Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public void  booleanList(List<Boolean> toggles)
    {
        togglesList=toggles;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView unitImage;
        TextView unitTitle;
        TextView unitRute;
        TextView unitRealPercent;
        TextView unitObjPercent;
        TextView unitDifference;
        TextView unitStatus;
        TextView unitGeoExample;
        //Switch unitSwicth;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unitImage = itemView.findViewById(R.id.unit_tracking_img2);
            unitTitle = itemView.findViewById(R.id.unit_tracking_title2);
            unitRute = itemView.findViewById(R.id.txt_unit_rute);
            unitRealPercent = itemView.findViewById(R.id.txt_unit_real);
            unitObjPercent = itemView.findViewById(R.id.txt_unit_objective);
            unitDifference = itemView.findViewById(R.id.txt_unit_difference);
            unitStatus = itemView.findViewById(R.id.txt_unit_status);
            unitGeoExample = itemView.findViewById(R.id.txt_unit_geo_example);
            //unitSwicth = itemView.findViewById(R.id.unit_tracking_swicth);
        }
    }

    public void setFilter(List<Unit> vehicles) {
        this.vehicleList = new ArrayList<>();
        this.vehicleList.addAll(vehicles);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }*/

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void getAdress(List<String> from) {
        textaddres=from;
    }

    /**public interface OnClickVehiclesMapListener {
        void onClickVehiclesMap(int position);
    }*/

    /**public class ViewHolder extends RecyclerView.ViewHolder {

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
    }*/

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
        //Switch unitSwicth;


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

            //unitGeoExample = itemView.findViewById(R.id.txt_unit_geo_example);
            //unitSwicth = itemView.findViewById(R.id.unit_tracking_swicth);
        }
    }
}

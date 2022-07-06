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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.UnitMapContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.adapter.UnitAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.UnitSupport;
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

    /**@NonNull
    @Override
    public UnitAssignSupportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_support, parent, false);
        return new UnitAssignSupportAdapter.ViewHolder(view);
    }*/

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

        /**holder.unitSwicth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firstLoginFalse();
                    unitSelected();
                    UnitDB.updateCheckedStatus(vehicle.getVehicleName(),true);

                    if(vehicleList.size()==togglesList.size())
                    {

                        togglesList.get(position).equals(true);
                        if(integerList.contains((Integer.valueOf(vehicle.getCveVehicle()))))
                        {

                        }else {
                            integerList.add(vehicleList.get(position).getCveVehicle());
                        }
                    }
                    Log.e("groupsitems","n "+integerList);
                }else{
                    UnitDB.updateCheckedStatus(vehicle.getVehicleName(),false);


                    if(integerList.contains((Integer.valueOf(vehicle.getCveVehicle()))))
                    {
                        integerList.remove(Integer.valueOf(vehicle.getCveVehicle()));
                        if( UnitDB.getUnitListActive().isEmpty())
                        {
                            integerList.clear();
                        }
                        else{
                            List<Integer> namesinlist=new ArrayList<>();
                            for(int i=0;i<UnitDB.getUnitListActive().size();i++)
                            {
                                namesinlist.add( UnitDB.getUnitListActive().get(i).getCveVehicle());

                            }
                            integerList.clear();
                            integerList=namesinlist;
                        }
                    }


                    Log.e("groupsitems","iL   "+integerList);
                    Log.e("groupsitems","cveActive  "+ UnitDB.getUnitListActive());
                    //integerList.remove(vehicle.getCveVehicle());
                }
            }

        });*/

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
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.itemView.findFocus().getScrollBarSize();
            //String sendTime = unitList.get(position).getSendTime();
            // Log.e("bloquesdeunides",""+holder.getAdapterPosition() +"   LP "+unitList.get(position).getCveVehicle());//+holder.getLayoutPosition()+"  ");
            //lastitem=holder.getAdapterPosition();
            //Log.e("bloquesdeunides",""+lastitem);
            /**if (sendTime != null) {
                Calendar calendar = Calendar.getInstance();
                String[] sendTimeformat = sendTime.split(" ");
                String date = sendTimeformat[0];
                String time = sendTimeformat[1];

                String[] dateFormat = date.split("-");
                String year = dateFormat[0];
                String month = dateFormat[1];
                String day = dateFormat[2];

                int deviceDay = calendar.get(Calendar.DAY_OF_MONTH);
                int deviceMonth = calendar.get(Calendar.MONTH);
                int deviceYear = calendar.get(Calendar.YEAR);

                int deviceMonthDF = deviceMonth + 1;
                int dayResult = deviceDay - Integer.valueOf(day);
                int monthResult = deviceMonthDF - Integer.valueOf(month);
                int yearResult = deviceYear - Integer.valueOf(year);


                String[] timeformat = time.split(":");
                String hour = timeformat[0];
                String minute = timeformat[1];
                String second = timeformat[2];

                int deviceHour = calendar.get(Calendar.HOUR_OF_DAY);
                int deviceMinutes = calendar.get(Calendar.MINUTE);
                int deviceSeconds = calendar.get(Calendar.SECOND);

                int wsHour = Integer.valueOf(hour);
                int wsMinute = Integer.valueOf(minute);
                int wsSecond = Integer.valueOf(second);

                int hourResult = deviceHour - wsHour;
                int minuteResult = deviceMinutes - wsMinute;
                Log.e("debugthesendtime",""+unitList.get(position).getVehicleName()+": "+sendTime+" Y:"+yearResult+"  M:"+monthResult+"  D:"+dayResult+"  H:"+hourResult+" M:"+minuteResult);
                if (yearResult == 0) {
                    if (monthResult == 0) {
                        if (dayResult == 0) {
                            if (hourResult == 0) {
                                if (minuteResult <= 14) {
                                    holder.txtLastSendTime.setText("0" + minuteResult + " min");
                                } else {
                                    holder.txtLastSendTime.setText(minuteResult + " min");
                                }
                            } else {
                                if (hourResult <= 6) {
                                    holder.txtLastSendTime.setText(hourResult + " hr");
                                } else {
                                    holder.txtLastSendTime.setText(hourResult + " hrs");
                                }
                            }
                        } else {
                            if (dayResult >= -1) {
                                holder.txtLastSendTime.setText(dayResult + " día");
                            } else {
                                holder.txtLastSendTime.setText(dayResult + " días");
                            }
                        }
                    }else{
                        if  (monthResult >= 1) {
                            holder.txtLastSendTime.setText(monthResult + " M");
                        } else {
                            holder.txtLastSendTime.setText(monthResult + " M");
                        }
                    }
                }

            } else {
                holder.txtLastSendTime.setText("----");

            }*/

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
                holder.unitStatus.setText("Atrasado");
                holder.unitStatus.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                holder.unitDifference.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                holder.unitImage.setImageResource(R.drawable.ic_camion_azul);
                holder.dotColor.setImageResource(R.drawable.red_dot);
            } else if (data.get(position).getStatus() == 2){
                holder.unitStatus.setText("A tiempo");
                holder.unitStatus.setTextColor(ContextCompat.getColor(context,R.color.colorOrangeYellow));
                holder.unitDifference.setTextColor(ContextCompat.getColor(context,R.color.colorOrangeYellow));
                holder.dotColor.setImageResource(R.drawable.yellow_dot);
            } else if (data.get(position).getStatus() == 3){
                holder.unitStatus.setText("Avanzado");
                holder.unitStatus.setTextColor(ContextCompat.getColor(context,R.color.green));
                holder.unitDifference.setTextColor(ContextCompat.getColor(context,R.color.green));
                holder.dotColor.setImageResource(R.drawable.green_dot);
            } else if (data.get(position).getStatus() == 0){
                holder.unitStatus.setText("Sin Estatus");
            }

            //Para que aparezca el icono del camion dependiendo el Json
            /**if(data.get(position).getHelp_State() == 1){
                holder.unitImage.setImageResource(R.drawable.ic_camion_verde);
            } else if (data.get(position).getHelp_State() == 2){
                //nada
            } else if (data.get(position).getHelp_State() == 0){
                holder.unitImage.setImageResource(R.drawable.ic_camion_gris);
            }*/

            //Ubicacion
            if(textaddres!=null)
            {
                // Log.e("bloquesdeunides","list String :   "+textaddres.get(position));

                //  if(textaddres.get(position).equals(""))
                //   {
                //     holder.txtUnitGeoReference.setText("---- ---- ---- ----");
                //   }
                //       else{
                //    holder.txtUnitGeoReference.setText();
                for(String name:textaddres )
                {
                    if(name.contains(data.get(position).getVehicle_Name()))

                    {
                        String[] parts1 = name.split(":::");
                        String part1 = parts1[0];
                        String part2 = parts1[1];
                        holder.unitGeoExample.setText(part2);      /**puedes vrificar que las direcciones coincidan con los items introduciendo part 1 en el campo part2*/
                    }
                }

                //  }
            }else
            {
                holder.unitGeoExample.setText("---- ---- ---- ----");
            }
    /*    if (unitList.get(position).getGeoreference() != null){
            if (unitList.get(position).getGeoreference().equals("")){
                holder.txtUnitGeoReference.setText("---- ---- ---- ----");
            } else {
                holder.txtUnitGeoReference.setText(unitList.get(position).getGeoreference());
            }
        }else {
            holder.txtUnitGeoReference.setText("---- ---- ---- ----");
        }*/

            /**DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
            decimalFormat.format(unitList.get(position).getKmTravel());
            String decimalUnitKMTravel = decimalFormat.format(unitList.get(position).getKmTravel()) + "km";
            holder.txtUnitKmTravel.setText(decimalUnitKMTravel);*/

            /**Status*/
            /**if (unitList.get(position).getVehicleSwitch() == 1) {
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarGreen));
                holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGreen));
            } else if (unitList.get(position).getVehicleSwitch() == 2) {
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarOrange));
                holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBorderCarOrange));
            } else if (unitList.get(position).getVehicleSwitch() == 3) {
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarRed));
                holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBorderCarRed));
            }else if (unitList.get(position).getVehicleSwitch() == 4) {
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlack));
                holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBlack));
            } else if (unitList.get(position).getVehicleSwitch()== 0){
                holder.cardViewUnitContainer.setCardBackgroundColor(context.getResources().getColor(R.color.colorBorderCarGray));
                holder.imgUnitCircle.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGray));
            }*/

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

            holder.unitImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

                    Toast.makeText(context, vehicleBundle.toString(), Toast.LENGTH_LONG).show();
                }
            });

            /**holder.llMainContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle vehicleBundle = new Bundle();

                    vehicleBundle.putInt("vehicleCve", unitList.get(position).getCveVehicle());
                    vehicleBundle.putInt("vehicleSwitch", unitList.get(position).getVehicleSwitch());
                    vehicleBundle.putString("vehicleName", unitList.get(position).getVehicleName());
                    vehicleBundle.putString("vehicleImage", unitList.get(position).getVehicleImage());
                    vehicleBundle.putString("vehicleSendTime", unitList.get(position).getSendTime());
                    vehicleBundle.putString("vehicleDescBrand", unitList.get(position).getDescBrand());
                    vehicleBundle.putString("vehicleDescModel", unitList.get(position).getDescModel());
                    vehicleBundle.putString("vehicleYear", unitList.get(position).getVehicleYear());
                    vehicleBundle.putString("vehicleVin", unitList.get(position).getVehicleVin());
                    vehicleBundle.putString("vehiclePlate", unitList.get(position).getVehiclePlate());
                    vehicleBundle.putString("vehicleGeoreference", unitList.get(position).getGeoreference());
                    vehicleBundle.putString("vehicleTimeTravel", unitList.get(position).getTimeTravel());
                    vehicleBundle.putString("vehicleTimeElapsed", unitList.get(position).getTimeElapsed());
                    vehicleBundle.putDouble("vehicleLatitude", unitList.get(position).getLatitude());
                    vehicleBundle.putDouble("vehicleLongitude", unitList.get(position).getLongitude());
                    vehicleBundle.putString("vehicleMileage", unitList.get(position).getVehicleName());
                    vehicleBundle.putDouble("vehicleKmTravel", unitList.get(position).getKmTravel());
                    vehicleBundle.putDouble("vehicleCurrentSpeed", unitList.get(position).getCurrentSpeed());
                    vehicleBundle.putDouble("vehicleMaxSpeed", unitList.get(position).getMaxSpeed());

                    Intent intent = new Intent(context, UnitMapContainer.class);
                    intent.putExtras(vehicleBundle);
                    context.startActivity(intent);
                }
            });*/
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
            unitGeoExample = itemView.findViewById(R.id.txt_unit_geo_example);
            //unitSwicth = itemView.findViewById(R.id.unit_tracking_swicth);
        }
    }

    /**public void setFilter(List<Unit> unitList) {
        this.unitList = new ArrayList<>();
        this.unitList.addAll(unitList);
        notifyDataSetChanged();
    }*/

    /**public void setFilter(List<SupportUnitData> unitList) {
        this.data = new ArrayList<>();
        this.data.addAll(unitList);
        notifyDataSetChanged();
    }*/
}

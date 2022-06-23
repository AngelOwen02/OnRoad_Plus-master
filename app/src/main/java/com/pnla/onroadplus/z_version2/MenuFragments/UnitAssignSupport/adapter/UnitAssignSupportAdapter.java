package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.SplashScreenActivity;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UnitAssignSupportAdapter extends RecyclerView.Adapter<UnitAssignSupportAdapter.ViewHolder> {

    private List<Unit> vehicleList;
    private Context context;
    public static List<Integer> integerList = new ArrayList<>();
    public  static List<Boolean> togglesList=new ArrayList<>();
    public List<String> textaddres= new ArrayList<>();

    public UnitAssignSupportAdapter(List<Unit> vehicleList, Context context) {
        this.vehicleList = vehicleList;
        this.context = context;
    }

    @NonNull
    @Override
    public UnitAssignSupportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_support, parent, false);
        return new UnitAssignSupportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
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

        Log.e("doitonce", "" + integerList);
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

        if (vehicleList.get(position).getGeoreference() != null){
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
    }

    @Override
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
    }

    public void getAdress(List<String> from) {
        textaddres=from;
    }
}

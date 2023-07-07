package com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.SplashScreenActivity;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

public class UnitTrackingAdapter extends RecyclerView.Adapter<UnitTrackingAdapter.ViewHolder> {

    private List<Unit> vehicleList;
    private Context context;
    public static List<Integer> integerList = new ArrayList<>();
    public  static List<Boolean> togglesList=new ArrayList<>();

    public UnitTrackingAdapter(List<Unit> vehicleList, Context context) {
        this.vehicleList = vehicleList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_tracking, parent, false);
        return new UnitTrackingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Unit vehicle = vehicleList.get(position);
        //Log.e("unitsthaticansaw3",""+ UnitDB.getUnitListActive());
        //Log.e("unitsthaticansaw3",""+ integerList);
        if(!UnitDB.getUnitListActive().isEmpty()) {
            //Log.e("unitsfirstscreen","size db"+ UnitDB.getUnitListActive().size());
            for(int i=0;i<UnitDB.getUnitListActive().size();i++)
            {

                Log.e("unitsfirstscreen",""+ i);
                integerList.add(UnitDB.getUnitListActive().get(i).getCveVehicle());// integerList.add(UnitDB.getUnitListActive().get(i).getCveVehicle());
            }
            SplashScreenActivity.datapersistance=UnitDB.getUnitListActive();
        }
        if (integerList.contains(Integer.valueOf( vehicle.getCveVehicle()))) {
            holder.unitSwicth.setChecked(true);
            UnitDB.updateCheckedStatus(vehicle.getVehicleName(),true);
        } else {
            holder.unitSwicth.setChecked(false);
            UnitDB.updateCheckedStatus(vehicle.getVehicleName(),false);

        }


        //Log.e("unitsthaticansaw",""+integerList);

        holder.unitSwicth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                   // Log.e("groupsitems","cveActive  "+ UnitDB.getUnitListActive());
                    //integerList.remove(vehicle.getCveVehicle());
                }
            }

        });
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

        holder.unitTitle.setText(vehicle.getVehicleName());

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
        Switch unitSwicth;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unitImage = itemView.findViewById(R.id.unit_tracking_img);
            unitTitle = itemView.findViewById(R.id.unit_tracking_title);
            unitSwicth = itemView.findViewById(R.id.unit_tracking_swicth);
        }
    }

    public void setFilter(List<Unit> vehicles) {
        this.vehicleList = new ArrayList<>();
        this.vehicleList.addAll(vehicles);
        notifyDataSetChanged();
    }

   /* @Override
    public long getItemId(int position) {
        return position;
    }*/

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    private void firstLoginTrue() {
        SharedPreferences prefs = context.getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirst", true);
        editor.commit();
    }

    private void firstLoginFalse() {
        SharedPreferences prefs = context.getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirst", false);
        editor.commit();
    }

    private void unitSelected() {
        SharedPreferences prefs = context.getSharedPreferences("TrackingUnit:Selected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isSelected", true);
        editor.commit();
    }
}

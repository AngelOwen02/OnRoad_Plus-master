package com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter;

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
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.interactor.UnitTrackingInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter.UnitTrackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.UnitGroup;

import java.util.ArrayList;
import java.util.List;

public class GroupTrackingAdapter extends RecyclerView.Adapter<GroupTrackingAdapter.ViewHolder> {

    private List<Group> groupTrackings;
    private Context context;
    public static List<Integer> integerList1 = new ArrayList<>();
    public  static List<Boolean> togglesList1=new ArrayList<>();
    private UnitTrackingPresenter presenter;
    public static int intforrequest;
public static boolean request=false;
    public static  List<String> dataofvehiclesgroupsnames2=new ArrayList<>();
    public GroupTrackingAdapter(List<Group> groups, Context context) {
        this.groupTrackings = groups;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group_tracking, parent, false);
        return new GroupTrackingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Log.e("groupsitems","YList "+ integerList1);
        final Group vehicle = groupTrackings.get(position);
       //Log.e("grupos",""+groupTrackings.size());

        Log.e("groupsitems","IL"+UnitTrackingAdapter.integerList);
        Log.e("groupsitems","IL1"+integerList1);

        if(!GroupDB.getActiveGroupList().isEmpty()) {
            for(int i=0;i<GroupDB.getActiveGroupList().size();i++)
            {
                integerList1.add(GroupDB.getActiveGroupList().get(i).getCve_vehicle_group());
            }
        }
        if (integerList1.contains(Integer.valueOf( vehicle.getCve_vehicle_group()))) {
            holder.switchGroup.setChecked(true);



            // UnitTrackingAdapter.integerList.add(17412);/**ejemplo de unidad aderida */
        } else {
            holder.switchGroup.setChecked(false);

        }


        holder.txtNameGroup.setText(groupTrackings.get(position).getVehicle_group());

        holder.switchGroup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    groupSelected();
                    firstLoginFalse();

                   GroupDB.updateCheckedStatus(vehicle.getCve_vehicle_group(),true);                 /**     esto deberia corresponder a la base que queda en true */
                 //   Log.e("dothetogles","listapor - "+ UnitDB.getUnitListActive());

                 //   Log.e("dothetogles",": "+  vehicle.getVehicles().toString());
                    if (groupTrackings.size() == togglesList1.size()){


                        togglesList1.get(position).equals(true);

                        if (integerList1.contains(Integer.valueOf(vehicle.getCve_vehicle_group()))){

                        }else {
                         //   presenter.getVehiclesinGroups(context);
                           /* String items=UnitsInteractorImpl.dataofvehiclesgroupsnames.get(position).substring(1);
                              Log.e("items",""+items);
                              String items2=items.substring(0,items.length()-1);
                              Log.e("items", ""+items2);
                              int commas = 1;
                              for (int i = 0; i<items2.length(); i++){
                              if(items2.charAt(i)== ',') commas++;}
                              String[] parts1 = items2.split(",");
                              for(int i=0;i<commas;i++){
                              UnitTrackingAdapter.integerList.add(Integer.parseInt(parts1[i].toString().trim()));
                               Log.e("enteros",""+UnitTrackingAdapter.integerList.size());}*/
                            integerList1.add(groupTrackings.get(position).getCve_vehicle_group());/**      esto solo es la lista de toogles que quedan seteados */
                            Log.e("dothetogles","tooglelist=   "+ groupTrackings.get(position).getCve_vehicle_group());
                            request=true;
                            intforrequest=(groupTrackings.get(position).getCve_vehicle_group());

                           // String items = dataofvehiclesgroupsnames2.toString();
                           // Log.e("itemsnew","toogleadd"+items);
                            //UnitTrackingAdapter.integerList.add(Integer.parseInt(UnitsInteractorImpl.dataofvehiclesgroupsnames.toString().trim()));


                            Log.e("groupsitems","IL1+"+integerList1);
                        }
                    }


                }else{
                      GroupDB.updateCheckedStatus(vehicle.getCve_vehicle_group(),false);              /**     esto deberia corresponder a la base que queda en false */

                    togglesList1.get(position).equals(false);
                    //Log.e("dothetogles","tooglelist=  "+ togglesList1);
                    if (integerList1.contains((Integer.valueOf(vehicle.getCve_vehicle_group())))){
                     /*   String items=UnitsInteractorImpl.dataofvehiclesgroupsnames.get(position).substring(1);                        String items2=items.substring(0,items.length()-1);                        //int commas = integerList1.size();                        int commas = 1;                        for (int i = 0; i<items2.length(); i++){                            if(items2.charAt(i)== ',') commas++;                        }                        String[] parts1 = items2.split(",");                        Log.e("parts2",""+ items2);                        for(int i = 0; i<commas; i++)                        {                            //UnitTrackingAdapter.integerList.clear();                            //Log.e("position",""+UnitTrackingAdapter.integerList.get(position));                            //UnitTrackingAdapter.integerList.remove(Integer.parseInt(parts1[i].trim()));                            UnitTrackingAdapter.integerList = new ArrayList<>();                            if (UnitTrackingAdapter.integerList.contains(Integer.parseInt(parts1[i].trim()))){                                UnitTrackingAdapter.integerList.remove(vehicle.getPositionItem());                            }else {                                UnitTrackingAdapter.integerList = new ArrayList<>();                            }                        }*/
                        integerList1.remove(Integer.valueOf(vehicle.getCve_vehicle_group()));   /**      esto solo es la lista de toogles que quedan seteados */
                        Log.e("dothetogles","toogledelete=   remove"+ groupTrackings.get(position).getCve_vehicle_group());
                        request=false;
                        if(!UnitTrackingAdapter.integerList.isEmpty())
                        {
                            intforrequest=(groupTrackings.get(position).getCve_vehicle_group());
                            Log.e("itemsnew","QUITAR              "+ intforrequest);
                        }
                        else {
                            intforrequest = 0;
                            Log.e("itemsnew","QUITAR              "+ intforrequest);
                        }
                        String items = dataofvehiclesgroupsnames2.toString();
                      //  Log.e("itemsnew","toogledelete"+items+"  QUITAR"+ UnitTrackingInteractorImpl.groupsdataInteger);
                        //UnitTrackingAdapter.integerList.remove(Integer.parseInt(UnitsInteractorImpl.dataofvehiclesgroupsnames.toString().trim()));
                      for (int i=0;i<UnitTrackingInteractorImpl.groupsdataInteger.size();i++)
                        {
                            if(UnitTrackingInteractorImpl.groupsdataInteger.contains(UnitTrackingInteractorImpl.groupsdataInteger.get(i)))
                            {

                            }
                            else
                            {
                                UnitTrackingAdapter.integerList.remove(UnitTrackingInteractorImpl.groupsdataInteger.get(i));
                            }

                        }

                        Log.e("itemsnew","QUITAR       datos "+ UnitTrackingInteractorImpl.groupsdataInteger);
                        Log.e("itemsnew","lista remove intgl "+ UnitTrackingAdapter.integerList);

                       /* if( UnitDB.getUnitListActive().isEmpty())
                        {
                            UnitTrackingAdapter.integerList.clear();
                        }
                        else{
                            List<Integer> namesinlist=new ArrayList<>();
                            for(int i=0;i<UnitDB.getUnitListActive().size();i++)
                            {
                                namesinlist.add( UnitDB.getUnitListActive().get(i).getCveVehicle());

                            }
                            UnitTrackingAdapter.integerList.clear();
                            UnitTrackingAdapter.integerList=namesinlist;
                        }*/

                     /*  if(GroupDB.getActiveGroupList().isEmpty())
                       {
                           if(UnitTrackingAdapter.integerList.isEmpty()||UnitTrackingAdapter.integerList==null)
                           {
                               UnitDB.getUnitListActive().clear();

                           }
                       }*/
                        if(GroupDB.getActiveGroupList().isEmpty())
                        {
                            integerList1.clear();
                        }
                        else
                        {
                            List<Integer> namesinlist=new ArrayList<>();
                            for(int i=0;i<GroupDB.getActiveGroupList().size();i++)
                            {
                                namesinlist.add( GroupDB.getActiveGroupList().get(i).getCve_vehicle_group());

                            }
                            integerList1.clear();
                            integerList1=namesinlist;
                        }
                        Log.e("groupsitems","IL1-"+integerList1);
                    }
                }
            }
        });


        List<UnitGroup> vehicleList = groupTrackings.get(position).getVehicles();
        if (vehicleList != null) {
            int numberVehicles = vehicleList.size();
            if (numberVehicles == 1) {
                holder.txtVehiclesNumber.setText("" + numberVehicles + " " + context.getString(R.string.textVehicle));
            } else {
                holder.txtVehiclesNumber.setText("" + numberVehicles + " " + context.getString(R.string.textVehicles));
            }
        } else {
            holder.txtVehiclesNumber.setText("" + context.getString(R.string.textWithoutVehicles));
        }

        Glide.with(context).load(R.drawable.car).into(holder.imgGroup);

    }


    @Override
    public int getItemCount() {
        return groupTrackings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameGroup;
        private TextView txtVehiclesNumber;
        private ImageView imgGroup;
        private Switch switchGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNameGroup = itemView.findViewById(R.id.txt_name_item_group_tracking);
            txtVehiclesNumber = itemView.findViewById(R.id.txt_number_item_group_tracking);
            imgGroup = itemView.findViewById(R.id.img_item_group_tracking);
            switchGroup = itemView.findViewById(R.id.switch_item_group_tracking);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public void  booleanList(List<Boolean> toggles)
    {
        togglesList1=toggles;
        notifyDataSetChanged();
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

    private void groupSelected() {
        SharedPreferences prefs = context.getSharedPreferences("TrackingGroup:Selected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isSelected", true);
        editor.commit();
    }

    public void setFilter(List<Group> groups) {
        this.groupTrackings = new ArrayList<>();
        this.groupTrackings.addAll(groups);
        notifyDataSetChanged();
    }


}

package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.view.driversAsingment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Adapter.adapterTripulantes;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.View.zoneAsignViewImpl;

import java.util.ArrayList;
import java.util.List;

public class adapTirpulantes extends RecyclerView.Adapter<adapTirpulantes.ViewHolder>{
    private driversAsingment myviewclass;
    private Context mycontext;
    private List<String> tripulantes;
    private boolean isSelectedAll;
    private List<String> list=new ArrayList<>();
    private List<String> listmirror=new ArrayList<>();
    private     List<Integer> indexl=new ArrayList<>();
    private List<String> templistadd=new ArrayList<>();
    private List<String> templistremove=new ArrayList<>();
    public adapTirpulantes(driversAsingment viewclass, List<String> cveVehicles,List<String> list, Context context) {
        this.myviewclass=viewclass;
        tripulantes=cveVehicles;
        mycontext=context;
        this.list=list;
        listmirror=list;
        templistadd.clear();
        templistremove.clear();
    }
    public void selectAll(){
        isSelectedAll=true;
        listmirror=tripulantes;
        notifyDataSetChanged();
    }
    public void unselectall(){
        listmirror=new ArrayList<>();
        isSelectedAll=false;
        notifyDataSetChanged();
    }
    public void setFilter(List<String> cveVehicles) {
        this.tripulantes=new ArrayList<>();
        this.tripulantes.addAll(cveVehicles);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public adapTirpulantes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mycontext).inflate(R.layout.rv_tripulantes,parent, false);
        return new adapTirpulantes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapTirpulantes.ViewHolder holder, final int position) {
        if(tripulantes.get(position).contains("/")) {
            String[] parts=tripulantes.get(position).split("/");
            holder.nameTripulante.setText(parts[0]);
        }
        // holder.mychekcbox.addFocusables();


        if (!isSelectedAll)
        {holder.mychekcbox.setChecked(false);}
        else
        { holder.mychekcbox.setChecked(true);}
        if(listmirror.size()!=0) {
            if (listmirror.contains(tripulantes.get(position))) {
                holder.mychekcbox.setChecked(true);
                Log.e("tripulantesoverButton","  alldata  "+listmirror);
            }
        }

        holder.mychekcbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                //  objIncome.setSelected(isChecked);
                //buttonView.setChecked(false);

                if(isChecked)
                {
                    if(listmirror.contains(tripulantes.get(position)))
                    {

                    }else {
                        templistadd.add(String.valueOf( tripulantes.get(position)));
                        Log.e("tripulantesoverButton","  TEMPADD  "+tripulantes.get(position)+"  "+templistadd);
                        if(templistremove.contains(String.valueOf(tripulantes.get(position))))
                        {
                            templistremove.remove(tripulantes.get(position));
                        }
                        if(templistadd!=null) {
                            myviewclass.newItemTripulantes(templistadd);
                        }
                    }
                }else
                {


                    Log.e("tripulantesoverButton","  listmirror  "+tripulantes.size()+" "+"    "+listmirror);
                        templistremove.add(String.valueOf( tripulantes.get(position)));
                    Log.e("tripulantesoverButton","  TEMPDELETED  "+"    "+templistremove);
                        if(listmirror.contains(tripulantes.get(position)))
                        {
                            int index= listmirror.indexOf(tripulantes.get(position));
                          Log.e("tripulantesoverButton","  INDEX  "+index);
                           indexl.add(index);
                        }

                        Log.e("tripulantesoverButton"," fIANL TEMPDELETED  "+tripulantes.get(position)+" "+templistremove);
                    if(indexl!=null||!indexl.isEmpty()) {
                        Log.e("tripulantesoverButton",""+indexl);
                        myviewclass.indextoclear(indexl);
                        //myviewclass.newItemTripulantes(listmirror2);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return tripulantes.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintripulante;
        CheckBox mychekcbox;
        TextView nameTripulante;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintripulante =itemView.findViewById(R.id.contrainTrip);
            mychekcbox=itemView.findViewById(R.id.checkboxtripulante);
            nameTripulante=itemView.findViewById(R.id.nametripulante);
        }
    }
}
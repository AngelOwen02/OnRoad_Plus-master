package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Adapter;

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
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.View.zoneAsignViewImpl;

import java.util.ArrayList;
import java.util.List;

public class adapterTripulantes  extends RecyclerView.Adapter<adapterTripulantes.ViewHolder>{
    private zoneAsignViewImpl myviewclass;
    private Context mycontext;
    private List<String> tripulantes;

    private boolean isSelectedAll;
    private List<String> listmirror=new ArrayList<>();
    private List<String> list=new ArrayList<>();
    private List<String> list2=new ArrayList<>();

    public adapterTripulantes(zoneAsignViewImpl viewclass, List<String> cveVehicles,List<String> list, Context context) {
        this.myviewclass=viewclass;
        this.tripulantes=cveVehicles;
        this.mycontext=context;
        this.list=list;
        this.list2=list;
    }
    public void selectAll(){
        isSelectedAll=true;
        listmirror=tripulantes;
        list=tripulantes;
        list2=tripulantes;
        myviewclass.tripulantestcheckBox(list2);/**list2 debe ser igual tripulantes y guardar debe mandar a la view*/
        notifyDataSetChanged();
    }
    public void unselectall(){
        listmirror=new ArrayList<>();
        isSelectedAll=false;

      //  list2.clear();
      //  myviewclass.tripulantestcheckBox(list2);/**list2 debe ser igual empty y guardar debe mandar a la view*/
        notifyDataSetChanged();
    }
    public void setFilter(List<String> cveVehicles) {
        this.tripulantes=new ArrayList<>();
        this.tripulantes.addAll(cveVehicles);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mycontext).inflate(R.layout.rv_tripulantes,parent, false);
        return new adapterTripulantes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.nameTripulante.setText(tripulantes.get(position));/**todos los nombres de los tripulantes*/
        if (!isSelectedAll)
        {holder.mychekcbox.setChecked(false);}
        else
        { holder.mychekcbox.setChecked(true); }
        if(list.size()!=0)
        {
            if(list.contains(tripulantes.get(position))){
                holder.mychekcbox.setChecked(true );
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
                    if(list.contains(tripulantes.get(position))){
                        Log.e("tripuFlow","el item ya lo contiene");
                     }else
                    {

                        list2.add(tripulantes.get(position));
                        Log.e("tripuFlow","no lo contiene agreuga "+tripulantes.get(position)+"      "+list2);
                        myviewclass.tripulantestcheckBox(list2);
                    }
                }else{
                    if(list.contains(tripulantes.get(position))){

                        list2.remove(tripulantes.get(position));
                        Log.e("tripuFlow","el item ya lo contiene reumeve "+tripulantes.get(position)+"    "+list2);
                        myviewclass.tripulantestcheckBox(list2);
                    }else
                    {

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

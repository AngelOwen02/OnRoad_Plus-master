package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListdata;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.adapter.adapterGeocercas;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class checkListAdapter1 extends RecyclerView.Adapter<checkListAdapter1.ViewHolder> {
    private Context context;
    private CheckListViewImpl myview;
    private List<checkListdata> data;

    public checkListAdapter1(List<checkListdata> data,CheckListViewImpl myview, Context context) {
        this.myview=myview;
        this.context=context;
        this.data= data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checklist, parent, false);
        return new checkListAdapter1.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull checkListAdapter1.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.textView11.setText(data.get(position).getDescTripMgmSection());

        //Para la imagen del Aprobador
        if(data.get(position).isAprobador() == true){
            holder.userChecklist.setVisibility(View.VISIBLE);
        } else {
            holder.userChecklist.setVisibility(View.GONE);
        }

        //al dar clic en el carro
        holder.buttonquestions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    if (menuViewImpl.selectedVehicle == false) {
                     Toast.makeText(context, "Debes seleccionar un vehículo ", Toast.LENGTH_SHORT).show();
                      //  myview.goquestionaryFragment(data.get(position).getCveTripMgmSection(), position);
                    } else {
<<<<<<< HEAD
                      myview.goquestionaryFragment(data.get(position).getCveTripMgmSection(), position,data.get(position).isAprobador());
=======

                      myview.goquestionaryFragment(data.get(position).getCveTripMgmSection(), position,data.get(position).isAprobador(),data.get(position).getEmail());
>>>>>>> 0dbd9fe96fa40c7a424cd425156033d56aa7aa75
                        //Toast.makeText(context, data.get(position).getDescTripMgmSection(), Toast.LENGTH_SHORT).show();
                    }
            }
        });

        //Al dar clic en el icono del Aprobador
        holder.userChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Si funciona", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String vehicleName = preferences.getString(GeneralConstantsV2.NAME_CHECKLIST_VEHICLE, null);
        String vehicleCve = preferences.getString(GeneralConstantsV2.CVE_CHECKLIST_VEHICLE, null);

        if(vehicleName!=null) {
            menuViewImpl.selectedVehicle = true;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(GeneralConstantsV2.NAME_CHECKLIST_VEHICLE, vehicleName);
            editor.putString(GeneralConstantsV2.CVE_CHECKLIST_VEHICLE, vehicleCve);

            editor.commit();
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilter(List<checkListdata> checkListdata) {
        this.data = new ArrayList<>();
        this.data.addAll(checkListdata);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout buttonquestions;
        TextView textView11;
        ImageView userChecklist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonquestions = itemView.findViewById(R.id.goQuestions);
            textView11 = itemView.findViewById(R.id.textView11);
            userChecklist = itemView.findViewById(R.id.ic_user_checklist_rv);
        }
    }
}

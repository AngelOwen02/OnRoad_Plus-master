package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.historicChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.Historic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class historicAdapter extends RecyclerView.Adapter<historicAdapter.ViewHolder> {

    private List<Historic> data;
    private Context context;
    private historicChecklist myview;

    public historicAdapter(List<Historic> data, historicChecklist myview, Context context) {
        this.data = data;
        this.myview = myview;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checklist_historic, parent, false);
        return new historicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        //Aqui van los datos del end
        //Esto es para el nombre del vehiculo
        if(data.get(position).getVehicleName() != null) {
            if(data.get(position).getVehicleName().equals("")) {
                holder.unitName.setText("----");
            } else {
                holder.unitName.setText(data.get(position).getVehicleName());
            }
        } else {
            holder.unitName.setText("----");
        }

        //Fecha del vehiculo (?
        String date_s = data.get(position).getDate_Insert();

        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        holder.unitDate.setText(dt1.format(date));

        //Imagen (Semaforo)
        //Verde: Icono de Siga
        if(data.get(position).getTrafficLight() == 1) {
            holder.unitImageScore.setImageResource(R.drawable.ic_siga);
        }
        //Amarillo: Icono de Cuidado
        else if (data.get(position).getTrafficLight() == 2) {
            holder.unitImageScore.setImageResource(R.drawable.ic_warning);
        }
        //Rojo: Icono de Alto
        else if (data.get(position).getTrafficLight() == 3) {
            holder.unitImageScore.setImageResource(R.drawable.ic_stop);
        } else {
            holder.unitImageScore.setVisibility(View.INVISIBLE);
        }

        //Para la imagen del Aprobador
        /**if(data.get(position).getApprovement() == true) {
            holder.unitApproved.setColorFilter(ContextCompat.getColor(context, R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.unitApproved.setVisibility(View.VISIBLE);
        } else if (data.get(position).getApprovement() == false){
            holder.unitApproved.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.unitApproved.setVisibility(View.VISIBLE);
        } else if (data.get(position).getApprovement() == null){
            holder.unitApproved.setVisibility(View.GONE);
        }*/

        if(data.get(position).getApprovement()==null) {
            //Toast.makeText(context, "Vacio", Toast.LENGTH_SHORT).show();
            holder.unitApproved.setVisibility(View.GONE);
        } else if (data.get(position).getApprovement()==(false)) {
            holder.unitApproved.setImageResource(R.drawable.ic_user_checklist);
        } else if (data.get(position).getApprovement()==(true)){
            holder.unitApproved.setImageResource(R.drawable.ic_use_checklist);
        }

        //Score del vehiculo
        holder.unitScore.setText(data.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return data.size();
        //return 4;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {//todo resolved with https://stackoverflow.com/questions/36712704/why-is-my-item-image-in-custom-recyclerview-changing-while-scrolling
        return position;
    }
    public void setFilter(List<Historic> unitList) {
        this.data = new ArrayList<>();
        this.data.addAll(unitList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
     //   ConstraintLayout buttonquestions;

        TextView unitName, unitDate, unitScore;
        ImageView unitImageScore, unitApproved;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // buttonquestions=itemView.findViewById(R.id.goQuestions);

            unitName = itemView.findViewById(R.id.txt_vehicle_name_his);
            unitDate = itemView.findViewById(R.id.txt_date_his);
            unitImageScore = itemView.findViewById(R.id.imageSemaforo_his);
            unitApproved = itemView.findViewById(R.id.ic_user_checklist_historic);
            unitScore = itemView.findViewById(R.id.txt_score_his);
        }
    }
}

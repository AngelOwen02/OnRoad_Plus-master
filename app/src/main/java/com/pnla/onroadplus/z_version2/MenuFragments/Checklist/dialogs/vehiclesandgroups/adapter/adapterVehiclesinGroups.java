package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.Vehiclec2;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.view.dialogViewGroups;
import com.pnla.onroadplus.z_version2.SplashScreenActivity;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

public class adapterVehiclesinGroups  extends RecyclerView.Adapter<adapterVehiclesinGroups.ViewHolder> {

    private Context context;
    private dialogViewGroups myview;
    private List<Vehiclec2> data;
    private checkListPresenter presenter;
    private String valueSelected;

    public adapterVehiclesinGroups(List<Vehiclec2>  data, String value, dialogViewGroups myview, Context context) {
        this.myview = myview;
        this.context = context;
        this.data = data;
        this.valueSelected=value;
    }

    @NonNull
    @Override
    public adapterVehiclesinGroups.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle_dialog, parent, false);
        return new adapterVehiclesinGroups.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final adapterVehiclesinGroups.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // Nombre del Vehiculo
        holder.txtUnitDialog.setText(data.get(position).getVehicleName());

        // Si el vehículo actual está seleccionado, marca la casilla de verificación
        if (data.get(position).getVehicleName().equals(valueSelected)) {
            holder.checkBoxDialog.setVisibility(View.VISIBLE);
            holder.checkBoxDialog.setChecked(true);
        } else {
            holder.checkBoxDialog.setChecked(false);
        }

        // Aquí deselecciona todas las demás casillas de verificación
        for (int i = 0; i < getItemCount(); i++) {
            if (i != position) {
                data.get(i).setChecked(false);
            }
        }

        holder.checkBoxDialog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context, "Vehiculo: " + data.get(position).getVehicleName() + " seleccionado", Toast.LENGTH_SHORT).show();
                    SplashScreenActivity.selectedVehicle = true;
                    data.get(position).setChecked(true); // Guarda ese valor en los shared
                    valueSelected = data.get(position).getVehicleName();
                    myview.newValue(data.get(position).getVehicleName(), data.get(position).getCveVehicle());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public void mnotify() {
        notifyDataSetChanged();
    }
    public void setFilter(List<Vehiclec2> dialogData) {
        this.data = new ArrayList<>();
        this.data.addAll(dialogData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUnitDialog;
        TextView txtUnitDialog;
        CheckBox checkBoxDialog, checkBoxDialog2;

        public ViewHolder(@NonNull View itemview) {
            super(itemview);

            imgUnitDialog = itemview.findViewById(R.id.img_unit_dialog);
            txtUnitDialog = itemview.findViewById(R.id.txt_unit_name_dialog);
            checkBoxDialog = (CheckBox) itemview.findViewById(R.id.checkbox_dialog);
            checkBoxDialog2 = (CheckBox) itemview.findViewById(R.id.checkbox_dialog2);
        }
    }
}
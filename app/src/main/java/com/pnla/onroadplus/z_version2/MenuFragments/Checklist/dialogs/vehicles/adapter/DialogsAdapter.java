package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Interactor.checkListInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Interactor.checkListInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.interactor.DialogsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.interactor.DialogsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view.*;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.*;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.ViewHolder> {

    private Context context;
    private DialogsViewImpl myview;
    private List<DialogsData> data;
    private checkListPresenter presenter;

    public DialogsAdapter(List<DialogsData> data, DialogsViewImpl myview, Context context) {
        this.myview = myview;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle_dialog, parent, false);
        return new DialogsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DialogsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        //Nombre del Vehiculo
        holder.txtUnitDialog.setText(data.get(position).getVehicleName());

        if(menuViewImpl.selectedVehicle == false) {
            holder.checkBoxDialog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked==true) {
                        //Toast.makeText(context, data.get(position).getCveVehicle().toString(), Toast.LENGTH_SHORT).show();
                        String cveVehicle = data.get(position).getCveVehicle().toString();
                        //Toast.makeText(context, cveVehicle, Toast.LENGTH_SHORT).show();

                        menuViewImpl.selectedVehicle = true;
                        myview.closeDialog();
                        //updateS();
                        //holder.checkBoxDialog.setActivated(false);
                        //notifyDataSetChanged();
                    }
                }
            });
        } else {
            holder.checkBoxDialog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked==true) {
                        Toast.makeText(context, data.get(position).getCveVehicle().toString(), Toast.LENGTH_SHORT).show();
                        menuViewImpl.selectedVehicle = true;
                        myview.closeDialog();
                        //updateS();
                        //holder.checkBoxDialog.setActivated(false);
                        //notifyDataSetChanged();
                    }
                }
            });
        }

        /**if(menuViewImpl.selectedVehicle == true) {
            holder.checkBoxDialog.setEnabled(false);
        }*/

        //notifyDataSetChanged();
    }

    public void updateS() {
        checkListInteractor checkListInteractor = new checkListInteractorImpl(presenter, myview.getContext());
        checkListInteractor.requestCheckList();
    }

    @Override
    public int getItemCount() {
        return data.size();
        //return 4;
    }

    public void setFilter(List<DialogsData> dialogData) {
        this.data = new ArrayList<>();
        this.data.addAll(dialogData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUnitDialog;
        TextView txtUnitDialog;
        CheckBox checkBoxDialog;

        public ViewHolder(@NonNull View itemview) {
            super(itemview);

            imgUnitDialog = itemview.findViewById(R.id.img_unit_dialog);
            txtUnitDialog = itemview.findViewById(R.id.txt_unit_name_dialog);
            checkBoxDialog = (CheckBox) itemview.findViewById(R.id.checkbox_dialog);
        }
    }
}

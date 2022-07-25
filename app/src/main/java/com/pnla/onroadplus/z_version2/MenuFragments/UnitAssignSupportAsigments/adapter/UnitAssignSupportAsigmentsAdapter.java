package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.adapter.UnitAssignSupportAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.SingleUnitSupportData;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.view.UnitAssignSupportAsigmentsViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitData;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import de.hdodenhof.circleimageview.CircleImageView;

public class UnitAssignSupportAsigmentsAdapter extends RecyclerView.Adapter<UnitAssignSupportAsigmentsAdapter.ViewHolder> implements View.OnClickListener{

    private List<SingleSupportUnitData> data;
    private String descLayer;
    private Context context;
    private UnitAssignSupportAsigmentsViewImpl myView;

    public UnitAssignSupportAsigmentsAdapter(UnitAssignSupportAsigmentsViewImpl myView, List<SingleSupportUnitData> data, String descLayer, Context context){
        this.data = data;
        this.descLayer = descLayer;
        this.context = context;
        this.myView = myView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //View view = LayoutInflater.from(context).inflate(R.layout.unit_support_cl, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.unit_support_cl_2, parent, false);
        return new UnitAssignSupportAsigmentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position){
        //Set Data
        //Nombre del vehiculo
        holder.unitName.setText(data.get(position).getVehicle_Name());

        //Nombre de la Ruta
        //holder.unitRute.setText(data.get(position).getDesc_Layer());
        holder.unitRute.setText(descLayer);

        //Diferencia del vehiculo (En Porcentaje)
        String PercentHelp = new String(String.valueOf(data.get(position).getPercentToHelp()));
        PercentHelp.equals(data.get(position).getPercentToHelp());
        holder.unitPercent.setText(PercentHelp + "%");

        //Distancia
        holder.unitDistance.setText(String.format("%.2f", data.get(position).getDistance()) + " Km.");

        //Ubicacion
        holder.unitGeo.setText(data.get(position).getGeoReference());

        //Status de la Imagen
        Glide.with(context).load(R.drawable.sedan).into(holder.imgUnitCircle);
        holder.imgUnitCircle.setBorderColor(ContextCompat.getColor(context, R.color.colorBorderCarRed));

        /**if (data.get(position).getUrl_Image() == null) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgUnitCircle);
        }
        else if (data.get(position).getUrl_Image().equals("string")) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgUnitCircle);
        } else if (data.get(position).getUrl_Image().equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(context).load(R.drawable.sedan).into(holder.imgUnitCircle);
        }
        else {
            Glide.with(context).load(data.get(position).getUrl_Image()).into(holder.imgUnitCircle);
        }*/

        //Toast.makeText(context, data.get(position).getUrl_Image(), Toast.LENGTH_SHORT).show();

        /**if (data.get(position).getUrlImage() != null) {
         Glide.with(context).load(data.get(position).getUrlImage()).into(holder.imgUnitCircle);
         } else {
         Glide.with(context).load(R.drawable.sedan).into(holder.imgUnitCircle);
         }*/

        holder.spinnerOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/**esto son los tres puntos de la tarjeta*/
                if(holder.fakespinner.getVisibility()==View.GONE)
                {
                    holder.fakespinner.setVisibility(View.VISIBLE);
                }else
                {
                    holder.fakespinner.setVisibility(View.GONE);
                }
            }
        });

        holder.editUnitfakespinner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.editunitspinner(data.get(position).getCveLayer(), data.get(position).getCve_Vehicle());
                Log.e("assistencesupport", "" + data.get(position).getCve_Vehicle() + "     " + data.get(position).getCveLayer() );
            }
        });

        holder.eraseUnitfakespinner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //corregir cuando ya tenga el nombre dle vehiculo
                myView.alertBuilder(data.get(position).getCve_Vehicle(), data.get(position).getCveLayer());
                //myView.eraseunitfromsupport(data.get(position).getCveLayer(), data.get(position).getCve_Vehicle());
            }
        });
    }

    @Override
    public int getItemCount(){
        return  data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TextViews, etc
        TextView unitName, unitRute, unitPercent, unitDistance, fakeAssign, fakeErase;
        TextView unitGeo, editUnitfakespinner2, eraseUnitfakespinner2;
        ImageView spinnerOptions;
        Spinner myspinnerOptions;
        CardView fakespinner;
        CircleImageView imgUnitCircle;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //Referencias a la vista xml
            unitName = itemView.findViewById(R.id.txt_unit_name_single_rv);
            unitRute = itemView.findViewById(R.id.txt_unit_rute_single_rv);
            imgUnitCircle = itemView.findViewById(R.id.unit_tracking_img2);
            unitPercent = itemView.findViewById(R.id.txt_percent_single_rv);
            unitDistance = itemView.findViewById(R.id.txt_unit_distance_single_rv);
            unitGeo = itemView.findViewById(R.id.txt_unit_geo_reference_single_rv);

            editUnitfakespinner2 = itemView.findViewById(R.id.editUnitfakespinner2);
            eraseUnitfakespinner2 = itemView.findViewById(R.id.eraseUnitfakespinner2);

            //spinnerOptions = itemView.findViewById(R.id.optionspoints);
            spinnerOptions = itemView.findViewById(R.id.optionspoints2);
            myspinnerOptions = itemView.findViewById(R.id.spinnerUnitAsingmentOptions2);

            //ArrayAdapter<CharSequence> adapterOptions = ArrayAdapter.createFromResource(context,R.array.optionsUnitAsignmentsArray2, android.R.layout.simple_spinner_item);
            ArrayAdapter<CharSequence> adapterOptions = ArrayAdapter.createFromResource(context, R.array.optionsUnitAsignmentsArray2, android.R.layout.simple_spinner_item);
            adapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            fakespinner = itemView.findViewById(R.id.constrainUnitSpinnerfake2);
            fakeAssign = itemView.findViewById(R.id.editUnitfakespinner);
            fakeErase = itemView.findViewById(R.id.eraseUnitfakespinner);

            myspinnerOptions.setAdapter(adapterOptions);
        }
    }

    @Override
    public void onClick(View view){
        Toast.makeText(view.getContext(), "Si funciona", Toast.LENGTH_SHORT).show();
    }
}
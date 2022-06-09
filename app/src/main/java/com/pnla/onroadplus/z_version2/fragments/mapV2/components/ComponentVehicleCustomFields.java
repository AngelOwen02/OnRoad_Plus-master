package com.pnla.onroadplus.z_version2.fragments.mapV2.components;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComponentVehicleCustomFields extends ConstraintLayout {

    private Typeface latoRegularTypeface;

    /**
     * Titles
     */
    private TextView txvLastMessageHeaderTitle, txvVehicleAddressHeaderTitle, txvSateliteHeaderTitle, txvVehicleAltitudeHeaderTitle;
    private TextView txvCustomFieldsTitle, txvVehicleMarkTitle, txvVehicleDescriptionTitle, txvVehicleModelTitle;
    private TextView txvVehiclePlateTitle, txvVehicleSerieTitle, txvVehicleInsuranceTitle, txvVehiclePolicyTitle;
    private TextView txvOdometerTitle, txvHorometerTitle;

    /**
     * Values
     */
    private TextView txvLastMessageResponse, txvVehicleAddressResponse, txvSatelitesResponse, txvVehicleAltitudeResponse;
    private TextView txvVehicleMarkResponse, txvVehicleDescriptionResponse, txvVehicleModelResponse, txvVehiclePlateResponse;
    private TextView txvVehicleSerieResponse, txvVehicleInsuranceResponse, txvVehiclePolicyResponse;
    private TextView txvOdometerResponse, txvHorometerResponse;


    public ComponentVehicleCustomFields(Context context) {
        super(context);
        init();
    }

    public ComponentVehicleCustomFields(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentVehicleCustomFields(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.component_vehicle_custom_fields, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

        //views
        txvLastMessageHeaderTitle = findViewById(R.id.txvLastMessageHeaderTitle);
        txvVehicleAddressHeaderTitle = findViewById(R.id.txvVehicleAddressHeaderTitle);
        txvSateliteHeaderTitle = findViewById(R.id.txvSateliteHeaderTitle);
        txvVehicleAltitudeHeaderTitle = findViewById(R.id.txvVehicleAltitudeHeaderTitle);
        txvCustomFieldsTitle = findViewById(R.id.txvCustomFieldsTitle);
        txvVehicleMarkTitle = findViewById(R.id.txvVehicleMarkTitle);
        txvVehicleDescriptionTitle = findViewById(R.id.txvVehicleDescriptionTitle);
        txvVehicleModelTitle = findViewById(R.id.txvVehicleModelTitle);
        txvVehiclePlateTitle = findViewById(R.id.txvVehiclePlateTitle);
        txvVehicleSerieTitle = findViewById(R.id.txvVehicleSerieTitle);
        txvVehicleInsuranceTitle = findViewById(R.id.txvVehicleInsuranceTitle);
        txvVehiclePolicyTitle = findViewById(R.id.txvVehiclePolicyTitle);
        txvOdometerTitle = findViewById(R.id.txvOdometer);
        txvHorometerTitle = findViewById(R.id.txvHorometer);

        txvLastMessageResponse = findViewById(R.id.txvLastMessageResponse);
        txvVehicleAddressResponse = findViewById(R.id.txvVehicleAddressResponse);
        txvSatelitesResponse = findViewById(R.id.txvSatelitesResponse);
        txvVehicleAltitudeResponse = findViewById(R.id.txvVehicleAltitudeResponse);
        txvVehicleMarkResponse = findViewById(R.id.txvVehicleMarkResponse);
        txvVehicleDescriptionResponse = findViewById(R.id.txvVehicleDescriptionResponse);
        txvVehicleModelResponse = findViewById(R.id.txvVehicleModelResponse);
        txvVehiclePlateResponse = findViewById(R.id.txvVehiclePlateResponse);
        txvVehicleSerieResponse = findViewById(R.id.txvVehicleSerieResponse);
        txvVehicleInsuranceResponse = findViewById(R.id.txvVehicleInsuranceResponse);
        txvVehiclePolicyResponse = findViewById(R.id.txvVehiclePolicyResponse);
        txvHorometerResponse = findViewById(R.id.txvHorometerResponse);
        txvOdometerResponse = findViewById(R.id.txvOdometerResponse);

        //fonts
        this.latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        setFonts();
    }

    private void setFonts() {
        txvLastMessageHeaderTitle.setTypeface(latoRegularTypeface);
        txvVehicleAddressHeaderTitle.setTypeface(latoRegularTypeface);
        txvSateliteHeaderTitle.setTypeface(latoRegularTypeface);
        txvVehicleAltitudeHeaderTitle.setTypeface(latoRegularTypeface);
        txvCustomFieldsTitle.setTypeface(latoRegularTypeface);
        txvVehicleMarkTitle.setTypeface(latoRegularTypeface);
        txvVehicleDescriptionTitle.setTypeface(latoRegularTypeface);
        txvVehicleModelTitle.setTypeface(latoRegularTypeface);
        txvVehiclePlateTitle.setTypeface(latoRegularTypeface);
        txvVehicleSerieTitle.setTypeface(latoRegularTypeface);
        txvVehicleInsuranceTitle.setTypeface(latoRegularTypeface);
        txvVehiclePolicyTitle.setTypeface(latoRegularTypeface);
        txvOdometerTitle.setTypeface(latoRegularTypeface);
        txvHorometerTitle.setTypeface(latoRegularTypeface);

        txvLastMessageResponse.setTypeface(latoRegularTypeface);
        txvVehicleAddressResponse.setTypeface(latoRegularTypeface);
        txvSatelitesResponse.setTypeface(latoRegularTypeface);
        txvVehicleAltitudeResponse.setTypeface(latoRegularTypeface);
        txvVehicleMarkResponse.setTypeface(latoRegularTypeface);
        txvVehicleDescriptionResponse.setTypeface(latoRegularTypeface);
        txvVehicleModelResponse.setTypeface(latoRegularTypeface);
        txvVehiclePlateResponse.setTypeface(latoRegularTypeface);
        txvVehicleSerieResponse.setTypeface(latoRegularTypeface);
        txvVehicleInsuranceResponse.setTypeface(latoRegularTypeface);
        txvVehiclePolicyResponse.setTypeface(latoRegularTypeface);
        txvOdometerResponse.setTypeface(latoRegularTypeface);
        txvHorometerResponse.setTypeface(latoRegularTypeface);
    }

    public void setVehicleDescription(VehicleDescriptionData data) {

      /* txvLastMessageResponse.setText(setNullText(data.getLastMessage()));
        txvVehicleAddressHeaderTitle.setText(data.getAddress());
        txvSateliteHeaderTitle.setText(data.getSatelites()+"");
        txvVehicleAltitudeHeaderTitle.setText(data.getAltitude()+"");
        txvVehicleMarkTitle.setText(data.getDescBrand());
        txvVehicleDescriptionTitle.setText(data.getDescModel());
        txvVehicleModelTitle.setText(data.getVehicleYear());
        txvVehiclePlateTitle.setText(data.getVehiclePlate());
        txvVehicleSerieTitle.setText(data.getVehicleVin());
       txvVehiclePolicyTitle.setText(setNullText(data.getPolicyNumber()));
       txvOdometerTitle.setText(setNullText(data.getOdometer()));
       txvHorometerTitle.setText(setNullText(data.getHorometer()));
       txvVehicleInsuranceTitle.setText(setNullText(data.getInsuranceName())); */

        String currentDate = data.getLastMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = null;
        try {
            newDate = dateFormat.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = dateFormat.format(newDate);
       txvLastMessageResponse.setText(setNullText(date));
        txvVehicleAddressResponse.setText(setNullText(data.getAddress()));
        txvSatelitesResponse.setText(setNullText(data.getSatelites() + ""));
        txvVehicleAltitudeResponse.setText(setNullText(data.getAltitude() + ""));
        txvVehicleMarkResponse.setText(setNullText(data.getDescBrand()));
        txvVehicleDescriptionResponse.setText(setNullText(data.getDescModel()));
        txvVehicleModelResponse.setText(setNullText(data.getVehicleYear()));
        txvVehiclePlateResponse.setText(setNullText(data.getVehiclePlate()));
        txvVehicleSerieResponse.setText(setNullText(data.getVehicleVin()));
        txvVehicleInsuranceResponse.setText(setNullText(data.getInsuranceName()));
        txvVehiclePolicyResponse.setText(setNullText(data.getPolicyNumber()));
        txvOdometerResponse.setText(setNullText(data.getOdometer()));
        txvHorometerResponse.setText(setNullText(data.getHorometer()));
    }

    public boolean isEmpty(String data){
        if (data == null){
            return true;
        } else {
            return false;
        }
    }

    public String setNullText ( String data){
        if (isEmpty(data)){
            return "---";
        }else {
            return data;
        }
    }

    public void cleanCustomFields() {
        txvLastMessageResponse.setText(null);
        txvVehicleAddressResponse.setText(null);
        txvSatelitesResponse.setText(null);
        txvVehicleAltitudeResponse.setText(null);
        txvVehicleMarkResponse.setText(null);
        txvVehicleDescriptionResponse.setText(null);
        txvVehicleModelResponse.setText(null);
        txvVehiclePlateResponse.setText(null);
        txvVehicleSerieResponse.setText(null);
        txvVehicleInsuranceResponse.setText(null);
        txvVehiclePolicyResponse.setText(null);
        txvOdometerResponse.setText(null);
        txvHorometerResponse.setText(null);
    }


}

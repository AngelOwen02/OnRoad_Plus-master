package com.pnla.onroadplus.z_version2.fragments.mapV2.components;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ComponentVehicleHeader extends ConstraintLayout implements View.OnClickListener {

    private ConstraintLayout constraintVehicleHeader;
    private CircleImageView imgvVehicleImageHeader;
    private TextView txvVehicleNameHeader, txvHourAndSpeedHeader, txvVehicleAddressHeader;
    private TextView txvSpeedHeader, txvHourHeader, txvKilometersHeader;
    private Typeface latoRegularTypeface, latoBoldTypeface;
    private OnClickVehicleHeaderListener listener;

    private Typeface robotoMedium;
    private Typeface robotoRegular;


    private CircleImageView imgUnitCircle;
    private CardView cardViewUnitContainer;
    private TextView txtUnitName;
    private TextView txtUnitMaxSpeed;
    private TextView txtUnitGeoReference;
    private TextView txtUnitKmTravel;
    private TextView txtLastSendTime;
    private TextView txtHour;
    private ImageView imgMaxSpeed;
    private ImageView imgShape;
    private ImageView imgDistance;
    private LinearLayout llMainContainer;

    public ComponentVehicleHeader(Context context) {
        super(context);
        init();
    }

    public ComponentVehicleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentVehicleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.component_vehicle_header, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

        txtUnitName = findViewById(R.id.txt_vehicle_header_name);
        txtUnitMaxSpeed = findViewById(R.id.txt_vehicle_header_speed);
        txtUnitGeoReference = findViewById(R.id.txt_vehicle_header_adress);
        txtUnitKmTravel = findViewById(R.id.txt_vehicle_header_km);
        txtLastSendTime = findViewById(R.id.txt_vehicle_header_last_send_time);
        txtHour = findViewById(R.id.txt_vehicle_header_hour);
        imgUnitCircle = findViewById(R.id.img_vehicle_header);
        cardViewUnitContainer = findViewById(R.id.cardview_vehicle_header_time_container);
        imgMaxSpeed = findViewById(R.id.img_vehicle_header_speed);
        imgShape = findViewById(R.id.img_vehicle_header_hour);
        imgDistance = findViewById(R.id.img_vehicle_header_km);


        //views
        //constraintVehicleHeader = findViewById(R.id.constraintVehicleHeader);
        //imgvVehicleImageHeader = findViewById(R.id.imgvVehicleImageHeader);
        //txvVehicleNameHeader = findViewById(R.id.txvVehicleNameHeader);
        //txvHourAndSpeedHeader = findViewById(R.id.txvHourAndSpeedHeader);
        //txvVehicleAddressHeader = findViewById(R.id.txvVehicleAddressHeader);
        //txvSpeedHeader = findViewById(R.id.txvSpeedHeader);
        //txvHourHeader = findViewById(R.id.txvHourHeader);
        //txvKilometersHeader = findViewById(R.id.txvKilometersHeader);

        //constraintVehicleHeader.setOnClickListener(this);

        robotoMedium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf");
        robotoRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        setFonts();
    }

    private void setFonts() {
        /*txvVehicleNameHeader.setTypeface(latoBoldTypeface);
        txvHourAndSpeedHeader.setTypeface(latoRegularTypeface);
        txvVehicleAddressHeader.setTypeface(latoRegularTypeface);
        txvSpeedHeader.setTypeface(latoRegularTypeface);
        txvHourHeader.setTypeface(latoRegularTypeface);
        txvKilometersHeader.setTypeface(latoRegularTypeface);*/

        txtUnitName.setTypeface(robotoMedium);
        txtLastSendTime.setTypeface(robotoRegular);
        txtUnitMaxSpeed.setTypeface(robotoRegular);
        txtHour.setTypeface(robotoRegular);
        txtUnitKmTravel.setTypeface(robotoRegular);
        txtUnitGeoReference.setTypeface(robotoRegular);
    }

    public void setVehicleData(int status, String name, String adress, String vehicleTimeTravel, String vehicleKmTravel, String vehicleCurrentSpeed, String image, String sendTime) {

        txtUnitName.setText(name);
        //txtLastSendTime.setText("No se que pedo");

        Log.e("mvehicleSendTime",""+vehicleCurrentSpeed);
        if(vehicleCurrentSpeed.equals(".00"))
        {
            txtUnitMaxSpeed.setText("0.0" + "km/h");
        }
        else if(vehicleCurrentSpeed.equals("0.0"))
        {
            txtUnitMaxSpeed.setText("0.0" + "km/h");
        }else {
            txtUnitMaxSpeed.setText(vehicleCurrentSpeed + "km/h");
        }
        if (txtHour.equals("")) {
            txtHour.setText("----");
        } else {
            txtHour.setText(vehicleTimeTravel);

        }
        txtUnitKmTravel.setText(vehicleKmTravel + "km");
        if (adress.equals("")||adress==null){
            txtUnitGeoReference.setText("---- ---- ---- ----");

        }else {
            txtUnitGeoReference.setText(adress);
        }

        if (status == 1) {
            cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBorderCarGreen));
            imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGreen));
        } else if (status == 2) {
            cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBorderCarOrange));
            imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarOrange));
        } else if (status == 3) {
            cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBorderCarRed));
            imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarRed));
        } else if (status == 4){
            cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBlack));
            imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBlack));
        } else {
            cardViewUnitContainer.setCardBackgroundColor(getContext().getResources().getColor(R.color.colorBorderCarGray));
            imgUnitCircle.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGray));
        }

        if (sendTime != null) {
            Calendar calendar = Calendar.getInstance();
            String[] sendTimeformat = sendTime.split(" ");
            String date = sendTimeformat[0];
            String time = sendTimeformat[1];

            String[] dateFormat = date.split("-");
            String year = dateFormat[0];
            String month = dateFormat[1];
            String day = dateFormat[2];

            int deviceDay = calendar.get(Calendar.DAY_OF_MONTH);
            int deviceMonth = calendar.get(Calendar.MONTH);
            int deviceYear = calendar.get(Calendar.YEAR);


            int deviceMonthDF = deviceMonth + 1;
            int dayResult = deviceDay - Integer.valueOf(day);
            int monthResult = deviceMonthDF - Integer.valueOf(month);
            int yearResult = deviceYear - Integer.valueOf(year);


            String[] timeformat = time.split(":");
            String hour = timeformat[0];
            String minute = timeformat[1];
            String second = timeformat[2];

            int deviceHour = calendar.get(Calendar.HOUR_OF_DAY);
            int deviceMinutes = calendar.get(Calendar.MINUTE);
            int deviceSeconds = calendar.get(Calendar.SECOND);

            int wsHour = Integer.valueOf(hour);
            int wsMinute = Integer.valueOf(minute);
            int wsSecond = Integer.valueOf(second);

            int hourResult = deviceHour - wsHour;
            int minuteResult = deviceMinutes - wsMinute;

            if (yearResult == 0) {
                if (monthResult == 0) {
                    if (dayResult == 0) {
                        if (hourResult == 0) {
                            if (minuteResult <= 9) {
                                txtLastSendTime.setText("0" + minuteResult + " min");
                            } else {
                                txtLastSendTime.setText(minuteResult + " min");
                            }
                        } else {
                            if (hourResult <= 1) {
                                txtLastSendTime.setText(hourResult + " hr");
                            } else {
                                txtLastSendTime.setText(hourResult + " hrs");
                            }
                        }
                    } else {
                        if (dayResult == 1) {
                            txtLastSendTime.setText(dayResult + " día");
                        } else {
                            txtLastSendTime.setText(dayResult + " días");
                        }
                    }
                }else {
                    if (monthResult == 1){
                        txtLastSendTime.setText(monthResult + " mes");
                    }else {
                        txtLastSendTime.setText(monthResult + " meses");
                    }
                }
            }

        } else {
            txtLastSendTime.setText("Sin datos");

        }


        if (image == null) {
            Glide.with(getContext()).load(R.drawable.sedan).into(imgUnitCircle);
        } else if (image.equals("string")) {
            Glide.with(getContext()).load(R.drawable.sedan).into(imgUnitCircle);
        } else if (image.equals(GeneralConstantsV2.NO_IMAGE)) {
            Glide.with(getContext()).load(R.drawable.sedan).into(imgUnitCircle);
        } else {
            Glide.with(getContext()).load(image).into(imgUnitCircle);
        }
    }


    public void setVehicleDataHeader(VehicleV2Map vehicle) {


        int status = vehicle.getVehicleSwitch();

        String vehicleName = vehicle.getVehicleName();
        String vehicleAddres = vehicle.getGeoreference();
        String timeTravel = vehicle.getTimeTravel();
        String kmTravel = vehicle.getKmTravel() + "";
        String timeElapsed = vehicle.getTimeElapsed();
        String currentSpeed = vehicle.getCurrentSpeed() + "";
        String vehicleImage = vehicle.getVehicleImage();

        //VehicleData
        txvVehicleNameHeader.setText(vehicleName);
        txvVehicleAddressHeader.setText(vehicleAddres);
        txvHourHeader.setText(timeTravel);
        txvKilometersHeader.setText(kmTravel);
        // txvHourAndSpeedHeader.setText(timeTravel + " | " + currentSpeed + " km/h");
        txvSpeedHeader.setText(currentSpeed);

        //VehicleImage
        if (vehicleImage == GeneralConstantsV2.NO_IMAGE) {
            Glide.with(getContext()).load(R.drawable.sedan).into(imgvVehicleImageHeader);
        } else {
            Glide.with(getContext()).load(vehicleImage).into(imgvVehicleImageHeader);
        }

        //VehicleStatus
        //pintamos el status del detalle de vehiculo
        if (status == 3) {
            // txvHourAndSpeedHeader.setBackgroundResource(R.drawable.textview_background_speed_red);
            imgvVehicleImageHeader.setBorderColor(getResources().getColor(R.color.colorBorderCarRed));
        } else if (status == 1) {
            //txvHourAndSpeedHeader.setBackgroundResource(R.drawable.textview_background_speed_green);
            imgvVehicleImageHeader.setBorderColor(getResources().getColor(R.color.colorBorderCarGreen));
        } else if (status == 2) {
            // txvHourAndSpeedHeader.setBackgroundResource(R.drawable.textview_background_speed_orange);
            imgvVehicleImageHeader.setBorderColor(getResources().getColor(R.color.colorBorderCarOrange));
        } else if (status == 4){
            imgvVehicleImageHeader.setBorderColor(getResources().getColor(R.color.colorBlack));
        }
        else if (status == 0){
            //txvHourAndSpeedHeader.setBackgroundResource(R.drawable.textview_background_speed_gray);
            imgvVehicleImageHeader.setBorderColor(getResources().getColor(R.color.colorBorderCarGray));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraintVehicleHeader:
                if (listener != null) {
                    listener.onClickVehicleHeader();
                }
                break;
        }
    }

    public void setOnClickVehicleHeaderListener(OnClickVehicleHeaderListener listener) {
        this.listener = listener;
    }

    public interface OnClickVehicleHeaderListener {
        void onClickVehicleHeader();
    }

}

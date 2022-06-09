package com.pnla.onroadplus.z_version2.fragments.notifications_v2_map;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.online.view.ActivityOnlineV2;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationV2OnRoad;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;
import com.pranavpandey.android.dynamic.utils.DynamicUnitUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentNotificationV2Map extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    public static final String TAG = FragmentNotificationV2Map.class.getSimpleName();

    private NotificationV2OnRoad notification;

    private GoogleMap mMap;
    private MapView mapView;
    private TextView txvVehicleNameItemNotification, txvNotificationNameItemNotification, txvDateNotificationItemNotification;
    private CircleImageView imgvItemCarNotification;
    private ConstraintLayout constraintNotificationInfo;
    private ImageView imgvBackButtonNotificationMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOnlineV2.isVisibleVehiclesList = true;
        Bundle bundle = getArguments();
        if (bundle != null) {
            notification = bundle.getParcelable(GeneralConstantsV2.NOTIFICATION);
        }
    }
/*
    private int currentApiVersion;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getActivity().getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }
        return inflater.inflate(R.layout.yourLayout, container, false);
    }

    */
private int currentApiVersion;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_v2_map, container, false);
        initViews(view);
        setFonts();

        txvVehicleNameItemNotification.setText(notification.getVehicleName());
        txvNotificationNameItemNotification.setText(notification.getNotification());
        txvDateNotificationItemNotification.setText(notification.getDateNotification());

        if (notification.getImageVehicle() != null) {
            Glide.with(getContext())
                    .load(notification.getImageVehicle())
                    .into(imgvItemCarNotification);
        } else {
            Glide.with(getContext())
                    .load(R.drawable.sedan)
                    .into(imgvItemCarNotification);
        }

        /*

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_FULLSCREEN);
            final View decorView = getActivity().getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }


*/

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.notificationsMap);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        BitmapDescriptor icon;
        MapsInitializer.initialize(getActivity());
        mapView.setVisibility(View.VISIBLE);
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mMap.getUiSettings();
        LatLng notificationPosition = new LatLng(notification.getLatitude(), notification.getLongitude());

        if (notification.getImageVehicle() != null) {
            //imagen por url
            new GetBitmapFromURLAsync(notification.getImageVehicle(), notification.getVehicleName(), notification.getLatitude(), notification.getLongitude()).execute();
        } else {
            //imagen en drawable
            icon = BitmapDescriptorFactory.fromResource(R.drawable.sedan);
            mMap.addMarker(new MarkerOptions().position(notificationPosition).title(notification.getVehicleName()).icon(icon));
            float zoomLevel = 17f;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(notificationPosition, zoomLevel));
            mMap.getUiSettings().setCompassEnabled(false);
        }
    }

    private void initViews(View view) {
        txvVehicleNameItemNotification = view.findViewById(R.id.txvVehicleNameItemNotification);
        txvNotificationNameItemNotification = view.findViewById(R.id.txvNotificationNameItemNotification);
        txvDateNotificationItemNotification = view.findViewById(R.id.txvDateNotificationItemNotification);
        imgvItemCarNotification = view.findViewById(R.id.imgvItemCarNotification);
        constraintNotificationInfo = view.findViewById(R.id.constraintNotificationInfo);
        imgvBackButtonNotificationMap = view.findViewById(R.id.imgvBackButtonNotificationMap);

        constraintNotificationInfo.setOnClickListener(this);
        imgvBackButtonNotificationMap.setOnClickListener(this);
    }

    private void setFonts() {
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        Typeface latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        txvVehicleNameItemNotification.setTypeface(latoBoldTypeface);
        txvNotificationNameItemNotification.setTypeface(latoRegularTypeface);
        txvDateNotificationItemNotification.setTypeface(latoRegularTypeface);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraintNotificationInfo:
                LatLng notificationPosition = new LatLng(notification.getLatitude(), notification.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(notificationPosition, GeneralConstantsV2.VEHICLE_SELECTED));
                break;
            case R.id.imgvBackButtonNotificationMap:
                returnToNotifications();
                break;
        }
    }

    private void returnToNotifications() {
        getActivity().getSupportFragmentManager().popBackStack();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this);
    }

    private class GetBitmapFromURLAsync extends AsyncTask<Void, Void, Bitmap> {

        private ProgressDialog progressDialog;

        private DialogTrackingLoader dialogTrackingLoader;
        private double latitude;
        private double longitude;
        private String vehicleImageUrl;
        private String vehicleName;
        private Bitmap bitmapMarker;

        public GetBitmapFromURLAsync(String vehicleImageUrl, String vehicleName, double latitude, double longitude) {
            this.vehicleImageUrl = vehicleImageUrl;
            this.vehicleName = vehicleName;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());

            progressDialog.setMessage("Cargando Mapa OnRoad");
            progressDialog.setCancelable(false);
            progressDialog.show();

           // dialogTrackingLoader = new DialogTrackingLoader();
           // dialogTrackingLoader.show(getActivity().getSupportFragmentManager(), DialogTrackingLoader.TAG);
        }

        @Override
        protected Bitmap doInBackground(Void... voids){//(Void... params) {
            bitmapMarker = getBitmapFromURL(vehicleImageUrl);
            return bitmapMarker;



        }

        @Override
        protected void onPostExecute(Bitmap bitmapDownload){//{(Bitmap bitmapDownload) {//
            progressDialog.hide();

            //dialogTrackingLoader.dismiss();
            //  bitmapMarker = bitmapDownload;
            Bitmap bitmap;
            BitmapDescriptor icon;

            if (bitmapMarker != null) {
                bitmap = Bitmap.createScaledBitmap(bitmapMarker, 160, 160, false);
                View marker = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.notifications_marker, null);
                icon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(marker, 160, 160, bitmap, vehicleName));
            } else {
               // icon = BitmapDescriptorFactory.fromResource(R.drawable.sedan);


                bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sedan);
                Bitmap resized = Bitmap.createScaledBitmap(bitmap, 160, 160, true);
                icon = BitmapDescriptorFactory.fromBitmap(resized);
            }


            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .anchor(0.5f, 0.5f)
                    .icon(icon));

            super.onPostExecute(bitmapDownload);

      /*  MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.position(new LatLng(latitude, longitude));
            markerOptions.title(vehicleName);
            markerOptions.infoWindowAnchor(2, 2);
            markerOptions.anchor(0.5f, 0.5f);
            markerOptions.icon(icon);
*/

            float zoomLevel = 17f;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoomLevel));
            mMap.getUiSettings().setCompassEnabled(false);


        }
        private Bitmap createBitmapFromView(@NonNull View view, int width, int height, Bitmap resource, String name) {

            CircleImageView circleImageView = view.findViewById(R.id.unit_marker_img1);
            TextView vehicleName = view.findViewById(R.id.unit_marker_title1);

            circleImageView.setImageBitmap(resource);
            vehicleName.setText(name);

            //  setImageBorderColor(vehicleSwitch, circleImageView);

            if (width > 0 && height > 0) {
                view.measure(View.MeasureSpec.makeMeasureSpec(DynamicUnitUtils.convertDpToPixels(width), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(DynamicUnitUtils.convertDpToPixels(height), View.MeasureSpec.EXACTLY));
            }
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

            Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                    view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Drawable background = view.getBackground();

            if (background != null) {
                background.draw(canvas);
            }
            view.draw(canvas);
            return bitmap;
        }

        public Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
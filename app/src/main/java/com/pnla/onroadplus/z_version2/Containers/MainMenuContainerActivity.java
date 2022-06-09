package com.pnla.onroadplus.z_version2.Containers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pnla.onroadplus.BuildConfig;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.Version;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionRequest;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionResponse;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionService;
import com.pnla.onroadplus.z_version2.MenuFragments.Contact.view.ContactViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.view.NotificationsViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Profile.view.ProfileViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;
import com.pnla.onroadplus.z_version2.TrackingMapFragment;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainMenuContainerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private Toolbar btnToolbar;
    private TextView txtTitleToolbar;
    private BottomNavigationView bottomNavigationView;
    Handler handler = new Handler();
    Runnable activityRefresh;
    public Timer timer;
    public TimerTask timerTask;
    public boolean chiringuito = false;
    public Version version;
    public String nVer;
    private List<String> versiones;
    private String name = "OnRoad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_container);
        initMenuMainContainer();
      //  dialog();
    }

    private void dialog(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        final String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);

        final VersionRequest request = new VersionRequest(name,token);
        Retrofit retrofit = RetrofitClientV2.getRetrofitInstance();
        VersionService service = retrofit.create(VersionService.class);
        Call<VersionResponse> call = service.getVersion(request);
        call.enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                if (response.body().getResponseCode()==105) {
                    versiones = new ArrayList<>();
                    version = response.body().getData();
                    if(version!=null){
                        String [] string1 = BuildConfig.VERSION_NAME.split("[.]");
                        String [] string2 = version.getDesc_version().split("[.]");
                        Integer[] number2 = new Integer[string2.length];

                        Integer[] numbers = new Integer[string2.length];


                        for (int i = 0; i < string2.length; i++) {
                            if (string1.length-1<i)
                                numbers[i] = 0;
                            else
                                numbers[i] = Integer.parseInt(string1[i]);
                        }


                        for (int i = 0; i < string2.length; i++) {
                            number2[i] = Integer.parseInt(string2[i]);
                        }

                        for (int i = 0; i < number2.length; i++) {
                            if (number2[i] > numbers[i]) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainMenuContainerActivity.this, R.style.AlertDialogCustom);
                                builder.setTitle("Hay una nueva versión disponible de OnRoad");
                                builder.setMessage("Tu versión: " + BuildConfig.VERSION_NAME + "\n\n" + "Nueva versión: " + version.getDesc_version());
                                builder.setCancelable(true);
                                builder.setPositiveButton("Descargar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.newlandapps.onroad"));
                                        intent.setPackage("com.android.vending");
                                        startActivity(intent);
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            } else {

                            }
                        }
                    }
                }
            }


            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {

            }
        });
    }

    private void initMenuMainContainer() {

        btnToolbar = findViewById(R.id.bnc_toolbar);
        txtTitleToolbar = findViewById(R.id.toolbar_title);
        bottomNavigationView = findViewById(R.id.bottom_nav_container);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        txtTitleToolbar.setText("Unidades");
        Bundle bndl = getIntent().getExtras();
        String nav = bndl.getString("nav");


        if (nav.equals("TRACKING")) {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
            bottomNavigationView.getMenu().getItem(1).setChecked(true);
            bottomNavigationView.getMenu().getItem(1).setEnabled(false);
            // showFragment(new TrackingMapFragment());

            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_menu_fragment, new TrackingMapFragment(), "Tracking");
            ft.commit();
        } else if (nav.equals("UNITS")) {
            UnitsViewImpl unitsView = new UnitsViewImpl();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_menu_fragment, unitsView, "NewFragment");
            ft.detach(unitsView);
            ft.attach(unitsView);
            ft.commit();
            dialog();

        } else {
            UnitsViewImpl unitsView = new UnitsViewImpl();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_menu_fragment, unitsView, "NewFragment");
            ft.commit();
        }


        toolbarSetup();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_units:
                //timer = new Timer();
                //timer.scheduleAtFixedRate(timerTask,0,10000);
                item.setChecked(true);
                item.setEnabled(false);
                bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                bottomNavigationView.getMenu().getItem(2).setEnabled(true);
                bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                bottomNavigationView.getMenu().getItem(4).setEnabled(true);
                UnitsViewImpl unitsView = new UnitsViewImpl();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_menu_fragment, unitsView, "NewFragment");
                ft.detach(unitsView);
                ft.attach(unitsView);
                ft.commit();
                txtTitleToolbar.setText("Unidades");
                break;
            case R.id.navigation_tracking:
                item.setChecked(true);
                item.setEnabled(false);
                bottomNavigationView.getMenu().getItem(0).setEnabled(true);
                bottomNavigationView.getMenu().getItem(2).setEnabled(true);
                bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                bottomNavigationView.getMenu().getItem(4).setEnabled(true);
                showFragment(new TrackingMapFragment());
                txtTitleToolbar.setText("Rutas");
                break;
            case R.id.navigation_notifications:
                item.setChecked(true);
                item.setEnabled(false);
                bottomNavigationView.getMenu().getItem(0).setEnabled(true);
                bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                bottomNavigationView.getMenu().getItem(4).setEnabled(true);
                showFragment(new NotificationsViewImpl());//esto remplaza todos los archivos de notificaciones   y mueve los requeridos a Menufragments -------> se cambio FragmentNotificationsV2
                txtTitleToolbar.setText("Notificaciones");
                break;
            case R.id.navigation_contact:
                item.setChecked(true);
                item.setEnabled(false);
                bottomNavigationView.getMenu().getItem(0).setEnabled(true);
                bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                bottomNavigationView.getMenu().getItem(2).setEnabled(true);
                bottomNavigationView.getMenu().getItem(4).setEnabled(true);
                showFragment(new ContactViewImpl());
                txtTitleToolbar.setText("Contacto");
                break;
            case R.id.navigation_profile:
                item.setChecked(true);
                item.setEnabled(false);
                bottomNavigationView.getMenu().getItem(0).setEnabled(true);
                bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                bottomNavigationView.getMenu().getItem(2).setEnabled(true);
                bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                showFragment(new ProfileViewImpl());
                txtTitleToolbar.setText("Perfil");
                break;
        }
        return false;
    }


    public void showFragment(Fragment fragment) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_menu_fragment, fragment, "NewFragment");
        ft.commit();
    }

    private void toolbarSetup() {
        btnToolbar.setTitle("");
        setSupportActionBar(btnToolbar);
    }

    private void enabledItemTrue(BottomNavigationView navigationView, int id){
        navigationView.getMenu().getItem(id).setEnabled(true);
    }

    private void enabledItemFalse(BottomNavigationView navigationView, int id){
        navigationView.getMenu().getItem(id).setEnabled(false);
    }


}

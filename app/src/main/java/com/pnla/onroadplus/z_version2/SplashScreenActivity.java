package com.pnla.onroadplus.z_version2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmConfig;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {
    /**ESTE ES EL SPLASH CORRECTO*/
    public static List<Unit> datapersistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initSplashScreenActivity();

    }
 
    private void initSplashScreenActivity() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, +WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Log.e("checkinguser","splash"+UserDataDB.getUserData().getEmployee_name());
        RealmConfig.initRealm(getApplicationContext());
                    Log.e("checkinguser","splash              "+UnitDB.getUnitList());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserDataDB.isEmpty()){
                    Log.e("checkinguser","go to login  ");
                    goToLoginContainer();
                }else {
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                    String status = preferences.getString(GeneralConstantsV2.CLOSE_SESSION_PREFERENCES, null);
                    Log.e("checkinguser","go to units menu "+"  st "+status+" "+UnitDB.getUnitListActive());
                    if(status.equals("YES"))
                    {
                        goToLoginContainer();
                    }else
                    {
                        goToMenu();
                    }

                }
            }
        }, 2500);
    }

    private void goToLoginContainer() {
        Bundle bndl = new Bundle();
        bndl.putBoolean("HelpStatus", false);
        Intent intent = new Intent(SplashScreenActivity.this, LoginContainerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bndl);
        startActivity(intent);
        finish();
    }

    private void goToMenu(){
        Bundle bundle = new Bundle();
        bundle.putString("nav","UNITS");
        Intent intent = new Intent(SplashScreenActivity.this,  menuViewImpl.class);// menuViewImpl.class);//MainMenuContainerActivity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

}
package com.pnla.onroadplus.z_version2;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initSplashScreenActivity();
    }

    private void initSplashScreenActivity() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, +WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userDataisEmpty()) {
                    goToMainContainer();
                } else {
                    goToMenu();
                }
            }
        }, 2500);
    }

    private void goToMainContainer() {

        Intent intent = new Intent(SplashScreen.this, LoginContainerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void goToMenu() {
        Intent intent = new Intent(SplashScreen.this, MainMenuContainerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private boolean userDataisEmpty() {
        return UserDataDB.isEmpty();
    }*/
}

package com.pnla.onroadplus.z_version2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.helpV2.ProfileHelpFragment;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class HelpContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_container);


    }

    public  void showFragment(){
        Bundle bundle = new Bundle();
        bundle.putBoolean(GeneralConstantsV2.INVOKED_LOGIN_SCREEN, GeneralConstantsV2.IS_FIRST_TIME);
        ProfileHelpFragment profileHelpFragment = new ProfileHelpFragment();
        profileHelpFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.container_help, profileHelpFragment).commit();
    }
}

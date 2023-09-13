package com.pnla.onroadplus.z_version2.MenuFragments.Profile.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
//import com.dynatrace.android.agent.Dynatrace;
import com.pnla.onroadplus.BuildConfig;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Contact.view.ContactViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Profile.presenter.ProfilePresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Profile.presenter.ProfilePresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.view.zonesFragment;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.SplashScreenActivity;
import com.pnla.onroadplus.z_version2.UserDataDB;
import com.pnla.onroadplus.z_version2.activities.HelpContainerActivity;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.PersistenceUtilities;

import java.io.File;


public class ProfileViewImpl extends Fragment implements ProfileView, CompoundButton.OnCheckedChangeListener, LinearLayout.OnClickListener {

   public static final String TAG = ProfileViewImpl.class.getSimpleName();
    private Switch switchNotifications;
    private ImageView imgUser;
    private TextView txtUsername;
    private TextView txtEmail;
    private TextView txtNotificationsButton;
    private TextView txtHelpButton;
    private TextView txtLogoutButton;
    private ProfilePresenter presenter;
    private TextView toolbarTitle;
    private TextView versionCode;
    private String vName = BuildConfig.VERSION_NAME;
    public static boolean toggleValue=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initProfileView(view);


        return view;
    }

    // UI Profile //

    private void initProfileView(View view) {
        ConstraintLayout contact = view.findViewById(R.id.ll_help_button_container1);
        ConstraintLayout llLogout = view.findViewById(R.id.ll_logout_button_container);
        ConstraintLayout llHelp = view.findViewById(R.id.ll_help_button_container);
        imgUser = view.findViewById(R.id.img_pi);
        txtUsername = view.findViewById(R.id.txt_pi_names);
        txtEmail = view.findViewById(R.id.txt_pi_email);
        txtNotificationsButton = view.findViewById(R.id.txvEnableNotifications);
        txtHelpButton = view.findViewById(R.id.txt_hb);
        txtLogoutButton = view.findViewById(R.id.txt_lb);
        switchNotifications = view.findViewById(R.id.switchNotifications);
        versionCode = view.findViewById(R.id.versionCode);

        toolbarTitle = view.findViewById(R.id.default_toolbar_title);
        toolbarTitle.setText("Perfil");
        versionCode.setText(vName);
        switchNotifications.setOnCheckedChangeListener(this);
        contact.setOnClickListener(this);
        llLogout.setOnClickListener(this);
        llHelp.setOnClickListener(this);

        presenter = new ProfilePresenterImpl(getContext());
        presenter.setView(this);
        presenter.initThemeSettings();
        presenter.initProfileSettings();
        presenter.getNotificationsConfiguration(getContext());


    }

    @Override
    public void setUserProfileData(String username, String email) {
        txtUsername.setText(username);
        txtEmail.setText(email);
    }

    @Override
    public void setFonts(Typeface robotoRegular, Typeface robotoBold) {
        txtUsername.setTypeface(robotoBold);
        txtEmail.setTypeface(robotoRegular);
        txtNotificationsButton.setTypeface(robotoRegular);
        txtHelpButton.setTypeface(robotoRegular);
        txtLogoutButton.setTypeface(robotoRegular);
        toolbarTitle.setTypeface(robotoBold);

    }

    // Notifications Switch //


    @Override
    public void activeNotifications() {
        txtNotificationsButton.setText(getString(R.string.textDisableNotifications));
        switchNotifications.setChecked(true);
        toggleValue=true;
    }

    @Override
    public void disabledNotifications() {
        txtNotificationsButton.setText(getString(R.string.textEnableNotifications));
        switchNotifications.setChecked(false);
        toggleValue=false;
    }

    @Override
    public void setUserImageProfile(String urlImage) {
        String UrL = urlImage;
        if (UrL.contains(",")){
            String[] separated = UrL.split(",");
            String uno = separated[0];
            String dos = separated[1];
            final String encodedString = dos;
            final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",") + 1);
            final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
            Glide.with(getContext()).load(decodedBytes).error(R.drawable.ic_profile_default).into(imgUser);
        } else {
            Glide.with(getContext()).load(urlImage).error(R.drawable.ic_profile_default).into(imgUser);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            presenter.activeNotifications();
            presenter.saveUserNotificationState(true, getContext());
        } else {
            presenter.disabledNotifications();
            presenter.saveUserNotificationState(false, getContext());
        }
    }

    // Help & Logout //

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_help_button_container1:

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ContactViewImpl contacto = new ContactViewImpl();
                transaction.replace(R.id.conteinerMainFragments, contacto, ContactViewImpl.TAG).commit();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                break;
            case R.id.ll_help_button_container:
                presenter.showHelpScreen();

                break;
            case R.id.ll_logout_button_container:
                presenter.logout();
                zonesFragment.fullZones.clear();
                zonesFragment.pointInZone.clear();
                zonesFragment.geocercas.clear();
                PersistenceUtilities.cleanAllValues(getContext());
                SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();
                UnitDB.deleteDB();
                GroupDB.deleteDB();
                UnitTrackingAdapter.integerList.clear();
                //Dynatrace.endVisit();
                //UserDataDB.deleteDB();
                break;
        }
    }

    @Override
    public void goToHelpScreen() {
        Intent intent = new Intent(getContext(), HelpContainerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
    @Override
    public void goToLoginScreen() {
     //   UnitDB.deleteDB();        //pending effects to check
        SplashScreenActivity.clearStaticValues();
        deleteCache(getContext());
        Intent intent = new Intent(getContext(), SplashScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.setFlags(0);
        Bundle bndl = new Bundle();
        bndl.putBoolean("HelpStatus", false);

        startActivity(intent);
    }
}

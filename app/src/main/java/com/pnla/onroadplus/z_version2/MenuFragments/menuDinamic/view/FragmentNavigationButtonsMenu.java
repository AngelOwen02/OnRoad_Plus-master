package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dynatrace.android.agent.DTXAction;
import com.dynatrace.android.agent.Dynatrace;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Contact.view.ContactViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.view.NotificationsViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Profile.view.ProfileViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.view.zonesFragment;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.presenter.menuPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.presenter.menuPresenterImpl;
import com.pnla.onroadplus.z_version2.TrackingMapFragment;
import com.pnla.onroadplus.z_version2.fragments.contactV2.view.FragmentContactV2;

import java.util.ArrayList;
import java.util.List;

public class FragmentNavigationButtonsMenu extends Fragment implements View.OnClickListener,menuView {
    public static final String TAG = FragmentNavigationButtonsMenu.class.getSimpleName();

    private ConstraintLayout unidades, rastreo, notificaciones, zonas, contacto, perfil; //butonprofile,inprocesmensajero,completemensajero,inprogressupervisor,completedsupervisor;//u,t,n,z,c,p
    private float f1, f2, f3, f4, f5, f6 = 0f;
    private boolean u, v, w, x, y, z=false ;
    private boolean a, b, c, d, e, f;
    private menuPresenter presenter;
    private ImageView menu1, menu2, menu3, menu4, menu5, menu6;
    private Guideline guideline1, guideline2, guideline3, guideline4, guideline5, guideline6;
    private Toolbar btnToolbar;
    private TextView txtTitleToolbar;
    private List<String> myemenuItems=new ArrayList<>();
    private Handler handler = new Handler();
    public Runnable runnable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_bar, container, false);
        initView(view);
        presenter.itemsMenu();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                presenter.itemsMenu();
                handler.postDelayed(this,20000);
            }
        },20000);
        return view;
      }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,10000);
    }
        private void initView(View view) {
       /*     f1= .166f;
            f2=.333f;
            f3=.5f;
            f4=.666f;
            f5=.833f;
            f6=1;
            menuViewImpl.a=f1;
            menuViewImpl.b=f2;
            menuViewImpl.c=f3;
            menuViewImpl.d=f4;
            menuViewImpl.e=f5;
            menuViewImpl.f=f6;*/

         //   btnToolbar = view.findViewById(R.id.bnc_toolbar);
          //  txtTitleToolbar =view.findViewById(R.id.toolbar_title);

            Bundle bndl = getActivity().getIntent().getExtras();
            String nav = bndl.getString("nav");
         //   toolbarSetup();
        /**  Constrains    */

            unidades=view.findViewById(R.id.constraintUnits);
            rastreo=view.findViewById(R.id.constraintTracking);
            notificaciones=view.findViewById(R.id.constraintNotification);
            zonas=view.findViewById(R.id.constrainZones);
            contacto=view.findViewById(R.id.constrainContact);
            perfil=view.findViewById(R.id.constrainProfile);


            menu1=view.findViewById(R.id.camion1);
            menu2=view.findViewById(R.id.rastreoB);
            menu3=view.findViewById(R.id.notificacionesB);
            menu4=view.findViewById(R.id.zonesimage);
            menu5=view.findViewById(R.id.mensajes);;
            menu6=view.findViewById(R.id.perfilicon);


            guideline1 = view.findViewById(R.id.guideline11g);
            guideline2 = view.findViewById(R.id.guideline12g);
            guideline3 = view.findViewById(R.id.guideline13g);
            guideline4 = view.findViewById(R.id.guideline14g);
            guideline5 = view.findViewById(R.id.guideline15g);
            guideline6 = view.findViewById(R.id.guideline16g);


            ConstraintLayout.LayoutParams params1 = (ConstraintLayout.LayoutParams) guideline1.getLayoutParams();
            ConstraintLayout.LayoutParams params2 = (ConstraintLayout.LayoutParams) guideline2.getLayoutParams();
            ConstraintLayout.LayoutParams params3 = (ConstraintLayout.LayoutParams) guideline3.getLayoutParams();
            ConstraintLayout.LayoutParams params4 = (ConstraintLayout.LayoutParams) guideline4.getLayoutParams();
            ConstraintLayout.LayoutParams params5 = (ConstraintLayout.LayoutParams) guideline5.getLayoutParams();
            ConstraintLayout.LayoutParams params6 = (ConstraintLayout.LayoutParams) guideline6.getLayoutParams();

            params1.guidePercent =menuViewImpl.a; // 45% // range: 0 <-> 1
            params2.guidePercent =menuViewImpl.b; // 45% // range: 0 <-> 1
            params3.guidePercent =menuViewImpl.c; // 45% // range: 0 <-> 1
            params4.guidePercent =menuViewImpl.d; // 45% // range: 0 <-> 1
            params5.guidePercent =menuViewImpl.e; // 45% // range: 0 <-> 1
            params6.guidePercent =menuViewImpl.f; // 45% // range: 0 <-> 1


            guideline1.setLayoutParams(params1);
            guideline2.setLayoutParams(params2);
            guideline3.setLayoutParams(params3);
            guideline4.setLayoutParams(params4);
            guideline5.setLayoutParams(params5);
            guideline6.setLayoutParams(params6);

            menu1.setOnClickListener(this);
            menu2.setOnClickListener(this);
            menu3.setOnClickListener(this);
            menu4.setOnClickListener(this);
            menu5.setOnClickListener(this);
            menu6.setOnClickListener(this);

            if (nav.equals("TRACKING")) {
                TrackingFragment();

            } else if (nav.equals("UNITS")) {
                UnitsFragment();
                /**aqui se deberia iniciar el fragments de unidades y de contenr una version menor a la de la alerta desplegar el mensaje para actualizar la version*/
                //  UnitsViewImpl unitsView = new UnitsViewImpl();
                // FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //ft.replace(R.id.main_menu_fragment, unitsView, "NewFragment");
                //ft.detach(unitsView);
                //ft.attach(unitsView);
                //ft.commit();
                //dialog();

            } else if (nav.equals("ZONES")) {
                ZonesFragment();
            }
            else {
                UnitsFragment();
                /** De cualquier forma el fragment que debe mostraRCE AL INICIO ES EL DE UNIDADES POR LO QUE DEBE ESTAR SETEADO AQUI */
            }

            presenter= new menuPresenterImpl(this,getContext());


    }
        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.camion1:
                    DTXAction menuButton_Units= Dynatrace.enterAction("menuButton_Units");
                    UnitsFragment();
                    menuButton_Units.leaveAction();
                    break;
                case R.id.rastreoB:
                    DTXAction menuButton_Tracking= Dynatrace.enterAction("menuButton_Tracking");
                    TrackingFragment();
                    menuButton_Tracking.leaveAction();
                    break;
                case R.id.notificacionesB:
                    DTXAction menuButton_Notifications= Dynatrace.enterAction("menuButton_Notifications");
                    NotificationsFragment();
                    menuButton_Notifications.leaveAction();
                    break;
                case R.id. zonesimage:
                    DTXAction menuButton_Zones= Dynatrace.enterAction("menuButton_Zones");
                    ZonesFragment();
                    menuButton_Zones.leaveAction();
                    break;
                case R.id.mensajes:
                    DTXAction menuButton_Contact= Dynatrace.enterAction("menuButton_Contact");
                    MessageFragment();
                    menuButton_Contact.leaveAction();
                    break;
                case R.id.perfilicon:
                    DTXAction menuButton_Profile= Dynatrace.enterAction("menuButton_Profile");
                    profileFragment();
                    menuButton_Profile.leaveAction();
                    break;
            }
        }

    private void UnitsFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        UnitsViewImpl unidades = new UnitsViewImpl();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, unidades, UnitsViewImpl.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        /**aqui se deben iluminar y desabilitar los botones para los menus para que puedan ser oprimidos solo en una ocacion*///iluminate3();
        illuminateUnits();
    }

    private void TrackingFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        TrackingMapFragment rastreo = new TrackingMapFragment();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, rastreo, TrackingMapFragment.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        /**aqui se deben iluminar y desabilitar los botones para los menus para que puedan ser oprimidos solo en una ocacion*///iluminate3();
        illuminateTracking();
    }


    private void NotificationsFragment() {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        NotificationsViewImpl notificaciones = new NotificationsViewImpl();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, notificaciones, NotificationsViewImpl.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        /**aqui se deben iluminar y desabilitar los botones para los menus para que puedan ser oprimidos solo en una ocacion*///iluminate3();
        illuminateNotifications();
    }



    private void ZonesFragment() {
       // Toast.makeText(getContext(), "aqui va el fragment de zonas", Toast.LENGTH_SHORT).show();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        zonesFragment zones = new zonesFragment();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, zones, zonesFragment.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        /**aqui se deben iluminar y desabilitar los botones para los menus para que puedan ser oprimidos solo en una ocacion*///iluminate3();
        illuminateZones();
    }



    private void MessageFragment() {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ContactViewImpl contacto = new ContactViewImpl();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, contacto, FragmentContactV2.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        /**aqui se deben iluminar y desabilitar los botones para los menus para que puedan ser oprimidos solo en una ocacion*///iluminate3();
        illuminateContact();
    }


    private void profileFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ProfileViewImpl profile = new ProfileViewImpl();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, profile, ProfileViewImpl.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        /**aqui se deben iluminar y desabilitar los botones para los menus para que puedan ser oprimidos solo en una ocacion*///iluminate3();
        iLLuiminateProfile();
    }


    private void illuminateUnits() {
        menu1.setImageResource(R.drawable.ic_units_on);
        menu2.setImageResource(R.drawable.ic_tracking_off);
        menu3.setImageResource(R.drawable.ic_notificacionesoff);
        menu4.setImageResource(R.drawable.ic_zonasoff);
        menu5.setImageResource(R.drawable.ic_contact_off);
        menu6.setImageResource(R.drawable.ic_profile_off);
        a=true;
        b=false;
        c=false;
        d=false;
        e=false;
        f=false;

        checkEnablebuttons();


    }
    private void illuminateTracking() {
        menu1.setImageResource(R.drawable.ic_units_off);
        menu2.setImageResource(R.drawable.ic_tracking_on);
        menu3.setImageResource(R.drawable.ic_notificacionesoff);
        menu4.setImageResource(R.drawable.ic_zonasoff);
        menu5.setImageResource(R.drawable.ic_contact_off);
        menu6.setImageResource(R.drawable.ic_profile_off);
        a=false;
        b=true;
        c=false;
        d=false;
        e=false;
        f=false;
        checkEnablebuttons();

    }
    private void illuminateNotifications() {
        menu1.setImageResource(R.drawable.ic_units_off);
        menu2.setImageResource(R.drawable.ic_tracking_off);
        menu3.setImageResource(R.drawable.ic_icono_notificaciones__1_);
        menu4.setImageResource(R.drawable.ic_zonasoff);
        menu5.setImageResource(R.drawable.ic_contact_off);
        menu6.setImageResource(R.drawable.ic_profile_off);
        a=false;
        b=false;
        c=true;
        d=false;
        e=false;
        f=false;
        checkEnablebuttons();

    }
    private void illuminateZones() {
        menu1.setImageResource(R.drawable.ic_units_off);
        menu2.setImageResource(R.drawable.ic_tracking_off);
        menu3.setImageResource(R.drawable.ic_notificacionesoff);
        menu4.setImageResource(R.drawable.ic_icono_zonas__1_);
        menu5.setImageResource(R.drawable.ic_contact_off);
        menu6.setImageResource(R.drawable.ic_profile_off);
        a=false;
        b=false;
        c=false;
        d=true;
        e=false;
        f=false;
        checkEnablebuttons();

    }

    private void illuminateContact() {
        menu1.setImageResource(R.drawable.ic_units_off);
        menu2.setImageResource(R.drawable.ic_tracking_off);
        menu3.setImageResource(R.drawable.ic_notificacionesoff);
        menu4.setImageResource(R.drawable.ic_zonasoff);
        menu5.setImageResource(R.drawable.ic_contact_on);
        menu6.setImageResource(R.drawable.ic_profile_off);
        a=false;
        b=false;
        c=false;
        d=false;
        e=true;
        f=false;
        checkEnablebuttons();

    }

    private void iLLuiminateProfile() {
        menu1.setImageResource(R.drawable.ic_units_off);
        menu2.setImageResource(R.drawable.ic_tracking_off);
        menu3.setImageResource(R.drawable.ic_notificacionesoff);
        menu4.setImageResource(R.drawable.ic_zonasoff);
        menu5.setImageResource(R.drawable.ic_contact_off);
        menu6.setImageResource(R.drawable.ic_profile_on);
        a=false;
        b=false;
        c=false;
        d=false;
        e=false;
        f=true;
        checkEnablebuttons();

    }

    private void checkEnablebuttons() {

        if(a==true&&b==false&&c==false&&d==false&&e==false&&f==false)
        {
            menu1.setEnabled(false);
            menu2.setEnabled(true);
            menu3.setEnabled(true);
            menu4.setEnabled(true);
            menu5.setEnabled(true);
            menu6.setEnabled(true);
        }else if(a==false&&b==true&&c==false&&d==false&&e==false&&f==false)
        {
            menu1.setEnabled(true);
            menu2.setEnabled(false);
            menu3.setEnabled(true);
            menu4.setEnabled(true);
            menu5.setEnabled(true);
            menu6.setEnabled(true);
        }else if(a==false&&b==false&&c==true&&d==false&&e==false&&f==false)
        {
            menu1.setEnabled(true);
            menu2.setEnabled(true);
            menu3.setEnabled(false);
            menu4.setEnabled(true);
            menu5.setEnabled(true);
            menu6.setEnabled(true);
        }else if(a==false&&b==false&&c==false&&d==true&&e==false&&f==false)
        {
            menu1.setEnabled(true);
            menu2.setEnabled(true);
            menu3.setEnabled(true);
            menu4.setEnabled(false);
            menu5.setEnabled(true);
            menu6.setEnabled(true);
        }else if(a==false&&b==false&&c==false&&d==false&&e==true&&f==false)
        {
            menu1.setEnabled(true);
            menu2.setEnabled(true);
            menu3.setEnabled(true);
            menu4.setEnabled(true);
            menu5.setEnabled(false);
            menu6.setEnabled(true);
        }else if(a==false&&b==false&&c==false&&d==false&&e==false&&f==true)
        {
            menu1.setEnabled(true);
            menu2.setEnabled(true);
            menu3.setEnabled(true);
            menu4.setEnabled(true);
            menu5.setEnabled(true);
            menu6.setEnabled(false);
        }

    }

    private void toolbarSetup() {
        btnToolbar.setTitle("");/** aqui deberiamos setear la informacion para el action bar con el titulo del fragment q   ue permanezca vizualizado*/
        //setSupportActionBar(btnToolbar);
    }
    @Override
        public void showError (String error){

        }

        @Override
        public void closeAppSessionExpired () {

        }

        @Override
        public void listItems (List < String > items) {
            this.myemenuItems=items;
            Log.e("datalistmenus1",""+myemenuItems+ '\n');

            if( myemenuItems.get(0).contains("false"))
            {
                u=false;
            }else
            {
                u=true;
            }
            if( myemenuItems.get(1).contains("false"))
            {
                v=false;
            }else
            {
                v=true;
            }
            if( myemenuItems.get(2).contains("false"))
            {
                w=false;
            }else
            {
                w=true;
            }
            if( myemenuItems.get(3).contains("false"))
            {
                x=false;
            }else
            {
                x=true;
            }
            if( myemenuItems.get(4).contains("false"))
            {
                y=false;
            }else
            {
                y=true;
            }
            if( myemenuItems.get(5).contains("false"))
            {
                z=false;
            }else
            {
                z=true;
            }
            Log.e("datalistmenus2","u="+u+" v="+v+" w="+w+" x="+x+ " y="+ y+" z="+z+'\n');
            menuConstrains();
        }


        public void menuConstrains ()
        {
            /***///if statements with this annotation ar checked
            if (u == false && v == false && w == false && x == false && y == false && z == false) {/***///.5f
                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 0f;//1
                f4 = 0f;//1
                f5 = 0f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == false && x == false && y == false && z == true) {/***/

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 0f;//1
                f4 = 0f;//1
                f5 = 0f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == false && x == false && y == true && z == false) {/***/

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 0f;//1
                f4 = 0f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == false && x == true && y == false && z == false) {/***/

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 0f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == true && x == false && y == false && z == false) {/***/

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 1f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == true && w == false && x == false && y == false && z == false) {/***/

                f1 = 0f;//1
                f2 = 1f;//1
                f3 = 1f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == true && v == false && w == false && x == false && y == false && z == false) {/***/

                f1 = 1f;//1
                f2 = 1f;//1
                f3 = 1f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == true && v == true && w == false && x == false && y == false && z == false) {/***/

                f1 = .5f;//1
                f2 = 1f;//1
                f3 = 1f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == true && v == false && w == true && x == false && y == false && z == false) {/***/

                f1 = .5f;//1
                f2 = .5f;//1
                f3 = 1f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == true && w == true && x == false && y == false && z == false) {/***/

                f1 = 0f;//1
                f2 = .5f;//1
                f3 = 1f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == true && v == false && w == false && x == true && y == false && z == false) {/***/ //TRUE	FALSE	FALSE	TRUE	FALSE	FALSE

                f1 = .5f;//1
                f2 = .5f;//1
                f3 = .5f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == true && w == false && x == true && y == false && z == false) {/***/  //FALSE	TRUE	FALSE	TRUE	FALSE	FALSE

                f1 = 0f;//1
                f2 = .5f;//1
                f3 = .5f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == true && x == true && y == false && z == false) {/***/  //FALSE	FALSE	TRUE	TRUE	FALSE	FALSE

                f1 = 0f;//1
                f2 = .0f;//1
                f3 = .5f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == true && x == true && y == false && z == false) { /***/ //FALSE	FALSE	TRUE	TRUE	FALSE	FALSE

                f1 = 0f;//1
                f2 = .0f;//1
                f3 = .5f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == true && v == false && w == false && x == false && y == true && z == false) { /***/ //TRUE	FALSE	FALSE	FALSE	TRUE	FALSE

                f1 = .5f;//1
                f2 = .5f;//1
                f3 = .5f;//1
                f4 = .5f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == true && w == false && x == false && y == true && z == false) { /***/ //FALSE	TRUE	FALSE	FALSE	TRUE	FALSE

                f1 = 0f;//1
                f2 = .5f;//1
                f3 = .5f;//1
                f4 = .5f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == true && x == false && y == true && z == false) {/***/  //FALSE	FALSE	TRUE	FALSE	TRUE	FALSE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = .5f;//1
                f4 = .5f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == false && x == true && y == true && z == false) { /***/ //FALSE	FALSE	FALSE	TRUE	TRUE	FALSE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 0f;//1
                f4 = .5f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == false && x == true && y == false && z == true) {  /***///TRUE	FALSE	FALSE	FALSE	FALSE	TRUE

                f1 =  0f;//1
                f2 =  0f;//1
                f3 =  0f;//1
                f4 = .5f;//1
                f5 = .5f;
                f6 = 1f;//profile visible

            } else if (u == false && v == true && w == false && x == false && y == false && z == true) {/***///FALSE	TRUE	FALSE	FALSE	FALSE	TRUE

                f1 = 0f;//1
                f2 = .5f;//1
                f3 = .5f;//1
                f4 = .5f;//1
                f5 = .5f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == true && x == false && y == false && z == true) { /***/ //FALSE	FALSE	TRUE	FALSE	FALSE	TRUE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = .5f;//1
                f4 = .5f;//1
                f5 = .5f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == false && x == false && y == true && z == true) { /***/ //FALSE	FALSE	FALSE	FALSE	TRUE	TRUE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 0f;//1
                f4 = 0f;//1
                f5 = .5f;
                f6 = 1f;//profile visible

            } else if (u == false && v == false && w == false && x == true && y == false && z == true) {/***/  //FALSE	FALSE	FALSE	TRUE	FALSE	TRUE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 0f;//1
                f4 = .5f;//1
                f5 = .5f;
                f6 = 1f;//profile visible

            }/***/
            else if (u == true && v == true && w == false && x == false && y == true && z == true) { /***/ //TRUE	TRUE	FALSE	FALSE	TRUE	TRUE

                f1 = .25f;//1
                f2 = .5f;//1
                f3 = .5f;//1
                f4 = .5f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***/
            else if (u == true && v == true && w == false && x == true && y == false && z == true) {  /***///TRUE	TRUE	FALSE	TRUE	FALSE	TRUE

                f1 = .25f;//1
                f2 = .5f;//1
                f3 = .5f;//1
                f4 = .75f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***/
            else if (u == true && v == true && w == false && x == true && y == true && z == false) {/***/  //TRUE	TRUE	FALSE	TRUE	TRUE	FALSE

                f1 = .25f;//1
                f2 = .5f;//1
                f3 = .5f;//1
                f4 = .75f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***/
            else if (u == true && v == true && w == true && x == false && y == false && z == true) {/***///TRUE	TRUE	TRUE	FALSE	FALSE	TRUE

                f1 = .25f;//1
                f2 = .5f;//1
                f3 = .75f;//1
                f4 = .75f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***/
            else if (u == true && v == true && w == true && x == false && y == true && z == false) {/***///TRUE	TRUE	TRUE	FALSE	TRUE	FALSE

                f1 = .25f;//1
                f2 = .5f;//1
                f3 = .75f;//1
                f4 = .75f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***/
            else if (u == true && v == true && w == true && x == true && y == false && z == false) {/***/ //TRUE	TRUE	TRUE	TRUE	FALSE	FALSE

                f1 = .25f;//1
                f2 = .5f;//1
                f3 = .75f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == true && v == false && w == true && x == false && y == true && z == true) {/***///TRUE	FALSE	TRUE	FALSE	TRUE	TRUE

                f1 = .25f;//1
                f2 = .25f;//1
                f3 = .5f;//1
                f4 = .5f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == false && v == true && w == true && x == false && y == true && z == true) {/***///FALSE	TRUE	TRUE	FALSE	TRUE	TRUE

                f1 = 0f;//1
                f2 = .25f;//1
                f3 = .5f;//1
                f4 = .5f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == true && v == false && w == true && x == true && y == false && z == true) {/***///TRUE	FALSE	TRUE	TRUE	FALSE	TRUE

                f1 = .25f;//1
                f2 = .25f;//1
                f3 = .5f;//1
                f4 = .75f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == false && v == true && w == true && x == true && y == false && z == true) {/***///FALSE	TRUE	TRUE	TRUE	FALSE	TRUE


                f1 = 0f;//1
                f2 = .25f;//1
                f3 = .5f;//1
                f4 = .75f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == true && v == false && w == true && x == true && y == true && z == false) {/***///TRUE	FALSE	TRUE	TRUE	TRUE	FALSE


                f1 = .25f;//1
                f2 = .25f;//1
                f3 = .5f;//1
                f4 = .75f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == false && v == true && w == true && x == true && y == true && z == false) {/***///FALSE	TRUE	TRUE	TRUE	TRUE	FALSE

                f1 = 0f;//1
                f2 = .25f;//1
                f3 = .5f;//1
                f4 = .75f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == true && v == false && w == false && x == true && y == true && z == true) {/***///TRUE	FALSE	FALSE	TRUE	TRUE	TRUE

                f1 = .25f;//1
                f2 = .25f;//1
                f3 = .255f;//1
                f4 = .5f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == false && v == false && w == true && x == true && y == true && z == true) {/***///FALSE	FALSE	TRUE	TRUE	TRUE	TRUE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = .25f;//1
                f4 = .5f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***//***/
            else if (u == false && v == true && w == false && x == true && y == true && z == true) {/***///FALSE	TRUE	FALSE	TRUE	TRUE	TRUE

                f1 = 0f;//1
                f2 = .25f;//1
                f3 = .25f;//1
                f4 = .5f;//1
                f5 = .75f;
                f6 = 1f;//profile visible

            }/***//***//***/
            else if (u == true && v == true && w == true && x == false && y == true && z == true) {/***///TRUE	TRUE	TRUE	FALSE	TRUE	TRUE

                f1 = .2f;//1
                f2 = .4f;//1
                f3 = .6f;//1
                f4 = .6f;//1
                f5 = .8f;
                f6 = 1f;//profile visible

            }/***//***//***/
            else if (u == true && v == true && w == true && x == true && y == false && z == true) {/***///TRUE	TRUE	TRUE	TRUE	FALSE	TRUE

                f1 = .2f;//1
                f2 = .4f;//1
                f3 = .6f;//1
                f4 = .8f;//1
                f5 = .8f;
                f6 = 1f;//profile visible

            }/***//***//***/
            else if (u == true && v == false && w == true && x == true && y == true && z == true) {/***///TRUE	FALSE	TRUE	TRUE	TRUE	TRUE

                f1 = .2f;//1
                f2 = .2f;//1
                f3 = .4f;//1
                f4 = .6f;//1
                f5 = .8f;
                f6 = 1f;//profile visible

            }/***//***//***/
            else if (u == false && v == true && w == true && x == true && y == true && z == true) {/***///FALSE	TRUE	TRUE	TRUE	TRUE	TRUE

                f1 = 0;//1
                f2 = .2f;//1
                f3 = .4f;//1
                f4 = .6f;//1
                f5 = .8f;
                f6 = 1f;//profile visible

            }/***//***//***/
            else if (u == true && v == true && w == false && x == true && y == true && z == true) {/***///TRUE	TRUE	FALSE	TRUE	TRUE	TRUE

                f1 = .2f;//1
                f2 = .4f;//1
                f3 = .4f;//1
                f4 = .6f;//1
                f5 = .8f;
                f6 = 1f;//profile visible

            }/***//***//***/
            else if (u == true && v == true && w == true && x == true && y == true && z == false) {/***///TRUE	TRUE	TRUE	TRUE	TRUE	FALSE

                f1 = .2f;//1
                f2 = .4f;//1
                f3 = .6f;//1
                f4 = .8f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == true && w == false && x == false && y == false && z == true) {/***///TRUE	TRUE	FALSE	FALSE	FALSE	TRUE

                f1 = .333f;//1
                f2 = .666f;//1
                f3 = .666f;//1
                f4 = .666f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == false && w == true && x == false && y == false && z == true) {/***///TRUE	FALSE	TRUE	FALSE	FALSE	TRUE

                f1 = .333f;//1
                f2 = .333f;//1
                f3 = .666f;//1
                f4 = .666f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == true && w == true && x == false && y == false && z == true) {/***///FALSE	TRUE	TRUE	FALSE	FALSE	TRUE

                f1 = 0f;//1
                f2 = .333f;//1
                f3 = .666f;//1
                f4 = .666f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == false && w == false && x == false && y == true && z == true) {/***///TRUE	FALSE	FALSE	FALSE	TRUE	TRUE

                f1 = .333f;//1
                f2 = .333f;//1
                f3 = .333f;//1
                f4 = .333f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == true && w == false && x == false && y == true && z == true) {/***///FALSE	TRUE	FALSE	FALSE	TRUE	TRUE

                f1 = 0f;//1
                f2 = .333f;//1
                f3 = .333f;//1
                f4 = .333f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == false && w == true && x == false && y == true && z == true) {/***///FALSE	FALSE	TRUE	FALSE	TRUE	TRUE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = .333f;//1
                f4 = .333f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == false && w == false && x == true && y == false && z == true) {/***///TRUE	FALSE	FALSE	TRUE	FALSE	TRUE

                f1 = .333f;//1
                f2 = .333f;//1
                f3 = .333f;//1
                f4 = .666f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == true && w == false && x == true && y == false && z == true) {/***///FALSE	TRUE	FALSE	TRUE	FALSE	TRUE

                f1 = 0f;//1
                f2 = .333f;//1
                f3 = .333f;//1
                f4 = .666f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == false && w == true && x == true && y == false && z == true) {/***///FALSE	FALSE	TRUE	TRUE	FALSE	TRUE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = .333f;//1
                f4 = .666f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == false && w == false && x == true && y == true && z == true) {/***///FALSE	FALSE	FALSE	TRUE	TRUE	TRUE

                f1 = 0f;//1
                f2 = 0f;//1
                f3 = 0f;//1
                f4 = .333f;//1
                f5 = .666f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == true && w == true && x == false && y == false && z == false) {/***///TRUE	TRUE	TRUE	FALSE	FALSE	FALSE

                f1 = .333f;//1
                f2 = .666f;//1
                f3 = 1f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == false && w == true && x == false && y == true && z == false) {/***///TRUE	FALSE	TRUE	FALSE	TRUE	FALSE

                f1 = .333f;//1
                f2 = .333f;//1
                f3 = .666f;//1
                f4 = .666f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == true && w == false && x == false && y == true && z == false) {/***///TRUE	TRUE	FALSE	FALSE	TRUE	FALSE

                f1 = .333f;//1
                f2 = .666f;//1
                f3 = .666f;//1
                f4 = .666f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == true && w == true && x == false && y == true && z == false) {/***///FALSE	TRUE	TRUE	FALSE	TRUE	FALSE

                f1 = 0f;//1
                f2 = .333f;//1
                f3 = .666f;//1
                f4 = .666f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == true && w == false && x == true && y == false && z == false) {/***///TRUE	TRUE	FALSE	TRUE	FALSE	FALSE

                f1 = .333f;//1
                f2 = .666f;//1
                f3 = .666f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == false && w == true && x == true && y == false && z == false) {/***///TRUE	FALSE	TRUE	TRUE	FALSE	FALSE

                f1 = .333f;//1
                f2 = .333f;//1
                f3 = .666f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == true && w == true && x == true && y == false && z == false) {/***///FALSE	TRUE	TRUE	TRUE	FALSE	FALSE

                f1 = 0;//1
                f2 = .333f;//1
                f3 = .666f;//1
                f4 = 1f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == true && v == false && w == false && x == true && y == true && z == false) {/***///TRUE	FALSE	FALSE	TRUE	TRUE	FALSE

                f1 = .333f;//1
                f2 = .333f;//1
                f3 = .333f;//1
                f4 = .666f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == false && w == true && x == true && y == true && z == false) {/***///FALSE	FALSE	TRUE	TRUE	TRUE	FALSE

                f1 = 0;//1
                f2 = 0;//1
                f3 = .333f;//1
                f4 = .666f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/***//***//***//***/
            else if (u == false && v == true && w == false && x == true && y == true && z == false) {//FALSE	TRUE	FALSE	TRUE	TRUE	FALSE

                f1 = 0;//1
                f2 = .333f;//1
                f3 = .333f;//1
                f4 = .666f;//1
                f5 = 1f;
                f6 = 1f;//profile visible

            }/**********************/
            else if (u == true && v == true && w == true && x == true && y == true && z == true) {//TRUE	TRUE	TRUE	TRUE	TRUE	TRUE

                f1 = .166f;//1
                f2 = .333f;//1
                f3 = .5f;//1
                f4 = .666f;//1
                f5 = .833f;
                f6 = 1f;//profile visible

            }

            ConstraintLayout.LayoutParams params1 = (ConstraintLayout.LayoutParams) guideline1.getLayoutParams();
            ConstraintLayout.LayoutParams params2 = (ConstraintLayout.LayoutParams) guideline2.getLayoutParams();
            ConstraintLayout.LayoutParams params3 = (ConstraintLayout.LayoutParams) guideline3.getLayoutParams();
            ConstraintLayout.LayoutParams params4 = (ConstraintLayout.LayoutParams) guideline4.getLayoutParams();
            ConstraintLayout.LayoutParams params5 = (ConstraintLayout.LayoutParams) guideline5.getLayoutParams();
            ConstraintLayout.LayoutParams params6 = (ConstraintLayout.LayoutParams) guideline6.getLayoutParams();

            params1.guidePercent =f1; // 45% // range: 0 <-> 1
            params2.guidePercent =f2; // 45% // range: 0 <-> 1
            params3.guidePercent =f3; // 45% // range: 0 <-> 1
            params4.guidePercent =f4; // 45% // range: 0 <-> 1
            params5.guidePercent =f5; // 45% // range: 0 <-> 1
            params6.guidePercent =f6; // 45% // range: 0 <-> 1


            guideline1.setLayoutParams(params1);
            guideline2.setLayoutParams(params2);
            guideline3.setLayoutParams(params3);
            guideline4.setLayoutParams(params4);
            guideline5.setLayoutParams(params5);
            guideline6.setLayoutParams(params6);

            menuViewImpl.a=f1;
            menuViewImpl.b=f2;
            menuViewImpl.c=f3;
            menuViewImpl.d=f4;
            menuViewImpl.e=f5;
            menuViewImpl.f=f6;
            if(a==true&&u==false)
            {
            profileFragment();
//                Log.e("menuvisible","units hide"+'\n');
            }
            if(b==true&&v==false)
            {
            profileFragment();
                Log.e("menuvisible","rastreo hide"+'\n');
            }
            if(c==true&&w==false)
            {
            profileFragment();
                Log.e("menuvisible","notifications hide"+'\n');
            }
            if(d==true&&x==false)
            {
            profileFragment();
                Log.e("menuvisible","zones hide"+'\n');
            }
            if(e==true&&y==false)
            {
            profileFragment();
                Log.e("menuvisible","contact hide"+'\n');
            }
            if(f==true&&z==false)
            {
            profileFragment();
                Log.e("menuvisible","profile hide"+'\n');
            }
        }
    }


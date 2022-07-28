package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class FragmentNavigationButtonsMenuv2 extends Fragment implements View.OnClickListener,menuView{
    public static final String TAG = FragmentNavigationButtonsMenuv2.class.getSimpleName();
    private List<String> myemenuItems=new ArrayList<>();
    private Handler handler = new Handler();
    public Runnable runnable;
    private boolean u, v, w, x, y, z=false ;
    private boolean a, b, c, d, e, f;
    private menuPresenter presenter;
    private ImageView menu1, menu2, menu3, menu4, menu5, menu6;
    private Guideline guideline1, guideline2, guideline3, guideline4, guideline5, guideline6;
    private ConstraintLayout unidades, rastreo, notificaciones, zonas, contacto, perfil;
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

        unidades = view.findViewById(R.id.constraintUnits);
        rastreo = view.findViewById(R.id.constraintTracking);
        notificaciones = view.findViewById(R.id.constraintNotification);
        zonas = view.findViewById(R.id.constrainZones);
        contacto = view.findViewById(R.id.constrainContact);
        perfil = view.findViewById(R.id.constrainProfile);


        menu1 = view.findViewById(R.id.camion1);
        menu2 = view.findViewById(R.id.rastreoB);
        menu3 = view.findViewById(R.id.notificacionesB);
        menu4 = view.findViewById(R.id.zonesimage);
        menu5 = view.findViewById(R.id.mensajes);
        ;
        menu6 = view.findViewById(R.id.perfilicon);


        guideline1 = view.findViewById(R.id.guideline11g);
        guideline2 = view.findViewById(R.id.guideline12g);
        guideline3 = view.findViewById(R.id.guideline13g);
        guideline4 = view.findViewById(R.id.guideline14g);
        guideline5 = view.findViewById(R.id.guideline15g);
        guideline6 = view.findViewById(R.id.guideline16g);

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
    public void listItems(List<String> items) {
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
        //menuConstrains();
    }
    @Override
    public void showError(String error) {
        /**TODO no data*/
    }

    @Override
    public void closeAppSessionExpired() {
        /**TODO no data*/
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
    @Override
    public void onClick(View v) {

    }

}

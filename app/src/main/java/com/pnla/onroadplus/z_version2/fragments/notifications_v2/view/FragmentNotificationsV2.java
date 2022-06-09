package com.pnla.onroadplus.z_version2.fragments.notifications_v2.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.adapters.AdapterNotificationsV2;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.adapters.AdapterVehiclesNotificationV2;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.common.CommonVehicleNotification;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2SetData;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2View;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationV2OnRoad;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.presenters.NotificationsV2PresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2_map.FragmentNotificationV2Map;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class    FragmentNotificationsV2 extends Fragment implements SearchView.OnQueryTextListener,
        View.OnClickListener, NotificationsV2View, AdapterVehiclesNotificationV2.OnClickVehicleListener, AdapterNotificationsV2.OnClickNotificationListener {

    public static final String TAG = FragmentNotificationsV2.class.getSimpleName();
 private static final String TAG1="LISTA";
    private NotificationsV2SetData notificationsV2SetData;
    private NotificationsV2Presenter presenter;

    private DatePickerDialog.OnDateSetListener mDateSetListenr;

    /**
     * Views
     */
    private ConstraintLayout btnAllNotifications, btnNotificationByVehicle,constraintLayout2;

    private ImageView imgvCalendar,imgvCalendar1, imgvSearchVehicleNotification, imgvCloseSearchNotification,toolbarImgBack;//,imgvButtonhideunitsnotifications;
    private ConstraintLayout constraintSearchViewNotifications;
    private SearchView searchViewNotifications;
    private TextView txvDateNotification;
    private TextView txtButtonAll;
    private TextView txtButtonUnits;
    private LinearLayout llContainerAll;
    private LinearLayout llContainerUnits;
    private ConstraintLayout progressBar;
    private LinearLayout containerall;
    /**
     * RecyclerView de vehículos
     */
    private List<VehicleV2> vehicles;
    private RecyclerView rvVehicles;
    private AdapterVehiclesNotificationV2 adapterVehicles;

    /**
     * RecyclerView de Notificaciones
     */
    private List<NotificationV2OnRoad> notifications;
    private RecyclerView rvNotifications;
    private AdapterNotificationsV2 adapterNotificationsV2;

    private ConstraintLayout constraintRootNotifications;

    private DialogTrackingLoader loader;
    private boolean isVisibleAllNotifications = true;
    private String mainDate;

    private List<String> vehiclelist;
    private List<String> notificationsList;
    private List<String> itemNotificationList;
    private List<String> itemNotificationListCount;
    /**
     * Vehiculo seleccionado
     */
    private int vehicleSelectedCve;
    private String vehicleSelectedName, vehicleSelectedImage;

    /**
     *
     * boolean para esconder lista de vehiculos
     */

    /**
     *
     *
     * string para texto de numero de notificaciones
     *
     * **/

    private boolean isinunits=false;
    //private Integer numero;
    private  String notification;

    //Nuevo loader
    private ProgressDialog progressDialog;

    private String nameplusnumber;
    private String parta;
    private String[] parts;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications_v2, container, false);


        initNotificationsView(view);
        setFonts();

        initThemeConfig();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.cancelRequest();
    }

    @Override
    public void onResume() {//este metodo recarga las ultimas notificaciones
        super.onResume();
        presenter.cancelRequest();
        if (isVisibleAllNotifications) {
            if (vehicles != null && vehicles.size() > 0) {
                presenter.rechargeNotifications(vehicles, mainDate, getContext());
            }
        } else {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelRequest();
        presenter.setView(null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //notificationsV2SetData = (NotificationsV2SetData) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNotificationByVehicle:


                llContainerUnits.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
                txtButtonUnits.setTextColor(getResources().getColor(R.color.colorWhite));
                txtButtonAll.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
                toolbarImgBack.setVisibility(View.GONE);
                llContainerAll.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                if(adapterNotificationsV2!=null)
                { showLoader();}
                else{

                }
                showVehicles();
                break;
            case R.id.imgvSearchVehicleNotification:
                showSearchView();
                break;
            case R.id.imgvCloseSearchNotification:
                closeSearchView();
                break;
            case R.id.btnAllNotifications:
                llContainerAll.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
                txtButtonAll.setTextColor(getResources().getColor(R.color.colorWhite));
                txtButtonUnits.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
                llContainerUnits.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                if(adapterNotificationsV2!=null){

                         toolbarImgBack.setVisibility(View.GONE);
                         showAllNotifications();
                }
                else{
                    }
                break;
            case R.id.imgvCalendar:
                showCalendar();
                break;
            case R.id.toolbar_unit_tracking_img_back1:
                rvVehicles.setVisibility(View.VISIBLE);
                toolbarImgBack.setVisibility(View.GONE);

                containerall.setVisibility(View.VISIBLE);
                txvDateNotification.setVisibility(View.VISIBLE);
                imgvCalendar.setVisibility(View.VISIBLE);
                imgvCalendar1.setVisibility(View.VISIBLE);
                   // Toast.makeText(getContext(),  "back", Toast.LENGTH_SHORT).show();
                    break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {//filtro de query
        if (newText == null || newText.equals("")) {
            imgvCloseSearchNotification.setVisibility(View.VISIBLE);
        } else {
            imgvCloseSearchNotification.setVisibility(View.GONE);
        }
        if (adapterVehicles != null) {       //query filtro notificaciones por unitdades
            List<VehicleV2> listaFiltradaGroup = filterListGroup(vehicles, newText);
            adapterVehicles.setFilter(listaFiltradaGroup);

        }
        if(adapterNotificationsV2 != null) {//query filtro notificaciones por todas las notificaciones
            List<NotificationV2OnRoad> filterListNotifications=filterListNotifications(notifications,newText);
            adapterNotificationsV2.setFilter(filterListNotifications);
        }
        return false;
    }

    @Override
    public void showLoader() {
     /*   loader = new DialogTrackingLoader();
        loader.show(getActivity().getSupportFragmentManager(), DialogTrackingLoader.TAG);*/
       // progressBar.setVisibility(View.VISIBLE);

        progressDialog.setMessage("Cargando Notificaciones OnRoad");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideLoader() {
      /*  if (loader != null) {
            loader.dismiss();
            loader = null;
        }
*/
        //progressBar.setVisibility(View.GONE);
        progressDialog.hide();
    }
    @Override
    public void showMessage(String message) {
    }

    @Override
    public void sessionExpired(String message) {
        showMessage(message);
        getActivity().finish();
    }

    @Override
    public void showCurrentDate(String currentDate) {
        txvDateNotification.setText(currentDate);
    }

    @Override
    public void fillVehiclesList(List<VehicleV2> vehicles) {
        this.vehicles = vehicles;
        fillVehicles();
    }

    @Override
    public void fillAllNotifications(List<NotificationV2OnRoad> notifications) {
        this.notifications = notifications;
        fillNotificationsList();

    }

    @Override
    public void onClickVehicle(View v, int vehicleCve, String vehicleName, String vehicleImage)//,String notificaions)//remarcado al darle clic al vehiculo se genera la lista de notificaciones por vehiculo
    {
        this.vehicleSelectedCve = vehicleCve;
        this.vehicleSelectedName = vehicleName;
        this.vehicleSelectedImage = vehicleImage;
        showLoader();
        presenter.cancelRequest();

        cleaListNotifications();

        List<VehicleV2> vehicleV2s = new ArrayList<>();      /**Aqui lo importante es setear la clave de vehiculo,*/
        vehicleV2s.add(new VehicleV2("", vehicleName, vehicleImage, 0.0, 0.0, true, vehicleCve, 0));//se realiza la peticion de las notificaciones por nombre
        presenter.getAllNotification(vehicleV2s, txvDateNotification.getText().toString(), getContext());

    }
    private void itmeCount()//esto se ejecuta al dar click en el boton por unidad¡
    {

        presenter.cancelRequest();

        //cleaListNotifications();
        List<VehicleV2> vehicleV2s = new ArrayList<>();//se crea una nueva lista con un nombre
        CommonVehicleNotification.currentItem = new VehicleV2("", vehicles.get(0).getVehicleName(), vehicles.get(0).getVehicleImage(), vehicles.get(0).getLatitude(), vehicles.get(0).getLongitude(), true, vehicles.get(0).getCveVehicle(), vehicles.get(0).getPositionItem());
        CommonVehicleNotification.nameVehicle = CommonVehicleNotification.currentItem.getVehicleName();
        CommonVehicleNotification.realPosition = -1;
        vehicleV2s.add(vehicles.get(0));
        presenter.getAllNotification(vehicleV2s, txvDateNotification.getText().toString(), getContext());/**Consumimos webservice de la primer unidad*/
        checkAllNotifications();
        }

    private void fillVehicles() {//esto se ejecuta al dar click en el boton notificaciones
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapterVehicles = new AdapterVehiclesNotificationV2(vehicles, getContext());
        adapterVehicles.setOnClickVehicleListener(FragmentNotificationsV2.this);
        rvVehicles.setLayoutManager(layoutManager);
        rvVehicles.setAdapter(adapterVehicles);
        presenter.getAllNotification(vehicles, txvDateNotification.getText().toString(), getContext());//esto es el fragment de las notificaciones por unidad
        }

    private void checkAllNotifications()
    {

        List<VehicleV2> vehicleV2s = new ArrayList<>();

        notificationsList = new ArrayList<>();
        vehiclelist=new ArrayList<>();
        itemNotificationList=new ArrayList<>();
        itemNotificationListCount=new ArrayList<>();
       // List<String> notifcount=new ArrayList<>();

        for (int i = 0; i < notifications.size(); i++) {
             notificationsList.add(notifications.get(i).getVehicleName());

        }
        for (int j = 0; j < vehicles.size(); j++) {
            vehiclelist.add(vehicles.get(j).getVehicleName());
        }

         for (String item : vehiclelist)
        {
            if (notificationsList.contains(item) == true)
            {
                int num =Collections.frequency(notificationsList, item);
                /***Set<String> unique =new HashSet<String>(notificationsList);

                for(String key : unique) {

                    nameplusnumber = key + ":" + Integer.toString(Collections.frequency(notificationsList, item));
                    parts = nameplusnumber.split(":");
                    parta = parts[0];
                    String partb = parts[1];
                }**/
                itemNotificationListCount.add(Integer.toString(num));
             //   notifcount.add(item);
            }
            else
            {
            //    notifcount.add("000000");
                itemNotificationListCount.add("0");
            }

        }
        Set<String> unique =new HashSet<String>(notificationsList);
        for(String key : unique) {

            //itemNotificationList.add(key+":"+Integer.toString(Collections.frequency(notificationsList, key)));

           String nameplusnumber1 = key + ":" + Integer.toString(Collections.frequency(notificationsList, key));
            String[] parts1 = nameplusnumber1.split(":");
            String part1 = parts1[0]; // 004
            String part2 = parts1[1];
            itemNotificationList.add(part1 + ":" + part2);

        }
/**
 *
 *
 * las listas en el log son las que nos llevan al conteo de notificaciones por item
 *
 * **/
     //  Log.i(TAG1,""+ notificationsList);
   //    Log.i(TAG1,""+ vehiclelist);
      // Log.i(TAG1,""+itemNotificationListCount);
     //  Log.i(TAG1,""+itemNotificationList+"/n");

        /****/

        adapterVehicles.holderTextList(itemNotificationListCount);
       // Log.i(TAG1,""+itemNotificationListCount.size());


    }

    private void fillNotificationsList() { ////////////esto es la lista en todos instantes

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapterNotificationsV2 = new AdapterNotificationsV2(getContext(), notifications);
        adapterNotificationsV2.setOnClickNotificationListener(FragmentNotificationsV2.this);
        rvNotifications.setLayoutManager(layoutManager);
        rvNotifications.setAdapter(adapterNotificationsV2);

       //Toast.makeText(getContext(),  Integer.toString(adapterNotificationsV2.getItemCount()), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getContext(),  Integer.toString(vehicles.size()), Toast.LENGTH_SHORT).show();
        if(adapterNotificationsV2.getItemCount()!=0){
            constraintLayout2.setVisibility(View.GONE);
        }else{
            constraintLayout2.setVisibility(View.VISIBLE);

        }
        //   if(adapterVehicles.holderText()==Integer.toString(adapterNotificationsV2.getItemCount()));
        adapterVehicles.holderText(Integer.toString(notifications.size()));
        //
    }

    @Override
    public void onClickHide(View imgv)
    {
        toolbarImgBack.setVisibility(View.VISIBLE);
        rvVehicles.setVisibility(View.GONE);

        containerall.setVisibility(View.GONE);
        txvDateNotification.setVisibility(View.GONE);
        imgvCalendar.setVisibility(View.GONE);
        imgvCalendar1.setVisibility(View.GONE);
    }




    @Override
    public void onClickNotification(NotificationV2OnRoad notification) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelable(GeneralConstantsV2.NOTIFICATION, notification);
        FragmentNotificationV2Map fragmentNotificationV2Map = new FragmentNotificationV2Map();
        fragmentNotificationV2Map.setArguments(bundle);

        transaction.addToBackStack(FragmentNotificationV2Map.TAG);
        transaction.replace(R.id.constraintRootNotifications, fragmentNotificationV2Map, FragmentNotificationV2Map.TAG).commit();
    }

    private void initNotificationsView(View view) {


        constraintRootNotifications = view.findViewById(R.id.constraintRootNotifications);

        searchViewNotifications = view.findViewById(R.id.searchViewNotifications);
        containerall= view.findViewById(R.id.ll_main_container_notifications);
        btnAllNotifications = view.findViewById(R.id.btnAllNotifications);
        btnNotificationByVehicle = view.findViewById(R.id.btnNotificationByVehicle);

        imgvCalendar = view.findViewById(R.id.imgvCalendar);
        imgvCalendar1 = view.findViewById(R.id.imgvCalendar1);
        constraintLayout2= view.findViewById(R.id.constraintLayout2);
        constraintSearchViewNotifications = view.findViewById(R.id.constraintSearchViewNotifications);
        imgvSearchVehicleNotification = view.findViewById(R.id.imgvSearchVehicleNotification);
        imgvCloseSearchNotification = view.findViewById(R.id.imgvCloseSearchNotification);

        txvDateNotification = view.findViewById(R.id.txvDateNotification);
        toolbarImgBack = view.findViewById(R.id.toolbar_unit_tracking_img_back1);
        rvVehicles = view.findViewById(R.id.rvVehicles);
        rvNotifications = view.findViewById(R.id.rvNotifications);

        txtButtonAll = view.findViewById(R.id.txt_btn_notifications_all);
        txtButtonUnits = view.findViewById(R.id.txt_btn_notifications_units);
        llContainerAll = view.findViewById(R.id.ll_container_notifications_all);
        llContainerUnits = view.findViewById(R.id.ll_container_notifications_units);
        progressBar = view.findViewById(R.id.units_view_progress_bar1);
        searchViewNotifications.setOnQueryTextListener(this);
        btnAllNotifications.setOnClickListener(this);
        btnNotificationByVehicle.setOnClickListener(this);


        progressDialog = new ProgressDialog(getActivity());

        imgvCalendar.setOnClickListener(this);

        imgvSearchVehicleNotification.setOnClickListener(this);
        imgvCloseSearchNotification.setOnClickListener(this);
        toolbarImgBack.setVisibility(View.GONE);

        toolbarImgBack.setOnClickListener(this);
        presenter = new NotificationsV2PresenterImpl();
        presenter.setView(this);
        presenter.getMainDate();
        presenter.getAllVehicles(getContext());

            constraintLayout2.setVisibility(View.GONE);



        imgvCalendar1.setVisibility(View.VISIBLE);
        datePickerListener();
    }



    private void showSearchView() {
        //if (!isVisibleAllNotifications) {
        constraintSearchViewNotifications.setVisibility(View.VISIBLE);
        //   }
    }

    private void closeSearchView() {
        constraintSearchViewNotifications.setVisibility(View.GONE);
    }

    private  void countlisUByUnits(){

    }

 private void cleaListNotifications(){
     if (adapterNotificationsV2 != null && adapterNotificationsV2.getItemCount() > 0)   /**Limpiamos todas las notificaciones de la lista*/ {
         adapterNotificationsV2.clearNotifications();

     }

 }
    private void showVehicles() {

        isVisibleAllNotifications = false;
        //seteamos null para qe cuando el usuario seleccione btn "por unidad" el adapter pinte la 1ra unidad en bold
        constraintSearchViewNotifications.setVisibility(View.GONE);/**   esto es el campo de busqueda   **/
        /* rvVehicles.setVisibility(View.VISIBLE);                         /**Se esconden los vehiculos                        */
        ViewCompat.setElevation(btnNotificationByVehicle, 6);   /**Se marca el boton porVehiculo                    */
        ViewCompat.setElevation(btnAllNotifications, 0);        /**Se desmarca el boton todos                       */
        imgvSearchVehicleNotification.setVisibility(View.VISIBLE);      /**Se muestra boton para visualizar el searchView   */
        constraintLayout2.setVisibility(View.GONE);
        rvVehicles.setVisibility(View.VISIBLE);                         /**Se muestran todos los vehiculos                  */

        isinunits=true;
        if(adapterNotificationsV2!=null)
        {itmeCount();}
        else{
            Toast.makeText(getContext(),  "sin notificaciones", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAllNotifications() {//muestra todas kas notificaciones


        isVisibleAllNotifications = true;

        ViewCompat.setElevation(btnAllNotifications, 6);            /**Se marca el boton todos                  */
        ViewCompat.setElevation(btnNotificationByVehicle, 0);       /**Se desmarca el boton "porVehiculo"       */
        constraintSearchViewNotifications.setVisibility(View.GONE);          /**Escondemos el searchView                */
        searchViewNotifications.setQuery("", false);            /**Limpiamos el serchView                  */
        rvVehicles.setVisibility(View.GONE);                                 /**Escondemos toda la lista de vehiculos   */
        cleaListNotifications();
        isinunits=false;
        showLoader();
        presenter.getAllNotification(vehicles, txvDateNotification.getText().toString(), getContext());   /*Se muestran todas las notificaciones/

       /* try {

            Thread.sleep(2000);*/
        if(adapterNotificationsV2.getItemCount()==0)
        {
            constraintLayout2.setVisibility(View.VISIBLE);
        }
        else{
            constraintLayout2.setVisibility(View.GONE);
        }
     /*   } catch (InterruptedException e) {

            e.printStackTrace();
        }*/
    }

    private void showCalendar() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListenr, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private List<VehicleV2> filterListGroup(List<VehicleV2> vehicles, String text) {
        if (vehicles != null) {
            List<VehicleV2> listaFiltada = new ArrayList<>();
            text = text.toLowerCase();
            for (VehicleV2 nota : vehicles) {
                String nota2 = nota.getVehicleName().toLowerCase();
                if (nota2.contains(text)) {
                    listaFiltada.add(   nota);
                }
            }
            return listaFiltada;
        } else {
            return null;
        }
    }
    private List<NotificationV2OnRoad> filterListNotifications(List<NotificationV2OnRoad> notifications, String text) {
        if (notifications != null) {
            List<NotificationV2OnRoad> listaFiltrada = new ArrayList<>();
            text = text.toLowerCase();
            for (NotificationV2OnRoad nota : notifications) {
                String nota2 = nota.getNotification().toLowerCase();
                if (nota2.contains(text)) {
                    listaFiltrada.add(   nota);
                }
            }
            return listaFiltrada;
        } else {
            return null;
        }
    }
    private List<NotificationV2OnRoad> filterListNotifications2(List<NotificationV2OnRoad> notificationsporitem, String nombre_unico){
    if(notificationsporitem!=null){
        List<NotificationV2OnRoad> listaporitem=new ArrayList<>();
        nombre_unico=nombre_unico.toLowerCase();
        for(NotificationV2OnRoad header : notificationsporitem) {
        String nota2=header.getDateNotification().toLowerCase();
            if(nota2.contains(nombre_unico)){
                listaporitem.add(header);

            }
        }
        return listaporitem;
    }
      else{  return  null;}

    }
    private void removeFocus() {
        searchViewNotifications.setQuery("", false);
        constraintRootNotifications.requestFocus();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initThemeConfig(){

        txtButtonAll.setText("Todas");
        txtButtonUnits.setText("Por Unidad");


        llContainerAll.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        txtButtonAll.setTextColor(getResources().getColor(R.color.colorWhite));
        txtButtonUnits.setTextColor(getResources().getColor(R.color.colorOrangeYellow));



    }
    private void datePickerListener() {
        mDateSetListenr = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (view.isShown()) {
                    removeFocus();
                    hideKeyboard(getActivity());
                    int dia = dayOfMonth;
                    int mes = month + 1;

                    String sDay = "" + dia;
                    String sMonth = "" + mes;

                    if (sDay.length() == 1) {
                        sDay = "0" + sDay;
                    }
                    if (sMonth.length() == 1) {
                        sMonth = "0" + sMonth;
                    }
                    mainDate = sDay + "/" + sMonth + "/" + year;
                    txvDateNotification.setText(sDay + "/" + sMonth + "/" + year);
                    showLoader();
                    if (isVisibleAllNotifications) {    /***      Consumimos el servicio, si estan vvisiables todas las notificaciones mostramos todas  */
                        presenter.cancelRequest();
                        if (adapterNotificationsV2 != null && adapterNotificationsV2.getItemCount() > 0) {
                            adapterNotificationsV2.clearNotifications();
                        }
                        presenter.getAllNotification(vehicles, txvDateNotification.getText().toString(), getContext());
                    } else {
                        presenter.cancelRequest();
                        if (adapterNotificationsV2 != null && adapterNotificationsV2.getItemCount() > 0) {
                           // adapterNotificationsV2.clearNotifications();
                        }
                     /*  List<VehicleV2> vehicleV2s = new ArrayList<>();
                        /**Aqui lo importante es setear la clave de vehiculo,
                        vehicleV2s.add(new VehicleV2("", vehicleSelectedName, vehicleSelectedImage, 0.0, 0.0, false, vehicleSelectedCve, 0));
                        presenter.getAllNotification(vehicleV2s, txvDateNotification.getText().toString(), getContext());
                    */
                        llContainerAll.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
                        txtButtonAll.setTextColor(getResources().getColor(R.color.colorWhite));
                        txtButtonUnits.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
                        llContainerUnits.setBackgroundColor(getResources().getColor(R.color.colorWhite));

                        ViewCompat.setElevation(btnAllNotifications, 6);            /**Se marca el boton todos                  */
                        ViewCompat.setElevation(btnNotificationByVehicle, 0);       /**Se desmarca el boton "porVehiculo"       */
                        constraintSearchViewNotifications.setVisibility(View.GONE);          /**Escondemos el searchView                */
                        searchViewNotifications.setQuery("", false);            /**Limpiamos el serchView                  */
                        rvVehicles.setVisibility(View.GONE);                                 /**Escondemos toda la lista de vehiculos   */
                        cleaListNotifications();
                        isinunits=false;
                        //presenter.getAllNotification(vehicles, txvDateNotification.getText().toString(), getContext());
                        presenter.getAllNotification(vehicles, txvDateNotification.getText().toString(), getContext());}
                }
            }
        };
    }

    private void setFonts() {
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        // btnAllNotifications.setTypeface(latoBoldTypeface);
        // btnNotificationByVehicle.setTypeface(latoBoldTypeface);
        // btnNotificationByVehicle.setTypeface(latoBoldTypeface);
        // txvDateNotification.setTypeface(latoBoldTypeface);

        Typeface robotoRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoMedium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf");
        Typeface robotoLight = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Light.ttf");

        //new version
        txtButtonAll.setTypeface(robotoMedium);
        txtButtonUnits.setTypeface(robotoMedium);
    }



}

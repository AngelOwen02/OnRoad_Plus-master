package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.View;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Adapter.adapterAsignmentsEdit;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Adapter.adapterTripulantes;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.Tripulante;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Presenter.zonePresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Presenter.zonePresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.adapter.zonesAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view.zonesRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class zoneAsignViewImpl extends AppCompatActivity implements View.OnClickListener,zoneAsignView{

    private ImageView addAsignment,trashCan,newtrascan,routesSearch,back ,yellowB,circleB,selectE,crossAction,pencil;/**imagen + de agregar icono de borar, icono de borrar, filtro de rutas, icono atras icono amarillo, circulo blanco, icono tod0 , equis, lappiz*/
    private TextView zoneName,texttodo,textnumselected,penciltext;  /**encabezado con el nombre de la zona , texto tod0 y numer0 de items seleccionados*/
    private TextView textEmpty1,textEmpty2; /** texto de si asignaciones*/
    private TextView GuardarDialogTripulantes;
    private TextView checkAllTripulantes,uncheckAllTripulantes;/**iconos de tod0 y limpiar tripulantes*/
    private ConstraintLayout barEditeErase   ,DialogAdaptertripulantes,backgroundDialogTripulante;  /**Constrain de dialog y constrain de background de dialog de tripuklantes*/
    private ProgressDialog progressDialog; /**progres dialog de vista*/
    private RecyclerView rv, rvDialog;
    private SearchView searchView;
    private CardView fieldforSeach;
    private SearchView searchViewa,searchViewT;
    private String nameZ;

    private adapterAsignmentsEdit adapter;         /**adaptador de asinaciones*/
    private adapterTripulantes adaterTripulacion;  /**adaptador de tripulantes*/

    private adapterAsignmentsEdit adapterAsigments;
    private adapterTripulantes adapterTripulantesDialog;
    private zonePresenter presenter;


    private List<VehicleDriver> myAsignments;

    private List<String> myvehicles=new ArrayList<>();
    private List<String> mydrivers=new ArrayList<>();
    private List<String> myd2=new ArrayList<>();
    private  List<String> indexToErase=new ArrayList<>();
    public static List<String> indexes=new ArrayList<>();

    private int itemtoperformClick;

    private int dialogtripulantes;
    private List<String> newtripulantesList;

    private List<String> D=new ArrayList<>();
    private List<String> V=new ArrayList<>();
    private String cveLayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignvehicle);
        initView();

    }
    private void initView() {
        progressDialog = new ProgressDialog(this);
        back=findViewById(R.id.backasignvehicle);

        textEmpty1=findViewById(R.id.textViewAsignVehicle1);
        textEmpty2=findViewById(R.id.textViewVehicleAsign2);
        zoneName=findViewById(R.id.routeName);

        rv=findViewById(R.id.recyclerViewAsignVehicle);/**rv de tarjetas*/
        nameZ=zonesAdapter.zoneName;
        zoneName.setText(nameZ);
        barEditeErase=findViewById(R.id.constrainbareditErase);

        DialogAdaptertripulantes=findViewById(R.id.backgrounddialogAsigments);/**Dialog Tripulantes*/
        backgroundDialogTripulante=findViewById(R.id.constrintohide);//backgrounddialogAsigments);
        backgroundDialogTripulante.setOnClickListener(this);
        rvDialog=findViewById(R.id.rvtripulantes);


        yellowB =findViewById(R.id.yellowtocircle);
        circleB=findViewById(R.id.circletoyellow);
        texttodo =findViewById(R.id.todotext);
        textnumselected=findViewById(R.id.selectedItems);  /**texto de numero de items en la lista*/
        indexes.clear();
        circleB.setOnClickListener(this);//meterlo al case

        selectE=findViewById(R.id.selecteverything);/** icono de imagen todo*/
        selectE.setOnClickListener(this);

        crossAction=findViewById(R.id.crosscancel);/**simbolo de eliminar*/
        crossAction.setOnClickListener(this);

        addAsignment=findViewById(R.id.additemAsignment);/**agrega un nuevo item*/
        addAsignment.setOnClickListener(this);

        routesSearch=findViewById(R.id.routesSearch);/**    esto es el filtro*/
        fieldforSeach=findViewById(R.id.searchField);

        checkAllTripulantes=findViewById(R.id.textViewTODO);
        checkAllTripulantes.setOnClickListener(this);

        uncheckAllTripulantes=findViewById(R.id.txtViewClean);
        uncheckAllTripulantes.setOnClickListener(this);

        GuardarDialogTripulantes=findViewById(R.id.guardarDialogTripulantes);
        GuardarDialogTripulantes.setOnClickListener(this);



        searchViewa= (SearchView) this.findViewById(R.id.searchViewRoutes);
        searchViewa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<VehicleDriver> newAsig=filteredAsignments(myAsignments,newText);
                adapter.setFilter(newAsig);
                return false;
            }
        });
        searchViewT=(SearchView) this.findViewById(R.id.searchViewtripulantes);
        searchViewT.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<String> newTripulantes=filterTripulantes(mydrivers,newText);
                adaterTripulacion.setFilter(newTripulantes);
                return false;
            }
        });

        pencil=findViewById(R.id.editpencil);       /***/
        penciltext=findViewById(R.id.penciltext);
        trashCan=findViewById(R.id.trashcan);
        trashCan.setOnClickListener(this);
        newtrascan=findViewById(R.id.newtrash);
        newtrascan.setOnClickListener(this);

        pencil.setOnClickListener(this);
        routesSearch.setOnClickListener(this);
        back.setOnClickListener(this);


        //region presenter Request
        presenter=new zonePresenterImpl(this,getApplicationContext());
        String cvelayer = String.valueOf(zonesAdapter.cveLayerZone);
        cveLayerName=zonesAdapter.cveLayerZoneName;
        Log.e("tripuFlow","1 cvelayer :  "+cvelayer);
        presenter.requestAsignment(cvelayer);
        presenter.requestUnitsCatalog();
        presenter.requesDriverCatalog();
        //endregion


    }

    //region setData methods ........=>  ......myvehicles[cveD,....]......mydrivers[cveD,....]......myd2[cved2.....]
    @Override
    public void setasigments(List<VehicleDriver> Asignments) {
    this.myAsignments=Asignments;
        if(myAsignments!=null) {


            for (int j = 0; j < myAsignments.size(); j++) {

                Log.e("tripulantesnewFLOW25","myAsignments View"+
                        "   vehicle:"+myAsignments.get(j).getCveVehicle()+
                        "   driver:"+myAsignments.get(j).getCveDriver()+
                        "   tripulantes"+myAsignments.get(j).getTripulantes());
//                if(myAsignments.get(j).getTripulantes()!=null)
//                {
//                    if(myAsignments.get(j).getTripulantes().size()==1){
//
//                    for(int k=0;k<myAsignments.get(j).getTripulantes().size();k++)
//                    {
//                        if(myAsignments.get(j).getTripulantes().get(k).getTripulanteName().equals(""))
//                        {
//                            myAsignments.get(j).getTripulantes().clear();
//                            Log.e("tripulantesnewFLOW23","vT: "+myAsignments.get(j).getTripulantes());
//                        }
//                    }
//                    }
//                }
            }


        }else
        {
            Log.e("tripuFlow",""+myAsignments);
            myAsignments=new ArrayList<>();
        }
        fillAdapter();
        textEmpty1.setVisibility(View.GONE);
        textEmpty2.setVisibility(View.GONE);
        rv.setVisibility(View.VISIBLE);
    }
    @Override
    public void setDrivers(List<String> drivers) {
    this.mydrivers=drivers;
        Log.e("tripulantesnewFLOW","D"+mydrivers);
    }

    @Override
    public void setDrivers2(List<String> d2) {
        this.myd2=d2;
        Log.e("sizetripulantes","D"+myd2);
    }

    @Override
    public void setVehicless(List<String> vehicles) {
        this.myvehicles=vehicles;
        Log.e("tripulantesnewFLOW","V"+myvehicles);

    }
    @Override
    public void setD(List<String> d) {
        this.D=d;
    }

    @Override
    public void setV(List<String> v) {
        this.V=v;
        presenter.hideDialog();
    }

    @Override
    public void restartAfterUpdate() {

        Intent intent = new Intent(zoneAsignViewImpl.this,zoneAsignViewImpl.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        android.app.AlertDialog.Builder logoutDialog = new android.app.AlertDialog.Builder(getApplicationContext(), R.style.AlertDialogCustom);
        logoutDialog.setMessage(message);
        logoutDialog.setCancelable(false);
        logoutDialog.setPositiveButton(R.string.logout_dialog_btn_ok, null);
        logoutDialog.show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando Asignaciones");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }


    //endregion setData*/


    private void fillAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
        rv.setLayoutManager(layoutManager);
        adapter=new adapterAsignmentsEdit(this,myAsignments,myvehicles,mydrivers,getApplicationContext());
        rv.setAdapter(adapter);
        rv.smoothScrollToPosition(myAsignments.size()-1);

    }
    private void fillDataDialogTripulantes(List<String> trip)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvDialog.setLayoutManager(layoutManager);

        adaterTripulacion=new adapterTripulantes(this, myd2,trip,getApplicationContext() );
        rvDialog.setAdapter(adaterTripulacion);
    }




    private List<VehicleDriver> filteredAsignments(List<VehicleDriver> data, String text)
    {
        List<VehicleDriver> asignments=new ArrayList<>();
        asignments.clear();
        text = text.toLowerCase();
        if(data!=null)
        {           for (VehicleDriver newasign : data) {
                        String vehicleName = newasign.getVehicleName().toLowerCase();
                        if(vehicleName.contains(text))
                        {
                            asignments.add(newasign);
                        }

            }
        }
        return asignments;
    }


    private List<String> filterTripulantes(List<String> mydriversC ,String text)
    {
        List<String> tripulantes=new ArrayList<>();
        tripulantes.clear();
        text=text;
        if(mydriversC!=null)
        {
            if(mydrivers!=null){
                for(int i=0;i<mydrivers.size();i++)
                {
                    String v=mydrivers.get(i);
                    if(v.contains(text))
                    {
                        tripulantes.add(v);
                    }
                }
            }

        }
        return tripulantes;
    }

    //region eraseDialog este bloque despliega la alerta en la view para eliminar la tarjeta pos posicion
    public void deleteAlertDialog(final int position)
    {
        indexToErase.clear();
        Log.e("editEraseB","asingmentdatasize : "+ myvehicles.size());
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        // builder.setTitle("¿Deseas eliminar el elemento asignado?");
        builder.setMessage("¿Deseas eliminar el elemento asignado?");
        builder.setCancelable(true);
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

/**
                if(myvehicles.size()==1) {
                    indexToErase.clear();
                    myvehicles.clear();
                    List<vehicleZones> zones=new ArrayList<>();
                    vehicleZones data = new vehicleZones(0,0);
                    zones.add(data);
                    Log.e("checkEmpty","-"+zones);
  //                  presenter.updateAsignments(zones);
                    chekcinAsigments();

                }else
                {
                    indexToErase.add(String.valueOf(position));
                    orderErase();
                }
**/
                Log.e("tripulantesnewFLOW","tripulantesnewFLOW"+position);
                List<VehicleDriver> newmyasignments=new ArrayList<>();
                newmyasignments=myAsignments;
                presenter.auditTrail("Zona: "+cveLayerName+" removio el vehiculo: "+newmyasignments.get(position).getVehicleName()+"|"+newmyasignments.get(position).getCveVehicle());
                newmyasignments.remove(position);

                Log.e("tripulantesnewFLOW","tripulantesnewFLOW"+newmyasignments);
                //Log.e("tripuFlow4",""+newmyasignments.size()+newmyasignments.get(0)); este log solo fue para verificar si elimina la data
                presenter.updateAsignments(newmyasignments);

                dialog.dismiss();
               // restartActivity();

            }
        });
        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }


    //endregion

    public void restartActivity() {


        Intent intent = new Intent(zoneAsignViewImpl.this,zoneAsignViewImpl.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }

    public void safeData(int posdata,String nombre,String vehiculo,List<String> tripulantes,boolean isnew)
    {
        if(isnew==true)
        {//region newitem
//            Log.e("tripuFlow8", "validar si es nuevo N" + isnew+"   Datos nuevos N "+nombre+"  V "+vehiculo+"   T  " +tripulantes.size()+ " actual size"+myAsignments.size() );//tripulantes.size()); tripulantes size dio 0 revisar

                int dimensionArray = myAsignments.size() - 1;
                Log.e("tripuFlow7", "validar si es nuevo F" + isnew);
                /**validar datos para item nuevo*/
                if (tripulantes == null) {
                    Log.e("tripuFlow5", "tripúlantes null  posicion  " + posdata + " N " + nombre + "  V " + vehiculo + " T " + tripulantes);

                    List<Tripulante> nuevetripulacion = new ArrayList<>();
                    nuevetripulacion.clear();
                    List<Integer> tripulantesListEndpointu = new ArrayList<>();
                    tripulantesListEndpointu.clear();
                    int cveD = 0;
                    int cveV = 0;
                    /**considerar en el item 0 y en un nuevo item*/
                    Log.e("tripuFlow3", "calcula la data  posicion de item " + posdata + " Nombre " + nombre + "  Vehiculo " + vehiculo + " Tripulantes " + tripulantes);
                    Log.e("tripuFlow6", "data de tripulantes despued de editar en null ");
                    for (int i = 0; i < D.size(); i++)//itero sobre todos los nombres de conductores
                    {
                        String[] parts = D.get(i).split("/");//parts[0] => nombre part[1]=>cve
                        Log.e("tripuFlow3", "" + D.get(i));
                        if (parts[0].equals(nombre))///si el nombre existe en la lista de conductores y es igual a Nombre
                        {
                            cveD = Integer.valueOf(parts[1]);//
                        }

//                for(int l=0;l<tripulantes.size();l++)// para cada iteracion por conductor verifico si el tripulante existe para agregar el cve a la lista tripulantesListEndpointu
//                {
//                    if(parts[0].contains(tripulantes.get(l)))
//                    {
//                        Log.e("tripuFlow4"," "+parts[1]+","+tripulantes.get(l));
//                        Tripulante agregatripulante= new Tripulante(Integer.valueOf( parts[1]),tripulantes.get(l));
//                        tripulantesListEndpointu.add(Integer.valueOf(parts[1]));
//                        nuevetripulacion.add(agregatripulante);
//
//                    }
//                }
                    }
                    for (int i = 0; i < V.size(); i++)//
                    {
                        String[] parts = V.get(i).split("/");
                        Log.e("tripuFlow5", "" + V.get(i));
                        if (parts[0].equals(vehiculo))///si el nombre existe en la lista de conductores y es igual a Nombre
                        {
                            Log.e("tripuFlow5", "salio bien " + vehiculo + "   cve " + parts[1]);
                            cveV = Integer.valueOf(parts[1]);//
                        } else {
                            Log.e("tripuFlow5", "esta aqui verifica que salio mal en tripulantes con null " + vehiculo + "   cve " + parts[1]);
                        }
                    }
                    Log.e("tripuFlow3", "cveVehicle: " + cveV + " cveDriver: " + cveD + "  tripulantes " + nuevetripulacion.size() + "   vehiculo: " + vehiculo + "  nombre: " + nombre);//esto tiene que ser un set de la posicion
                    /**la variable que hace update es diferente a la que hace get*/
                    // List<VehicleDriver> fullArrayD=new ArrayList<>();
                    //fullArrayD.clear();
                    Log.e("tripuFlow6", "tripulacion anterior N: " + myAsignments.get(posdata).getTripulantes().size());
                    VehicleDriver setvehicle = new VehicleDriver(cveV, cveD, myAsignments.get(posdata).getTripulantes(), vehiculo, nombre);


                    myAsignments.set(posdata, setvehicle);//se tiene que reconstruir el modelo de myasigments a tipi de valor correspondient

                    presenter.auditTrail("Zona: "+cveLayerName+" agrego el vehiculo: "+myAsignments.get(posdata).getVehicleName()+"|"+myAsignments.get(posdata).getCveVehicle());
                    presenter.updateAsignments(myAsignments);


                    restartActivity();
    } else/**revisar data**/ {
                    Log.e("tripuFlow5", "tripúlantes no null");

                    List<Tripulante> nuevetripulacion = new ArrayList<>();
                    nuevetripulacion.clear();
                    List<Integer> tripulantesListEndpointu = new ArrayList<>();
                    tripulantesListEndpointu.clear();
                    int cveD = 0;
                    int cveV = 0;
                    /**considerar en el item 0 y en un nuevo item*/
                    Log.e("tripuFlow3", "calcula la data  posicion de item " + posdata + " Nombre " + nombre + "  Vehiculo " + vehiculo + " Tripulantes " + tripulantes);
                    Log.e("tripuFlow6", "data de tripulantes despued de editar " + tripulantes + "   " + tripulantes.size());
                    for (int i = 0; i < D.size(); i++)//itero sobre todos los nombres de conductores
                    {
                        String[] parts = D.get(i).split("/");//parts[0] => nombre part[1]=>cve
                        Log.e("tripuFlow3", "" + D.get(i));
                        if (parts[0].equals(nombre))///si el nombre existe en la lista de conductores y es igual a Nombre
                        {
                            cveD = Integer.valueOf(parts[1]);//
                        }

                        for (int l = 0; l < tripulantes.size(); l++)// para cada iteracion por conductor verifico si el tripulante existe para agregar el cve a la lista tripulantesListEndpointu
                        {
                            if (parts[0].equals(tripulantes.get(l))) {
                                Log.e("tripuFlow6", " " + parts[1] + "," + tripulantes.get(l));
                                Tripulante agregatripulante = new Tripulante(Integer.valueOf(parts[1]), tripulantes.get(l));
                                tripulantesListEndpointu.add(Integer.valueOf(parts[1]));
                                nuevetripulacion.add(agregatripulante);

                            }
                        }
                    }
                    for (int i = 0; i < V.size(); i++)//
                    {
                        String[] parts = V.get(i).split("/");
                        Log.e("tripuFlow3", "" + V.get(i));
                        if (parts[0].equals(vehiculo))///si el nombre existe en la lista de conductores y es igual a Nombre
                        {
                            Log.e("tripuFlow5", "salio bien " + vehiculo + "   cve " + parts[1]);
                            cveV = Integer.valueOf(parts[1]);//
                        } else {
                            Log.e("tripuFlow5", "esta aqui verifica que salio mal  ve: " + vehiculo + "  parts " + parts[1]);
                        }
                    }
                    Log.e("tripuFlow3", "cveVehicle: " + cveV + " cveDriver: " + cveD + "  tripulantes " + nuevetripulacion.size() + "   vehiculo: " + vehiculo + "  nombre: " + nombre);//esto tiene que ser un set de la posicion
                    /**la variable que hace update es diferente a la que hace get*/
                    // List<VehicleDriver> fullArrayD=new ArrayList<>();
                    //fullArrayD.clear();
                    Log.e("tripuFlow6", "nueva tripulacion N: " + nuevetripulacion.size() + " nueva tripulacion " + nuevetripulacion.get(0).getTripulanteName());
                    VehicleDriver setvehicle = new VehicleDriver(cveV, cveD, nuevetripulacion, vehiculo, nombre);
                    //  nuevetripulacion.clear();


                    myAsignments.set(posdata, setvehicle);//se tiene que reconstruir el modelo de myasigments a tipi de valor correspondient

                    for (int i = 0; i < myAsignments.size(); i++) {
                        Log.e("tripuFlow6", "" + myAsignments.get(i).getTripulantes().size());
                    }
                    presenter.auditTrail("Zona: "+cveLayerName+" agrego el vehiculo: "+myAsignments.get(posdata).getVehicleName()+"|"+myAsignments.get(posdata).getCveVehicle());
                    presenter.updateAsignments(myAsignments);
                    restartActivity();

                }



          //endregion
            /**FIN NEW ITEM**/
        }else if(isnew==false) {
            int dimensionArray = myAsignments.size() - 1;
            Log.e("tripuFlow7", "validar si es nuevo F" + isnew);
            /**validar datos para item nuevo*/
            if (tripulantes == null) {
                Log.e("tripuFlow5", "tripúlantes null  posicion  " + posdata + " N " + nombre + "  V " + vehiculo + " T " + tripulantes);

                List<Tripulante> nuevetripulacion = new ArrayList<>();
                nuevetripulacion.clear();
                List<Integer> tripulantesListEndpointu = new ArrayList<>();
                tripulantesListEndpointu.clear();
                int cveD = 0;
                int cveV = 0;
                /**considerar en el item 0 y en un nuevo item*/
                Log.e("tripuFlow3", "calcula la data  posicion de item " + posdata + " Nombre " + nombre + "  Vehiculo " + vehiculo + " Tripulantes " + tripulantes);
                Log.e("tripuFlow6", "data de tripulantes despued de editar en null ");
                for (int i = 0; i < D.size(); i++)//itero sobre todos los nombres de conductores
                {
                    String[] parts = D.get(i).split("/");//parts[0] => nombre part[1]=>cve
                    Log.e("tripuFlow3", "" + D.get(i));
                    if (parts[0].equals(nombre))///si el nombre existe en la lista de conductores y es igual a Nombre
                    {
                        cveD = Integer.valueOf(parts[1]);//
                    }

//                for(int l=0;l<tripulantes.size();l++)// para cada iteracion por conductor verifico si el tripulante existe para agregar el cve a la lista tripulantesListEndpointu
//                {
//                    if(parts[0].contains(tripulantes.get(l)))
//                    {
//                        Log.e("tripuFlow4"," "+parts[1]+","+tripulantes.get(l));
//                        Tripulante agregatripulante= new Tripulante(Integer.valueOf( parts[1]),tripulantes.get(l));
//                        tripulantesListEndpointu.add(Integer.valueOf(parts[1]));
//                        nuevetripulacion.add(agregatripulante);
//
//                    }
//                }
                }
                for (int i = 0; i < V.size(); i++)//
                {
                    String[] parts = V.get(i).split("/");
                    Log.e("tripuFlow5", "" + V.get(i));
                    if (parts[0].equals(vehiculo))///si el nombre existe en la lista de conductores y es igual a Nombre
                    {
                        Log.e("tripuFlow5", "salio bien " + vehiculo + "   cve " + parts[1]);
                        cveV = Integer.valueOf(parts[1]);//
                    } else {
                        Log.e("tripuFlow5", "esta aqui verifica que salio mal en tripulantes con null " + vehiculo + "   cve " + parts[1]);
                    }
                }
                Log.e("tripuFlow3", "cveVehicle: " + cveV + " cveDriver: " + cveD + "  tripulantes " + nuevetripulacion.size() + "   vehiculo: " + vehiculo + "  nombre: " + nombre);//esto tiene que ser un set de la posicion
                /**la variable que hace update es diferente a la que hace get*/
                // List<VehicleDriver> fullArrayD=new ArrayList<>();
                //fullArrayD.clear();
                Log.e("tripuFlow6", "tripulacion anterior N: " + myAsignments.get(posdata).getTripulantes().size());
                VehicleDriver setvehicle = new VehicleDriver(cveV, cveD, myAsignments.get(posdata).getTripulantes(), vehiculo, nombre);


                myAsignments.set(posdata, setvehicle);//se tiene que reconstruir el modelo de myasigments a tipi de valor correspondient


                presenter.updateAsignments(myAsignments);


                restartActivity();
            } else/**revisar data**/ {
                Log.e("tripuFlow5", "tripúlantes no null");

                List<Tripulante> nuevetripulacion = new ArrayList<>();
                nuevetripulacion.clear();
                List<Integer> tripulantesListEndpointu = new ArrayList<>();
                tripulantesListEndpointu.clear();
                int cveD = 0;
                int cveV = 0;
                /**considerar en el item 0 y en un nuevo item*/
                Log.e("tripuFlow3", "calcula la data  posicion de item " + posdata + " Nombre " + nombre + "  Vehiculo " + vehiculo + " Tripulantes " + tripulantes);
                Log.e("tripuFlow6", "data de tripulantes despued de editar " + tripulantes + "   " + tripulantes.size());
                for (int i = 0; i < D.size(); i++)//itero sobre todos los nombres de conductores
                {
                    String[] parts = D.get(i).split("/");//parts[0] => nombre part[1]=>cve
                    Log.e("tripuFlow3", "" + D.get(i));
                    if (parts[0].equals(nombre))///si el nombre existe en la lista de conductores y es igual a Nombre
                    {
                        cveD = Integer.valueOf(parts[1]);//
                    }

                    for (int l = 0; l < tripulantes.size(); l++)// para cada iteracion por conductor verifico si el tripulante existe para agregar el cve a la lista tripulantesListEndpointu
                    {
                        if (parts[0].equals(tripulantes.get(l))) {

                            Log.e("tripuFlow23", " " + parts[1] + "," + tripulantes.get(l));

                            Tripulante agregatripulante = new Tripulante(Integer.valueOf(parts[1]), tripulantes.get(l));
                            tripulantesListEndpointu.add(Integer.valueOf(parts[1]));
                            nuevetripulacion.add(agregatripulante);

                        }
                    }
                }
                for (int i = 0; i < V.size(); i++)//
                {
                    String[] parts = V.get(i).split("/");
                    Log.e("tripuFlow3", "" + V.get(i));
                    if (parts[0].equals(vehiculo))///si el nombre existe en la lista de conductores y es igual a Nombre
                    {
                        Log.e("tripuFlow23", "salio bien " + vehiculo + "   cve " + parts[1]);
                        cveV = Integer.valueOf(parts[1]);//
                    } else {
                        Log.e("tripuFlow23", "esta aqui verifica que salio mal  ve: " + vehiculo + "  parts " + parts[1]);
                    }
                }
                Log.e("tripuFlow23", "cveVehicle: " + cveV + " cveDriver: " + cveD + "  tripulantes " + nuevetripulacion.size() + "   vehiculo: " + vehiculo + "  nombre: " + nombre);//esto tiene que ser un set de la posicion
                /**la variable que hace update es diferente a la que hace get*/
                // List<VehicleDriver> fullArrayD=new ArrayList<>();
                //fullArrayD.clear();
//                Log.e("tripuFlow6", "nueva tripulacion N: " + nuevetripulacion.size() + " nueva tripulacion " + nuevetripulacion.get(0).getTripulanteName());
                VehicleDriver setvehicle = new VehicleDriver(cveV, cveD, nuevetripulacion, vehiculo, nombre);
                //  nuevetripulacion.clear();

                Log.e("tripuFlow23","post camino vacio");
                myAsignments.set(posdata, setvehicle);//se tiene que reconstruir el modelo de myasigments a tipi de valor correspondient

                for (int i = 0; i < myAsignments.size(); i++) {
                    Log.e("tripuFlow23", i +"   full data to Request   "  + myAsignments.get(i).getTripulantes().size()+"   "+
                            "   vehicle:"+myAsignments.get(i).getCveVehicle()+
                            "   driver:"+myAsignments.get(i).getCveDriver()+
                            "   tripulantes"+myAsignments.get(i).getTripulantes());
                    if(myAsignments.get(i).getTripulantes().size()==0)
                    {
                        Tripulante agregatripulante = new Tripulante(0, "string");
                        myAsignments.get(i).getTripulantes().add(agregatripulante);
                        Log.e("tripuFlow23","tripulante vacio "+myAsignments.get(i).getTripulantes()+" cve "+myAsignments.get(i).getTripulantes().get(0).getCveTripulante()+" N:"+myAsignments.get(i).getTripulantes().get(0).getTripulanteName());
                    }
                    else
                    {
                        for( int k=0;k<myAsignments.get(i).getTripulantes().size();k++)
                        {
                            Log.e("tripuFlow23"," cve: "+ myAsignments.get(i).getTripulantes().get(k).getCveTripulante()+"  name: "+myAsignments.get(i).getTripulantes().get(k).getTripulanteName());
                        }
                    }
                }

                presenter.updateAsignments(myAsignments);


            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, zonesRecyclerView.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        }

    //region metodos sin usar
    private void outputData()
    {
        for (int j = 0; j < myAsignments.size(); j++) {

            Log.e("tripulantesnewFLOW","myAsignments View"+
                    "   vehicle:"+myAsignments.get(j).getCveVehicle()+
                    "   driver:"+myAsignments.get(j).getCveDriver()+
                    "   tripulantes"+myAsignments.get(j).getTripulantes());
        }
    }

    public void performClickforRV()
    {
        rv.findViewHolderForAdapterPosition(itemtoperformClick).itemView.findViewById(R.id.editAsignment).performClick();
    }

    public void performbyRV(int position) {
        this.itemtoperformClick=position;
    }


    public void  chekcinAsigments()
    {
//        if(myvehicles==null||myvehicles.isEmpty())
//        {Log.e("hiddenButtons"," show bu");
//            textEmpty1.setVisibility(View.VISIBLE);
//            textEmpty2.setVisibility(View.VISIBLE);
//            pencil.setVisibility(View.VISIBLE);
//            penciltext.setVisibility(View.VISIBLE);
//            barEditeErase.setVisibility(View.GONE);
//
//            rv.setVisibility(View.GONE);
//
//        }else
//        {
//
//            Log.e("hiddenButtons"," hiden bu");
//            textEmpty1.setVisibility(View.GONE);//boton de sin asignaciones
//            textEmpty2.setVisibility(View.GONE);
//            textnumselected.setVisibility(View.GONE);
//            crossAction.setVisibility(View.GONE);
//            barEditeErase.setVisibility(View.GONE);
//            back.setVisibility(View.VISIBLE);
//            zoneName.setVisibility(View.VISIBLE);
//
//
//            rv.setVisibility(View.VISIBLE);
//
//
//        }
        fillAdapter();
    }



    public void orderErase()
    {

        if(indexes.size()==myvehicles.size())
        {

        }
        //      clearIndexes();
        back.setVisibility(View.VISIBLE);
        zoneName.setVisibility(View.VISIBLE);
        crossAction.setVisibility(View.GONE);
        yellowB.setVisibility(View.GONE);
        circleB.setVisibility(View.GONE);
        texttodo.setVisibility(View.GONE);
        textnumselected.setVisibility(View.GONE);

        //  trashCan.setVisibility(View.GONE);
        routesSearch.setVisibility(View.VISIBLE);
    }
    //endregion
    /**metodo que elimina el item siempre  cuando venga en 0 el cve del vehiculo*/
    public void eraseCeroitem(int position) {
        List<VehicleDriver> newList=new ArrayList<>();
        newList=myAsignments;
        newList.remove(position);
        myAsignments=newList;
        fillAdapter();
    }
    public void hideadapterdilogTripulantes()
    {
        DialogAdaptertripulantes.setVisibility(View.GONE);
    }
    public void showDialogTripulantes(int cardlocation, List<String> newTripulantes)/**este metodo muestra la posicion de la tarjeta modificada de tripulantes*/
    {
        this.dialogtripulantes=cardlocation;
        Log.e("tripulantes",""+cardlocation);
        //  presenter.requestUnitsCatalog();
        DialogAdaptertripulantes.setVisibility(View.VISIBLE);
        Log.e("tripuFlow2","tripulantes AFTER SAVE "+ newTripulantes);
        List<String> tripulantestCheckbox=new ArrayList<>();
        if(newTripulantes!=null)
        {
         tripulantestCheckbox=newTripulantes;
        }else {
            for (int i = 0; i < myAsignments.get(cardlocation).getTripulantes().size(); i++) {
                tripulantestCheckbox.add(myAsignments.get(cardlocation).getTripulantes().get(i).getTripulanteName());
            }
        }
        fillDataDialogTripulantes(tripulantestCheckbox);

    }
    public void tripulantestcheckBox(List<String> tripulantest)
    {
        this.newtripulantesList=tripulantest;
      //  if(!newtripulantesList.isEmpty()) {
            adapter.notifyDataSetChanged();
       // }

    }

@Override
public void onClick(View v) {
    switch (v.getId()) {
        case R.id.backasignvehicle:/** Boton atras encabezado*/
            //super.onBackPressed();
            Intent intent = new Intent(this, zonesRecyclerView.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            indexes.clear();
            break;
        case R.id.routesSearch:/** Filtro de busqueda*/
            if(fieldforSeach.getVisibility()==View.GONE)
            {
                fieldforSeach.setVisibility(View.VISIBLE);
               // rv.smoothScrollToPosition(myAsignments.size()-1);/** ESTA LINEA regresa a la arte inicial*/
            }else
            {
                fieldforSeach.setVisibility(View.GONE);
            }
            break;
        case R.id.constrintohide:        /** constrain obscuro que oculta el  constrain de tripulantes*/
            DialogAdaptertripulantes.setVisibility(View.GONE);
            break;
        case R.id.textViewTODO:
            adaterTripulacion.selectAll();
            break;
        case R.id.txtViewClean:
            adaterTripulacion.unselectall();
            break;
        case R.id.guardarDialogTripulantes:
            if(newtripulantesList!=null) {
            Log.e("tripuFlow2",""+dialogtripulantes+"   "+newtripulantesList+"    lista de tripulantes anterior:  "+myAsignments.get(dialogtripulantes).getTripulantes().size());

                Log.e("tripuFlow6", " numero de tripulantes " + newtripulantesList.size());
            }
            DialogAdaptertripulantes.setVisibility(View.GONE);
            adapter.setDataholdertripulantes(dialogtripulantes,newtripulantesList);
            DialogAdaptertripulantes.setVisibility(View.GONE);
//            List<Tripulante> Ntp=new ArrayList<>();
//            for(int i=0;i<newtripulantesList.size();i++)
//            {
//                Tripulante ntrp=new Tripulante(0,newtripulantesList.get(i));
//                Ntp.add(ntrp);
//            }
//
//            VehicleDriver vehicleDriver=new VehicleDriver(myAsignments.get(dialogtripulantes).getCveVehicle(),myAsignments.get(dialogtripulantes).getCveDriver(),Ntp,myAsignments.get(dialogtripulantes).getVehicleName(),myAsignments.get(dialogtripulantes).getDriverName());
//            myAsignments.set(dialogtripulantes,vehicleDriver);
//
//            fillAdapter();

            break;
        case R.id. additemAsignment:/**boton  de agregar iten  nuevo   REVISAR CUANDO ESTQA EN 0*/
            int cveVehicle=0;
            int cveDriver=0;
            List<Tripulante> tripulantes=new ArrayList<>();
            //Tripulante tp=new Tripulante(0,"");
            tripulantes.clear();
            if(myAsignments!=null)
            {
                Log.e("tripuFlow9","not null");
                myAsignments.add(new VehicleDriver(cveVehicle,cveDriver,tripulantes,"Selecciona un vehículo","Selecciona un conductor"));
                fillAdapter();
                rv.smoothScrollToPosition(myAsignments.size() - 1);}
            else{
                Log.e("tripuFlow9","null");
                List<VehicleDriver> newAsigment=new ArrayList<>();
                myAsignments=newAsigment;
                myAsignments.add(new VehicleDriver(cveVehicle,cveDriver,tripulantes,"Selecciona un vehículo","Selecciona un conductor"));
                fillAdapter();
                textEmpty1.setVisibility(View.GONE);
                textEmpty2.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
            }


            break;
    }
}
}

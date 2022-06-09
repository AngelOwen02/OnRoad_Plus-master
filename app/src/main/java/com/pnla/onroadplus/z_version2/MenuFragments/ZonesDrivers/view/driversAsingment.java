package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.view;
//com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.view.driversAsingment
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.adapter.adapTirpulantes;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.VhicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.VehicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.adapter.zonesAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.adapter.dirverAsingmenAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.vehicleZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.presenter.driverAsignmentPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.presenter.driverAsignmentPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view.zonesRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class driversAsingment  extends AppCompatActivity  implements View.OnClickListener,driverAsignmentView {

    private TextView zoneName,textampty1,textempty2,texttodo,textnumselected,penciltext,checkAllTripulantes,uncheckAllTripulantes,GuardarDialogTripulantes;
    private String nameZ;
    private ProgressDialog progressDialog;
    private driverAsignmentPresenter presenter;
    private List<String> asingmentdata;
    private RecyclerView rv, rvDialog;
    private dirverAsingmenAdapter adapter;
    //private adapterTripulantes adaterTripulacion;
    private ImageView addAsignment,trashCan,newtrascan,routesSearch,back ,yellowB,circleB,selectE,crossAction,pencil;
    private  List<String> indexToErase=new ArrayList<>();
    private CardView fieldforSeach;
    private SearchView searchViewa,searchViewT;
    private int itemsAdap;
    public static List<String> indexes=new ArrayList<>();
    private alerDialog alert;
    private ConstraintLayout barEditeErase   ,DialogAdaptertripulantes,backgroundDialogTripulante;
    private  List<String> mydriversCatalog=new ArrayList<>();
    private List<VhicleDriver> myAsignments;
    private   List<String> nombresTripulantesresid=new ArrayList<>();
    private adapTirpulantes adaterTripulacion ;
    private List<String> Flist=new ArrayList<>();
    private List<String> newData=new ArrayList<>();
    private List<String> allnewData=new ArrayList<>();
    private List<String> allnewDataR=new ArrayList<>();
    private int cardLocation;
    private List<Integer> eraseitems=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignvehicle);
        initView();

    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        back=findViewById(R.id.backasignvehicle);
        addAsignment=findViewById(R.id.additemAsignment);
        textampty1=findViewById(R.id.textViewAsignVehicle1);
        textempty2=findViewById(R.id.textViewVehicleAsign2);
        zoneName=findViewById(R.id.routeName);
        rv=findViewById(R.id.recyclerViewAsignVehicle);

        nameZ=zonesAdapter.zoneName;
        zoneName.setText(nameZ);
        barEditeErase=findViewById(R.id.constrainbareditErase);
        /**aqui va la ui de tripulantes*/
        DialogAdaptertripulantes=findViewById(R.id.backgrounddialogAsigments);
        backgroundDialogTripulante=findViewById(R.id.constrintohide);//backgrounddialogAsigments);
        backgroundDialogTripulante.setOnClickListener(this);

        rvDialog=findViewById(R.id.rvtripulantes);
        checkAllTripulantes=findViewById(R.id.textViewTODO);
        checkAllTripulantes.setOnClickListener(this);

        uncheckAllTripulantes=findViewById(R.id.txtViewClean);
        uncheckAllTripulantes.setOnClickListener(this);

        GuardarDialogTripulantes=findViewById(R.id.guardarDialogTripulantes);
        GuardarDialogTripulantes.setOnClickListener(this);

        /***/
        yellowB =findViewById(R.id.yellowtocircle);
        circleB=findViewById(R.id.circletoyellow);
        texttodo =findViewById(R.id.todotext);
        textnumselected=findViewById(R.id.selectedItems);
        indexes.clear();
        circleB.setOnClickListener(this);//meterlo al case

        selectE=findViewById(R.id.selecteverything);
        selectE.setOnClickListener(this);

        crossAction=findViewById(R.id.crosscancel);
        crossAction.setOnClickListener(this);
        /***/


        routesSearch=findViewById(R.id.routesSearch);/**    esto es el filtro*/
        fieldforSeach=findViewById(R.id.searchField);
        searchViewa= (SearchView) this.findViewById(R.id.searchViewRoutes);
        searchViewa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<String> newAsig=filteredAsignments(asingmentdata,newText);
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
                List<String> newTripulantes=filterTripulantes(mydriversCatalog,newText);
                adaterTripulacion.setFilter(newTripulantes);
                return false;
            }
        });

        //filterTripulantes

        pencil=findViewById(R.id.editpencil);
        penciltext=findViewById(R.id.penciltext);
        trashCan=findViewById(R.id.trashcan);
        trashCan.setOnClickListener(this);
        newtrascan=findViewById(R.id.newtrash);
        newtrascan.setOnClickListener(this);

        pencil.setOnClickListener(this);
        routesSearch.setOnClickListener(this);
        back.setOnClickListener(this);
        String cvelayer = String.valueOf(zonesAdapter.cveLayerZone);//getIntent().getStringExtra("cveAsignments");
        Log.e("asignment","¡IMPORTANTE ! cvelayer :  "+cvelayer);
        presenter= new driverAsignmentPresenterImpl(this,getApplicationContext());

        presenter.requestAsignment(cvelayer);
        presenter.requestTripulantes();
        presenter.requestUnitsCatalog();
        presenter.requesDriverCatalog();
        addAsignment.setOnClickListener(this);

    }


/**   CLASE INICIAL DONDE SE LLENA TODA LA DATA*/
    @Override
    public void chekcAsignments(List<String> asingment) {
        this.asingmentdata=asingment;

        chekcinAsigments();
//        if(dirverAsingmenAdapter.mirrorData.size()!=asingment.size())
//        {
//            Log.e("asignment","se ha agregado un item"+dirverAsingmenAdapter.mirrorData.size());
//        }
       // fillAdapter();

    }
    @Override
    public void getdivers(List<String> driversCatalog) {
       this.mydriversCatalog=driversCatalog;
        Log.e("tripulantes","tripulantes :  "+mydriversCatalog );


    }

    @Override
    public void setTripulantes(List<VhicleDriver> myAsignments) {
        this.myAsignments=myAsignments;
        Log.e("finalUpdate","size "+ myAsignments.size());


    }

    private void fillAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
        rv.setLayoutManager(layoutManager);
        checkDataAsigment();
        adapter=new  dirverAsingmenAdapter(this,asingmentdata,nombresTripulantesresid,getApplicationContext());
        rv.setAdapter(adapter);


    }
    private void fillDataDialogTripulantes(List<String> list)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvDialog.setLayoutManager(layoutManager);

        adaterTripulacion=new adapTirpulantes(this, mydriversCatalog,list,getApplicationContext() );
        rvDialog.setAdapter(adaterTripulacion);
    }
    private  void checkDataAsigment()
    {
        if(myAsignments!=null) {


            nombresTripulantesresid.clear();
            for (int j = 0; j < myAsignments.size(); j++) {

                Log.e("tripulantesnewFLOW", "myAsignments View" +
                        "   vehicle:" + myAsignments.get(j).getCveVehicle() +
                        "   driver:" + myAsignments.get(j).getCveDriver() +
                        "   tripulantes" + myAsignments.get(j).getTripulantes());
                if (myAsignments.get(j).getTripulantes().size() != 0) {
                    List<String> array = new ArrayList<>();
                    array.clear();
                    for (int i = 0; i < myAsignments.get(j).getTripulantes().size(); i++) {
                     //   array.add(String.valueOf(myAsignments.get(j).getTripulantes().get(i)));
                            if( mydriversCatalog.size()!=0) {
                                for (int k = 0; k < mydriversCatalog.size(); k++) {
                                //    Log.e("tripulantesnewFLOW", "dirvers :  " + mydriversCatalog.get(k));
                                    if(!String.valueOf(myAsignments.get(j).getTripulantes().get(i)).equals("0")&& mydriversCatalog.get(k).contains(String.valueOf(myAsignments.get(j).getTripulantes().get(i))))
                                    {
//                                        String[] parts=mydriversCatalog.get(k).split("/");
//                                        array.add(parts[0]);
                                       array .add(mydriversCatalog.get(k));
                                    }
                                }
                            }else{


                            }

                    }
                    nombresTripulantesresid.add(String.valueOf(array));
                }
            }
            Log.e("tripulantesnewFLOW", "EXTRAYENDO LISTA :  " + nombresTripulantesresid);


        }
    }
    public void  chekcinAsigments()
    {
        if(asingmentdata==null||asingmentdata.isEmpty())
        {Log.e("hiddenButtons"," show bu");
            textampty1.setVisibility(View.VISIBLE);
            textempty2.setVisibility(View.VISIBLE);
            pencil.setVisibility(View.VISIBLE);
            penciltext.setVisibility(View.VISIBLE);
            barEditeErase.setVisibility(View.GONE);

            rv.setVisibility(View.GONE);

        }else
        {

            Log.e("hiddenButtons"," hiden bu");
            textampty1.setVisibility(View.GONE);//boton de sin asignaciones
            textempty2.setVisibility(View.GONE);
            textnumselected.setVisibility(View.GONE);
            crossAction.setVisibility(View.GONE);
            barEditeErase.setVisibility(View.GONE);
            back.setVisibility(View.VISIBLE);
            zoneName.setVisibility(View.VISIBLE);


            rv.setVisibility(View.VISIBLE);


        }
        fillAdapter();
    }
    private List<String> filteredAsignments(List<String> data,String text)
    {
        List<String> asignments=new ArrayList<>();
        asignments.clear();
        text=text.toLowerCase();
        if(data!=null)
        {
            if(asingmentdata!=null) {
                for (int i = 0; i < asingmentdata.size(); i++) {
                    String v = asingmentdata.get(i).toLowerCase();
                    if (v.contains(text)) {
                        asignments.add(v.toUpperCase());
                    }
                }
            }

        }
        return asignments;
    }

    private List<String> filterTripulantes(List<String> mydriversC ,String text)
    {
        List<String> tripulantes=new ArrayList<>();
        tripulantes.clear();
        text=text.toLowerCase();
        if(mydriversC!=null)
        {
            if(mydriversCatalog!=null){
                for(int i=0;i<mydriversCatalog.size();i++)
                {
                    String v=mydriversCatalog.get(i).toLowerCase();
                    if(v.contains(text))
                    {
                        tripulantes.add(v);
                    }
                }
            }

        }
        return tripulantes;
    }
    public void agimenfullItems(List<String> info)
    {
    //    Log.e("checkEmpty","+"+info);
        List<vehicleZones> zones=new ArrayList<>();
        List<vehicleZones> mirror=new ArrayList<>();
        zones.clear();
       // Log.e("viewtoReuquestA",""+ info+ "     layer "+zonesAdapter.cveLayerZone);
        if(info!=null)
        {

            for (int i=0;i<info.size();i++)
            {
                String part=info.get(i);

                String[] sections=part.split(",");

                    if (sections[2] != null && sections[3] != null ) {/**aqui hay que validar el null he intercambiarlo por 0 en los cves*/
                      if(sections[2].equals("f"))
                      {

                      }else {
                          Log.e("nullsections", "  cveDriver: " + sections[2] + "  cveVehicle:  " + sections[3]);
                          vehicleZones data = new vehicleZones(Integer.valueOf(sections[2]), Integer.valueOf(sections[3]));
                          zones.add(data);                                                                                                      //                    Log.e("viewtoReuquestA", "zonas  " + part);//                    Log.e("viewtoReuquestA", "zonas  " + sections[2] + "  " + sections[3]);//                    Log.e("viewtoReuquestA", "zonas  " + data);
                      }
                    }

            }



              presenter.updateAsignments(zones);
            //region DataNEWUPDATE
            if(allnewData.isEmpty())
            {
                //region salvarsinmodificar
                List<Integer> lastripulantes=new ArrayList<>();
                List<VehicleDriver> vehicleDrivers=new ArrayList<>();
                if(myAsignments.size()==asingmentdata.size())
                {

                    for(int k=0;k<myAsignments.size();k++)
                    {
                        String[] parts=asingmentdata.get(k).split(",");
                        Log.e("finalUpdate","  cveDriver "+ parts[3]+"  cveVehicle   "+parts[2]);
                        VehicleDriver vd=new VehicleDriver(Integer.valueOf(parts[3]),Integer.valueOf(parts[2]),myAsignments.get(k).getTripulantes());
                        Log.e("UpdateNewEndpointNOMOD","list to endpoint new Update :"+ " cveDriver: "+Integer.valueOf(parts[3])+"  cveVehicle: "+parts[2]+ " List<Integer> tripulantes:  "+myAsignments.get(k).getTripulantes());
                        vehicleDrivers.add(vd);
                    }

                }
//                                for (int j = 0; j < myAsignments.size(); j++) {
//                                    Log.e("finalUpdate","  "+myAsignments.size());
//                                  //  lastripulantes.addAll(myAsignments.get(j).getTripulantes());
//                                    Log.e("finalUpdate","  "+myAsignments.get(j).getTripulantes());
//
//                                }
//                                Log.e("finalUpdate","  asignmentdata "+asingmentdata);

                Log.e("UpdateNewEndpointNOMOD","list to endpoint new Update :"+vehicleDrivers);/** esto va al presenter y al nuevo endpoint*/
                presenter.newUPdate(vehicleDrivers);
                //endregion
            }
            else
            {
                List<String> outboundsFic=new ArrayList<>();
                for(int l=0;l<allnewData.size();l++)
                {
                    if(!allnewData.get(l).equals(""))
                    {
                        outboundsFic.add(allnewData.get(l));
                    }

                }
                if(outboundsFic!=null&&!outboundsFic.isEmpty())
                {
                    allnewData=outboundsFic;
                }
                //region salvarconnuevadata
                Log.e("UpdateNewEndpoint","  asignmentdata "+asingmentdata+ "    "+allnewData+ " position to replace "+ cardLocation);//cardLocation
                List<Integer> newTripulantsList=new ArrayList<>();
                newTripulantsList.clear();
                for(int l=0;l<allnewData.size();l++)
                {
                    Log.e("outboundsExc", " " + allnewData.get(l));
                    if(!allnewData.get(l).equals("")||!allnewData.get(l).isEmpty()||!allnewData.get(l).contains("/")||allnewData.get(l)!=null||!allnewData.get(l).equals(" ")) {
                    String[] parts = allnewData.get(l).split("/");
                    Log.e("errorCheckmariana", " " + parts[l]);
                    newTripulantsList.add(Integer.valueOf(parts[1]));
                    }
                }
                myAsignments.get(cardLocation).setTripulantes(newTripulantsList);
                for (int j = 0; j < myAsignments.size(); j++) {/** esto solo extra los tripulantes*/
                    Log.e("finalUpdate","  "+myAsignments.size());
                    //  lastripulantes.addAll(myAsignments.get(j).getTripulantes());
                    Log.e("UpdateNewEndpoint","  "+myAsignments.get(j).getTripulantes());

                }
                Log.e("finalUpdate","  asignmentdata "+asingmentdata);


                List<Integer> lastripulantes=new ArrayList<>();
                List<VehicleDriver> vehicleDrivers=new ArrayList<>();
                if(myAsignments.size()==asingmentdata.size())
                {

                    for(int k=0;k<myAsignments.size();k++)
                    {
                        String[] parts=asingmentdata.get(k).split(",");
                        Log.e("finalUpdate","  cveDriver "+ parts[3]+"  cveVehicle   "+parts[2]);
                        VehicleDriver vd=new VehicleDriver(Integer.valueOf(parts[3]),Integer.valueOf(parts[2]),myAsignments.get(k).getTripulantes());
                        Log.e("UpdateNewEndpoint","list to endpoint new Update :"+ " cveDriver: "+Integer.valueOf(parts[3])+"  cveVehicle: "+parts[2]+ " List<Integer> tripulantes:  "+myAsignments.get(k).getTripulantes());
                        vehicleDrivers.add(vd);
                    }

                }

                Log.e("UpdateNewEndpoint","list to endpoint new Update :"+vehicleDrivers);/** esto va al presenter y al nuevo endpoint*/
                presenter.newUPdate(vehicleDrivers);
                //endregion
            }

            // Log.e("lastUpdate","allzones   +"+zones+"  asignmentdata "+asingmentdata);
            //region testdatosdeasignacion
            for(int i=0;i<asingmentdata.size();i++)
            {
                String[] parts=asingmentdata.get(i).split(",");
                Log.e("finalUpdate","  cveDriver "+ parts[3]+"  cveVehicle   "+parts[2]);
            }
            //endregion

//endregion DataNEWUPDATE

              chekcinAsigments();


        }else
        {
            vehicleZones data = new vehicleZones(0,0);
            zones.add(data);
            Log.e("checkEmpty","-"+zones);
            presenter.updateAsignments(zones);
            chekcinAsigments();
        }
        if(asingmentdata!=null||asingmentdata.size()!=0||asingmentdata.size()!=1)
        {rv.smoothScrollToPosition(asingmentdata.size()-1);}
    }
    public void showDialogTripulantes(int cardlocation)/**este metodo muestra la posicion de la tarjeta modificada de tripulantes*/
    {
        this.cardLocation=cardlocation;
        Log.e("prechekc","cardlocation:   "+cardlocation );
      //  presenter.requestUnitsCatalog();
        DialogAdaptertripulantes.setVisibility(View.VISIBLE);
        String s = nombresTripulantesresid.get(cardlocation);
        Log.e("prechekc","s:   "+s );
        if(s.contains(",")) {
            String[] strings = s.substring(0, s.length() - 1).split(",");
            for (int i = 0; i < strings.length; i++) {
                strings[i] = strings[i].substring(1);
                Log.e("prechekc","  tripulantes brakeys i1"+strings[i]);

            }
            Flist = Arrays.asList(strings);
        }else
        {

            String[] strings = s.substring(0).split(",");
            for (int i = 0; i < strings.length; i++) {
                if(strings[i].substring(1).equals("\\]"))
                {
                   strings[i]="";
                }
                    else{
                    strings[i] = strings[i].substring(1);

                }
                Log.e("tripulantesoverButton","  tripulantes brakeys i2"+strings[i]);
            }
            Log.e("prechekc","  tripulantes brakeys i2s"+s);
        Flist = Arrays.asList(strings);
        }
        if(Flist.contains(" \\]"))
        {
            Flist.clear();
        }
        Log.e("prechekc","  tripulantes brakeys "+Flist);

        /** aqui deberiamos iterar sobre la lista de conuctores y obtener las posiciones y los conducoter coinciden con list*/

       fillDataDialogTripulantes(Flist);

    }
    public void newItemTripulantes(List<String> newData) {

        this.newData=newData;
    }

    public void setlistfromAdapterTripulantes( List<String> newlist)
    {
       // fillDataDialogTripulantes(newlist);
    }
    public void showtrashcan(List<String> positions,int items)
    {
        this.itemsAdap=items;
        this.indexToErase=positions;

        Log.e("indicatoritems"," total :  "+itemsAdap);
        if(!positions.isEmpty())
        {
       //     trashCan.setVisibility(View.VISIBLE);
            routesSearch.setVisibility(View.GONE);
        }
        else
        {
       //     trashCan.setVisibility(View.GONE);
            routesSearch.setVisibility(View.VISIBLE);
        }
        if(itemsAdap!=0)
        {
            pencil.setVisibility(View.VISIBLE);
            penciltext.setVisibility(View.VISIBLE);
            /**
             yellowB =findViewById(R.id.yellowtocircle);
             circleB=findViewById(R.id.circletoyellow);
             texttodo =findViewById(R.id.todotext);
             textnumselected=findViewById(R.id.selectedItems);
             zoneName
             back*/
            barEditeErase.setVisibility(View.VISIBLE);
            back.setVisibility(View.GONE);
            zoneName.setVisibility(View.GONE);
            //circleB.setVisibility(View.VISIBLE);
          //  texttodo.setVisibility(View.VISIBLE);
            crossAction.setVisibility(View.VISIBLE);
            textnumselected.setVisibility(View.VISIBLE);
            textnumselected.setText(itemsAdap+" Seleccionado (s)");
            if(itemsAdap==asingmentdata.size()&&asingmentdata.size()!=1)
            {/**aqui va la x que cancela todas las selecciones*/
             //   yellowB.setVisibility(View.VISIBLE);
                pencil.setVisibility(View.GONE);
                penciltext.setVisibility(View.GONE);
                circleB.setVisibility(View.GONE);
            }else if(itemsAdap>1)
            {
            pencil.setVisibility(View.GONE);
            penciltext.setVisibility(View.GONE);
            }
                else
            {

               // yellowB.setVisibility(View.GONE);
             // circleB.setVisibility(View.GONE);
            }
        }
        else
        {
            crossAction.setVisibility(View.GONE);
            barEditeErase.setVisibility(View.GONE);
            back.setVisibility(View.VISIBLE);
            zoneName.setVisibility(View.VISIBLE);

            yellowB.setVisibility(View.GONE);
            circleB.setVisibility(View.GONE);
         //  texttodo.setVisibility(View.GONE);
            textnumselected.setVisibility(View.GONE);
            fillAdapter();
        }

        Log.e("myvolante",""+asingmentdata.size());
    }
        public void clearIndexes()
        {

            Log.e("editEraseC","indextoErase "+indexToErase+"   asignmentdData: "+asingmentdata+" indexes: "   +indexes);

            if(!indexToErase.isEmpty()) {

                if(indexToErase.size()==asingmentdata.size()) {
                    indexToErase.clear();
                    asingmentdata.clear();
                    List<vehicleZones> zones = new ArrayList<>();
                    vehicleZones data = new vehicleZones(0, 0);
                    zones.add(data);
                    Log.e("checkEmpty", "-" + zones);
                    presenter.updateAsignments(zones);
                    chekcinAsigments();


                }else {

                    Log.e("editEraseC", "!empty " + indexToErase);

                    for (int i = 0; i < indexToErase.size(); i++) {
                        int index = Integer.valueOf(indexToErase.get(i));
                        //if (index < asingmentdata.size()) {
                        Log.e("editEraseC", "inde for " +index+"  size : "+asingmentdata.size());
                            asingmentdata.set(index,"f,f,f,f");
                       // }
                    }
                    Log.e("editEraseC", "----  " + asingmentdata);
                    indexes.clear();
                    List<String> cloneAsignment=new ArrayList<>();
                    cloneAsignment.clear();

                    for (int i = 0;i<asingmentdata.size();i++)
                    {
                        if(asingmentdata.get(i).contains("f,f,f,f"))
                        {
                           /// asingmentdata.remove(i);
                        }
                        else
                        {
                            cloneAsignment.add(asingmentdata.get(i));
                        }
                    }
                    Log.e("editEraseC", "finalll cloneAsignemnrt" + cloneAsignment );
                    asingmentdata=cloneAsignment;
                    Log.e("editEraseC", "----  " + asingmentdata);
                    if (asingmentdata.isEmpty())/** aqui se verifica si asigmentdata queda vacio*/ {
                        agimenfullItems(null);
                    } else {
                        agimenfullItems(asingmentdata);
                    }
                    fillAdapter();

                }

            }else
            { Log.e("editEraseC","empty "+indexToErase);
                agimenfullItems(null);
            }
        }
        public void orderErase()/** esta clase solo afecta la visibilidad de los items del menu superior*/
        {

            if(indexes.size()==asingmentdata.size())
            {

            }
            clearIndexes();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, zonesRecyclerView.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void customDialog() {
        /**ExternalGPSDialog externalGPSDialog = new ExternalGPSDialog();
         externalGPSDialog.setLocationVehicle(lat, lng);
         externalGPSDialog.show(getActivity().getSupportFragmentManager(), ExternalGPSDialog.TAG);*/
        alerDialog customdialog=new alerDialog(this,getApplicationContext());
        customdialog.show(this.getSupportFragmentManager(),alerDialog.TAG);
    }


    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando asignaciones");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }



    public void restoreCleanUI() {
    }

//    private List<visitedData1> filterZones(List<visitedData1> vData, String text){
//        List<visitedData1> visitedDataList = new ArrayList<>();
//        text =  text.toLowerCase();
//        if (vData != null){
//            for (visitedData1 zones : vData){
//                if (zones.getDescLayer() != null){
//                    String zoneName = zones.getDescLayer().toLowerCase();
//                    if (zoneName != null) {
//                        if (zoneName.contains(text)) {
//                            visitedDataList.add(zones);
//                        }
//                    }
//                }
//            }
//        }
//        return visitedDataList;
//    }
    public void deleteAlertDialog(final int position)
    {
        indexToErase.clear();
        Log.e("editEraseB","asingmentdatasize : "+ asingmentdata.size());
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
       // builder.setTitle("¿Deseas eliminar el elemento asignado?");
        builder.setMessage("¿Deseas eliminar el elemento asignado?");
        builder.setCancelable(true);
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if(asingmentdata.size()==1) {
                    indexToErase.clear();
                    asingmentdata.clear();
                    List<vehicleZones> zones=new ArrayList<>();
                    vehicleZones data = new vehicleZones(0,0);
                    zones.add(data);
                    Log.e("checkEmpty","-"+zones);
                    presenter.updateAsignments(zones);
                    chekcinAsigments();

                }else
                {
                    indexToErase.add(String.valueOf(position));
                    orderErase();
                }

                dialog.dismiss();

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

    private void anotherDialog()
    {
        indexToErase.clear();
        Log.e("editEraseB","asingmentdatasize : "+ asingmentdata.size());
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setMessage("¿Deseas eliminar el elemento asignado?");
        builder.setCancelable(true);
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(asingmentdata.size()==1) {
                    indexToErase.clear();
                    asingmentdata.clear();
                    List<vehicleZones> zones=new ArrayList<>();
                    vehicleZones data = new vehicleZones(0,0);
                    zones.add(data);
                    Log.e("checkEmpty","-"+zones);
                    presenter.updateAsignments(zones);
                    chekcinAsigments();

                }else
                {
                  //  indexToErase.add(String.valueOf(position));
                    orderErase();
                }

                dialog.dismiss();

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
    public void hideshowBarConstrain() {
        if(barEditeErase.getVisibility()==View.GONE)
        {
            barEditeErase.setVisibility(View.VISIBLE);
        }else
        {
            barEditeErase.setVisibility(View.GONE);
        }
    }


    public void indextoclear(List<Integer> indexl) {
        this.eraseitems=indexl;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id. additemAsignment:
                asingmentdata.add(" ,Selecciona un vehículo,0,0");
                int cveVehicle=0;
                int cveDriver=0;
                List<Integer> tripulantes=new ArrayList<>();
                tripulantes.add(0);
                myAsignments.add(new VhicleDriver(cveVehicle,cveDriver,tripulantes));
                chekcinAsigments();
                rv.smoothScrollToPosition(asingmentdata.size()-1);
                //fillAdapter();
                break;
            case R.id.newtrash:


                customDialog();

                break;
            case R.id.backasignvehicle:
                //super.onBackPressed();
                Intent intent = new Intent(this, zonesRecyclerView.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                indexes.clear();
                break;
            case R.id.routesSearch:/** hide show field for searchview*/
                if(fieldforSeach.getVisibility()==View.GONE)
                {
                    fieldforSeach.setVisibility(View.VISIBLE);
                }else
                {
                    fieldforSeach.setVisibility(View.GONE);
                }
                break;

            case R.id.crosscancel:
                indexes.clear();
                back.setVisibility(View.VISIBLE);
                routesSearch.setVisibility(View.VISIBLE);
                zoneName.setVisibility(View.VISIBLE);
                textnumselected.setVisibility(View.GONE);
                barEditeErase.setVisibility(View.GONE);
                crossAction.setVisibility(View.GONE);
                fillAdapter();
                rv.smoothScrollToPosition(asingmentdata.size()-1);
                break;

            case R.id.selecteverything://circletoyellow://selecteverything
                //  yellowB.setVisibility(View.VISIBLE);
                crossAction.setVisibility(View.VISIBLE);
                // circleB.setVisibility(View.GONE);
                indexes.clear();
                for (int i =0; i<asingmentdata.size();i++)
                {
                    indexes.add(String.valueOf(i));

                }
                fillAdapter();
                adapter.notifyDataSetChanged();
                Log.e("driindexes",""+ driversAsingment.indexes);
                break;
            case R.id.editpencil:
                Log.e("reditoedit",""+indexToErase);
                rv.findViewHolderForAdapterPosition(Integer.valueOf(indexToErase.get(0))).itemView.findViewById(R.id.editAsignment).performClick();
                break;

            case R.id.constrintohide:
                DialogAdaptertripulantes.setVisibility(View.GONE);
                break;
            case R.id.textViewTODO:
                adaterTripulacion.selectAll();
                break;
            case R.id.txtViewClean:
                adaterTripulacion.unselectall();
                break;
            case R.id.guardarDialogTripulantes:
                /**Aqui se necesida editar l textview del holder*/
//                if(!eraseitems.isEmpty())
//                {
//                    List<String> Flist2=new ArrayList<>();
//                    Flist2=Flist;
//                    for(int i=0;i<eraseitems.size();i++)
//                    {
//                        Flist2.set(eraseitems.get(i),"");
//                        Log.e("tripulantesoverButton","Lista F2          "+Flist2);
//                    }
//                    if(Flist2!=Flist)
//                    {   Flist.clear();
//                        for (int k=0;k<Flist2.size();k++)
//                        {
//                            if(Flist2.get(k).isEmpty())
//                            {
//                                Flist2.remove(k);
//                            }else
//                            {
//                                Flist.add(Flist2.get(k));
//                            }
//                        }
//
//                    }
//
//                }

                allnewData.clear();
                Log.e("tripulantesoverButton","Lista F          "+Flist);
                Log.e("tripulantesoverButton","Lista newData    "+newData);
                Log.e("tripulantesoverButton","Lista allnewData "+allnewData);
                if(Flist!=null||!Flist.isEmpty())
                {allnewData.addAll(Flist);
                    allnewData.addAll(newData);
                    Log.e("tripulantesoverButton","iF ");
                }else
                {
                    Log.e("tripulantesoverButton","else ");
                    for(int i=0;i<newData.size();i++)
                    {
                        Log.e("tripulantesoverButton"," "+newData.get(i));
                        allnewData.add(newData.get(i));
                    }

                }
                Log.e("tripulantesoverButton","Lista F "+Flist);
                Log.e("tripulantesoverButton","Lista newData    "+newData);
                Log.e("tripulantesoverButton","Lista allnewData "+allnewData);
                if(allnewData.get(0).isEmpty())
                {
                    allnewData.remove(0);
                }
                allnewDataR.clear();
                for(int h=0;h<Flist.size();h++)
                {

                    if(allnewData.get(h).equals(""))
                    {
                        //allnewData.remove(h);
                    }
                    else
                    {
                        allnewDataR.add(allnewData.get(h));
                    }
                }
                Log.e("tripulantesoverButton","lista nwDatatripulantes "+allnewData+"    "+cardLocation+"    size:  "+allnewData.size()+"   "+allnewDataR);
                DialogAdaptertripulantes.setVisibility(View.GONE);DialogAdaptertripulantes.setVisibility(View.GONE);
                if(!allnewDataR.isEmpty())
                {
                    //allnewData=allnewDataR;
                    adapter.setDataholdertripulantes( cardLocation,String.valueOf( allnewDataR));
                }
                else
                {
                    adapter.setDataholdertripulantes( cardLocation,String.valueOf( allnewData));
                }

                break;

        }

    }

}

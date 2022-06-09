package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.Zone;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.presenter.supervisorPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.presenter.supervisorPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Interactor.zoneAsignInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.View.zoneAsignViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.adapter.zonesAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view.zonesRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class zoneSupervisorAsignment extends AppCompatActivity implements supervisorView,View.OnClickListener{
    private TextView toolbar;
    private String zoneName,supervisorName,supervisorCVE;
    private List<String> catalog;
    private Spinner supervisors;
    private Context context;
    private supervisorPresenter presenter;
    private ImageView back;
    private CardView buttonsafechanges,buttonsafechanges2, buttonErase;
    private ProgressDialog progressDialog;

    private AutoCompleteTextView supervisorAutocomplete;
    private ImageView searchImage;
    private int zonecveLayer;
    private int newCveEmploye;
   private   List<String> coductores=new ArrayList<>();
   private String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignsupervisor);
        initView();
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        context=getApplicationContext();
        supervisorCVE=String.valueOf( zonesAdapter.supervisorCve);
        zoneName=zonesAdapter.cveLayerZoneName;
        zonecveLayer=zonesAdapter.cveLayerZone;
        supervisorName=zonesAdapter.supervisorName;

        toolbar=findViewById(R.id.routeName);
        toolbar.setText(zoneName);
        supervisors=findViewById(R.id.appCompatSpinner);
        back =findViewById(R.id.backsupervisorAsignment);
        buttonsafechanges=findViewById(R.id.buttonGuardarcambiosSupervisor);
        buttonsafechanges2=findViewById(R.id.buttonGuardarcambiosSupervisor2);
        buttonErase=findViewById(R.id.delete);
        //buttonErase.setVisibility(View.VISIBLE);
        supervisorAutocomplete=findViewById(R.id.autocomplete);

        searchImage=findViewById(R.id.searchnameVehicles);
        searchImage.setOnClickListener(this);
        buttonErase.setOnClickListener(this);
        buttonsafechanges.setOnClickListener(this);
        buttonsafechanges2.setOnClickListener(this);
        back.setOnClickListener(this);
       presenter= new supervisorPresenterImpl(this,getApplicationContext());
       presenter.requestCatalog();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, zonesRecyclerView.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void setEmployes(List<String> tripulantesdata) {
        this.catalog=tripulantesdata;
        fillSpinner();
        presenter.hideDialog();
        selectSpinnerPosition();
    }

    private void selectSpinnerPosition() {

        Log.e("zonesnames&colors15","S: "+supervisorName);
        if(!supervisorName.equals(""))
        {
            buttonErase.setVisibility(View.VISIBLE);
            buttonsafechanges2.setVisibility(View.VISIBLE);
            buttonsafechanges.setVisibility(View.GONE);
            Log.e("zonesnames&colors15","S value: "+supervisorName);
            for(int i=0;i<catalog.size();i++) {
                    if(i==0)
                    {

                    }else {
                        String name = catalog.get(i);
                        String[] parts = name.split("/");
                        // Log.e("zonesnames&colors15", "P: " + parts[0]+"  " +parts[1]);
                        if (parts[0].contains(supervisorName)) {
                            int index = i;
                            supervisors.setSelection(index);
                            newCveEmploye=Integer.valueOf( parts[1]);
                            Log.e("zonesnames&colors15", "I: " +  parts[0] +"    "+supervisorName +"    "+index+ "   colocar "+parts[1]);
                        }
                    }
            }
        }else
        {
            Log.e("zonesnames&colors15","S value: "+supervisorName);
            buttonErase.setVisibility(View.GONE);
            buttonsafechanges2.setVisibility(View.GONE);
        }
    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando asignaciones de supervisores");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void restarView() {
        Intent intent = new Intent(this,zoneSupervisorAsignment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }

    private void fillSpinner() {
       // final List<String> coductores=new ArrayList<>();
        for(int i=0;i<catalog.size();i++) {
            if(i==0)
            {
                coductores.add(catalog.get(i));
            }else {
                String name = catalog.get(i);
                String[] parts = name.split("/");
                coductores.add(parts[0]);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,R.layout.list_item, coductores);
        supervisors.setAdapter(adapter);
        supervisors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("zonesnames&colors16","spinner vehicle : "+adapterView.getItemAtPosition(i) + " index "+i);

                if(i==0)
                {

                }else
                {
                    supervisorName=String.valueOf(adapterView.getItemAtPosition(i));
                }
//                if(i==0)
//                {
//                    newCveEmploye=0;
//                }

                for(int p=0;p<catalog.size();p++) {
                    if(p==0)
                    {

                    }else {
                        String name = catalog.get(p);
                        String[] parts = name.split("/");
                        if (parts[0].contains(String.valueOf( adapterView.getItemAtPosition(i)))) {
                            newCveEmploye=Integer.valueOf(parts[1]);
                        }

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> adapterAutocoplete=new ArrayAdapter<String>(context,R.layout.list_item,coductores);
        supervisorAutocomplete.setAdapter(adapterAutocoplete);
        supervisorAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("supervisores","pos"+adapterView.getItemAtPosition(i));
                if(coductores.contains(adapterView.getItemAtPosition(i)))
                {
                    int pos=coductores.indexOf(adapterView.getItemAtPosition(i));
                    Log.e("supervisores","pos"+ pos);
                    supervisors.setSelection(pos);
                   nombre=String.valueOf( adapterView.getItemAtPosition(i));
                }
                Log.e("supervisores","V after select autocomplete "+ nombre);
                searchImage.performClick();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backsupervisorAsignment:
                Intent intent = new Intent(this, zonesRecyclerView.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.buttonGuardarcambiosSupervisor:
                Log.e("supervisorlog","Guadar :  "+zoneName+"  "+supervisorName+"  "+supervisorCVE);//presenter.safenewdfata
                if(supervisorName.equals(""))
                {
                    Log.e("supervisorlog","no hacer nada y toast");
                    Toast.makeText(context, "Debes seleccionar un supervisor", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    presenter.setZones(zonecveLayer,newCveEmploye);
                    buttonErase.setVisibility(View.VISIBLE);
                    buttonsafechanges2.setVisibility(View.VISIBLE);
                    buttonsafechanges.setVisibility(View.GONE);
                    Log.e("supervisorlog","ejecuta lo demas");
                }
              
                
                break;
            case R.id.buttonGuardarcambiosSupervisor2:
                Log.e("supervisorlog","Guadar2 :  "+zoneName+"  "+supervisorName+"  "+supervisorCVE);//presenter.safenewdfata
                presenter.setZones(zonecveLayer,newCveEmploye);
                break;
            case R.id.delete:
                zonesAdapter.supervisorName="";
                supervisorName="";
                presenter.setZones(zonecveLayer,0);
                supervisors.setSelection(0);
                buttonErase.setVisibility(View.GONE);
                buttonsafechanges2.setVisibility(View.GONE);
                buttonsafechanges.setVisibility(View.VISIBLE);
                break;
            case R.id. searchnameVehicles:
                if(supervisorAutocomplete.getVisibility()==View.GONE) {
                    supervisorAutocomplete.setVisibility(View.VISIBLE);
                    supervisors.setVisibility(View.GONE);
                }else{
                    supervisorAutocomplete.setVisibility(View.GONE);
                    supervisors.setVisibility(View.VISIBLE);
                }
                break;
        }

    }
}

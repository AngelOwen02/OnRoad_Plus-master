package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Interactor.zoneAsignInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.View.zoneAsignViewImpl;

import java.util.ArrayList;
import java.util.List;

public class adapterAsignmentsEdit  extends RecyclerView.Adapter<adapterAsignmentsEdit.ViewHolder>{

    private Context context;
    private zoneAsignViewImpl myview;
    private List<VehicleDriver> myAsignments;
    private List<String> myVehicles;
    private List<String> myDrivers;
    private int tripulantesPosition;
    private List<String> newTripulantes;
    private String nombre,vehiculo;
    private boolean isnew=false;
    private  String tripulantes = "";
    public adapterAsignmentsEdit(zoneAsignViewImpl myview, List<VehicleDriver> myAsignments,List<String> autos,List<String> conductores,Context context)
    {
        this.myview=myview;
        this.myAsignments=myAsignments;
        this.context=context;
        this.myVehicles=autos;
        this.myDrivers=conductores;
    }
    public void setDataholdertripulantes(int position,List<String> text)
    {
        this.tripulantesPosition=position;
        this.newTripulantes=text;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_asingmrent,parent, false);
        return new adapterAsignmentsEdit.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        /**meter in if para detectar el nombre de conductor y vehiculo   ===> if 0 valor default if ===> */

        if (String.valueOf(myAsignments.get(position).getCveVehicle()).equals("0")) {
            int positionperformclick = position;
             Log.e("tripuFlow7","value nuvo item    "+myAsignments.get(position).getCveVehicle()+"    position to performclick "+ position);
            if (position == myAsignments.size() - 1) {
                isnew = true;
                Log.e("tripuFlow7", "return to 2 " + vehiculo + "  is in new " + isnew);



                holder.cardViewfiled.setVisibility(View.GONE);
                holder.constrainRequest.setVisibility(View.VISIBLE);
                Log.e("tripuFlow7", "posicion en size-1");
            } else if (holder.vehicle.getText().equals("Test Vehicle")) {
                Log.e("tripuFlow7", "textdefault");
                holder.cardViewfiled.setVisibility(View.GONE);
                holder.constrainRequest.setVisibility(View.VISIBLE);
            }
        } else {

            Log.e("tripuFlow7", "return to  V" + vehiculo  +" N"+ nombre+ "  is in new " + isnew);
            if (isnew) {

            } else {
                Log.e("tripuFlow10",""+myAsignments.get(position).getVehicleName());
                holder.vehicle.setText(String.valueOf(myAsignments.get(position).getVehicleName()));
                if(zoneAsignInteractorImpl.mynamesVehicles.contains(myAsignments.get(position).getVehicleName()))
                {
                    int pos= zoneAsignInteractorImpl.mynamesVehicles.indexOf(myAsignments.get(position).getVehicleName());
                    holder.spinnerVehicle.setSelection(pos);

                }
                vehiculo = String.valueOf(myAsignments.get(position).getVehicleName());
                Log.e("tripuFlow11", "return to  V" + vehiculo  +" N"+ nombre+ "  is in new " + isnew);
            }

        }
        if (isnew) {

        } else {
            Log.e("tripuFlow7", "return to  V" + vehiculo  +" N"+ nombre+ "  is in new " + isnew);
            Log.e("tripuFlow10",""+myAsignments.get(position).getDriverName());
            holder.name.setText(String.valueOf(myAsignments.get(position).getDriverName()));
            if(zoneAsignInteractorImpl.mynamesDrivers.contains(myAsignments.get(position).getDriverName()))
            {
                int pos=zoneAsignInteractorImpl.mynamesDrivers.indexOf(myAsignments.get(position).getDriverName());
                holder.spinerName.setSelection(pos);

            }
            nombre = String.valueOf(myAsignments.get(position).getDriverName());
            if(nombre.equals(""))
            {
                Log.e("tripuFlow11","nombre vacio");
                holder.name.setText("Sin conductor");
            }
            Log.e("tripuFlow11", "return to  V" + vehiculo  +" N"+ nombre+ "  tripulantes " + isnew);
        }
        if(isnew)
        {
            holder.tripulantes.setText( "Sin tripulantes");
            holder.txtVeditTrip.setText("Selecciona tripulantes");
        }
        else{
        tripulantes="";
        List<String> data = new ArrayList<>();
        if (myAsignments != null) {
            if(myAsignments.get(position).getTripulantes()!=null||myAsignments.get(position).getTripulantes().size()>0||!myAsignments.get(position).getTripulantes().isEmpty())
            {


                for (int i = 0; i < myAsignments.get(position).getTripulantes().size(); i++) {
                    data.add(myAsignments.get(position).getTripulantes().get(i).getTripulanteName());
                }
                if (!data.isEmpty()) {
                    tripulantes = String.valueOf(data);
                    tripulantes.substring(1);
                    tripulantes.substring(0, tripulantes.length() - 1);
                }
            }else {
                tripulantes="Selecciona tripulantes";
            }
        }
            Log.e("tripuFlow12","else is new "+tripulantes+ "    mA "+myAsignments.get(position).getTripulantes().size());
            if(myAsignments.get(position).getTripulantes().size()==0)
            {

                tripulantes="Sin tripulantes";
            }else if(myAsignments.get(position).getTripulantes().size()==1&&myAsignments.get(position).getTripulantes().get(0).getTripulanteName().equals(""))
            {
//                Log.e("tripuFlow12","hay un valor con nombre vacio");
//                myAsignments.get(position).getTripulantes().clear();
//                if(myAsignments.get(position).getTripulantes().size()==0)
//                {
//
//                    tripulantes="Sin tripulantes";
//                }
            }
        holder.tripulantes.setText(tripulantes);/**ste es el nombre de la tarjeta al principio*/
        holder.txtVeditTrip.setText(tripulantes);/**este es el nombre del editable*/

        //if(newTripulantes!=""&&position==tripulantesPosition)
    }

        if(newTripulantes!=null)
        {
            Log.e("tripuFlow12","!comparacion datos despues de guardar "+newTripulantes+"     "+tripulantesPosition);
            if(tripulantesPosition==position)

            {
                holder.txtVeditTrip.setText(String.valueOf( newTripulantes));
                myview.showDialogTripulantes(tripulantesPosition,newTripulantes);
                myview.hideadapterdilogTripulantes();
            }
        }else
        {
            Log.e("tripuFlow12","comparacion datos despues de guardar "+newTripulantes+"     "+tripulantesPosition);
            if(isnew)
            {
                holder.tripulantes.setText("Sin tripulantes");
                holder.txtVeditTrip.setText("Sin tripulantes");
            }else
            {
                holder.tripulantes.setText(tripulantes);
                holder.txtVeditTrip.setText(tripulantes);
            }

        }
        holder.spinnerOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/**esto son los tres puntos de la tarjeta*/
                if(holder.fakespinner.getVisibility()==View.GONE)
                {
                    holder.fakespinner.setVisibility(View.VISIBLE);
                }else
                {
                    holder.fakespinner.setVisibility(View.GONE);
                }
            }
        });
//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /** esto deberia editar solo los campos para el request*/
//             //   holder.cardViewfiled.setVisibility(View.GONE);
//                holder.constrainRequest.setVisibility(View.VISIBLE);
//            }
//        });
        holder.fakeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           /*** esto es el dialog editar*/
                holder.cardViewfiled.setVisibility(View.GONE);
                holder.constrainRequest.setVisibility(View.VISIBLE);
                holder.fakespinner.setVisibility(View.GONE);
                nombre=myAsignments.get(position).getDriverName();
                vehiculo= myAsignments.get(position).getVehicleName();
            }
        });
        holder.fakeErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*** esto es el dialog eliminar*/
                holder.fakespinner.setVisibility(View.GONE);
                Log.e("editEraseB",""+position);
                myview.deleteAlertDialog(position);
            }
        });


        //region save/cancel on editItems

        holder.cancel.setOnClickListener(new View.OnClickListener() { // este metodo deberia de hacer finish a toda la vista y consultar de nuevo toda la informacion
            @Override
            public void onClick(View v) {
                myview.restartActivity();
//                if(myAsignments.get(position).getCveVehicle()==0)
//                {
//                    myview.eraseCeroitem(position);    /**metodo que elimina el item siempre  cuando venga en 0 el cve del vehiculo*/
//                }else {
//                    holder.cardViewfiled.setVisibility(View.VISIBLE);
//                    holder.constrainRequest.setVisibility(View.GONE);
//                }
            }
        });



        //endregion

        //region tripulantes
//        holder.tripulantest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//
//
//            /**mañana spinner de tripulantes*/
//
//        });
        /***/
        holder.overButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// este  es un boton sobrepuesto al spinner
                int cardlocation=position;
                Log.e("tripuFlow4"," data de tripulantes   "+ newTripulantes);
                myview.showDialogTripulantes(cardlocation, newTripulantes);
            }
        });
        //endregion tripulantes

        holder.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("tripuFlow7","autocomplete Driver"+adapterView.getItemAtPosition(i)+"   ");
                if(zoneAsignInteractorImpl.mynamesDrivers.contains(adapterView.getItemAtPosition(i)))
                {
                    int pos=zoneAsignInteractorImpl.mynamesDrivers.indexOf(adapterView.getItemAtPosition(i));
                    holder.spinerName.setSelection(pos);
                    nombre=String.valueOf( adapterView.getItemAtPosition(i));
                }
                Log.e("tripuFlow7","V after select autocomplete "+ nombre);
                holder.searchnameDriver.performClick();
            }
        });

        holder.autoCompleteTextViewVehicles.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("tripuFlow7","autocomplete Vehicle "+adapterView.getItemAtPosition(i)+"   ");
               if(zoneAsignInteractorImpl.mynamesVehicles.contains(adapterView.getItemAtPosition(i)))
               {
                   int pos= zoneAsignInteractorImpl.mynamesVehicles.indexOf(adapterView.getItemAtPosition(i));
                   holder.spinnerVehicle.setSelection(pos);
                   vehiculo=String.valueOf( adapterView.getItemAtPosition(i));
               }
                Log.e("tripuFlow7","V after select autocomplete "+ vehiculo);
                holder.searchNameVehicles.performClick();
            }
        });
        holder.spinerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               nombre=String.valueOf( adapterView.getItemAtPosition(i));
                Log.e("tripuFlow7", "spinner name : " + adapterView.getItemAtPosition(i) + "   " + i + "   nombre  " + nombre);
            if(nombre.equals(adapterView.getItemAtPosition(i))){
                Log.e("tripuFlow7","IF 1 N "+nombre);
                if (nombre != "" && zoneAsignInteractorImpl.mynamesDrivers.contains(String.valueOf(myAsignments.get(position).getDriverName()))&&nombre.equals(myAsignments.get(position).getDriverName())) {
                    int pos = zoneAsignInteractorImpl.mynamesDrivers.indexOf(myAsignments.get(position).getDriverName());
                    holder.spinerName.setSelection(pos);
                    Log.e("tripuFlow7","IF 2 N "+nombre);
                }
                else
                {
                    Log.e("tripuFlow7","IF 2  else N "+nombre);
                }
            } else
            {   Log.e("tripuFlow7","ELSE 1 N");
                if (zoneAsignInteractorImpl.mynamesDrivers.contains(nombre))
                {
                    int pos=zoneAsignInteractorImpl.mynamesDrivers.indexOf(nombre);
                    holder.spinerName.setSelection(pos);
                    Log.e("tripuFlow7","ELSE IF 1 N "+nombre);
                }else
                {
                    Log.e("tripuFlow7","ELSE else 1 N "+nombre);
                }
            }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.spinnerVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                vehiculo=String.valueOf( adapterView.getItemAtPosition(i));
                Log.e("tripuFlow7","spinner vehicle : "+adapterView.getItemAtPosition(i)+"   "+i+"  vehiculo "+vehiculo );
                if(vehiculo.equals( adapterView.getItemAtPosition(i))) {//si el valor de vehiculo es el del item escoge de acuerdo a  my asignments
                    Log.e("tripuFlow7","IF 1 V "+ vehiculo);
                    if (zoneAsignInteractorImpl.mynamesVehicles.contains(String.valueOf(myAsignments.get(position).getVehicleName()))&&vehiculo.equals( myAsignments.get(position).getVehicleName())) {
                        Log.e("tripuFlow7","IF 2 V "+ vehiculo);
                        int pos = zoneAsignInteractorImpl.mynamesVehicles.indexOf(myAsignments.get(position).getVehicleName());
                        holder.spinnerVehicle.setSelection(pos);

                    }else
                    {
                        Log.e("tripuFlow7","IF 2  else V " +vehiculo);
                    }
                }else//si no
                {Log.e("tripuFlow7","ELSE 1 V "+ vehiculo);
                    if (zoneAsignInteractorImpl.mynamesVehicles.contains(vehiculo))
                    {
                        int pos=zoneAsignInteractorImpl.mynamesVehicles.indexOf(vehiculo);
                        holder.spinnerVehicle.setSelection(pos);
                        Log.e("tripuFlow7","ELSE IF 1 V "+vehiculo);

                    }else
                    {
                        Log.e("tripuFlow7","ELSE else 1 V "+vehiculo);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// el save solo manda la data a la view para procesarla nombre vehiculo y la lista de tripulantes
//                holder.cardViewfiled.setVisibility(View.VISIBLE);
//                holder.constrainRequest.setVisibility(View.GONE);
                Log.e("tripuFlow7"," data de tripulantes a guardar   nombre: "+ nombre+"       vehiculo "+vehiculo+"  tripulantes"+newTripulantes+" real vehicle "+myAsignments.get(position).getVehicleName());

                if(isnew==false) {
                    Log.e("tripuFlow7","isnew ==false    V= "+ myAsignments.get(position).getVehicleName()+"     "+vehiculo);
                    if(myAsignments.get(position).getVehicleName().equals(vehiculo))
                    {
                        if( myAsignments.get(position).getDriverName().equals(nombre))
                        {
                            Log.e("tripulanteValue"," value:" +newTripulantes);
                            myview.safeData(position, myAsignments.get(position).getDriverName(), myAsignments.get(position).getVehicleName(), newTripulantes, isnew);
                        }else
                        {
                            myview.safeData(position,nombre, myAsignments.get(position).getVehicleName(), newTripulantes, isnew);
                        }


                    }else
                    {
                        if( myAsignments.get(position).getDriverName().equals(nombre))
                        {
                            myview.safeData(position, myAsignments.get(position).getDriverName(), vehiculo, newTripulantes, isnew);
                        }else
                        {
                           if( isnew==true)
                           {

                           }
                            if(!vehiculo.equals(myAsignments.get(position).getVehicleName()))
                            {
                                vehiculo=myAsignments.get(position).getVehicleName();
                            }
                            myview.safeData(position,myAsignments.get(position).getDriverName(), vehiculo, newTripulantes, isnew);
                        }

                    }
                    //myview.safeData(position, myAsignments.get(position).getDriverName(), myAsignments.get(position).getVehicleName(), newTripulantes, isnew);/** colocar el valor de vehiculo nombre y de tripulantes*/
                }else
                { Log.e("tripuFlow7","isnew ==true");
                    if(vehiculo.equals("Selecciona un vehículo"))
                    {
                        Log.e("tripuFlow7","ESTO REINICIA TODO");
                        myview.restartActivity();
                    }else {
                        Log.e("tripuFlow7","nuevo item");
                        myview.safeData(position, nombre, vehiculo, newTripulantes, isnew);
                    }
                }
                isnew=false;
                tripulantes="";
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {

        return myAsignments.size();
    }

    public void setFilter(List<VehicleDriver> newAsig) {
        this.myAsignments= new ArrayList<>();
        this.myAsignments.addAll(newAsig);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vehicle,name,tripulantes,edit ,fakeEdit,fakeErase,txtVeditTrip;
        CardView cardViewfiled,fakespinner;
        ConstraintLayout constrainRequest,ConstrainErase;
        Button save,cancel, overButton;
        Spinner spinerName,spinnerVehicle,myspinnerOptions,tripulantest;///volver serchable el spinner de nombres de conductores
        ImageView checkErase ,spinnerOptions ,searchnameDriver,searchNameVehicles;
        AutoCompleteTextView autoCompleteTextView,autoCompleteTextViewVehicles;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicle=itemView.findViewById(R.id.filedVehicle);
            name=itemView.findViewById(R.id.filedname);
            tripulantes=itemView.findViewById(R.id.filednametripulantes);
            txtVeditTrip=itemView.findViewById(R.id.textviewEditTrip);
            edit=itemView.findViewById(R.id.editAsignment);
            cardViewfiled=itemView.findViewById(R.id.cardviewFilldata);
            constrainRequest =itemView.findViewById(R.id.constrainRequesVehiclesandUnits);
            save =itemView.findViewById(R.id.buttonSaveAsignment);
            cancel= itemView.findViewById(R.id.buttonCancelAsignment);



            spinnerOptions= itemView.findViewById(R.id.optionspoints);
            myspinnerOptions= itemView.findViewById(R.id.spinnerAsingmentOptions);

            ArrayAdapter<CharSequence> adapteroptions = ArrayAdapter.createFromResource(context,R.array.optionsAsignmentsArray,android.R.layout.simple_spinner_item);
            adapteroptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fakespinner= itemView.findViewById(R.id.constrainSpinnerfake);
            fakeEdit= itemView.findViewById(R.id.editfakespinner);
            fakeErase= itemView.findViewById(R.id.erasefakespinner);

            myspinnerOptions.setAdapter(adapteroptions);

            tripulantest=itemView.findViewById(R.id.appCompatSpinnerTripulantes);
            overButton= itemView.findViewById(R.id.overbuttonTripulantes);

            /**autocompleteTextFilters*/

            //region spinner vehiculos y conductores
            spinnerVehicle = itemView.findViewById(R.id.appCompatSpinner);
            spinerName= itemView.findViewById(R.id.appCompatSpinner2);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,R.layout.list_item, zoneAsignInteractorImpl.mynamesVehicles);
            spinnerVehicle.setAdapter(adapter);
            ArrayAdapter<String> adapterSpinner2=new ArrayAdapter<String>(context,R.layout.list_item2,zoneAsignInteractorImpl.mynamesDrivers);
            spinerName.setAdapter(adapterSpinner2);
            //endregion
            //region autocompleteText

            searchnameDriver=itemView.findViewById(R.id.searchnameDriver);
            searchNameVehicles=itemView.findViewById(R.id.searchnameVehicles);

            autoCompleteTextView=itemView.findViewById(R.id.autoCompleteTextViewDrivers);
            autoCompleteTextViewVehicles=itemView.findViewById(R.id.autoCompleteTextViewVehicles);
            ArrayAdapter<String> adapterAutocopleteV=new ArrayAdapter<String>(context,R.layout.list_item,zoneAsignInteractorImpl.mynamesVehicles);//zoneAsignInteractorImpl.mynamesVehicles
            autoCompleteTextViewVehicles.setAdapter(adapterAutocopleteV);

            ArrayAdapter<String> adapterautocomplete2=new ArrayAdapter<String>(context,R.layout.list_item2,zoneAsignInteractorImpl.mynamesDrivers);
            autoCompleteTextView.setAdapter(adapterautocomplete2);

            searchnameDriver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(spinerName.getVisibility()==View.VISIBLE)
                    {
                        spinerName.setVisibility(View.GONE);
                        autoCompleteTextView.setVisibility(View.VISIBLE);
                    }else
                    {
                        spinerName.setVisibility(View.VISIBLE);
                        autoCompleteTextView.setVisibility(View.GONE);
                    }
                }
            });

            searchNameVehicles.setOnClickListener(new View.OnClickListener() {/** esto oculta muestra*/
                @Override
                public void onClick(View v) {
                    if(spinnerVehicle.getVisibility()==View.VISIBLE)
                    {
                        spinnerVehicle.setVisibility(View.GONE);
                        autoCompleteTextViewVehicles.setVisibility(View.VISIBLE);
                    }else
                    {
                        spinnerVehicle.setVisibility(View.VISIBLE);
                        autoCompleteTextViewVehicles.setVisibility(View.GONE);
                    }
                }
            });
            //endregion





//            ArrayAdapter<CharSequence> adapteroptions = ArrayAdapter.createFromResource(context,R.array.optionsAsignmentsArray,R.layout.itemspinner);
//            adapteroptions.setDropDownViewResource(R.layout.itemspinnerchekeed);


//

//
//            checkErase=itemView.findViewById(R.id.iconyellow);
//            ConstrainErase=itemView.findViewById(R.id.ConstrainFilldata);


//            onlyNames.clear();
//            onlyVehicles.clear();
//            updatevehiclesZones.clear();
//            for(int i = 0; i< driverAsignmentInteractorImplements.namesVehicles.size(); i++)
//            {
//                String name=driverAsignmentInteractorImplements.namesVehicles.get(i);
//                String[] part=name.split("/");
//                onlyVehicles.add(part[0]);
//            }
//            for(int k=0;k<driverAsignmentInteractorImplements.namesDrivers.size();k++)
//            {
//                String name=driverAsignmentInteractorImplements.namesDrivers.get(k);
//                String[] part=name.split("/");
//                onlyNames.add(part[0]);
//            }


//

//            Log.e("driindexes","I2"+ driversAsingment.indexes);
//            if(!driversAsingment.indexes.isEmpty())
//            {
//                checkErase.setVisibility(View.VISIBLE);
//                itemesSelected.clear();
//
////                for(int i=0;i<driversAsingment.indexes.size();i++)
////                {
////
////                }
//                itemesSelected.addAll(driversAsingment.indexes);
//                //   Log.e("editEraseB","D i "+driversAsingment.indexes);
//                viewclass.showtrashcan(itemesSelected,data.size());
//            }else
//            {
//
//                checkErase.setVisibility(View.GONE);
//            }


        }
    }
}

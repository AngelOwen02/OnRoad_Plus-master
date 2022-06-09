package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.adapter;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.interactor.driverAsignmentInteractorImplements;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.VhicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.vehicleZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.view.driversAsingment;

import java.util.ArrayList;
import java.util.List;

public class dirverAsingmenAdapter extends RecyclerView.Adapter<dirverAsingmenAdapter.ViewHolder>{

    private Context context;
    private List<String> data;
    public static List<String> mirrorData;
    private List<String> onlyVehicles=new ArrayList<>();
    private List<String> onlyNames=new ArrayList<>();
    private List<vehicleZones> updatevehiclesZones=new ArrayList<>();
    private String CveVehicle;
    private String CveDriver;
    private driversAsingment viewclass;
    private  List<String> itemesSelected=new ArrayList<>();
    private  boolean setplace=false;
    private String lastname,lastvehicle;
    int countToErase=0;
    private String beforeValue1,beforeValue2;
    private List<String> myAsignments;

    private int tripulantesPosition;
    private String newTripulantes;
    //private int editErase;
    public dirverAsingmenAdapter(driversAsingment view,List<String> data,List<String> myAsignments,Context context)
    {
        this.viewclass=view;
        this.data=data;
        this.myAsignments=myAsignments;
        this.context=context;
         mirrorData=data;
    }
    public void setDataholdertripulantes(int position,String text)
    {
        this.tripulantesPosition=position;
        this.newTripulantes=text;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_asingmrent,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        int locationd=position;
        final String part=data.get(position);
        final String[] sections=part.split(",");
//        CveVehicle = sections[2];
//        CveDriver = sections[3];
        Log.e("viewtoReuquestA",""+ data);
        //Log.e("driindexes","I3"+ driversAsingment.indexes);
        if(data!=null)
        {
      //      view.agimenfullItems(data);
        }

        if(!driversAsingment.indexes.isEmpty()||driversAsingment.indexes!=null)
        {
            //Log.e("driindexes","I4"+ data.indexOf(driversAsingment.indexes));

                //if(data.contains(data.indexOf(driversAsingment.indexes)))/** si en data existe el index de la lista estatica*/
//            for(int i=0;i<driversAsingment.indexes.size();i++)
//            {
//                itemesSelected.add(String.valueOf( i));
//                holder.checkErase.setVisibility(View.VISIBLE);
//            }
//            view.showtrashcan(itemesSelected,data.size());
//             notifyDataSetChanged();


              //                itemesSelected.addAll(driversAsingment.indexes);
//                //Log.e("driindexes","I3"+ itemesSelected);
//                view.showtrashcan(itemesSelected,data.size());
//                notifyDataSetChanged();

                /***/


        }
        else        {
           // data.set(position, holder.name.getText()+","+holder.vehicle.getText()+","+0+","+0);
     //       view.agimenfullItems(null);// revisar el dato con cve 0 empleado y cehiculo
        }
        if(sections[1].equals("Selecciona un vehículo"))
        {
            holder.cardViewfiled.setVisibility(View.GONE);
            holder.constrainRequest.setVisibility(View.VISIBLE);
        }
        if(sections[0].equals("")||sections[0].equals(" "))
        {
            holder.name.setTextColor(Color.GRAY);
            holder.name.setText("Sin conductor Asignado");
        }
            else
         {


            holder.name.setText(sections[0]);
        }
        holder.vehicle.setText(sections[1]);
        if(myAsignments.size()!=0) {
            if (myAsignments.get(position) != null && myAsignments != null && !myAsignments.isEmpty()) {
                if (myAsignments.get(position).equals("[]")) {
                    holder.tripulantes.setTextColor(Color.GRAY);
                    holder.tripulantes.setText("Sin Tripulantes");
                } else {
                    String[] parts=myAsignments.get(position).split("/");
                    String datainholder="";
                    List<String> realtext=new ArrayList<>();
                    realtext.clear();
                    Log.e("correcTextripulantes",""+String.valueOf(myAsignments.get(position))+" tamaño:  "+parts);
                    for (int i=0;i<parts.length;i++)
                    {
                        if(i==0)
                        {
                            String newstring=parts[i].substring(1);
                            realtext.add(newstring);
                        }else if(parts[i].contains(", ")){
                            String[] newpart=parts[i].split(", ");
                           // Log.e("correcTextripulantes"," middle "+newpart[0]+":   :"+newpart[1]);
                            realtext.add(newpart[1]);
                        }else if(i==parts.length)
                        {

                        }
                        Log.e("correcTextripulantes"," item:  "+parts[i]);
                    }
                    String listexvalue=String.valueOf(realtext);
                    String substr1=listexvalue.substring(1);
                    String sbstr2=substr1.substring(0,substr1.length()-1);
                    Log.e("correcTextripulantes",""+ String.valueOf(sbstr2));
                    holder.tripulantes.setText(sbstr2);
                    holder.txtVeditTrip.setText(sbstr2);
                }
            }
        }else
        {
            holder.tripulantes.setTextColor(Color.GRAY);
            holder.tripulantes.setText("Sin Tripulantes");
        }
        if(newTripulantes!=null) {
            if (!newTripulantes.equals("")) {
                Log.e("correcTextripulantes2", "set valores en holder" + newTripulantes + " position  " + tripulantesPosition+"   "+newTripulantes.length());
                if(position==tripulantesPosition)
                {
                    Log.e("ERASE",""+newTripulantes);
                    String[] parts=myAsignments.get(position).split("/");
                    List<String> realtext=new ArrayList<>();
                    realtext.clear();
                    for (int i=0;i<parts.length;i++)
                    {
                        if(i==0)
                        {
                            String newstring=parts[i].substring(1);
                            realtext.add(newstring);
                        }else if(parts[i].contains(", ")){
                            String[] newpart=parts[i].split(", ");
                            // Log.e("correcTextripulantes"," middle "+newpart[0]+":   :"+newpart[1]);
                            realtext.add(newpart[1]);
                        }else if(i==parts.length)
                        {

                        }
                        Log.e("correcTextripulantes"," item:  "+parts[i]);
                    }
                    String listexvalue=String.valueOf(realtext);
                    String substr1=listexvalue.substring(1);
                    String sbstr2=substr1.substring(0,substr1.length()-1);

                    holder.txtVeditTrip.setText(newTripulantes);
                }

                newTripulantes = "";
            }
        }


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** esto deberia editar solo los campos para el request*/
                holder.cardViewfiled.setVisibility(View.GONE);
                holder.constrainRequest.setVisibility(View.VISIBLE);


                if(onlyVehicles.contains(lastvehicle))
                {
                    int location=onlyVehicles.indexOf(holder.vehicle.getText());
                    holder.spinnerVehicle.setSelection(location);
                }
                if(onlyNames.contains(lastname))
                {
                    int location=onlyNames.indexOf(holder.name.getText());
                    holder.spinerName.setSelection(location);
                    //holder.autoCompleteTextView.setCompletionHint();
                }
            }
        });
        //
        holder.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().isEmpty())
                {
                    Log.e("autocompletenamdriver", "empty value : " + parent.getItemAtPosition(position).toString());
                }else {
                    Log.e("autocompletenamdriver", "name: " + parent.getItemAtPosition(position).toString());
                    holder.name.setText(parent.getItemAtPosition(position).toString());
                }

            }
            });
        //
        //
         holder.autoCompleteTextViewVehicles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().isEmpty())
                {
                    Log.e("autocompletenamdriver", "empty value : " + parent.getItemAtPosition(position).toString());
                }else {
                    Log.e("aftersafe", "unit : " + parent.getItemAtPosition(position).toString());
                    Log.e("autocompletenamdriver", "name: " + parent.getItemAtPosition(position).toString());
                    holder.vehicle.setText(parent.getItemAtPosition(position).toString());/**mañana perform click*/
                    if(onlyVehicles.contains(parent.getItemAtPosition(position).toString()))
                    {
                        int location=onlyVehicles.indexOf(holder.vehicle.getText());
                        holder.spinnerVehicle.setSelection(location);
                    }
                    holder.searchNameVehicles.performClick();
                }

            }
        });

        //

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.vehicle.getText().equals("Selecciona un vehículo"))
                {
                    data.remove(position);
                    Log.e("asignment",""+data );
                    //Toast.makeText(context, "tienes que elegir un vehiculo para generar una asignacion", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    //holder.autoCompleteTextView.clearListSelection();
                }
                else
                {
                    holder.cardViewfiled.setVisibility(View.VISIBLE);
                    holder.constrainRequest.setVisibility(View.GONE);

                    holder.autoCompleteTextView.setText("");
                    holder.autoCompleteTextViewVehicles.setText("");
                    if(holder.vehicle.getText()!=lastvehicle)
                    {
                        holder.vehicle.setText(lastvehicle);
                    }
                    if(holder.name.getText()!=lastname)
                    {
                        holder.name.setText(lastname);
                    }

                    viewclass.agimenfullItems(data);

                }
                Log.e("asignment",""+data );
            }
        });

        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**aqui solo se guarda la data modigicada yse realiza el request que inserta informacion*/
                Log.e("asignmentI","only data ..... :"+data);
                holder.cardViewfiled.setVisibility(View.VISIBLE);
                holder.constrainRequest.setVisibility(View.GONE);

                Log.e("asignmentI","nombres principales      "+holder.name.getText()+" "+holder.vehicle.getText());
                String cveAlternateVehicle=String.valueOf( sections[2]);
                /**aqui se debe contemplar el nombre del null*/
                if(CveVehicle.equals("0"))
                {
                    data.remove(position);

                Toast.makeText(context, "Tienes que elegir un vehículo para generar una asignación", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
                else
                {
                    if(CveDriver==null)
                    {
                        CveDriver="0";
                    }
                    Log.e("asignmentI","before     : "+data );/**nota para pasado mañana*/
                    if(data.toString().contains(CveVehicle)) {
                        Log.e("asignmentI","cve "+CveVehicle +"   |"+sections[1]+"|");

                        beforeValue2=sections[1];
                        if(sections[3].equals("0")&&sections[0].equals(" ")&&sections[1].equals("Selecciona un vehículo")) {
                                data.remove(position);
                                Toast.makeText(context, "No puedes agregar dos veces la misma unidad", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                        }else
                        {
                            Log.e("asignmentI","V  " +sections.length +"   " +sections[0]+"  "+sections[1]+"  "+sections[2]+" "+sections[3]);
                            if(data.toString().contains(holder.vehicle.getText()))
                            Log.e("asignmentI","V "+holder.vehicle.getText()+ "  cveV "+CveVehicle +"   bv"+ beforeValue1+"  cvebv2 "+beforeValue2 );
                            if(data.toString().contains(holder.vehicle.getText()))
                            {
                                data.set(position, holder.name.getText() + "," + beforeValue2 + "," + beforeValue1 + "," + CveDriver);
                               // Toast.makeText(context, "No puedes registrar mas de dos veces la unidad", Toast.LENGTH_SHORT).show();
                                holder.vehicle.setText(beforeValue2);
                                holder.name.setText(sections[0]);
                                Log.e("asignmentI","V if 2 "+holder.vehicle.getText()+ "  cveV "+CveVehicle +"   bv"+ beforeValue1+"  cvebv2 "+beforeValue2 );
                            }else
                            {
                                data.set(position, holder.name.getText() + "," + holder.vehicle.getText() + "," + CveVehicle + "," + CveDriver);
                                Log.e("asignmentI","V else "+holder.vehicle.getText()+ "  cveV "+CveVehicle +"   bv"+ beforeValue1+"  cvebv2 "+beforeValue2 );
                            }
                        }
                    }else
                    {
                        data.set(position, holder.name.getText() + "," + holder.vehicle.getText() + "," + CveVehicle + "," + CveDriver);
                        Log.e("asignmentI","V else out "+holder.vehicle.getText()+ "  cveV "+CveVehicle +"   bv"+ beforeValue1+"  cvebv2 "+beforeValue2 );
                    }

                }

                Log.e("asignmentI"," after "+data );
                if(data!=null)
                {

                    viewclass.agimenfullItems(data);
                }
                //driversAsingment.AsignmentDrivers();

            }
        });
        holder.spinnerVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {/** caso para cambio de vehiculo*/
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int place, long id) {
                    //notifyDataSetChanged();
                    lastvehicle=String.valueOf( holder.vehicle.getText());
                    Log.e("aftersafe", "vehicleCVE: " +lastvehicle);
                    if(!driverAsignmentInteractorImplements.namesVehicles.get(place).equals("Selecciona un vehículo")) {//beforecSelecciona un vehículo
                        Log.e("asignmentH","nothin selected A "+driverAsignmentInteractorImplements.namesVehicles.get(place));//lastvehicle);

                        if(holder.vehicle.getText()==null||holder.vehicle.getText()==""||holder.vehicle.getText()!=onlyVehicles.get(place)||holder.vehicle.getText()==" "){
                        holder.vehicle.setText(onlyVehicles.get(place));}
                        if(driverAsignmentInteractorImplements.namesVehicles.get(place).contains(String.valueOf(onlyVehicles.get(place))))//nombres de vehiculos con cves vs solo nombres
                        {

                            String name=driverAsignmentInteractorImplements.namesVehicles.get(place);
                            Log.e("asignmentH"," "+ name);
                            if(name.equals("Selecciona un vehículo"))
                            {
                                CveVehicle="0";
                            }else{
                            String[] part=name.split("/");
                            beforeValue1=CveVehicle;
                            CveVehicle=part[1];                                                         // CveVehicle = sections[2];
                           Log.e("asignmentH", "vehicleCVE: " + part[1]);}
                        }else {
                            Log.e("asignment","nothin selected A else");
                           // CveVehicle = "hola";/**esto es por que el  valor default en  esta posicion es el item en data*/
                        }

                        Log.e("asignmentH", "" + onlyVehicles.get(place));
                    }else

                    {
                        if(sections[2]!=null)
                        {CveVehicle = sections[2];/**esto es por que el  valor default en  esta posicion es el item en data*/
                            Log.e("asignmentH","section2     "+ sections[2]);
                        }

                        if(holder.vehicle.getText()!=null||holder.vehicle.getText()!="")
                        {           Log.e("asignmentH","  "+onlyVehicles.indexOf(holder.vehicle.getText()));
                                  if(onlyVehicles.contains(holder.vehicle.getText()))
                                      {
                                           int location=onlyVehicles.indexOf(holder.vehicle.getText());
                                           holder.spinnerVehicle.setSelection(location);
                                      }
                        }else
                        {
                          //  Log.e("asignment","nothin selected B else");
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        holder.spinerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {/** caso para cambio de conductor*/
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int place, long id) {
                    lastname=String.valueOf(holder.name.getText());
                if(!driverAsignmentInteractorImplements.namesDrivers.get(place).equals("Selecciona un conductor")) {
                    //Log.e("asignmentI","ERROR  a "+driverAsignmentInteractorImplements.namesDrivers.get(place));
                   // Log.e("asignment","nothin selected Ac "+lastname);
                    if( holder.name.getText()==null|| holder.name.getText()==""||holder.name.getText()!=onlyNames.get(place))
                    {holder.name.setText(onlyNames.get(place));}
                   // Log.e("asignment","item en empty");
                    if(driverAsignmentInteractorImplements.namesDrivers.get(place).contains(String.valueOf( onlyNames.get(place))))
                    {
                        String name=driverAsignmentInteractorImplements.namesDrivers.get(place);
                        String[] part=name.split("/");
                        CveDriver=part[1];
                        //CveDriver = sections[3];
                        //Log.e("asignment", "nameCVE: " + part[1]);
                        //Log.e("asignment","item  de lista estatica en solo nombres conductores");

                    }

                    else
                     {
                        // Log.e("asignment","nothin selected A else");
                      //  CveDriver = "adios";/***esto es por que en data no existe ese valor*/

                    }
                  //  Log.e("asignment", "" + onlyNames.get(place));
                }else
                {
                //    Log.e("asignmentI","ERROR b "+driverAsignmentInteractorImplements.namesDrivers.get(place));
                    if(sections[3]!=null) {
                        CveDriver = sections[3];
                    }
                    if(holder.name.getText()!=null||holder.name.getText()!="")
                    {// Log.e("asignmentI","nothin selected B");
                        if(onlyNames.contains(holder.name.getText()))
                        {
                            int location=onlyNames.indexOf(holder.name.getText());
                            holder.spinerName.setSelection(location);
                        }
                    }else
                {
                   // Log.e("asignment","nothin selected B else");
                }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        holder.ConstrainErase.setOnLongClickListener(new View.OnLongClickListener() {


            @Override
            public boolean onLongClick(View v) {
                Log.e("driindexes",""+position);

                if( holder.checkErase.getVisibility()==View.GONE)
                {
                    countToErase=countToErase+1;
                    holder.checkErase.setVisibility(View.VISIBLE);
                    if(itemesSelected.contains(String.valueOf( position)))
                    {

                    }else {
                        itemesSelected.add(String.valueOf(position));
                        //view.hideshowBarConstrain();
                    }
                    Log.e("editEraseB",""+itemesSelected);
                }
                else
                {
                    Log.e("editEraseB",""+itemesSelected);
                    if(countToErase==0 && holder.checkErase.getVisibility()==View.VISIBLE)
                    {
                        countToErase=data.size();
                    }
                    countToErase=countToErase-1;
                    Log.e("editEraseB",""+itemesSelected);
                    holder.checkErase.setVisibility(View.GONE);
                    if(itemesSelected.contains(String.valueOf( position)))
                    {
                        itemesSelected.remove(String.valueOf(position));
                        //view.hideshowBarConstrain();
                    }else {

                    }if(countToErase==0)
                        {
                         Log.e("editEraseB",""+itemesSelected);
                            driversAsingment.indexes.clear();
                            viewclass.showtrashcan(itemesSelected,0);
                        }
                }
                viewclass.showtrashcan(itemesSelected,countToErase);
                Log.e("longasignment",""+data.size()+"  "+data );
                Log.e("longasignment","It S "+itemesSelected.size()+"  "+itemesSelected );
                Log.e("myvolante","longclick "+ itemesSelected);
                Log.e("longasignment",""+countToErase);
                return false;
            }
        });
        Glide.with(context).load(R.drawable.ic_icn_masopciones).into( holder.spinnerOptions);
        holder.spinnerOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***
                if(holder.myspinnerOptions.getVisibility()== View.GONE)
                {
                    holder.myspinnerOptions.setVisibility(View.VISIBLE);
                    holder.myspinnerOptions.performClick();
                    Log.e("editEraseB", "spinnerEraseEditValue:  ");

                   // holder.myspinnerOptions.setSelection(0,false);

                }else
                {
                    holder.myspinnerOptions.setVisibility(View.GONE);

                }*/
                if(holder.fakespinner.getVisibility()==View.GONE)
                {
                    holder.fakespinner.setVisibility(View.VISIBLE);
                }else
                {
                  holder.fakespinner.setVisibility(View.GONE);
                }



            }
        });
        holder.fakeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardViewfiled.setVisibility(View.GONE);
                holder.constrainRequest.setVisibility(View.VISIBLE);
                if(onlyVehicles.contains(lastvehicle))
                {
                    int location=onlyVehicles.indexOf(holder.vehicle.getText());
                    holder.spinnerVehicle.setSelection(location);
                }
                if(onlyNames.contains(lastname))
                {
                    int location=onlyNames.indexOf(holder.name.getText());
                    holder.spinerName.setSelection(location);
                }
                holder.fakespinner.setVisibility(View.GONE);
            }
        });

        holder.fakeErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fakespinner.setVisibility(View.GONE);
                Log.e("editEraseB",""+position);
                viewclass.deleteAlertDialog(position);
            }
        });

     //   holder.myspinnerOptions.get
        //holder.myspinnerOptions.getOnItemClickListener(new AdapterView<>())
        holder.myspinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int placv, long id) {


                    Log.e("editEraseB", "spinnerEraseEditValue:  "+parent.getItemAtPosition(placv));
                return;
               // holder.myspinnerOptions.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              //  holder.myspinnerOptions.setVisibility(View.GONE);
             //   Log.e("editEraseB", "spinnerEraseEditValue:  "+parent.);
                return;
            }
        });
        /**se comento esto por que no necesitamos el itemselector en el spinner*/
        holder.tripulantest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


            /**mañana spinner de tripulantes*/

        });
        /***/
    holder.overButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int cardlocation=position;
            viewclass.showDialogTripulantes(cardlocation);
        }
    });
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilter(List<String> newAsig) {
        this.data=new ArrayList<>();
        this.data.addAll(newAsig);
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

            overButton= itemView.findViewById(R.id.overbuttonTripulantes);

            /**autocompleteTextFilters*/
            autoCompleteTextView=itemView.findViewById(R.id.autoCompleteTextViewDrivers);
            autoCompleteTextViewVehicles=itemView.findViewById(R.id.autoCompleteTextViewVehicles);
            spinnerOptions= itemView.findViewById(R.id.optionspoints);
            myspinnerOptions= itemView.findViewById(R.id.spinnerAsingmentOptions);
            tripulantest=itemView.findViewById(R.id.appCompatSpinnerTripulantes);
            searchnameDriver=itemView.findViewById(R.id.searchnameDriver);
            searchNameVehicles=itemView.findViewById(R.id.searchnameVehicles);
//            ArrayAdapter<CharSequence> adapteroptions = ArrayAdapter.createFromResource(context,R.array.optionsAsignmentsArray,android.R.layout.simple_spinner_item);
//            adapteroptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


             ArrayAdapter<CharSequence> adapteroptions = ArrayAdapter.createFromResource(context,R.array.optionsAsignmentsArray,R.layout.itemspinner);
             adapteroptions.setDropDownViewResource(R.layout.itemspinnerchekeed);

                fakespinner= itemView.findViewById(R.id.constrainSpinnerfake);
                fakeEdit= itemView.findViewById(R.id.editfakespinner);
                fakeErase= itemView.findViewById(R.id.erasefakespinner);

            myspinnerOptions.setAdapter(adapteroptions);

            spinnerVehicle = itemView.findViewById(R.id.appCompatSpinner);
            spinerName= itemView.findViewById(R.id.appCompatSpinner2);

            checkErase=itemView.findViewById(R.id.iconyellow);
            ConstrainErase=itemView.findViewById(R.id.ConstrainFilldata);
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

            searchNameVehicles.setOnClickListener(new View.OnClickListener() {
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

            onlyNames.clear();
            onlyVehicles.clear();
            updatevehiclesZones.clear();
            for(int i=0;i<driverAsignmentInteractorImplements.namesVehicles.size();i++)
            {
                String name=driverAsignmentInteractorImplements.namesVehicles.get(i);
                String[] part=name.split("/");
                onlyVehicles.add(part[0]);
            }
            for(int k=0;k<driverAsignmentInteractorImplements.namesDrivers.size();k++)
            {
                String name=driverAsignmentInteractorImplements.namesDrivers.get(k);
                String[] part=name.split("/");
                onlyNames.add(part[0]);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,R.layout.list_item, onlyVehicles);
            spinnerVehicle.setAdapter(adapter);

            ArrayAdapter<String> adapterAutocopleteV=new ArrayAdapter<String>(context,R.layout.list_item,onlyVehicles);
            autoCompleteTextViewVehicles.setAdapter(adapterAutocopleteV);

            ArrayAdapter<String> adapterautocomplete2=new ArrayAdapter<String>(context,R.layout.list_item2,onlyNames);
            autoCompleteTextView.setAdapter(adapterautocomplete2);

            ArrayAdapter<String> adapterSpinner2=new ArrayAdapter<String>(context,R.layout.list_item2,onlyNames);
            spinerName.setAdapter(adapterSpinner2);
            Log.e("driindexes","I2"+ driversAsingment.indexes);
            if(!driversAsingment.indexes.isEmpty())
            {
                checkErase.setVisibility(View.VISIBLE);
                itemesSelected.clear();

//                for(int i=0;i<driversAsingment.indexes.size();i++)
//                {
//
//                }
                itemesSelected.addAll(driversAsingment.indexes);
             //   Log.e("editEraseB","D i "+driversAsingment.indexes);
                viewclass.showtrashcan(itemesSelected,data.size());
            }else
            {

                checkErase.setVisibility(View.GONE);
            }


        }
    }
}

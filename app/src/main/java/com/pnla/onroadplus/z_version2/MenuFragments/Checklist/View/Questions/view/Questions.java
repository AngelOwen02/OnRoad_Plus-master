package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.pnla.onroadplus.BuildConfig;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.sectionsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.mquestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter.questionPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter.questionsPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.historicChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.trafic_light.view.traficDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Questions  extends Fragment implements View.OnClickListener ,questionView{
    public static final String TAG = Questions.class.getSimpleName();
    private sectionsAdapter adapterQuestionary;
 //   private CardView cardView;
    private ViewPager2 pager;
    private PagerAdapter pAdapter;
    private LinearLayout dotslayout;
    private int sizeArange;
    private Button buttongochecklist;
    private ImageView searchCheckList,historic_checks;
    private ProgressDialog progressDialog;
    private List<dataSections> dataSections;
    private List<dataQuestions> dataQuestions1;
    private questionPresenter presenter;
    private boolean isfirsttime=false;
    public static int posrv;
    private sectionsAdapter sA;
    private TextView titlefileds;
    private int checklistN,Checkl;
    private boolean aproved;
    public static  List<dataChecklist> fulChecklist= new ArrayList<>();

    //camera
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    String currentPhotoPath;
    private File myimageFile;
    private Uri imageUri;
    ImageView imageViewP;
    
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            checklistN= bundle.getInt("cveTripMgmSection");
            Checkl= bundle.getInt("Section");
            aproved=bundle.getBoolean("aprobador");
        }
        initContactView(view);

      // /** esto inicializa la posicion y el numero de puntos del quiestionario*/
        return view;
    }

    @SuppressLint("NewApi")
    private void initContactView(View view) {
       // cardView=view.findViewById(R.id.cardviewitem);

        searchCheckList = view.findViewById(R.id.search_checkList);
        searchCheckList.setVisibility(View.INVISIBLE);
        pager = (ViewPager2) view.findViewById(R.id.cardviewitem);
        dotslayout = view.findViewById(R.id.dots_layout);
        buttongochecklist=view.findViewById(R.id.buttongochecklist);
        titlefileds=view.findViewById(R.id.titlefileds);
        buttongochecklist.setOnClickListener(this);
        historic_checks=view.findViewById(R.id.historic_checks);
        historic_checks.setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        presenter=new questionsPresenterImpl(this,getContext());
        presenter.getpSections(Checkl);


    }

    void filldataAdapter()/// modulo de secciones adpater
    {
                sA=new sectionsAdapter(dataQuestions1,sizeArange,this, getContext());
                pager.setAdapter(sA);
                pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                    @Override
                    public void onPageSelected(final int position) {
                        super.onPageSelected(position);

                                posrv=position;
                                sA.notifyDataSetChanged();


                        Log.e("finalcheck",""+position);
                        movedots(position);
                        titlefileds.setText(dataQuestions1.get(position).getDescTripMgmSection());
                        if(position!=0) {
                        }
                            if(position==sizeArange-1)
                            {
                                buttongochecklist.setVisibility(View.VISIBLE);
                            }
                            else
                            {

                                buttongochecklist.setVisibility(View.GONE);
                            }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        super.onPageScrollStateChanged(state);
                    }
                });


    }

    private void menutransition() // fragmento anterior
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CheckListViewImpl checklist = new CheckListViewImpl();
        transaction.replace(R.id.conteinerMainFragments, checklist, CheckListViewImpl.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

    }
    private void gotohistoric()
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        historicChecklist historic = new historicChecklist();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, historic, historicChecklist.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }
    @Override
    //Pressed return button - returns to the results menu
    public void onResume() { //onback
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    menutransition();
                    return true;
                }
                return false;
            }
        });
    }

    //region dots
    @SuppressLint("NewApi")
    public void movedots(int position) {// mueve los puntos

     //   if (dotslayout.getChildCount() > 0) {
            dotslayout.removeAllViews();
      //  }

        final ImageView dots[]=new ImageView[sizeArange];//[fulldata.get(companyIndex).getBanner().size()];
        for (int i=0; i<sizeArange;i++){//(int i=0; i<fulldata.get(companyIndex).getBanner().size();i++){
            dots[i]=new ImageView(getContext());
            if(i==position)
            {
                dots[i].setImageDrawable(getContext().getDrawable(R.drawable.black_dot_svg));//esto pone el dot en negro
                if(dataQuestions1!=null)
                {

                }
            }else
            {
                dots[i].setImageDrawable(getContext().getDrawable(R.drawable.gray_dot_svg));//esto pone el dot en gris
            }
            final LinearLayout.LayoutParams linearLayout=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setMargins(4,0,4,0);
            final int finalI = i;
            dotslayout.post(new Runnable() {
                @Override
                public void run() {
                    dotslayout.addView(dots[finalI],linearLayout);
                }
            });


        }
    }
//endregion dots

    @Override
    public void setSections(List<dataSections> data) {//secciones
        this.dataSections=data;
        if(dataSections!=null)
        {
            sizeArange= dataSections.size();

            if(sizeArange!=0) {
                if (isfirsttime == false) {
                    posrv=0;
                    presenter.getpQuestions(Checkl);//dataSections.get(0).getCveTripMgmSection());
                   // movedots(0);
                    isfirsttime = true;
                } else {
                }
            }else{
                Toast.makeText(getContext(), "sin datos", Toast.LENGTH_SHORT).show();
            }


        }
    }
    public void safeValues(int position, boolean b, int value, int i, Integer cveTripMgmQuestion, Integer score, Integer cveTripMgmAnswer)
    {
        Log.e("finalCheckdata3"," "+position+" "+b+" "+value+" "+i); //todo  posiciondepregunta | switchboolean | valueAnswerpos | type: 1,2   1 ~ switch 2 ~ multiple
        String h = null;
        for(int v=0;v<fulChecklist.size();v++)
        {
            if(fulChecklist.get(v).getAnswerId()==cveTripMgmQuestion)
            {
                h= String.valueOf( v);
            }
        }
        Log.e("finalCheckdata4","index "+h+"  Answerpos: "+value);
        int iterator=Integer.valueOf( h);
        //Before
        Log.e("finalCheckdata4","index "+fulChecklist.get(iterator).getAnswerPos());
        //After
        fulChecklist.get(iterator).setAnswerPos(value);
        fulChecklist.get(iterator).setScore(score);
        fulChecklist.get(iterator).setCveTripMgmAnswer(cveTripMgmAnswer);
        Log.e("finalCheckdata4","index "+fulChecklist.get(iterator).getAnswerPos());
    }

    @SuppressLint("NewApi")   /** ESTE METODO SALVA LA INFORMACION DE TODAS LAS PREGUNTAS EN SHARED PREFERENCES*/
    @Override
    public void setQuestions(List<dataQuestions> data) {
        this.dataQuestions1=data;
        if(dataQuestions1!=null)
        {
           Log.e("finalChecklistdata","inpect questions: "+dataQuestions1.size()+"   fulChecklist: "+fulChecklist.size());//+"  "+dataSections.get(1).getDescTripMgmSection() // movedots(0);
            List<mquestions> mquestion=new ArrayList<>();
            mquestion.clear();
            for(int i=0;i<dataQuestions1.size();i++) {
               for(int k=0;k<dataQuestions1.get(i).getQuestions().size();k++)
                {
                    mquestion.add(dataQuestions1.get(i).getQuestions().get(k));
                    Log.e("finalCheckdata3", " mquestion" + mquestion.size());
                }
            }
           // Log.e("finalCheckdata3", " Tmquestionf" + mquestion.size());
            fulChecklist.clear();
            if(mquestion!=null) {
                for (int j=0;j< mquestion.size();j++)
                {
                    fulChecklist.add(new dataChecklist(mquestion.get(j).getOriginAdm(),mquestion.get(j).getCveTripMgmQuestion(),mquestion.get(j).getCveTripMgmSection(),0,"",mquestion.get(j).getCveTripMgmQuestion(),0,0));
                }
            }
            Log.e("finalChecklistdata", " T fulChecklist" +fulChecklist.size());
            // TODO shared preferences  del array de objetos aqui
            filldataAdapter();
        }

    }

    @Override
    public void showDialog() {
        progressDialog.setMessage("Cargando preguntas");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideDialog() {
        progressDialog.dismiss();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttongochecklist:
                presenter.sendfullchecklist(Checkl,aproved);
               // Toast.makeText(getContext(), "mandar Valor de preguntas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.historic_checks:
                menutransition();
                break;
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /// prefix /
                ".jpg",         // suffix /
                storageDir      // directory /
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    @Override
    public void successetCehcklist(String valueSemaforo) {

            traficDialog trafigAlert = new traficDialog();
            trafigAlert.show(getActivity().getSupportFragmentManager(), traficDialog.TAG);
//            trafigAlert.getActivity().getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                @Override
//                public void onBackStackChanged() {
//                    Toast.makeText(getContext(), "listo", Toast.LENGTH_SHORT).show();
//                }});


      // menutransition();//todo este metodo viene del presente cuando envia correctamente el checklist

    }
    public void succestraficDialog(){
        gotohistoric();
    }

    private void askCameraPermissions() {//todo pregunta por los permisos si existen va por la camara
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA} , CAMERA_PERM_CODE);
        } else {
            //Metodo de abrir la Camara
            //openCamera();
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(imageUri!=null) {
        Log.e("imagefromDecode1", "" + resultCode + "  " + imageUri);
        Log.e("imagefromDecode1", "" + ":   " + "" + myimageFile);
        String pathImage = String.valueOf(imageUri);
        String substring = pathImage.substring(1);
        //urispahts.add(substring);

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File f = new File(currentPhotoPath);
               // imageViewP.setImageURI(Uri.fromFile(f));
            }else
            {
                Toast.makeText(getContext(), "Dispositivo no compatible con la funcionalidad", Toast.LENGTH_SHORT).show();
                Log.e("imagefromDecode1","error decoding");
            }
        }else{
            Log.e("imagefromDecode1","error decoding2");
        }
        //} else {

        //}
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int [] grantResults){//todo este permiso entra despues en caso del que permiso no este declarado
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //Abrir camara
                //openCamera();
                dispatchTakePictureIntent();
            } else {
                //Alerta cuando sean necesarios los permisos
                Toast.makeText(getContext(), "Los permisos son necesarios para usar la Camara", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(Objects.requireNonNull(getActivity().getApplicationContext()),
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                //imageUri = photoURI;
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }
    public void takePick(Integer cveTripMgmQuestion) {
        Log.e("takepic",""+cveTripMgmQuestion);
        askCameraPermissions();

        //todo ... se requiere el intent de la cammara y el extra es el base 64 que va en fulchecklist si el cve en esa lista concide





        //todo .....    aqui  va el presenter para la imagen
        //todo .....    se requiere un model para el siguiente endpoint
        //todo ......agregar a endpoints         /dashboard/sendChecklist
        //todo ......  sacar pojo
        // {
        //  "approvement": true,        mandar en true
        //  "cve_trip_mgm_checklist": 0,  takePick
        //  "cve_vehicle": 0,          sacar de shared preferen ces de login
        //  "destination_trip": 0,     este seimpre es 0
        //  "email": "string",         sacar de getfulchecklits
        //  "image": [
        //    {
        //      "cve_trip_mgm_question": 0,  este es el valor de takepick de aqui
        //      "image": "string"
        //    }
        //  ],
        //  "json_answer": "string",      este mandar como el ejemplo de skype
        //  "origin_trip": 0,
        //  "score": 0,                de fullchecklits hacer un for y un add a un int nuevo
        //  "token": "string"         sacara de shared preferecnces
        //}
    }

    public void showerrormistakeanswers() {
        Toast.makeText(getContext(), "sin respuestas configuradas en la web", Toast.LENGTH_SHORT).show();
    }
}

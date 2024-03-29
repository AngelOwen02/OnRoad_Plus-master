package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
 import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.textfield.TextInputEditText;
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
import com.pnla.onroadplus.z_version2.SplashScreenActivity;

import java.io.ByteArrayOutputStream;
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
    private String emailaprovador;
    public static  List<dataChecklist> fulChecklist= new ArrayList<>();

    //camera
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    String currentPhotoPath;
    private File myimageFile;
    private Uri imageUri;
    ImageView imageViewP;
    Bitmap rotatedBitmap = null;
    private Integer cvetemp;
    private String start;
    private View mainView;
    private List<mquestions> mquestion;
    private ConstraintLayout dialog_observatiosn;
    private EditText editextObservations;
    private Button dismissObservations,saveObservation;
    private  Integer cveObservations;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        mainView=view;
        Bundle bundle = this.getArguments();

        if(bundle != null){
            checklistN= bundle.getInt("cveTripMgmSection");
            Checkl= bundle.getInt("Section");
            aproved=bundle.getBoolean("aprobador");
            emailaprovador= bundle.getString("emailaprobador");
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
        dialog_observatiosn=view.findViewById(R.id.dialog_observatiosn);
        dialog_observatiosn.setOnClickListener(this);
        dismissObservations=view.findViewById(R.id.dismissObservations);
        dismissObservations.setOnClickListener(this);
        saveObservation=view.findViewById(R.id.saveObservation);
        saveObservation.setOnClickListener(this);
        editextObservations=view.findViewById(R.id.editextObservations);
        progressDialog = new ProgressDialog(getActivity());
        presenter=new questionsPresenterImpl(this,getContext());
        presenter.getpSections(Checkl);

        Date currentDate = new Date();

        // Define the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time
        start = dateFormat.format(currentDate);
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
    public void safeValues(int position, boolean b, int value, int i, Integer cveTripMgmQuestion, Integer score, Integer cveTripMgmAnswer,String openAnswer)
    {
        List<dataChecklist> fulChecklist2 = new ArrayList<>();
        fulChecklist2.clear();
        fulChecklist2=fulChecklist;
        String h = null;

        for(int v=0;v<fulChecklist.size();v++)
        {
            if(fulChecklist.get(v).getAnswerId()==cveTripMgmQuestion)
            {
                h= String.valueOf( v);
            }
        }
        int iterator=Integer.valueOf( h);
        //Before
        //After


        fulChecklist2.get(iterator).setAnswerPos(value);
        fulChecklist2.get(iterator).setScore(score);

        fulChecklist2.get(iterator).setCveTripMgmAnswer(cveTripMgmAnswer);
        fulChecklist2.get(iterator).setDesc_trip_mgm_answer(openAnswer);
        fulChecklist=fulChecklist2;
    }

    @SuppressLint("NewApi")   /** ESTE METODO SALVA LA INFORMACION DE TODAS LAS PREGUNTAS EN SHARED PREFERENCES*/
    @Override
    public void setQuestions(List<dataQuestions> data) {
        this.dataQuestions1=data;
        if(dataQuestions1!=null)
        {
           Log.e("finalChecklistdata","inpect questions: "+dataQuestions1.size()+"   fulChecklist: "+fulChecklist.size());//+"  "+dataSections.get(1).getDescTripMgmSection() // movedots(0);
            mquestion=new ArrayList<>();
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
                    fulChecklist.add(new dataChecklist(mquestion.get(j).getOriginAdm(),mquestion.get(j).getCveTripMgmQuestion(),mquestion.get(j).getCveTripMgmSection(),0,"",mquestion.get(j).getCveTripMgmQuestion(),0,0,"",""));
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
                presenter.sendfullchecklist(Checkl,aproved,emailaprovador,start);
                SplashScreenActivity.selectedVehicle = false;
               // Toast.makeText(getContext(), "mandar Valor de preguntas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.historic_checks:
                menutransition();
                break;
            case  R.id.dismissObservations:
                editextObservations.setText("");
                dialog_observatiosn.setVisibility(View.GONE);
                break;
            case R.id.saveObservation:
                //todo fullchecklist agregar el parametro
                //Toast.makeText(getContext(), "Salvar parametro", Toast.LENGTH_SHORT).show();
                if(!editextObservations.getText().equals("")){
                    List<dataChecklist> saveObservations=new ArrayList<>();
                    saveObservations.clear();
                    for(dataChecklist q:fulChecklist){
                        if(q.getCveTripMgmQuestion()==cveObservations)
                        {
                            q.setComments(editextObservations.getText().toString());
                        }
                        saveObservations.add(q);
                    }
                    fulChecklist.clear();
                    fulChecklist=saveObservations;
                }
                editextObservations.setText("");
                dialog_observatiosn.setVisibility(View.GONE);
                break;
            case  R.id.dialog_observatiosn:
                editextObservations.setText(" ");
                editextObservations.setText("");
                dialog_observatiosn.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public void successetCehcklist(String valueSemaforo, int finalscore, boolean aprobacionR) {

            Bundle args = new Bundle();
            args.putString("semaforofinal", valueSemaforo);
            args.putInt("finalscore",finalscore);
            args.putBoolean("reqaprob",aprobacionR);
            traficDialog trafigAlert = new traficDialog();
            trafigAlert.setArguments(args);
            trafigAlert.show(getActivity().getSupportFragmentManager(), traficDialog.TAG);



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
        Log.e("photoFlow","rC:   "+requestCode);
        Log.e("photoFlow","resC: "+resultCode);
        Log.e("photoFlow","data: "+data);
    //    Log.e("imagefromDecode1", "" + resultCode + "  " + imageUri);
       // Log.e("imagefromDecode1", "" + ":   " + "" + myimageFile);
        String pathImage = String.valueOf(imageUri);
        String substring = pathImage.substring(1);
        //urispahts.add(substring);

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                getBase64();
               // File f = new File(currentPhotoPath);

               // imageViewP.setImageURI(Uri.fromFile(f));
                ErradicatedFiler();
            }  else
            {
                //File file =new File(imageUri.getPath());

                ErradicatedFiler();
            }
        }else{
            Log.e("imagefromDecode1","error decoding2");
        }
        //} else {

        //}
    }

    private void getBase64() {


        Bitmap bitmap1= BitmapFactory.decodeFile(currentPhotoPath);
        Bitmap bitmap= Bitmap.createScaledBitmap(bitmap1, 460, 460, false);
        try {
            ExifInterface exifInterface = new ExifInterface(currentPhotoPath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap,90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap,180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap,270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 2, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        Log.e("photoFlow2",encoded);
        for(int i=0;i<fulChecklist.size();i++)
        {
            if(fulChecklist.get(i).getCveTripMgmQuestion()==cvetemp)
            {
                fulChecklist.get(i).setFoto(encoded);
            }
        }
    }

    private void ErradicatedFiler()
    {
        if(myimageFile.exists())
        {
            try {
                myimageFile.getCanonicalFile().delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(myimageFile.exists()){
                getContext().deleteFile(myimageFile.getName());
            }
        }
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
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        myimageFile = File.createTempFile(
                imageFileName,  /// prefix /
                ".jpg",         // suffix /
                storageDir      // directory /
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = myimageFile.getAbsolutePath();
        return myimageFile;
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
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }
    public void errorpic() {
        Toast.makeText(getContext(), "Dispositivo no compatible con la funcionalidad", Toast.LENGTH_SHORT).show();
    }
    public void showObservations(Integer mcveObservations) {
        this.cveObservations=mcveObservations;
        dialog_observatiosn.setVisibility(View.VISIBLE);
    }
    public void takePick(Integer cveTripMgmQuestion) {
        this.cvetemp=cveTripMgmQuestion;
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


    public void showTooltipi(Integer cveTripMgmQuestion) {

    for(mquestions q:mquestion)
    {
        if(q.getCveTripMgmQuestion()==cveTripMgmQuestion)
        {
            if(!q.getInstructions().equals("")&&q.getInstructions()!=null&&!q.getInstructions().isEmpty()&&!q.getInstructions().equals(" ")) {
                Toast.makeText(getContext(), "" + q.getInstructions(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    //todo hacer un toad¿s flotante para cada boton que corresponda con las cordenadas de la pantalla principal

//        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//        View inflatedView = layoutInflater.inflate(R.layout.item_floating_toast, null, false);
//
//        ImageView imageView = inflatedView.findViewById(R.id.imageView5);
//        imageView.setX(10f);
//        imageView.setY(80f);



    }


}

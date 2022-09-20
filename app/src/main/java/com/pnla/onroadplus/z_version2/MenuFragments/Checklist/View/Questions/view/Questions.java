package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.sectionsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.mquestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter.questionPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter.questionsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class Questions  extends Fragment implements View.OnClickListener ,questionView{
    public static final String TAG = Questions.class.getSimpleName();
    private sectionsAdapter adapterQuestionary;
 //   private CardView cardView;
    private ViewPager2 pager;
    private PagerAdapter pAdapter;
    private LinearLayout dotslayout;
    private int sizeArange;
    private Button buttongochecklist;
    private ProgressDialog progressDialog;
    private List<dataSections> dataSections;
    private List<dataQuestions> dataQuestions1;
    private questionPresenter presenter;
    private boolean isfirsttime=false;
    public static int posrv;
    private sectionsAdapter sA;
    private TextView titlefileds;
    private int checklistN,Checkl;
    public static  List<dataChecklist> fulChecklist= new ArrayList<>();
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            checklistN= bundle.getInt("cveTripMgmSection");
            Checkl= bundle.getInt("Section");
        }
        initContactView(view);

      // /** esto inicializa la posicion y el numero de puntos del quiestionario*/
        return view;
    }

    @SuppressLint("NewApi")
    private void initContactView(View view) {
       // cardView=view.findViewById(R.id.cardviewitem);

        pager = (ViewPager2) view.findViewById(R.id.cardviewitem);
        dotslayout = view.findViewById(R.id.dots_layout);
        buttongochecklist=view.findViewById(R.id.buttongochecklist);
        titlefileds=view.findViewById(R.id.titlefileds);
        buttongochecklist.setOnClickListener(this);
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
    public void safeValues(int position, boolean b, int value, int i, Integer cveTripMgmQuestion)
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
        Log.e("finalCheckdata4","index "+fulChecklist.get(iterator).getAnswerPos());
    }

    @SuppressLint("NewApi")   /** ESTE METODO SALVA LA INFORMACION DE TODAS LAS PREGUNTAS EN SHARED PREFERENCES*/
    @Override
    public void setQuestions(List<dataQuestions> data) {
        this.dataQuestions1=data;
        if(dataQuestions1!=null)
        {
           Log.e("finalCheckdata3","inpect questions: "+dataQuestions1.size()+"   fulChecklist: "+fulChecklist.size());//+"  "+dataSections.get(1).getDescTripMgmSection() // movedots(0);
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
                    fulChecklist.add(new dataChecklist(mquestion.get(j).getOriginAdm(),mquestion.get(j).getCveTripMgmQuestion(),mquestion.get(j).getCveTripMgmSection(),0,"",mquestion.get(j).getCveTripMgmQuestion()));
                }
            }
            Log.e("finalCheckdata3", " T fulChecklist" +fulChecklist.size());
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
                presenter.sendfullchecklist();
                Toast.makeText(getContext(), "mandar Valor de preguntas", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void successetCehcklist() {
       menutransition();//todo este metodo viene del presente cuando envia correctamente el checklist
    }



    public void takePick(Integer cveTripMgmQuestion) {
        Log.e("takepic",""+cveTripMgmQuestion);
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
}

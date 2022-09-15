package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.sectionsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklistHistoric;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.Answer;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.mquestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter.questionPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.presenter.questionsPresenterImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

public class Questions  extends Fragment implements View.OnClickListener ,questionView {

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
    private int checklistN;
    private ImageView btnSearch;

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            checklistN= bundle.getInt("cveTripMgmSection");
        }
        initContactView(view);

      // /** esto inicializa la posicion y el numero de puntos del quiestionario*/
        return view;
    }

    @SuppressLint("NewApi")
    private void initContactView(View view) {
       // cardView=view.findViewById(R.id.cardviewitem);

        btnSearch = view.findViewById(R.id.search_checkList);
        btnSearch.setVisibility(View.INVISIBLE);

        pager = (ViewPager2) view.findViewById(R.id.cardviewitem);
        dotslayout = view.findViewById(R.id.dots_layout);
        buttongochecklist=view.findViewById(R.id.buttongochecklist);
        titlefileds=view.findViewById(R.id.titlefileds);
        buttongochecklist.setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        presenter=new questionsPresenterImpl(this,getContext());
        presenter.getpSections(checklistN);


    }

    void filldataAdapter() {
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

    private void menutransition() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CheckListViewImpl checklist = new CheckListViewImpl();
        transaction.replace(R.id.conteinerMainFragments, checklist, CheckListViewImpl.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    @Override
    //Pressed return button - returns to the results menu
    public void onResume() {
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

    @SuppressLint("NewApi")
    public void movedots(int position) {

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

    @Override
    public void setSections(List<dataSections> data) {
        this.dataSections=data;
        if(dataSections!=null)
        {
            sizeArange= dataSections.size();

            if(sizeArange!=0) {
                if (isfirsttime == false) {
                    posrv=0;
                    presenter.getpQuestions(checklistN);//dataSections.get(0).getCveTripMgmSection());
                   // movedots(0);

                    isfirsttime = true;
                } else {
                }
            }else{
                Toast.makeText(getContext(), "sin datos", Toast.LENGTH_SHORT).show();
            }


        }
    }

    @SuppressLint("NewApi")
    @Override
    public void setQuestions(List<dataQuestions> data) {
        this.dataQuestions1=data;
        if(dataQuestions1!=null) {
               //Log.e("checklistQuestions","setQuestions: "+"size: "+dataQuestions1.size()+"       " +dataQuestions1.get(4).getQuestions().size()+"   pointspos:"+posrv);//+"  "+dataSections.get(1).getDescTripMgmSection()

           // movedots(0);

           // TODO shared preferences  del array de objetos aqui

            if(CheckListViewImpl.fulChecklist!=null){//sie el cheklist esta vacio
                Log.e("fulChecklistF","if case "+CheckListViewImpl.fulChecklist);
                Log.e("fulChecklistF","if case "+CheckListViewImpl.fulChecklist.size());//TODO esto deberia comprobarse desde shared preferences
            }else                                     //de no estar vacio comprobar con los valores registrados
            {
                CheckListViewImpl.fulChecklist=new ArrayList<>();
                CheckListViewImpl.fulChecklist.clear();
                List<dataChecklist> fulChecklist=new ArrayList<>();
                fulChecklist.clear();
                for (int i=0;i<dataQuestions1.size();i++)/**secciones*/
                {
                    Log.e("fulChecklistS",dataQuestions1.get(i).getDescTripMgmSection()+"    "+dataQuestions1.get(i).getCveTripMgmSection());
                    List<mquestions> mq= dataQuestions1.get(i).getQuestions();
                    if(mq!=null)
                    {

                        for(int k=0;k<mq.size();k++)   /**preguntas*/
                        {
                            List<String> answer=new ArrayList<>();
                            answer.clear();
                            List<Answer> Ans= mq.get(k).getAnswers();
                            if(Ans!=null)
                            {
                                for(int j=0;j<Ans.size();j++)    /** respuestas*/
                                {
                                    answer.add(dataQuestions1.get(i).getQuestions().get(k).getAnswers().get(j).getDescTripMgmAnswer());
                                    Log.e("fulChecklistA",""+dataQuestions1.get(i).getQuestions().get(k).getAnswers().get(j).getDescTripMgmAnswer());
                                }
                            }
                           /* Log.e("fulChecklistQ","origin: "+dataQuestions1.get(i).getQuestions().get(k).getOriginAdm()+         //oirigen
                                       " section: "+dataQuestions1.get(i).getCveTripMgmSection()+
                                       " cve: "+dataQuestions1.get(i).getQuestions().get(k).getCveTripMgmQuestion()+                        //cve pregunta
                                       " Q: "+dataQuestions1.get(i).getQuestions().get(k).getDescTripMgmQuestion()+                         //pregunta txt
                                       " A: "+answer);                                                                                      //posibles respuestas*/


                            dataChecklist dataChecklist1=new dataChecklist(dataQuestions1.get(i).getOriginAdm(),                    //se agregan todos los
                                                                 dataQuestions1.get(i).getQuestions().get(k).getCveTripMgmQuestion(),
                                                                 dataQuestions1.get(i).getQuestions().get(k).getCveTripMgmSection(),0,"");
                            Log.e("fulChecklistF",""+dataQuestions1.get(i).getCveTripMgmSection()+"   "+dataQuestions1.get(i).getQuestions().get(k).getCveTripMgmSection());
                            if(dataChecklist1.getSection()==dataQuestions1.get(i).getCveTripMgmSection()) {
                                fulChecklist.add(dataChecklist1);
                            }

                        }
                        List<dataChecklist> fulChecklist2=new ArrayList<>();
                        fulChecklist2.clear();
                        for(int o=0;o<fulChecklist.size();o++)
                        {
                            if(fulChecklist.get(o).getSection()==dataQuestions1.get(i).getCveTripMgmSection())
                            {
                                fulChecklist2.add(fulChecklist.get(o));
                            }
                        }
                        CheckListViewImpl.fulChecklist.add(i,fulChecklist2);
//  Log.e("fulChecklistF",""+dataQuestions1.get(i).getCveTripMgmSection());

//    private int origin;
//    private String answerId;
//    private int section;
//    private int answerPos;
//    private String foto;

                    }
                  /** este dato se itera 7 veces*/

                }
                Log.e("fulChecklistF","else case "+ CheckListViewImpl.fulChecklist.size());
                //TODO-DONE aqui deberiamos guardar el valor en shared preferences;
                SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(CheckListViewImpl.fulChecklist);
                editor.putString(GeneralConstantsV2.CHECKLIST_DATA, json);
                editor.commit();
            }
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
                menutransition();
                Toast.makeText(getContext(), "mandar Valor de preguntas", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

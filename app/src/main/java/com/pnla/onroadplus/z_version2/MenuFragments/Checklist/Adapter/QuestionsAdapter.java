package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.Answer;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder>{
    private Questions myview;
    private Context context;
    private dataQuestions data;
    private ArrayAdapter<String> spinnerArrayAdapter;
    private boolean poss=false;
    private List<dataChecklist> saved=new ArrayList<>();
    private int currentSection;
    private boolean firstspinner=false;
    public QuestionsAdapter(dataQuestions dataQuestions, Questions mview, Context context) {
        this.myview=mview;
        this.context=context;
        this.data=dataQuestions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_questions_type, parent, false);
        return new QuestionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if(data!=null) {
            if (data.getQuestions().get(position).getAnswers()!=null){
                if (data.getQuestions().get(position).getAnswers().get(0).getObjectType() == 3) {//esto es un spiner
                    Log.e("multianswerType", " : " + position);
                    holder.optionanswer.setVisibility(View.VISIBLE);
                    holder.openanswer.setVisibility(View.GONE);
                    holder.switchanswer.setVisibility(View.GONE);
                    holder.description1.setText(data.getQuestions().get(position).getDescTripMgmQuestion());
                    List<String> arrayAdapter = new ArrayList<>();

                    for (int i = 0; i < data.getQuestions().get(position).getAnswers().size(); i++) {
                        arrayAdapter.add(data.getQuestions().get(position).getAnswers().get(i).getDescTripMgmAnswer());
                    }

                    arrayAdapter.add(0, "Selecciona una opción");
                    spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, arrayAdapter);//simple_spinner_item ::   simple_spinner_dropdown_item
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    holder.spinnerquestionary.setAdapter(spinnerArrayAdapter);
                    holder.spinnerquestionary.setSelection(0, false);
                    if (Questions.fulChecklist != null) {

                        for (int i = 0; i < Questions.fulChecklist.size(); i++) {
                            Log.e("finalCheckdata6", " " + data.getQuestions().get(position).getCveTripMgmQuestion() + "   " + Questions.fulChecklist.get(i).getAnswerId());//Questions.fulChecklist.get(i).getAnswerId()
                            if (Questions.fulChecklist.get(i).getCveTripMgmQuestion() == data.getQuestions().get(position).getCveTripMgmQuestion())//todo getAnswerPos() es la pregunta cambiar nombre
                            {
                                //Log.e("finalCheckdata6"," "+Questions.fulChecklist.get(i).getAnswerPos());
                                Log.e("finalCheckdata7"," "+ holder.spinnerquestionary.getSelectedItem().toString());
                                //  if(holder.spinnerquestionary.getSelectedItem().toString().equals("Selecciona una opción"))
                                //  {
                                //   holder.spinnerquestionary.setSelection(0, false);//https://stackoverflow.com/questions/2562248/how-to-keep-onitemselected-from-firing-off-on-a-newly-instantiated-spinner
                                //  }else{
                                holder.spinnerquestionary.setSelection(Questions.fulChecklist.get(i).getAnswerPos());
                                // }
                            }
                        }
                    }

                    //  holder.spinnerquestionary.setSelection(2);

                    holder.spinnerquestionary.post(new Runnable() {
                        public void run() {
                            holder.spinnerquestionary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                 //   Log.e("finalCheckdata5", " " + Questions.fulChecklist.size());

                                    //todo  posiciondepregunta | switchboolean | valueAnswerpos | type: 1,2   1  switch 2  multiple
                                    Log.e("finalCheckdata7", " sectionP :  " + data.getQuestions().get(position).getCveTripMgmSection() +
                                            " QuestionP : " + position +
                                            " answerP :   " + i);
                                    Log.e("finalCheckdata7",""+adapterView.getPositionForView(view));
                                    Log.e("finalCheckdata7",""+adapterView.getPositionForView(view));
                                    if (i == 0) {
                                        myview.safeValues(position, false, i, 3, data.getQuestions().get(position).getCveTripMgmQuestion(), 0, null,null);

                                    } else {
                                        myview.safeValues(position, false, i, 3, data.getQuestions().get(position).getCveTripMgmQuestion(), data.getQuestions().get(position).getAnswers().get(i - 1).getTripMgmAnswerValue(), data.getQuestions().get(position).getAnswers().get(i - 1).getCveTripMgmAnswer(),null);
                                    }
                                  //  Log.e("dapterv3Q","Q: "+data.getQuestions().get(position).getCveTripMgmQuestion()+" selected: "+ data.getQuestions().get(position).getAnswers().get(i - 1).getCveTripMgmAnswer());

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        }
                    });
                } else if (data.getQuestions().get(position).getAnswers().get(0).getObjectType() == 2) {                                                         //switches
                    holder.optionanswer.setVisibility(View.GONE);
                    holder.openanswer.setVisibility(View.GONE);
                    holder.switchanswer.setVisibility(View.VISIBLE);
                    holder.description2.setText(data.getQuestions().get(position).getDescTripMgmQuestion());

                    if (Questions.fulChecklist != null) {

                        for (int i = 0; i < Questions.fulChecklist.size(); i++) {
                            Log.e("finalCheckdata6", " " + data.getQuestions().get(position).getCveTripMgmQuestion() + "   " + Questions.fulChecklist.get(i).getAnswerId());//Questions.fulChecklist.get(i).getAnswerId()
                            if (Questions.fulChecklist.get(i).getCveTripMgmQuestion() == data.getQuestions().get(position).getCveTripMgmQuestion())//todo getAnswerPos() es la pregunta cambiar nombre
                            {
                                // Log.e("finalCheckdata6"," "+Questions.fulChecklist.get(i).getAnswerPos());
                                if (Questions.fulChecklist.get(i).getAnswerPos() == 0) {
                                    holder.switchquestionary.setChecked(false);
                                    myview.safeValues(position, false, 0, 2, data.getQuestions().get(position).getCveTripMgmQuestion(), data.getQuestions().get(position).getAnswers().get(1).getTripMgmAnswerValue(), data.getQuestions().get(position).getAnswers().get(1).getCveTripMgmAnswer(),null);
                                } else {
                                    holder.switchquestionary.setChecked(true);
                                }
                            }

                        }
                    }

                    holder.switchquestionary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            Log.e("finalCheckdata4", "section : " + data.getQuestions().get(position).getCveTripMgmSection() +
                                    " switchpos: " + position +
                                    " answer: " + b);
                            //todo  posiciondepregunta | switchboolean | valueAnswerpos | type: 1,2   1  switch 2  multiple
                            if (b == false) {
                                myview.safeValues(position, b, 0, 2, data.getQuestions().get(position).getCveTripMgmQuestion(), data.getQuestions().get(position).getAnswers().get(1).getTripMgmAnswerValue(), data.getQuestions().get(position).getAnswers().get(1).getCveTripMgmAnswer(),null);
                            } else {
                                myview.safeValues(position, b, 1, 2, data.getQuestions().get(position).getCveTripMgmQuestion(), data.getQuestions().get(position).getAnswers().get(0).getTripMgmAnswerValue(), data.getQuestions().get(position).getAnswers().get(0).getCveTripMgmAnswer(),null);
                            }
                        }
                    });

                }else if (data.getQuestions().get(position).getAnswers().get(0).getObjectType() == 1){
                    holder.textopenquestion1.setText(data.getQuestions().get(position).getDescTripMgmQuestion());
                    if(Questions.fulChecklist != null&&!Questions.fulChecklist.equals(""))
                    {

                        for (int i = 0; i < Questions.fulChecklist.size(); i++) {

                            if (Questions.fulChecklist.get(i).getCveTripMgmQuestion() == data.getQuestions().get(position).getCveTripMgmQuestion()) {
                                myview.safeValues(position,false,1,1,data.getQuestions().get(position).getCveTripMgmQuestion(),data.getQuestions().get(position).getAnswers().get(0).getTripMgmAnswerValue(),data.getQuestions().get(position).getAnswers().get(0).getCveTripMgmAnswer(),Questions.fulChecklist.get(i).getDesc_trip_mgm_answer());
                                holder.inputText.setText(Questions.fulChecklist.get(i).getDesc_trip_mgm_answer());
                            }
                        }

                    }else
                    {
                        myview.safeValues(position,false,1,1,data.getQuestions().get(position).getCveTripMgmQuestion(),data.getQuestions().get(position).getAnswers().get(0).getTripMgmAnswerValue(),data.getQuestions().get(position).getAnswers().get(0).getCveTripMgmAnswer(),"");
                    }

                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence textWatcher, int i, int i1, int i2) {
                            Log.e("textWatcher","beforeTextChanged: "+textWatcher);

                         }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            Log.e("textWatcher","onTextChanged: "+charSequence);
                           // myview.safeValues(position,false,1,1,data.getQuestions().get(position).getCveTripMgmQuestion(),data.getQuestions().get(position).getAnswers().get(0).getTripMgmAnswerValue(),data.getQuestions().get(position).getAnswers().get(0).getCveTripMgmAnswer(),charSequence.toString());
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            Log.e("textWatcher","afterTextChanged: "+editable.toString());

                            //todo  posiciondepregunta | switchboolean | valueAnswerpos | type: 1,2   1 ~ switch 2 ~ multiple / cveTripMgmQuestion / score /cveTripMgmAnswer
                           /* List<Answer> answers=new ArrayList<>();
                            answers.clear();
                            Answer answer0=new Answer(data.getOriginAdm(),data.getQuestions().get(position).getCveTripMgmSection(),data.getQuestions().get(position).getCveTripMgmQuestion(),data.getQuestions().get(position).getAnswers().get(0).getCveTripMgmAnswer(),editable.toString(),data.getQuestions().get(position).getAnswers().get(0).getTripMgmAnswerValue(),1);
                            answers.add(answer0);
                            data.getQuestions().get(position).setAnswers(answers);*/
                            myview.safeValues(position,false,1,1,data.getQuestions().get(position).getCveTripMgmQuestion(),data.getQuestions().get(position).getAnswers().get(0).getTripMgmAnswerValue(),data.getQuestions().get(position).getAnswers().get(0).getCveTripMgmAnswer(),editable.toString());
                            /**spinner*/     //  myview.safeValues(position, false, i, 2, data.getQuestions().get(position).getCveTripMgmQuestion(), 0, null);
                            /**switch*/      //  myview.safeValues(position, b, 1, 1, data.getQuestions().get(position).getCveTripMgmQuestion(), data.getQuestions().get(position).getAnswers().get(0).getTripMgmAnswerValue(), data.getQuestions().get(position).getAnswers().get(0).getCveTripMgmAnswer());

                        }
                    });
                }else{

                }
            }else
            {
                myview.showerrormistakeanswers();
            }
        }
        if(data.getQuestions().get(position).getInstructions()!=null){
            if(data.getQuestions().get(position).getInstructions().equals(""))
            {
                holder.info1.setVisibility(View.GONE);
                holder.info2.setVisibility(View.GONE);
                holder.info3.setVisibility(View.GONE);
            }else {
                holder.info1.setVisibility(View.VISIBLE);
                holder.info2.setVisibility(View.VISIBLE);
                holder.info3.setVisibility(View.VISIBLE);
            }
        }else {
            holder.info1.setVisibility(View.GONE);
            holder.info2.setVisibility(View.GONE);
            holder.info3.setVisibility(View.GONE);
        }
        if(data.getQuestions().get(position).isRequired_evidence()==false)
        {
            holder.imagephoto.setVisibility(View.GONE);
            holder.imagephoto1.setVisibility(View.GONE);
            holder.imagephoto2.setVisibility(View.GONE);
        }else{
            holder.imagephoto.setVisibility(View.VISIBLE);
            holder.imagephoto1.setVisibility(View.VISIBLE);
            holder.imagephoto2.setVisibility(View.VISIBLE);
        }
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q) {

            holder.imagephoto2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myview.takePick(data.getQuestions().get(position).getCveTripMgmQuestion());
                    Log.e("photoFlow", "take pic");
                }
            });
            holder.imagephoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myview.takePick(data.getQuestions().get(position).getCveTripMgmQuestion());
                    Log.e("photoFlow", "take pic");
                }
            });
            holder.imagephoto1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myview.takePick(data.getQuestions().get(position).getCveTripMgmQuestion());
                    Log.e("photoFlow", "take pic");
                }
            });
        }else
        {
            holder.imagephoto.setVisibility(View.GONE);
            holder.imagephoto2.setVisibility(View.GONE);
            //myview.errorpic();
        }
        holder.info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myview.showTooltipi(data.getQuestions().get(position).getCveTripMgmQuestion());
            }
        });
        holder.info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myview.showTooltipi(data.getQuestions().get(position).getCveTripMgmQuestion());
            }
        });
        holder.info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myview.showTooltipi(data.getQuestions().get(position).getCveTripMgmQuestion());
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.getQuestions().size();
    }

    public void setvalues(dataQuestions ndata) {
        this.data=ndata;

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout switchanswer,optionanswer,openanswer;
        TextView description1,description2,textopenquestion1;
        Spinner spinnerquestionary;
        Switch switchquestionary;
        private ImageView imagephoto,imagephoto2,imagephoto1,info1,info2,info3;
        private TextInputEditText inputText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagephoto2=itemView.findViewById(R.id.imagephoto2);
            imagephoto=itemView.findViewById(R.id.imagephoto);
            imagephoto1=itemView.findViewById(R.id.imagephoto1);
            switchquestionary=itemView.findViewById(R.id.switchquestionary);
            switchanswer=itemView.findViewById(R.id.switchanswer);
            optionanswer=itemView.findViewById(R.id.optionanswer);
            openanswer=itemView.findViewById(R.id.openanswer);
            textopenquestion1 =itemView.findViewById(R.id.textopenquestion1);
            inputText=itemView.findViewById(R.id.inputText);
            description1=itemView.findViewById(R.id.textopenquestion); //open
            description2=itemView.findViewById(R.id.textbooleanonly);  //bool
            spinnerquestionary=itemView.findViewById(R.id.spinnerquestionary);
            info1=itemView.findViewById(R.id.info1);
            info2=itemView.findViewById(R.id.info2);
            info3=itemView.findViewById(R.id.info3);

        }
    }
}
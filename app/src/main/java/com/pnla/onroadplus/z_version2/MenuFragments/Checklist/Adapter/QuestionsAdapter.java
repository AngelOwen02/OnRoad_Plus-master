package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
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
    public void onBindViewHolder(@NonNull QuestionsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        if(data!=null) {
            if (data.getQuestions().get(position).getAnswers()!=null){
                if (data.getQuestions().get(position).getAnswers().size() != 2) {//esto es un spiner
                    Log.e("multianswerType", " : " + position);
                    holder.optionanswer.setVisibility(View.VISIBLE);
                    holder.switchanswer.setVisibility(View.GONE);
                    holder.description1.setText(data.getQuestions().get(position).getDescTripMgmQuestion());
                    List<String> arrayAdapter = new ArrayList<>();

                    for (int i = 0; i < data.getQuestions().get(position).getAnswers().size(); i++) {
                        arrayAdapter.add(data.getQuestions().get(position).getAnswers().get(i).getDescTripMgmAnswer());
                    }

                    arrayAdapter.add(0, "Selecciona una opción");
                    spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, arrayAdapter);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    holder.spinnerquestionary.setAdapter(spinnerArrayAdapter);

                    if (Questions.fulChecklist != null) {

                        for (int i = 0; i < Questions.fulChecklist.size(); i++) {
                            Log.e("finalCheckdata6", " " + data.getQuestions().get(position).getCveTripMgmQuestion() + "   " + Questions.fulChecklist.get(i).getAnswerId());//Questions.fulChecklist.get(i).getAnswerId()
                            if (Questions.fulChecklist.get(i).getCveTripMgmQuestion() == data.getQuestions().get(position).getCveTripMgmQuestion())//todo getAnswerPos() es la pregunta cambiar nombre
                            {
                                // Log.e("finalCheckdata6"," "+Questions.fulChecklist.get(i).getAnswerPos());
                                holder.spinnerquestionary.setSelection(Questions.fulChecklist.get(i).getAnswerPos());
                            }

                        }
                    }

                    //  holder.spinnerquestionary.setSelection(2);


                    holder.spinnerquestionary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Log.e("finalCheckdata5", " " + Questions.fulChecklist.size());

                            //todo  posiciondepregunta | switchboolean | valueAnswerpos | type: 1,2   1 ~ switch 2 ~ multiple
                            Log.e("finalCheckdata4", " sectionP :  " + data.getQuestions().get(position).getCveTripMgmSection() +
                                    " QuestionP : " + position +
                                    " answerP :   " + i);
                            if (i == 0) {
                                myview.safeValues(position, false, i, 2, data.getQuestions().get(position).getCveTripMgmQuestion(), 0, null);
                            } else {
                                myview.safeValues(position, false, i, 2, data.getQuestions().get(position).getCveTripMgmQuestion(), data.getQuestions().get(position).getAnswers().get(i - 1).getTripMgmAnswerValue(), data.getQuestions().get(position).getAnswers().get(i - 1).getCveTripMgmAnswer());
                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                } else {                                                         //switches
                    holder.optionanswer.setVisibility(View.GONE);
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
                            //todo  posiciondepregunta | switchboolean | valueAnswerpos | type: 1,2   1 ~ switch 2 ~ multiple
                            if (b == false) {
                                myview.safeValues(position, b, 0, 1, data.getQuestions().get(position).getCveTripMgmQuestion(), data.getQuestions().get(position).getAnswers().get(1).getTripMgmAnswerValue(), data.getQuestions().get(position).getAnswers().get(1).getCveTripMgmAnswer());
                            } else {
                                myview.safeValues(position, b, 1, 1, data.getQuestions().get(position).getCveTripMgmQuestion(), data.getQuestions().get(position).getAnswers().get(0).getTripMgmAnswerValue(), data.getQuestions().get(position).getAnswers().get(0).getCveTripMgmAnswer());
                            }
                        }
                    });

                }
            }else
            {
                myview.showerrormistakeanswers();
            }
        }
        holder.imagephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myview.takePick(data.getQuestions().get(position).getCveTripMgmQuestion());
            }
        });
        holder.imagephoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myview.takePick(data.getQuestions().get(position).getCveTripMgmQuestion());
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
    ConstraintLayout switchanswer,optionanswer;
    TextView description1,description2;
    Spinner spinnerquestionary;
    Switch switchquestionary;
    private ImageView imagephoto,imagephoto2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagephoto2=itemView.findViewById(R.id.imagephoto2);
            imagephoto=itemView.findViewById(R.id.imagephoto);
            switchquestionary=itemView.findViewById(R.id.switchquestionary);
            switchanswer=itemView.findViewById(R.id.switchanswer);
            optionanswer=itemView.findViewById(R.id.optionanswer);
            description1=itemView.findViewById(R.id.textopenquestion); //open
            description2=itemView.findViewById(R.id.textbooleanonly);  //bool
            spinnerquestionary=itemView.findViewById(R.id.spinnerquestionary);


        }
    }
}

package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

    public QuestionsAdapter(dataQuestions dataQuestions, Questions mview, Context context) {
        this.myview=myview;
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
        if(poss==false) {
            Log.e("finalcheck", "heredada " + Questions.posrv);
            poss=true;
        }

        if (data.getQuestions().get(position).getAnswers().size()!=2) {//si no son booleanas
            Log.e("multianswerType"," : "+position);
            holder.optionanswer.setVisibility(View.VISIBLE);
            holder.switchanswer.setVisibility(View.GONE);
            holder.description1.setText(data.getQuestions().get(position).getDescTripMgmQuestion());
            List<String> arrayAdapter=new ArrayList<>();

            for(int i=0;i<data.getQuestions().get(position).getAnswers().size();i++)
            {
                arrayAdapter.add(data.getQuestions().get(position).getAnswers().get(i).getDescTripMgmAnswer());
            }

            arrayAdapter.add(0,"-- Selecciona Una opcion. --");
            spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, arrayAdapter );
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spinnerquestionary.setAdapter(spinnerArrayAdapter);
            //holder.spinnerquestionary.setPrompt("Selecciona una opcion ");
  /*         for(int k=0;k<data.getQuestions().get(position).getAnswers().size();k++)
            {
                final int finalK = k;
                data.getQuestions().get(position).getAnswers().get(k).getObjectType()

            }*/
            holder.spinnerquestionary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    /**    */
                    Log.e("spinnerquestionary"," sectionP :  "+data.getQuestions().get(position).getCveTripMgmSection()+
                                                         " QuestionP : "+position+
                                                         " answerP :   "+i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }else{                                                         //son preguntas abiertas
            holder.optionanswer.setVisibility(View.GONE);
            holder.switchanswer.setVisibility(View.VISIBLE);
            holder.description2.setText(data.getQuestions().get(position).getDescTripMgmQuestion());
        }

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            switchanswer=itemView.findViewById(R.id.switchanswer);
            optionanswer=itemView.findViewById(R.id.optionanswer);
            description1=itemView.findViewById(R.id.textopenquestion); //open
            description2=itemView.findViewById(R.id.textbooleanonly);  //bool
            spinnerquestionary=itemView.findViewById(R.id.spinnerquestionary);


        }
    }
}

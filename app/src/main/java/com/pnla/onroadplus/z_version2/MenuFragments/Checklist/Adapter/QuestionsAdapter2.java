package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.mquestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections.dataSections;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;

import java.util.List;

public class QuestionsAdapter2 extends RecyclerView.Adapter<QuestionsAdapter2.ViewHolder>{
    private Questions myview;
    private Context context;
    private List<mquestions> data;
    //  private mquestions mquestions;
//
    public QuestionsAdapter2(List<mquestions> data, Questions myview, Context context) {
        this.myview=myview;
        this.context=context;
        this.data=data;
        // this.mquestions=mquestions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_questions_type, parent, false);
        return new QuestionsAdapter2.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter2.ViewHolder holder, int position) {

        if(data.get(position).getAnswers().size()>2)
        {
            holder.optionanswer.setVisibility(View.GONE);
//            holder.switchanswer.setVisibility(View.VISIBLE);
        }else {
            holder.optionanswer.setVisibility(View.VISIBLE);
           holder.switchanswer.setVisibility(View.GONE);
        }
        // myview.setQuestionsAdapter(this);
//        if (position%2==0) {
//            holder.optionanswer.setVisibility(View.VISIBLE);
//            holder.switchanswer.setVisibility(View.GONE);
//        }else{
//            holder.optionanswer.setVisibility(View.GONE);
//            holder.switchanswer.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout switchanswer,optionanswer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            switchanswer=itemView.findViewById(R.id.switchanswer);
            optionanswer=itemView.findViewById(R.id.optionanswer);

        }
    }
}

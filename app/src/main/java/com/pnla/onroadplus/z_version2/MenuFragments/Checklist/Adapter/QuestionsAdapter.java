package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.mquestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder>{
   private Questions myview;
    private Context context;
    private List<mquestions> data;
//
    public QuestionsAdapter(List<mquestions> data, Questions myview, Context context) {
    this.myview=myview;
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_questions_type, parent, false);
        return new QuestionsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.ViewHolder holder, int position) {
        if (position%2==0) {
            holder.optionanswer.setVisibility(View.VISIBLE);
            holder.switchanswer.setVisibility(View.GONE);
        }else{
            holder.optionanswer.setVisibility(View.GONE);
            holder.switchanswer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if(data!=null)
        {
            return data.size();
        }else
        {
            Log.e("questionsConf","valor en null data");
            return 0;
        }

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

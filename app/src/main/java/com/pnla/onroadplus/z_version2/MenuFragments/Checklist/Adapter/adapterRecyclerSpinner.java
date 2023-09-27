package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;

public class adapterRecyclerSpinner extends RecyclerView.Adapter<adapterRecyclerSpinner.ViewHolder>{
    private Context context;
    private Integer size=0;
    private Questions myview;
    private  Integer cveTripMgmQuestion;
    public adapterRecyclerSpinner(Questions myview, Boolean requiredObservations, Boolean required_evidence, Integer cveTripMgmQuestion, Context context)
    {
        this.cveTripMgmQuestion=cveTripMgmQuestion;
        this.myview=myview;
        this.context=context;
        int observations=0;
        int evidence=0;
        if(requiredObservations==true){
            observations=1;
        }
        if(required_evidence==true){
            evidence=1;
        }
        this.size=observations+evidence;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fake_spinner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterRecyclerSpinner.ViewHolder holder, int i) {
//

        if(i==0)
        {
            holder.contrainItemdots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myview.takePick(cveTripMgmQuestion);
                }
            });

        }else {
            holder.contrainItemdots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myview.showObservations(cveTripMgmQuestion);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout contrainItemdots;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contrainItemdots=itemView.findViewById(R.id.contrainItemdots);
        }
    }
}

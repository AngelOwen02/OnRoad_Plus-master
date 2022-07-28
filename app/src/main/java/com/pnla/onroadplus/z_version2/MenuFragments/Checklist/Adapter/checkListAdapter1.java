package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.adapter.adapterGeocercas;

public class checkListAdapter1 extends RecyclerView.Adapter<checkListAdapter1.ViewHolder> {
    private Context context;
    private CheckListViewImpl myview;

    public checkListAdapter1(CheckListViewImpl myview, Context context) {
        this.myview=myview;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checklist, parent, false);
        return new checkListAdapter1.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull checkListAdapter1.ViewHolder viewHolder, int i) {
        viewHolder.buttonquestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myview.goquestionaryFragment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout buttonquestions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonquestions=itemView.findViewById(R.id.goQuestions);
        }
    }
}

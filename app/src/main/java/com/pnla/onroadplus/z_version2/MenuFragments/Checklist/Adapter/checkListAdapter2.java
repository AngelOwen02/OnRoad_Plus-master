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
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.historicChecklist;

public class checkListAdapter2 extends RecyclerView.Adapter<checkListAdapter2.ViewHolder> {
    private Context context;
    private historicChecklist myview;

    public checkListAdapter2(historicChecklist myview, Context context) {
        this.myview=myview;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checklist_historic, parent, false);
        return new checkListAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull checkListAdapter2.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
     //   ConstraintLayout buttonquestions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // buttonquestions=itemView.findViewById(R.id.goQuestions);
        }
    }
}

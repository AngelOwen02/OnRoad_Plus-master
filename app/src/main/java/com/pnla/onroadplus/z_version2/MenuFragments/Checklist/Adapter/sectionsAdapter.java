package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.questions.dataQuestions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;

import java.util.List;

public class sectionsAdapter extends RecyclerView.Adapter<sectionsAdapter.ViewHolder>{// PagerAdapter {

    private Context context;
    private Questions mview;
    private  int sizedots;
    private List<dataQuestions> dataQuestions1;
    private QuestionsAdapter adapter;

    public sectionsAdapter( List<dataQuestions> data, int size, Questions mview, Context context) {//(FragmentManager childFragmentManager, List<Banners> banners, Context context) {
        this.context= context;
        this.mview=mview;
        this.dataQuestions1=data;
        this.sizedots=size;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pager_questions, viewGroup, false);
        return new sectionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sectionsAdapter.ViewHolder holder, int position) {
      adapter=new QuestionsAdapter(dataQuestions1.get(position),mview, context);//TODO cambiar esto por el iterador
      holder.rv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return dataQuestions1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView rv;
        public ViewHolder(View view) {
            super(view);
            rv=itemView.findViewById(R.id.rvQuestions);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(layoutManager);
        }
    }
}

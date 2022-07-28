package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view;

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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.QuestionsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.checkListAdapter1;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.sectionsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.fragments.contactV2.view.FragmentContactV2;

public class Questions  extends Fragment implements View.OnClickListener{
    public static final String TAG = Questions.class.getSimpleName();
    private sectionsAdapter adapterQuestionary;
 //   private CardView cardView;
    private ViewPager pager;
    private PagerAdapter pAdapter;
    private LinearLayout dotslayout;


      private int sizeArange;
    private Button buttongochecklist;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        initContactView(view);

      // /** esto inicializa la posicion y el numero de puntos del quiestionario*/
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initContactView(View view) {
       // cardView=view.findViewById(R.id.cardviewitem);
        pager = (ViewPager) view.findViewById(R.id.cardviewitem);
        dotslayout = view.findViewById(R.id.dots_layout);
        buttongochecklist=view.findViewById(R.id.buttongochecklist);
        buttongochecklist.setOnClickListener(this);
        sizeArange=6;
        movedots(0);
        filldataAdapter();
    }

    void filldataAdapter()//pager
    {
        pAdapter = new sectionsAdapter(sizeArange,this, getContext());//(getChildFragmentManager(), fulldata.get(companyIndex).getBanner(),getContext());
        pager.setAdapter(pAdapter);

        pAdapter.notifyDataSetChanged();


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageSelected(int position) {
                Log.e("bannerPos",""+position);
                movedots(position);
                if(position==sizeArange-1)
                {
                    buttongochecklist.setVisibility(View.VISIBLE);
                 //   Toast.makeText(getContext(), "showButton", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    buttongochecklist.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void menutransition()
    {
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void movedots(int position) {
        if(dotslayout.getChildCount()>0)
        {
            dotslayout.removeAllViews();
        }
        ImageView dots[]=new ImageView[sizeArange];//[fulldata.get(companyIndex).getBanner().size()];
        for (int i=0; i<sizeArange;i++){//(int i=0; i<fulldata.get(companyIndex).getBanner().size();i++){
            dots[i]=new ImageView(getContext());
            if(i==position)
            {
                dots[i].setImageDrawable(getContext().getDrawable(R.drawable.black_dot_svg));
            }else
            {
                dots[i].setImageDrawable(getContext().getDrawable(R.drawable.gray_dot_svg));
            }
            LinearLayout.LayoutParams linearLayout=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setMargins(4,0,4,0);
            dotslayout.addView(dots[i],linearLayout);
        }
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

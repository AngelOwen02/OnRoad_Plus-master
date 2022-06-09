package com.pnla.onroadplus.z_version2.MenuFragments.Help.Screens;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.fragments.helpV2.adapters.AdapterHelpV2;

import java.util.ArrayList;
import java.util.List;

public class FragmentHelp extends Fragment implements View.OnClickListener {
    private int positionCurrentItem = 0;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private AdapterHelpV2 adapterViewPager;
    private ImageView imgvStep;


    public FragmentHelp() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_help, container, false);
        initFragmentHelp(view);
        return view;
    }

    private void initFragmentHelp(View view) {
        viewPager = view.findViewById(R.id.view_pager_loginhelp);
        TextView btnSkip = view.findViewById(R.id.txt_btn_skip);
        TextView btnNext = view.findViewById(R.id.txt_btn_next);
        imgvStep = view.findViewById(R.id.imgvStep);

        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentHelp1());
        fragmentList.add(new FragmentHelp2());
        fragmentList.add(new FragmentHelp3());
        fragmentList.add(new FragmentHelp4());
        fragmentList.add(new FragmentHelp5());
        fragmentList.add(new FragmentHelp6());
        fragmentList.add(new FragmentHelp7());
        fragmentList.add(new FragmentHelp8());
        fragmentList.add(new FragmentHelp9());

        adapterViewPager = new AdapterHelpV2(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterViewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                positionCurrentItem = position;

                if (position == 0) {
                    imgvStep.setImageResource(R.drawable.dots_step_1);
                } else if (position == 1) {
                    imgvStep.setImageResource(R.drawable.dots_step_2);
                } else if (position == 2) {
                    imgvStep.setImageResource(R.drawable.dots_step_3);
                } else if (position == 3) {
                    imgvStep.setImageResource(R.drawable.dots_step_4);
                } else if (position == 4) {
                    imgvStep.setImageResource(R.drawable.dots_step_5);
                } else if (position == 5) {
                    imgvStep.setImageResource(R.drawable.dots_step_6);
                } else if (position == 6) {
                    imgvStep.setImageResource(R.drawable.dots_step_7);
                } else if (position == 7) {
                    imgvStep.setImageResource(R.drawable.dots_step_8);
                } else if (position == 8) {
                    imgvStep.setImageResource(R.drawable.dots_step_9);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void goToLoginScreen() {
        Bundle bndl = new Bundle();
        bndl.putBoolean("HelpStatus", true);
        Intent intent = new Intent(getContext(), LoginContainerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bndl);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_btn_next:
                if (positionCurrentItem == 8) {
                    goToLoginScreen();
                } else {
                    viewPager.setCurrentItem(positionCurrentItem + 1, true);
                }
                break;
            case R.id.txt_btn_skip:
                goToLoginScreen();
                break;
        }
    }
}

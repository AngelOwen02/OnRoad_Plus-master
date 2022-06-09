package com.pnla.onroadplus.z_version2.fragments.helpV2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.online.view.ActivityOnlineV2;
import com.pnla.onroadplus.z_version2.fragments.helpV2.adapters.AdapterHelpV2;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step1;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step2;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step3;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step4a;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step4b;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step4c;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step5;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step6;
import com.pnla.onroadplus.z_version2.fragments.helpV2.screens.FragmentHelpV2Step7;
import com.pnla.onroadplus.z_version2.fragments.loginV2.view.FragmentLoginV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.view.FragmentMapV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

public class FragmentHelpV2 extends Fragment implements View.OnClickListener {
    public static final String TAG = FragmentHelpV2.class.getSimpleName();

    /**
     * View's
     */
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private AdapterHelpV2 adapterViewPager;
    private ImageView imgvStep;
    private TextView txvBtnSkip, txvBtnNext;
    private int positionCurrentItem = 0;

    private boolean invlokedLoginScreen = false;

    private OnHelpV2Listener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOnlineV2.isVisibleVehiclesList = true;
        Bundle bundle = getArguments();
        if (bundle != null) {
            invlokedLoginScreen = bundle.getBoolean(GeneralConstantsV2.INVOKED_LOGIN_SCREEN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_v2, container, false);
        initView(view);
        Toast.makeText(getContext(), "Fragment Help V2", Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //listener = (OnHelpV2Listener) context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txvBtnNext:
                if (positionCurrentItem == 8) {
                    if (invlokedLoginScreen) {
                        showFragmentLoginV2();
                    } else {
                        showFragmentMapV2();
                    }
                } else {
                    viewPager.setCurrentItem(positionCurrentItem + 1, true);
                }
                break;
            case R.id.txvBtnSkip:
                if (invlokedLoginScreen) {
                    showFragmentLoginV2();
                } else {
                    showFragmentMapV2();
                }
                break;
        }
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.viewPagerConteiner);
        imgvStep = view.findViewById(R.id.imgvStep);
        txvBtnSkip = view.findViewById(R.id.txvBtnSkip);
        txvBtnNext = view.findViewById(R.id.txvBtnNext);

        txvBtnSkip.setOnClickListener(this);
        txvBtnNext.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentHelpV2Step1());
        fragmentList.add(new FragmentHelpV2Step2());
        fragmentList.add(new FragmentHelpV2Step3());
        fragmentList.add(new FragmentHelpV2Step4a());
        fragmentList.add(new FragmentHelpV2Step4b());
        fragmentList.add(new FragmentHelpV2Step4c());
        fragmentList.add(new FragmentHelpV2Step5());
        fragmentList.add(new FragmentHelpV2Step6());
        fragmentList.add(new FragmentHelpV2Step7());

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

    private void showFragmentLoginV2() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFragmentsLoginV2, new FragmentLoginV2(), FragmentLoginV2.TAG).commit();
        transaction.addToBackStack(FragmentLoginV2.TAG);
    }

    private void showFragmentMapV2() {
        listener.showToolbar();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.popBackStack();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentMapV2.TAG);
        transaction.replace(R.id.containerFragmentsOnLineV2, new FragmentMapV2(), FragmentMapV2.TAG).commit();
    }

    public interface OnHelpV2Listener {
        void showToolbar();
    }

}

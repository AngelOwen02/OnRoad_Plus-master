package com.pnla.onroadplus.z_version2.fragments.helpV2.screens;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pnla.onroadplus.R;

public class FragmentHelpV2Step4a extends Fragment {

    public static final String TAG = FragmentHelpV2Step4a.class.getSimpleName();
    private ImageView imgvCarStatus;
    private TextView txvIgnitionTitle, txvIgnitionSubtitle, txvIgnitionDescription, txvIgnitionStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_step_4a, container, false);
        initViews(view);
        setFonts();
        return view;
    }

    private void initViews(View view) {
        imgvCarStatus =  view.findViewById(R.id.imgvCarStatus);
        txvIgnitionStatus =  view.findViewById(R.id.txvIgnitionStatus);
        txvIgnitionDescription =  view.findViewById(R.id.txvIgnitionDescription);
    }

    private void setFonts() {
        Typeface latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        txvIgnitionStatus.setTypeface(latoBoldTypeface);
        txvIgnitionDescription.setTypeface(latoRegularTypeface);
    }

}

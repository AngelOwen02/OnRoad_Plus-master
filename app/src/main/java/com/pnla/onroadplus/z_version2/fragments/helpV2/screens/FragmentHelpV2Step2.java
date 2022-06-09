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

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;

public class FragmentHelpV2Step2 extends Fragment {

    public static final String TAG = FragmentHelpV2Step2.class.getSimpleName();
    private TextView txvSelectTitle, txvSelectSubtitle, txvSelectDescription;
    private ImageView imgvSelectedHelp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_step_2, container, false);
        initViews(view);
        setFonts();
        Glide.with(getContext()).load(R.drawable.vehicles).into(imgvSelectedHelp);
        return view;
    }

    private void initViews(View view) {
        txvSelectSubtitle = view.findViewById(R.id.txvSelectSubtitle);
        txvSelectDescription = view.findViewById(R.id.txvSelectDescription);
        imgvSelectedHelp = view.findViewById(R.id.imgvSelectedHelp);
    }

    private void setFonts() {
        Typeface latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        txvSelectSubtitle.setTypeface(latoBoldTypeface);
        txvSelectDescription.setTypeface(latoRegularTypeface);
    }

}

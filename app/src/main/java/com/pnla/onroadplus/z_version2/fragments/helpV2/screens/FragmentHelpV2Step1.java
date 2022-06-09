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

public class FragmentHelpV2Step1 extends Fragment {

    public static final String TAG = FragmentHelpV2Step1.class.getSimpleName();
    private TextView txvTrackTitle, txvTrackSubtitle, txvTrackDescription;
    private ImageView imgvTrackImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_step_1, container, false);
        initViews(view);
        setFonts();
        Glide.with(getContext()).load(R.drawable.track_image_help).into(imgvTrackImage);
        return view;
    }

    private void initViews(View view) {
        txvTrackSubtitle =  view.findViewById(R.id.txvTrackSubtitle);
        txvTrackDescription =  view.findViewById(R.id.txvTrackDescription);
        imgvTrackImage =  view.findViewById(R.id.imgvTrackImage);
    }

    private void setFonts() {
        Typeface latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        txvTrackSubtitle.setTypeface(latoBoldTypeface);
        txvTrackDescription.setTypeface(latoRegularTypeface);
    }

}

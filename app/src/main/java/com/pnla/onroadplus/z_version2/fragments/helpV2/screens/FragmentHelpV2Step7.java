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

public class FragmentHelpV2Step7 extends Fragment {

    public static final String TAG = FragmentHelpV2Step7.class.getSimpleName();
    private TextView txvContactTitle, txvContactSubtitle, txvContactDescription;
    private ImageView imgvContactHelp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_step_7, container, false);
        initViews(view);
        setFonts();
        Glide.with(getContext()).load(R.drawable.track_img_step_7).into(imgvContactHelp);
        return view;
    }

    private void initViews(View view) {
        txvContactSubtitle = view.findViewById(R.id.txvContactSubtitle);
        txvContactDescription = view.findViewById(R.id.txvContactDescription);
        imgvContactHelp = view.findViewById(R.id.imgvContactHelp);
    }

    private void setFonts() {
        Typeface latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        txvContactTitle.setTypeface(latoBoldTypeface);
        txvContactSubtitle.setTypeface(latoBoldTypeface);
        txvContactDescription.setTypeface(latoRegularTypeface);
    }

}

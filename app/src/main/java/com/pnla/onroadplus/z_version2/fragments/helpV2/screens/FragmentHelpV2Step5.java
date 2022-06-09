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

public class FragmentHelpV2Step5 extends Fragment {

    public static final String TAG = FragmentHelpV2Step5.class.getSimpleName();
    private TextView txvNotificationTitle, txvNotificationSubtitle, txvNotificationDescription;
    private ImageView imgvNotificationHelp, imgvNotificationHelp2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_step_5, container, false);
        initViews(view);
        setFonts();
        Glide.with(getContext()).load(R.drawable.notifications).into(imgvNotificationHelp);
        return view;
    }

    private void initViews(View view) {
        txvNotificationSubtitle = view.findViewById(R.id.txvNotificationSubtitle);
        txvNotificationDescription = view.findViewById(R.id.txvNotificationDescription);
        imgvNotificationHelp = view.findViewById(R.id.imgvNotificationHelp);
    }

    private void setFonts() {
        Typeface latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        txvNotificationTitle.setTypeface(latoBoldTypeface);
        txvNotificationSubtitle.setTypeface(latoBoldTypeface);
        txvNotificationDescription.setTypeface(latoRegularTypeface);
    }

}

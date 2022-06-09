package com.pnla.onroadplus.z_version2.MenuFragments.Help.Screens;

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

public class FragmentHelp8 extends Fragment {

    public static final String TAG = FragmentHelp8.class.getSimpleName();
    private TextView txvCommandsTitle, txvCommandsSubtitle, txvCommandsDescription;
    private ImageView imgvCommandsHelp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_step_6, container, false);
        initViews(view);
     //   setFonts();
        Glide.with(getContext()).load(R.drawable.help_8).into(imgvCommandsHelp);
        return view;
    }

    private void initViews(View view) {
        txvCommandsSubtitle = view.findViewById(R.id.txvCommandsSubtitle);
        txvCommandsDescription = view.findViewById(R.id.txvCommandsDescription);
        imgvCommandsHelp = view.findViewById(R.id.imgvCommandsHelp);
    }

  /*  private void setFonts() {
        Typeface latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        txvCommandsTitle.setTypeface(latoBoldTypeface);
        txvCommandsSubtitle.setTypeface(latoBoldTypeface);
        txvCommandsDescription.setTypeface(latoRegularTypeface);
    } */

}

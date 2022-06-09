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

public class FragmentHelp3 extends Fragment {

    public static final String TAG = FragmentHelp3.class.getSimpleName();
    private TextView txvGoTitle, txvGoSubtitle, txvGoDescription;
    private ImageView imgvTrack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_step_3, container, false);
        initViews(view);
    //    setFonts();
        Glide.with(getContext()).load(R.drawable.help_3).into(imgvTrack);
        return view;
    }

    private void initViews(View view) {
        txvGoSubtitle = view.findViewById(R.id.txvGoSubtitle);
        txvGoDescription = view.findViewById(R.id.txvGoDescription);
        imgvTrack = view.findViewById(R.id.imgvTrack);
    }

  /*  private void setFonts() {
        Typeface latoRegularTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
        Typeface latoBoldTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        txvGoTitle.setTypeface(latoBoldTypeface);
        txvGoSubtitle.setTypeface(latoBoldTypeface);
        txvGoDescription.setTypeface(latoRegularTypeface);
    } */

}
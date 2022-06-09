package com.pnla.onroadplus.z_version2.MenuFragments.Help;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pnla.onroadplus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpContainer extends Fragment {


    public HelpContainer() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_container, container, false);

        return view;
    }

}

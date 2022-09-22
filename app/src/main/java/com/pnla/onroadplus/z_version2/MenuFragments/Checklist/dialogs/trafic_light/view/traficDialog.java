package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.trafic_light.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.questionView;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.DialogsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view.DialogsView;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view.DialogsViewImpl;

import java.util.List;

public class traficDialog extends DialogFragment implements View.OnClickListener {
    public static final String TAG = traficDialog.class.getSimpleName();
    private Button buttontraficAcept;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_traficlights_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.grayBD);
        setCancelable(true);

        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {
        buttontraficAcept=view.findViewById(R.id.buttontraficAcept);
        buttontraficAcept.setOnClickListener(this);
    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttontraficAcept:
                closeDialog();
                break;
        }
    }

}

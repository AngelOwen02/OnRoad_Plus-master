package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;

public class DialogsViewImpl extends DialogFragment  implements View.OnClickListener {
    public static final String TAG = DialogsViewImpl.class.getSimpleName();
    private Button externalconstraint;
    private ConstraintLayout externalconstraint2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_vehicles_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfashadow) ;
        setCancelable(true);

        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {

        externalconstraint = view.findViewById(R.id.externalconstraint);
        externalconstraint.setOnClickListener(this);

        externalconstraint2 = view.findViewById(R.id.externalconstraint2);
        externalconstraint2.setOnClickListener(this);
    }
    public void closeDialog() {
        this.dismiss();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.externalconstraint2:
//                closeDialog();
//                break;

            case R.id.externalconstraint:
                closeDialog();
                break;
            //case    R.id.:
                //Toast.makeText(getContext(), "ir al menu", Toast.LENGTH_SHORT).show();
            //    closeDialog();

               // break;
        }
    }

}
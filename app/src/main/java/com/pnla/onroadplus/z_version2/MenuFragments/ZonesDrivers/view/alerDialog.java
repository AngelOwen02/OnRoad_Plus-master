package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.pnla.onroadplus.R;

import java.util.ArrayList;
import java.util.List;

public class alerDialog extends DialogFragment implements View.OnClickListener {
public static final String TAG = alerDialog.class.getSimpleName();
public driversAsingment dA;
private ConstraintLayout cv1,cv2;
private ConstraintLayout c1;
private Context context;
private String announce;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customdialog, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        setCancelable(false);
        initDialog(view);
//        setFonts();
        return view;
    }

        public alerDialog(driversAsingment dA,Context context)
        {
            this.dA=dA;

            this.context=context;
        }

    private void initDialog(View view) {
        c1=view.findViewById(R.id.backgrounCustomDialog);
        cv2=view.findViewById(R.id.buttonCDialog);
        cv1=view.findViewById(R.id.cardView3Bedi);
        c1.setOnClickListener(this);
        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backgrounCustomDialog:
                this.dismiss();
                break;
            case R.id.cardView3Bedi:/**eliminar index ACEPTAR*/
                dA.orderErase();
                /**eliminar */
                this.dismiss();
                break;
            case R.id.buttonCDialog:
                List<String> emptylist2=new ArrayList<>();
                dA.indexes.clear();
                dA.showtrashcan(emptylist2,0);;

                this.dismiss();
                break;

        }

    }
}

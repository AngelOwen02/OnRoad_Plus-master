package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.trafic_light.view;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.questionView;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.historicChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.DialogsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view.DialogsView;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view.DialogsViewImpl;

import java.util.List;

public class traficDialog extends DialogFragment implements View.OnClickListener {
    public static final String TAG = traficDialog.class.getSimpleName();
    private Button buttontraficAcept;
    private String semafor;
    private int scoreF;
    private ImageView taficc_Ligth;
    private ConstraintLayout alto,bajo,moderado;
    private TextView scorealt,scorebajo,scoremoderado;

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
        Bundle mArgs = getArguments();
        semafor = mArgs.getString("semaforofinal");
        scoreF= mArgs.getInt("finalscore");

        initDialog(view);
        //setFonts();
        return view;
    }
//constrainalto
    //constrainmoderado
    //constrainbajo
    private void initDialog(View view) {
        taficc_Ligth =view.findViewById(R.id.taficc_Ligth);


        scorebajo=view.findViewById(R.id.scorefbajo);
        scoremoderado=view.findViewById(R.id.scorefmod);
        scorealt=view.findViewById(R.id.scoref);

        alto=view.findViewById(R.id.constrainalto);
        moderado=view.findViewById(R.id.constrainmoderado);
        bajo=view.findViewById(R.id.constrainbajo);

        alto.setVisibility(View.GONE);
        moderado.setVisibility(View.GONE);
        bajo.setVisibility(View.GONE);

        buttontraficAcept=view.findViewById(R.id.buttontraficAcept);
        buttontraficAcept.setOnClickListener(this);
        if(semafor.equals("1"))
        {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_siga);
            taficc_Ligth.setImageDrawable(drawable);
            alto.setVisibility(View.GONE);
            moderado.setVisibility(View.GONE);
            bajo.setVisibility(View.VISIBLE);
            scorebajo.setText(String.valueOf( scoreF));
        }else if(semafor.equals("2"))
        {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_warning);
            taficc_Ligth.setImageDrawable(drawable);
            alto.setVisibility(View.GONE);
            moderado.setVisibility(View.VISIBLE);
            bajo.setVisibility(View.GONE);
            scoremoderado.setText(String.valueOf(scoreF));
        }else if(semafor.equals("3")){
            Drawable drawable = getResources().getDrawable(R.drawable.ic_stop);
            taficc_Ligth.setImageDrawable(drawable);
            alto.setVisibility(View.VISIBLE);
            moderado.setVisibility(View.GONE);
            bajo.setVisibility(View.GONE);
            scorealt.setText(String.valueOf(scoreF));
        }else{
            Toast.makeText(getContext(), "semaforo fuera de rango", Toast.LENGTH_SHORT).show();
            closeDialog();
        }
    }

    public void closeDialog() {
        this.dismiss();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conteinerMainFragments, new historicChecklist(), historicChecklist.TAG).commit();
        getActivity().getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

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

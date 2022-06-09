package com.pnla.onroadplus.z_version2.fragments.contactV2.view;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.online.view.ActivityOnlineV2;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.ContactV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.ContactV2View;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.FragmentContactV2Data;
import com.pnla.onroadplus.z_version2.fragments.contactV2.presenters.ContactV2PresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.view.FragmentVehiclesV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

public class FragmentContactV2 extends Fragment implements ContactV2View, View.OnClickListener, DialogOkMessageTracking.OnTrackingAppOkMessageDialogListener {
    public static final String TAG = FragmentContactV2.class.getSimpleName();

    private ContactV2Presenter presenter;

    // ViewImpl implements View - ViewImpl contains Presenter

    private TextView txvTo, txvCopy, txvSubject;
    private EditText edtEmailSupport, edtCopy, edtSubject, edtMessageEmail;
    private CardView btnSendEmail;

    private DialogTrackingLoader loader;
    private DialogOkMessageTracking dialogOkMessageTracking;

    private FragmentContactV2Data fragmentContactV2Data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOnlineV2.isVisibleVehiclesList = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        initContactView(view);
        setFonts();
        filterEditTextContact();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //fragmentContactV2Data = (FragmentContactV2Data) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendEmail:
                presenter.validateUserDataToSend(edtEmailSupport.getText().toString(), edtSubject.getText().toString(), edtMessageEmail.getText().toString(), getContext());
                break;
        }
    }

    @Override
    public void showLoader() {
        loader = new DialogTrackingLoader();
        loader.show(getActivity().getSupportFragmentManager(), DialogTrackingLoader.TAG);
    }

    @Override
    public void hideLoader() {
        if (loader != null) {
            loader.dismiss();
            loader = null;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sessionExpired(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void successContactService() {
        dialogOkMessageTracking = new DialogOkMessageTracking();
        dialogOkMessageTracking.setOnTrackingAppOkMessageDialogListener(FragmentContactV2.this);
        dialogOkMessageTracking.setInfoNormalDialog(GeneralConstantsV2.TYPE_OK_CONTACT);
        dialogOkMessageTracking.show(getActivity().getSupportFragmentManager(), DialogOkMessageTracking.TAG);
    }

    @Override
    public void setUserEmail(String email) {
        edtCopy.setText(email);
    }

    private void initContactView(View view) {
        txvTo = view.findViewById(R.id.txvTo);
        txvCopy = view.findViewById(R.id.txvCopy);
        txvSubject = view.findViewById(R.id.txvSubject);
        edtEmailSupport = view.findViewById(R.id.edtEmailSupport);
        edtCopy = view.findViewById(R.id.edtCopy);
        edtSubject = view.findViewById(R.id.edtSubject);
        edtMessageEmail = view.findViewById(R.id.edtMessageEmail);
        btnSendEmail = view.findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(this);
        edtEmailSupport.setEnabled(false);
        edtCopy.setEnabled(false);
        //fragmentContactV2Data.setContactTitleToolbar(getString(R.string.textItemContact));
        presenter = new ContactV2PresenterImpl();
        presenter.setView(this);
        presenter.getUserEmail(getContext());
    }

    private void setFonts() {

        Typeface robotoRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoMedium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf");
        Typeface robotoLight = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Light.ttf");

        txvTo.setTypeface(robotoLight);
        txvCopy.setTypeface(robotoLight);
        txvSubject.setTypeface(robotoLight);
        edtEmailSupport.setTypeface(robotoMedium);
        edtCopy.setTypeface(robotoMedium);
        edtSubject.setTypeface(robotoMedium);
        edtMessageEmail.setTypeface(robotoRegular);

    }

    private void filterEditTextContact() {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    int type = Character.getType(source.charAt(i));
                    if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                        return "";
                    }
                }
                return null;
            }
        };
        edtMessageEmail.setFilters(new InputFilter[]{filter});
        edtSubject.setFilters(new InputFilter[]{filter});
        edtCopy.setFilters(new InputFilter[]{filter});
    }

    @Override   //método del dialogo
    public void onOkMessage() {
        dialogOkMessageTracking.dismiss();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerFragmentsOnLineV2, new FragmentVehiclesV2(), FragmentVehiclesV2.TAG).commit();
    }

}

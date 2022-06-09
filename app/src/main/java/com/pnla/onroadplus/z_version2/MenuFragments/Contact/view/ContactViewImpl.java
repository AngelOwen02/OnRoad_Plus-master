package com.pnla.onroadplus.z_version2.MenuFragments.Contact.view;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.Contact.presenter.ContactPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Contact.presenter.ContactPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.FragmentContactV2Data;
import com.pnla.onroadplus.z_version2.fragments.contactV2.view.FragmentContactV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactViewImpl extends Fragment implements ContactView, CardView.OnClickListener{

    public static final String TAG = FragmentContactV2.class.getSimpleName();

    private ContactPresenter presenter;

    private TextView txtTo;
    private TextView txtCopy;
    private TextView txtSubject;
    private EditText edtxtEmailSupport;
    private EditText edtxtCopy;
    private EditText edtxtSubject;
    private EditText edtxtMessageEmail;
    private CardView btnSendEmail;
    private DialogTrackingLoader loader;
    private DialogOkMessageTracking dialogOkMessageTracking;
    private FragmentContactV2Data fragmentContactV2Data;

    private TextView toolbarTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_view_impl, container, false);

        initContactView(view);

        return view;
    }

    private void initContactView(View view) {
        txtTo = view.findViewById(R.id.txvTo);
        txtCopy = view.findViewById(R.id.txvCopy);
        txtSubject = view.findViewById(R.id.txvSubject);
        edtxtEmailSupport = view.findViewById(R.id.edtEmailSupport);
        edtxtCopy = view.findViewById(R.id.edtCopy);
        edtxtSubject = view.findViewById(R.id.edtSubject);
        edtxtMessageEmail = view.findViewById(R.id.edtMessageEmail);
        btnSendEmail = view.findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(this);
        edtxtEmailSupport.setEnabled(false);
        edtxtCopy.setEnabled(false);
        toolbarTitle = view.findViewById(R.id.default_toolbar_title);
        toolbarTitle.setText("Contacto");

        presenter = new ContactPresenterImpl(getContext());
        presenter.setView(this);
        presenter.initThemeSettings();
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

    }

    @Override
    public void sessionExpired(String message) {

        UnitDB.deleteDB();
        GroupDB.deleteDB();
        RealmUserData.deleteDB();

        Bundle bndl = new Bundle();
        bndl.putBoolean("HelpStatus", true);

        Intent intent = new Intent(getContext(), LoginContainerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bndl);
        getContext().startActivity(intent);

        Toast.makeText(getContext(), "La session ha expirado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successContactService() {
        presenter.sendEmail();
        edtxtSubject.getText().clear();
        edtxtMessageEmail.getText().clear();
    }

    @Override
    public void setUserEmail(String email) {
        edtxtCopy.setText(email);
    }

    @Override
    public void setSupportEmail(String email) {
        edtxtEmailSupport.setText(email);
    }

    @Override
    public void setFonts(Typeface robotoLight, Typeface robotoRegular, Typeface robotoMedium) {
        txtTo.setTypeface(robotoLight);
        txtCopy.setTypeface(robotoLight);
        txtSubject.setTypeface(robotoLight);
        edtxtEmailSupport.setTypeface(robotoMedium);
        edtxtCopy.setTypeface(robotoMedium);
        edtxtSubject.setTypeface(robotoMedium);
        edtxtMessageEmail.setTypeface(robotoRegular);

        Typeface robotoBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
        toolbarTitle.setTypeface(robotoBold);

    }

    @Override
    public void onClick(View v) {
        presenter.validateUserDataToSend(edtxtEmailSupport.getText().toString(), edtxtSubject.getText().toString(), edtxtMessageEmail.getText().toString(), getContext());

    }
}

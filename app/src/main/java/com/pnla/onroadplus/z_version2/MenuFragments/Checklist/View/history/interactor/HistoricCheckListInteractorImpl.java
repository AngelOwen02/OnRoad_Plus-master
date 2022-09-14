package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.Historic;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.checkListHistoricResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.checklistHistoricRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.presenter.HistoricCheckListPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.presenter.HistoricCheckListPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.util.histprocService;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoricCheckListInteractorImpl implements HistoricCheckListInteractor {

    private HistoricCheckListPresenter presenter;
    private histprocService histprocService;
    private Context context;
    private Retrofit retrofitClient;

    public HistoricCheckListInteractorImpl(HistoricCheckListPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        initRetrofit();
        requestHistoric();
    }

    private void initRetrofit(){
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        histprocService = retrofitClient.create(histprocService.class);
    }

    @Override
    public void requestHistoric() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            requestSoportes(token);
        }
    }

    private void requestSoportes(String token) {
        checklistHistoricRequest request = new checklistHistoricRequest(token);
        presenter.showProgressDialog();
            histprocService.getCheckList(request).enqueue(new Callback<checkListHistoricResponse>() {
                @Override
                public void onResponse(Call<checkListHistoricResponse> call, Response<checkListHistoricResponse> response) {
                    validateCode(response, context);

                }

                @Override
                public void onFailure(Call<checkListHistoricResponse> call, Throwable t) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

    }

    private void validateCode(Response<checkListHistoricResponse> response, Context context) {
        if(RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getHistoricData(response, context);
        } else {
            presenter.failureResponse(RetrofitValidationsV2.getErrorByStatus(response.code() , context));
            Toast.makeText(context, "sesion expirada3", Toast.LENGTH_SHORT).show();
        }
    }

    private void getHistoricData(Response<checkListHistoricResponse> response, Context context) {
        checkListHistoricResponse checkListHistoricResponse = response.body();
        if(checkListHistoricResponse != null) {
            int responseCode = checkListHistoricResponse.getResponseCode();
            if(responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                List<Historic> data = checkListHistoricResponse.getData();
                presenter.setHistoric(data);
                presenter.hideProgressDialog();
            } else {
                Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                presenter.hideProgressDialog();
            }
        } else {
            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
            presenter.hideProgressDialog();
        }
    }
}

package com.pnla.onroadplus.z_version2.MenuFragments.Commands.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.presenter.CommandsPresenter;

import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aservice.CommandsV2Services;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandV2;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandV2Data;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandsV2Request;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandsV2Response;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.asend.CommandSendV2Request;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.asend.CommandSendV2Response;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.acommandsutils.CommandsUtils;

import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.AuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.responseAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.setAuditTrail;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommandsInteractorImpl implements CommandsInteractor {

    private CommandsPresenter presenter;
    private CommandsV2Services services;
    private Retrofit retrofitClient;
    private String VehicleName;
    private String CommandName;
private Context context;
    public CommandsInteractorImpl(CommandsPresenter presenter) {
        this.presenter = presenter;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(CommandsV2Services.class);
    }

    @Override
    public void getVehicleData(Bundle bundle) {
        if (bundle != null) {
            String vehicleName = bundle.getString("vehicleName");
            VehicleName=vehicleName;
            int vehicleCve = bundle.getInt("vehicleCve");
            presenter.setVehicleDataToView(vehicleName, vehicleCve);
        } else {
            presenter.setMessageToView("Error, no se pudo obtener información del vehículo.");
        }
    }

    @Override
    public void getCommands(int vehicleCve, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String validation = CommandsUtils.validateTokenCve(token, vehicleCve);
        if (validation.equals(GeneralConstantsV2.SUCCESS)) {
            startCommandsRequest(token, vehicleCve, context);
        } else {
            presenter.setMessageToView(validation);
        }
    }



    private void startCommandsRequest(String token, int vehicleCve, final Context context) {
        CommandsV2Request request = new CommandsV2Request(vehicleCve, token);
        services.getCommands(request).enqueue(new Callback<CommandsV2Response>() {
            @Override
            public void onResponse(Call<CommandsV2Response> call, Response<CommandsV2Response> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<CommandsV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateCode(Response<CommandsV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getCommandsData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getCommandsData(Response<CommandsV2Response> response, Context context) {
        CommandsV2Response commandsV2Response = response.body();
        if (commandsV2Response != null) {
            int responseCode = commandsV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                CommandV2Data data = commandsV2Response.getData();
                if (data != null) {
                    List<CommandV2> commands = data.getRoutines();
                    if (commands != null) {
                        presenter.setCommandsListToview(commands);
                    } else {
                        presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(commandsV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
        }
    }

    @Override
    public void sendCommand(String msgRoutine, String uniqueId, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String validation = CommandsUtils.validateCommandToSend( msgRoutine,token, uniqueId);
        CommandName=msgRoutine;
        if (validation.equals(GeneralConstantsV2.SUCCESS)) {
            Log.e("routinas",""+ msgRoutine+" "+token +" "+ uniqueId);
            startSendCommandRequest(msgRoutine,token,  uniqueId, context);
        } else {
            presenter.setMessageToView(validation);
        }
    }

    private void startSendCommandRequest(String cveRoutineToSend,String token, String cveDeviceToSend,  final Context context) {
        CommandSendV2Request request = new CommandSendV2Request(cveRoutineToSend,token, cveDeviceToSend);
        services.sendCommand(request).enqueue(new Callback<CommandSendV2Response>() {
            @Override
            public void onResponse(Call<CommandSendV2Response> call, Response<CommandSendV2Response> response) {
                validateCodeSendCommand(response, context);
            }

            @Override
            public void onFailure(Call<CommandSendV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateCodeSendCommand(Response<CommandSendV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getSendCommandResponse(response, context);
          Log.e("routinas",""+response.body().getResponseCode());
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getSendCommandResponse(Response<CommandSendV2Response> response, Context context) {
        CommandSendV2Response commandSendV2Response = response.body();
       Log.e("routinas",""+response.body().getMessage());
        if (commandSendV2Response != null) {
            int responseCode = commandSendV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                presenter.successSendCommand(VehicleName,CommandName);
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(commandSendV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView("No se recibió respuesta del servidor.");
        }
    }

    @Override
    public void newsetAuditTrail(String name) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            myauditTrail(name,token);
        }
    }
    private void myauditTrail(String name ,String token)
    {
        AuditTrail mynewAuditTrail=new AuditTrail("Onroad_Comandos","Envio de Comandos",""+name);
        setAuditTrail request=new setAuditTrail(mynewAuditTrail,token);
        services.auditTrail(request).enqueue(new Callback<responseAuditTrail>() {
            @Override
            public void onResponse(Call<responseAuditTrail> call, Response<responseAuditTrail> response) {
                validateCodeauditTrail(response,context);
            }

            @Override
            public void onFailure(Call<responseAuditTrail> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private  void  validateCodeauditTrail(Response<responseAuditTrail> response,Context context)
    {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            responseSetAuditTrial(response,context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }
    private void responseSetAuditTrial(Response<responseAuditTrail> response,Context context)
    {
        responseAuditTrail auditResponse=response.body();
        if(auditResponse!=null)
        {
            int responseCode=auditResponse.getResponseCode();
            String message=auditResponse.getMessage();
            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK)
            {

            }
        }

    }
}

package com.pnla.onroadplus.z_version2.fragments.commands.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.CommandsV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.CommandsV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.CommandsV2Services;
import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandV2;
import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandV2Data;
import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandsV2Request;
import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandsV2Response;
import com.pnla.onroadplus.z_version2.fragments.commands.models.send.CommandSendV2Request;
import com.pnla.onroadplus.z_version2.fragments.commands.models.send.CommandSendV2Response;
import com.pnla.onroadplus.z_version2.fragments.commands.utils.CommandsUtils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommandsV2InteractorImpl implements CommandsV2Interactor {

    private CommandsV2Presenter presenter;
    private CommandsV2Services services;
    private Retrofit retrofitClient;

    public CommandsV2InteractorImpl(CommandsV2Presenter presenter) {
        this.presenter = presenter;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(CommandsV2Services.class);
    }

    @Override
    public void getVehicleData(Bundle bundle) {
        if (bundle != null) {
            String vehicleName = bundle.getString(GeneralConstantsV2.VEHICLE_NAME_SELECTED);
            int vehicleCve = bundle.getInt(GeneralConstantsV2.VEHICLE_CVE_SELECTED);
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
                    if (commands != null && commands.size() > 0) {
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
    public void sendCommand(int cveDeviceToSend, int cveRoutineToSend, int vehicleCve, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String validation = CommandsUtils.validateCommandToSend(token, cveDeviceToSend, cveRoutineToSend, vehicleCve);
        if (validation.equals(GeneralConstantsV2.SUCCESS)) {
            startSendCommandRequest(token, cveDeviceToSend, cveRoutineToSend, vehicleCve, context);
        } else {
            presenter.setMessageToView(validation);
        }
    }

    private void startSendCommandRequest(String token, int cveDeviceToSend, int cveRoutineToSend, int vehicleCve, final Context context) {
        CommandSendV2Request request = new CommandSendV2Request(token, cveDeviceToSend, cveRoutineToSend, vehicleCve);
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
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getSendCommandResponse(Response<CommandSendV2Response> response, Context context) {
        CommandSendV2Response commandSendV2Response = response.body();
        if (commandSendV2Response != null) {
            int responseCode = commandSendV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                presenter.successSendCommand();
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(commandSendV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView("No se recibió respuesta del servidor.");
        }
    }

}

package com.pnla.onroadplus.z_version2.fragments.commands.interfaces;

import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandsV2Request;
import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandsV2Response;
import com.pnla.onroadplus.z_version2.fragments.commands.models.send.CommandSendV2Request;
import com.pnla.onroadplus.z_version2.fragments.commands.models.send.CommandSendV2Response;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CommandsV2Services {

    /**
     * get commands by vehicle
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_VEHICLE_COMMANDS)
    Call<CommandsV2Response> getCommands(@Body CommandsV2Request request);

    /**
     * send command
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.SEND_COMMAND)
    Call<CommandSendV2Response> sendCommand(@Body CommandSendV2Request request);

}

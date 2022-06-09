package com.pnla.onroadplus.z_version2.MenuFragments.Commands.aservice;

import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandsV2Request;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandsV2Response;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.asend.CommandSendV2Request;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.asend.CommandSendV2Response;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.responseAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.setAuditTrail;
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


    @POST(RetrofitEndPointsV2.POST_AUDIT_TRAIL)
    Call<responseAuditTrail> auditTrail(@Body setAuditTrail auditrequest);
}

package com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces;

import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationsV2Request;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationsV2Response;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2Request;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2Response;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NotificationsV2Services {
    /**
     * get vehicles
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_FULL_VEHICLES)
    Call<VehicleV2Response> getFullVehicles(@Body VehicleV2Request request);

    /**
     * Get all notifications
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_ALL_NOTIFICATIONS)
    Call<NotificationsV2Response> getAllNotifications(@Body NotificationsV2Request request);

}

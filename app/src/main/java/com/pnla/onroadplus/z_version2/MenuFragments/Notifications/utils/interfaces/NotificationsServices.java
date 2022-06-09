package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.interfaces;

import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.VehiclesRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.VehiclesResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface NotificationsServices {
    /**
     * get vehicles
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_FULL_VEHICLES)
    Call<VehiclesResponse> getFullVehicles(@Body VehiclesRequest request);

    /**
     * Get all notifications
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_ALL_NOTIFICATIONS)
    Call<NotificationsResponse> getAllNotifications(@Body NotificationsRequest request);
}

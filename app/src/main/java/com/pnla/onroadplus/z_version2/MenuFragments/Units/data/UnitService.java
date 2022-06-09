package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.pnla.onroadplus.z_version2.MenuFragments.TrackingV2.model.gecercasRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.TrackingV2.model.geoCercasResponse;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Request;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Response;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionRequest;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UnitService {

    @POST(RetrofitEndPointsV2.GET_FULL_VEHICLES)
    Call<UnitResponse> getFullVehicles(@Body UnitRequest request);

    @POST(RetrofitEndPointsV2.GET_FULL_GEOREFERENCE)
    Call<GeoreferenceResponse> getFullGeoReference(@Body GeoreferenceRequest request);

    @POST(RetrofitEndPointsV2.GET_VEHICLE_DESCRIPTION)
    Call<VehicleDescriptionResponse> getVehicleDescription(@Body VehicleDescriptionRequest request);

    @POST(RetrofitEndPointsV2.GET_VEHICLE_TRIP)
    Call<TripsV2Response> getTrips(@Body TripsV2Request request);

    @POST(RetrofitEndPointsV2.GET_VEHICLE_GROUP_LIST)
    Call<GroupResponse> getGroups(@Body GroupRequest request);

    @POST(RetrofitEndPointsV2.GET_VEHICLES_GROUP_LIST_VEHICLES)
    Call<GroupvehicleInsideResponse> getVehiclesInGroups(@Body GroupvehicleInsideRequest request);

    @POST(RetrofitEndPointsV2.GET_MENUOBJECT)
    Call<geoCercasResponse> hideShowgeoCercas(@Body gecercasRequest request);
}

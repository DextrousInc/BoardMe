package com.dextrous.hack.boardme.service;


import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.model.RouteLocation;
import com.dextrous.hack.boardme.model.TravelHistory;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.BoardRouteResponse;
import com.dextrous.hack.boardme.response.GenericListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BoardmeAPIService {

    @GET("users/all")
    Call<GenericListResponse<User>> getAllUsers();

    @GET("travel-history/all")
    Call<GenericListResponse<TravelHistory>> getUserTravelHistory(@Query("userId") String userId);

    @GET("routes/all")
    Call<GenericListResponse<Route>> getAllRoutes();

    @GET("routes/locations/all")
    Call<GenericListResponse<RouteLocation>> getAllStopsInRoute(@Query("routeId") String routeId);

    @GET("board-me")
    Call<BoardRouteResponse> getBoardingRouteData(@Query("beaconId") String beaconId, @Query("userId") String userId,
                                                  @Query("latitude") String latitude, @Query("longitude") String longitude);
}

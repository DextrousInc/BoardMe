package com.dextrous.hack.boardme.service;


import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.model.RouteLocation;
import com.dextrous.hack.boardme.model.TravelHistory;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.BoardRouteResponse;
import com.dextrous.hack.boardme.response.BoardWaitResponse;
import com.dextrous.hack.boardme.response.GenericBeanResponse;
import com.dextrous.hack.boardme.response.GenericListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BoardMeAPIService {

    @GET("users/all")
    Call<GenericListResponse<User>> getAllUsers();

    @POST("login")
    @FormUrlEncoded
    Call<GenericBeanResponse<User>> loginUser(@FieldMap Map<String, Object> form);

    @POST("travel-history/add")
    @FormUrlEncoded
    Call<GenericBeanResponse<TravelHistory>> addTravelHistory(@FieldMap Map<String, Object> form);

    @GET("travel-history/all")
    Call<GenericListResponse<TravelHistory>> getUserTravelHistory(@Query("userId") String userId);

    @GET("routes/all")
    Call<GenericListResponse<Route>> getAllRoutes();

    @GET("routes/locations/all")
    Call<GenericListResponse<RouteLocation>> getAllStopsInRoute(@Query("routeId") String routeId);

    @GET("board-me")
    Call<BoardRouteResponse> getBoardingRouteData(@Query("beaconId") String beaconId, @Query("userId") String userId,
                                                  @Query("latitude") String latitude, @Query("longitude") String longitude);
    @GET("board-wait")
    Call<GenericBeanResponse<BoardWaitResponse>> getBoardWaitData( @Query("userId") String userId, @Query("routeId") String routeId,
                                                  @Query("latitude") String latitude, @Query("longitude") String longitude);
}

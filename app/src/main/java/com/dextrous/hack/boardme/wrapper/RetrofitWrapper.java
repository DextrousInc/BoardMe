package com.dextrous.hack.boardme.wrapper;


import com.dextrous.hack.boardme.service.BoardMeAPIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOCAL_SERVER_URL;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.SERVER_DATE_FORMAT;

public class RetrofitWrapper {

    private static Gson gson = new GsonBuilder()
            .setDateFormat(SERVER_DATE_FORMAT)
            .create();
    //Date format Sun, 03 Apr 2016 13:30:39 GMT
    private static Retrofit retrofit = null;
    private RetrofitWrapper() {

    }

    private static void start(String baseURL) {
        baseURL = baseURL != null ? baseURL : LOCAL_SERVER_URL;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public static BoardMeAPIService build(String baseURL) {
        start(baseURL);
        return retrofit.create(BoardMeAPIService.class);
    }

}

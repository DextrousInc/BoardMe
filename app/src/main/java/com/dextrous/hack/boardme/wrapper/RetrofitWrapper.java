package com.dextrous.hack.boardme.wrapper;


import com.dextrous.hack.boardme.service.BoardmeAPIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dextrous.hack.boardme.constant.BoardmeConstants.SERVER_DATE_FORMAT;
import static com.dextrous.hack.boardme.constant.BoardmeConstants.LOCAL_SERVER_URL;

public class RetrofitWrapper {

    private static Gson gson = new GsonBuilder()
            .setDateFormat(SERVER_DATE_FORMAT)
            .create();
    //Date format Sun, 03 Apr 2016 13:30:39 GMT
    private static Retrofit retrofit = null;
    private RetrofitWrapper() {

    }

    public static void start(String baseURL) {
        baseURL = baseURL != null ? baseURL : LOCAL_SERVER_URL;
        if(retrofit == null){
           retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }
    public static BoardmeAPIService build() {
        return retrofit.create(BoardmeAPIService.class);
    }

}
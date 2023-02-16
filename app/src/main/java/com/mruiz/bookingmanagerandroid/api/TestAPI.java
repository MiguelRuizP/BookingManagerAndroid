package com.mruiz.bookingmanagerandroid.api;

import com.mruiz.bookingmanagerandroid.payload.SimpleMessageDto;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TestAPI {
    @POST("/test")
    Call<SimpleMessageDto> test(@Header("Authorization") String authHeader);
}

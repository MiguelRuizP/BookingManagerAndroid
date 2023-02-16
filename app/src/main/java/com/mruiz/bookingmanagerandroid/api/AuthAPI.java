package com.mruiz.bookingmanagerandroid.api;

import com.mruiz.bookingmanagerandroid.payload.FullUserDto;
import com.mruiz.bookingmanagerandroid.payload.SimpleMessageDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthAPI {
    @POST("/auth/token")
    Call<SimpleMessageDto> token(@Header("Authorization") String authHeader);

    @POST("/auth/register")
    Call<SimpleMessageDto> register(@Body FullUserDto userDto);
}

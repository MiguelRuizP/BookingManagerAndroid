package com.mruiz.bookingmanagerandroid.api.builder;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mruiz.bookingmanagerandroid.api.AuthAPI;
import com.mruiz.bookingmanagerandroid.payload.FullUserDto;
import com.mruiz.bookingmanagerandroid.payload.SimpleMessageDto;

import java.io.IOException;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIBuilder {
    @Getter
    private Retrofit retrofit;
    private AuthAPI authAPI;
    @Getter
    private String token;

    public APIBuilder(String baseURL){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        authAPI = retrofit.create(AuthAPI.class);
    }

    public int login(String username, String password) {
        Call<SimpleMessageDto> call = authAPI.token(encodeLoginHeader(username, password));
        Response<SimpleMessageDto> response = null;
        try {
            response = call.execute();
            if(response.isSuccessful()){
                token = "Bearer " + response.body().getMessage();
            }
            return response.code();
        } catch (IOException e) {
            return 0;
        }
    }

    private String encodeLoginHeader(String username, String password){
        String base = username + ":" + password;
        return "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
    }

    public int register(String username, String email, String password){
        FullUserDto userDto = new FullUserDto(username, email, password);
        Call<SimpleMessageDto> call = authAPI.register(userDto);
        try {
            return call.execute().code();
        } catch (IOException e) {
            return 0;
        }
    }

}

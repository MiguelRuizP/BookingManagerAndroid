package com.mruiz.bookingmanagerandroid.api.builder;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mruiz.bookingmanagerandroid.Constants;
import com.mruiz.bookingmanagerandroid.api.AuthAPI;
import com.mruiz.bookingmanagerandroid.model.payload.FullUserDto;
import com.mruiz.bookingmanagerandroid.model.payload.SimpleMessageDto;

import java.io.IOException;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIBuilder {

    public static APIBuilder publicBuilder;
    @Getter
    private Retrofit retrofit;
    private AuthAPI authAPI;
    @Getter
    private String token;

    public APIBuilder(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder().baseUrl(Constants.IP)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        authAPI = retrofit.create(AuthAPI.class);
    }

    public int login(String username, String password) {
        int code = 0;
        try{
            Response<SimpleMessageDto> response = getResponse(authAPI.token(encodeLoginHeader(username, password)));
            code = response.code();
            token = "Bearer " + response.body().getMessage();
        } catch (Exception ex) {

        }
        return code;
    }

    private String encodeLoginHeader(String username, String password){
        String base = username + ":" + password;
        return "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
    }

    public int register(String username, String email, String password){
        FullUserDto userDto = new FullUserDto(username, email, password);
        try {
            return getResponse(authAPI.register(userDto)).code();
        } catch (Exception ex) {
            return 0;
        }
    }

    public <T> Response<T> getResponse(Call<T> call){
        try{
            Response<T> response = call.execute();
            return response;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public <T> T getBody(Call<T> call) {
        Response<T> response = getResponse(call);
        if(response != null) {
            return response.body();
        } else {
            return null;
        }
    }

}

package com.mruiz.bookingmanagerandroid.api.builder;

import com.mruiz.bookingmanagerandroid.api.TestAPI;
import com.mruiz.bookingmanagerandroid.payload.SimpleMessageDto;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class TestAPIBuilder{

    private TestAPI testAPI;
    private APIBuilder apiBuilder;

    public TestAPIBuilder(APIBuilder apiBuilder) {
        this.apiBuilder = apiBuilder;
        testAPI = apiBuilder.getRetrofit().create(TestAPI.class);
    }

    public String test(){
        Call<SimpleMessageDto> call = testAPI.test(apiBuilder.getToken());
        try {
            Response<SimpleMessageDto> response = call.execute();
            if(response.isSuccessful()){
                return response.body().getMessage();
            }
        } catch (IOException e) {

        }
        return null;
    }
}

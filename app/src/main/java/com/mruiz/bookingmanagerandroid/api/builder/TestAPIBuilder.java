package com.mruiz.bookingmanagerandroid.api.builder;

import com.mruiz.bookingmanagerandroid.api.TestAPI;

public class TestAPIBuilder{

    private TestAPI testAPI;
    private APIBuilder apiBuilder;

    public TestAPIBuilder(APIBuilder apiBuilder) {
        this.apiBuilder = apiBuilder;
        testAPI = apiBuilder.getRetrofit().create(TestAPI.class);
    }

    public String test(){
        return apiBuilder.getBody(testAPI.test(apiBuilder.getToken())).getMessage();
    }
}

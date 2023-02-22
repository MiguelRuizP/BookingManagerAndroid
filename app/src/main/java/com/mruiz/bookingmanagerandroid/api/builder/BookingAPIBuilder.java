package com.mruiz.bookingmanagerandroid.api.builder;

import com.mruiz.bookingmanagerandroid.api.BookingAPI;
import com.mruiz.bookingmanagerandroid.model.payload.BookingListDto;
import com.mruiz.bookingmanagerandroid.model.payload.CreateBookingDto;

import java.util.Date;

public class BookingAPIBuilder {
    private BookingAPI bookingAPI;
    private APIBuilder apiBuilder;

    public BookingAPIBuilder(APIBuilder apiBuilder){
        this.apiBuilder = apiBuilder;
        bookingAPI = apiBuilder.getRetrofit().create(BookingAPI.class);
    }

    public BookingListDto getBookings(){
        return apiBuilder.getBody(bookingAPI.getBookings(apiBuilder.getToken()));
    }
    
    public boolean book(Date date) {
        CreateBookingDto createBookingDto = new CreateBookingDto(date);
        return apiBuilder.getResponse(bookingAPI.book(apiBuilder.getToken(), createBookingDto)).isSuccessful();
    }

}

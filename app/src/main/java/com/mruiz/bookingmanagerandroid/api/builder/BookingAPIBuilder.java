package com.mruiz.bookingmanagerandroid.api.builder;

import com.mruiz.bookingmanagerandroid.api.BookingAPI;
import com.mruiz.bookingmanagerandroid.model.payload.BookingListDto;
import com.mruiz.bookingmanagerandroid.model.payload.BookingDateDto;
import com.mruiz.bookingmanagerandroid.model.payload.ChangeBookingActiveDto;

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
        BookingDateDto bookingDateDto = new BookingDateDto(date);
        return apiBuilder.getResponse(bookingAPI.book(apiBuilder.getToken(), bookingDateDto)).isSuccessful();
    }

    public BookingListDto getBookingsDate(Date date) {
        BookingDateDto bookingDateDto = new BookingDateDto(date);
        return apiBuilder.getBody(bookingAPI.getBookingsDate(apiBuilder.getToken(), bookingDateDto));
    }

    public boolean setActive(int id, boolean active) {
        ChangeBookingActiveDto changeBookingActiveDto = new ChangeBookingActiveDto(id, active);
        return apiBuilder.getResponse(bookingAPI.setActive(apiBuilder.getToken(), changeBookingActiveDto)).isSuccessful();
    }

    public boolean delete(int id) {
        ChangeBookingActiveDto changeBookingActiveDto = new ChangeBookingActiveDto(id, false);
        return apiBuilder.getResponse(bookingAPI.delete(apiBuilder.getToken(), changeBookingActiveDto)).isSuccessful();
    }

}

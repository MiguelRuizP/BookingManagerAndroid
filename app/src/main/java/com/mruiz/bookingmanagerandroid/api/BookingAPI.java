package com.mruiz.bookingmanagerandroid.api;

import com.mruiz.bookingmanagerandroid.model.payload.BookingListDto;
import com.mruiz.bookingmanagerandroid.model.payload.CreateBookingDto;
import com.mruiz.bookingmanagerandroid.model.payload.SimpleMessageDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BookingAPI {
    @POST("/bookin/book")
    Call<SimpleMessageDto> book(@Header("Authorization") String authHeader, @Body CreateBookingDto createBookingDto);

    @POST("/booking/getBookings")
    Call<BookingListDto> getBookings(@Header("Authorization") String authHeader);
}

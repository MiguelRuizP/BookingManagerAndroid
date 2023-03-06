package com.mruiz.bookingmanagerandroid.api;

import com.mruiz.bookingmanagerandroid.model.payload.BookingListDto;
import com.mruiz.bookingmanagerandroid.model.payload.BookingDateDto;
import com.mruiz.bookingmanagerandroid.model.payload.ChangeBookingActiveDto;
import com.mruiz.bookingmanagerandroid.model.payload.SimpleMessageDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BookingAPI {
    @POST("/booking/book")
    Call<SimpleMessageDto> book(@Header("Authorization") String authHeader, @Body BookingDateDto bookingDateDto);

    @POST("/booking/getBookings")
    Call<BookingListDto> getBookings(@Header("Authorization") String authHeader);

    @POST("/booking/getBookingsDate")
    Call<BookingListDto> getBookingsDate(@Header("Authorization") String authHeader, @Body BookingDateDto bookingDateDto);

    @POST("/booking/setActive")
    Call<BookingListDto> setActive(@Header("Authorization") String authHeader, @Body ChangeBookingActiveDto changeBookingActiveDto);

    @POST("/booking/delete")
    Call<BookingListDto> delete(@Header("Authorization") String authHeader, @Body ChangeBookingActiveDto changeBookingActiveDto);
}

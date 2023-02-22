package com.mruiz.bookingmanagerandroid.model.payload;

import com.mruiz.bookingmanagerandroid.model.Booking;

import java.util.List;

import lombok.Data;

@Data
public class BookingListDto {
    private List<Booking> bookings;
}

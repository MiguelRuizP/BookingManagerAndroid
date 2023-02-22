package com.mruiz.bookingmanagerandroid.model.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBookingDto {
    private Date date;
}

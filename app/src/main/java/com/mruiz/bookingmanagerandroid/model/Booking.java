package com.mruiz.bookingmanagerandroid.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private int id;
    private int userId;
    private Date date;
    private boolean notified;
    private boolean active;
}

package com.mruiz.bookingmanagerandroid.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mruiz.bookingmanagerandroid.R;
import com.mruiz.bookingmanagerandroid.api.BookingAPI;
import com.mruiz.bookingmanagerandroid.api.builder.APIBuilder;
import com.mruiz.bookingmanagerandroid.api.builder.BookingAPIBuilder;
import com.mruiz.bookingmanagerandroid.model.payload.BookingDateDto;

import java.util.Calendar;
import java.util.Date;

public class CreateBookingActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_booking);

        DatePicker datePicker = findViewById(R.id.date);
        TimePicker timePicker = findViewById(R.id.time);
        Button createBooking = findViewById(R.id.create_booking);

        BookingAPIBuilder bookingAPIBuilder = new BookingAPIBuilder(APIBuilder.publicBuilder);

        createBooking.setOnClickListener(view -> {
            Date date = getDateFromPickers(datePicker, timePicker);

            // Si la fecha es mayor a la actual
            if (date.compareTo(new Date()) > 0){
                new Thread(() -> {
                    boolean bookStatus = bookingAPIBuilder.book(date);
                    this.runOnUiThread(() -> {
                        if (bookStatus){
                            Toast.makeText(getApplicationContext(),"Booking successfully created",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Error creating booking. Try again",Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();
                }).start();
            } else {
                Toast.makeText(getApplicationContext(),"Date must be past today",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static java.util.Date getDateFromPickers(DatePicker datePicker, TimePicker timePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);

        return calendar.getTime();
    }
}

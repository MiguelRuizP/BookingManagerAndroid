package com.mruiz.bookingmanagerandroid.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mruiz.bookingmanagerandroid.R;
import com.mruiz.bookingmanagerandroid.api.builder.APIBuilder;
import com.mruiz.bookingmanagerandroid.api.builder.BookingAPIBuilder;
import com.mruiz.bookingmanagerandroid.model.payload.BookingListDto;

public class BookingListActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIBuilder apiBuilder = APIBuilder.publicBuilder;

        BookingAPIBuilder bookingAPIBuilder = new BookingAPIBuilder(apiBuilder);

        new Thread(() -> {
            BookingListDto bookingListDto = bookingAPIBuilder.getBookings();
            bookingListDto.getBookings().forEach(System.out::println);
        }).start();
    }

}

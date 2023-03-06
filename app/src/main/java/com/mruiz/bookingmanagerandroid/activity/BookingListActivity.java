package com.mruiz.bookingmanagerandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.mruiz.bookingmanagerandroid.R;
import com.mruiz.bookingmanagerandroid.activity.adapter.BookingAdapter;
import com.mruiz.bookingmanagerandroid.api.builder.APIBuilder;
import com.mruiz.bookingmanagerandroid.api.builder.BookingAPIBuilder;
import com.mruiz.bookingmanagerandroid.model.Booking;
import com.mruiz.bookingmanagerandroid.model.payload.BookingListDto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class BookingListActivity  extends AppCompatActivity {

    private boolean reloadNeeded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_list);

        APIBuilder apiBuilder = APIBuilder.publicBuilder;
        BookingAPIBuilder bookingAPIBuilder = new BookingAPIBuilder(apiBuilder);

        CalendarView calendarView = findViewById(R.id.calendar);
        ListView listView = findViewById(R.id.listview);
        Button createBooking = findViewById(R.id.create_booking);
        Button refresh = findViewById(R.id.refresh);

        BookingAdapter bookingAdapter = new BookingAdapter(this, new ArrayList<>(), bookingAPIBuilder);
        listView.setAdapter(bookingAdapter);

        new Thread(() -> {
            BookingListDto bookingListDto = bookingAPIBuilder.getBookings();

            this.runOnUiThread(() -> {
                List<EventDay> events = new ArrayList<>();

                if(bookingListDto != null){
                    bookingListDto.getBookings().forEach(booking -> {
                        Calendar calendar = new GregorianCalendar();

                        calendar.setTime(booking.getDate());
                        events.add(new EventDay(calendar, R.drawable.ic_launcher_background));
                    });

                    calendarView.setEvents(events);
                }

            });
        }).start();

        calendarView.setOnDayClickListener(eventDay -> {
            Date date = eventDay.getCalendar().getTime();
            new Thread(() -> {
                List<Booking> bookings = bookingAPIBuilder.getBookingsDate(date).getBookings();
                this.runOnUiThread(() -> {
                    bookingAdapter.clear();
                    bookingAdapter.addAll(bookings);
                });
            }).start();
        });

        createBooking.setOnClickListener(view -> {
            reloadNeeded = true;
            startActivity(new Intent(this.getApplicationContext(), CreateBookingActivity.class));
        });

        refresh.setOnClickListener(view -> {
            finish();
            startActivity(getIntent());
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (reloadNeeded) {
            restart();
        }
    }

    public void restart(){
        finish();
        startActivity(getIntent());
    }

}

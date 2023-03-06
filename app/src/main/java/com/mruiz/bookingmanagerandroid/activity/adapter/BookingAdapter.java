package com.mruiz.bookingmanagerandroid.activity.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mruiz.bookingmanagerandroid.Constants;
import com.mruiz.bookingmanagerandroid.R;
import com.mruiz.bookingmanagerandroid.activity.BookingListActivity;
import com.mruiz.bookingmanagerandroid.api.builder.BookingAPIBuilder;
import com.mruiz.bookingmanagerandroid.model.Booking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookingAdapter extends ArrayAdapter<Booking> {

    private BookingAPIBuilder bookingAPIBuilder;
    private BookingListActivity activity;

    public BookingAdapter(BookingListActivity activity, List<Booking> bookings, BookingAPIBuilder bookingAPIBuilder){
        super(activity, R.layout.booking_listview, bookings);
        this.activity = activity;
        this.bookingAPIBuilder = bookingAPIBuilder;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        Booking booking = getItem(pos);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.booking_listview, parent, false);

        TextView id = convertView.findViewById(R.id.id);
        TextView date = convertView.findViewById(R.id.date);
        CheckBox active = convertView.findViewById(R.id.active);
        Button delete = convertView.findViewById(R.id.delete_button);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        id.setText("ID: " + booking.getId());
        date.setText(simpleDateFormat.format(booking.getDate()));
        active.setChecked(booking.isActive());
        active.setEnabled(booking.getDate().compareTo(new Date()) > 0);

        active.setOnClickListener(view -> {
            boolean updateActive = active.isChecked();
            new Thread(() -> bookingAPIBuilder.setActive(booking.getId(), updateActive)).start();
        });

        delete.setOnClickListener(view -> {
            new AlertDialog.Builder(activity)
                    .setTitle("DELETE")
                    .setMessage("Do you really want delete this booking?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                        new Thread(() -> {
                            if (bookingAPIBuilder.delete(booking.getId())) {
                                activity.runOnUiThread(() -> {
                                    activity.restart();
                                });
                            }
                        }).start();
                    })
                    .setNegativeButton(android.R.string.no, (dialog, whichButton) -> {}).show();
        });

        return convertView;
    }
}

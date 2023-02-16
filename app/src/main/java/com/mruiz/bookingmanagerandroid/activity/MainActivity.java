package com.mruiz.bookingmanagerandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mruiz.bookingmanagerandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);

        login.setOnClickListener(view -> startActivity(new Intent(this.getApplicationContext(), LoginActivity.class)));
        register.setOnClickListener(view -> startActivity(new Intent(this.getApplicationContext(), RegisterActivity.class)));
    }
}
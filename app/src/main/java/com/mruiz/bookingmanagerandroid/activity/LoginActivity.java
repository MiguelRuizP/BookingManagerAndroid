package com.mruiz.bookingmanagerandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mruiz.bookingmanagerandroid.Constants;
import com.mruiz.bookingmanagerandroid.R;
import com.mruiz.bookingmanagerandroid.api.builder.APIBuilder;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button button = findViewById(R.id.loginbutton);

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);

        button.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            new Thread(() -> {
                APIBuilder apiBuilder = new APIBuilder();
                int loginStatus = apiBuilder.login(username, password);

                this.runOnUiThread(() -> {
                    if(loginStatus == 200){
                        Toast.makeText(getApplicationContext(), "Welcome, " + username + "!",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(this.getApplicationContext(), BookingListActivity.class);
                        APIBuilder.publicBuilder = apiBuilder;
                        startActivity(intent);
                    } else if(loginStatus >= 400 && loginStatus < 500){
                        Toast.makeText(getApplicationContext(),username + " unauthorized",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Server connection error",Toast.LENGTH_SHORT).show();
                    }
                });

            }).start();

        });
    }
}

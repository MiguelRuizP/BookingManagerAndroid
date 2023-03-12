package com.mruiz.bookingmanagerandroid.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mruiz.bookingmanagerandroid.Constants;
import com.mruiz.bookingmanagerandroid.R;
import com.mruiz.bookingmanagerandroid.api.builder.APIBuilder;

public class RegisterActivity  extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button button = findViewById(R.id.registerbutton);

        EditText usernameEditText = findViewById(R.id.username);
        EditText emailEditText = findViewById(R.id.email);
        EditText passwordEditText = findViewById(R.id.password);

        button.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(),"You must complete all fields",Toast.LENGTH_SHORT).show();
            } else if (!email.trim().matches(emailPattern)) {
                Toast.makeText(getApplicationContext(),"You must enter a valid email",Toast.LENGTH_SHORT).show();
            } else {
                new Thread(() -> {
                    APIBuilder apiBuilder = new APIBuilder();
                    int requestStatus = apiBuilder.register(username, email, password);

                    this.runOnUiThread(() -> {
                        if(requestStatus == 200){
                            Toast.makeText(getApplicationContext(),username + " registered successfully!",Toast.LENGTH_SHORT).show();
                            finish();
                        } else if(requestStatus >= 400 && requestStatus < 500){
                            Toast.makeText(getApplicationContext(), "Couldn't register. Try changing user/email",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Server connection error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }).start();
            }
        });
    }
}

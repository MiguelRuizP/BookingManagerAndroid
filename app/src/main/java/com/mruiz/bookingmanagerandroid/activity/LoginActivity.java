package com.mruiz.bookingmanagerandroid.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mruiz.bookingmanagerandroid.Constants;
import com.mruiz.bookingmanagerandroid.R;
import com.mruiz.bookingmanagerandroid.api.builder.APIBuilder;
import com.mruiz.bookingmanagerandroid.api.builder.TestAPIBuilder;

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
                APIBuilder apiBuilder = new APIBuilder(Constants.IP);
                int loginStatus = apiBuilder.login(username, password);

                this.runOnUiThread(() -> {
                    if(loginStatus == 200){
                        Toast.makeText(getApplicationContext(),username + " logeado con éxito",Toast.LENGTH_SHORT).show();
                    } else if(loginStatus >= 400 && loginStatus < 500){
                        Toast.makeText(getApplicationContext(),username + " no autorizado",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error de conexión con el servidor",Toast.LENGTH_SHORT).show();
                    }
                });

            }).start();

        });
    }
}

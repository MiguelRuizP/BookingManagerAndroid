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

            new Thread(() -> {
                APIBuilder apiBuilder = new APIBuilder();
                int requestStatus = apiBuilder.register(username, email, password);

                this.runOnUiThread(() -> {
                    if(requestStatus == 200){
                        Toast.makeText(getApplicationContext(),username + " registrado con éxito",Toast.LENGTH_SHORT).show();
                    } else if(requestStatus >= 400 && requestStatus < 500){
                        Toast.makeText(getApplicationContext(), "No se ha podido registrar a " + username,Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error de conexión con el servidor",Toast.LENGTH_SHORT).show();
                    }
                });

            }).start();

        });
    }
}

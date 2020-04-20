package com.example.magazzino;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    protected EditText Email;
    protected EditText Password;
    protected Button Login;
    protected TextView Register;
    protected TextView FacebookLogin;
    protected int counter = 3;
    protected FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email =  findViewById(R.id.email);
        Password =  findViewById(R.id.password);
        Login =  findViewById(R.id.btn_login);
        Register =  findViewById(R.id.sendToSignUp);
        FacebookLogin =  findViewById(R.id.facebook_login);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(indent);
            }
        });
    }

    protected void validate(String useName, String usePassword) {
        if ((useName.equals("admin")) && (usePassword.equals("123"))) {
            Intent indent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(indent);
        } else {
            counter--;
            if (counter == 0) {
                Login.setEnabled(false);
            }
        }
    }

}

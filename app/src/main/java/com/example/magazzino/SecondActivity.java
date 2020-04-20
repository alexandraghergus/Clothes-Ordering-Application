package com.example.magazzino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    protected TextView Login;
    protected EditText Name;
    protected EditText ManagerCode;
    protected EditText phoneNumber;
    protected EditText emailAddress;
    protected EditText user;
    protected EditText password;
    protected Button SignUp;
    protected Switch aSwitch;
    protected FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Name = findViewById(R.id.Name);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailAddress = findViewById(R.id.mail);
        user = findViewById(R.id.username);
        password = findViewById(R.id.generatePassword);
        SignUp = findViewById(R.id.btn_sign_up);
        Login = findViewById(R.id.sendToLogin);
        aSwitch=findViewById(R.id.switch1);
        ManagerCode=findViewById(R.id.managercode);

        firebaseAuth = FirebaseAuth.getInstance();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String user_email = emailAddress.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SecondActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                //finish();
                                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(SecondActivity.this, "Registration Failed" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        ManagerCode.setVisibility(View.INVISIBLE);
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagerCode.setVisibility(View.VISIBLE);
                aSwitch.setVisibility(View.INVISIBLE);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(indent);
            }
        });
    }

    protected Boolean validate() {
        Boolean result = false;

        String fname = Name.getText().toString();
        String manager = ManagerCode.getText().toString();
        String phone = phoneNumber.getText().toString();
        String email = emailAddress.getText().toString();
        String pass = password.getText().toString();
        String username = user.getText().toString();

        if (fname.isEmpty() || manager.isEmpty() || phone.isEmpty() || email.isEmpty() || username.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}

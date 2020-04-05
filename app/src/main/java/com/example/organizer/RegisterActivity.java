package com.example.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends MainActivity {


    private TextView loginText;
    private TextView passwordText;
    private TextView confirmPasswordText;
    private TextView hintText;
    private TextView loginAccountText;

    private EditText loginInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private EditText hintInput;

    private Button registerButton;

    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        hideNavigationBar();

        dbHelper = new DatabaseHelper(this);

        loginText = findViewById(R.id.rloginText);
        passwordText = findViewById(R.id.rpasswordText);
        confirmPasswordText = findViewById(R.id.rconfirmPasswordText);
        hintText = findViewById(R.id.rhintText);
        loginAccountText = findViewById(R.id.rcreatedText);

        loginInput = findViewById(R.id.rloginInput);
        passwordInput = findViewById(R.id.rpasswordInput);
        confirmPasswordInput = findViewById(R.id.rconfirmPasswordInput);
        hintInput = findViewById(R.id.rhintInput);

        registerButton = findViewById(R.id.registerButton);

        loginAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmedPassword = confirmPasswordInput.getText().toString();
                String hint = hintInput.getText().toString();

                if(password.equals(confirmedPassword))
                {
                    long value = dbHelper.addUser(login, password, hint);
                    if(value>0)
                    {
                        Toast.makeText(getBaseContext(), "Account registered successfully!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Registration failed!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Passwords doesn't match!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

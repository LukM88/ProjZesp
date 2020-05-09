package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.PasswordAuthentication;

public class MainActivity extends AppCompatActivity {
    public static String login;
    private TextView titleText;
    private TextView loginText;
    private TextView passwordText;
    private TextView codeText;
    private TextView codeViewText;
    private TextView createAccountText;

    private EditText loginInput;
    private EditText passwordInput;
    private EditText codeInput;

    private ImageView hintImage;

    private Button forgotButton;
    private Button loginButton;

    private int code;
    private String sCode;

    DatabaseHelper dbHelper;
    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideNavigationBar();

        dbHelper = new DatabaseHelper(this);

        titleText = findViewById(R.id.titleText);
        loginText = findViewById(R.id.loginText);
        passwordText = findViewById(R.id.passwordText);
        codeText = findViewById(R.id.codeText);
        codeViewText = findViewById(R.id.codeView);
        createAccountText = findViewById(R.id.createText);

        loginInput = findViewById(R.id.loginInput);
        passwordInput = findViewById(R.id.passwordInput);
        codeInput = findViewById(R.id.codeInput);

        forgotButton = findViewById(R.id.forgotButton);
        loginButton = findViewById(R.id.loginButton);

        hintImage = findViewById(R.id.hintImage);

        int max = 9999;
        int min = 1000;
        int range = max - min + 1;

        code = (int)(Math.random() * range) + min;
        sCode = String.valueOf(code);
        codeViewText.setText(sCode);

        hintImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Rewrite the code from right to the field", Toast.LENGTH_LONG).show();
            }
        });

        createAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sqlDB = dbHelper.getReadableDatabase();
                Cursor cursor = dbHelper.getHint(loginInput.getText().toString(), sqlDB);
                if(cursor.moveToFirst()) {
                    String hint = cursor.getString(0);
                    Toast.makeText(getBaseContext(), hint, Toast.LENGTH_SHORT).show();
                    dbHelper.close();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = loginInput.getText().toString();
                String password = passwordInput.getText().toString();
                boolean res = dbHelper.doesUserExist(login,password);
                if(res)
                {
                    if(codeInput.getText().toString().equals(codeViewText.getText().toString()))
                    {
                        Toast.makeText(getBaseContext(), "Login success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), CalendarActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Wrong code!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void hideNavigationBar()
    {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }

}

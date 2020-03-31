package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button nextButton;
    private TextView titleText;
    private EditText password;

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
        // myDB= new DBHelper(this);

        password =(EditText) findViewById(R.id.editText);
        nextButton = findViewById(R.id.nextButton);
        titleText = findViewById(R.id.titleText);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = null;

                String json="";
                try {
                    InputStream is = getAssets().open("data");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    String line = "";
                    while (line!=null){
                        line=bufferedReader.readLine();
                        json= json + line;
                    }

                    JSONArray jasonArray = new JSONArray(json);
                    JSONObject jasonObject = jasonArray.getJSONObject(0);
                    pwd = jasonObject.getString("password");
                    if(password.getText().toString().equals(pwd)) {
                        Intent intent = new Intent(getBaseContext(), CalendarActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getBaseContext(),"błędne hasło",Toast.LENGTH_LONG).show();
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "błąd pliku", Toast.LENGTH_LONG).show();


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

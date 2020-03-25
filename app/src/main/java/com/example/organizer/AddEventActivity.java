package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AddEventActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Intent intent = getIntent();

        String selectedDay = intent.getStringExtra("day");
        String selectedMonth = intent.getStringExtra("month");
        String selectedYear = intent.getStringExtra("year");
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }
}

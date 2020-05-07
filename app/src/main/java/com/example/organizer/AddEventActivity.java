package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddEventActivity extends MainActivity {
    private Button backBtn;
    private Button addBtn;
    private EditText nameText;
    private EditText descriptionText;
    private Spinner day;
    private Spinner month;
    private Spinner year;
    private EditText HH;
    private EditText MM;
    private Spinner notification;
    private Spinner priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        hideNavigationBar();
        Intent intent = getIntent();
        backBtn=findViewById(R.id.cancelButton);
        addBtn=findViewById(R.id.addEventButton);
        nameText=findViewById(R.id.eventNameText);
        descriptionText=findViewById(R.id.descriptionText);
        day=findViewById(R.id.eventSpinnerDay);
        month=findViewById(R.id.eventSpinnerMonth);
        year=findViewById(R.id.eventSpinnerYear);
        HH=findViewById(R.id.hourText);
        MM=findViewById(R.id.minutesText);
        priority=findViewById(R.id.prioritySpinner);
        notification=findViewById(R.id.notificationSpinner);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //TODO napisanie dodawania eventu do bazy przemyśleć wywalenie spinerów na dizeń
               // DatabaseHelper dbHelper = new DatabaseHelper(getBaseContext());

               // dbHelper.addEvent(this.)

                Intent intent = new Intent(getBaseContext(), CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });

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

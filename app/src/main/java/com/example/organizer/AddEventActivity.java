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
import android.widget.Toast;

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
        String selectedDay = intent.getStringExtra("day");
        String selectedMonth = intent.getStringExtra("month");
        String selectedYear = intent.getStringExtra("year");
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
               DatabaseHelper dbHelper = new DatabaseHelper(getBaseContext());
                if(!nameText.getText().toString().isEmpty()) {
                   if(!selectedYear.isEmpty()|| !selectedMonth.isEmpty() || !selectedDay.isEmpty()){
                       try {
                           dbHelper.addEvent(nameText.getText().toString(), descriptionText.getText().toString(), HH.getText().toString(), MM.getText().toString(), " ", false, day.toString(), month.toString(), year.toString(), MainActivity.login);
                       }catch (Exception e){
                           Toast.makeText(getBaseContext(),"Invalid data type!!",Toast.LENGTH_LONG).show();
                           e.printStackTrace();
                       }finally {
                           dbHelper.close();
                       }
                       }else {
                       Toast.makeText(getBaseContext(),"Event have to have date",Toast.LENGTH_LONG).show();
                   }

                }else{
                    Toast.makeText(getBaseContext(),"Event have to have name!!",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getBaseContext(),"Event added",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), CalendarActivity.class);
                startActivity(intent);

                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }
}

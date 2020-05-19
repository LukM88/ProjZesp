package com.example.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class CalendarActivity extends MainActivity  {

    private CalendarView calendar;
    private Button todoBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        hideNavigationBar();
        DatabaseHelper db = new DatabaseHelper(getBaseContext());
        //db.showEvents();
        calendar = findViewById(R.id.calendar);
        todoBtn = findViewById(R.id.todoBtn);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                MyDate date = new MyDate();
                date.setDay(String.valueOf(dayOfMonth));
                date.setMonth( String.valueOf(month + 1));
                date.setYear(String.valueOf(year));
                String selectedDate = date.getDate();

                Intent intent = new Intent(getBaseContext(), AddEventActivity.class);
                intent.putExtra("day", date.getDay());
                intent.putExtra("month", date.getMonth());
                intent.putExtra("year", date.getYear());
                startActivity(intent);

                Toast.makeText(getBaseContext(), selectedDate, Toast.LENGTH_LONG).show();
            }
        });
        todoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ToDoListActivity.class);
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

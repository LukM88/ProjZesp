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

        calendar = findViewById(R.id.calendar);
        todoBtn = findViewById(R.id.todoBtn);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = (dayOfMonth) + "-" + (month + 1) + "-" + (year);
                String selectedDay = String.valueOf(dayOfMonth);
                String selectedMonth = String.valueOf(month + 1);
                String selectedYear = String.valueOf(year);

                Intent intent = new Intent(getBaseContext(), AddEventActivity.class);
                intent.putExtra("day", selectedDay);
                intent.putExtra("month", selectedMonth);
                intent.putExtra("year", selectedYear);
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

package com.example.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class CalendarActivity extends MainActivity  {

    private CalendarView calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        hideNavigationBar();

        calendar = findViewById(R.id.calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = (dayOfMonth) + "-" + (month+1) + "-" + (year);
                String selectedDay = String.valueOf(dayOfMonth);
                String selectedMonth = String.valueOf(month+1);
                String selectedYear = String.valueOf(year);

                Intent intent = new Intent(getBaseContext(), AddEventActivity.class);
                intent.putExtra("day", selectedDay);
                intent.putExtra("month", selectedMonth);
                intent.putExtra("year", selectedYear);
                startActivity(intent);

                Toast.makeText(getBaseContext(), selectedDate, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }
}

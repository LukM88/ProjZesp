package com.example.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlanForDay extends MainActivity{
    private ListView lista;
    private TextView title;
    private TextView description;
    private TextView time;
    private FloatingActionButton addButt;
    private Button backButt;
    private MyDate date=new MyDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_for_day);
        hideNavigationBar();
        date.setDay(getIntent().getStringExtra("day"));
        date.setMonth(getIntent().getStringExtra("mont"));
        date.setYear(getIntent().getStringExtra("year"));
        title=findViewById(R.id.dateText);
        title.setText(date.getDate());
        lista=findViewById(R.id.listForDay);
        lista.setAdapter(new CustomAdapter(getBaseContext(),date));
        description=findViewById(R.id.textView2);
        time=findViewById(R.id.HHtext);
        backButt=findViewById(R.id.backButton);
        backButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });
        addButt=findViewById(R.id.floatingActionButton2);
        addButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedDate = date.getDate();

                Intent intent = new Intent(getBaseContext(), AddEventActivity.class);
                intent.putExtra("day", date.getDay());
                intent.putExtra("month", date.getMonth());
                intent.putExtra("year", date.getYear());
                startActivity(intent);

                Toast.makeText(getBaseContext(), selectedDate, Toast.LENGTH_LONG).show();
            }
        });
    }

}

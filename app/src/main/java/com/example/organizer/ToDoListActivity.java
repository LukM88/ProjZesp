package com.example.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

public class ToDoListActivity extends MainActivity {
    private ListView lista;
    private Button logOut;
    private Button calenderBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        hideNavigationBar();

        lista = findViewById(R.id.lista);
        CustomAdapter customAdapter = new CustomAdapter(getBaseContext ());
        lista.setAdapter(customAdapter);
        logOut = findViewById(R.id.wylogujBtn);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        calenderBtn = findViewById(R.id.calenderBtn);
        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Intent intent = new Intent(getBaseContext(), CalendarActivity.class);

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }
}

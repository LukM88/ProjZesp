package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ToDoListActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }
}

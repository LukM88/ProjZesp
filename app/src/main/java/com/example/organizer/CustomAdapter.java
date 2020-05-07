package com.example.organizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.organizer.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    ArrayList<ToDo> names=new ArrayList<ToDo>();
    Context context;
    LayoutInflater inflter;
    String value;
    ListView gridView;
    DatabaseHelper dbHelper;

    public CustomAdapter(Context context) {
        this.context = context;
        inflter = (LayoutInflater.from(context));
        dbHelper = new DatabaseHelper(context);
        //TODO usunąć wymuszane dodawanie eventów
        //dbHelper.addEvent("Event","Event na podstawie klasy ToDo","00","00","fuck",false,"01","05","2020");
        //dbHelper.addEvent("Event2","Event2 na podstawie klasy ToDo","00","00","fuck",false,"01","05","2020");
        //dbHelper.addEvent("Event3","Event3 na podstawie klasy ToDo","00","00","fuck",false,"01","05","2020");

        names=dbHelper.getEvents();
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.list_item, null);
        final CheckedTextView simpleCheckedTextView = view.findViewById(R.id.simpleCheckedTextView);
        simpleCheckedTextView.setText(names.get(position).getName());
// perform on Click Event Listener on CheckedTextView
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleCheckedTextView.isChecked()) {
// set cheek mark drawable and set checked property to false
                    value = "un-Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(null);
                    simpleCheckedTextView.setChecked(false);
                } else {
// set cheek mark drawable and set checked property to true
                    value = "Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(R.drawable.check);
                    simpleCheckedTextView.setChecked(true);
                }
                Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
            }
        });
        simpleCheckedTextView.setOnLongClickListener (new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,names.get(position).getDescription()+" \ntime:"+names.get(position).getTime()  ,Toast.LENGTH_LONG).show();
                return false;
            }



        });
        return view;
    }
}
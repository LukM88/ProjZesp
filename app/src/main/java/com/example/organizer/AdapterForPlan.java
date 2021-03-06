package com.example.organizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterForPlan extends BaseAdapter {
    ArrayList<ToDo> names=new ArrayList<ToDo>();
    Context context;
    LayoutInflater inflter;
    String value;
    ListView gridView;
    DatabaseHelper dbHelper;
    int focuse=0;
    public AdapterForPlan(Context context,MyDate date){
        this.context = context;
        inflter = (LayoutInflater.from(context));
        dbHelper = new DatabaseHelper(context);
        //dbHelper.showEvents();
        names=dbHelper.getToDoes(date);
        dbHelper.close();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.list_item, null);

        final CheckedTextView simpleCheckedTextView = convertView.findViewById(R.id.simpleCheckedTextView);
        final ImageView imageView = convertView.findViewById(R.id.imageView2);
        simpleCheckedTextView.setText(names.get(position).getName());
        this.focuse=position;
        System.out.println(imageView.isShown());
        if (names.get(position).getState()) {
            simpleCheckedTextView.setChecked(true);
            simpleCheckedTextView.setCheckMarkDrawable(R.drawable.check);
        } else {
            simpleCheckedTextView.setChecked(false);
        }

// perform on Click Event Listener on CheckedTextView
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                dbHelper= new DatabaseHelper(context);

                if (names.get(position).getState()) {
// set cheek mark drawable and set checked property to false
                    value = "un-Checked";
                    dbHelper.chceck(names.get(position));
                    names.get(position).setState(false);
                    simpleCheckedTextView.setCheckMarkDrawable(null);
                    simpleCheckedTextView.setChecked(false);
                } else {
                    dbHelper.chceck(names.get(position));
// set cheek mark drawable and set checked property to true

                    value = "Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(R.drawable.check);
                    simpleCheckedTextView.setChecked(true);
                    names.get(position).setState(true);
                }
                //names.set(position,dbHelper.getEvent(Integer.parseInt(names.get(position).getID())));
                dbHelper.close();

            }
        });
        simpleCheckedTextView.setOnLongClickListener (new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                return false;
            }



        });
        return convertView;
    }
    public ToDo getDetails(int positione){
        return names.get(positione);
    }
}

package com.example.organizer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbName = "Calender.db";
    public static final String usersTab = "User";
    public static final String userId = "id";
    public static final String userPhoneNum = "PHONE";
    public static final String userEMail = "email";
    public static final String userPassword = "pwd";

    public static final String calenderTab = "Calender";
    public static final String calenderId = "id";
    public static final String calenderName = "name";
    public static final String calenderDateTime = "YYYYMMDDHHMM";
    public static final String calenderNotyfication = "Notyfication";
    public static final String calenderPriority = "Priority";
    public static final String calenderDescription = "Description";

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ usersTab +" ("+ userId +" INTEGER PRIMARY KEY, "+ userPhoneNum +" TEXT, "+ userEMail +" TEXT, "+ userPassword +" TEXT)");
        db.execSQL("CREATE TABLE "+ calenderTab +" ("+ calenderId +" INTEGER PRIMARY KEY, "+ calenderName +" TEXT, "+ calenderDateTime +" DATEIME, "+calenderNotyfication+" INT, "+calenderPriority+" INT, "+calenderDescription+" TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ usersTab);
        onCreate(db);
    }
}

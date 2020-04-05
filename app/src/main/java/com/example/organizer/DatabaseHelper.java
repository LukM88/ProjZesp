package com.example.organizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "organizer.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "login";
    public static final String COL_3 = "password";
    public static final String COL_4 = "hint";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (ID INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, password TEXT, hint TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String login, String password, String hint){
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("password", password);
        contentValues.put("hint", hint);
        long res = sqlDB.insert("users", null, contentValues);
        sqlDB.close();
        return res;
    }

    public boolean doesUserExist(String login, String password){
        String[] columns = {COL_1};
        SQLiteDatabase sqlDB = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {login, password};
        Cursor cursor = sqlDB.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        sqlDB.close();

        if(count>0)
            return true;
        else
            return false;
    }

    public Cursor getHint(String login, SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {COL_4};
        String selection = COL_2 + " LIKE ?";
        String[] selection_args = {login};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, projections, selection, selection_args, null,null,null);
        return cursor;
    }
}

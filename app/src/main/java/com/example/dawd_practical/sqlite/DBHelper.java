package com.example.dawd_practical.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dawd_practical.models.Feedback;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "feedback";
    // Country table name
    private static final String TABLE_FEEDBACK = "feedback";
    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String MESSAGE = "Message";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + TABLE_FEEDBACK
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + MESSAGE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_FEEDBACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new country
    public void add(Feedback feedback) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, feedback.getName());
        values.put(EMAIL, feedback.getEmail());
        values.put(MESSAGE, feedback.getMessage());

        // Inserting Row
        db.insert(TABLE_FEEDBACK, null, values);
        db.close(); // Closing database connection
    }

    // Getting single country
    Feedback get(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_FEEDBACK, new String[]{KEY_ID, NAME, EMAIL, MESSAGE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Feedback employee = new Feedback(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getLong(2));
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return country
        return employee;
    }

    // Getting All Countries
    public List getAll() {
        List<Feedback> list = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FEEDBACK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Feedback feedback = new Feedback();
                feedback.setId(Integer.parseInt(cursor.getString(0)));
                feedback.setName(cursor.getString(1));
                feedback.setEmail(cursor.getString(2));
                feedback.setMessage(cursor.getString(3));
                list.add(feedback);
            } while (cursor.moveToNext());
        }
        return list;
    }


    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FEEDBACK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}

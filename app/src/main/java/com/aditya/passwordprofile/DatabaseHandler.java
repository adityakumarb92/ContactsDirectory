package com.aditya.passwordprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "usersManager";

    // users table name
    private static final String TABLE_USERS = "users";

    // users Table Columns names
    private static final String KEY_PASS = "pass";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHOTO = "photo";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USERS="CREATE TABLE " + TABLE_USERS + "("
                + KEY_NAME +" TEXT PRIMARY KEY,"
                + KEY_PASS +" TEXT,"
                + KEY_PHOTO  +" BLOB" + ")";
        db.execSQL(CREATE_TABLE_USERS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }



    //Insert values to the table users
    public void addusers(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values=new ContentValues();

        values.put(KEY_NAME, user.getName());
        values.put(KEY_PASS,user.getPasscode());
        values.put(KEY_PHOTO, user.getImage() );


        db.insert(TABLE_USERS, null, values);
        db.close();
    }




    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setName(cursor.getString(0));
                user.setPasscode(cursor.getString(1));
                user.setImage(cursor.getBlob(2));


                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return user list
        return userList;
    }







}

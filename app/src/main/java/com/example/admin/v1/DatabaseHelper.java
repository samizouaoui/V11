package com.example.admin.v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * Created by Admin on 03/12/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_FIRSTNAME = "prenom";
    private static final String COLUMN_LASTNAME = "nom";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "mp";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table user (prenom text not null,nom text not null,email text primary key,mp text not null);";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = ("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL(query);
        this.onCreate(db);
    }


    public boolean CheckEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public void insertuser(user u) {
        db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(COLUMN_FIRSTNAME, u.getPrenom());
        Values.put(COLUMN_LASTNAME, u.getNom());
        Values.put(COLUMN_EMAIL, u.getEmail());
        Values.put(COLUMN_PASSWORD, u.getMp());

        db.insert(TABLE_NAME, null, Values);
        db.close();
    }

    public String searchPass(EditText email) {
        db = this.getReadableDatabase();
        String query = "select email,mp from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }
}

package com.example.admin.v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Admin on 03/12/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user1.db";
    public static final String TABLE_NAME = "user";
    private static final String COLUMN_FIRSTNAME = "prenom";
    private static final String COLUMN_LASTNAME = "nom";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "mp";
    private static final String COLUMN_POIDS = "poids";
    private static final String COLUMN_TAILLE = "taille";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (prenom TEXT,nom TEXT, email TEXT PRIMARY KEY ,mp INTEGER,poids TEXT NULL,taille TEXT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean CheckEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }


    public boolean insertData(user u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, u.getPrenom());
        contentValues.put(COLUMN_LASTNAME, u.getNom());
        contentValues.put(COLUMN_EMAIL, u.getEmail());
        contentValues.put(COLUMN_PASSWORD, u.getMp());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updatePT(String id,String p,String t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL,id);
        contentValues.put(COLUMN_POIDS,p);
        contentValues.put(COLUMN_TAILLE,t);

        long result= db.update(TABLE_NAME, contentValues, "email = ?",new String[] { id });
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor searchPass(String email, String mp) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select email,mp from " + TABLE_NAME+" where email='"+email+"' and mp='"+mp+"';", null);
       return cursor;
    }

    public Cursor getPoids(String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select poids from " + TABLE_NAME+" where email='"+email+"';", null);
        return cursor;

    }

    public Cursor getTaille(String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select taille from " + TABLE_NAME+" where email='"+email+"';", null);
        return cursor;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}

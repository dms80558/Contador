package com.example.contador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "usuarios";
    private static final String USER_ID = "_id";
    public static final String COLUMN_USERNAME = "nombre_usuario";
    public static final String COLUMN_PASSWORD = "contraseña";
    public static final String COLUMN_SCORE = "puntos";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_SCORE + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", COLUMN_USERNAME);
        values.put("password", COLUMN_PASSWORD);

        long result = db.insert("usuarios", null, values);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] u = {username};
        Cursor cursor = db.rawQuery("select * from usuarios where nombre_usuario=?", u);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkPassword(String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] u = {password};
        Cursor cursor = db.rawQuery("select * from usuarios where contraseña=?", u);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkuserNamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] u = {username};
        String [] p = {password};
        Cursor cursor = db.rawQuery("select * from usuarios where nombre_usuario=? and contraseña=?", u );
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

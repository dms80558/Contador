package com.example.contador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.math.BigInteger;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "datos";
    public static final String COLUMN_USERNAME = "nombre_usuario";
    public static final String COLUMN_PASSWORD = "contraseña";
    public static final String COLUMN_SCORE = "puntos";
    public static final String COLUMN_COSTO = "costo";
    public static final String COLUMN_INCREMENTO = "incremento";
    public static final String COLUMN_ICON = "icono";
    public static final String COLUMN_AUTOCLICK = "autoclick";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY UNIQUE, " +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_SCORE + " TEXT," +
                COLUMN_COSTO + " INTEGER," +
                COLUMN_INCREMENTO + " INTEGER," +
                COLUMN_ICON + " INTEGER,"+
                COLUMN_AUTOCLICK + " INTEGER CHECK ( " + COLUMN_AUTOCLICK + " IN (0,1)" +")"
                +");";
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

        long result = db.insert("datos", null, values);
        if (result == -1) return false;
        else
            return true;
    }


    public void saveDatos(String nombreuser, String score,int costo, int incremento, int icon, int autoclick) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from datos where nombre_usuario=?", new String[]{nombreuser});

        String password="";
        if(cursor.moveToFirst() && cursor.getCount() >= 1){
            int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);
            password = cursor.getString(passwordIndex);
        }
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, nombreuser);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_SCORE, score);
        cv.put(COLUMN_COSTO,costo);
        cv.put(COLUMN_INCREMENTO,incremento);
        cv.put(COLUMN_ICON,icon);
        cv.put(COLUMN_AUTOCLICK,autoclick);

        long result = db.update(TABLE_NAME, cv, "nombre_usuario=?", new String[]{nombreuser});
        if (result <=0) {
            Toast.makeText(context, "No se ha podido guardarlos datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Se han guardado los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] u = {username};
        Cursor cursor = db.rawQuery("select * from datos where nombre_usuario=?", u);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkPassword(String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] u = {password};
        Cursor cursor = db.rawQuery("select * from datos where contraseña=?", u);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkuserNamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] u = {username, password};

        Cursor cursor = db.rawQuery("select * from datos where nombre_usuario=? and contraseña=?", u);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}

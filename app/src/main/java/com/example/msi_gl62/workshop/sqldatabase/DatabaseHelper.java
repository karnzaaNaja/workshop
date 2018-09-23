package com.example.msi_gl62.workshop.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.msi_gl62.workshop.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "register";
    private static final String ID = "id";
    private static final String TABLE_USER = "user";
    private static final String COL_NAME = "name";
    private static final String COL_PASS = "pass";
    private static final String COL_EMAIL = "email";
    private static final String COL_TEL = "tel";
    private static final String COL_ADDRESS = "address";


    private static final String CREATE_DATABASE =
            "CREATE TABLE " + TABLE_USER + "(" + ID + " INTEGER PRIMARY KEY,"
                    + COL_NAME + " VARCHAR(50)," + COL_PASS + " VARCHAR(50),"+COL_TEL + " VARCHAR(50),"+COL_ADDRESS + " VARCHAR(50),"
                    + COL_EMAIL + " VARCHAR(50)" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
        Log.i(TAG, "CREATE DATABASE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        Log.i(TAG, "UPGRADE DATABASE");
        onCreate(db);
    }


    public List<UserModel> readUser(){
        List<UserModel> listUser = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do{
                UserModel model = new UserModel();
                model.setId(cursor.getString(cursor.getColumnIndex(ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                model.setPass(cursor.getString(cursor.getColumnIndex(COL_PASS)));
                model.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
                model.setTel(cursor.getString(cursor.getColumnIndex(COL_TEL)));
                model.setAddress(cursor.getString(cursor.getColumnIndex(COL_ADDRESS)));
                listUser.add(model);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return listUser;
    }


    public void insertUser(String name,String pass,String email,String tel,String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("pass",pass);
        values.put("email",email);
        values.put("tel",tel);
        values.put("address",address);

        db.insert(TABLE_USER,null,values);
    }

    public void updateUser(String id,String name,String pass,String email,String tel,String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("pass",pass);
        values.put("email",email);
        values.put("tel",tel);
        values.put("address",address);

        db.update(TABLE_USER,values,ID + " = ?",new String[]{id});
        Log.i(TAG, "UPDATE DATABASE");
    }

    public void deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER,ID + " = ?",new String[]{id});
        Log.i(TAG, "DELETE DATABASE");
    }
}

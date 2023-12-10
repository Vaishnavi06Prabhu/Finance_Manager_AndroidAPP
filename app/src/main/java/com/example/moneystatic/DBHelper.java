package com.example.moneystatic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }
//name=amount contact=type, dob=category
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Userdetails (id INTEGER PRIMARY KEY AUTOINCREMENT, amount TEXT, type TEXT, category TEXT,userid TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    //Insert
    public Boolean insertuserdata(String amount, String type, String category,String userid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("type", type);
        contentValues.put("category", category);
        contentValues.put("userid", userid);

        long result = DB.insert("Userdetails", null, contentValues);
        return result != -1;
    }

    //UpDate
    public Boolean updateuserdata(int id, String amount, String type, String category,String userid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("type", type);
        contentValues.put("category", category);
        contentValues.put("userid", userid);

        int result = DB.update("Userdetails", contentValues, "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }


    //Delete
    public Boolean deleteuserdata(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();

        int result = DB.delete("Userdetails", "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }


    //View
    public  Cursor Getdata()
    {
        SQLiteDatabase DB=this.getWritableDatabase();


        Cursor cursor=DB.rawQuery("Select * from Userdetails",null);
        return  cursor;
    }
    // View
    public Cursor getDataByUserId(String userid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Userdetails WHERE userid=?", new String[]{userid});
    }
}

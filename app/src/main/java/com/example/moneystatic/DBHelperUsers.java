package com.example.moneystatic;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import androidx.annotation.Nullable;

public class DBHelperUsers extends SQLiteOpenHelper {
    public DBHelperUsers(Context context) {
        super(context, "UserLogin.db", null, 1);
    }
    //name=amount contact=type, dob=category
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE UserTable (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, emai TEXT, password TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists UserTable");
    }

    //Insert
    public Boolean insertuserdata(String name, String emai, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("emai", emai);
        contentValues.put("password", password);

        long result = DB.insert("UserTable", null, contentValues);
        return result != -1;
    }

    //UpDate
    public Boolean updateuserdata(int id, String name, String emai, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("emai", emai);
        contentValues.put("password", password);

        int result = DB.update("UserTable", contentValues, "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }


    //Delete
    public Boolean deleteuserdata(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();

        int result = DB.delete("UserTable", "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }


    //View
    public Cursor Getdata()
    {
        SQLiteDatabase DB=this.getWritableDatabase();


        Cursor cursor=DB.rawQuery("Select * from UserTable",null);
        return  cursor;
    }
//
public boolean checkCredentials(String email, String password) {
    SQLiteDatabase DB = this.getReadableDatabase();
    String[] columns = {"emai", "password"};
    String selection = "emai=? and password=?";
    String[] selectionArgs = {email, password};

    // Query the database to check if the credentials are valid
    Cursor cursor = DB.query("UserTable", columns, selection, selectionArgs, null, null, null);

    boolean isValid = cursor.moveToFirst(); // If moveToFirst() returns true, it means the credentials are valid

    cursor.close();
    return isValid;
}
//

}

package com.food_court;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context,"Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Userdetails(name TEXT primary key, age TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Userdetails");
    }
//    ---------------------------------------- Create
    public boolean createData(String name , String age){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("age",age);
        long result= DB.insert("Userdetails",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
//----------------------------------Update
    public boolean updateData(String name , String age){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("age",age);

        Cursor cursor = DB.rawQuery("select 8 from Userdetails where name=?",new String[]{name});
        if(cursor.getCount()>0){
            long result= DB.update("Userdetails",contentValues,"name=?",new String[]{name});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
// ------------------------------------ Delete
    public boolean deleteData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select 8 from Userdetails where name=?",new String[]{name});
        if(cursor.getCount()>0){
            long result= DB.delete("Userdetails","name=?",new String[]{name});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
// ----------------------------- Read
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
    }
}

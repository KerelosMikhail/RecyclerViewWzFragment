package com.kerelos.recyclerviewsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kerelos.recyclerviewsample.model.Student;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context) {
        super(context, "myClass.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table kid ( id Integer primary key autoincrement, name text, age Text )");
        Log.d("created","table OK") ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists kid ");
        onCreate(db);
    }
    public boolean insertData (String name, String age){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put("name",name);
        row.put("age",age);

        long result = db.insert("kid",null,row);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllDate (){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        res = db.rawQuery("Select * from kid",null);
        return res;

    }

    public ArrayList<Student> getData(){

        ArrayList<Student> all = new ArrayList<Student>();
        Cursor res = getAllDate();

        while (res.moveToNext()){
            Student student = new Student();
            student.setId(res.getString(0));
            student.setName(res.getString(1));
            student.setPhone(res.getString(2));
            all.add(student);
        }
        return all;
    }

}

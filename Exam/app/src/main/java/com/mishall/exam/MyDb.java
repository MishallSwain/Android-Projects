package com.mishall.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDb extends SQLiteOpenHelper {

    public static final String DBNAME="contacts.db";
    public static final String TNAME="contacts";
    public static final int VERSION=1;
    public static final String COL1="name";
    public static final String COL2="cno";
    public static final String COL3="email";


    public MyDb(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TNAME+" (name text,cno text primary key,email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TNAME);
        onCreate(db);
    }
    public boolean insertData(String name, String cno, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV=new ContentValues();
        cV.put(COL1,name);
        cV.put(COL2,cno);
        cV.put(COL3,email);
        long result = db.insert(TNAME,null,cV);
        if (result ==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TNAME,null);
        return (res);
    }

    public boolean updateData(String name, String cno, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV=new ContentValues();
        cV.put(COL1,name);
        cV.put(COL2,cno);
        cV.put(COL3,email);
        db.update(TNAME,cV, "name = ?",new String[] { name });
        return true;
    }
    public Integer deleteData (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TNAME, "name = ?",new String[] {name});
    }

}
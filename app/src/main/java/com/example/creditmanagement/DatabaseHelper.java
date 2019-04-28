package com.example.creditmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "CREDIT";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,CREDIT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,user.getName());
        contentValues.put(COL_3,user.getEmail());
        contentValues.put(COL_4,user.getCredit());
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    /*public boolean insertDataTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_TRANSACTION,transaction.getSenderName());
        contentValues.put(COL_3_TRANSACTION,transaction.getRecieverName());
        contentValues.put(COL_4_TRANSACTION,transaction.getAmount());
        long result=db.insert(TABLE_NAME_TRANSACTION,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }*/

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    /*public Cursor getAllDataTransaction(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME_TRANSACTION,null);
        return cursor;
    }*/

    public Cursor getRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select NAME, EMAIL, CREDIT from "+TABLE_NAME+" WHERE ID = ?",new String[]{id});
        return cursor;
    }

    /*public Cursor getRowTransaction(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select SENDER_NAME, RECIEVER_NAME, CREDIT from "+TABLE_NAME_TRANSACTION+" WHERE ID = ?",new String[]{id});
        return cursor;
    }*/

//updating by name
    public boolean updateData(String name,int credit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4,credit);
        db.update(TABLE_NAME, contentValues, "NAME = ?",new String[] {name});
        return true;
    }
    public void updateId(String oldId, String newId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,newId);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] {oldId});
    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
       return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
}

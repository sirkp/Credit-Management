package com.example.creditmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class TransactionDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Transaction.db";
    public static final String TABLE_NAME = "all_transaction_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SENDER_NAME";
    public static final String COL_3 = "RECIEVER_NAME";
    public static final String COL_4 = "CREDIT";


    public TransactionDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,SENDER_NAME TEXT,RECIEVER_NAME TEXT,CREDIT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,transaction.getSenderName());
        contentValues.put(COL_3,transaction.getRecieverName());
        contentValues.put(COL_4,transaction.getAmount());
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public Cursor getRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select SENDER_NAME, RECIEVER_NAME, CREDIT from "+TABLE_NAME+" WHERE ID = ?",new String[]{id});
        return cursor;
    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }

}

package com.example.datasql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "PC.db", null, 1);
    }

    //2eme constructor pour la creation de base de donnees personalisé

    public DBHelper(Context context,String database) {
        super(context, database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table PCdetails(id integer primary key autoincrement, modele Text, marque Text,prix Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists PCdetails");
    }

    public Boolean insertuserdata(String id,String modele,String marque,String prix){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("modele",modele);
        contentValues.put("marque",marque);
        contentValues.put("prix",prix);
        long result=DB.insert("PCdetails",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean updateuserdata(String id,String modele,String marque,String prix){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("modele",modele);
        contentValues.put("marque",marque);
        contentValues.put("prix",prix);

        Cursor cursor = DB.rawQuery("Select * from PCdetails where id = ?",new String[] {id});
        if (cursor.getCount()>0) {
            long result = DB.update("PCdetails", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean deletedata(String id){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from PCdetails where id = ?",new String[] {id});
        if (cursor.getCount()>0) {
            long result = DB.delete("PCdetails",  "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from PCdetails",null);
        return cursor;
    }

    public Cursor getdata2(String marque){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from PCdetails where marque = ?",new String[] {marque});
        return cursor;
    }
    protected void onDestroy(){
        SQLiteDatabase DB = this.getReadableDatabase();
        DB.close();
        SQLiteDatabase.deleteDatabase(new File("PC.db"));
    }
}

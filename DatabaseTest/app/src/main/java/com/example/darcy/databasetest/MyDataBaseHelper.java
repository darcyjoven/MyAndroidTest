package com.example.darcy.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by darcy on 2017/6/25 0025.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK="create table Book(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price integer," +
            "name text)";
    private Context mContext;
    public MyDataBaseHelper(Context context,String name, SQLiteDatabase.CursorFactory factory,int version ){
        super(context,name,factory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"Create successed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

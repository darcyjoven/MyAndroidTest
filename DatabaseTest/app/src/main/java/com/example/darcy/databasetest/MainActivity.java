package com.example.darcy.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private MyDataBaseHelper dataBaseHelper;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new MyDataBaseHelper(this,"BookStore.db",null,1);
        Button createDatabse = (Button)findViewById(R.id.create_database);
        createDatabse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.getWritableDatabase();
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
                ContentValues values=new ContentValues();

                values.put("name","the da vinci code");
                values.put("author","dan brown");
                values.put("pages","454");
                values.put("price",18.34);
                db.insert("Book",null,values);
                Toast.makeText(MainActivity.this,"sssss",Toast.LENGTH_SHORT).show();

            }
        });
        Button updateData = (Button)findViewById(R.id.add_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price",10.00);
                db.update("Book",values, "name=?",new String[]{"the da vinci code"});
                Toast.makeText(MainActivity.this,"ddddd",Toast.LENGTH_SHORT).show();

            }
        });

        Button deleteData = (Button)findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
                db.delete("Book","page>?",new String[]{"50"});
                Toast.makeText(MainActivity.this,"aaaa",Toast.LENGTH_SHORT).show();

            }
        });

        Button queryData = (Button)findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                Log.d(TAG, "onClick: name: ");
                Log.d(TAG, "onClick: suthor: ");
                Log.d(TAG, "onClick: pages: ");
                if(cursor.moveToFirst()){
                    do{
                        //遍历cursor对象，取出数据并打印
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("aithor"));
                        Double pages=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "onClick: name: "+name);
                        Log.d(TAG, "onClick: suthor: "+author);
                        Log.d(TAG, "onClick: pages: "+pages);
                    }while (cursor.moveToFirst());
                }
                cursor.close();
            }
        });
    }
}

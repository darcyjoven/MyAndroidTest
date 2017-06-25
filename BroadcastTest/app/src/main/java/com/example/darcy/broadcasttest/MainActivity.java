package com.example.darcy.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private LocalReceicer localReceicer;
    private LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent("com.example.darcy.broadcasttest.MY_BROADCAST");
                Intent intent=new Intent("com.example.darcy.broadcasttest.LOCAL_BROADCAST");
               // sendOrderedBroadcast(intent,null);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        /*intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);*/
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.darcy.broadcasttest.LOCAL_BROADCAST");
        localReceicer=new LocalReceicer();
        localBroadcastManager.registerReceiver(localReceicer,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceicer);

    }
    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null && networkInfo.isAvailable()){
                Toast.makeText(context,"有网",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"没网",Toast.LENGTH_SHORT).show();
            }

        }
    }
    class LocalReceicer extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"收到本地广播",Toast.LENGTH_SHORT).show();
        }
    }
}



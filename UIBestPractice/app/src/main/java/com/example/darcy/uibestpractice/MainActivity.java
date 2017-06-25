package com.example.darcy.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;
    private Button btnsend;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initText();
        inputText=(EditText)findViewById(R.id.edit_view);
        btnsend=(Button)findViewById(R.id.btnsend);
        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recrcler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);

        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);

                    msgRecyclerView.scrollToPosition(msgList.size()-1);

                    inputText.setText("");
                }
            }
        });
    }
    public void initText(){
        for(int i=0;i<12;i++){
            Msg msg;

            if(i%2==0){
                msg=new Msg("sdasda",Msg.TYPE_RECEIVED);
            }else {
                msg=new Msg("sasfasf",Msg.TYPE_SENT);
            }
            msgList.add(msg);
        }
    }
}

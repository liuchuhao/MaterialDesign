package cn.cq1s.materialtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private List<Msg>msgList=new ArrayList<>();
    private EditText inputText;

    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        String data=getIntent().getStringExtra("param");
        name=data;
        setTitle(data);
        initMsgs();
        inputText=(EditText)findViewById(R.id.input_text);
        Button send=(Button)findViewById(R.id.send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=inputText.getText().toString();
                if (!"".equals(content)){
                    Msg msg=new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);//renew,insert
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });
    }
    private void initMsgs() {
        Msg msg1=new Msg("你好，朋友。", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("你好，请问你是？", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("我是"+name+"。很高兴认识你！", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
    public static void actionStart(Context context, String data){
        Intent intent=new Intent(context,ChatActivity.class);
        intent.putExtra("param",data);
        context.startActivity(intent);
    }//使用频繁的方法用静态
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit:
               finish();
                break;
            default:
        }
        return true;
    }
}

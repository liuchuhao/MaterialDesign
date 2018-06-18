package cn.cq1s.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class FruitActivity extends AppCompatActivity {
public static final String FRUIT_NAME="fruit_name";
public static final String FRUIT_IMAGE_ID="fruit_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent=getIntent();
        final String fruitName=intent.getStringExtra(FRUIT_NAME);
        String fruitImaged=intent.getStringExtra(FRUIT_IMAGE_ID);
        Toolbar toolbar=findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView=findViewById(R.id.fruit_image_view);
        TextView fruitContentText=findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load("http://q.qlogo.cn/headimg_dl?dst_uin="+fruitImaged+"&spec=640&img_type=jpg").into(fruitImageView);
        String fruitContent=generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);
        FloatingActionButton chatInit=findViewById(R.id.chat_init);
        chatInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatActivity.actionStart(FruitActivity.this,fruitName);
            }
        });
    }
    private String generateFruitContent(String fruitName){
        StringBuilder fruitContent=new StringBuilder();
        if (fruitName.equals("刘楚豪")){
         fruitContent.append("刘楚豪\nQQ：8843326\n年龄；20\n电话号码：18875004090\n\n520遍名字：\n");
        }
        if (fruitName.equals("肖杨川")){
            fruitContent.append("肖杨川\nQQ：1033599459\n年龄；19\n电话号码：17723510016\n\n520遍名字：\n");
        }
        for (int i=0;i<500;i++){
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

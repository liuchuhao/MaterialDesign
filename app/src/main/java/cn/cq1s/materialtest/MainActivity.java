package cn.cq1s.materialtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.cq1s.materialtest.db.PersonData;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<PersonData> personDataList;
    private Fruit[]fruits={new Fruit("王进","798590514"),
            new Fruit("孙静一","1149414439"),
            new Fruit("潘博粲","1067506558"),
            new Fruit("喻家艺","2889000469"),
            new Fruit("罗佳","1729033362"),
            new Fruit("王月","965493008"),
            new Fruit("万渝","861513622"),
            new Fruit("廖雨欣","2528176719"),
            new Fruit("唐言","1044487307"),
            new Fruit("卢文靓","1053473137"),
            new Fruit("李海艳","1416783717"),
            new Fruit("杜佳玥","596536768"),
            new Fruit("郭光辉","1364865260"),
            new Fruit("洪银","2360818977"),
            new Fruit("李官鑫","2369407891"),
            new Fruit("谭晓月","940263964"),
            new Fruit("陈捷","892742450"),
            new Fruit("陈昱如","1298112932"),
            new Fruit("陈秋秀","1244893956"),
            new Fruit("代圆媛","3467697519"),
            new Fruit("张粤瑜","1244026353"),
            new Fruit("文琳竣","1274829263"),
            new Fruit("李林林","1162277949"),
            new Fruit("孙锐","841838705"),
            new Fruit("冉幕洋","1358688479"),
            new Fruit("肖杨川","1033599459"),
            new Fruit("高艺丹","1483289018"),
            new Fruit("楚纽娟","1601107330"),
            new Fruit("刘楚豪","8843326"),
            new Fruit("张又方","3061357221"),
            new Fruit("曾熙评","791605303"),
            new Fruit("邱睿","286856321"),
            new Fruit("李楠","438386288"),
            new Fruit("陈梓露","741948067"),
            new Fruit("夏婷","1075396703"),
            new Fruit("秦凤杰","1241537386"),
            new Fruit("范箐","3464497520"),
            new Fruit("周灵","1183127920"),
            new Fruit("黄汗","1303898345"),
            new Fruit("夏子寒","1052720689"),
            new Fruit("叶楠","1045095087"),
            new Fruit("蒲炳地","1582620146"),
            new Fruit("张寒梅","544505776"),
            new Fruit("王力彬","1714158604"),
            new Fruit("田益曼","1977285524"),
            new Fruit("侯福鸿","1406457356"),
            new Fruit("李中林","3501719680"),
            new Fruit("李昕","935416785"),
            new Fruit("秦晴","2782605143"),
            new Fruit("李晓凤","1028504914"),
            new Fruit("张德林","1952086845"),
            new Fruit("李萍萍","1982612959"),
            new Fruit("卿学燕","957969310"),
            new Fruit("颜如玉","3142038477"),
            new Fruit("金怡","1541798964"),
            new Fruit("张力丹","619661807"),
            new Fruit("黄婷婷","564572908")};
    private List<Fruit>fruitList=new ArrayList<>();
    private FruitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                            }
                        }).show();

            }
        });
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
initFruits();
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout=findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }
    private void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initFruits();
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });

    }}).start();
    }
private void initFruits(){
        personDataList= DataSupport.findAll(PersonData.class);
            if (personDataList.size()>0){
                fruitList.clear();
                for (PersonData personData:personDataList){
                    Fruit[] addFruits={new Fruit(personData.getPersonName(),personData.getPersonCode())};
                    fruitList.add(addFruits[0]);
                }
            }else {
              firstAdd();
            }
}
private void firstAdd(){
        for(Fruit fruit:fruits){
            PersonData personData=new PersonData();
            personData.setPersonName(fruit.getName());
            personData.setPersonCode(fruit.getImageId());
            personData.save();
        }
    initFruits();
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked Setting",Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return true;

    }
}

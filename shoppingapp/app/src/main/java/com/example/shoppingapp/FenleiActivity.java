package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FenleiActivity extends AppCompatActivity {
    private Toolbar toolbar1;
    Intent intent_zhuye, intent_gouwuche,intent_geren,intent_tool;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);
        ImageView zhuye=findViewById(R.id.zhuye);
//        ImageView fenlei=findViewById(R.id.fenlei);
        ImageView gouwuche=findViewById(R.id.gouwuche);
        ImageView geren=findViewById(R.id.geren);
        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quanju f=new quanju();
        if(f.getFlag()==-1) {
            zhuye.setOnClickListener(new MyImage_true());
//        fenlei.setOnClickListener(new MyImage());
            gouwuche.setOnClickListener(new MyImage_true());
            geren.setOnClickListener(new MyImage_true());
        }
        else if (f.getFlag()==1)
        {
            zhuye.setOnClickListener(new MyImage_false());
//        fenlei.setOnClickListener(new MyImage());
            gouwuche.setOnClickListener(new MyImage_false());
            geren.setOnClickListener(new MyImage_false());
        }
        else
        {
            zhuye.setOnClickListener(new MyImage_true());
//        fenlei.setOnClickListener(new MyImage());
            gouwuche.setOnClickListener(new MyImage_true());
            geren.setOnClickListener(new MyImage_true());
        }
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent_tool=new Intent(FenleiActivity.this,MainActivity.class);
                startActivity(intent_tool);
            }
        });
    }
    private class MyImage_true implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.zhuye:
                     intent_zhuye=new Intent(FenleiActivity.this,MainActivity.class);
                    startActivity(intent_zhuye);
                    finish();
                    break;
//                case R.id.fenlei:
//                    Intent intent1=new Intent(UserActivity.this,FenleiActivity.class);
//                    startActivity(intent1);
//                    break;
                case R.id.gouwuche:
                      intent_gouwuche=new Intent(FenleiActivity.this,Gouwuche.class);
                    startActivity(intent_gouwuche);
                    finish();
                    break;
                case R.id.geren:
                     intent_geren=new Intent(FenleiActivity.this,UserActivity.class);
                    startActivity(intent_geren);
                    finish();
                    break;
            }
        }
    }
    private class MyImage_false implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.zhuye:
                    intent_zhuye=new Intent(FenleiActivity.this,MainActivity.class);
                    startActivity(intent_zhuye);
                    finish();
                    break;
//                case R.id.fenlei:
//                    Intent intent1=new Intent(UserActivity.this,FenleiActivity.class);
//                    startActivity(intent1);
//                    break;
                case R.id.gouwuche:
                    intent_gouwuche=new Intent(FenleiActivity.this,Gouwuche.class);
                    startActivity(intent_gouwuche);
                    finish();
                    break;
                case R.id.geren:
                    intent_geren=new Intent(FenleiActivity.this,SuccessfulActivity.class);
                    startActivity(intent_geren);
                    finish();
                    break;
            }
        }
    }
}

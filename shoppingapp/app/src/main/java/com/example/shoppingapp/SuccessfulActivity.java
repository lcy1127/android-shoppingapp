package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessfulActivity extends AppCompatActivity {
    private Toolbar toolbar1;
    private TextView user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccessful);
        toolbar1 = findViewById(R.id.toolbar1);
        user=findViewById(R.id.username);
        ImageView zhuye=findViewById(R.id.zhuye);
        ImageView fenlei=findViewById(R.id.fenlei);
        ImageView gouwuche=findViewById(R.id.gouwuche);
        ImageView dingdan=findViewById(R.id.dingdan);

        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_dingdan=new Intent(SuccessfulActivity.this,Dingdan.class);
                startActivity(intent_dingdan);
                finish();
            }
        });

        zhuye.setOnClickListener(new MyImage());
        fenlei.setOnClickListener(new MyImage());
        gouwuche.setOnClickListener(new MyImage());
//        geren.setOnClickListener(new MainActivity.MyImage());
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btn_adress=findViewById(R.id.btn_address);

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tool=new Intent(SuccessfulActivity.this,MainActivity.class);
                startActivity(intent_tool);
                finish();
            }
        });

        quanju f= new quanju();
        user.setText(f.getName());



        Button btn_back=findViewById(R.id.dislogin);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f.setFlag(-1);
                Intent intent_back=new Intent(SuccessfulActivity.this,UserActivity.class);
                startActivity(intent_back);
                finish();
            }
        });
        btn_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_address=new Intent(SuccessfulActivity.this,address.class);
                startActivity(intent_address);
                finish();
            }
        });

    }
    private class MyImage implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.zhuye:
                    Intent intent_zhuye=new Intent(SuccessfulActivity.this,MainActivity.class);
                    startActivity(intent_zhuye);
                    break;
                case R.id.fenlei:
                    Intent intent_fenlei=new Intent(SuccessfulActivity.this,FenleiActivity.class);
                    startActivity(intent_fenlei);
                    break;
                case R.id.gouwuche:
                    Intent intent_guwuche=new Intent(SuccessfulActivity.this,Gouwuche.class);
                    startActivity(intent_guwuche);
                    break;
//                case R.id.geren:
//                    Intent intent3=new Intent(MainActivity.this,UserActivity.class);
//                    startActivity(intent3);
//                    break;
            }
        }
    }
}

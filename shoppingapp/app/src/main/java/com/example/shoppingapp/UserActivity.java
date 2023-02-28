package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.shoppingapp.User;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    private Toolbar toolbar1;
    private EditText username;
    private EditText userpassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar1 = findViewById(R.id.toolbar1);
        ImageView zhuye=findViewById(R.id.zhuye);
        ImageView fenlei=findViewById(R.id.fenlei);
        ImageView gouwuche=findViewById(R.id.gouwuche);
        quanju f=new quanju();

//        ImageView geren=findViewById(R.id.geren);
        zhuye.setOnClickListener(new MyImage());
        fenlei.setOnClickListener(new MyImage());
        gouwuche.setOnClickListener(new MyImage());

//        geren.setOnClickListener(new MainActivity.MyImage());
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tool=new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent_tool);
                finish();
            }
        });

        Button register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register=new Intent(UserActivity.this,Register.class);
                startActivity(intent_register);
                finish();
            }
        });

        Button login = findViewById(R.id.login);
        username = (EditText) findViewById(R.id.userName);
        userpassword = (EditText) findViewById(R.id.userpassword);
        UserService sv_lg=new UserService(UserActivity.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(username.getText().toString().trim()) || TextUtils.isEmpty(userpassword.getText().toString().trim()))
                {
                   Toast.makeText(UserActivity.this,"登录信息不完备",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (sv_lg.login(username.getText().toString().trim(),userpassword.getText().toString().trim()))
                    {
                        f.setFlag(1);
                        Toast.makeText(UserActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        f.setName(username.getText().toString().trim());
                        Intent intent_login=new Intent(UserActivity.this,SuccessfulActivity.class);
                        startActivity(intent_login);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(UserActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private class MyImage implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.zhuye:
                    Intent intent_zhuye=new Intent(UserActivity.this,MainActivity.class);
                    startActivity(intent_zhuye);
                    finish();
                    break;
                case R.id.fenlei:
                   Intent intent_fenlei=new Intent(UserActivity.this,FenleiActivity.class);
                    startActivity(intent_fenlei);
                    finish();
                    break;
                case R.id.gouwuche:
                   Intent intent_gouwuche=new Intent(UserActivity.this,Gouwuche.class);
                    startActivity(intent_gouwuche);
                    finish();
                    break;
//                case R.id.geren:
//                    Intent intent3=new Intent(MainActivity.this,UserActivity.class);
//                    startActivity(intent3);
//                    break;
            }
        }
    }

}

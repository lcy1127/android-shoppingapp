package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.shoppingapp.User;


/*
注册写入数据库
 */
public class Register extends AppCompatActivity {
    private EditText username;
    private EditText userpassword;
    private Button reday;
    private Button back;
    private Toolbar toolbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reday = findViewById(R.id.reday);
        back = findViewById(R.id.back);
        username = (EditText) findViewById(R.id.userName);
        userpassword =(EditText) findViewById( R.id.userpassword);
        User user=new User();
        quanju f=new quanju();
//        user.setName(username.getText().toString());
//        user.setPassword(userpassword.getText().toString());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(Register.this,UserActivity.class);
                startActivity(intent_back);
                finish();
            }
        });
        UserService rg_sv=new UserService(Register.this);
        reday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setName(username.getText().toString());
                user.setPassword(userpassword.getText().toString());
                if(!TextUtils.isEmpty(username.getText().toString().trim()) && !TextUtils.isEmpty(userpassword.getText().toString().trim())) {

                    if (rg_sv.login(username.getText().toString().trim(), userpassword.getText().toString().trim())) {
                        Toast.makeText(Register.this, "该用户名已被注册,请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        if (rg_sv.register(user)) ;
                        {
                            f.setFlag(1);
                            Toast toast_register = Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT);
                            toast_register.show();
                            f.setName(username.getText().toString().trim());
                            Intent intent_reday = new Intent(Register.this, SuccessfulActivity.class);
                            intent_reday.putExtra("username", username.getText().toString().trim());
                            startActivity(intent_reday);
                            finish();
                        }

                    }
                }
                else {
                    Toast.makeText(Register.this,"注册信息不完备",Toast.LENGTH_SHORT).show();
                }
            }
        });
        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tool=new Intent(Register.this,UserActivity.class);
                startActivity(intent_tool);
            }
        });
    }

}

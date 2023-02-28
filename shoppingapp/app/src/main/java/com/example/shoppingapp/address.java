package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class address extends AppCompatActivity {
    private Toolbar toolbar1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        toolbar1=findViewById(R.id.toolbar1);
        Button btn_save=findViewById(R.id.save);
        EditText edt_consignee=findViewById(R.id.consignee);
        EditText edt_phone=findViewById(R.id.phonenumber);
        EditText edt_address=findViewById(R.id.address);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tool=new Intent(address.this,SuccessfulActivity.class);
                startActivity(intent_tool);
            }
        });
        quanju f=new quanju();
        UserService sg=new UserService(com.example.shoppingapp.address.this);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edt_consignee.getText().toString())&&!TextUtils.isEmpty(edt_phone.getText().toString())&&!TextUtils.isEmpty(edt_address.getText().toString()))
                {
                    if (sg.addaddress(edt_consignee.getText().toString(),edt_phone.getText().toString(),edt_address.getText().toString(),f.getName()))
                    {
                        Toast.makeText(com.example.shoppingapp.address.this,"地址保存成功",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(com.example.shoppingapp.address.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

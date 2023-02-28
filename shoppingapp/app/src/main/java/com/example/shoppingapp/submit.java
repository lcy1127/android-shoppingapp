package com.example.shoppingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class submit extends AppCompatActivity {
    goods good=new goods();
    UserService sg=new UserService(submit.this);
    quanju f=new quanju();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent_get=getIntent();
        TextView tx_imformation=findViewById(R.id.imformation);
        TextView tx_number=findViewById(R.id.number);
        TextView tx_danjia=findViewById(R.id.price);
        TextView tx_hejinumber=findViewById(R.id.heji_number);
        TextView tx_zongjia=findViewById(R.id.heji_price);
        TextView tx_consignee=findViewById(R.id.consignee);
        TextView tx_phonenumber=findViewById(R.id.phonenumber);
        TextView tx_address=findViewById(R.id.tx_address);
        Button btn_submit=findViewById(R.id.submit);
        Button btn_address=findViewById(R.id.changeaddress);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

       int number=intent_get.getIntExtra("shopnum",0);
       double danjia=intent_get.getDoubleExtra("danjia",0);
       double zongjia=intent_get.getDoubleExtra("zongjia",0);
       String imformation=intent_get.getStringExtra("imformation");
       String name=intent_get.getStringExtra("name");
       int imageId=intent_get.getIntExtra("image",0);

       tx_imformation.setText(imformation);
       tx_number.setText(number+"");
       tx_danjia.setText("￥"+danjia);
       tx_zongjia.setText("￥"+zongjia);
       tx_hejinumber.setText(number+"");

      Toolbar toolbar1=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String consignee=sg.getconsignee(f.getName());
        String phonenumber=sg.getphonenumber(f.getName());
        String address=sg.getaddress(f.getName());

        tx_consignee.setText(consignee);
        tx_phonenumber.setText(phonenumber);
        tx_address.setText(address);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog2 = new AlertDialog.Builder(submit.this)
                        .setTitle("提示")
                        .setMessage("确认退出?")
                        .setIcon(R.mipmap.taobao)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent_back=new Intent(submit.this,MainActivity.class);
                                startActivity(intent_back);
                                finish();
                            }
                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create();
                alertDialog2.show();
            }
        });
        good.setNumber(number);
        good.setName(name);
        good.setPrice(zongjia);
        good.setImageId(imageId);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sg.buy(good,f.getName(),danjia,simpleDateFormat.format(date)))
                {
                    Toast.makeText(submit.this,"购买成功",Toast.LENGTH_SHORT).show();
                    Intent intent_buy=new Intent(submit.this,MainActivity.class);
                    startActivity(intent_buy);
                    finish();
                }
            }
        });
        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_address=new Intent(submit.this,address.class);
                startActivity(intent_address);
                finish();
            }
        });


    }
}

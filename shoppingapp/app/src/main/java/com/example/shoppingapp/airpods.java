package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class airpods extends AppCompatActivity {
    private Toolbar toolbar1;
    private Intent intent_tool;
    int number=1;
    double price=1999.00;
    String name="airpodspro";
    goods good=new goods();
    quanju f=new quanju();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airpods);
        toolbar1=findViewById(R.id.toolbar1);
        ImageButton btn_delete=findViewById(R.id.butn_minus);
        ImageButton btn_add=findViewById(R.id.butn_add);
        Button btn_addcar=findViewById(R.id.btn_addshopcar);
        Button btn_shop=findViewById(R.id.btn_shop);
        TextView text_price=findViewById(R.id.tx_price);
        TextView text_number=findViewById(R.id.number);
        TextView tx1=findViewById(R.id.tx1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_tool=new Intent(airpods.this,MainActivity.class);
                startActivity(intent_tool);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                price=1999*number;
                text_number.setText(number+"");
                text_price.setText(price+"");
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number!=1)
                {
                    number--;
                    price=price-1999;
                }
                if (number==1)
                {
                    price=1999.00;
                }
                text_number.setText(number+"");
                text_price.setText(price+"");
            }
        });
        good.setNumber(number);
        good.setName(name);
        good.setPrice(price);
        good.setImageId(R.drawable.airpods);
        UserService sg=new UserService(airpods.this);
        double danjia=1999.00;
        btn_addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f.getFlag()!=1)
                {
                    Toast.makeText(airpods.this,"请先登录",Toast.LENGTH_SHORT).show();
                    Intent intent_orange=new Intent(airpods.this,UserActivity.class);
                    startActivity(intent_orange);
                    finish();
                }
                else {
                    if(sg.chaxun(good,f.getName()))
                    {
                        sg.update(number,price,f.getName(),name);
                        Toast.makeText(airpods.this,"成功加入购物车",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        if (sg.addcar(good,f.getName(),danjia))
                        {
                            Toast.makeText(airpods.this,"成功加入购物车",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f.getFlag()!=1)
                {
                    Toast.makeText(airpods.this,"请先登录",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(orange.this,good.getName()+good.getPrice()+good.getNumber(),Toast.LENGTH_SHORT).show();
                    Intent intent_orange=new Intent(airpods.this,UserActivity.class);
                    startActivity(intent_orange);
                    finish();
                }
                else
                {
                    Intent intent_shop=new Intent(airpods.this,submit.class);
                    intent_shop.putExtra("danjia",danjia);
                    intent_shop.putExtra("shopnum",number);
                    intent_shop.putExtra("imformation",tx1.getText().toString());
                    intent_shop.putExtra("zongjia",price);
                    intent_shop.putExtra("name",name);
                    intent_shop.putExtra("image",good.getImageId());
                    startActivity(intent_shop);
                    finish();
                }

            }
        });
    }
}

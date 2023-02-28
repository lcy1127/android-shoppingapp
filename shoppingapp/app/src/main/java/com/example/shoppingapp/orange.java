package com.example.shoppingapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class orange extends AppCompatActivity {
    private Toolbar toolbar1;
    private Intent intent_tool;
    String name="江西橘子";
    int number=1;
    double price=99.00;
    quanju f=new quanju();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orange);
        toolbar1=findViewById(R.id.toolbar1);
        ImageButton btn_delete=findViewById(R.id.butn_minus);
        TextView text_price=findViewById(R.id.tx_price);
        TextView tx1=findViewById(R.id.tx1);
        ImageButton btn_add=findViewById(R.id.butn_add);
        Button btn_shop=findViewById(R.id.btn_shop);
        Button btn_addshopcar=findViewById(R.id.btn_addshopcar);
        TextView text_number=findViewById(R.id.number);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        goods good=new goods();
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_tool=new Intent(orange.this,MainActivity.class);
                startActivity(intent_tool);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                price=99*number;
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
                    price=price-99;
                }
                if (number==1)
                {
                    price=99.00;
                }
                text_number.setText(number+"");
                text_price.setText(price+"");
            }
        });
//        btn_shop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        good.setName(name);
        good.setPrice(price);
        good.setNumber(number);
        good.setImageId(R.drawable.orange);
        UserService sg=new UserService(orange.this);
        double danjia=99.00;
        btn_addshopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f.getFlag()!=1)
                {
                    Toast.makeText(orange.this,"请先登录",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(orange.this,good.getName()+good.getPrice()+good.getNumber(),Toast.LENGTH_SHORT).show();
                    Intent intent_orange=new Intent(orange.this,UserActivity.class);
                    startActivity(intent_orange);
                    finish();
                }
                else {
                    if(sg.chaxun(good,f.getName()))
                    {
                        sg.update(number,price,f.getName(),name);
                        Toast.makeText(orange.this,"成功加入购物车",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        if (sg.addcar(good,f.getName(),danjia))
                        {
                            Toast.makeText(orange.this,"成功加入购物车",Toast.LENGTH_SHORT).show();
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
                 Toast.makeText(orange.this,"请先登录",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(orange.this,good.getName()+good.getPrice()+good.getNumber(),Toast.LENGTH_SHORT).show();
                 Intent intent_orange=new Intent(orange.this,UserActivity.class);
                 startActivity(intent_orange);
                 finish();
             }
             else
             {
                 Intent intent_shop=new Intent(orange.this,submit.class);
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

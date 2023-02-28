package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class Dingdan extends AppCompatActivity {
    private List<SP> SPlist;
    UserService sg=new UserService(Dingdan.this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        ListView listView=findViewById(R.id.List_gouwuche);
        Toolbar toolbar1=findViewById(R.id.toolbar1);
        TextView n=findViewById(R.id.dingdannum);
        quanju f = new quanju();
        if (f.getFlag() == -1) {
            Toast.makeText(Dingdan.this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent_login = new Intent(Dingdan.this, UserActivity.class);
            startActivity(intent_login);
            finish();
        } else if (f.getFlag() == 1) {
            SPlist = sg.alllist_t(f.getName());
            listView.setAdapter(new MyAdapter());
            setSupportActionBar(toolbar1);

            getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_tool = new Intent(Dingdan.this, SuccessfulActivity.class);
                    startActivity(intent_tool);
                    finish();
                }
            });
            n.setText("("+sg.allCaseNum_d()+")");
        }
            else{
                Toast.makeText(Dingdan.this, "请先登录", Toast.LENGTH_SHORT).show();
                Intent intent_login = new Intent(Dingdan.this, UserActivity.class);
                startActivity(intent_login);
                finish();
            }
        }
    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return SPlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;

            if (convertView == null){

                //布局填充器对象，用于把xml对象转化为view对象
                LayoutInflater inflater = Dingdan.this.getLayoutInflater();

                view = inflater.inflate(R.layout.activity_item3,null);
            }else {

                view = convertView;
            }

            //给view中的年龄和姓名赋值
            TextView tvName = (TextView) view.findViewById(R.id.imformation);//这里需调用view中的findViewById，不然会在R.layout.activity_main中寻找
            TextView tvNumber = view.findViewById(R.id.number);
            TextView tvPrice=view.findViewById(R.id.allprice);
            TextView tvTime=view.findViewById(R.id.time);
            ImageView tvImage=view.findViewById(R.id.imageview);
//            Button tvbtndelete=view.findViewById(R.id.btn_delete);
//            Button tvbtnadd=view.findViewById(R.id.btn_add);
//                TextView tvcheck=view.findViewById(R.id.CB);

            SP sp=SPlist.get(position);

            tvName.setText(sp.getName());
            tvNumber.setText("x"+sp.getNumber());
            tvPrice.setText("￥"+sp.getPrice()+"");
            tvImage.setImageResource(sp.getImageId());
            tvTime.setText(sp.getTime());



            return view;


        }
    }
    }


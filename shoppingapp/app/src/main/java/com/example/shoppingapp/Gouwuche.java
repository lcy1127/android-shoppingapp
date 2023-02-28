package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class Gouwuche extends AppCompatActivity {
    private Toolbar toolbar1;
    private List<Shangpin> shangpinList;
    private MyAdapter mAdapter;


    UserService sg = new UserService(Gouwuche.this);
    Intent intent_zhuye, intent_fenlei, intent_geren, intent_tool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gouwuche);
        toolbar1 = findViewById(R.id.toolbar1);
        ImageView zhuye = findViewById(R.id.zhuye);
        ImageView fenlei = findViewById(R.id.fenlei);
        TextView n = findViewById(R.id.goodnumber);
        TextView allprice = findViewById(R.id.allprice);
        ListView listView = findViewById(R.id.List_gouwuche);
        mAdapter = new MyAdapter();
//        ImageView gouwuche=findViewById(R.id.gouwuche);
        ImageView geren = findViewById(R.id.geren);
        quanju f = new quanju();
        double ALLp=0;
        if (f.getFlag() == -1) {
            Toast.makeText(Gouwuche.this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent_login = new Intent(Gouwuche.this, UserActivity.class);
            startActivity(intent_login);
            finish();
        } else if (f.getFlag() == 1) {
            zhuye.setOnClickListener(new MyImage_false());
            fenlei.setOnClickListener(new MyImage_false());
//        gouwuche.setOnClickListener(new MyImage());
            geren.setOnClickListener(new MyImage_false());
            shangpinList = sg.alllist(f.getName());
            listView.setAdapter(mAdapter);
            for (int i=0;i<shangpinList.size();i++)
            {
                if (shangpinList.get(i).getCheck())
                {
                   ALLp= sg.getp(f.getName(),shangpinList.get(i).getName())+ALLp;
                }
            }
            allprice.setText("￥"+ALLp);
            setSupportActionBar(toolbar1);

            getSupportActionBar().setHomeButtonEnabled(true); //设置导航栏可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent_tool = new Intent(Gouwuche.this, MainActivity.class);
                    startActivity(intent_tool);
                }
            });
            n.setText("(" + sg.allCaseNum() + ")");


        } else {
            Toast.makeText(Gouwuche.this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent_login = new Intent(Gouwuche.this, UserActivity.class);
            startActivity(intent_login);
            finish();
        }


    }


    private class MyImage_false implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.zhuye:
                    intent_zhuye = new Intent(Gouwuche.this, MainActivity.class);
                    startActivity(intent_zhuye);
                    finish();
                    break;
                case R.id.fenlei:
                    intent_fenlei = new Intent(Gouwuche.this, FenleiActivity.class);
                    startActivity(intent_fenlei);
                    finish();
                    break;
//                case R.id.gouwuche:
//                    Intent intent2=new Intent(UserActivity.this,Gouwuche.class);
//                    startActivity(intent2);
//                    break;
                case R.id.geren:
                    intent_geren = new Intent(Gouwuche.this, SuccessfulActivity.class);
                    startActivity(intent_geren);
                    finish();
                    break;
            }
        }
    }

    private class MyAdapter extends BaseAdapter {
        public boolean flage = false;

        @Override
        public int getCount() {
            return shangpinList.size();
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

            if (convertView == null) {

                //布局填充器对象，用于把xml对象转化为view对象
                LayoutInflater inflater = Gouwuche.this.getLayoutInflater();

                view = inflater.inflate(R.layout.activity_item2, null);
            } else {

                view = convertView;
            }

            //给view中的年龄和姓名赋值
            TextView tvName = (TextView) view.findViewById(R.id.imformation);//这里需调用view中的findViewById，不然会在R.layout.activity_main中寻找
            TextView tvNumber = view.findViewById(R.id.number);
            TextView tvPrice = view.findViewById(R.id.allprice);
            ImageView tvImage = view.findViewById(R.id.imageview);
//            Button tvbtndelete=view.findViewById(R.id.btn_delete);
//            Button tvbtnadd=view.findViewById(R.id.btn_add);
            CheckBox tvcheck = view.findViewById(R.id.CB);

            Shangpin shangpin = shangpinList.get(position);

            tvName.setText(shangpin.getName());
            tvNumber.setText(shangpin.getNumber() + "");
            tvPrice.setText("￥" + shangpin.getPrice() + "");
            tvImage.setImageResource(shangpin.getImageId());
            tvcheck.setChecked(shangpin.getCheck());

//            if (flage) {
//                tvcheck.setVisibility(View.VISIBLE);
//            } else {
//                tvcheck.setVisibility(View.GONE);
//            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = tvcheck;
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                        shangpinList.get(position).setCheck(false);
                    } else {
                        checkBox.setChecked(true);
                        shangpinList.get(position).setCheck(true);
                    }
                }
            });


            return view;


        }


    }





}

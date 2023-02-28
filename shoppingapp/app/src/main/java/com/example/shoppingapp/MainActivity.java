package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;

import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar1;
    ImageView iv1, iv2;
    private GridView gview;
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;
    private ViewPager viewPager;
    private LinearLayout pointGroup;
    private TextView iamgeDesc;

    private int[] icon={R.drawable.orange,R.drawable.shoes_1,R.drawable.clothe_1,R.drawable.cookie,R.drawable.dish,R.drawable.iphone,R.drawable.books,R.drawable.hat,R.drawable.airpods};
    private String[] iconName={"新鲜好吃的橙子","时尚靓丽女鞋","潮流先锋上衣","好吃不胖曲奇","皇家宫廷餐具","外国尖端手机","考研408全套书籍","jennie同款小熊帽","华强北airpodspro"};
    // 图片资源ID
    private final int[] imageIds = { R.drawable.clothe, R.drawable.tidy, R.drawable.medicine,
            R.drawable.shoes, R.drawable.bag };

    // 图片标题集合
    private final String[] imageDescriptions = { "潮货汇集",
            "超值囤货", "天猫医药", "特卖专场", "潮流名品" };

    private ArrayList<ImageView> imageList;

    /**
     * 上一个页面的位置
     */
    protected int lastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        toolbar1 = findViewById(R.id.toolbar1);
        iv1 = findViewById(R.id.location);
        iv2 = findViewById(R.id.scan);
        quanju f=new quanju();
//        ImageView zhuye=findViewById(R.id.zhuye);
        ImageView fenlei=findViewById(R.id.fenlei);
        ImageView gouwuche=findViewById(R.id.gouwuche);
        ImageView geren=findViewById(R.id.geren);
        ImageView im_dingdan=findViewById(R.id.test);
        setSupportActionBar(toolbar1);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pointGroup = (LinearLayout) findViewById(R.id.point_group);
        iamgeDesc = (TextView) findViewById(R.id.image_desc);
        iamgeDesc.setText(imageDescriptions[0]);
        //跳转界面
//        zhuye.setOnClickListener(new MyImage());
            if (f.getFlag() == -1) {
                fenlei.setOnClickListener(new MyImage_true());
                gouwuche.setOnClickListener(new MyImage_true());
                geren.setOnClickListener(new MyImage_true());
            }
            else if (f.getFlag() == 1) {
                fenlei.setOnClickListener(new MyImage_false());
                gouwuche.setOnClickListener(new MyImage_false());
                geren.setOnClickListener(new MyImage_false());

            }
            else
            {
                fenlei.setOnClickListener(new MyImage_true());
                gouwuche.setOnClickListener(new MyImage_true());
                geren.setOnClickListener(new MyImage_true());
            }
        im_dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (f.getFlag() == 1)
                {Intent intent_dingdan=new Intent(MainActivity.this,Dingdan.class);
                startActivity(intent_dingdan);
                finish();}
                else if(f.getFlag()==-1)
                {
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                    Intent intent_false=new Intent(MainActivity.this,UserActivity.class);
                    startActivity(intent_false);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                    Intent intent_false_1=new Intent(MainActivity.this,UserActivity.class);
                    startActivity(intent_false_1);
                    finish();
                }
            }
        });
        imageList = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {

            // 初始化图片资源
            ImageView image = new ImageView(this);
            image.setBackgroundResource(imageIds[i]);
            imageList.add(image);

            // 添加指示点
            ImageView point = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            params.rightMargin = 20;
            point.setLayoutParams(params);

            point.setBackgroundResource(R.drawable.differentpoint);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointGroup.addView(point);
        }

        viewPager.setAdapter(new MyPagerAdapter());

        // viewPager.setCurrentItem(Integer.MAX_VALUE/2 -
        // (Integer.MAX_VALUE/2%imageList.size())) ;

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            /**
             * 页面切换后调用
             * position 新的页面位置
             */
            public void onPageSelected(int position) {

                position = position % imageList.size();

                // 设置文字描述内容
                iamgeDesc.setText(imageDescriptions[position]);

                // 改变指示点的状态
                // 把当前点enbale 为true
                pointGroup.getChildAt(position).setEnabled(true);
                // 把上一个点设为false
                pointGroup.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;

            }

            @Override
            /**
             * 页面正在滑动的时候，回调
             */
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            /**
             * 当页面状态发生变化的时候，回调
             */
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*
         * 自动循环： 1、定时器：Timer 2、开子线程 while true 循环 3、ColckManager 4、 用handler
         * 发送延时信息，实现循环
         */
        isRunning = true;
        // 设置图片的自动滑动
        handler.sendEmptyMessageDelayed(0, 3000);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(MainActivity.this, "当前定位", Toast.LENGTH_SHORT).show();

            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(MainActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();

            }
        });

gview=(GridView) findViewById(R.id.grid_view);
data_list =new ArrayList<Map<String,Object>>();
getData_list();
String[] from={"image","text"};
int[] to={R.id.image1,R.id.itemtext1};
simpleAdapter=new SimpleAdapter(this,data_list,R.layout.activity_item1,from,to);
gview.setAdapter(simpleAdapter);

gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 0:
                Intent intent_orange=new Intent(MainActivity.this,orange.class);
                startActivity(intent_orange);
                finish();
                break;
            case 1:
                Intent intent_shoes=new Intent(MainActivity.this,shoes.class);
                startActivity(intent_shoes);
                finish();
                break;
            case 2:
                Intent intent_clothe=new Intent(MainActivity.this,clothe.class);
                startActivity(intent_clothe);
                finish();
                break;
            case 3:
                Intent intent_cookie=new Intent(MainActivity.this,cookie.class);
                startActivity(intent_cookie);
                finish();
                break;
            case 4:
                Intent intent_dish=new Intent(MainActivity.this,dish.class);
                startActivity(intent_dish);
                finish();
                break;
            case 5:
                Intent intent_phone=new Intent(MainActivity.this,iphone.class);
                startActivity(intent_phone);
                finish();
                break;
            case 6:
                Intent intent_books=new Intent(MainActivity.this,books.class);
                startActivity(intent_books);
                finish();
                break;
            case 7:
                Intent intent_hat=new Intent(MainActivity.this,hat.class);
                startActivity(intent_hat);
                finish();
                break;
            case 8:
                Intent intent_airpods=new Intent(MainActivity.this,airpods.class);
                startActivity(intent_airpods);
                finish();
                break;
        }
    }
});

    }
    private class MyImage_true implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fenlei:
                   Intent intent_fenlei =new Intent(MainActivity.this,FenleiActivity.class);
                     startActivity(intent_fenlei);
                    break;
                case R.id.gouwuche:
                    Intent intent_gouwuche=new Intent(MainActivity.this,Gouwuche.class);
                    startActivity(intent_gouwuche);
                    break;
                case R.id.geren:
                    Intent intent_geren=new Intent(MainActivity.this,UserActivity.class);
                    startActivity(intent_geren);
                    break;
            }
        }
    }

    private class MyImage_false implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fenlei:
                    Intent intent_fenlei =new Intent(MainActivity.this,FenleiActivity.class);
                    startActivity(intent_fenlei);
                    break;
                case R.id.gouwuche:
                    Intent intent_gouwuche=new Intent(MainActivity.this,Gouwuche.class);
                    startActivity(intent_gouwuche);
                    break;
                case R.id.geren:
                    Intent intent_geren=new Intent(MainActivity.this,SuccessfulActivity.class);
                    startActivity(intent_geren);
                    break;
            }
        }


    }



    /**
     * 判断是否自动滚动
     */
    private boolean isRunning = false;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            // 让viewPager 滑动到下一页
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        };
    };

    protected void onDestroy() {

        super.onDestroy();
        isRunning = false;
    };

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        /**
         * 获得页面的总数
         */
        public int getCount() {
            return Integer.MAX_VALUE; // 使得图片可以循环
        }

        @Override
        /**
         * 获得相应位置上的view
         * container view的容器，其实就是viewpager自身
         * position 相应的位置
         */
        public Object instantiateItem(ViewGroup container, int position) {

            // System.out.println("instantiateItem ::" + position);

            // 给 container 添加一个view
            container.addView(imageList.get(position % imageList.size()));
            // 返回一个和该view相对的object
            return imageList.get(position % imageList.size());
        }

        @Override
        /**
         * 判断 view和object的对应关系
         */
        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        /**
         * 销毁对应位置上的object
         */
        public void destroyItem(ViewGroup container, int position, Object object) {
            // System.out.println("destroyItem ::" + position);

            container.removeView((View) object);
            object = null;
        }
    }

    public List<Map<String, Object>> getData_list() {
        for (int i=0;i<icon.length;i++)
        {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }
}






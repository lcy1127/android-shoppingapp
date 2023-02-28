package com.example.shoppingapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private DatabaseHelper dbHelper;

    public UserService(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    //不固定加密方式
    public byte[] encrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (bytes[i] ^ key);
            key = bytes[i];
        }
        return bytes;
    }

    //解密实现
    public byte[] decrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = len - 1; i > 0; i--) {
            bytes[i] = (byte) (bytes[i] ^ bytes[i - 1]);
        }
        bytes[0] = (byte) (bytes[0] ^ key);
        return bytes;
    }

    public boolean login(String username, String password) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select password from user where username=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst() == true) {
            byte[] bytes = cursor.getBlob(0);
            String result = new String(decrypt(bytes));
            if (password.equals(result)) {
                return true;
            }
        }
        return false;

    }

    //注册用
    public boolean register(User user) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into user(username,password) values(?,?)";
        byte[] bytes = encrypt(user.getPassword().getBytes());//加密
        Object obj[] = {user.getName(), bytes};
        sdb.execSQL(sql, obj);
        return true;
    }

    public boolean addcar(goods good, String name, double price) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into shop_table(username,goodname,number,price,danjia,image) values(?,?,?,?,?,?)";
        Object obj[] = {name, good.getName(), good.getNumber(), good.getPrice(), price,good.getImageId()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public boolean update(int n, double price, String name, String GoodName) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        sdb.execSQL("update shop_table set number= number+?,price=price+? where username = ? and goodname = ?", new Object[]{n, price, name, GoodName});
        return true;
    }

    public boolean chaxun(goods good, String name) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from shop_table where goodname=? and username=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{good.getName(), name});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean addaddress(String consignee, String number, String address, String name) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        byte[] byte1 = encrypt(consignee.getBytes());//加密
        byte[] byte2 = encrypt(number.getBytes());//加密
        byte[] byte3 = encrypt(address.getBytes());//加密
        sdb.execSQL("update user set consignee=?,phonenumber=?,address=? where username = ?", new Object[]{byte1, byte2, byte3, name});
        return true;
    }

    public boolean buy(goods good, String name, double danjia, String time) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into order_table(username,goodname,number,danjia,price,time,image) values(?,?,?,?,?,?,?)";
        Object obj[] = {name, good.getName(), good.getNumber(), danjia, good.getPrice(), time,good.getImageId()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public String getconsignee(String name) {
        String result = "";
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select consignee from user where username=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{name});
        if (cursor.moveToFirst()) {
            byte[] bytes = cursor.getBlob(0);
            result = new String(decrypt(bytes));
            return result;
        }
        cursor.close();
        return result;
    }

    public String getphonenumber(String name) {
        String result = "";
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select phonenumber from user where username=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{name});
        if (cursor.moveToFirst()) {
            byte[] bytes = cursor.getBlob(0);
            result = new String(decrypt(bytes));
            return result;
        }
        cursor.close();
        return result;
    }

    public String getaddress(String name) {
        String result = "";
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select address from user where username=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{name});
        if (cursor.moveToFirst()) {
            byte[] bytes = cursor.getBlob(0);
            result = new String(decrypt(bytes));
            return result;
        }
        cursor.close();
        return result;
    }
    public List<Shangpin> alllist(String name) {
        String goodname;
        int number;
        double price;
        int image;
        List<Shangpin> list = new ArrayList<>();
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select goodname,number,price,image from shop_table where username=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{name});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                goodname = cursor.getString(0);
                number = cursor.getInt(1);
                price = cursor.getDouble(2);
                image = cursor.getInt(3);

                list.add(new Shangpin(image, goodname, price, number,true));

            }
            return list;
        }
            cursor.close();
            return list;
        }
    public long allCaseNum( ){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql = "select count(*) from shop_table";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }
    public List<SP> alllist_t(String name) {
        String goodname;
        int number;
        double price;
        int image;
        String time;
        List<SP> list = new ArrayList<>();
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select goodname,number,price,time,image from order_table where username=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{name});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                goodname = cursor.getString(0);
                number = cursor.getInt(1);
                price = cursor.getDouble(2);
                time=cursor.getString(3);
                image = cursor.getInt(4);

                list.add(new SP(image,number, price, goodname,time));

            }
            return list;
        }
        cursor.close();
        return list;
    }
    public long allCaseNum_d( ){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql = "select count(*) from order_table";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }
    public double getp(String name,String goodname)
    {
        double result=0;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select price from shop_table where username=? and goodname=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{name,goodname});
        if (cursor.moveToFirst()) {
           result=cursor.getDouble(0);
           return result;
        }
        cursor.close();
        return result;
    }

    public boolean find(String goodname) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select goodname from good_table where goodname=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{goodname});
        if (cursor.moveToFirst() == true) {
            String result =cursor.getString(0);
            if (goodname.equals(result)) {
                return true;
            }
        }
        return false;

    }
}
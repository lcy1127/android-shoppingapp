package com.example.shoppingapp;

public class Shangpin {
    private int imageId;
    private String name;
    private double price;
    private int number;
    private boolean check;

    public Shangpin(int imageId, String name, double price, int number,boolean check)
    {
        this.imageId=imageId;
        this.name=name;
        this.price=price;
        this.number=number;
        this.check=check;
//        this.check=check;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
        @Override
    public String toString() {
        return "Shangpin{" + "imageId="+imageId + ", name='" + name + ", number=" + number +", price="+price+ '}';
    }


}

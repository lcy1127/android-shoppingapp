package com.example.shoppingapp;

public class SP {
    private int imageId;
    private String name;
    private double price;
    private int number;
    private String time;

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SP(int imageId, int number, double price, String name, String time)
    {
        this.imageId=imageId;
        this.name=name;
        this.price=price;
        this.number=number;
        this.time=time;
    }
}

package com.example.fwd;

import android.location.Address;

public class Donator {

    private String Name;
    private String Phonenumber;
    private String Foodtype;
    private String Foodexpriy;
    private String Foodcount;
    private String Fromtime;
    private String Totime;
    private String Address;
    private String Status;
    private String Key;


    public Donator( String name, String phonenumber, String foodtype, String foodexpriy, String foodcount, String fromtime, String totime, String address, String status, String key) {

        Name = name;
        Phonenumber = phonenumber;
        Foodtype = foodtype;
        Foodexpriy = foodexpriy;
        Foodcount = foodcount;
        Fromtime = fromtime;
        Totime = totime;
        Address = address;
        Status = status;
        Key = key;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getFoodtype() {
        return Foodtype;
    }

    public void setFoodtype(String foodtype) {
        Foodtype = foodtype;
    }

    public String getFoodexpriy() {
        return Foodexpriy;
    }

    public void setFoodexpriy(String foodexpriy) {
        Foodexpriy = foodexpriy;
    }

    public String getFoodcount() {
        return Foodcount;
    }

    public void setFoodcount(String foodcount) {
        Foodcount = foodcount;
    }

    public String getFromtime() {
        return Fromtime;
    }

    public void setFromtime(String fromtime) {
        Fromtime = fromtime;
    }

    public String getTotime() {
        return Totime;
    }

    public void setTotime(String totime) {
        Totime = totime;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }


    @Override
    public String toString() {
        return "Donator{" +
                ", Name='" + Name + '\'' +
                ", Phonenumber='" + Phonenumber + '\'' +
                ", Foodtype='" + Foodtype + '\'' +
                ", Foodexpriy='" + Foodexpriy + '\'' +
                ", Foodcount='" + Foodcount + '\'' +
                ", Fromtime='" + Fromtime + '\'' +
                ", Totime='" + Totime + '\'' +
                ", Address='" + Address + '\'' +
                ", Status ='" + Status + '\'' +
                ", Key ='" + Key + '\'' +
                '}';
    }
}

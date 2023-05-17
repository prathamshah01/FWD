package com.example.fwd;

import android.location.Address;

public class Donator {

    private String Name,Phonenumber,Foodtype,Foodexpriy,Foodcount,Fromtime,Totime,Address;

    public Donator( String name, String phonenumber, String foodtype, String foodexpriy, String foodcount, String fromtime, String totime, String address) {

        Name = name;
        Phonenumber = phonenumber;
        Foodtype = foodtype;
        Foodexpriy = foodexpriy;
        Foodcount = foodcount;
        Fromtime = fromtime;
        Totime = totime;
        Address = address;
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
                '}';
    }
}

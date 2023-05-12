package com.example.fwd;

public class Registration {

    private String key,Name,Pone,Email;

    public Registration(String name, String pone, String email) {

        Name = name;
        Pone = pone;
        Email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPone() {
        return Pone;
    }

    public void setPone(String pone) {
        Pone = pone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    @Override
    public String toString() {
        return "Registration{" +
                "key='" + key + '\'' +
                ", Name='" + Name + '\'' +
                ", Pone='" + Pone + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}

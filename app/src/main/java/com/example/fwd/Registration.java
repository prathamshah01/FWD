package com.example.fwd;

public class Registration {

    private String key,Name,Pone,Email,Password,ConfirmPassword;

    public Registration(String name, String pone, String email, String password, String confirmPassword) {
        Name = name;
        Pone = pone;
        Email = email;
        Password = password;
        ConfirmPassword = confirmPassword;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "key='" + key + '\'' +
                ", Name='" + Name + '\'' +
                ", Pone='" + Pone + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", ConfirmPassword='" + ConfirmPassword + '\'' +
                '}';
    }
}

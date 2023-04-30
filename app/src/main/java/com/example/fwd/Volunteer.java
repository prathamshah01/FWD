package com.example.fwd;

public class Volunteer {

    private String key,volunteerName, volunteerPhone, volunteerAddress, volunteerPickupTime;

    public Volunteer(String volunteerName, String volunteerPhone, String volunteerAddress, String volunteerPickupTime) {
        this.volunteerName = volunteerName;
        this.volunteerPhone = volunteerPhone;
        this.volunteerAddress = volunteerAddress;
        this.volunteerPickupTime = volunteerPickupTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getVolunteerPhone() {
        return volunteerPhone;
    }

    public void setVolunteerPhone(String volunteerPhone) {
        this.volunteerPhone = volunteerPhone;
    }

    public String getVolunteerAddress() {
        return volunteerAddress;
    }

    public void setVolunteerAddress(String volunteerAddress) {
        this.volunteerAddress = volunteerAddress;
    }

    public String getVolunteerPickupTime() {
        return volunteerPickupTime;
    }

    public void setVolunteerPickupTime(String volunteerPickupTime) {
        this.volunteerPickupTime = volunteerPickupTime;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "key='" + key + '\'' +
                ", volunteerName='" + volunteerName + '\'' +
                ", volunteerPhone='" + volunteerPhone + '\'' +
                ", volunteerAddress='" + volunteerAddress + '\'' +
                ", volunteerPickupTime='" + volunteerPickupTime + '\'' +
                '}';
    }
}

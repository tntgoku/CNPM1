package com.example.engineering.Model;

public class Customer {
    private String ID;
    private String Name;
    private String Date;
    private  int Gender;
    private String Address;

    public Customer(){

    }
    public Customer(String iD, String name, String date, int gender, String address) {
        ID = iD;
        Name = name;
        Date = date;
        Gender = gender;
        Address = address;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public String getGender() {
        return (Gender==1)?"Nam":"Nữ";
    }
    public void setGender(int gender) {
        Gender = gender;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }

    
    
}

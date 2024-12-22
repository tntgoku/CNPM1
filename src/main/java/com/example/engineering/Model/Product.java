package com.example.engineering.Model;

public class Product {
    private String IDP;
    private String  nameP;
    private int  IDCate;
    private int Quantity ;
    private int Amount ;
    private int Damount ;
    private String Desp; 
    private String img; 
    private String timer;

    
    public Product() {
    }
    public Product(String iDP){
        this.IDP=iDP;
    }

    public Product(String iDP, String nameP, int iDCate, 
    int quantity, int amount, int damount, String desp, String img,
            String timer) {
        IDP = iDP;
        this.nameP = nameP;
        IDCate = iDCate;
        Quantity = quantity;
        Amount = amount;
        Damount = damount;
        Desp = desp;
        this.img = img;
        this.timer = timer;
    }


    public String getIDP() {
        return IDP;
    }


    public void setIDP(String iDP) {
        IDP = iDP;
    }


    public String getNameP() {
        return nameP;
    }


    public void setNameP(String nameP) {
        this.nameP = nameP;
    }


    public int getIDCate() {
        return IDCate;
    }


    public void setIDCate(int iDCate) {
        IDCate = iDCate;
    }


    public int getQuantity() {
        return Quantity;
    }


    public void setQuantity(int quantity) {
        Quantity = quantity;
    }


    public int getAmount() {
        return Amount;
    }


    public void setAmount(int amount) {
        Amount = amount;
    }


    public int getDamount() {
        return Damount;
    }


    public void setDamount(int damount) {
        Damount = damount;
    }


    public String getDesp() {
        return Desp;
    }


    public void setDesp(String desp) {
        Desp = desp;
    }


    public String getImg() {
        return img;
    }


    public void setImg(String img) {
        this.img = img;
    }


    public String getTimer() {
        return timer;
    }


    public void setTimer(String timer) {
        this.timer = timer;
    }


    @Override
    public String toString() {
        return "Product [IDP=" + IDP + ", nameP=" + nameP + ", IDCate=" + IDCate + ", Quantity=" + Quantity
                + ", Amount=" + Amount + ", Damount=" + Damount + ", Desp=" + Desp + ", img=" + img + ", timer=" + timer
                + "]";
    }



}

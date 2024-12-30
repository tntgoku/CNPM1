package com.example.engineering.Model;

public class oder {
    String ID;
    String IDC,timer;
    int Total;
    int ppm;
    String process;
    String address,phone,name;

    
    public oder(String iD, String iDC, String timer, int total, int ppm, String process, String address, String phone,
            String name) {
        ID = iD;
        IDC = iDC;
        this.timer = timer;
        Total = total;
        this.ppm = ppm;
        this.process = process;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getIDC() {
        return IDC;
    }
    public void setIDC(String iDC) {
        IDC = iDC;
    }
    public String getTimer() {
        return timer;
    }
    public void setTimer(String timer) {
        this.timer = timer;
    }
    public int getTotal() {
        return Total;
    }
    public void setTotal(int total) {
        Total = total;
    }
    public int getPpm() {
        return ppm;
    }
    public void setPpm(int ppm) {
        this.ppm = ppm;
    }
    public String getProcess() {
        return process;
    }
    public void setProcess(String process) {
        this.process = process;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "oder [ID=" + ID + ", IDC=" + IDC + ", timer=" + timer + ", Total=" + Total + ", ppm=" + ppm
                + ", process=" + process + ", address=" + address + ", phone=" + phone + ", name=" + name + "]";
    }

    
}

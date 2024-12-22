package com.example.engineering.Model;

public class Account {
    private String IDA	;
    private String IDuser;
    private String username;
    private String password;
    private String email;
    private int role;
    public Account() {
    }
    public Account(String iDA, String iDuser, String username, String password, String email, int role) {
        IDA = iDA;
        IDuser = iDuser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    public String getIDA() {
        return IDA;
    }
    public void setIDA(String iDA) {
        IDA = iDA;
    }
    public String getIDuser() {
        return IDuser;
    }
    public void setIDuser(String iDuser) {
        IDuser = iDuser;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "Account [IDA=" + IDA + ", IDuser=" + IDuser + ", username=" + username + ", password=" + password
                + ", email=" + email + ", role=" + role + "]";
    }
    
           
}

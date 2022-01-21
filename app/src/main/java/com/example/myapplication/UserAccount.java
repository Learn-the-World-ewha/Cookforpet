package com.example.myapplication;

public class UserAccount {
    private String emailid;
    private String pwd;
    private String idToken;//고유 토큰 정보

    public UserAccount(){}

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String id) { this.emailid = emailid; }

    public String getPwd() { return pwd; }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

}

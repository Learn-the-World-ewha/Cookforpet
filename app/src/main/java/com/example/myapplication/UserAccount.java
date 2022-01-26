package com.example.myapplication;

public class UserAccount {
    private String emailid;
    private String pwd;
    private String idToken;//고유 토큰 정보
    private String pettype;
    private String effect;

    public UserAccount(){}

    public String getEmailid(String email) {
        return emailid;
    }

    public void setEmailid(String emailid) { this.emailid = emailid; }

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

    public String getPettype() { return pettype; }

    public void setPettype(String pettype) { this.pettype = pettype; }

    public String getEffect() { return effect; }

    public void setEffect(String effect) { this.effect = effect; }

}

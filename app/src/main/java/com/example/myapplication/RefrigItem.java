package com.example.myapplication;

public class RefrigItem {
    String img_url;
    String rcp_txt;
    String date_txt;
    String type_txt, tip_txt;
    String rcp_code;

    public RefrigItem(String img_url, String rcp_txt, String type_txt, String tip_txt, String date_txt, String rcp_code) {
        this.img_url = img_url;
        this.rcp_txt = rcp_txt;
        this.date_txt = date_txt;
        this.type_txt = type_txt;
        this.tip_txt = tip_txt;
        this.rcp_code = rcp_code;
    }

    public String getRcp_code() {
        return rcp_code;
    }

    public void setRcp_code(String rcp_code) {
        this.rcp_code = rcp_code;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRcp_txt() {
        return rcp_txt;
    }

    public void setRcp_txt(String rcp_txt) {
        this.rcp_txt = rcp_txt;
    }

    public String getDate_txt() {
        return date_txt;
    }

    public void setDate_txt(String date_txt) {
        this.date_txt = date_txt;
    }

    public String getType_txt() {
        return type_txt;
    }

    public void setType_txt(String type_txt) {
        this.type_txt = type_txt;
    }

    public String getTip_txt() {
        return tip_txt;
    }

    public void setTip_txt(String tip_txt) {
        this.tip_txt = tip_txt;
    }
}

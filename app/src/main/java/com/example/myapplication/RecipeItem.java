package com.example.myapplication;


public class RecipeItem {
    String img_url;
    String rcp_txt;
    String mat_txt;
    String txt_tip, txt_type, txt_sum, txt_eff, txt_like, txt_time;

    public RecipeItem(String img_url, String rcp_txt, String mat_txt,
                      String sum, String type, String time, String tip, String eff, String like){
        this.img_url = img_url;
        this.rcp_txt = rcp_txt;
        this.mat_txt = mat_txt;
        txt_tip = tip;
        txt_sum = sum;
        txt_type = type;
        txt_time = time;
        txt_eff = eff;
        txt_like = like;
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

    public String getMat_txt() {
        return mat_txt;
    }

    public void setMat_txt(String mat_txt) {
        this.mat_txt = mat_txt;
    }
    public String getTxt_tip() {
        return txt_tip;
    }

    public void setTxt_tip(String txt_tip) {
        this.txt_tip = txt_tip;
    }

    public String getTxt_type() {
        return txt_type;
    }

    public void setTxt_type(String txt_type) {
        this.txt_type = txt_type;
    }

    public String getTxt_sum() {
        return txt_sum;
    }

    public void setTxt_sum(String txt_sum) {
        this.txt_sum = txt_sum;
    }

    public String getTxt_eff() {
        return txt_eff;
    }

    public void setTxt_eff(String txt_eff) {
        this.txt_eff = txt_eff;
    }

    public String getTxt_like() {
        return txt_like;
    }

    public void setTxt_like(String txt_like) {
        this.txt_like = txt_like;
    }

    public String getTxt_time() {
        return txt_time;
    }

    public void setTxt_time(String txt_time) {
        this.txt_time = txt_time;
    }
}

package com.example.myapplication;

import android.graphics.Bitmap;

public class RecipeItem {
    String img_url;
    String rcp_txt;
    String mat_txt;

    public RecipeItem(String img_url, String rcp_txt, String mat_txt){
        this.img_url = img_url;
        this.rcp_txt = rcp_txt;
        this.mat_txt = mat_txt;
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
}

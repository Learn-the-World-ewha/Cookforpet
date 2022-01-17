package com.example.myapplication;

import android.graphics.Bitmap;

public class RecipeItem {
    String img_url;
    String rcp_txt;
    String mat_1st;
    String mat_2nd;
    String mat_3rd;

    public RecipeItem(String img_url, String rcp_txt, String mat_1st, String mat_2nd, String mat_3rd){
        this.img_url = img_url;
        this.rcp_txt = rcp_txt;
        this.mat_1st = mat_1st;
        this.mat_2nd = mat_2nd;
        this.mat_3rd = mat_3rd;
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

    public String getMat_1st() {
        return mat_1st;
    }

    public void setMat_1st(String mat_1st) {
        this.mat_1st = mat_1st;
    }

    public String getMat_2nd() {
        return mat_2nd;
    }

    public void setMat_2nd(String mat_2nd) {
        this.mat_2nd = mat_2nd;
    }

    public String getMat_3rd() {
        return mat_3rd;
    }

    public void setMat_3rd(String mat_3rd) {
        this.mat_3rd = mat_3rd;
    }
}

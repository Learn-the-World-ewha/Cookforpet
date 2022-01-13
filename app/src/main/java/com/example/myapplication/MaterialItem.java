package com.example.myapplication;

public class MaterialItem {
    String mat_name;
    String mat_qtt;

    public MaterialItem(String mat_name, String mat_qtt) {
        this.mat_name = mat_name;
        this.mat_qtt = mat_qtt;
    }

    public String getMat_name() {
        return mat_name;
    }

    public void setMat_name(String mat_name) {
        this.mat_name = mat_name;
    }

    public String getMat_qtt() {
        return mat_qtt;
    }

    public void setMat_qtt(String mat_qtt) {
        this.mat_qtt = mat_qtt;
    }
}

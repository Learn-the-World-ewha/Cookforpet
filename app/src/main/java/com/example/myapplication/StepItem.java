package com.example.myapplication;

public class StepItem {
    String step_num;
    String step_txt;
    String step_img;

    public StepItem(String num, String txt, String img){
        step_num = num;
        step_txt = txt;
        step_img = img;
    }

    public String getStep_num() {
        return step_num;
    }

    public void setStep_num(String step_num) {
        this.step_num = step_num;
    }

    public String getStep_txt() {
        return step_txt;
    }

    public void setStep_txt(String step_txt) {
        this.step_txt = step_txt;
    }

    public String getStep_img() {
        return step_img;
    }

    public void setStep_img(String step_img) {
        this.step_img = step_img;
    }
}

package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StepList_2 extends LinearLayout {

    TextView txt_stepNum;
    TextView txt_step;
    ImageView img_step;
    private Bitmap image;
    private String text1;
    private String text2;

    public StepList_2(Context context) {
        super(context);
        init(context);
    }
    public StepList_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.step_list, this, true);

        img_step = findViewById(R.id.img_step);
        txt_step = findViewById(R.id.txt_step);
        txt_stepNum = findViewById(R.id.txt_stepNum);
    }

    public Bitmap getImg_step() {
        return image;
    }
    public void setImg_step(Bitmap image) {
        this.image = image;
    }
    public String getTxt_step() {
        return text1;
    }
    public String getTxt_stepNum() {
        return text2;
    }

    public void setImg_step(int resId) {
        img_step.setImageResource(resId);
    }
    public void setTxt_step(String step) {
        txt_step.setText(step);
    }
    public void setTxt_stepNum(String num) {
        txt_stepNum.setText(num);
    }
}

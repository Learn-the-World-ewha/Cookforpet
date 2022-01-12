package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class StepList extends LinearLayout {

    TextView txt_stepNum;
    TextView txt_step;
    ImageView img_step;

    public StepList(Context context) {
        super(context);
        init(context);
    }
    public StepList(Context context, AttributeSet attrs) {
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

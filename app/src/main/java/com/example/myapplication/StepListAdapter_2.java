package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StepListAdapter_2 extends BaseAdapter {
    private Context mContext;
    private ArrayList<StepList_2> stepList2s = new ArrayList<StepList_2>();

    public StepListAdapter_2(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return stepList2s.size();
    }

    @Override
    public Object getItem(int i) {
        return stepList2s.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // step_list.xml 레이아웃을 inflate해서 참조획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.step_list, parent, false);
        }

        // step_list.xml 의 참조 획득
        ImageView img_step=(ImageView)convertView.findViewById(R.id.img_step);
        TextView txt_step = (TextView)convertView.findViewById(R.id.txt_step);
        TextView txt_stepNum = (TextView)convertView.findViewById(R.id.txt_stepNum);
        StepList_2 stepList2 = stepList2s.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        img_step.setImageBitmap(stepList2.getImg_step());
        txt_step.setText(stepList2.getTxt_step());
        txt_stepNum.setText(stepList2.getTxt_stepNum());

        return convertView;
    }
    public void addItem(Bitmap image, String text1, String text2){
        StepList_2 stepList2 = new StepList_2(mContext);
        stepList2.setImg_step(image);
        stepList2.setTxt_step(text1);
        stepList2.setTxt_stepNum(text2);

        stepList2s.add(stepList2);
    }
}

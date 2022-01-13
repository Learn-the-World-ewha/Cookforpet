package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MatListAdapter_2 extends BaseAdapter {
    private Context mContext;
    private ArrayList<MatList_2> matList2s = new ArrayList<MatList_2>();
    /*
    private OnDeleteClickListener mListener;
    public interface OnDeleteClickListener{ // 인터페이스 정의
        void onDelete(View v, int pos);
    }
    public ListViewAdapter(Context context, OnDeleteClickListener listener){
        this.mContext = context;
        this.mListener = listener;
    }
    */
    public MatListAdapter_2(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return matList2s.size();
    }

    @Override
    public Object getItem(int i) {
        return matList2s.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // mat_list.xml 레이아웃을 inflate해서 참조획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mat_list, parent, false);
        }

        // mat_list.xml 의 참조 획득
        TextView txt_mat = (TextView)convertView.findViewById(R.id.txt_mat);
        TextView txt_unit = (TextView)convertView.findViewById(R.id.txt_unit);
        MatList_2 matList2 = matList2s.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        txt_mat.setText(matList2.getTxt_mat());
        txt_unit.setText(matList2.getTxt_unit());

        // 리스트 아이템 삭제
        //btn_delete.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        listItems.remove(position);
        //        notifyDataSetChanged();
        //    }
        //});

        return convertView;
    }
    public void addItem(String text1, String text2){
        MatList_2 matList2 = new MatList_2(mContext);

        matList2.setTxt_mat(text1);
        matList2.setTxt_unit(text2);

        matList2s.add(matList2);
    }

    //public void removeItem(int pos){
    //  listItems.remove(pos);
    //notifyDataSetChanged();
    //}

}

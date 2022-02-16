package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RefrigFragment extends Fragment {
    TextView username_txt, cook_sum;
    RecyclerView recycler_rcp;
    RefrigItemAdapter adapter;
    ArrayList<ArrayList<String>> code_date;
    ViewGroup rootView;
    UserActivity activity;
    Context context;
    Integer count;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_refrig, container, false);
        context = container.getContext();

        activity = (UserActivity) getActivity();

        //유저 이름 띄우기
        username_txt=rootView.findViewById(R.id.name_txt);
        username_txt.setText(activity.user_name);

        id = activity.user_code;
        //결과값 갯수 출력
        count = activity.dbAc.getRefResultSum(id);
        cook_sum = rootView.findViewById(R.id.cook_sum);
        cook_sum.setText(count.toString());

        //레시피 목록 출력
        recycler_rcp = rootView.findViewById(R.id.recycler_ref);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recycler_rcp.setLayoutManager(layoutManager);
        adapter = new RefrigItemAdapter();

        code_date= activity.dbAc.getCodeDate(id);
        ArrayList<ArrayList<String>> Refriglist = activity.dbAc.getRefrigList(code_date);
        for(int i=0; i<Refriglist.size(); i++){
            for (int j=0; j<code_date.size(); j++){
                String tmp_date = code_date.get(j).get(1);
                adapter.addItem(new RefrigItem(Refriglist.get(j).get(0), Refriglist.get(j).get(1), Refriglist.get(j).get(2),
                        Refriglist.get(j).get(3), tmp_date));
            }
        }
        recycler_rcp.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new OnRefrigItemClickListener() {
//            @Override
//            public void onItemClick(RefrigItemAdapter.ViewHolder holder, View view, int position) {
//                RefrigItem item = adapter.getItem(position);
////                Intent intent = new Intent(context, RecipeActivity.class);
////                intent.putExtra("recipe_code", recipe_code.get(position));
////                intent.putExtra("recipe_name",item.rcp_txt);
////                intent.putExtra("img_url",item.img_url);
////                intent.putExtra("recipe_sum", item.txt_sum);
////                intent.putExtra("recipe_type", item.txt_type);
////                intent.putExtra("recipe_time", item.txt_time);
////                intent.putExtra("recipe_tip",item.txt_tip);
////                intent.putExtra("recipe_eff",item.txt_eff);
////                intent.putExtra("recipe_like",item.txt_like);
////                startActivity(intent);  // activity 이동
//                Bundle bundle = new Bundle();
//                bundle.putString("img_url",item.img_url);
//                bundle.putString("recipe_name",item.rcp_txt);
//                bundle.putString("recipe_type",item.type_txt);
//                bundle.putString("recipe_tip",item.tip_txt);
//                bundle.putString("recipe_cookdate",item.date_txt);
//
//                FragmentManager fm = activity.getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                RefDetailFragment rd = new RefDetailFragment();
//                rd.setArguments(bundle);
//                ft.replace(R.id.fragment_container, rd).commit();
//
//            }
//        });

        return rootView;
    }
}
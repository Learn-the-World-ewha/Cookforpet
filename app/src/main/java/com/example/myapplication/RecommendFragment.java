package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecommendFragment extends Fragment {
    SubMainActivity activity;
    //Context context;
    ViewGroup rootView;

    Button recommend_btn;
    RecyclerView recycler_rcp;
    RecipeItemAdapter adapter;
    ArrayList<String> recipe_code;

    String effect;
    int pettype;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    private DatabaseReference reference;
    DatabaseAccess dbAc;




    @Override //프래그먼트->액티비티로 데이터 전송
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (SubMainActivity) context; //서브메인액티비티 얻어내기
    }

    public void onDetach(Context context){
        super.onDetach();
        activity=null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recommend, container, false);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupViews();
        fetchCookForPet();
        setButton();
        recycler_rcp.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
            @Override
            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
                RecipeItem item = adapter.getItem(position);
                Intent intent = new Intent(activity, RecipeActivity.class);
                intent.putExtra("recipe_code", recipe_code.get(position));
                intent.putExtra("recipe_name",item.rcp_txt);
                intent.putExtra("img_url",item.img_url);
                intent.putExtra("recipe_sum", item.txt_sum);
                intent.putExtra("recipe_type", item.txt_type);
                intent.putExtra("recipe_time", item.txt_time);
                intent.putExtra("recipe_tip",item.txt_tip);
                intent.putExtra("recipe_eff",item.txt_eff);
                intent.putExtra("recipe_like",item.txt_like);
                startActivity(intent);  // activity 이동
            }
        });
        }
    private void setupViews() {

        recycler_rcp = (RecyclerView) rootView.findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler_rcp.setLayoutManager(layoutManager);
        reference = FirebaseDatabase.getInstance().getReference("Cookforpet");
        adapter = new RecipeItemAdapter();
    }
    private void setupDatabase() {
        reference = FirebaseDatabase.getInstance().getReference("Cookforpet");
    }

    private void fetchCookForPet() {
        DatabaseReference usertype = reference.child("UserAccount").child(user.getUid()).child("pettype");
        usertype.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String tmp = snapshot.getValue().toString();
                if (tmp.equals("Dog"))
                    pettype = 2;
                else if (tmp.equals("Cat"))
                    pettype = 3;

            fetchEffect();
        }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("fail","pettype");
            }
        });
    }

    private void fetchEffect() {
        DatabaseReference usereffect = reference.child("UserAccount").child(user.getUid()).child("effect");
        usereffect.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                effect = snapshot.getValue().toString();
                fetchAndBindRecipeItems();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void fetchAndBindRecipeItems() {
        ArrayList <RecipeItem> items=getRecipeItems();
        adapter.setItems(items);
    }

    private ArrayList<RecipeItem> getRecipeItems(){
        recipe_code=activity.dbAc.getRecommendCode(pettype,effect);
        ArrayList<ArrayList<String>> recipeLists = activity.dbAc.getRecipelist(recipe_code);

        ArrayList<RecipeItem> result= new ArrayList<>();
        for (ArrayList<String> receiptList : recipeLists) {
            // 아래 데이터타입 변환하는 코드를 보면 파이어베이스 구조를 점검해 보셔야 할 것으로 보이네요.
            // 아래처럼 리스트 구조가 아니라, 맵처럼 해당 필드를 가지는 구조로 flat하게 바꾸셔야 할 것 같은데요...
            RecipeItem item = new RecipeItem(
                    receiptList.get(0),
                    receiptList.get(1),
                    receiptList.get(8),
                    receiptList.get(2),
                    receiptList.get(3),
                    receiptList.get(4),
                    receiptList.get(5),
                    receiptList.get(6),
                    receiptList.get(7));
            result.add(item);
        }
    return result;
    }
    private void setButton(){
        recommend_btn = rootView.findViewById(R.id.recommend_btn);
        recommend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChanged(4);
            }
        });
    }

}



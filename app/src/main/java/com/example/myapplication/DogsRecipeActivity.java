package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DogsRecipeActivity extends AppCompatActivity {

    //DataBaseHelper DB;
    TextView txt_title, txt_tip, txt_type, txt_sum, txt_eff, txt_like, txt_time;
    ImageView img_main;
    RecyclerView recycler_mat, recycler_step;
    Button btn_complete;
    ToggleButton tog_like;
    ArrayList<String> mat_txt, unit_txt, stepNum_txt, step_txt, step_img;
    String main_img, title_txt, tip_txt, recipe_code, type_txt, time_txt, sum_txt, eff_txt;
    int like_sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_recipe);

        Intent intent = getIntent();
        recipe_code=intent.getStringExtra("recipe_code");

        //레시피 관련 정보 출력
        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_type = (TextView)findViewById(R.id.txt_type);
        txt_sum = (TextView)findViewById(R.id.txt_sum);
        txt_tip = (TextView)findViewById(R.id.txt_tip);
        txt_eff = (TextView)findViewById(R.id.txt_eff);
        txt_time = (TextView)findViewById(R.id.txt_time);
        txt_like = (TextView)findViewById(R.id.txt_like);
        img_main = (ImageView)findViewById(R.id.img_main);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT recipe_name, recipe_sum, type, cook_time, tip, img_main, effect, like_sum"
                + " FROM recipe" + " WHERE recipe_code=?", new String[]{recipe_code});

            title_txt = cursor.getString(0);
            sum_txt = cursor.getString(1);
            type_txt = cursor.getString(2);
            time_txt = cursor.getString(3);
            tip_txt = cursor.getString(4);
            main_img = cursor.getString(5);
            eff_txt = cursor.getString(6);
            like_sum = cursor.getInt(7);
        cursor.close();
        dbHelper.close();

        //xml에 적용
        txt_title.setText(title_txt);
        switch (type_txt) {
            case "냥키친":
                txt_type.setText("Cat's");
                break;
            case "멍키친":
                txt_type.setText("Dog's");
                break;
        }
        txt_sum.setText(sum_txt);
        txt_tip.setText(tip_txt);
        txt_eff.setText(eff_txt);
        txt_time.setText(time_txt);
        txt_like.setText(like_sum);
        Glide.with(this)
                .load(main_img)
                .into(img_main);


        recycler_mat = findViewById(R.id.recycler_mat);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler_mat.setLayoutManager(layoutManager);
        MaterialItemAdapter adapter = new MaterialItemAdapter();

        adapter.addItem(new MaterialItem("다진 소고기", "200g"));
        adapter.addItem(new MaterialItem("간장", "1 큰술"));
        adapter.addItem(new MaterialItem("대파", "20g"));
        adapter.addItem(new MaterialItem("물", "200ml"));
        recycler_mat.setAdapter(adapter);

        recycler_step = findViewById(R.id.recycler_step);
        LinearLayoutManager layoutManager2 =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_mat.setLayoutManager(layoutManager);
        StepItemAdapter adapter2 = new StepItemAdapter();
        adapter2.addItem(new StepItem("1","양파와 닭고기를 잘게 썰어주세요.",
                "https://recipe.bom.co.kr/uploads/posts//images//20190109//5c353f65a6ecd.jpeg"));
        adapter2.addItem(new StepItem("2","프라이팬에 올리브 오일 1티스푼을 넣고 준간 불에 가열합니다.",
                "https://recipe.bom.co.kr/uploads/posts//images//20190211//5c60fa6580baf.jpeg"));
        recycler_step.setAdapter(adapter2);

//        MatList matlist = findViewById(R.id.matlist);
//        matlist.setTxt_mat("다진 소고기");
//        matlist.setTxt_unit("200g");
//        StepList steplist = findViewById(R.id.steplist);
//        steplist.setImg_step(R.drawable.ic_launcher_foreground);
//        steplist.setTxt_stepNum("1");
//        steplist.setTxt_step("당근을 잘게 다집니다.");
    }
    public void onButton1Clicked(View v) {
        Toast.makeText(this, "My refrigerator에 추가되었습니다.", Toast.LENGTH_LONG).show();
    }

}
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class SubMainActivity extends AppCompatActivity {
    private ImageButton imgBtn_search;
    private EditText editTxt_search;
    private String search;
    private ImageButton imgBtn_home;
    private ImageButton imgBtn_user;
    Intent intent;

    DatabaseAccess dbAc;
    Fragment recommendFragment;
    Fragment resultFragment;
    Fragment dogResultFragment, catResultFragment;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch(curId){
            case R.id.menu_home:
                intent = new Intent(SubMainActivity.this, SubMainActivity.class);
                startActivity(intent);  // activity 이동
                break;
            case R.id.menu_user:
                intent = new Intent(SubMainActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_main);

        final InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE) ;

        recommendFragment = (RecommendFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        resultFragment = new ResultFragment();
        dogResultFragment = new DogResultFragment();
        catResultFragment = new CatResultFragment();

        dbAc = DatabaseAccess.getInstance(getApplicationContext());
        dbAc.open();

//        RecyclerView recyclerView = findViewById(R.id.recycler_rcp);
//        LinearLayoutManager layoutManager =
//                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        RecipeItemAdapter adapter = new RecipeItemAdapter();
//        // recipeItem 넣어야함
//        recyclerView.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
//            @Override
//            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
//                RecipeItem item = adapter.getItem(position);
//                Intent intent = new Intent(SubMainActivity.this, SearchResultActivity.class);
//                startActivity(intent);  // activity 이동
//            }
//        });


        editTxt_search = findViewById(R.id.editTxt_search);
        imgBtn_search = findViewById(R.id.imgBtn_search);
        imgBtn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                onFragmentChanged(1);
            }
        });

//    25~마지막줄은 수정해야 함. 미완성 레시피 추천 함수임.
//            public void getVal(); {
//
//                DatabaseHelper dbHelper = new DatabaseHelper(this);
//                SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//                Cursor cursor = db.rawQuery("SELECT * FROM recipe_ingredient where RECIPE_ID = 181",null);
//                //" and name = ?",new String[]{"홍길동"});
//                while (cursor.moveToNext())
//                {
//                    val += cursor.getString(2)+", ";
//
//                }
//                sqlresult.setText("재료: "+val);
//                cursor.close();
//                dbHelper.close();
//            }
    }
    public void onFragmentChanged(int index){
        if (index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recommendFragment).commit();
        } else if (index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
        } else if (index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dogResultFragment).commit();
        } else if (index == 3){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, catResultFragment).commit();
        }
    }
    public String getSearch(){
        String s = editTxt_search.getText().toString();
        return s;
    }
}
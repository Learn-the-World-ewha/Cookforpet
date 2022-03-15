package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity {

    RefrigFragment rfragment;
    LikesFragment lfragment;
    DeleteFragment dfragment;

    String user_name, user_code;
    Intent intent;
    DatabaseAccess dbAc;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    private DatabaseReference reference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_userbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch(curId){
            case R.id.menu_home:
                intent = new Intent(UserActivity.this, SubMainActivity.class);
                startActivity(intent);  // activity 이동
                break;
            case R.id.menu_logout:
                showLogoutMessage();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        rfragment = new RefrigFragment();
        lfragment = new LikesFragment();
        dfragment = new DeleteFragment();

        dbAc = DatabaseAccess.getInstance(getApplicationContext());
        dbAc.open();

        reference= FirebaseDatabase.getInstance().getReference("Cookforpet");
        DatabaseReference usercode = reference.child("UserAccount").child(user.getUid());
        usercode.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                    user_code = String.valueOf(task.getResult().getValue());
            }
        });

        DatabaseReference username = reference.child("UserAccount").child(user.getUid()).child("name");
        username.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                    user_name = String.valueOf(task.getResult().getValue());
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, rfragment).commit();



        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("My Refrigerator"));
        tabs.addTab(tabs.newTab().setText("Likes"));
        tabs.addTab(tabs.newTab().setText("Delete Account"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                Log.d("UserActivity", "선택된 탭: " + pos);

                Fragment selected = null;
                if (pos == 0) {
                    selected = rfragment;
                } else if (pos == 1) {
                    selected = lfragment;
                } else if (pos == 2){
                    selected = dfragment;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }
    private void showLogoutMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("로그아웃 하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_info);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(user!=null){
                    firebaseAuth.signOut();
                    intent = new Intent(UserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
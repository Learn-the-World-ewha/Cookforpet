package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteFragment extends Fragment {
    ViewGroup rootView;
    UserActivity activity;
    Context context;
    TextView username_txt;

    private Button btn_yes;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_delete, container, false);
        context = container.getContext();

        activity = (UserActivity) getActivity();

        //유저 이름 띄우기
        username_txt=rootView.findViewById(R.id.name_txt);
        username_txt.setText(activity.user_name);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        reference = FirebaseDatabase.getInstance().getReference("Cookforpet");
        btn_yes = rootView.findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child("UserAccount").child(user.getUid()).removeValue();

                user.delete();

                Intent intent = new Intent(context, MainActivity.class);

                startActivity(intent);  // activity 이동
            }
        });

        return rootView;
    }
}
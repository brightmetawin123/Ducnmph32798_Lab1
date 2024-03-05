package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText edEmail, edPass;
    Button btnLogin;
    TextView tvQuenPass, tvDangky;

FirebaseAuth mAuth;

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            startActivity(new Intent(MainActivity.this, Dangxuat.class));
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        edEmail = findViewById(R.id.ed_emmail);
        edPass = findViewById(R.id.ed_Password);
        btnLogin = findViewById(R.id.btn_login);
        tvQuenPass = findViewById(R.id.tv_quenpass);
        tvDangky = findViewById(R.id.tv_dangky);

        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Dangky.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }

    private  void login(){
        String email = edEmail.getText().toString();
        String pass = edPass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
           FirebaseUser user = mAuth.getCurrentUser();
           startActivity(new Intent(MainActivity.this, Dangxuat.class));
                    Toast.makeText(MainActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
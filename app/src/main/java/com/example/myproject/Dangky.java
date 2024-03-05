package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dangky extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText edEmail, edPass;
    Button btnDangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        mAuth = FirebaseAuth.getInstance();

        edEmail = findViewById(R.id.ed_emmail);
        edPass = findViewById(R.id.ed_Password);
        btnDangky = findViewById(R.id.btn_dangky);
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String pass = edPass.getText().toString();
                if (email.isEmpty()|| pass.isEmpty()){
                    Toast.makeText(Dangky.this, "Bạn cần nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
                }else{
    DangkyTK();
                }

            }
        });
    }
    private void DangkyTK(){
        mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(), edPass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               FirebaseUser user = mAuth.getCurrentUser();
               Toast.makeText(Dangky.this, "Bạn đã đăng ký thành công !!!", Toast.LENGTH_SHORT).show();
           }else{
               Log.d("ZZZZ", "onComplete: " + task.getException())  ;
               Toast.makeText(Dangky.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
           }

            }
        });
    }
}
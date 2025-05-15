package com.example.memento;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    DatabaseHelper db;
    EditText  etPassword, etRePassword, etuserName, etStoreName;
    Button btnSignup, btnSLogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        etuserName = findViewById(R.id.etuserName);
        etStoreName = findViewById(R.id.etStoreName);

        btnSignup = findViewById(R.id.btnSignUp);
        btnSLogin = findViewById(R.id.btnSLogin);

        db = new DatabaseHelper(this);

    /// BTN SIGN UP ACCOUNT ///
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etuserName.getText().toString();
                String password = etPassword.getText().toString();
                String rePassword = etRePassword.getText().toString();
                String storeName = etStoreName.getText().toString();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)){
                    Toast.makeText(SignUp.this, "All fields Required", Toast.LENGTH_SHORT).show();

                }else {
                    if (password.equals(rePassword)){
                        Boolean checkEmail = db.checkUserName(userName);
                        if(checkEmail == false){
                            Boolean insertAccount = db.insertAccount(userName,storeName,password);
                            if (insertAccount == true){
                                Toast.makeText(SignUp.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),Login.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(SignUp.this, "Registration failed!", Toast.LENGTH_SHORT).show();

                            }
                        }else {
                            Toast.makeText(SignUp.this, "you already have a account!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignUp.this, "password do not much!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /// BTN LOGIN ////
        btnSLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(getApplicationContext(), Login.class);
              startActivity(i); // 16:20
            }
        });
    }
}
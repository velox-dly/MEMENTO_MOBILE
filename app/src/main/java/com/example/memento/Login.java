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

public class  Login extends AppCompatActivity {
        EditText    etLogPassword, etuserNameLog;
        Button btnLogin;
        DatabaseHelper db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etuserNameLog =     findViewById(R.id.etuserNameLog);
        etLogPassword = findViewById(R.id.etLogPassword);
        btnLogin = findViewById(R.id.btnLogin);
        db = new DatabaseHelper(this);


        /// BTN LOGIN ////
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etuserNameLog.getText().toString();
                String password = etLogPassword.getText().toString();

                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "All fields Required", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkEmail = db.checkPassword(userName, password);
                    if (checkEmail == true){
                        Toast.makeText(Login.this,"Login Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, Dashboard.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
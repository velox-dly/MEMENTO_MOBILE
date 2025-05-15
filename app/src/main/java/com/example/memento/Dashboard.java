package com.example.memento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    TextView tvUserName, tvStoreName;
    Animation atg;
    GridLayout glIcons;
    CardView cvAddDebtor, cvEditRec, cvList, cvDevelopers;
    DatabaseHelper db;
    String [] userName;
    String []   storeName;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvUserName = findViewById(R.id.tvUserName);
        tvStoreName = findViewById(R.id.tvStoreName);

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        glIcons = (GridLayout) findViewById(R.id.glIcons);
        db = new DatabaseHelper(this);
        cvAddDebtor = (CardView) findViewById(R.id.cvAddDebtor);
        cvEditRec = (CardView) findViewById(R.id.cvEditRec);
        cvList = (CardView) findViewById(R.id.cvList);
        cvDevelopers = (CardView) findViewById(R.id.cvDevelopers);

        glIcons.startAnimation(atg);
        getAccountData();

        ///// ADD RECORD ////
        cvAddDebtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addDebtor = new Intent(Dashboard.this, AddDebtor.class);
                startActivity(addDebtor);
            }
        });

        ///// EDIT RECORD ////
        cvEditRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editRec = new Intent(Dashboard.this, EditRecord.class);
                startActivity(editRec);
            }
        });

        //// LIST RECORD ////
        cvList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listDebt = new Intent(Dashboard.this, ListDebt.class);
                startActivity(listDebt);

            }
        });

        //// ABOUT DEVELOPERS ////
        cvDevelopers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent developers = new Intent(Dashboard.this, Developers.class);
                startActivity(developers);
            }
        });
    }

    /// DASHBOARD SET USERNAME AND STORE NAME  ///////
    public void getAccountData(){
        Cursor res = db.getAccountData("0");

        if (res.getCount() == 0) {
         //   Toast.makeText(this, "No record found! Please add a record first.", Toast.LENGTH_SHORT).show();

            userName = new String[]{"No record found"};
            storeName = new String[]{"No record found"};
        }else {
           userName = new String[res.getCount()];
            storeName = new String[res.getCount()];
        } //int ctr = 0;
        while (res.moveToNext()) {
            tvUserName.setText("Hello, " + res.getString(0));
            tvStoreName.setText(res.getString(1));
        }

    }


}

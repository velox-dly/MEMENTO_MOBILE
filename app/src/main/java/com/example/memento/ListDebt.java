package com.example.memento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ListDebt extends AppCompatActivity {
    TextView tvRecords;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_debt);

        getSupportActionBar().setTitle("List of Debtors");

        tvRecords = (TextView) findViewById(R.id.tvRecords);
        db = new DatabaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //para sa back button lang to

        Cursor res = db.getData("0");
        if(res.getCount()==0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(ListDebt.this);
            alert.setTitle("Error");
            alert.setMessage("Record not found!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent toMain =  new Intent(ListDebt.this, MainActivity.class);
                    startActivity(toMain);
                }
            });

            return;
        }
        StringBuffer b = new StringBuffer();
        while (res.moveToNext()){
            b.append("Customer ID: " + "\t\t\t\t\t  " + res.getString(0)+ "\n");
            b.append("Name: " + "\t\t\t\t\t\t\t\t\t\t\t\t\t"  + res.getString(1)+ "\n");
            b.append("Contact Number: " + "\t\t\t" + res.getString(2)+ "\n");
            b.append("Date Utang: " + "\t\t\t\t\t\t\t" + res.getString(3)+ "\n\n");

            b.append("(1) Product: " + "\t\t\t" + res.getString(4)+ "\n");
            b.append("(1) Quantity: " + "\t\t\t" + res.getString(5)+ "\n");
            b.append("(1) Price: " + "\t\t\t\t\t\t" + res.getString(6)+ "\n\n");

            b.append("(2) Product: " + "\t\t\t" + res.getString(7)+ "\n");
            b.append("(2) Quantity: " + "\t\t\t" + res.getString(8)+ "\n");
            b.append("(2) Price: " + "\t\t\t\t   " + res.getString(9)+ "\n\n");

            b.append("(3) Product: " + "\t\t\t" + res.getString(10)+ "\n");
            b.append("(3) Quantity: " + "\t\t\t" + res.getString(11)+ "\n");
            b.append("(3) Price: " + "\t\t\t\t\t\t" + res.getString(12)+ "\n\n");

            b.append("(4) Product: " + "\t\t\t" + res.getString(13)+ "\n");
            b.append("(4) Quantity: " + "\t\t\t" + res.getString(14)+ "\n");
            b.append("(4) Price: " + "\t\t\t\t\t\t" + res.getString(15)+ "\n\n");

            b.append("(5) Product: " + "\t\t\t" + res.getString(16)+ "\n");
            b.append("(5) Quantity: " + "\t\t\t" + res.getString(17)+ "\n");
            b.append("(5) Price: " + "\t\t\t\t\t\t" + res.getString(18)+ "\n\n");
            b.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            b.append("\t\t\t\t\t\t\t\t\t\t\t\t" + "Total: " + res.getString(19) + "\n\n");
            b.append("- - - - - - - - - - - - - - - - - - - - - - - - - \n\n");
        }
        tvRecords.setText(b.toString());
    }
}
package com.example.memento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddDebtor extends AppCompatActivity {
    DatePickerDialog DpPicker;
    Calendar calendar;
    DatabaseHelper dbHelper;
    EditText etName, etCpnum, etDatePicker;
    AutoCompleteTextView etProd1, etProd2, etProd3, etProd4, etProd5;
    EditText etQty1, etQty2, etQty3, etQty4, etQty5;
    EditText etPrice1, etPrice2, etPrice3, etPrice4, etPrice5;
    TextView tvTotal;
    Button btnCompute, btnCancel, btnSave;

    String[] products = {"Sardinas", "Pancit canton", "Toyo", "suka", "Itlog", "Mantika", "Asukal",
            "Kopiko Blanca", "Kopiko Brown Coffee", "GreatTaste Choco", "GreatTaste White",
            "Nescafe original", "Milo", "Birch Tree", "Bear Brand Swak", "Kopiko Black",
            "Nescafe decaf 25g", "Nescafe Original 25g", "GreatTaste Premium classic 25g",
            "UFC Ketchup", "Mang Tomas", "Knorr Cubes Pork", "Knorr Cubes Shrimp", "Knor cubes Chicken",
            "Knorr Cubes Beef", "Cream Silk", "Palmolive", "Downy", "Surf Fabcon", "Surf Powder",
            "Keratin", "Safeguard Sachet"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debtor);

        getSupportActionBar().setTitle("Add Debtor");

        dbHelper = new DatabaseHelper(this);
        etName = (EditText) findViewById(R.id.etName);
        etCpnum = (EditText) findViewById(R.id.etCpnum);
        etProd1 = (AutoCompleteTextView) findViewById(R.id.etProd1);
        etProd2 = (AutoCompleteTextView) findViewById(R.id.etProd2);
        etProd3 = (AutoCompleteTextView) findViewById(R.id.etProd3);
        etProd4 = (AutoCompleteTextView) findViewById(R.id.etProd4);
        etProd5 = (AutoCompleteTextView) findViewById(R.id.etProd5);
        etQty1 = (EditText) findViewById(R.id.etQty1);
        etQty2 = (EditText) findViewById(R.id.etQty2);
        etQty3 = (EditText) findViewById(R.id.etQty3);
        etQty4 = (EditText) findViewById(R.id.etQty4);
        etQty5 = (EditText) findViewById(R.id.etQty5);
        etPrice1 = (EditText) findViewById(R.id.etPrice1);
        etPrice2 = (EditText) findViewById(R.id.etPrice2);
        etPrice3 = (EditText) findViewById(R.id.etPrice3);
        etPrice4 = (EditText) findViewById(R.id.etPrice4);
        etPrice5 = (EditText) findViewById(R.id.etPrice5);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btnCompute = (Button) findViewById(R.id.btnCompute);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);
        etDatePicker = (EditText) findViewById(R.id.etDatePicker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /// SETTING CALENDAR ///
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCaledar();
            }
            public void updateCaledar() {
                String format = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                etDatePicker.setText(sdf.format(calendar.getTime()));
            }

        };
            /// CALINDAR LISTENER ///
        etDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddDebtor.this, date,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //// BTN COMPUTE ////
        btnCompute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                int qty1=0, qty2=0, qty3=0, qty4=0, qty5=0;
                    double price1=0, price2=0, price3=0, price4=0, price5=0;

                if (TextUtils.isEmpty(etQty1.getText())) {
                    qty1 = 0;
                } else {
                    qty1 = Integer.parseInt(etQty1.getText().toString());
                }
                if (TextUtils.isEmpty(etQty2.getText())) {
                    qty2 = 0;
                } else {
                    qty2 = Integer.parseInt(etQty2.getText().toString());
                }
                if (TextUtils.isEmpty(etQty3.getText())) {
                    qty3 = 0;
                } else {
                    qty3 = Integer.parseInt(etQty3.getText().toString());
                }
                if (TextUtils.isEmpty(etQty4.getText())) {
                    qty4 = 0;
                } else {
                    qty4 = Integer.parseInt(etQty4.getText().toString());
                }
                if (TextUtils.isEmpty(etQty5.getText())) {
                    qty5 = 0;
                } else {
                    qty5 = Integer.parseInt(etQty5.getText().toString());
                }

                if (TextUtils.isEmpty(etPrice1.getText())) {
                    price1 = 0;
                } else {
                    price1 = Double.parseDouble(etPrice1.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice2.getText())) {
                    price2 = 0;
                }  else {
                    price2 = Double.parseDouble(etPrice2.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice3.getText())) {
                    price3 = 0;
                }  else {
                    price3 = Double.parseDouble(etPrice3.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice4.getText())) {
                    price4 = 0;
                }  else {
                    price4 = Double.parseDouble(etPrice4.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice5.getText())) {
                    price5 = 0;
                }  else {
                    price5 = Double.parseDouble(etPrice5.getText().toString());
                }

                double total = (qty1*price1) + (qty2*price2) + (qty3*price3) + (qty4*price4) + (qty5*price5);
                tvTotal.setText((String.format("%.2f", total)));

            }
        });

        ///// BTN CANCEL ////
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder cancel = new AlertDialog.Builder(AddDebtor.this);
                cancel.setTitle("ALERT");
                cancel.setMessage("Are you sure you want to cancel? Records might not be saved.");
                cancel.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearFields();
                        Intent toMain = new Intent(AddDebtor.this, Dashboard.class);
                        startActivity(toMain);
                    }
                });
                cancel.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                cancel.show();

            }
        });

        /// BTN SAVE ///
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TO CHECK IF THE INPUTS ARE EMPTY OR NOT //
                String name="", cpnum="", date="";
                String prod1, prod2, prod3, prod4, prod5;
                int qty1=0, qty2=0, qty3=0, qty4=0, qty5=0;
                double price1=0, price2=0, price3=0, price4=0, price5=0, total=0;

                if (TextUtils.isEmpty(etName.getText())){
                    Toast.makeText(AddDebtor.this, "Name is required.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    name = etName.getText().toString();
                }
                if (TextUtils.isEmpty(etCpnum.getText())){
                    Toast.makeText(AddDebtor.this, "Cellphone Number is required.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    cpnum = etCpnum.getText().toString();
                }
                if (TextUtils.isEmpty(etDatePicker.getText())){
                    Toast.makeText(AddDebtor.this, "Date is required.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    date = etDatePicker.getText().toString();
                }
                if (TextUtils.isEmpty(etProd1.getText())){
                    prod1 = " - ";
                }else {
                    prod1 = etProd1.getText().toString();
                }
                if (TextUtils.isEmpty(etProd2.getText())){
                    prod2 = " - ";
                }else {
                    prod2 = etProd2.getText().toString();
                }
                if (TextUtils.isEmpty(etProd3.getText())){
                    prod3 = " - ";
                }else {
                    prod3 = etProd3.getText().toString();
                }
                if (TextUtils.isEmpty(etProd4.getText())){
                    prod4 = " - ";
                }else {
                    prod4 = etProd4.getText().toString();
                }
                if (TextUtils.isEmpty(etProd5.getText())){
                    prod5 = " - ";
                }else {
                    prod5 = etProd5.getText().toString();
                }
                if (TextUtils.isEmpty(etQty1.getText())) {
                    qty1 = 0;
                } else {
                    qty1 = Integer.parseInt(etQty1.getText().toString());
                }
                if (TextUtils.isEmpty(etQty2.getText())) {
                    qty2 = 0;
                } else {
                    qty2 = Integer.parseInt(etQty2.getText().toString());
                }
                if (TextUtils.isEmpty(etQty3.getText())) {
                    qty3 = 0;
                } else {
                    qty3 = Integer.parseInt(etQty3.getText().toString());
                }
                if (TextUtils.isEmpty(etQty4.getText())) {
                    qty4 = 0;
                } else {
                    qty4 = Integer.parseInt(etQty4.getText().toString());
                }
                if (TextUtils.isEmpty(etQty5.getText())) {
                    qty5 = 0;
                } else {
                    qty5 = Integer.parseInt(etQty5.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice1.getText())) {
                    price1 = 0;
                } else {
                    price1 = Double.parseDouble(etPrice1.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice2.getText())) {
                    price2 = 0;
                }  else {
                    price2 = Double.parseDouble(etPrice2.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice3.getText())) {
                    price3 = 0;
                }  else {
                    price3 = Double.parseDouble(etPrice3.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice4.getText())) {
                    price4 = 0;
                }  else {
                    price4 = Double.parseDouble(etPrice4.getText().toString());
                }
                if (TextUtils.isEmpty(etPrice5.getText())) {
                    price5 = 0;
                }  else {
                    price5 = Double.parseDouble(etPrice5.getText().toString());
                }
                if (TextUtils.isEmpty(tvTotal.getText())) {
                    total = 0;
                }  else {
                    total = Double.parseDouble(tvTotal.getText().toString());
                }

                boolean res = dbHelper.insertRecord(name, cpnum, date,
                        prod1, qty1, price1,
                        prod2, qty2, price2,
                        prod3, qty3, price3,
                        prod4, qty4, price4,
                        prod5, qty5, price5,
                        total);
                if(res==true){
                    Toast.makeText(AddDebtor.this,"Record Added!", Toast.LENGTH_SHORT).show();
                    clearFields();
                }else {
                    Toast.makeText(AddDebtor.this,"Error saving Record!", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(AddDebtor.this, Dashboard.class);
                startActivity(i);

            }
        });

        ArrayAdapter<String> productAdapter = new ArrayAdapter<String>(AddDebtor.this, R.layout.auto_layout, R.id.tvAuto, products);
                etProd1.setAdapter(productAdapter);
                etProd2.setAdapter(productAdapter);
                etProd3.setAdapter(productAdapter);
                etProd4.setAdapter(productAdapter);
                etProd5.setAdapter(productAdapter);
    }
    ////  CLEAR / REFRESH METHOD ////
    public  void clearFields() {
        etName.setText("");
        etCpnum.setText("");
        etDatePicker.setText("");
        etProd1.setText("");
        etProd2.setText("");
        etProd3.setText("");
        etProd4.setText("");
        etProd5.setText("");
        etQty1.setText("");
        etQty2.setText("");
        etQty3.setText("");
        etQty4.setText("");
        etQty5.setText("");
        etPrice1.setText("");
        etPrice2.setText("");
        etPrice3.setText("");
        etPrice4.setText("");
        etPrice5.setText("");
        tvTotal.setText("");
    }

}



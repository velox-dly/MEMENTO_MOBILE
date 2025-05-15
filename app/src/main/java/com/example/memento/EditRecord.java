package com.example.memento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EditRecord extends AppCompatActivity {
    DatabaseHelper dbHelper;
    Calendar calendar;
    SQLiteDatabase db;
    String[] customerid;
    String[] fullName;
    String[] contactNumber;
    String[] dateUtang;
    String[] product1;
    Integer[] Qty1;
    Double[] price1;
    String[] product2;
    Integer[] Qty2;
    Double[] price2;
    String[] product3;
    Integer[] Qty3;
    Double[] price3;
    String[] product4;
    Integer[] Qty4;
    Double[] price4;
    String[] product5;
    Integer[] Qty5;
    Double[] price5;
    Double[] total;
    ListView lv;
    View mView;
    Dialog dialog;
    DatePickerDialog DpPicker;

    // PRODUCT ARRAYS
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
        setContentView(R.layout.activity_edit_record);

        getSupportActionBar().setTitle("Edit Record");

        mView = getLayoutInflater().inflate(R.layout.edit_record_layout, null); // EDIT_RECORD_LAYOUT yung mag popup kapag long press
        AlertDialog.Builder b = new AlertDialog.Builder(EditRecord.this);
        b.setView(mView);
        dialog = b.create();

        dbHelper = new DatabaseHelper(this);

        getData();

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long customerid) {

                String a = parent.getItemAtPosition(position).toString();
                RetrieveData(a);
                return true;
            }
        });
    }

    /////////////// GET DATA ///////////////////////////
    public void getData() {
        lv = (ListView) findViewById(R.id.lvRecords);

        Cursor res = dbHelper.getData("0");

        if (res.getCount() == 0) {
            Toast.makeText(EditRecord.this, "No record found!", Toast.LENGTH_SHORT).show();

            customerid = new String[]{"No record found"};
            fullName = new String[]{"No record found"};
            contactNumber = new String[]{"No record found"};
            dateUtang = new String[]{"No record found"};

            product1 = new String[]{"No record found"};
            Qty1 = new Integer[]{0};
            price1 = new Double[]{0.00};

            product2 = new String[]{"No record found"};
            Qty2 = new Integer[]{0};
            price2 = new Double[]{0.00};

            product3 = new String[]{"No record found"};
            Qty3 = new Integer[]{0};
            price3 = new Double[]{0.00};

            product4 = new String[]{"No record found"};
            Qty4 = new Integer[]{0};
            price4 = new Double[]{0.00};

            product5 = new String[]{"No record found"};
            Qty5 = new Integer[]{0};
            price5 = new Double[]{0.00};

            total = new Double[]{0.00};


        } else {
            customerid = new String[res.getCount()];
            fullName = new String[res.getCount()];
            contactNumber = new String[res.getCount()];
            dateUtang = new String[res.getCount()];

            product1 = new String[res.getCount()];
            Qty1 = new Integer[res.getCount()];
            price1 = new Double[res.getCount()];

            product2 = new String[res.getCount()];
            Qty2 = new Integer[res.getCount()];
            price2 = new Double[res.getCount()];

            product3 = new String[res.getCount()];
            Qty3 = new Integer[res.getCount()];
            price3 = new Double[res.getCount()];

            product4 = new String[res.getCount()];
            Qty4 = new Integer[res.getCount()];
            price4 = new Double[res.getCount()];

            product5 = new String[res.getCount()];
            Qty5 = new Integer[res.getCount()];
            price5 = new Double[res.getCount()];

            total = new Double[res.getCount()];

            int ctr = 0;
            while (res.moveToNext()) {
                customerid[ctr] = res.getString(0);
                fullName[ctr] = res.getString(1);
                contactNumber[ctr] = res.getString(2);
                dateUtang[ctr] = res.getString(3);

                product1[ctr] = res.getString(4);
                Qty1[ctr] = res.getInt(5);
                price1[ctr] = res.getDouble(6);

                product2[ctr] = res.getString(7);
                Qty2[ctr] = res.getInt(8);
                price2[ctr] = res.getDouble(9);

                product3[ctr] = res.getString(10);
                Qty3[ctr] = res.getInt(11);
                price3[ctr] = res.getDouble(12);

                product4[ctr] = res.getString(13);
                Qty4[ctr] = res.getInt(14);
                price4[ctr] = res.getDouble(15);

                product5[ctr] = res.getString(16);
                Qty5[ctr] = res.getInt(17);
                price5[ctr] = res.getDouble(18);

                total[ctr] = res.getDouble(19);
                ctr++;

            }
        }
        CustomAdapter adapter = new CustomAdapter(this, customerid, fullName, contactNumber, dateUtang,
                product1, Qty1, price1,
                product2, Qty2, price2,
                product3, Qty3, price3,
                product4, Qty4, price4,
                product5, Qty5, price5, total);
                lv.setAdapter(adapter);

    }

    //////////DELETE RECORD DIALOG////////////
    public void DeleteRec(String customerid) {
        final String delID = customerid;
        DialogInterface.OnClickListener dialogInterface = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Boolean resDel = dbHelper.deleteData(delID);
                        if (resDel == true) {
                            dialog.dismiss();
                            Toast.makeText(EditRecord.this, "Record Deleted!", Toast.LENGTH_SHORT).show();
                            getData();
                        } else {
                            Toast.makeText(EditRecord.this, "Error deleting Record", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;

                }
         }
        };
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setCancelable(true);
        b.setTitle("Delete Record");
        b.setMessage("Are you sure you want to delete this record?")
            .setPositiveButton("YES", dialogInterface).setNegativeButton("NO",dialogInterface).show();

    }

            /////   RETRIEVE DATA  ////////////
    public void RetrieveData(String customerid) {

        final String GetId = customerid;
        Cursor res = dbHelper.getData(customerid);
        EditText fullName = (EditText) mView.findViewById(R.id.etEditFullName);
        EditText contactNumber = (EditText) mView.findViewById(R.id.etEditContactNumber);
        EditText utangDate = (EditText) mView.findViewById(R.id.etEditUtangDate);

        AutoCompleteTextView product1 = (AutoCompleteTextView) mView.findViewById(R.id.acEditProd1);
        EditText Qty1z = (EditText) mView.findViewById(R.id.etEditQty1);
        EditText price1 = (EditText) mView.findViewById(R.id.etEditPrice1);

        AutoCompleteTextView product2 = (AutoCompleteTextView) mView.findViewById(R.id.acEditProd2);
        EditText Qty2z = (EditText) mView.findViewById(R.id.etEditQty2);
        EditText price2 = (EditText) mView.findViewById(R.id.etEditPrice2);

        AutoCompleteTextView product3 = (AutoCompleteTextView) mView.findViewById(R.id.acEditProd3);
        EditText Qty3z = (EditText) mView.findViewById(R.id.etEditQty3);
        EditText price3 = (EditText) mView.findViewById(R.id.etEditPrice3);

        AutoCompleteTextView product4 = (AutoCompleteTextView) mView.findViewById(R.id.acEditProd4);
        EditText Qty4z = (EditText) mView.findViewById(R.id.etEditQty4);
        EditText price4 = (EditText) mView.findViewById(R.id.etEditPrice4);

        AutoCompleteTextView product5 = (AutoCompleteTextView) mView.findViewById(R.id.acEditProd5);
        EditText Qty5z = (EditText) mView.findViewById(R.id.etEditQty5);
        EditText price5 = (EditText) mView.findViewById(R.id.etEditPrice5);

        TextView total = (TextView) mView.findViewById(R.id.tvEditTotal);

        Button btnEditCompute = (Button) mView.findViewById(R.id.btnEditCompute);
        Button btnDelete = (Button) mView.findViewById(R.id.btnDelete);
        Button btnSaveUpdate = (Button) mView.findViewById(R.id.btnSaveUpdate);


        // FOR AUTOCOMPLETE ARRAY ADAPTER
        ArrayAdapter<String> productAdapter = new ArrayAdapter<String>(EditRecord.this, R.layout.auto_layout, R.id.tvAuto, products);
        product1.setAdapter(productAdapter);
        product2.setAdapter(productAdapter);
        product3.setAdapter(productAdapter);
        product4.setAdapter(productAdapter);
        product5.setAdapter(productAdapter);

        if (res.getCount() == 0) {
            Toast.makeText(EditRecord.this, "No record found!", Toast.LENGTH_LONG).show();
        } else {
            while (res.moveToNext()) {

                fullName.setText(res.getString(1));
                contactNumber.setText(res.getString(2));
                utangDate.setText(res.getString(3));

                product1.setText(res.getString(4));
                Qty1z.setText(String.valueOf(res.getInt(5)));
                price1.setText(String.valueOf(res.getDouble(6)));

                product2.setText(res.getString(7));
                Qty2z.setText(String.valueOf(res.getInt(8)));
                price2.setText(String.valueOf(res.getDouble(9)));

                product3.setText(res.getString(10));
                Qty3z.setText(String.valueOf(res.getInt(11)));
                price3.setText(String.valueOf(res.getDouble(12)));

                product4.setText(res.getString(13));
                Qty4z.setText(String.valueOf(res.getInt(14)));
                price4.setText(String.valueOf(res.getDouble(15)));

                product5.setText(res.getString(16));
                Qty5z.setText(String.valueOf(res.getInt(17)));
                price5.setText(String.valueOf(res.getDouble(18)));

                total.setText(String.valueOf(res.getString(19)));
            }
        }

        dialog.show();

            //// CALENDAR SET ////
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
                utangDate.setText(sdf.format(calendar.getTime()));
            }
        };

        /// CALENDAR LISTENER ////
        utangDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditRecord.this, date,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /// BTN SAVE UPDATE /////////////
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TO CHECK IF THE INPUTS ARE EMPTY OR NOT //
                String Fullname, cNumber, Udate;
                String P1, P2, P3, P4, P5;
                int q1=0, q2=0, q3=0, q4=0, q5=0;
                double Pr1=0, Pr2=0, Pr3=0, Pr4=0, Pr5=0, Totalz=0;

                if (TextUtils.isEmpty(fullName.getText())){
                    Toast.makeText(EditRecord.this, "Name is required.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Fullname = fullName.getText().toString();
                }
                if (TextUtils.isEmpty(contactNumber.getText())){
                    Toast.makeText(EditRecord.this, "Cellphone Number is required.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    cNumber =  contactNumber.getText().toString();
                }
                if (TextUtils.isEmpty(utangDate.getText())){
                    Toast.makeText(EditRecord.this, "Date is required.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Udate = utangDate.getText().toString();
                }
                if (TextUtils.isEmpty(product1.getText())){
                    P1 = " - ";
                }else {
                    P1 = product1.getText().toString();
                }
                if (TextUtils.isEmpty(product2.getText())){
                    P2  = " - ";
                }else {
                    P2 = product2.getText().toString();
                }
                if (TextUtils.isEmpty(product3.getText())){
                    P3 = " - ";
                }else {
                    P3 = product3.getText().toString();
                }
                if (TextUtils.isEmpty(product4.getText())){
                    P4 = " - ";
                }else {
                    P4 = product4.getText().toString();
                }
                if (TextUtils.isEmpty(product5.getText())){
                    P5 = " - ";
                }else {
                    P5 = product5.getText().toString();
                }
                if (TextUtils.isEmpty(Qty1z.getText().toString())) {
                    q1 = 0;
                } else {
                    q1 = Integer.parseInt(Qty1z.getText().toString());
                }
                if (TextUtils.isEmpty(Qty2z.getText())) {
                    q2 = 0;
                } else {
                    q2 = Integer.parseInt(Qty2z.getText().toString());
                }
                if (TextUtils.isEmpty(Qty3z.getText())) {
                    q3 = 0;
                } else {
                    q3 = Integer.parseInt(Qty3z.getText().toString());
                }
                if (TextUtils.isEmpty(Qty4z.getText())) {
                    q4 = 0;
                } else {
                    q4 = Integer.parseInt(Qty4z.getText().toString());
                }
                if (TextUtils.isEmpty(Qty5z.getText())) {
                    q5 = 0;
                } else {
                    q5 = Integer.parseInt(Qty5z.getText().toString());
                }
                if (TextUtils.isEmpty(price1.getText())) {
                    Pr1 = 0;
                } else {
                    Pr1 = Double.parseDouble(price1.getText().toString());
                }
                if (TextUtils.isEmpty(price2.getText())) {
                    Pr2 = 0;
                }  else {
                    Pr2 = Double.parseDouble(price2.getText().toString());
                }
                if (TextUtils.isEmpty(price3.getText())) {
                    Pr3 = 0;
                }  else {
                    Pr3 = Double.parseDouble(price3.getText().toString());
                }
                if (TextUtils.isEmpty(price4.getText())) {
                    Pr4 = 0;
                }  else {
                    Pr4 = Double.parseDouble(price4.getText().toString());
                }
                if (TextUtils.isEmpty(price5.getText())) {
                    Pr5 = 0;
                }  else {
                    Pr5 = Double.parseDouble(price5.getText().toString());
                }
                if (TextUtils.isEmpty(total.getText())) {
                    Totalz = 0;
                }  else {
                    Totalz = Double.parseDouble(total.getText().toString());
                }

                        Boolean res = dbHelper.updateData(GetId, Fullname, cNumber, Udate,
                                                            P1, q1, Pr1,
                                                            P2, q2, Pr2,
                                                            P3,q3,Pr3,
                                                            P4, q4, Pr4,
                                                            P5, q5, Pr5, Totalz);
                if (res == true) {
                    getData();
                    dialog.dismiss();
                    Toast.makeText(EditRecord.this, "Record Updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditRecord.this, "Error Updating Record!", Toast.LENGTH_SHORT).show();
                }
            }

        });


        /// BTN EDIT COMPUTE ////
        btnEditCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty1=0, qty2=0, qty3=0, qty4=0, qty5=0;
                double price1z=0, price2z=0, price3z=0, price4z=0, price5z=0;

                if (TextUtils.isEmpty(Qty1z.getText())) {
                    qty1 = 0;
                } else {
                    qty1 = Integer.parseInt(Qty1z.getText().toString());
                }
                if (TextUtils.isEmpty(Qty2z.getText())) {
                    qty2 = 0;
                } else {
                    qty2 = Integer.parseInt(Qty2z.getText().toString());
                }
                if (TextUtils.isEmpty(Qty3z.getText())) {
                    qty3 = 0;
                } else {
                    qty3 = Integer.parseInt(Qty3z.getText().toString());
                }
                if (TextUtils.isEmpty(Qty4z.getText())) {
                    qty4 = 0;
                } else {
                    qty4 = Integer.parseInt(Qty4z.getText().toString());
                }
                if (TextUtils.isEmpty(Qty5z.getText())) {
                    qty5 = 0;
                } else {
                    qty5 = Integer.parseInt(Qty5z.getText().toString());
                }

                if (TextUtils.isEmpty(price1.getText())) {
                    price1z = 0;
                } else {
                    price1z = Double.parseDouble(price1.getText().toString());
                }
                if (TextUtils.isEmpty(price2.getText())) {
                    price2z = 0;
                }  else {
                    price2z = Double.parseDouble(price2.getText().toString());
                }
                if (TextUtils.isEmpty(price3.getText())) {
                    price3z = 0;
                }  else {
                    price3z = Double.parseDouble(price3.getText().toString());
                }
                if (TextUtils.isEmpty(price4.getText())) {
                    price4z = 0;
                }  else {
                    price4z = Double.parseDouble(price4.getText().toString());
                }
                if (TextUtils.isEmpty(price5.getText())) {
                    price5z = 0;
                }  else {
                    price5z = Double.parseDouble(price5.getText().toString());
                }

                double totalz = (qty1*price1z) + (qty2*price2z) + (qty3*price3z) + (qty4*price4z) + (qty5*price5z);
                total.setText((String.format("%.2f", totalz)));

            }
        });


        //// BTN DELETE  //////////////
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                DeleteRec(GetId);
            }
        });

    }
}


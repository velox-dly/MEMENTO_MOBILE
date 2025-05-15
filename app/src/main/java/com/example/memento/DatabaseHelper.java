package com.example.memento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String db_name = "memento_db";
    public static  final  String tbl_name = "debtor_tbl";
    public static final String tbl_name2z = "account_tbl";

    public DatabaseHelper(Context context) {
        super(context, db_name, null,3); // 3 is number of tables yung isa dummy for testing
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + tbl_name + "(customerid integer primary key autoincrement, fullName text, contactNumber text, dateUtang text," +
            " product1 text, Qty1 integer, price1 double," +
            "product2 text, Qty2 integer, price2 double," +
            "product3 text, Qty3 integer, price3 double, " +
            "product4 text, Qty4 integer, price4 double, " +
            "product5 text, Qty5 integer, price5 double, total double)");
    db.execSQL("CREATE TABLE "+ tbl_name2z + "(userName text primary key, storeName text, password text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tbl_name);
        db.execSQL("DROP TABLE IF EXISTS " + tbl_name2z);
        onCreate(db);
    }

                    //////////// FOR ACCOUNT DB  ////////////////

    /// INSERT ACCOUNT  ////
        public boolean insertAccount(String userName, String storeName, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v =   new ContentValues();

        v.put("userName", userName);
        v.put("storeName", storeName);
        v.put("password", password);


        long result = db.insert(tbl_name2z, null , v);
        if(result == -1){
            return false;
        }else{
            return true;
        }
        }

        /// CHECK USERNAME ///
    public  boolean checkUserName (String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tbl_name2z + " where userName=?", new String[]{userName});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    /// CHECK USERNAME AND PASSWORD  ///
    public  boolean checkPassword (String userName, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tbl_name2z + " where userName=? and password=?", new String[]{userName,password});
        if (cursor.getCount()>0){
            return true;
        }else   {
            return false;
        }
    }

    //// GET ACCOUNT DATA ////
    public Cursor getAccountData (String userName){ //cursor pointer
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        if (userName=="0"){
            res = db.rawQuery("SELECT * FROM " + tbl_name2z, null);
        }else {
            res = db.rawQuery("SELECT * FROM " + tbl_name2z + " WHERE userName=  '"+userName+"'",null);
        }
        return  res;
    }

        ///////////////////////////// PRODUCT / DEBT DB //////////////////


    /////// METHOD INSERT DATA /////
    public boolean insertRecord (String fullName, String contactNumber, String dateUtang,
                             String product1, Integer Qty1, Double price1,
                             String product2, Integer Qty2, Double price2,
                             String product3, Integer Qty3, Double price3,
                             String product4, Integer Qty4, Double price4,
                             String product5, Integer Qty5, Double price5, Double total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("FULLNAME",fullName);
        cv.put("CONTACTNUMBER",contactNumber);
        cv.put("DATEUTANG", dateUtang);

        cv.put("PRODUCT1",product1);
        cv.put("QTY1", Qty1);
        cv.put("PRICE1", price1);

        cv.put("PRODUCT2",product2);
        cv.put("QTY2", Qty2);
        cv.put("PRICE2", price2);

        cv.put("PRODUCT3",product3);
        cv.put("QTY3", Qty3);
        cv.put("PRICE3", price3);

        cv.put("PRODUCT4",product4);
        cv.put("QTY4", Qty4);
        cv.put("PRICE4", price4);

        cv.put("PRODUCT5",product5);
        cv.put("QTY5", Qty5);
        cv.put("PRICE5", price5);

        cv.put("TOTAL", total);
        long result = db.insert(tbl_name, null, cv);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    ///////// METHOD GET / RETRIEVE DATA ////////
    public Cursor getData (String customerid){ //cursor pointer
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        if (customerid=="0"){
            res = db.rawQuery("SELECT * FROM " + tbl_name, null);
        }else {
            res = db.rawQuery("SELECT * FROM " + tbl_name + " WHERE CUSTOMERID=  '"+customerid+"'",null);
        }
        return  res;
    }

    ////////// METHOD DELETE DATA ///////////////
    public boolean deleteData(String customerid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tbl_name,"CUSTOMERID=?", new String[]{customerid});
        return true;
    }

     /////// METHOD UPDATE DATA //////////////////
    public  boolean updateData (String customerid, String fullName, String contactNumber, String dateUtang,
                                String product1, Integer Qty1, Double price1,
                                String product2, Integer Qty2, Double price2,
                                String product3, Integer Qty3, Double price3,
                                String product4, Integer Qty4, Double price4,
                                String product5, Integer Qty5, Double price5, Double total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CUSTOMERID", customerid);
        cv.put("FULLNAME",fullName);
        cv.put("CONTACTNUMBER",contactNumber);
        cv.put("DATEUTANG", dateUtang );
        cv.put("PRODUCT1",product1);
        cv.put("QTY1", Qty1);
        cv.put("PRICE1", price1);

        cv.put("PRODUCT2",product2);
        cv.put("QTY2", Qty2);
        cv.put("PRICE2", price2);

        cv.put("PRODUCT3",product3);
        cv.put("QTY3", Qty3);
        cv.put("PRICE3", price3);

        cv.put("PRODUCT4",product4);
        cv.put("QTY4", Qty4);
        cv.put("PRICE4", price4);

        cv.put("PRODUCT5",product5);
        cv.put("QTY5", Qty5);
        cv.put("PRICE5", price5);

        cv.put("TOTAL", total);
        db.update(tbl_name, cv, "CUSTOMERID = ?", new String[]{customerid});
        return true;
    }
}

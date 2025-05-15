package com.example.memento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

public class CustomAdapter extends ArrayAdapter<String> {

    Context c;
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
    Double [] total;
    LayoutInflater inflater;

    public CustomAdapter(@NonNull Context context, String[] customerid, String[] fullName, String[] contactNumber, String[] dateUtang,
                         String[] product1, Integer[] Qty1, Double[] price1,
                         String[] product2, Integer[] Qty2, Double[] price2,
                         String[] product3, Integer[] Qty3, Double[] price3,
                         String[] product4, Integer[] Qty4, Double[] price4,
                         String[] product5, Integer[] Qty5, Double[] price5, Double[] total) {
        super(context, R.layout.row, customerid);
            this.c = context;
            this.fullName = fullName;
            this.contactNumber = contactNumber;
            this.dateUtang = dateUtang;
            this.product1 = product1;
            this.Qty1 = Qty1;
            this.price1 = price1;
            this.product2 = product2;
            this.Qty2 = Qty2;
            this.price2 = price2;
            this.product3= product3;
            this.Qty3 = Qty3;
            this.price3 = price3;
            this.product4 = product4;
            this.Qty4 = Qty4;
            this.price4 = price4;
            this.product5 = product5;
            this.Qty5 = Qty5;
            this.price5 = price5;
            this.total = total;
    }

    public class ViewHolder{
        TextView flN;
        TextView cNumber;
        TextView Udate;
        TextView p1;
        TextView Q1;
        TextView pr1;
        TextView p2;
        TextView Q2;
        TextView pr2;
        TextView p3;
        TextView Q3;
        TextView pr3;
        TextView p4;
        TextView Q4;
        TextView pr4;
        TextView p5;
        TextView Q5;
        TextView pr5;
        TextView totalz;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        if(convertView==null) {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, null);
        }

        ViewHolder holder = new ViewHolder();

        holder.flN = (TextView) convertView.findViewById(R.id.tvRowFullName);
        holder.cNumber = (TextView) convertView.findViewById(R.id.tvRowContactNumber);
        holder.Udate = (TextView) convertView.findViewById(R.id.tvRowDate);
        holder.p1 = (TextView) convertView.findViewById(R.id.tvRowProduct1);
        holder.Q1 = (TextView) convertView.findViewById(R.id.tvRowQty1);
        holder.pr1 = (TextView) convertView.findViewById(R.id.tvRowPrice1);

        holder.p2 = (TextView) convertView.findViewById(R.id.tvRowProduct2);
        holder.Q2 = (TextView) convertView.findViewById(R.id.tvRowQty2);
        holder.pr2 = (TextView) convertView.findViewById(R.id.tvRowPrice2);

        holder.p3 = (TextView) convertView.findViewById(R.id.tvRowProduct3);
        holder.Q3 = (TextView) convertView.findViewById(R.id.tvRowQty3);
        holder.pr3 = (TextView) convertView.findViewById(R.id.tvRowPrice3);

        holder.p4 = (TextView) convertView.findViewById(R.id.tvRowProduct4);
        holder.Q4 = (TextView) convertView.findViewById(R.id.tvRowQty4);
        holder.pr4 = (TextView) convertView.findViewById(R.id.tvRowPrice4);

        holder.p5 = (TextView) convertView.findViewById(R.id.tvRowProduct5);
        holder.Q5 = (TextView) convertView.findViewById(R.id.tvRowQty5);
        holder.pr5 = (TextView) convertView.findViewById(R.id.tvRowPrice5);

        holder.totalz = (TextView) convertView.findViewById(R.id.tvRowTotal);

        holder.flN.setText(fullName[position]);
        holder.cNumber.setText(contactNumber[position]);
        holder.Udate.setText(dateUtang[position]);

        holder.p1.setText(product1[position]);
        holder.Q1.setText(String.valueOf(Qty1[position]));
        holder.pr1.setText(String.valueOf(price1[position]));

        holder.p2.setText(product2[position]);
        holder.Q2.setText(String.valueOf(Qty2[position]));
        holder.pr2.setText(String.valueOf(price2[position]));

        holder.p3.setText(product3[position]);
        holder.Q3.setText(String.valueOf(Qty3[position]));
        holder.pr3.setText(String.valueOf(price3[position]));

        holder.p4.setText(product4[position]);
        holder.Q4.setText(String.valueOf(Qty4[position]));
        holder.pr4.setText(String.valueOf(price4[position]));

        holder.p5.setText(product5[position]);
        holder.Q5.setText(String.valueOf(Qty5[position]));
        holder.pr5.setText(String.valueOf(price5[position]));

        holder.totalz.setText(String.valueOf(total[position]));
        return convertView;

    }
}

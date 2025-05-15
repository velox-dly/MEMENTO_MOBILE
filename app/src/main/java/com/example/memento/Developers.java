package com.example.memento;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class Developers extends AppCompatActivity {
    TextView tvAbout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //para sa back button lang to
        getSupportActionBar().setTitle("About System / Developers");

       tvAbout = findViewById(R.id.tvAbout);

       tvAbout.setText("\n\n\n🦑 ABOUT MEMENTO\n\n\n\nA Retail Store Debt Management System that is made especially for small businesses like Sari-Sari stores. Every small business owner might benefit from this " +
               "system's considerably more effective, precise, and   straightforward approach to handling consumer debts. The system's user has the ability to add or modify a debtor's record. The user specifies the essential items in the Sari-Sari store, and the system computes them automatically.\n\n\n" + "✔ BENEFITS\n\n\nThe benefit of this system is that it is more efficient, accurate, and simple, which may be " +
               "helpful for all small business owners when it comes to managing customer bills. Furthermore, this system is more \n" +
               "convenient because it requires minimal effort to enter quantity, price, and products that the customers wish to owe. \n" +
               "And for the time being, we limited it to only entering 5 products , which reduces the complexity of adding a lot of \n" +
               "stuff. And it is more detailed when it comes to customer information." + "\n\n\n🍣 DEVELOPERS\n\n\n 👾 Programmers: \n\n Maria Asenath Amor \n Min Andrei Saygan \n Diosa Lee Yap \n\n\n 📑 Documentation: \n\n Nikki Marie Rocacurva  \n John Rey Estabillo \n Imee De Grano \n\n\n A Final Requirement in IT 307: Elective 2 Mobile Development \n 2021-2022 \n\n Thank you!🙌🏼 \n\n\n");



    }
}
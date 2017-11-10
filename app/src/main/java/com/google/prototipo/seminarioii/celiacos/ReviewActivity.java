package com.google.prototipo.seminarioii.celiacos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.*;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class ReviewActivity extends AppCompatActivity {


    List<Review> reviews;
    TableLayout tl;
    Button b3;
    TableRow row;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        TableLayout tl = (TableLayout) findViewById(R.id.table1);

        Bundle parametros = this.getIntent().getExtras();

        if (parametros != null)
        {
            row = new TableRow(this);
            row.setId(10);
            row.setBackgroundColor(Color.WHITE);        // part1
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            String a = parametros.getString("texto");
            TextView label_hello = new TextView(this);
            label_hello.setId(20);
            label_hello.setText(a);
            label_hello.setTextColor(Color.BLUE);          // part2
            //     label_hello.setInputType(1);

            row.addView(label_hello);// add the column to the table row here

            TextView label_android = new TextView(this);    // part3
            label_android.setId(21);// define id that must be unique
            label_android.setText("ANDROID..!!"); // set the text for the header
            label_android.setTextColor(Color.BLUE); // set the color

            row.addView(label_android); // add the column to the table row here
            tl.addView(row);
        }
    }


    

    public void onClick (View v)
    {
        Intent intent = new Intent(this, TextoActivity.class);
        startActivity(intent);
    }
}

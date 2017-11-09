package com.google.prototipo.seminarioii.celiacos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class TextoActivity extends AppCompatActivity {


    EditText etl;
    RatingBar rb;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto);

        Button b3 = (Button)  findViewById(R.id.button3);
        etl = (EditText)  findViewById(R.id.editText2);
        rb =  (RatingBar)  findViewById(R.id.ratingBar);

        intent = new Intent(this, ReviewActivity.class);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etl.getText().toString() != null)
                {
                    intent.putExtra("texto",etl.getText().toString());

                    startActivity(intent);
                    finish();
                }

                finish();
            }
        });
    }








}

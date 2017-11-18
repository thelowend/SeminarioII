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

import com.google.prototipo.seminarioii.celiacos.reviews.entities.UserReview;

import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    List<UserReview> reviews;
    TableLayout tl;
    Button b3;
    TableRow row;
    TextView puntaje;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


    }

    public void onClick (View v)
    {
        Intent intent = AgregarReviewActivity.makeIntent(ReviewActivity.this);
        intent.putExtra("establecimiento", "Hotel campero");
        intent.putExtra("establecimientoId", "1");
        startActivity(intent);
    }
}

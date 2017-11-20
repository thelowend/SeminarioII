package com.google.prototipo.seminarioii.celiacos;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.widget.ListAdapter;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.prototipo.seminarioii.celiacos.reviews.AdapterReview;

import com.google.prototipo.seminarioii.celiacos.reviews.entities.UserReview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SuppressWarnings("unchecked")
public class ReviewActivity extends AppCompatActivity {


    TableLayout tl;
    Button b3;
    TableRow row;
    TextView puntaje;
    UserReview value;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    UserReview test;

    List<UserReview> reviews = new ArrayList<>();
    ListView userReviews;
    int establecimientoId = 1;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        userReviews = findViewById(R.id.userReviews);

        //Get datasnapshot at your "UserReviews/$establecimientoId" root node
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("userReviews").child(String.valueOf(establecimientoId));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot child : dataSnapshot.getChildren()) {
                    UserReview userReview = new UserReview();
                    userReview.setEstablecimientoId((String) child.child("establecimientoId").getValue());
                    userReview.setComentario((String) child.child("comentario").getValue());
                    userReview.setFecha((String) child.child("fecha").getValue());
                    userReview.setUserId((String) child.child("userId").getValue());
                    userReview.setPuntaje((String) child.child("puntaje").getValue());
                    reviews.add(userReview);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Nueva user review", "Failed to read value.", databaseError.toException());
            }
        });

        ListAdapter adapter = new AdapterReview(this, reviews);
        userReviews.setAdapter(adapter);

    }

    public void onClick (View v)
    {
        Intent intent = AgregarReviewActivity.makeIntent(ReviewActivity.this);
        intent.putExtra("establecimiento", "Hotel campero");
        intent.putExtra("establecimientoId", "1");
        startActivity(intent);
    }


   public void getReviews() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("userReviews");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(UserReview.class);
                //puntaje.setText(value.getComentario());

                Log.d("Nueva user review", "La nueva user review es: " + value);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Nueva user review", "Failed to read value.", databaseError.toException());
            }
        });


    }


}

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.prototipo.seminarioii.celiacos.reviews.entities.UserReview;

import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    List<UserReview> reviews;
    TableLayout tl;
    Button b3;
    TableRow row;
    TextView puntaje;
    UserReview value;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        puntaje = findViewById(R.id.puntaje);
        getReviews();

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
                puntaje.setText(value.getComentario().toString());

                Log.d("Nueva user review", "La nueva user review es: " + value);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Nueva user review", "Failed to read value.", databaseError.toException());
            }
        });


    }


}

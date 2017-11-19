package com.google.prototipo.seminarioii.celiacos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.prototipo.seminarioii.celiacos.reviews.AdapterReview;
import com.google.prototipo.seminarioii.celiacos.reviews.entities.UserReview;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    List<UserReview> reviews = new ArrayList<>();
    ListView userReviews;
    int establecimientoId = 0;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        userReviews = findViewById(R.id.userReviews);

        ListAdapter adapter = new AdapterReview(this, reviews);
        userReviews.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("userReviews").child(String.valueOf(establecimientoId));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reviews.add(dataSnapshot.getValue(UserReview.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Nueva user review", "Failed to read value.", databaseError.toException());
            }
        });

    }

    public void onClick (View v)
    {
        Intent intent = AgregarReviewActivity.makeIntent(ReviewActivity.this);
        intent.putExtra("establecimiento", "Hotel campero");
        intent.putExtra("establecimientoId", 1);
        startActivity(intent);
    }
}

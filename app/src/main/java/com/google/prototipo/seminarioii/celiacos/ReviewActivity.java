package com.google.prototipo.seminarioii.celiacos;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.prototipo.seminarioii.celiacos.establecimiento.entities.Establecimiento;
import com.google.prototipo.seminarioii.celiacos.reviews.AdapterReview;

import com.google.prototipo.seminarioii.celiacos.reviews.entities.UserReview;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ReviewActivity extends AppCompatActivity {

    RatingBar ratingGral;
    TextView nombreResto;

    int contador;
    float punt;

    List<UserReview> reviews = new ArrayList<>();
    ListView userReviews;

    Establecimiento establecimiento = null;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        try {
            Bundle bundle = getIntent().getExtras();
            establecimiento = (Establecimiento) (bundle != null ? bundle.get("establecimiento") : new Establecimiento("-0.43","0.43", "Hotel DAuria", "1"));
            if (establecimiento != null) {
                userReviews = findViewById(R.id.userReviews);
                ratingGral = findViewById(R.id.ratingGral);
                nombreResto = findViewById(R.id.nombreResto);

                nombreResto.setText(establecimiento.getNombre());

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("userReviews").child(String.valueOf(establecimiento.getIdEstablecimiento()));
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        reviews.clear();
                        for (final DataSnapshot child : dataSnapshot.getChildren()) {
                            UserReview userReview = new UserReview();
                            userReview.setEstablecimientoId((String) child.child("establecimientoId").getValue());
                            userReview.setComentario((String) child.child("comentario").getValue());
                            userReview.setFecha((String) child.child("fecha").getValue());
                            userReview.setUserId((String) child.child("userId").getValue());
                            userReview.setPuntaje((String) child.child("puntaje").getValue());
                            reviews.add(userReview);
                            contador = contador + 1;
                            punt = punt + Float.parseFloat(userReview.getPuntaje());
                        }
                        ratingGral.setRating(punt / contador);
                        ListAdapter adapter = new AdapterReview(ReviewActivity.this, reviews);
                        userReviews.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Nueva user review", "Failed to read value.", databaseError.toException());
                    }
                });

                ListAdapter adapter = new AdapterReview(this, reviews);
                userReviews.setAdapter(adapter);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick (View v)
    {
        Intent intent = AgregarReviewActivity.makeIntent(ReviewActivity.this);
        intent.putExtra("establecimiento", establecimiento.getNombre());
        intent.putExtra("establecimientoId", establecimiento.getIdEstablecimiento());
        startActivity(intent);
    }

}

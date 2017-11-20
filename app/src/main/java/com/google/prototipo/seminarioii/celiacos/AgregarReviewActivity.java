package com.google.prototipo.seminarioii.celiacos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.prototipo.seminarioii.celiacos.reviews.entities.ReviewQuestion;
import com.google.prototipo.seminarioii.celiacos.reviews.entities.UserReview;

import com.google.gson.Gson;
import com.google.prototipo.seminarioii.celiacos.reviews.entities.ReviewQuestion;
import com.google.prototipo.seminarioii.celiacos.reviews.entities.UserReview;
import com.google.prototipo.seminarioii.celiacos.reviews.entities.UserReviewQuestion;

import com.google.prototipo.seminarioii.celiacos.reviews.enums.TiposOpciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarReviewActivity extends AppCompatActivity {

    private String establecimientoId;
    private int contador;
    private TextView pregunta;
    private TextView paginas;

    private RatingBar ratingBar;

    private float promedio;

    private Button boton_finalizar;
    private Button botonSi;
    private Button botonNo;
    private Button boton_siguiente;

    private EditText comentario;
    private List<ReviewQuestion> reviews = new ArrayList<>();

    private List<UserReviewQuestion> userReviewQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_review);

        comentario = findViewById(R.id.texto_usuario);
        botonSi = findViewById(R.id.botonSi);
        botonNo = findViewById(R.id.botonNo);
        boton_siguiente = findViewById(R.id.boton_siguiente);

        paginas = findViewById(R.id.paginas);
        pregunta = findViewById(R.id.pregunta);
        ratingBar = findViewById(R.id.ratingBar);
        boton_finalizar = findViewById(R.id.boton_finalizar);
        TextView establecimiento = findViewById(R.id.establecimiento);

        if (getIntent().getExtras() != null) {
            establecimiento.setText((String) getIntent().getExtras().getSerializable("establecimiento"));
            establecimientoId = (String) getIntent().getExtras().getSerializable("establecimientoId");
        }
        comentario.setVisibility(View.INVISIBLE);
        boton_finalizar.setVisibility(View.INVISIBLE);

        getReviewsMock();
        contador = 0;
        paginas.setText(String.format("Pagina %s de %s", contador +1, reviews.size()));
        calcular();
    }

    private void calcular(){
        if(contador == reviews.size()) {
            boton_finalizar.setVisibility(View.VISIBLE);
            comentario.setVisibility(View.VISIBLE);

            boton_siguiente.setVisibility(View.INVISIBLE);
            pregunta.setVisibility(View.INVISIBLE);
            ratingBar.setVisibility(View.INVISIBLE);
            botonNo.setVisibility(View.INVISIBLE);
            botonSi.setVisibility(View.INVISIBLE);
            paginas.setVisibility(View.INVISIBLE);

            pregunta.setText(R.string.label_gracias_review);
            ratingBar.setRating(promedio);
        }else {

            pregunta.setText(reviews.get(contador).getEnunciado());
            if (reviews.get(contador).getTipo() == TiposOpciones.ESTRELLAS) {
                ratingBar.setVisibility(View.VISIBLE);
                botonNo.setVisibility(View.INVISIBLE);
                botonSi.setVisibility(View.INVISIBLE);
                boton_siguiente.setVisibility(View.VISIBLE);
            } else {
                ratingBar.setVisibility(View.INVISIBLE);
                boton_siguiente.setVisibility(View.INVISIBLE);
                botonNo.setVisibility(View.VISIBLE);
                botonSi.setVisibility(View.VISIBLE);
            }
            getPregunta().setText(reviews.get(contador).getEnunciado());
            ratingBar.setRating(0);

            contador = contador + 1;
            paginas.setText(String.format("Pagina %s de %s", contador, reviews.size()));
        }
    }

    public void finish(View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("userReviews");

        UserReview userReview = new UserReview();
        userReview.setComentario(comentario.getText().toString());
        userReview.setEstablecimientoId(establecimientoId);
        userReview.setPuntaje(String.valueOf(promedio / userReviewQuestions.size()));
        userReview.setUserId("0");
        userReview.setQuestionsReviews(userReviewQuestions);
        userReview.setFecha(Calendar.getInstance().getTime().toString());

        myRef.child(String.valueOf(userReview.getEstablecimientoId())).push().setValue(userReview);

        Log.i("Creando user review", userReview.toString());

        finish();
    }



    public void rateNo(View view){
        float puntaje;
        if(reviews.get(contador).getTipo() == TiposOpciones.SINO)
            puntaje = 1;
        else
            puntaje = 5;

        promedio += puntaje;

        userReviewQuestions.add(new UserReviewQuestion(reviews.get(contador-1), puntaje));
        calcular();
    }

    public void rateSi(View view){
        float puntaje;
        if(reviews.get(contador).getTipo() == TiposOpciones.SINO)
            puntaje = 5;
        else
            puntaje = 1;

        promedio += puntaje;

        userReviewQuestions.add(new UserReviewQuestion(reviews.get(contador-1), puntaje));
        calcular();
    }

    public void rateRatingBar(View view){
        float puntaje = getRatingBar().getRating();
        promedio += puntaje;

        userReviewQuestions.add(new UserReviewQuestion(reviews.get(contador-1), puntaje));
        calcular();
    }

    private void getReviewsMock(){
        Map<String, Integer> mapEstrella = new HashMap<>();
        mapEstrella.put("1",1);
        mapEstrella.put("2",2);
        mapEstrella.put("3",3);
        mapEstrella.put("4",4);
        mapEstrella.put("5",5);

        Map<String, Integer> mapSiNo = new HashMap<>();
        mapSiNo.put("No",1);
        mapSiNo.put("Si",5);

        Map<String, Integer> mapSiNoInvertido = new HashMap<>();
        mapSiNo.put("No",5);
        mapSiNo.put("Si",1);

        reviews.add(new ReviewQuestion("¿Tiene Contaminacion cruzada?", TiposOpciones.SINOINVERTIDO, mapSiNoInvertido));
        reviews.add(new ReviewQuestion("poseen variedad de platos para celiacos", TiposOpciones.SINO, mapSiNo));
        //reviews.add(new ReviewQuestion("Restaurante", TiposOpciones.ESTRELLAS, mapEstrella));
        //reviews.add(new ReviewQuestion("Comida", TiposOpciones.ESTRELLAS, mapEstrella));
        reviews.add(new ReviewQuestion("Atencion", TiposOpciones.ESTRELLAS, mapEstrella));
    }

    public TextView getPregunta() {
        return pregunta;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, AgregarReviewActivity.class);
    }
}

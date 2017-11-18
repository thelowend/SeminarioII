package com.google.prototipo.seminarioii.celiacos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.prototipo.seminarioii.celiacos.shareReview.Reviews;
import com.google.prototipo.seminarioii.celiacos.shareReview.TiposOpciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextoActivity extends AppCompatActivity {

    private int contador;
    private TextView pregunta;
    private RatingBar ratingBar;

    private Intent intent;
    private float promedio;

    private Button botonSi;
    private Button botonNo;

    private List<Reviews> reviews = new ArrayList<Reviews>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto);
        reviewMock();


        botonSi = findViewById(R.id.botonSi);
        botonNo = findViewById(R.id.botonNo);

        pregunta = findViewById(R.id.pregunta);
        ratingBar = findViewById(R.id.ratingBar);
        intent = new Intent(this, ReviewActivity.class);

        if(!reviews.isEmpty()) {
            contador = 0;
            asd();
        }
    }

    private void asd(){
        pregunta.setText(reviews.get(contador).getEnunciado());
        if(reviews.get(contador).getTipo() == TiposOpciones.ESTRELLAS) {
            ratingBar.setVisibility(View.VISIBLE);
            botonNo.setVisibility(View.INVISIBLE);
            botonSi.setVisibility(View.INVISIBLE);
        }else {
            ratingBar.setVisibility(View.INVISIBLE);
            botonNo.setVisibility(View.VISIBLE);
            botonSi.setVisibility(View.VISIBLE);
        }
        getPregunta().setText(reviews.get(contador).getEnunciado());
    }

    public void finish(){
        //Aca obtenemos todas los rates que va cargando y el comentario final para pasarlo
        finish();
    }

    public void rate(View view){
        contador = contador + 1;
        asd();
    }

    private void reviewMock() {
        Map<String, Integer> mapEstrella = new HashMap<>();
        mapEstrella.put("1",1);
        mapEstrella.put("2",2);
        mapEstrella.put("3",3);
        mapEstrella.put("4",4);
        mapEstrella.put("5",5);

        Map<String, Integer> mapSiNo = new HashMap<>();
        mapEstrella.put("No",1);
        mapEstrella.put("Si",5);

        reviews.add(new Reviews("Tiene Contaminacion cruzada?", TiposOpciones.SINO, mapEstrella));
        reviews.add(new Reviews("oseen variedad de platos para celiacos", TiposOpciones.SINO, mapEstrella));
        reviews.add(new Reviews("Restaurante", TiposOpciones.ESTRELLAS, mapEstrella));
        reviews.add(new Reviews("Comida", TiposOpciones.ESTRELLAS, mapEstrella));
        reviews.add(new Reviews("Atencion", TiposOpciones.ESTRELLAS, mapEstrella));
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public TextView getPregunta() {
        return pregunta;
    }

    public void setPregunta(TextView pregunta) {
        this.pregunta = pregunta;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }

    @Override
    public Intent getIntent() {
        return intent;
    }

    @Override
    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public Button getBotonSi() {
        return botonSi;
    }

    public void setBotonSi(Button botonSi) {
        this.botonSi = botonSi;
    }

    public Button getBotonNo() {
        return botonNo;
    }

    public void setBotonNo(Button botonNo) {
        this.botonNo = botonNo;
    }
}

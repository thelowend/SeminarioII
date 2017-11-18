package com.google.prototipo.seminarioii.celiacos.reviews.entities;

import java.util.Map;

public class UserReview {

    private int establecimientoId;
    private Map<ReviewQuestion, Float> reviews;
    private String comentario;
    private int userId;

    public UserReview() {
    }

    public UserReview(int establecimientoId, Map<ReviewQuestion, Float> reviews, String comentario, int userId) {
        this.establecimientoId = establecimientoId;
        this.reviews = reviews;
        this.comentario = comentario;
        this.userId = userId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Map<ReviewQuestion, Float> getReviews() {
        return reviews;
    }

    public void setReviews(Map<ReviewQuestion, Float> reviews) {
        this.reviews = reviews;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}



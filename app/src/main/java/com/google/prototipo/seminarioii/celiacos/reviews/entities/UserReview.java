package com.google.prototipo.seminarioii.celiacos.reviews.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserReview {

    private int establecimientoId;
    private List<UserReviewQuestion> questionsReviews;
    private String comentario;
    private int userId;
    private Date fecha;

    public UserReview() {
    }

    public UserReview(int establecimientoId, List<UserReviewQuestion> questionsReviews, String comentario, int userId, Date fecha) {
        this.establecimientoId = establecimientoId;
        this.questionsReviews = questionsReviews;
        this.comentario = comentario;
        this.userId = userId;
        this.fecha = fecha;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<UserReviewQuestion> getQuestionsReviews() {
        return questionsReviews;
    }

    public void setQuestionsReviews(List<UserReviewQuestion> questionsReviews) {
        this.questionsReviews = questionsReviews;
    }
}



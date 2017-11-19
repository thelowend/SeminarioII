package com.google.prototipo.seminarioii.celiacos.reviews.entities;

public class UserReviewQuestion {

    public ReviewQuestion reviewQuestion;
    public float puntaje;

    public UserReviewQuestion() {
    }

    public UserReviewQuestion(ReviewQuestion reviewQuestion, float puntaje) {
        this.reviewQuestion = reviewQuestion;
        this.puntaje = puntaje;
    }

    public ReviewQuestion getReviewQuestion() {
        return reviewQuestion;
    }

    public void setReviewQuestion(ReviewQuestion reviewQuestion) {
        this.reviewQuestion = reviewQuestion;
    }

    public float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(float puntaje) {
        this.puntaje = puntaje;
    }
}

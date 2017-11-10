package com.google.prototipo.seminarioii.celiacos.shareReview;


import java.util.Map;

public class Reviews {

    private String enunciado;
    private TiposOpciones tipo;
    private Map<String, Integer> valores;

    public Reviews() {
    }

    public Reviews(String enunciado, TiposOpciones tipo, Map<String, Integer> valores) {
        this.enunciado = enunciado;
        this.tipo = tipo;
        this.valores = valores;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public TiposOpciones getTipo() {
        return tipo;
    }

    public void setTipo(TiposOpciones tipo) {
        this.tipo = tipo;
    }

    public Map<String, Integer> getValores() {
        return valores;
    }

    public void setValores(Map<String, Integer> valores) {
        this.valores = valores;
    }
}



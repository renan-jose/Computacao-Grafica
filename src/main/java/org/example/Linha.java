package org.example;

public class Linha{

    private Ponto inicio;
    private Ponto fim;

    public Linha(Ponto inicio, Ponto fim){

        this.inicio = inicio;
        this.fim = fim;

    }

    public Ponto getInicio(){

        return inicio;

    }

    public Ponto getFim(){

        return fim;

    }

    public void setInicio(Ponto inicio){

        this.inicio = inicio;

    }

    public void setFim(Ponto fim){

        this.fim = fim;

    }

    public float calcularInclinacao(){

        float dx = fim.getX() - inicio.getX();
        float dy = fim.getY() - inicio.getY();

        return dy / dx;

    }

}

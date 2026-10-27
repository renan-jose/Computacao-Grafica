package org.example;

public class Ponto{

    private float x, y;

    public Ponto(float x, float y){

        this.x = x;
        this.y = y;

    }

    public float getX(){

        return x;

    }

    public float getY(){

        return y;

    }

    public void setX(float x){

        this.x = x;

    }

    public void setY(float y){

        this.y = y;

    }

    public float calcularDistancia(float x, float y){

        return (float) (Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));

    }

}

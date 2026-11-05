package org.example;

public class Ponto{

    private float x, y, z;

    public Ponto(float x, float y, float z){

        this.x = x;
        this.y = y;
        this.z = z;

    }

    public float getX(){

        return x;

    }

    public float getY(){

        return y;

    }

    public float getZ(){

        return z;

    }

    public void setX(float x){

        this.x = x;

    }

    public void setY(float y){

        this.y = y;

    }

    public void setZ(float z){

        this.z = z;

    }

    public float calcularDistancia(float x, float y, float z){

        return (float) (Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - y, 2));

    }

}

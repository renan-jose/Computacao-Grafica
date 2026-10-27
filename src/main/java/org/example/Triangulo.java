package org.example;

public class Triangulo extends Poligono{

    public Triangulo(){

        super();

    }

    public void adicionarPonto(Ponto p){
        if(getPontoPoligono().size() < 3){
            adicionarPontoPoligono(p);
        }
    }

    public Ponto getPonto(int indice){

        return getPontoPoligono().get(indice);

    }

    public void resetar(){

        getPontoPoligono().clear();

    }

    public boolean estaCompleto(){

        if(getPontoPoligono().size() == 3){
            return true;
        }

        return false;

    }

}

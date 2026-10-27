package org.example;

import java.util.ArrayList;
import java.util.List;

public class Poligono{

    private List<Ponto> pontos;

    public Poligono(){

        this.pontos = new ArrayList<>();

    }

    public void adicionarPontoPoligono(Ponto p){

        this.pontos.add(p);

    }

    public List<Ponto> getPontoPoligono(){

        return this.pontos;

    }

}

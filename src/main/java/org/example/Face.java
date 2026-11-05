package org.example;

import java.util.ArrayList;
import java.util.List;

public class Face{

    private List<Integer> indicesVertices;

    public Face(){

        this.indicesVertices = new ArrayList<>();

    }

    public void adicionarIndice(int indice){

        this.indicesVertices.add(indice);

    }

    public List<Integer> getIndicesVertices(){

        return indicesVertices;

    }

    public int getQuantidadeVertices(){

        return indicesVertices.size();

    }

}

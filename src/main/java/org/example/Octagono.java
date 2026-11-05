package org.example;

import java.util.ArrayList;
import java.util.List;

public class Octagono{

    private List<Ponto> pontos;
    private float raio;

    public Octagono(float raio){

        this.raio = raio;
        this.pontos = new ArrayList<>();

    }

    public void formarOctagono(Ponto pontoClique){

        pontos.clear();

        float passo = (float) (2 * Math.PI / 8.0), angulo, x, y, z;
        int i;
        Ponto ponto;

        for(i = 0; i < 8; i++){
            angulo = i * passo;
            x = (float) (pontoClique.getX() + raio * Math.cos(angulo));
            y = (float) (pontoClique.getY() + raio * Math.sin(angulo));
            z = 0;
            ponto = new Ponto(x, y, z);
            pontos.add(ponto);
        }


    }

    public List<Ponto> getPontos(){

        return this.pontos;

    }

}

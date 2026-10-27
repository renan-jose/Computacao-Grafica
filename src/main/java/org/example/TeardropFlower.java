package org.example;

import java.util.ArrayList;
import java.util.List;

public class TeardropFlower{

    private List<Teardrop> teardrops;

    public TeardropFlower(){

        this.teardrops = new ArrayList<>();

    }

    public void formarFiguraA(Ponto centro){

        teardrops.clear();

        int totalTeardrops = 8, i;
        float passoAngulo = (float) (360 / totalTeardrops), orientacao;
        Teardrop teardrop;

        for(i = 0; i < totalTeardrops; i++){
            orientacao = i * passoAngulo;
            teardrop = new Teardrop(50, 30, orientacao, 100);
            teardrop.formarTeardrop(centro);
            teardrops.add(teardrop);

        }

    }

    public void formarFiguraB(Ponto centro){

        teardrops.clear();

        Octagono octagono = new Octagono(50);
        octagono.formarOctagono(centro);

        List<Ponto> pontosOctagono = octagono.getPontos();
        Ponto ponto;
        Teardrop teardrop;

        float passo = 360 / 8, orientacao, rotacao = 90;
        int i;

        for(i = 0; i < pontosOctagono.size(); i++){
            ponto = pontosOctagono.get(i);
            orientacao = i * passo + rotacao;
            teardrop = new Teardrop(50, 30, orientacao, 100);
            teardrop.formarTeardrop(ponto);
            teardrops.add(teardrop);
        }

    }

    public List<Teardrop> getTeardrops(){

        return this.teardrops;

    }

}

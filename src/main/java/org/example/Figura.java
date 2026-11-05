package org.example;

import java.util.ArrayList;
import java.util.List;

public class Figura{

    private List<Ponto> pontos;
    private Ponto pontoCorrente;

    public Figura(){

        this.pontos = new ArrayList<>();

    }

    public List<Ponto> getPontos(){

        return this.pontos;

    }

    public Ponto getPontoCorrente(){

        return pontoCorrente;

    }

    public void setPontoCorrente(Ponto pontoCorrente){

        this.pontoCorrente = pontoCorrente;

    }

    public void adicionarVertice(Ponto p){

        this.pontos.add(p);

    }

    public void removerVertice(float x, float y, float z){

        int removido = -1, i = 0;
        float menorDistancia = Float.MAX_VALUE;

        if(pontos.isEmpty() != true){
            for(i = 0; i < pontos.size(); i++){
                if(pontos.get(i).calcularDistancia(x, y, z) < menorDistancia){
                    menorDistancia = pontos.get(i).calcularDistancia(x, y, z);
                    removido = i;
                }
            }
        }

        if(removido != -1){
            pontos.remove(removido);
        }

    }

    public void selecionarMaisProximo(float x, float y, float z){

        int tonho = -1, i = 0;
        float menorDistancia = Float.MAX_VALUE;

        if(pontos.isEmpty() != true){
            for(i = 0; i < pontos.size(); i++){
                if(pontos.get(i).calcularDistancia(x, y, z) < menorDistancia){
                    menorDistancia = pontos.get(i).calcularDistancia(x, y, z);
                    tonho = i;
                }
            }
        }

        if(tonho != -1){
            this.pontoCorrente = pontos.get(tonho);
        }

    }

}

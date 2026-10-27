package org.example;

import java.util.ArrayList;
import java.util.List;

public class Transformacoes2D{

    private List<Ponto> pontos;

    public Transformacoes2D(){

        this.pontos = new ArrayList<>();

    }

    public List<Ponto> getPontos(){

        return this.pontos;

    }

    public void adicionarVertice(Ponto p){

        this.pontos.add(p);

    }

    public float[] calcularCM(){

        if(pontos.isEmpty() == true){

            return new float[]{0, 0};

        }

        float minX = Float.MAX_VALUE, maxX = -Float.MAX_VALUE;
        float minY = Float.MAX_VALUE, maxY = -Float.MAX_VALUE;

        for(Ponto p : pontos){
            if(p.getX() < minX){
                minX = p.getX();
            }
            if(p.getX() > maxX){
                maxX = p.getX();
            }
            if(p.getY() < minY){
                minY = p.getY();
            }
            if(p.getY() > maxY){
                maxY = p.getY();
            }
        }

        float centroX = (minX + maxX) / 2.0f;
        float centroY = (minY + maxY) / 2.0f;

        return new float[]{centroX, centroY};

    }

    public void transladar(float deltaX, float deltaY){

        for(Ponto p : pontos){
            p.setX(p.getX() + deltaX);
            p.setY(p.getY() + deltaY);
        }

    }

    public void escala(float E){

        if(pontos.isEmpty() == true){

            return;

        }

        float[] cm = calcularCM();
        float cmX = cm[0], cmY = cm[1];
        float xLinha, yLinha, novoX, novoY;

        for(Ponto p : pontos){
            // transladação p/ origem
            xLinha = p.getX() - cmX;
            yLinha = p.getY() - cmY;

            novoX = (xLinha * E) + cmX;
            novoY = (yLinha * E) + cmY;

            p.setX(novoX);
            p.setY(novoY);

        }

    }

    public void rotacionar(float anguloGraus){

        if(pontos.isEmpty() == true){

            return;

        }

        float[] cm = calcularCM();
        float cmX = cm[0], cmY = cm[1];
        float xLinha, yLinha, novoX, novoY;
        float theta = (float) Math.toRadians(anguloGraus);
        float cosseno = (float) Math.cos(theta), seno = (float) Math.sin(theta);

        for(Ponto p : pontos){

            // transladação p/ origem
            xLinha = p.getX() - cmX;
            yLinha = p.getY() - cmY;

            novoX = (xLinha * cosseno - yLinha * seno);
            novoY = (xLinha * seno + yLinha * cosseno);

            p.setX(novoX + cmX);
            p.setY(novoY + cmY);

        }

    }

}

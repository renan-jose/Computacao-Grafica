package org.example;

import java.util.ArrayList;
import java.util.List;

public class Transformacoes {

    private List<Ponto> pontos;

    public Transformacoes(){

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

            return new float[]{0, 0, 0};

        }

        float minX = Float.MAX_VALUE, maxX = -Float.MAX_VALUE;
        float minY = Float.MAX_VALUE, maxY = -Float.MAX_VALUE;
        float minZ = Float.MAX_VALUE, maxZ = -Float.MAX_VALUE;

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
            if(p.getZ() < minZ){
                minZ = p.getZ();
            }
            if(p.getZ() > maxZ){
                maxZ = p.getZ();
            }
        }

        float centroX = (minX + maxX) / 2.0f;
        float centroY = (minY + maxY) / 2.0f;
        float centroZ = (minZ + maxZ) / 2.0f;

        return new float[]{centroX, centroY, centroZ};

    }

    public void transladar(float deltaX, float deltaY, float deltaZ){

        for(Ponto p : pontos){
            p.setX(p.getX() + deltaX);
            p.setY(p.getY() + deltaY);
            p.setZ(p.getZ() + deltaZ);
        }

    }

    public void escala(float E){

        if(pontos.isEmpty() == true){

            return;

        }

        float[] cm = calcularCM();
        float cmX = cm[0], cmY = cm[1], cmZ = cm[2];
        float xLinha, yLinha, zLinha, novoX, novoY, novoZ;

        for(Ponto p : pontos){
            // transladação p/ origem
            xLinha = p.getX() - cmX;
            yLinha = p.getY() - cmY;
            zLinha = p.getZ() - cmZ;

            novoX = (xLinha * E) + cmX;
            novoY = (yLinha * E) + cmY;
            novoZ = (zLinha * E) + cmZ;

            p.setX(novoX);
            p.setY(novoY);
            p.setZ(novoZ);

        }

    }

    public void rotacionar(float anguloGraus, char eixo){

        if(pontos.isEmpty() == true){

            return;

        }

        float[] cm = calcularCM();
        float cmX = cm[0], cmY = cm[1], cmZ = cm[2];
        float xLinha, yLinha, zLinha, novoX, novoY, novoZ;
        float theta = (float) Math.toRadians(anguloGraus);
        float cosseno = (float) Math.cos(theta), seno = (float) Math.sin(theta);
        char e = Character.toLowerCase(eixo);

        for(Ponto p : pontos){

            // transladação p/ origem
            xLinha = p.getX() - cmX;
            yLinha = p.getY() - cmY;
            zLinha = p.getZ() - cmZ;

            switch(e){
                case 'x':
                    novoX = xLinha;
                    novoY = yLinha * cosseno - zLinha * seno;
                    novoZ = yLinha * seno + zLinha * cosseno;
                    break;

                case 'y':
                    novoX = zLinha * seno + xLinha * cosseno;
                    novoY = yLinha;
                    novoZ = zLinha * cosseno - xLinha * seno;
                    break;

                case 'z':
                    novoX = xLinha * cosseno - yLinha * seno;
                    novoY = xLinha * seno + yLinha * cosseno;
                    novoZ = zLinha;
                    break;

                default:
                    novoX = xLinha * cosseno - yLinha * seno;
                    novoY = xLinha * seno + yLinha * cosseno;
                    novoZ = zLinha;
                    return;
            }

            p.setX(novoX + cmX);
            p.setY(novoY + cmY);
            p.setZ(novoZ + cmZ);

        }

    }

}

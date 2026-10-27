package org.example;

import java.util.ArrayList;
import java.util.List;

public class Teardrop{

   private float raio, anguloV, orientacao;
   private int n;
   private List<Ponto> pontos;

   public Teardrop(float raio, float anguloV, float orientacao, int n){

       this.pontos = new ArrayList<>();
       this.raio = raio;
       this.anguloV = (float) Math.toRadians(anguloV); // ponta
       this.orientacao = (float) Math.toRadians(orientacao); // direção da ponta
       this.n = n;

   }

   public void formarTeardrop(Ponto pontoClique){

       pontos.clear();

       float alfa = anguloV / 2.0f;

       pontos.add(pontoClique);

       float d = (float) (raio / Math.sin(alfa));
       float xCentro = (float) (pontoClique.getX() - d * Math.cos(orientacao));
       float yCentro = (float) (pontoClique.getY() - d * Math.sin(orientacao));

       // orientacao girando
       float thetaInicio = (float) (orientacao + (Math.PI / 2));
       float thetaFim = (float) (orientacao + (3 * Math.PI / 2));
       float passo = (thetaFim - thetaInicio) / n; // distribuição uniforme dos pontos no arco

       int i;
       float theta, xArco, yArco;
       Ponto pontoArco;

       for(i = 0; i <= n; i++){
           theta = thetaInicio + (i * passo);
           xArco = (float) (xCentro + raio * Math.cos(theta));
           yArco = (float) (yCentro + raio * Math.sin(theta));
           pontoArco = new Ponto(xArco, yArco);
           pontos.add(pontoArco);
       }

   }

   public List<Ponto> getPontos(){

       return this.pontos;

   }

}

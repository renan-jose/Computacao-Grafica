package org.example;

import java.util.List;

public class Deformacao{

    private Triangulo V;
    private Triangulo W;

    public Deformacao(Triangulo V, Triangulo W){

        this.V = V;
        this.W = W;

    }

    public void deformar(List<Ponto> pontos){

        Ponto v1 = V.getPonto(0), v2 = V.getPonto(1), v3 = V.getPonto(2);
        Ponto w1 = W.getPonto(0), w2 = W.getPonto(1), w3 = W.getPonto(2);

        float x1 = v1.getX(), y1 = v1.getY();
        float x2 = v2.getX(), y2 = v2.getY();
        float x3 = v3.getX(), y3 = v3.getY();

        float u1 = w1.getX(), q1 = w1.getY();
        float u2 = w2.getX(), q2 = w2.getY();
        float u3 = w3.getX(), q3 = w3.getY();

        // W = T * V
        // T = W * V^-1

        float determinante = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);

        float a = (u1 * (y2 - y3) + u2 * (y3 - y1) + u3 * (y1 - y2)) / determinante;
        float b = (u1 * (x3 - x2) + u2 * (x1 - x3) + u3 * (x2 - x1)) / determinante;
        float c = (u1 * (x2 * y3 - x3 * y2) + u2 * (x3 * y1 - x1 * y3) + u3 * (x1 * y2 - x2 * y1)) / determinante;

        float d = (q1 * (y2 - y3) + q2 * (y3 - y1) + q3 * (y1 - y2)) / determinante;
        float e = (q1 * (x3 - x2) + q2 * (x1 - x3) + q3 * (x2 - x1)) / determinante;
        float f = (q1 * (x2 * y3 - x3 * y2) + q2 * (x3 * y1 - x1 * y3) + q3 * (x1 * y2 - x2 * y1)) / determinante;

        for(Ponto p : pontos){
            float x = p.getX();
            float y = p.getY();

            float novoX = a*x + b*y + c;
            float novoY = d*x + e*y + f;

            p.setX(novoX);
            p.setY(novoY);

        }

    }

}

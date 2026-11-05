package org.example.mouse;

import org.example.BSpline;
import org.example.Deformacao;
import org.example.Ponto;
import org.example.Triangulo;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAtividade06_2 extends MouseAdapter{

    private final Triangulo V;
    private final Triangulo W;
    private final BSpline bSpline;
    private final JPanel painel;

    public MouseAtividade06_2(Triangulo V, Triangulo W, BSpline bSpline, JPanel painel){

        this.V = V;
        this.W = W;
        this.bSpline = bSpline;
        this.painel = painel;

    }

    @Override
    public void mouseClicked(MouseEvent arg0){

        if(arg0.getButton() == MouseEvent.BUTTON1){
            Ponto p = new Ponto(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY(), 0);

            if(V.estaCompleto() == false){
                V.adicionarPonto(p);
            }else if(W.estaCompleto() == false){
                W.adicionarPonto(p);

                if(W.estaCompleto() == true){
                    Deformacao deformacao = new Deformacao(V, W);
                    deformacao.deformar(bSpline.getPontos());
                }
            }
        }

        painel.repaint();

    }

}

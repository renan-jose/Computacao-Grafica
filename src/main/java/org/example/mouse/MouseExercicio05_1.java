package org.example.mouse;

import org.example.BSpline;
import org.example.Ponto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseExercicio05_1 extends MouseAdapter{

    private final BSpline bSpline;
    private final JPanel painel;

    public MouseExercicio05_1(BSpline bSpline, JPanel painel){

        this.bSpline = bSpline;
        this.painel = painel;

    }

    @Override
    public void mouseClicked(MouseEvent arg0){

        // e.getButton() retorna um int
        if(arg0.getButton() == MouseEvent.BUTTON1){
            // ponto criado
            Ponto p = new Ponto(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY(), 0);
            // figura tem um ponto
            bSpline.adicionarVertice(p);
            bSpline.setPontoCorrente(p);
        }else{
            if(arg0.getButton() == MouseEvent.BUTTON3){
                bSpline.removerVertice(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY(), 0);
                bSpline.selecionarMaisProximo(arg0.getX(), arg0.getY(), 0);
            }
        }

        painel.repaint();

    }

}

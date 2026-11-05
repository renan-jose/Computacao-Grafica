package org.example.mouse;

import org.example.Ponto;
import org.example.Teardrop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseExercicio04_1 extends MouseAdapter{

    private Teardrop teardrop;
    private JPanel painel;

    public MouseExercicio04_1(Teardrop teardrop, JPanel painel){

        this.teardrop = teardrop;
        this.painel = painel;

    }

    @Override
    public void mousePressed(MouseEvent e){

        if(e.getButton() == MouseEvent.BUTTON1){
            Ponto p = new Ponto(e.getX(), e.getComponent().getHeight() - e.getY(), 0);
            teardrop.formarTeardrop(p);
        }

        painel.repaint();

    }

}

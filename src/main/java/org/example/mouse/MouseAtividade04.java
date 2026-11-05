package org.example.mouse;

import org.example.Ponto;
import org.example.TeardropFlower;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAtividade04 extends MouseAdapter{

    private TeardropFlower teardropFlower;
    private JPanel painel;

    public MouseAtividade04(TeardropFlower teardropFlower, JPanel painel){

        this.teardropFlower = teardropFlower;
        this.painel = painel;

    }

    @Override
    public void mousePressed(MouseEvent e) {

        Ponto p = new Ponto(e.getX(), e.getComponent().getHeight() - e.getY(), 0);

        if(e.getButton() == MouseEvent.BUTTON1){
            teardropFlower.formarFiguraA(p);
        }else{
            if(e.getButton() == MouseEvent.BUTTON3){
                teardropFlower.formarFiguraB(p);
            }
        }

        painel.repaint();

    }

}

package org.example.key;

import org.example.Figura;
import org.example.Ponto;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyExercicio02_2 extends KeyAdapter{

    private Figura figura;
    private JPanel painel;
    private float passo = 2.0f;

    public KeyExercicio02_2(Figura figura, JPanel painel){

        this.figura = figura;
        this.painel = painel;

    }

    @Override
    public void keyPressed(KeyEvent e){

        Ponto corrente = figura.getPontoCorrente();

        if(corrente ==  null){
            return;
        }

        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                corrente.setX(corrente.getX() - passo);
                break;

            case KeyEvent.VK_RIGHT:
                corrente.setX(corrente.getX() + passo);
                break;

            case KeyEvent.VK_UP:
                corrente.setY(corrente.getY() + passo);
                break;

            case KeyEvent.VK_DOWN:
                corrente.setY(corrente.getY() - passo);
                break;

            default:
                break;
        }

        painel.repaint();

    }

}

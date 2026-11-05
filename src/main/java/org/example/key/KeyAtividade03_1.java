package org.example.key;

import org.example.Dino;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAtividade03_1 extends KeyAdapter{

    private Dino dino;
    private JPanel painel;

    public KeyAtividade03_1(Dino dino, JPanel painel){

        this.dino = dino;
        this.painel = painel;

    }

    @Override
    public void keyTyped(KeyEvent e){

        char key = e.getKeyChar();

        if(key == 'o' || key == 'O'){
            dino.carregarDino("dino.dat");
            painel.repaint();
            System.out.println("Carregou");
        }

    }

}

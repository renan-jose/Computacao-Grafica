package org.example.key;

import org.example.BSpline;
import org.example.Triangulo;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAtividade06_2 extends KeyAdapter{

    private BSpline bSpline;
    private Triangulo V;
    private Triangulo W;
    private JPanel painel;

    public KeyAtividade06_2(BSpline bSpline, Triangulo V, Triangulo W, JPanel painel){

        this.bSpline = bSpline;
        this.V = V;
        this.W = W;
        this.painel = painel;

    }

    @Override
    public void keyTyped(KeyEvent e){

        char key = e.getKeyChar();

        if(key == 'o' || key == 'O'){
            bSpline.carregarBSpline("bspline.dat");
        }

        if(key == 'd' || key == 'D'){
            V.resetar();
            W.resetar();
            System.out.println("Forme um triangulo sobre a imagem com 3 pontos e depois outro em outro lugar");
        }

        painel.repaint();

    }

}

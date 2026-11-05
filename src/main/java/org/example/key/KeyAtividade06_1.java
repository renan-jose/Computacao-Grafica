package org.example.key;

import org.example.BSpline;
import org.example.Ponto;
import org.example.Transformacoes;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAtividade06_1 extends KeyAdapter{

    private BSpline bSpline;
    private Transformacoes transformacoes;
    private float passoTranslacao = 5;
    private float anguloRotacao = 5;
    private float Emais = 1.05f;
    private float Emenos = 0.95f;
    private JPanel painel;

    public KeyAtividade06_1(BSpline bSpline, JPanel painel){

        this.bSpline = bSpline;
        this.painel = painel;

    }

    @Override
    public void keyPressed(KeyEvent e){

        Transformacoes transformacao = new Transformacoes();

        for(Ponto p : bSpline.getPontos()){
            transformacao.adicionarVertice(p);
        }

        switch(e.getKeyCode()){

            case KeyEvent.VK_A:
                transformacao.transladar(-passoTranslacao, 0, 0);
                break;

            case KeyEvent.VK_D:
                transformacao.transladar(passoTranslacao, 0, 0);
                break;

            case KeyEvent.VK_W:
                transformacao.transladar(0, passoTranslacao, 0);
                break;

            case KeyEvent.VK_S:
                transformacao.transladar(0, -passoTranslacao, 0);
                break;

        }

        painel.repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

        char key = e.getKeyChar();

        Transformacoes transformacao = new Transformacoes();

        for(Ponto p : bSpline.getPontos()){
            transformacao.adicionarVertice(p);
        }

        if(key == 'r'){
            transformacao.rotacionar(-anguloRotacao, 'z');
        }else{
            if(key == 'R'){
                transformacao.rotacionar(anguloRotacao, 'z');
            }
        }

        if(key == 'e'){
            transformacao.escala(Emais);
        }else{
            if(key == 'E'){
                transformacao.escala(Emenos);
            }
        }

        if(key == 'g' || key == 'G'){
            bSpline.salvarBSpline("bspline.dat");
        }else{
            if(key == 'o' || key == 'O'){
                bSpline.carregarBSpline("bspline.dat");
            }
        }

        painel.repaint();

    }

}

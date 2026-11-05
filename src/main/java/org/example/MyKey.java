package org.example;

import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKey extends KeyAdapter{

    private Dino dino;
    private Figura figura;
    private JPanel painel;
    private float passo = 2.0f;
    private BSpline bSpline;
    private Transformacoes transformacoes;
    private float passoTranslacao = 5;
    private float anguloRotacao = 5;
    private float Emais = 1.05f;
    private float Emenos = 0.95f;
    private Triangulo V;
    private Triangulo W;

    public MyKey(Figura figura, JPanel painel, Dino dino, BSpline bSpline, Transformacoes transformacoes, Triangulo V, Triangulo W){

        this.figura = figura;
        this.painel = painel;
        this.dino = dino;
        this.bSpline = bSpline;
        this.transformacoes = transformacoes;
        this.V = V;
        this.W = W;

    }

    /*
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

    @Override
    public void keyTyped(KeyEvent e){

        char key = e.getKeyChar();

        if(key == 'o' || key == 'O'){
            dino.carregarDino("dino.dat");
            painel.repaint();
            System.out.println("Carregou");
        }

    }
     */

    //////////////////////////////////////////////

    // atividade06_1 ////////////////////////////////////////////

    /*
    @Override
    public void keyPressed(KeyEvent e){

        Transformacoes2D transformacao = new Transformacoes2D();

        for(Ponto p : bSpline.getPontos()){
            transformacao.adicionarVertice(p);
        }

        switch(e.getKeyCode()){

            case KeyEvent.VK_A:
                transformacao.transladar(-passoTranslacao, 0);
                break;

            case KeyEvent.VK_D:
                transformacao.transladar(passoTranslacao, 0);
                break;

            case KeyEvent.VK_W:
                transformacao.transladar(0, passoTranslacao);
                break;

            case KeyEvent.VK_S:
                transformacao.transladar(0, -passoTranslacao);
                break;

        }

        painel.repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

        char key = e.getKeyChar();

        Transformacoes2D transformacao = new Transformacoes2D();

        for(Ponto p : bSpline.getPontos()){
            transformacao.adicionarVertice(p);
        }

        if (key == 'r') {
            transformacao.rotacionar(-anguloRotacao);
        } else {
            if (key == 'R') {
                transformacao.rotacionar(anguloRotacao);
            }
        }

        if (key == 'e') {
            transformacao.escala(Emais);
        } else {
            if (key == 'E') {
                transformacao.escala(Emenos);
            }
        }

        if (key == 'g' || key == 'G') {
            bSpline.salvarBSpline("bspline.dat");
        } else {
            if (key == 'o' || key == 'O') {
                bSpline.carregarBSpline("bspline.dat");
            }
        }

        painel.repaint();

    }
     */

    //////////////////////////////////////////////

    // atividade06_2 ////////////////////////////////////////////

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

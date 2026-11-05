package org.example;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MyMouse extends MouseAdapter{

    private Figura figura;
    private JPanel painel;
    private Teardrop teardrop;
    private TeardropFlower teardropFlower;
    private BSpline bSpline;
    private Triangulo V;
    private Triangulo W;

    public MyMouse(Figura figura, JPanel painel, Teardrop teardrop, TeardropFlower teardropFlower, BSpline bSpline, Triangulo V, Triangulo W){

        this.figura = figura;
        this.painel = painel;
        this.teardrop = teardrop;
        this.teardropFlower = teardropFlower;
        this.bSpline = bSpline;
        this.V = V;
        this.W = W;

    }

    // exercicio02 ////////////////////////////////////////////

    /*
    @Override
    public void mouseClicked(MouseEvent arg0){

        // e.getButton() retorna um int
        if(arg0.getButton() == MouseEvent.BUTTON1){
            // ponto criado
            Ponto p = new Ponto(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY());
            // figura tem um ponto
            figura.adicionarVertice(p);
            figura.setPontoCorrente(p);
        }else{
            if(arg0.getButton() == MouseEvent.BUTTON3){
                figura.removerVertice(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY());
                figura.selecionarMaisProximo(arg0.getX(), arg0.getY());
            }
        }

        painel.repaint();
    }
    */

    //////////////////////////////////////////////

    // exercicio04_1 ////////////////////////////////////////////

    /*
    public void mousePressed(MouseEvent e){

        if(e.getButton() == MouseEvent.BUTTON1){
            Ponto p = new Ponto(e.getX(), e.getComponent().getHeight() - e.getY());
            teardrop.formarTeardrop(p);
        }

        painel.repaint();

    }
     */

    //////////////////////////////////////////////

    // atividade04 ////////////////////////////////////////////

    /*
    @Override
    public void mousePressed(MouseEvent e) {

        Ponto p = new Ponto(e.getX(), e.getComponent().getHeight() - e.getY());

        if(e.getButton() == MouseEvent.BUTTON1){
            teardropFlower.formarFiguraA(p);
        }else{
            if(e.getButton() == MouseEvent.BUTTON3){
                teardropFlower.formarFiguraB(p);
            }
        }

        painel.repaint();

    }
     */

    //////////////////////////////////////////////

    // exercicio06_1 ////////////////////////////////////////////

    /*
    @Override
    public void mouseClicked(MouseEvent arg0){

        // e.getButton() retorna um int
        if(arg0.getButton() == MouseEvent.BUTTON1){
            // ponto criado
            Ponto p = new Ponto(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY());
            // figura tem um ponto
            bSpline.adicionarVertice(p);
            bSpline.setPontoCorrente(p);
        }else{
            if(arg0.getButton() == MouseEvent.BUTTON3){
                bSpline.removerVertice(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY());
                bSpline.selecionarMaisProximo(arg0.getX(), arg0.getY());
            }
        }

        painel.repaint();

    }
     */

    //////////////////////////////////////////////

    // atividade06 ////////////////////////////////////////////

    /*
    @Override
    public void mouseDragged(MouseEvent arg0) {

        // atualizando o ponto em tempo real

        float x, y;
        Ponto pontoCorrente = bSpline.getPontoCorrente();

        if(bSpline.getPontoCorrente() != null){
            x = arg0.getX();
            y = arg0.getComponent().getHeight() - arg0.getY();
            pontoCorrente.setX(x);
            pontoCorrente.setY(y);
            painel.repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e){

        float x = e.getX();
        float y = e.getComponent().getHeight() - e.getY();
        Ponto p;

        if(e.getButton() == MouseEvent.BUTTON1){
            bSpline.selecionarMaisProximo(x, y);

            if(bSpline.getPontoCorrente() == null || bSpline.getPontoCorrente().calcularDistancia(x, y) > 15){
                p = new Ponto(x, y);
                bSpline.adicionarVertice(p);
                bSpline.setPontoCorrente(p);
            }
        }else{
            if(e.getButton() == MouseEvent.BUTTON3){
                bSpline.removerVertice(x, y);
            }
        }

        painel.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent arg0){

        bSpline.setPontoCorrente(null);

    }
     */

    //////////////////////////////////////////////

    @Override
    public void mouseEntered(MouseEvent arg0){

    }

    @Override
    public void mouseExited(MouseEvent arg0){

    }

    @Override
    public void mouseMoved(MouseEvent arg0){

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

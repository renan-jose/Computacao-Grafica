package org.example.mouse;

import org.example.BSpline;
import org.example.Ponto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAtividade05 extends MouseAdapter{

    private final BSpline bSpline;
    private final JPanel painel;

    public MouseAtividade05(BSpline bSpline, JPanel painel){

        this.bSpline = bSpline;
        this.painel = painel;

    }

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
            bSpline.selecionarMaisProximo(x, y, 0);

            if(bSpline.getPontoCorrente() == null || bSpline.getPontoCorrente().calcularDistancia(x, y, 0) > 15){
                p = new Ponto(x, y, 0);
                bSpline.adicionarVertice(p);
                bSpline.setPontoCorrente(p);
            }
        }else{
            if(e.getButton() == MouseEvent.BUTTON3){
                bSpline.removerVertice(x, y, 0);
            }
        }

        painel.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent arg0){

        bSpline.setPontoCorrente(null);

    }

}

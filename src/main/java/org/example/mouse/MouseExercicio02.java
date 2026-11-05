package org.example.mouse;

import org.example.Figura;
import org.example.Ponto;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseExercicio02 extends MouseAdapter{

    private final Figura figura;
    private final JPanel painel;

    public MouseExercicio02(Figura figura, JPanel painel){

        this.figura = figura;
        this.painel = painel;

    }

    @Override
    public void mouseClicked(MouseEvent arg0){

        // e.getButton() retorna um int
        if(arg0.getButton() == MouseEvent.BUTTON1){
            // ponto criado
            Ponto p = new Ponto(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY(), 0);
            // figura tem um ponto
            figura.adicionarVertice(p);
            figura.setPontoCorrente(p);
        }else{
            if(arg0.getButton() == MouseEvent.BUTTON3){
                figura.removerVertice(arg0.getX(), arg0.getComponent().getHeight() - arg0.getY(), 0);
                figura.selecionarMaisProximo(arg0.getX(), arg0.getY(), 0);
            }
        }

        painel.repaint();
    }

}

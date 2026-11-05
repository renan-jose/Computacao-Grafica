package org.example.key;

import org.example.Mesh;
import org.example.Ponto;
import org.example.Transformacoes;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KeyAtividade07_1 extends KeyAdapter{

    private Mesh mesh;
    private JPanel painel;
    private float anguloRotacao = 5.0f;
    private List<File> arquivosObj = new ArrayList<>();
    private int indiceAtual = 0;

    public KeyAtividade07_1(Mesh mesh, JPanel painel){

        this.mesh = mesh;
        this.painel = painel;
        mapearPastaOBJ("OBJ");

    }

    private void mapearPastaOBJ(String caminhoPasta){

        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".obj"));

        if(arquivos != null && arquivos.length > 0){
            for(File f : arquivos){
                arquivosObj.add(f);
            }

            mesh.carregarObj(arquivosObj.get(0).getAbsolutePath());
        }

    }

    @Override
    public void keyPressed(KeyEvent e){

        if(arquivosObj.isEmpty()){
            return;
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            indiceAtual = (indiceAtual + 1) % arquivosObj.size();
            mesh.carregarObj(arquivosObj.get(indiceAtual).getAbsolutePath());
            painel.repaint();
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            indiceAtual = (indiceAtual - 1 + arquivosObj.size()) % arquivosObj.size();
            mesh.carregarObj(arquivosObj.get(indiceAtual).getAbsolutePath());
            painel.repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e){

        char key = e.getKeyChar();

        Transformacoes transformacoes = new Transformacoes();

        for(Ponto p : mesh.getVertices()){
            transformacoes.adicionarVertice(p);
        }

        if(key == 'x'){
            transformacoes.rotacionar(-anguloRotacao, 'x');
        }else if(key == 'X'){
            transformacoes.rotacionar(anguloRotacao, 'x');
        }

        if(key == 'y'){
            transformacoes.rotacionar(-anguloRotacao, 'y');
        }else if(key == 'Y'){
            transformacoes.rotacionar(anguloRotacao, 'y');
        }

        if(key == 'z'){
            transformacoes.rotacionar(-anguloRotacao, 'z');
        }else if(key == 'Z'){
            transformacoes.rotacionar(anguloRotacao, 'z');
        }

        painel.repaint();

    }

}

package org.example.key;

import org.example.Camera;
import org.example.Mesh;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KeyAtividade08_1 extends KeyAdapter{

    private Mesh mesh;
    private Camera camera;
    private JPanel painel;
    private List<File> arquivosObj = new ArrayList<>();
    private int indiceAtual = 0;

    private float passoTranslacao = 5;
    private float anguloRotacao = 3;

    public KeyAtividade08_1(Camera camera, Mesh mesh, JPanel painel){

        this.camera = camera;
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

        switch(key){
            case 'z':
                camera.transladar(0, 0, -passoTranslacao);
                break;

            case 'Z':
                camera.transladar(0, 0, passoTranslacao);
                break;

            case 'p':
                camera.pitch(-anguloRotacao);
                break;

            case 'P':
                camera.pitch(anguloRotacao);
                break;

            case 'r':
                camera.roll(-anguloRotacao);
                break;

            case 'R':
                camera.roll(anguloRotacao);
                break;

            case 'y':
                camera.yaw(-anguloRotacao);
                break;
            case 'Y':
                camera.yaw(anguloRotacao);
                break;
        }

        painel.repaint();

    }

}

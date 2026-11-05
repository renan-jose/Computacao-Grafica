package org.example;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import org.example.key.*;
import org.example.mouse.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.List;

public class Principal implements GLEventListener{

    private GL2 gl;
    private Figura figura;
    private Dino dino;
    private Teardrop teardrop;
    private TeardropFlower teardropFlower;
    private BSpline bSpline;
    private Transformacoes transformacao;
    private Triangulo trianguloV;
    private Triangulo trianguloW;
    private Mesh mesh;
    public enum Tarefa{
        EXERCICIO_02_1,
        EXERCICIO_02_2,
        ATIVIDADE_03_1,
        EXERCICIO_04_1,
        EXERCICIO_04_2,
        ATIVIDADE_04,
        EXERCICIO_05_1,
        ATIVIDADE_05,
        ATIVIDADE_06_1,
        ATIVIDADE_06_2,
        ATIVIDADE_07_1
    }
    private Tarefa tarefaAtual = Tarefa.ATIVIDADE_06_2;
    private static MouseAdapter mouseAtual = null;
    private static KeyAdapter keyAtual = null;

    // construtor
    public Principal(Figura figura, Dino dino, Teardrop teardrop, TeardropFlower teardropFlower, BSpline bSpline, Transformacoes transformacao, Triangulo trianguloV, Triangulo trianguloW, Mesh mesh){

        gl = null;
        this.figura = figura;
        this.dino = dino;
        this.teardrop = teardrop;
        this.teardropFlower = teardropFlower;
        this.bSpline = bSpline;
        this.transformacao = transformacao;
        this.trianguloV = trianguloV;
        this.trianguloW = trianguloW;
        this.mesh = mesh;

    }

    public void setTarefaAtual(Tarefa tarefa){

        this.tarefaAtual = tarefa;

    }

    private static void trocarMouse(GLJPanel panel, MouseAdapter novoMouse){

        if(mouseAtual != null){
            panel.removeMouseListener(mouseAtual);
            panel.removeMouseMotionListener(mouseAtual);
        }

        mouseAtual = novoMouse;

        if(mouseAtual != null){
            panel.addMouseListener(mouseAtual);
            panel.addMouseMotionListener(mouseAtual);
        }

    }

    private static void trocarKey(GLJPanel panel, KeyAdapter novoKey){

        if(keyAtual != null){
            panel.removeKeyListener(keyAtual);
        }

        keyAtual = novoKey;

        if(keyAtual != null){
            panel.addKeyListener(keyAtual);
        }

        panel.requestFocusInWindow();

    }

    // GLAutoDrawable funciona como linguagem de script
    @Override
    public void init(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        // glClearColor cor que limpa a tela
        gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);

    }

    private void prepararProjecao2D(GLAutoDrawable drawable){

        int width = drawable.getSurfaceWidth();
        int height = drawable.getSurfaceHeight();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, width, 0.0, height, -1.0, 1.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

    }

    private void prepararProjecao3D(){

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-150.0, 150.0, -150.0, 150.0, -300.0, 300.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

    }

    public void exercicio02_1(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        // glClear limpa a tela
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glPointSize(10);

        // inicia configuração de desenhos
        gl.glBegin(GL2.GL_POINTS);

        gl.glColor3f(0.0f, 0.7f, 1.0f);

        for(Ponto p : figura.getPontos()){
            gl.glVertex2f(p.getX(), p.getY());
        }

        // encerra as configurações abertas
        gl.glEnd();

        gl.glPointSize(5);

        gl.glBegin(GL2.GL_POINTS);

        gl.glColor3f(0.0f, 0.7f, 1.0f);

        int i;

        // percorrer até o último elemento
        for(i = 0; i < figura.getPontos().size() - 1; i++){
            Ponto p0 = figura.getPontos().get(i);
            Ponto p1 = figura.getPontos().get(i + 1);
            Linha linha = new Linha(p0, p1);

            // coeficiente angular
            float a = linha.calcularInclinacao();
            // ponto onde a reta corta o eixo y
            float b = p0.getY() - a * p0.getX();

            // início e fim do eixo x
            float xMin = Math.min(p0.getX(), p1.getX());
            float xMax = Math.max(p0.getX(), p1.getX());

            float x;

            for(x = xMin; x <= xMax; x++){
                float y = a * x + b;
                gl.glVertex2f(x, y);
            }

        }

        gl.glEnd();

        gl.glFlush();

    }

    public void exercicio02_2(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        // glClear limpa a tela
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glPointSize(5);

        gl.glBegin(GL2.GL_POINTS);

        for(Ponto p : figura.getPontos()){
            if(p == figura.getPontoCorrente()){
                gl.glColor3f(1.0f, 0.0f, 0.0f);
            }else{
                gl.glColor3f(0.0f, 0.7f, 1.0f);
            }
            gl.glVertex2f(p.getX(), p.getY());
        }

        gl.glEnd();

        gl.glFlush();

    }

    public void atividade03_1(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        // glClear limpa a tela
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(0.0f, 0.7f, 1.0f);

        for(Poligono poligono : dino.getPoligonos()){
            gl.glBegin(GL2.GL_LINE_STRIP);
            for(Ponto p : poligono.getPontoPoligono()){
                gl.glVertex2f(p.getX(), p.getY());
            }
            gl.glEnd();
        }

        gl.glFlush();

    }

    public void exercicio04_1(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        // glClear limpa a tela
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(0.0f, 0.7f, 1.0f);

        gl.glBegin(GL2.GL_LINE_LOOP);
        for(Ponto ponto: teardrop.getPontos()){
            gl.glVertex2f(ponto.getX(), ponto.getY());
        }
        gl.glEnd();

        gl.glFlush();

    }

    public void exercicio04_2(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        // glClear limpa a tela
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // obs: em pixels
        int larguraJanela = drawable.getSurfaceWidth(), alturaJanela = drawable.getSurfaceHeight();

        // a janela é uma matriz e cada casinha da matriz vai ter uma largura e altura.
        int linhas = 2, colunas = 2;
        int larguraCasinha = larguraJanela / colunas;
        int alturaCasinha = alturaJanela / linhas;
        int i, j;

        for(i = 0; i < linhas; i++){
            for(j = 0; j < colunas; j++){
                gl.glViewport(j * larguraCasinha, i * alturaCasinha, larguraCasinha, alturaCasinha);

                gl.glMatrixMode(GL2.GL_PROJECTION);
                gl.glLoadIdentity();

                if(i == linhas - 1){
                    // avesso
                    gl.glOrtho(650.0, 0.0, 500.0, 0.0, -1.0, 1.0);
                }else{
                    gl.glOrtho(0.0, 650.0, 0.0, 500.0, -1.0, 1.0);
                }

                gl.glMatrixMode(GL2.GL_MODELVIEW);
                gl.glLoadIdentity();

                for(Poligono poligono : dino.getPoligonos()){
                    gl.glBegin(GL2.GL_LINE_STRIP);
                    for(Ponto p : poligono.getPontoPoligono()){
                        gl.glVertex2f(p.getX(), p.getY());
                    }
                    gl.glEnd();
                }
            }
        }

        gl.glFlush();

    }

    public void atividade_04(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(0.0f, 0.7f, 1.0f);

        for(Teardrop t : teardropFlower.getTeardrops()){
            gl.glBegin(GL2.GL_LINE_LOOP);
            for(Ponto p : t.getPontos()){
                gl.glVertex2f(p.getX(), p.getY());
            }
            gl.glEnd();
        }

        gl.glFlush();

    }

    public void exercicio05_1(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        // glClear limpa a tela
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glPointSize(10);

        // inicia configuração de desenhos
        gl.glBegin(GL2.GL_POINTS);

        gl.glColor3f(0.0f, 0.7f, 1.0f);

        List<Ponto> pontos = bSpline.getPontos();

        for(Ponto p : pontos){
            gl.glVertex2f(p.getX(), p.getY());
        }

        // encerra as configurações abertas
        gl.glEnd();

        if(pontos.size() >= 3){
            gl.glColor3f(0.0f, 0.7f, 1.0f);
            gl.glLineWidth(2.0f);
            gl.glBegin(GL2.GL_LINE_STRIP);

            List<Ponto> pontosCurva = bSpline.formarBSpline();

            for(Ponto p : pontosCurva){
                gl.glVertex2f(p.getX(), p.getY());
            }

            gl.glEnd();
        }

        gl.glFlush();

    }

    public void atividade_05(GLAutoDrawable drawable){

        exercicio05_1(drawable);

    }

    public void atividade06_1(GLAutoDrawable drawable){

        exercicio05_1(drawable);

    }

    public void atividade06_2(GLAutoDrawable drawable){

        exercicio05_1(drawable);

        gl = drawable.getGL().getGL2();

        gl.glPointSize(10);

        gl.glBegin(GL2.GL_POINTS);
        for(Ponto p : trianguloV.getPontoPoligono()){
            gl.glVertex2f(p.getX(), p.getY());
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_LINE_LOOP);
        for(Ponto p : trianguloV.getPontoPoligono()){
            gl.glVertex2f(p.getX(), p.getY());
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_POINTS);
        for(Ponto p : trianguloW.getPontoPoligono()){
            gl.glVertex2f(p.getX(), p.getY());
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_LINE_LOOP);
        for(Ponto p : trianguloW.getPontoPoligono()){
            gl.glVertex2f(p.getX(), p.getY());
        }
        gl.glEnd();

        gl.glFlush();

    }

    public void atividade07_1(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(0.0f, 0.7f, 1.0f);

        List<Ponto> vertices = mesh.getVertices();
        List<Face> faces = mesh.getFaces();

        for(Face face : faces){
            gl.glBegin(GL2.GL_LINE_LOOP);
            List<Integer> indicesVertices = face.getIndicesVertices();
            for(Integer indice : indicesVertices){
                Ponto p = vertices.get(indice);
                gl.glVertex3f(p.getX(), p.getY(), p.getZ());
            }
            gl.glEnd();
        }

        gl.glFlush();

    }

    // tela a ser repintada
    @Override
    public void display(GLAutoDrawable drawable){

        switch(tarefaAtual){
            case EXERCICIO_02_1:
                prepararProjecao2D(drawable);
                exercicio02_1(drawable);
                break;

            case EXERCICIO_02_2:
                prepararProjecao2D(drawable);
                exercicio02_2(drawable);
                break;

            case ATIVIDADE_03_1:
                prepararProjecao2D(drawable);
                atividade03_1(drawable);
                break;

            case EXERCICIO_04_1:
                prepararProjecao2D(drawable);
                exercicio04_1(drawable);
                break;

            case EXERCICIO_04_2:
                prepararProjecao2D(drawable);
                exercicio04_2(drawable);
                break;

            case ATIVIDADE_04:
                prepararProjecao2D(drawable);
                atividade_04(drawable);
                break;

            case EXERCICIO_05_1:
                prepararProjecao2D(drawable);
                exercicio05_1(drawable);
                break;

            case ATIVIDADE_05:
                prepararProjecao2D(drawable);
                atividade_05(drawable);
                break;

            case ATIVIDADE_06_1:
                prepararProjecao2D(drawable);
                atividade06_1(drawable);
                break;

            case ATIVIDADE_06_2:
                prepararProjecao2D(drawable);
                atividade06_2(drawable);
                break;

            case ATIVIDADE_07_1:
                prepararProjecao3D();
                atividade07_1(drawable);
                break;
        }

    }

    @Override
    public void dispose(GLAutoDrawable drawable){

    }

    // maximização/minimização
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){

        GL2 gl = drawable.getGL().getGL2();

        if(height <= 0){
            height = 1;
        }

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, width, 0.0, height, -1.0, 1.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

    }

    public static void main(String[] args){

        // Profile (GLProfile) especifica as requisições possíveis à Capacibilities
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        // Capacibilities (GLCapacibilities) gerencia as funções do OpenGL com as bibliotecas
        GLCapabilities capabilities = new GLCapabilities(profile);

        Figura figura = new Figura();
        Dino dino = new Dino();
        Teardrop teardrop = new Teardrop(50, 30, 45, 100);
        TeardropFlower teardropFlower = new TeardropFlower();
        BSpline bSpline = new BSpline();
        Transformacoes transformacao = new Transformacoes();
        Triangulo trianguloV = new Triangulo();
        Triangulo trianguloW = new Triangulo();
        Mesh mesh = new Mesh();

        GLJPanel panel = new GLJPanel(capabilities);
        Principal principal = new Principal(figura, dino, teardrop, teardropFlower, bSpline, transformacao, trianguloV, trianguloW, mesh);
        // barrona horizontal de cima
        JMenuBar menuBar = new JMenuBar();
        // dropdown
        JMenu menuTarefas = new JMenu("Tarefas");
        JFrame frame = new JFrame("Computação gráfica");

        panel.addGLEventListener(principal);
        panel.setSize(800, 800);

        JMenuItem itemExercicio02_1 = new JMenuItem("Exercício 02_1");
        itemExercicio02_1.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.EXERCICIO_02_1);
            MouseExercicio02 mouseExercicio02 = new MouseExercicio02(figura, panel);
            trocarMouse(panel, mouseExercicio02);
            trocarKey(panel, null);
            panel.repaint();
        });

        // Exercício 02_2
        JMenuItem itemExercicio02_2 = new JMenuItem("Exercício 02_2");
        itemExercicio02_2.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.EXERCICIO_02_2);
            MouseExercicio02 mouseExercicio02 = new MouseExercicio02(figura, panel);
            trocarMouse(panel, mouseExercicio02);
            KeyExercicio02_2 keyExercicio02_2 = new KeyExercicio02_2(figura, panel);
            trocarKey(panel, keyExercicio02_2);
            panel.repaint();
        });

        JMenuItem itemAtividade03_1 = new JMenuItem("Atividade 03_1");
        itemAtividade03_1.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.ATIVIDADE_03_1);
            trocarMouse(panel, null);
            KeyAtividade03_1 keyAtividade03_1 = new KeyAtividade03_1(dino, panel);
            trocarKey(panel, keyAtividade03_1);
            panel.repaint();
        });

        JMenuItem itemExercicio04_1 = new JMenuItem("Exercício 04_1");
        itemExercicio04_1.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.EXERCICIO_04_1);
            MouseExercicio04_1 mouseExercicio04_1 = new MouseExercicio04_1(teardrop, panel);
            trocarMouse(panel, mouseExercicio04_1);
            trocarKey(panel, null);
            panel.repaint();
        });

        JMenuItem itemExercicio04_2 = new JMenuItem("Exercício 04_2");
        itemExercicio04_2.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.EXERCICIO_04_2);
            trocarMouse(panel, null);
            KeyAtividade03_1 keyExercicio04_2 = new KeyAtividade03_1(dino, panel);
            trocarKey(panel, keyExercicio04_2);
            panel.repaint();
        });

        JMenuItem itemAtividade04 = new JMenuItem("Atividade 04");
        itemAtividade04.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.ATIVIDADE_04);
            MouseAtividade04 mouseAtividade04 = new MouseAtividade04(teardropFlower, panel);
            trocarMouse(panel, mouseAtividade04);
            trocarKey(panel, null);
            panel.repaint();
        });

        JMenuItem itemExercicio05_1 = new JMenuItem("Exercício 05_1");
        itemExercicio05_1.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.EXERCICIO_05_1);
            MouseExercicio05_1 mouseExercicio05_1 = new MouseExercicio05_1(bSpline, panel);
            trocarMouse(panel, mouseExercicio05_1);
            trocarKey(panel, null);
            panel.repaint();
        });

        JMenuItem itemAtividade05 = new JMenuItem("Atividade 05");
        itemAtividade05.addActionListener(e -> {
            principal.setTarefaAtual(Tarefa.ATIVIDADE_05);
            MouseAtividade05 mouseAtividade05 = new MouseAtividade05(bSpline, panel);
            trocarMouse(panel, mouseAtividade05);
            trocarKey(panel, null);
            panel.repaint();
        });

        JMenuItem itemAtividade06_1 = new JMenuItem("Atividade 06_1");
        itemAtividade06_1.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.ATIVIDADE_06_1);
            MouseExercicio06_1 mouseExercicio06_1 = new MouseExercicio06_1(bSpline, panel);
            trocarMouse(panel, mouseExercicio06_1);
            KeyAtividade06_1 keyAtividade06_1 = new KeyAtividade06_1(bSpline, panel);
            trocarKey(panel, keyAtividade06_1);
            panel.repaint();
        });

        JMenuItem itemAtividade06_2 = new JMenuItem("Atividade 06_2");
        itemAtividade06_2.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.ATIVIDADE_06_2);
            MouseAtividade06_2 mouseAtividade06_2 = new MouseAtividade06_2(trianguloV, trianguloW, bSpline, panel);
            trocarMouse(panel, mouseAtividade06_2);
            KeyAtividade06_2 keyAtividade06_2 = new KeyAtividade06_2(bSpline, trianguloV, trianguloW, panel);
            trocarKey(panel, keyAtividade06_2);
            panel.repaint();
        });

        JMenuItem itemAtividade07_1 = new JMenuItem("Atividade 07_1");
        itemAtividade07_1.addActionListener(e -> {
            principal.setTarefaAtual(Principal.Tarefa.ATIVIDADE_07_1);
            trocarMouse(panel, null);
            KeyAtividade07_1 keyAtividade07_1 = new KeyAtividade07_1(mesh, panel);
            trocarKey(panel, keyAtividade07_1);
            panel.repaint();
        });

        MouseAtividade06_2 mouseAtividade06_2 = new MouseAtividade06_2(trianguloV, trianguloW, bSpline, panel);
        trocarMouse(panel, mouseAtividade06_2);

        menuTarefas.add(itemExercicio02_1);
        menuTarefas.add(itemExercicio02_2);
        menuTarefas.addSeparator();
        menuTarefas.add(itemAtividade03_1);
        menuTarefas.addSeparator();
        menuTarefas.add(itemExercicio04_1);
        menuTarefas.add(itemExercicio04_2);
        menuTarefas.add(itemAtividade04);
        menuTarefas.addSeparator();
        menuTarefas.add(itemExercicio05_1);
        menuTarefas.add(itemAtividade05);
        menuTarefas.addSeparator();
        menuTarefas.add(itemAtividade06_1);
        menuTarefas.add(itemAtividade06_2);
        menuTarefas.addSeparator();
        menuTarefas.add(itemAtividade07_1);

        menuBar.add(menuTarefas);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
package org.example;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import javax.swing.JFrame;
import java.util.List;

public class Principal implements GLEventListener{

    private GL2 gl;
    private Figura figura;
    private Dino dino;
    private Teardrop teardrop;
    private TeardropFlower teardropFlower;
    private BSpline bSpline;
    private Transformacoes2D transformacao;
    private Triangulo trianguloV;
    private Triangulo trianguloW;

    // construtor
    public Principal(Figura figura, Dino dino, Teardrop teardrop, TeardropFlower teardropFlower, BSpline bSpline, Transformacoes2D transformacao, Triangulo trianguloV, Triangulo trianguloW){

        gl = null;
        this.figura = figura;
        this.dino = dino;
        this.teardrop = teardrop;
        this.teardropFlower = teardropFlower;
        this.bSpline = bSpline;
        this.transformacao = transformacao;
        this.trianguloV = trianguloV;
        this.trianguloW = trianguloW;

    }

    // GLAutoDrawable funciona como linguagem de script
    @Override
    public void init(GLAutoDrawable drawable){

        gl = drawable.getGL().getGL2();

        // glClearColor cor que limpa a tela
        gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 650, 0.0, 500, -1.0, 1.0);

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

    // tela a ser repintada
    @Override
    public void display(GLAutoDrawable drawable){

        //exercicio02_1(drawable);
        //exercicio02_2(drawable);

        //atividade03_1(drawable);
        //exercicio04_1(drawable);

        //exercicio04_2(drawable);

        //atividade_04(drawable);

        //exercicio05_1(drawable);

        //atividade_05(drawable);

        //atividade06_1(drawable);

        atividade06_2(drawable);

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
        Transformacoes2D transformacao = new Transformacoes2D();
        Triangulo trianguloV = new Triangulo();
        Triangulo trianguloW = new Triangulo();

        GLJPanel panel = new GLJPanel(capabilities);
        Principal principal = new Principal(figura, dino, teardrop, teardropFlower, bSpline, transformacao, trianguloV, trianguloW);

        panel.addGLEventListener(principal);
        panel.setSize(800, 800);

        MyMouse mouse = new MyMouse(figura, panel, teardrop, teardropFlower, bSpline, trianguloV, trianguloW);
        MyKey key = new MyKey(figura, panel, dino, bSpline, transformacao, trianguloV, trianguloW);

        panel.addMouseListener(mouse);
        panel.addMouseMotionListener(mouse);

        panel.addKeyListener(key);

        // Frame (JFrame) pode ser qualquer tipo de frame
        JFrame frame = new JFrame("Computação gráfica");

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
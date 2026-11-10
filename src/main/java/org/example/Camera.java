package org.example;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

public class Camera{

    Ponto olho;
    private float[] u = new float[3], v = new float[3], n =new float[3];
    private float anguloAbertura = 45, razaoAspecto = 1, perto = 0.1f, longe = 1000;

    public Camera(){

        definirComponentes(new Ponto(0, 0, 200), new Ponto(0, 0, 0), new float[]{0, 1, 0});
    }

    public Ponto getOlho(){

        return olho;

    }

    public void setDadosCamera(float campoVisao, float aspecto, float perto, float longe){

        this.anguloAbertura = campoVisao;
        this.razaoAspecto = aspecto;
        this.perto = perto;
        this.longe = longe;

    }

    public void aplicarProjecao(GL2 gl, GLU glu){

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(anguloAbertura, razaoAspecto, perto, longe);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

    }

    public void definirComponentes(Ponto olho, Ponto look, float[] up){

        this.olho = olho;

        n[0] = olho.getX() - look.getX();
        n[1] = olho.getY() - look.getY();
        n[2] = olho.getZ() - look.getZ();
        normalizarVetor(n);

        calcularProdutoVetorial(up, n, u);
        normalizarVetor(u);

        calcularProdutoVetorial(n, u, v);
        normalizarVetor(v);

    }

    public void aplicarViewMatriz(GL2 gl, GLU glu){

        gl.glLoadIdentity();
        glu.gluLookAt(olho.getX(), olho.getY(), olho.getZ(),
                olho.getX() - n[0], olho.getY() - n[1], olho.getZ() - n[2],
                v[0], v[1], v[2]);

    }

    private void normalizarVetor(float[] vetor){

        float magnitude = (float) Math.sqrt(Math.pow(vetor[0], 2) + Math.pow(vetor[1], 2) + Math.pow(vetor[2], 2));

        vetor[0] /= magnitude;
        vetor[1] /= magnitude;
        vetor[2] /= magnitude;

    }

    private void calcularProdutoVetorial(float[] vetor1, float[] vetor2, float[] resultante){

        resultante[0] = vetor1[1] * vetor2[2] - vetor1[2] * vetor2[1];
        resultante[1] = vetor1[2] * vetor2[0] - vetor1[0] * vetor2[2];
        resultante[2] = vetor1[0] * vetor2[1] - vetor1[1] * vetor2[0];

    }

    public void transladar(float deltaU, float deltaV, float deltaN){

        olho.setX(olho.getX() + deltaU * u[0] + deltaV * v[0] + deltaN * n[0]);
        olho.setY(olho.getY() + deltaU * u[1] + deltaV * v[1] + deltaN * n[1]);
        olho.setZ(olho.getZ() + deltaU * u[2] + deltaV * v[2] + deltaN * n[2]);

    }

    public void pitch(float anguloGraus){

        rotacionar(v, n, anguloGraus); // u

    }

    public void yaw(float anguloGraus){

        rotacionar(n, u, anguloGraus); // v

    }

    public void roll(float anguloGraus){

        rotacionar(u, v, anguloGraus); // n

    }

    public void rotacionar(float[] x, float[] y, float anguloGraus){

        float theta = (float) Math.toRadians(anguloGraus);
        float cosseno = (float) Math.cos(theta);
        float seno = (float) Math.sin(theta);
        float xLinha, yLinha;
        int i;

        for(i = 0; i< 3; i++){
            xLinha = x[i];
            yLinha = y[i];
            x[i] = xLinha * cosseno - yLinha * seno;
            y[i] = xLinha * seno + yLinha * cosseno;
        }

    }

}

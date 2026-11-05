package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class BSpline{

    private List<Ponto> pontos;
    private Ponto pontoCorrente;

    public BSpline(){

        this.pontos = new ArrayList<>();

    }

    public List<Ponto> getPontos(){

        return this.pontos;

    }

    public Ponto getPontoCorrente(){

        return pontoCorrente;

    }

    public void setPontoCorrente(Ponto pontoCorrente){

        this.pontoCorrente = pontoCorrente;

    }

    public void adicionarVertice(Ponto p){

        this.pontos.add(p);

    }

    // ordem k
    public float funcaoRecursiva(int i, int k, float t, float[] T){

        if(k == 1){
            if((t >= T[i] && t < T[i + 1]) || (t == T[T.length - 1] && t >= T[i] && t <= T[i + 1])){
                return 1;
            }
            return 0;
        }

        float pt1 = 0, denominador1 = T[i + k - 1] - T[i];

        if(denominador1 != 0){
            pt1 = ((t - T[i]) / denominador1) * funcaoRecursiva(i, k - 1, t, T);
        }

        float pt2 = 0, denominador2 = T[i + k] - T[i + 1];

        if(denominador2 != 0){
            pt2 = ((T[i + k] - t) / denominador2) * funcaoRecursiva(i + 1, k - 1, t, T);
        }

        return pt1 + pt2;

    }

    public List<Ponto> formarBSpline(){

        List<Ponto> pontosCurva = new ArrayList<>();
        int numeroPontos = pontos.size();
        int k = 3; // ordem

        if(numeroPontos < k){
            return pontosCurva;
        }

        int n = numeroPontos - 1; // somatório
        int numeroNos = n + k + 1;
        float[] T = new float[numeroNos]; // começa/termina
        int i;

        for(i = 0; i < numeroNos; i++){
            if(i < k){
                T[i] = 0.0f;
            }else if (i <= n){
                T[i] = (float) (i - k + 1) / (n - k + 2);
            }else{
                T[i] = 1.0f;
            }
        }

        float passo = 0.0005f;
        float t;
        Ponto ponto;

        for(t = 0; t <= 1; t += passo){
            float x = 0, y = 0, z = 0;
            for(i = 0; i <= n; i++){
                float N = funcaoRecursiva(i, k, t, T);
                x += N * pontos.get(i).getX();
                y += N * pontos.get(i).getY();
            }

            ponto = new Ponto(x, y, z);
            pontosCurva.add(ponto);
        }

        return pontosCurva;

    }

    public void removerVertice(float x, float y, float z){

        int removido = -1, i;
        float menorDistancia = Float.MAX_VALUE;

        if(pontos.isEmpty() != true){
            for(i = 0; i < pontos.size(); i++){
                if(pontos.get(i).calcularDistancia(x, y, z) < menorDistancia){
                    menorDistancia = pontos.get(i).calcularDistancia(x, y, z);
                    removido = i;
                }
            }
        }

        if(removido != -1){
            pontos.remove(removido);
        }

    }

    public void selecionarMaisProximo(float x, float y, float z){

        int tonho = -1, i = 0;
        float menorDistancia = Float.MAX_VALUE;

        if(pontos.isEmpty() != true){
            for(i = 0; i < pontos.size(); i++){
                if(pontos.get(i).calcularDistancia(x, y, z) < menorDistancia){
                    menorDistancia = pontos.get(i).calcularDistancia(x, y, z);
                    tonho = i;
                }
            }
        }

        if(tonho != -1){
            this.pontoCorrente = pontos.get(tonho);
        }

    }

    public void salvarBSpline(String caminho){

        try(PrintWriter writer = new PrintWriter(new FileWriter(caminho))){

            writer.println(pontos.size());
            for(Ponto p : pontos){
                writer.println(p.getX() + " " + p.getY());
            }
            System.out.println("Bspline salva");
        }catch(IOException e){
            System.out.println("Deu ruim na salvada");
        }

    }

    public void carregarBSpline(String caminho){

        pontos.clear();

        try(Scanner scanner = new Scanner(new File(caminho))){

            scanner.useLocale(Locale.US);

            int numeroPts = scanner.nextInt();
            int i;
            float x, y, z = 0;
            Ponto ponto;

            for(i = 0; i < numeroPts; i++){
                x = scanner.nextFloat();
                y = scanner.nextFloat();
                ponto = new Ponto(x, y, z);
                pontos.add(ponto);
            }
        }catch(IOException e){
            System.out.println("Erro no carregamento da B-Spline");
        }

    }

}

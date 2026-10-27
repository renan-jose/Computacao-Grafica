package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dino{

    private List<Poligono> poligonos;

    public Dino(){

        this.poligonos = new ArrayList<>();

    }

    public List<Poligono> getPoligonos(){

        return this.poligonos;

    }

    public void carregarDino(String caminho){

        poligonos.clear();

        try(Scanner scanner = new Scanner(new File(caminho))){

            int numeroPoligonos = scanner.nextInt(), i, j;

            for(i = 0; i < numeroPoligonos; i++){
                int numeroPontos = scanner.nextInt();
                Poligono poligono = new Poligono();

                for(j = 0; j < numeroPontos; j++){
                    float x = scanner.nextFloat();
                    float y = scanner.nextFloat();
                    Ponto ponto = new Ponto(x, y);
                    poligono.adicionarPontoPoligono(ponto);
                }

                poligonos.add(poligono);

            }

        }catch(IOException e){
            System.err.println("Erro ao carregar: " + e.getMessage());
        }

    }

}

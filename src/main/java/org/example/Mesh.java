package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mesh{

    private List<Ponto> vertices;
    private List<Face> faces;

    public Mesh(){

        this.vertices = new ArrayList<>();
        this.faces = new ArrayList<>();

    }

    public List<Ponto> getVertices(){

        return vertices;

    }

    public List<Face> getFaces(){

        return faces;

    }

    public void carregarObj(String caminho){

        vertices.clear();
        faces.clear();

        try(Scanner scanner = new Scanner(new File(caminho))){

            while(scanner.hasNextLine()){
                String linha = scanner.nextLine().trim();

                if(linha.isEmpty() || linha.startsWith("#")){
                    continue;
                }

                String[] partes = linha.split("\\s+");

                if(partes[0].equals("v")){
                    float x = Float.parseFloat(partes[1]);
                    float y = Float.parseFloat(partes[2]);
                    float z = Float.parseFloat(partes[3]);
                    Ponto ponto = new Ponto(x, y, z);
                    vertices.add(ponto);
                }else if(partes[0].equals("f")){
                    Face face = new Face();
                    int i;
                    for(i = 1; i < partes.length; i++){
                        String indiceStr = partes[i].split("/")[0];
                        int indiceVertice = Integer.parseInt(indiceStr) - 1;
                        face.adicionarIndice(indiceVertice);
                    }
                    faces.add(face);
                }

            }
        }catch(IOException e){
            System.err.println("Erro ao carregar: " + e.getMessage());
        }

    }

}

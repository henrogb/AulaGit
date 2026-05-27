package Programa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\henro.isquierdo\\OneDrive\\Estudos\\Java\\Curso\\Exercicios\\Ex17\\Arquivos\\votacao.txt";
        String pathW = "C:\\Users\\henro.isquierdo\\OneDrive\\Estudos\\Java\\Curso\\Exercicios\\Ex17\\Arquivos\\Results.txt"; 

        try(BufferedReader bf = new BufferedReader(new FileReader(path))){
            Map<String, Integer> result = new TreeMap<>();
            String line = bf.readLine();
            
            while (line != null) {
                String[] linha = line.split(",");
                result.merge(linha[0], Integer.parseInt(linha[1]), Integer::sum);
                line = bf.readLine();
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathW))) {
                File dir = new File(pathW);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                
                StringBuilder stb = new StringBuilder();
                result.forEach((key, value) -> {
                    stb.append(key + "," + value + "\n");
                });

                bw.write(stb.toString());
                System.out.println("arquivo criado com sucesso!");

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}

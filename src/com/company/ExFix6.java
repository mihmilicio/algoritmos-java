package com.company;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class ExFix6 {

    public static void main(String[] args) throws IOException {
        URL url = ExFix6.class.getResource("grupos_tabulados.txt");
        File arquivo = new File(url.getPath());
        Scanner leitor = new Scanner(arquivo);
        leitor.nextLine();

        int somaTotal = 0;
        char[] grupos = new char[6];
        int[] somaGrupos = {0, 0, 0, 0, 0, 0};
        int currentIndex = 0;


        while (leitor.hasNext()) {
            String[] conteudo = leitor.nextLine().split("\t");
            char grupo = conteudo[0].charAt(0);
            int valor = Integer.parseInt(conteudo[1]);

            somaTotal += valor;

            int index = -1;
            for (int i = 0; i < grupos.length; i++) {
                if (grupos[i] == (grupo)) {
                    index = i;
                }
            }
            if (index != -1) {
                somaGrupos[index] += valor;
            } else {
                grupos[currentIndex] = grupo;
                somaGrupos[currentIndex] += valor;
                currentIndex++;
            }
        }

        leitor.close();


        String urlOut = "./src/" + ExFix6.class.getPackage().getName().replace(".", "/");
        FileWriter arquivoOut = new FileWriter(urlOut + "/numeros.txt");
        PrintWriter gravador = new PrintWriter(arquivoOut);

        gravador.println("Soma total: " + somaTotal);

        for (int i = 0; i < grupos.length; i++) {
            gravador.println("Soma "+ grupos[i] + ": " + somaGrupos[i]);
        }

        gravador.close();

        System.out.println("Resultado do processament gravado em \"numeros.txt\"");
    }
}

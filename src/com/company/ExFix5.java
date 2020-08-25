package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class ExFix5 {

    public static void main(String[] args) throws FileNotFoundException {
        URL url = ExFix5.class.getResource("ExFix5-grupos_tabulados.txt");
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

        System.out.println("Soma total: " + somaTotal);

        for (int i = 0; i < grupos.length; i++) {
            System.out.println("Soma "+ grupos[i] + ": " + somaGrupos[i]);
        }
    }
}

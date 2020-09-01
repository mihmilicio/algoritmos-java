package com.company.ex7;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ExFix7 {
    public static void main(String[] args) throws IOException {
        URL url = ExFix7.class.getResource("alunos.csv");
        File arquivo = new File(url.getPath());
        Scanner leitor = new Scanner(new FileInputStream(arquivo));
        leitor.nextLine();

        List<Aluno> listAlunos = new ArrayList<Aluno>();

        while (leitor.hasNextLine()) {
            String[] conteudo = leitor.nextLine().split(";");
            if (conteudo.length > 1) {
                try {
                    listAlunos.add(new Aluno(
                            Integer.parseInt(conteudo[0]),
                            conteudo[1],
                            Double.parseDouble(conteudo[2].replace(',', '.'))
                    ));
                } catch (NumberFormatException ignored) {};
            }
        }
        leitor.close();

        String urlOut = "./src/" + ExFix7.class.getPackage().getName().replace(".", "/");
        FileWriter arquivoOut = new FileWriter(urlOut + "/alunos_resultado.txt");
        PrintWriter gravador = new PrintWriter(arquivoOut);


        int qtde = listAlunos.size();
        gravador.println("Quantidade de alunos: " + qtde);

        int qtdeApr = (int) listAlunos.stream().filter(aluno -> aluno.nota >= 6).count();
        gravador.println("Quantidade de aprovados: " + qtdeApr);

        int qtdeRep = (int) listAlunos.stream().filter(aluno -> aluno.nota < 6).count();
        gravador.println("Quantidade de reprovados: " + qtdeRep);

        listAlunos.sort((a, b) -> (int) (a.nota - b.nota)); // menor pro maior
        gravador.println("Menor nota: " + listAlunos.get(0).nota);
        gravador.println("Maior nota: " + listAlunos.get(qtde - 1).nota);

        double soma = listAlunos.stream().mapToDouble(aluno -> aluno.nota).sum();
        gravador.print("Média geral: "+ soma/qtde);

        gravador.close();

        System.out.println("Resultado do processamento gravado em \"alunos_resultado.txt\"");
    }
}

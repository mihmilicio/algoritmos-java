package com.company.atv3;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class At3 {
    public static void main(String[] args) throws IOException {
        URL url = At3.class.getResource("clientes.csv");
        File arquivo = new File(url.getPath());
        Scanner leitor = new Scanner(new FileInputStream(arquivo));
        leitor.nextLine();

        List<Cliente> listCliente = new ArrayList<Cliente>();

        while (leitor.hasNextLine()) {
            String[] conteudo = leitor.nextLine().split(";");
            if (conteudo.length > 1) {
                try {
                    listCliente.add(new Cliente(
                            conteudo[0],
                            conteudo[1],
                            Integer.parseInt(conteudo[2]),
                            Double.parseDouble(conteudo[3].replace(',', '.'))
                    ));
                } catch (NumberFormatException ignored) {};
            }
        }
        leitor.close();

        String urlOut = "./src/" + At3.class.getPackage().getName().replace(".", "/");
        FileWriter arquivoOut = new FileWriter(urlOut + "/relatorio_clientes.txt");
        PrintWriter gravador = new PrintWriter(arquivoOut);

        listCliente.sort((a, b) -> (int) (a.codigo - b.codigo)); // menor pro maior
        gravador.println("Cliente mais antigo: " + listCliente.get(0).nome + "\n");

        List<Double> listaValores = listCliente.stream().map(cliente -> cliente.credito).collect(Collectors.toList());
        double maxCredito = (double) Collections.max(listaValores);
        List<Cliente> maioresClientes = listCliente.stream().filter(cliente -> cliente.credito == maxCredito).collect(Collectors.toList());
        gravador.println("Cliente(s) com mais crédito: ");
        maioresClientes.forEach(cliente -> {
            gravador.println("\t" + cliente.nome);
        });
        gravador.println();


        boolean allUnique = listCliente.stream().map(cliente -> cliente.cpf).allMatch(new HashSet<>()::add);
        gravador.println("Existem clientes duplicados: " + (!allUnique ? "Sim" : "Não") + "\n");

        NumberFormat formatter = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale ("pt", "BR")));
        double soma = listCliente.stream().mapToDouble(cliente -> cliente.credito).sum();
        gravador.print("Valor total de crédito: R$" + formatter.format(soma));

        gravador.close();

        System.out.println("Resultado do processamento gravado em \"relatorio_clientes.txt\"");
    }
}

package com.company.atv3;

import com.company.generics.FileProcessor;
import com.company.generics.Reporter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class At3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        URL url = At3.class.getResource("clientes.csv");
        List<Cliente> listCliente = FileProcessor.processar(Cliente.class, url, "com.company.atv3.Cliente");

        PrintWriter gravador = Reporter.create("./src/com/company/atv3/relatorio_clientes.txt");

        listCliente.sort((a, b) -> a.codigo - b.codigo); // menor pro maior
        gravador.println("Cliente mais antigo: " + listCliente.get(0).nome + "\n");

        List<Double> listaValores = listCliente.stream().map(cliente -> cliente.credito).collect(Collectors.toList());
        double maxCredito = Collections.max(listaValores);
        List<Cliente> maioresClientes = listCliente.stream().filter(cliente -> cliente.credito == maxCredito).collect(Collectors.toList());
        gravador.println("Cliente(s) com mais crédito: ");
        maioresClientes.forEach(cliente -> gravador.println("\t" + cliente.nome));
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

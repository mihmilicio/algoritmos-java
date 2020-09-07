package com.company.atv3;

import com.company.generics.FileProcessor;
import com.company.generics.Reporter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class At3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        URL url = At3.class.getResource("clientes.csv");
        List<Cliente> listCliente = FileProcessor.processar(Cliente.class, url, "com.company.atv3.Cliente", ";", true);

        PrintWriter gravador = Reporter.create("./src/com/company/atv3/relatorio_clientes.txt");

        Relatorio.processar(listCliente, gravador);

        gravador.close();

        System.out.println("Resultado do processamento gravado em \"relatorio_clientes.txt\"");
    }
}

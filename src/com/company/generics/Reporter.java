package com.company.generics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Reporter {
    private static PrintWriter gravador;

    public static PrintWriter create (String url) throws IOException {
        FileWriter arquivoOut = new FileWriter(url);
        gravador = new PrintWriter(arquivoOut);
        return gravador;
    }
}

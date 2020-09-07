package com.company.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {
    public static <T> List<T> processar (Class<T> type, URL url, String className) throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File arquivo = new File(url.getPath());
        Scanner leitor = new Scanner(new FileInputStream(arquivo));
        leitor.nextLine();

        List<T> genList = new ArrayList<>();

        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor(String[].class);

        while (leitor.hasNextLine()) {
            String[] conteudo = leitor.nextLine().split(";");
            if (conteudo.length > 1) {
                genList.add((T) constructor.newInstance((Object) conteudo));
            }
        }
        leitor.close();

        return genList;
    }
}

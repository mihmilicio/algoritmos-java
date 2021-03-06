package com.company.atv4;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static com.company.atv4.At4.invalidOptionMessage;

public class Menus {
    private static List<List<Produto>> listCompleta = new ArrayList<>();


    private static void printPrincipal () {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("O que você deseja?");
        System.out.println("[1] - Soft Drinks");
        System.out.println("[2] - Vinhos");
        System.out.println("[3] - Pratos");
        System.out.println("[4] - Consultar carrinho");
        System.out.println("[5] - Enviar pedido");
        System.out.println("[6] - Consultar pedidos registrados");
        System.out.println("[7] - Encerrar :(");
    }

    public static int menuPrincipal () throws InterruptedException, IOException {
        int op;
        int retorno = 0;
        do {
            printPrincipal();

            op = At4.input.nextInt();

            if (op < 1 || op > 7) {
                System.out.println(invalidOptionMessage);
            } else {
                retorno = At4.getOptionPrincipal(op);
            }
        } while (op > 7 || op < 1 || retorno == 1);

        return retorno;

    }

    public static void printCategoria (int catPos) {
        String banner;
        if (catPos == 0) {
            banner = "|           SOFT DRINKS           |";
        } else if (catPos == 1) {
            banner = "|              VINHOS             |";
        } else {
            banner = "|              PRATOS             |";
        }
        System.out.println("  _______________________________ ");
        System.out.println(" /                               \\");
        System.out.println(banner);
        System.out.println(" \\_______________________________/");
        System.out.println();

        int listSize = listCompleta.get(catPos).size();
        IntStream.range(0, listSize).forEach(index -> {
            Produto produto = listCompleta.get(catPos).get(index);
            System.out.println("["+ (index + 1) +"] - " + produto.nome + "\t\t( R$ " + At4.formatter.format(produto.preco) + " )");
        });
        System.out.println("["+ (listSize + 1) +"] - Voltar ao menu principal");
    }

    public static void printEnviarPedido () {
        System.out.println("[1] - Continuar comprando");
        System.out.println("[2] - Adicionar observação ao pedido");
        System.out.println("[3] - Enviar");
        System.out.println("[4] - Cancelar pedido e encerrar");
    }


    public static int iniciar (List<List<Produto>> list) throws InterruptedException, IOException {
        System.out.println("Bem-vindo ao restaurante!");

        listCompleta = list;

        return menuPrincipal();
    }
}

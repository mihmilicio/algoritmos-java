package com.company.atv3;

public class Cliente {
    public String cpf;
    public String nome;
    public int codigo;
    public double credito;

    public Cliente(String[] conteudo) {
        this.cpf = conteudo[0];
        this.nome = conteudo[1];
        this.codigo = Integer.parseInt(conteudo[2]);
        this.credito = Double.parseDouble(conteudo[3].replace(",", "."));
    }
}

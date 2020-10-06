package com.company.sistema;

import java.sql.*;

public class Programa {
    public static void main(String[] args) throws SQLException {
        listar();

        incluir("Milena", 19);
        listar();

        atualizar(2, null, 50);
        atualizar(3, "Fabiane Silveira", -1);
        atualizar(5, "Matheus Luis", 10);
        listar();

        deletar(8);
        listar();

    }

    private static void listar () throws SQLException {
        String serverName = "localhost:3306";    //caminho do servidor do BD
        String mydatabase = "pessoas";        //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";        //nome de um usuário de seu BD
        String password = "123456";      //sua senha de acesso
        Connection con = DriverManager.getConnection(url, username, password);

        Statement statement = con.createStatement();
        String sql = "select * from dados";
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            int id = result.getInt("id");
            String nome = result.getString("nome");
            int idade = result.getInt("idade");

            System.out.println("id: "+ id + ";\tnome: "+ nome + ";\tidade: "+ idade+ ";");
        }

        con.close();
    }

    private static void incluir (String nome, int idade) throws SQLException {
        String serverName = "localhost:3306";    //caminho do servidor do BD
        String mydatabase = "pessoas";        //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";        //nome de um usuário de seu BD
        String password = "123456";      //sua senha de acesso
        Connection con = DriverManager.getConnection(url, username, password);

        String sql = "insert into dados (nome, idade) values (?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setInt(2, idade);

        int retorno = statement.executeUpdate();
        System.out.println("Retorno: "+ retorno);

        statement.close();
        con.close();
    }

    // set parameter as null/-1 to keep as it is
    private static void atualizar (int id, String nome, int idade) throws SQLException {
        String serverName = "localhost:3306";    //caminho do servidor do BD
        String mydatabase = "pessoas";        //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";        //nome de um usuário de seu BD
        String password = "123456";      //sua senha de acesso
        Connection con = DriverManager.getConnection(url, username, password);

        String sql;
        if (nome != null && idade != -1) {
            sql = "update dados set nome = ?, idade = ? where id = ?;";
        } else if (nome != null) {
            sql = "update dados set nome = ? where id = ?;";
        } else {
            sql = "update dados set idade = ? where id = ?;";
        }
        PreparedStatement statement = con.prepareStatement(sql);

        if (nome != null && idade != -1) {
            statement.setString(1, nome);
            statement.setInt(2, idade);
            statement.setInt(3, id);
        } else if (nome != null) {
            statement.setString(1, nome);
            statement.setInt(2, id);
        } else {
            statement.setInt(1, idade);
            statement.setInt(2, id);
        }

        int retorno = statement.executeUpdate();
        System.out.println("Retorno: "+ retorno);

        statement.close();
        con.close();
    }

    private static void deletar (int id) throws SQLException {
        String serverName = "localhost:3306";    //caminho do servidor do BD
        String mydatabase = "pessoas";        //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";        //nome de um usuário de seu BD
        String password = "123456";      //sua senha de acesso
        Connection con = DriverManager.getConnection(url, username, password);

        String sql = "delete from dados where id = ?;";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);

        int retorno = statement.executeUpdate();
        System.out.println("Retorno: "+ retorno);

        statement.close();
        con.close();
    }
}

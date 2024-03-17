package com.challenge.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    // configuracao usada para conectar com o banco de dados oracle
    private static final String USER ="system";
    private static final String PASS = "oracle";
    private static final String SERVER = "localhost";
    private static final String PORT = "1521";
    private static final String DATABASE = "xe";
    public static Connection getConnection() throws SQLException {
        //fazer a conexao do banco de dados atraves do jdbc de url
        String url = "jdbc:oracle:thin:@"+SERVER+":"+PORT+":"+DATABASE;
        Connection connection = DriverManager.getConnection(url, USER, PASS);
        //conectar com o schema criado la no dbeaver
        connection.createStatement().execute("alter session set current_schema=SORTEIO");

        return connection;
    }
}

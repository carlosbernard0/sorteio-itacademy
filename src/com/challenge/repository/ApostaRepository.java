package com.challenge.repository;

import com.challenge.entity.ApostaEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApostaRepository {
    public ApostaEntity salvarApostaDB(ApostaEntity aposta) {
        //abrir conexao
        Connection connection = null;
        try {
            //executar operacao
            connection = ConexaoDB.getConnection();

            String sqlSequence = "select seq_aposta.nextval proxval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer proximoId = -1;
            if (retorno.next()) {
                proximoId = retorno.getInt("proxval");
            }

            String sql = " INSERT INTO APOSTA " +
                    " (ID_APOSTA,NOME_APOSTADOR, CPF_APOSTADOR, NUMEROS_APOSTADOS)" +
                    " VALUES " +
                    " (?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setString(2, aposta.getNomeApostador());
            preparedStatement.setString(3, aposta.getCpfApostador());
            preparedStatement.setString(4, aposta.getNumerosApostados());

            int response = preparedStatement.executeUpdate();
            System.out.println("salvarApostaDB.resposta = " + response);

            aposta.setIdAposta(proximoId);
            return aposta;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //fechar conexao
            try{
                if(connection != null && !connection.isClosed()){
                    connection.close();
                };
            }catch (SQLException e){
                System.out.println("Falha ao fechar a conexao" +e.getMessage());
            }
        }
        return null;
    }

    public List<ApostaEntity> listarApostas() {
        //abrir conexao
        Connection connection = null;
        List<ApostaEntity> listaApostas = new ArrayList<>();
        try {
            //executar operacao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM APOSTA";

            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);



            while (res.next()){
                ApostaEntity aposta = new ApostaEntity();
                aposta.setIdAposta(res.getInt("id_aposta"));
                aposta.setNomeApostador(res.getString("nome_apostador"));
                aposta.setCpfApostador(res.getString("cpf_apostador"));
                aposta.setNumerosApostados(res.getString("numeros_apostados"));
                listaApostas.add(aposta);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                ;
            } catch (SQLException e) {
                System.out.println("Falha ao fechar a conexao" + e.getMessage());
            }
        }
        return listaApostas;
    }

    public void excluir(){
        Connection connection = null;
        try{
            connection = ConexaoDB.getConnection();

            String sql = "DROP TABLE APOSTA;";
            // Cria a string SQL para criar a tabela
            String sql2= "CREATE TABLE Aposta (\n" +
                    "  id_aposta NUMBER(38) PRIMARY KEY,\n" +
                    "  nome_apostador VARCHAR2(50) NULL,\n" +
                    "  cpf_apostador VARCHAR2(50) NULL,\n" +
                    "  numeros_apostados VARCHAR2(50) NULL\n" +
                    ");";
            Statement statement = connection.createStatement();
//            statement.executeQuery(sql);
//            System.out.println("");
            statement.executeQuery(sql2);
            System.out.println("Tabela criada");



        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                ;
            } catch (SQLException e) {
                System.out.println("Falha ao fechar a conexao" + e.getMessage());
            }
        }
    }
}

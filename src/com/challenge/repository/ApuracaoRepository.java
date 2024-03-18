package com.challenge.repository;

import com.challenge.entity.ApuracaoEntity;

import java.sql.*;

public class ApuracaoRepository {
    public ApuracaoEntity salvarApuracaoDB(ApuracaoEntity apuracao) {
        //abrir conexao
        Connection connection = null;
        try {
            //executar operacao
            connection = ConexaoDB.getConnection();

            String sqlSequence = "select seq_apuracao.nextval proxval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer proximoId = -1;
            if (retorno.next()) {
                proximoId = retorno.getInt("proxval");
            }

            String sql = " INSERT INTO APURACAO " +
                    " (ID_APURACAO,ID_SORTEIO, DATA_REALIZADA, APOSTAS_VENCEDORAS, ID_APOSTA)" +
                    " VALUES " +
                    " (?,?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setInt(2, apuracao.getSorteio().getIdSorteio());
            preparedStatement.setDate(3, new Date(apuracao.getDataRealizada().getTime()));
            preparedStatement.setString(4, apuracao.getApostasVencedoras());
            preparedStatement.setInt(5, apuracao.getAposta().getIdAposta());

            int response = preparedStatement.executeUpdate();
            System.out.println("salvarApuracaoDB.resposta = " + response);

            apuracao.setIdAposta(proximoId);
            return apuracao;

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
}

package com.challenge.repository;

import com.challenge.entity.PremioEntity;
import com.challenge.entity.SorteioEntity;

import java.sql.*;

public class PremioRepository {
    public PremioEntity salvarPremioDB(PremioEntity premio) {
        //abrir conexao
        Connection connection = null;
        try {
            //executar operacao
            connection = ConexaoDB.getConnection();

            String sqlSequence = "select seq_premio.nextval proxval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer proximoId = -1;
            if (retorno.next()) {
                proximoId = retorno.getInt("proxval");
            }

            String sql = " INSERT INTO PREMIO " +
                    " (ID_PREMIO,VALOR_PREMIO,NOME_PREMIO,ID_APOSTA)" +
                    " VALUES " +
                    " (?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setInt(2, premio.getValorPremio());
            preparedStatement.setString(3, premio.getNomePremio());
            preparedStatement.setInt(4, premio.getAposta().getIdAposta());

            int response = preparedStatement.executeUpdate();
            System.out.println("salvarPremioDB.resposta = " + response);

            premio.setIdPremio(proximoId);
            return premio;

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

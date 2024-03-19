package com.challenge.repository;

import com.challenge.entity.ApostaEntity;
import com.challenge.entity.SorteioEntity;

import java.sql.*;

public class SorteioRepository {
    public SorteioEntity salvarSorteioDB(SorteioEntity sorteio) {
        //abrir conexao
        Connection connection = null;
        try {
            //executar operacao
            connection = ConexaoDB.getConnection();

            String sqlSequence = "select seq_sorteio.nextval proxval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer proximoId = -1;
            if (retorno.next()) {
                proximoId = retorno.getInt("proxval");
            }

            String sql = " INSERT INTO SORTEIO " +
                    " (ID_SORTEIO,NUMEROS_SORTEADOS,TOTAL_APOSTAS,NUMEROS_DAS_APOSTAS)" +
                    " VALUES " +
                    " (?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setString(2, sorteio.getNumerosSorteados());
            preparedStatement.setInt(3, sorteio.getTotalApostas());
            preparedStatement.setString(4, sorteio.getNumerosApostas());

            int response = preparedStatement.executeUpdate();
            System.out.println("salvarSorteioDB.resposta = " + response);

            sorteio.setIdSorteio(proximoId);
            return sorteio;

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

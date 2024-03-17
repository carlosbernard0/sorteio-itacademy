package com.challenge.view;

import com.challenge.entity.ApostaEntity;
import com.challenge.service.ApostaService;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ApostaService apostaService = new ApostaService();
        int opcao = -1;

        while (opcao != 0){
            System.out.println("""
                    
                   ---Seja bem-vindo ao Sorteio Luck Choice---
                   
                   ---Escolha a Opção desejada---
                   0 - Sair
                   1 - Iniciar
                   2 - Registrar uma nova aposta
                   3 - Listar apostas
                   4 - Finalizar apostas e executar o sorteio
                   5 - Fim da apuração
                   6 - Premiação
                    """);
            opcao = input.nextInt();
            if (opcao >6 || opcao <0){
                System.out.println("Escolha uma opção válida!!");
                System.out.println();
            }
            input.nextLine();
            switch (opcao) {
                case 0:
                    opcao = 0;
                    break;
                case 1:

                    break;
                case 2:
                    ApostaEntity aposta = new ApostaEntity();

                    System.out.println("Insira seu nome:");
                    aposta.setNomeApostador(input.nextLine());

                    System.out.println("Insira seu cpf:");
                    aposta.setCpfApostador(input.nextLine());

                    System.out.println("A aposta é de 1 a 50 numeros...Deseja escolher os numeros para a aposta: s ou n");
                    String opcaoAposta = input.nextLine();
                    boolean deuCerto = false;
                    while (!opcaoAposta.equals("s") && !opcaoAposta.equals("n")){
                        System.out.println("Selecione 's' para Sim, e 'n' para Não");
                        opcaoAposta = input.nextLine();
                    }
                    if (opcaoAposta.equals("n")) {
                        Random random = new Random();
                        String[] apostas = new String[5];
                        for (int i = 0; i < 5; i++) {
                            String umaAposta = String.valueOf(random.nextInt(50) + 1);
                            apostas[i] = umaAposta;
                        }
                        System.out.println("Sua aposta 1 foi: " + apostas[0]);
                        System.out.println("Sua aposta 2 foi: " + apostas[1]);
                        System.out.println("Sua aposta 3 foi: " + apostas[2]);
                        System.out.println("Sua aposta 4 foi: " + apostas[3]);
                        System.out.println("Sua aposta 5 foi: " + apostas[4]);
                        String apostaAleatoria = apostas[0] + "," + apostas[1] + "," + apostas[2] + "," + apostas[3] + "," + apostas[4];
                        aposta.setNumerosApostados(apostaAleatoria);
                        deuCerto = true;
                    }
                    while (!deuCerto){
                        if (opcaoAposta.equals("s")) {
                            System.out.println("Digite 5 numeros separados por , para adicionar a aposta:");
                            String numerosEscolhidos = input.nextLine();
                            String[] numerosSeparados = numerosEscolhidos.split(",");
                            for (int i = 0; i < numerosSeparados.length; i++) {
                                int numero = Integer.parseInt(numerosSeparados[i]);
                                if (numero > 50 || numero < 1 || numerosSeparados.length != 5) {
                                    System.out.println("Escolha numeros que se encaixem no requisito!");
                                    deuCerto = false;
                                } else {
                                    aposta.setNumerosApostados(numerosEscolhidos);
                                    deuCerto = true;
                                }
                            }
                        }
                    }




                    try{
                       ApostaEntity apostaSalva = apostaService.salvarAposta(aposta);
                        System.out.println("Aposta salva, id = "+ apostaSalva.getIdAposta());
                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }



                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;

            }
        }
    }
}

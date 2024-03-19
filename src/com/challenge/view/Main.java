package com.challenge.view;

import com.challenge.entity.ApostaEntity;
import com.challenge.entity.ApuracaoEntity;
import com.challenge.entity.SorteioEntity;
import com.challenge.service.ApostaService;
import com.challenge.service.ApuracaoService;
import com.challenge.service.SorteioService;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ApostaService apostaService = new ApostaService();
        SorteioService sorteioService = new SorteioService();
        ApuracaoService apuracaoService = new ApuracaoService();
        List<String> listaDeTodosNumApostadosParaOSorteio = new ArrayList<>();
        List<String> listaDeTodosIdsApostadosParaOSorteio = new ArrayList<>();
        List<ApostaEntity> listaDeTodasAsApostas = new ArrayList<>();
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
                    System.out.println(listaDeTodosNumApostadosParaOSorteio);
                    System.out.println(listaDeTodosNumApostadosParaOSorteio.size());
//                    apostaService.excluir();
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
                            for (int j = 0; j < 5; j++) {
                               if (!umaAposta.equals(apostas[j]) ){
                                   apostas[i] = umaAposta;
                               }
                            }
                        }
                        System.out.println("Sua aposta 1 foi: " + apostas[0]);
                        System.out.println("Sua aposta 2 foi: " + apostas[1]);
                        System.out.println("Sua aposta 3 foi: " + apostas[2]);
                        System.out.println("Sua aposta 4 foi: " + apostas[3]);
                        System.out.println("Sua aposta 5 foi: " + apostas[4]);
                        Integer[] numerosInteger = new Integer[apostas.length];
                        for (int j = 0; j < apostas.length; j++) {
                            numerosInteger[j] = Integer.valueOf(apostas[j]);
                        }
                        Arrays.sort(numerosInteger);
                        String[] numerosStringArray = new String[numerosInteger.length];
                        for (int i = 0; i < numerosInteger.length; i++) {
                            numerosStringArray[i] = String.valueOf(numerosInteger[i]);
                        }
                        String numerosString = numerosStringArray[0] + "," + numerosStringArray[1] + "," + numerosStringArray[2] + "," + numerosStringArray[3] + "," + numerosStringArray[4];
                        aposta.setNumerosApostados(numerosString);

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
                                    Integer[] numerosInteger = new Integer[numerosSeparados.length];
                                    for (int j = 0; j < numerosSeparados.length; j++) {
                                        numerosInteger[j] = Integer.valueOf(numerosSeparados[j]);
                                    }
                                    Arrays.sort(numerosInteger);
                                    String[] numerosStringArray = new String[numerosInteger.length];
                                    for (int j = 0; j < numerosInteger.length; j++) {
                                        numerosStringArray[j] = String.valueOf(numerosInteger[j]);
                                    }
                                    String numerosString = numerosStringArray[0] + "," + numerosStringArray[1] + "," + numerosStringArray[2] + "," + numerosStringArray[3] + "," + numerosStringArray[4];
                                    aposta.setNumerosApostados(numerosString);
                                    deuCerto = true;
                                }
                            }
                        }
                    }
                    listaDeTodosNumApostadosParaOSorteio.add(aposta.getNumerosApostados());
                    listaDeTodosIdsApostadosParaOSorteio.add(String.valueOf(aposta.getIdAposta()));
                    listaDeTodasAsApostas.add(aposta);

                    try{
                       ApostaEntity apostaSalva = apostaService.salvarAposta(aposta);
                        System.out.println("Aposta salva, id = "+ apostaSalva.getIdAposta());
                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("---Apostas registradas até o momento--- ");
                    apostaService.listarApostas();
                    break;
                case 4:
                    System.out.println("Você deseja realizar o sorteio? Digite 's' para Sim");
                    String opcaoSorteio = input.nextLine();
                    while (!opcaoSorteio.equals("s")){
                        System.out.println("Deseja voltar ao menu? Digite 'menu' para voltar ao Menu de opções");
                        opcaoSorteio = input.nextLine();
                        if (opcaoSorteio.equals("menu")){
                            opcaoSorteio = "sair";
                            break;
                        }
                    }
                    if (opcaoSorteio.equals("sair")){
                        break;
                    }

                    //inicio do sorteio:
                    SorteioEntity sorteio = new SorteioEntity();
                    int qntdDeNumerosDoSorteio = 5;

                    Random random = new Random();
                    String[] sorteioNumerosSeparadosNoArray = new String[5];
                    for (int i = 0; i < 5; i++) {
                        String umNumeroSorteio = String.valueOf(random.nextInt(50) + 1);
                        for (int j = 0; j < 5; j++) {
                            if (umNumeroSorteio != sorteioNumerosSeparadosNoArray[j]){
                                sorteioNumerosSeparadosNoArray[i] = umNumeroSorteio;
                            }
                        }

                    }
                    Integer[] numerosInteger = new Integer[sorteioNumerosSeparadosNoArray.length];
                    for (int j = 0; j < sorteioNumerosSeparadosNoArray.length; j++) {
                        numerosInteger[j] = Integer.valueOf(sorteioNumerosSeparadosNoArray[j]);
                    }
                    Arrays.sort(numerosInteger);
                    String[] numerosStringArray = new String[numerosInteger.length];
                    for (int j = 0; j < numerosInteger.length; j++) {
                        numerosStringArray[j] = String.valueOf(numerosInteger[j]);
                    }
                    String sorteioNumerosAleatorio = numerosStringArray[0] + "," + numerosStringArray[1] + "," + numerosStringArray[2] + "," + numerosStringArray[3] + "," + numerosStringArray[4];
                    sorteio.setNumerosSorteados(sorteioNumerosAleatorio);


                    try{
                        SorteioEntity sorteioSalvo = sorteioService.salvarSorteio(sorteio);
                        System.out.println("Sorteio salvo, id = "+ sorteioSalvo.getIdSorteio());

                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }

                    //Inicio da apuracao
                    ApuracaoEntity apuracao = new ApuracaoEntity();
                    boolean achouVencedor = false;
                    int qntdDeVezes = 1;
                    while (!achouVencedor){


                        for (int i = 0; i < listaDeTodosNumApostadosParaOSorteio.size(); i++) {
                            //verificando se o numero da aposta é igual ao numero sorteadoapostada
                            if(listaDeTodasAsApostas.get(i).getNumerosApostados().equals(sorteio.getNumerosSorteados())){
                                apuracao.setAposta(listaDeTodasAsApostas.get(i));
                                apuracao.setIdAposta(listaDeTodasAsApostas.get(i).getIdAposta());
                                apuracao.setApostasVencedoras(listaDeTodasAsApostas.get(i).getNumerosApostados());
                                achouVencedor = true;
                            }else {
                                while (qntdDeVezes != 25){
                                    //processo de adicionar mais um numero nos numeros sorteados e ordenar de forma crescente
                                    List<String> numerosSorteadosStr= new ArrayList<>(List.of(sorteio.getNumerosSorteados().split(",")));
                                    numerosSorteadosStr.add(String.valueOf(random.nextInt((50)+1)));

                                    Integer[] numerosSorteadosInt = new Integer[numerosSorteadosStr.size()];
                                    for (int d = 0; d < numerosSorteadosStr.size(); d++) {
                                        numerosSorteadosInt[d] = Integer.valueOf(numerosSorteadosStr.get(d));
                                    }
                                    Arrays.sort(numerosSorteadosInt);
                                    String[] numerosPorOrdemSorteadosStr = new String[numerosSorteadosInt.length];
                                    String numFinalDeSorteio= "";
                                    for (int j = 0; j < numerosSorteadosInt.length; j++) {
                                        numerosPorOrdemSorteadosStr[j] = String.valueOf(numerosSorteadosInt[j]);
                                        if (numerosSorteadosInt[j] == numerosSorteadosInt.length-1){
                                            numFinalDeSorteio += numerosPorOrdemSorteadosStr[j];
                                        }else {
                                            numFinalDeSorteio += numerosPorOrdemSorteadosStr[j]+",";
                                        }

                                    }
                                    sorteio.setNumerosSorteados(numFinalDeSorteio);

                                    apuracao.setApostasVencedoras(sorteio.getNumerosSorteados());

                                    //ver se os numeros apostados sao iguais aos numeros sorteados

//                                    for (int j = 0; j <5; j++) {
//                                        if (arrayDosNumerosDasApostas[j].equals(numerosSorteadosInt[j])){
//                                            String[] arrayParaSalvar =new String[Integer.parseInt(listaDeTodasAsApostas.get(j).getNumerosApostados())];
//                                            String strParaApuracao= " ";
//                                            if (arrayParaSalvar[j] == arrayParaSalvar[4]){
//                                                strParaApuracao = String.valueOf(numerosSorteadosInt[j]);
//                                            }else {
//                                                strParaApuracao = numerosSorteadosInt[j]+",";
//                                            }
//                                            apuracao.setApostasVencedoras(strParaApuracao);
//                                            apuracao.setAposta(listaDeTodasAsApostas.get(i));
//                                            apuracao.setIdAposta(listaDeTodasAsApostas.get(i).getIdAposta());
//                                            achouVencedor = true;
//                                        }else {
//                                            System.out.println("Nao encontramos um vencedor");
//                                        }
//                                    }


                                    qntdDeVezes++;

                                }

                            }

                        }
                    }

                    //id_sorteio de apuracao
                    apuracao.setSorteio(sorteio);
                    apuracao.setIdSorteio(sorteio.getIdSorteio());
                    //data_realizada
                    Date dataAtual = new Date();
                    apuracao.setDataRealizada(dataAtual);


                    try{
                        ApuracaoEntity apuracaoSalva = apuracaoService.salvarApuracao(apuracao);
                        System.out.println("Apuracao salva, id = "+ apuracaoSalva.getIdApuracao());

                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }


                    break;
                case 5:
                    break;
                case 6:
                    break;

            }
        }


    }
}



package com.challenge.view;

import com.challenge.entity.ApostaEntity;
import com.challenge.entity.ApuracaoEntity;
import com.challenge.entity.SorteioEntity;
import com.challenge.service.ApostaService;
import com.challenge.service.ApuracaoService;
import com.challenge.service.SorteioService;


import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ApostaService apostaService = new ApostaService();
        SorteioService sorteioService = new SorteioService();
        ApuracaoService apuracaoService = new ApuracaoService();
        List<List<String>> listaDeTodosNumApostadosParaOSorteio = new ArrayList<>();
        List<String> listaDeTodosIdsApostadosParaOSorteio = new ArrayList<>();
        List<ApostaEntity> listaDeTodasAsApostas = new ArrayList<>();
        List<ApuracaoEntity> listaDaApuracao = new ArrayList<>();
        int idDaApuracaoFimApuracao = 0;

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
                    System.out.println(listaDeTodosNumApostadosParaOSorteio.get(0));


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
                    listaDeTodosNumApostadosParaOSorteio.add(Collections.singletonList(aposta.getNumerosApostados()));
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
                    Integer[] numerosSorteadosInteger = new Integer[sorteioNumerosSeparadosNoArray.length];
                    for (int j = 0; j < sorteioNumerosSeparadosNoArray.length; j++) {
                        numerosSorteadosInteger[j] = Integer.valueOf(sorteioNumerosSeparadosNoArray[j]);
                    }
                    Arrays.sort(numerosSorteadosInteger);
                    String[] numerosStringArray = new String[numerosSorteadosInteger.length];
                    for (int j = 0; j < numerosSorteadosInteger.length; j++) {
                        numerosStringArray[j] = String.valueOf(numerosSorteadosInteger[j]);
                    }
                    String sorteioNumerosAleatorio = numerosStringArray[0] + "," + numerosStringArray[1] + "," + numerosStringArray[2] + "," + numerosStringArray[3] + "," + numerosStringArray[4];
//                    sorteio.setNumerosSorteados(sorteioNumerosAleatorio);
                    sorteio.setNumerosSorteados("1,2,3,4,5");

                    sorteio.setTotalApostas(listaDeTodosNumApostadosParaOSorteio.size());
                    sorteio.setNumerosApostas(String.valueOf(listaDeTodosNumApostadosParaOSorteio));

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
                    while (!achouVencedor || qntdDeVezes == 25){
                        String listaDeNumerosApostados = sorteio.getNumerosApostas();
                        String[] numerosApostados = listaDeNumerosApostados.split("[*]");


                        for (int i = 0; i < numerosApostados.length; i++) {
                            System.out.println("tamanho da variavel numerosApostados = "+numerosApostados.length);
                            System.out.println(numerosApostados[i]);
                            if (Objects.equals(numerosApostados[i], sorteio.getNumerosSorteados())){
                                System.out.println("deu certo");
                                achouVencedor= true;
                            };

                        }

                        qntdDeVezes++;

                    }

                    //id_sorteio de apuracao
                    apuracao.setSorteio(sorteio);
                    apuracao.setIdSorteio(sorteio.getIdSorteio());
                    //data_realizada
                    Date dataAtual = new Date();
                    apuracao.setDataRealizada(dataAtual);

                    listaDaApuracao.add(apuracao);


                    try{
                        ApuracaoEntity apuracaoSalva = apuracaoService.salvarApuracao(apuracao);
                        idDaApuracaoFimApuracao = apuracaoSalva.getIdApuracao();
                        System.out.println("Apuracao salva, id = "+ apuracaoSalva.getIdApuracao());

                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }


                    break;
                case 5:
                    System.out.println("Os numeros sorteados foram: " + listaDaApuracao.get(idDaApuracaoFimApuracao).getApostasVencedoras());


                    break;
                case 6:
                    break;

            }
        }


    }
}



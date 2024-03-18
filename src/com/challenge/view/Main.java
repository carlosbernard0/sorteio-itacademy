package com.challenge.view;

import com.challenge.entity.ApostaEntity;
import com.challenge.entity.ApuracaoEntity;
import com.challenge.entity.SorteioEntity;
import com.challenge.service.ApostaService;
import com.challenge.service.ApuracaoService;
import com.challenge.service.SorteioService;

import java.sql.SQLException;
import java.sql.SQLOutput;
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
                        String numerosString = numerosInteger[0] + "," + numerosInteger[1] + "," + numerosInteger[2] + "," + numerosInteger[3] + "," + numerosInteger[4];
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
                                    String numerosString = numerosInteger[0] + "," + numerosInteger[1] + "," + numerosInteger[2] + "," + numerosInteger[3] + "," + numerosInteger[4];
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
                    SorteioEntity sorteio = new SorteioEntity();

                    Random random = new Random();
                    String[] sorteios = new String[5];
                    for (int i = 0; i < 5; i++) {
                        String umNumeroSorteio = String.valueOf(random.nextInt(50) + 1);
                        for (int j = 0; j < 5; j++) {
                            if (umNumeroSorteio != sorteios[j]){
                                sorteios[i] = umNumeroSorteio;
                            }
                        }

                    }
                    Integer[] numerosInteger = new Integer[sorteios.length];
                    for (int j = 0; j < sorteios.length; j++) {
                        numerosInteger[j] = Integer.valueOf(sorteios[j]);
                    }
                    Arrays.sort(numerosInteger);
                    String sorteioNumerosAleatorio = numerosInteger[0] + "," + numerosInteger[1] + "," + numerosInteger[2] + "," + numerosInteger[3] + "," + numerosInteger[4];
                    sorteio.setNumerosSorteados(sorteioNumerosAleatorio);


                    ApuracaoEntity apuracao = new ApuracaoEntity();
                    boolean achouVencedor = false;
                    int qntdDeVezes = 1;
                    while (!achouVencedor || qntdDeVezes == 25){
                        int quantidadeDeApostasRealizadas = listaDeTodosNumApostadosParaOSorteio.size();
                        for (int i = 0; i < quantidadeDeApostasRealizadas; i++) {
                            String[][] apostaDeCincoNum = new String[5][quantidadeDeApostasRealizadas];
                            apostaDeCincoNum[i][i] = listaDeTodosNumApostadosParaOSorteio.get(i);
                            if (apostaDeCincoNum[i][i] == sorteio.getNumerosSorteados()){
                                //setando a aposta vencedora noo idAposta da apuracao
                                ApostaEntity apostaVencedora = listaDeTodasAsApostas.get(i);
                                apuracao.setAposta(apostaVencedora);
                                apuracao.setIdAposta(apostaVencedora.getIdAposta());
                                //setando os numeros das apostas no Atributo apostasVencedoreas da apuracao
                                apuracao.setApostasVencedoras(apostaDeCincoNum[i][i]);
                                achouVencedor = true;
                            }else {
                                apuracao.setAposta(listaDeTodasAsApostas.get(0));
                                apuracao.setIdAposta(listaDeTodasAsApostas.get(0).getIdAposta());
                                apuracao.setApostasVencedoras(sorteio.getNumerosSorteados());
                                System.out.println("Nenhuma aposta conseguiu chegar nos numeros sorteados");
                                qntdDeVezes++;
                                if (qntdDeVezes == 25){
                                    achouVencedor= true;
                                }
                                sorteio.setNumerosSorteados(String.valueOf(random.nextInt(50) + 1));
                            }
                        }
                    }

                    apuracao.setSorteio(sorteio);
                    apuracao.setIdSorteio(sorteio.getIdSorteio());

                    Date dataAtual = new Date();
                    apuracao.setDataRealizada(dataAtual);


                    try{
                        SorteioEntity sorteioSalvo = sorteioService.salvarSorteio(sorteio);
                        System.out.println("Sorteio salva, id = "+ sorteioSalvo.getIdSorteio());
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

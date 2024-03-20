package com.challenge.view;

import com.challenge.entity.ApostaEntity;
import com.challenge.entity.ApuracaoEntity;
import com.challenge.entity.PremioEntity;
import com.challenge.entity.SorteioEntity;
import com.challenge.service.ApostaService;
import com.challenge.service.ApuracaoService;
import com.challenge.service.PremioService;
import com.challenge.service.SorteioService;


import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ApostaService apostaService = new ApostaService();
        SorteioService sorteioService = new SorteioService();
        ApuracaoService apuracaoService = new ApuracaoService();
        PremioService premioService = new PremioService();
        List<List<String>> listaDeTodosNumApostadosParaOSorteio = new ArrayList<>();
        List<String> listaDeTodosIdsApostadosParaOSorteio = new ArrayList<>();
        List<ApostaEntity> listaDeTodasAsApostas = new ArrayList<>();
        List<SorteioEntity>listaDeTodosOsSorteios = new ArrayList<>();
        List<ApuracaoEntity> listaDaApuracao = new ArrayList<>();
        int idDaApuracao =0;
        int posicaoDaApostaVencedora = -1;
        int quantidadeDeRodadas = 0;
        int opcao = -1;
        List<Integer> numerosSorteadosParaApuracao = new ArrayList<>();
        List<String> listaDasApostasVencedorasFinais = new ArrayList<>();

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
                        numerosSorteadosParaApuracao.add(numerosSorteadosInteger[j]);

                    }
                    String sorteioNumerosAleatorio = numerosStringArray[0] + "," + numerosStringArray[1] + "," + numerosStringArray[2] + "," + numerosStringArray[3] + "," + numerosStringArray[4];
//                    sorteio.setNumerosSorteados(sorteioNumerosAleatorio);
                    sorteio.setNumerosSorteados("1,2,3,4,5");

                    sorteio.setTotalApostas(listaDeTodosNumApostadosParaOSorteio.size());
                    sorteio.setNumerosApostas(String.valueOf(listaDeTodosNumApostadosParaOSorteio));
                    listaDeTodosOsSorteios.add(sorteio);

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
                    quantidadeDeRodadas = qntdDeVezes;
                    List<Integer> numerosSorteadosInt = new ArrayList<>();

                    //Usados para separar os numeros do sorteio em inteiro numerosSorteadosInt
                    String[] numerosSorteados = sorteio.getNumerosSorteados().split(",");
                    for (int i = 0; i < numerosSorteados.length; i++) {
                        numerosSorteadosInt.add(Integer.valueOf(numerosSorteados[i]));
                    }
                    while (!achouVencedor){

                        if (qntdDeVezes != 1){
                            numerosSorteadosInt.add(random.nextInt(50)+1);
                        }
                        System.out.println(numerosSorteadosInt);
                        System.out.println(qntdDeVezes);

                        //Usados para separar a numeros das apostas
                        String listaDeNumerosApostados = sorteio.getNumerosApostas();
                        String[] numerosApostadosComColchetes = listaDeNumerosApostados.split("], \\[");
                        String[] numerosApostadosSemColchetes = new String[5];
                        List<Integer> listaDosNumerosApostadosEmInt = new ArrayList<>();


                        for (int i = 0; i < numerosApostadosComColchetes.length; i++) {
                           numerosApostadosComColchetes[i] = numerosApostadosComColchetes[i].replace("[", "");
                           numerosApostadosComColchetes[i] = numerosApostadosComColchetes[i].replace("]", "");
                           numerosApostadosSemColchetes = numerosApostadosComColchetes[i].split(",");
                           //variavel numerosApostadosSemColchetes esta com os numeros separados em String
                            for (int j = 0; j < numerosApostadosSemColchetes.length; j++) {
                                try {
                                    listaDosNumerosApostadosEmInt.add(Integer.valueOf(numerosApostadosSemColchetes[j]));

                                }catch (NumberFormatException e){
                                    System.out.println(e.getMessage());
                                    System.out.println("Voce precisa colocar algum valor nas apostas");
                                }
                            }
                            for (int k = 0; k < 1; k++) {
                                Integer[] listaDosUltimosNumerosDasApostas = new Integer[5];
                                listaDosUltimosNumerosDasApostas[0] = listaDosNumerosApostadosEmInt.get(listaDosNumerosApostadosEmInt.size()-5);
                                listaDosUltimosNumerosDasApostas[1] = listaDosNumerosApostadosEmInt.get(listaDosNumerosApostadosEmInt.size()-4);
                                listaDosUltimosNumerosDasApostas[2] = listaDosNumerosApostadosEmInt.get(listaDosNumerosApostadosEmInt.size()-3);
                                listaDosUltimosNumerosDasApostas[3] = listaDosNumerosApostadosEmInt.get(listaDosNumerosApostadosEmInt.size()-2);
                                listaDosUltimosNumerosDasApostas[4] = listaDosNumerosApostadosEmInt.get(listaDosNumerosApostadosEmInt.size()-1);
                                //verifica se os numeros sao iguais
                                if (qntdDeVezes != 1){
                                    int achouValores = 0;
                                    for (int j = 0; j < numerosSorteadosInt.size(); j++) {
                                        int valoreDaLista = numerosSorteadosInt.get(j);
                                        if(listaDosUltimosNumerosDasApostas[k]==valoreDaLista){
                                            achouValores++;
                                        }
                                        if (achouValores == 5){
                                            achouVencedor = true;
                                            posicaoDaApostaVencedora = i;
                                            apuracao.setApostasVencedoras(Arrays.toString(listaDosUltimosNumerosDasApostas));
                                        }
                                    }
                                }else {
                                    if (Objects.equals(listaDosUltimosNumerosDasApostas[k], numerosSorteadosInt.get(k))){
                                        achouVencedor = true;
                                        posicaoDaApostaVencedora = i;
                                        apuracao.setApostasVencedoras(Arrays.toString(listaDosUltimosNumerosDasApostas));
                                    }
                                }

                            }
                        }
                        System.out.println("Achamos um vencedor: "+achouVencedor);

                        qntdDeVezes++;
                        if (qntdDeVezes == 26){
                            apuracao.setAposta(listaDeTodasAsApostas.get(0));
                            apuracao.setApostasVencedoras(sorteio.getNumerosSorteados());
                            apuracao.setIdAposta(listaDeTodasAsApostas.get(0).getIdAposta());
                            achouVencedor= true;
                        }else {
                            //setando aposta da apuracao
                            for (int i = 0; i < listaDeTodasAsApostas.size(); i++) {
                                int posicaoVencedoraDaAposta = posicaoDaApostaVencedora;
                                if (posicaoVencedoraDaAposta == i){
                                    apuracao.setAposta(listaDeTodasAsApostas.get(i));
                                    apuracao.setIdAposta(listaDeTodasAsApostas.get(i).getIdAposta());
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

                    listaDaApuracao.add(apuracao);
                    idDaApuracao++;
                    listaDasApostasVencedorasFinais.add(apuracao.getApostasVencedoras());



                    try{
                        ApuracaoEntity apuracaoSalva = apuracaoService.salvarApuracao(apuracao);
                        System.out.println("Apuracao salva, id = "+ apuracaoSalva.getIdApuracao());

                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("A) Os numeros sorteados foram: "+ numerosSorteadosParaApuracao);
                    System.out.println("B) A quantidade de rodadas feitas foram: "+ quantidadeDeRodadas);
                    System.out.println("C) A quantidade de apostas vencedoras foi: " +1);
                    System.out.println("D) Lista das apostas vencedoras: " +listaDasApostasVencedorasFinais);
                    System.out.println("E) A lista de todos os números apostados: " +listaDeTodosNumApostadosParaOSorteio);
                    System.out.println("Nro apostado    Qtd de apostas");


                    System.out.println();



                    break;
                case 6:
                    PremioEntity premio = new PremioEntity();

                    premio.setAposta(listaDeTodasAsApostas.get(posicaoDaApostaVencedora));
                    premio.setIdAposta(listaDeTodasAsApostas.get(posicaoDaApostaVencedora).getIdAposta());

                    int opcaoPremio = 0;

                    while (opcaoPremio == 0){
                        System.out.println("Parabens ao "+ premio.getAposta().getNomeApostador()+ " voce ganhou o sorteio!!");;
                        System.out.println("""
                            Escolha algum dos premios para continuar
                            1 - 1 ano de academia free
                            2 - Notebook Dell
                            3 - Bicicleta Speed
                            4 - Curso de programação avançado
                            5 - Viagem para o Exterior
                            """);
                        opcaoPremio = input.nextInt();
                    }
                    switch (opcaoPremio){
                        case 1:
                            premio.setValorPremio(1420);
                            premio.setNomePremio("1 ano de academia free");
                            break;
                        case 2:
                            premio.setValorPremio(4500);
                            premio.setNomePremio("Notebook Dell");
                            break;
                        case 3:
                            premio.setValorPremio(2500);
                            premio.setNomePremio("Bicicleta Speed");
                            break;
                        case 4:
                            premio.setValorPremio(2000);
                            premio.setNomePremio("Curso de programação avançado");
                            break;
                        case 5:
                            premio.setValorPremio(11000);
                            premio.setNomePremio("Viagem para o Exterior");
                            break;
                    }

                    System.out.println("Legal então você escolheu o premio "+premio.getNomePremio()+" com o valor de " + premio.getValorPremio());

                    try{
                        PremioEntity premioSalvo = premioService.salvarPremio(premio);
                        System.out.println("Premio salvo, id = "+ premioSalvo.getIdPremio());

                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    break;

            }
        }
    }
}



package org.example.principal;

import org.example.ferramentas.ConsultaMoeda;
import org.example.ferramentas.GeradorDeConversao;
import org.example.ferramentas.GeradorDeArquivo; // Importando o seu novo gerador
import org.example.modelos.MoedaDTO;

import java.util.ArrayList; // Import necessário para a lista
import java.util.List;      // Import necessário para a lista
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaMoeda consulta = new ConsultaMoeda();
        GeradorDeConversao gerador = new GeradorDeConversao();
        GeradorDeArquivo geradorDeArquivo = new GeradorDeArquivo(); // Criando a ferramenta de salvar

        List<String> historico = new ArrayList<>(); // Nossa lista para guardar as conversões
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n***************************************************");
            System.out.println("BEM-VINDO AO CONVERSOR DE MOEDAS!");
            System.out.println("1) Dolar (USD) =>> Peso Argentino (ARS)");
            System.out.println("2) Peso Argentino (ARS) =>> Dolar (USD)");
            System.out.println("3) Dolar (USD) =>> Real Brasileiro (BRL)");
            System.out.println("4) Real Brasileiro (BRL) =>> Dolar (USD)");
            System.out.println("5) Dolar (USD) =>> Peso Colombiano (COP)");
            System.out.println("6) Peso Colombiano (COP) =>> Dolar (USD)");
            System.out.println("7) Sair e Salvar Histórico");
            System.out.print("Escolha uma opcao valida: ");

            try {
                if (leitura.hasNextInt()) {
                    opcao = leitura.nextInt();

                    if (opcao == 7) {
                        // [x] Criar o arquivo de Histórico ao sair
                        if (!historico.isEmpty()) {
                            geradorDeArquivo.salvaJson(new ArrayList<>(historico));
                        }
                        System.out.println("Finalizando o programa. Ate logo!");
                        break;
                    }

                    if (opcao >= 1 && opcao <= 6) {
                        System.out.print("Qual valor voce deseja converter? ");
                        double valor = leitura.nextDouble();

                        // Fazemos a conversão e pegamos o texto do resultado para o histórico
                        String resultadoTexto = executarConversao(opcao, valor, consulta, gerador);
                        historico.add(resultadoTexto); // Adiciona na nossa "caderneta"

                    } else {
                        System.out.println("Opcao invalida! Escolha de 1 a 7.");
                    }
                } else {
                    System.out.println("Erro: Por favor, digite apenas números inteiros para o menu.");
                    leitura.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[AVISO] Valor invalido! Use apenas numeros (ex: 100 ou 50,50).");
                leitura.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERRO] Nao foi possivel realizar a operacao: " + e.getMessage());
            }
        }
    }

    // Mudei o método para "String" para ele devolver o texto que será salvo no histórico
    private static String executarConversao(int opcao, double valor, ConsultaMoeda consulta, GeradorDeConversao gerador) {
        String de = "", para = "";
        switch (opcao) {
            case 1 -> { de = "USD"; para = "ARS"; }
            case 2 -> { de = "ARS"; para = "USD"; }
            case 3 -> { de = "USD"; para = "BRL"; }
            case 4 -> { de = "BRL"; para = "USD"; }
            case 5 -> { de = "USD"; para = "COP"; }
            case 6 -> { de = "COP"; para = "USD"; }
        }

        MoedaDTO dados = consulta.buscaMoeda(de);
        String mensagemFinal = "";

        if (dados != null && dados.conversion_rates() != null) {
            double taxa = dados.conversion_rates().get(para);
            double resultado = gerador.calcular(valor, taxa);

            mensagemFinal = "O valor " + valor + " [" + de + "] equivale a =>>> " + String.format("%.2f", resultado) + " [" + para + "]";
            System.out.println("\n***************************************************");
            System.out.println(mensagemFinal);
            System.out.println("***************************************************");
        }
        return mensagemFinal; // Devolve o texto para ser guardado na lista
    }
}
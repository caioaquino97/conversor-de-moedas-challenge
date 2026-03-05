package org.example.ferramentas;

public class GeradorDeConversao {

    // Esse método recebe o valor (ex: 100 reais) e a taxa (ex: 0.20)
    public double calcular(double valorParaConverter, double taxaDaMoeda) {
        return valorParaConverter * taxaDaMoeda;
    }

    // Dica extra: você pode usar este método para exibir uma mensagem bonitinha
    public void exibirResultado(String moedaAlvo, double resultado) {
        System.out.println("O valor convertido para " + moedaAlvo + " é: " + resultado);
    }
}
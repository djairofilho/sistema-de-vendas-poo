package org.example;

public class Cliente {
    private final String nome;
    private double totalGasto;
    private int quantidadeCompras;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public void adicionarCompra(double valor) {
        totalGasto += valor;
        quantidadeCompras++;
    }

    public String getNome() {
        return nome;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }
}

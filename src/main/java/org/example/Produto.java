package org.example;

public class Produto {
    private final String nome;
    private int totalVendido;
    private double faturamento;

    public Produto(String nome) {
        this.nome = nome;
    }

    public void registrarVenda(int quantidade, double valor) {
        totalVendido += quantidade;
        faturamento += valor;
    }

    public String getNome() {
        return nome;
    }

    public int getTotalVendido() {
        return totalVendido;
    }

    public double getFaturamento() {
        return faturamento;
    }
}

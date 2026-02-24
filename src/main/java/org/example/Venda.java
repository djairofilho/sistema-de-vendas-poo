package org.example;

import java.time.LocalDate;

public class Venda {
    private final int codigoVenda;
    private final String cliente;
    private final String produto;
    private final int quantidade;
    private final double precoUnitario;
    private final LocalDate data;

    public Venda(int codigoVenda, String cliente, String produto, int quantidade, double precoUnitario, LocalDate data) {
        this.codigoVenda = codigoVenda;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.data = data;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public String getCliente() {
        return cliente;
    }

    public String getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public LocalDate getData() {
        return data;
    }

    public double getTotal() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "codigoVenda=" + codigoVenda +
                ", cliente='" + cliente + '\'' +
                ", produto='" + produto + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + String.format("%.2f", precoUnitario) +
                ", data=" + data +
                ", total=" + String.format("%.2f", getTotal()) +
                '}';
    }
}

package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SistemaVendas {
    private final List<Venda> vendas = new ArrayList<>();
    private final Map<String, Cliente> clientes = new HashMap<>();
    private final Map<String, Produto> produtos = new HashMap<>();

    public void carregarVendas(List<Venda> vendasLidas) {
        for (Venda venda : vendasLidas) {
            registrarVenda(venda);
        }
    }

    private void registrarVenda(Venda venda) {
        vendas.add(venda);

        Cliente cliente = clientes.computeIfAbsent(venda.getCliente(), Cliente::new);
        cliente.adicionarCompra(venda.getTotal());

        Produto produto = produtos.computeIfAbsent(venda.getProduto(), Produto::new);
        produto.registrarVenda(venda.getQuantidade(), venda.getTotal());
    }

    public List<Venda> getVendas() {
        return List.copyOf(vendas);
    }

    public double getFaturamentoTotal() {
        return vendas.stream().mapToDouble(Venda::getTotal).sum();
    }

    public int getQuantidadeTotalItensVendidos() {
        return vendas.stream().mapToInt(Venda::getQuantidade).sum();
    }

    public double getValorMedioPorVenda() {
        if (vendas.isEmpty()) {
            return 0.0;
        }
        return getFaturamentoTotal() / vendas.size();
    }

    public Optional<Cliente> getClienteQueMaisGastou() {
        return clientes.values()
                .stream()
                .max(Comparator.comparingDouble(Cliente::getTotalGasto));
    }

    public Map<String, Double> getTotalGastoPorCliente() {
        Map<String, Double> totais = new HashMap<>();
        for (Cliente cliente : clientes.values()) {
            totais.put(cliente.getNome(), cliente.getTotalGasto());
        }
        return totais;
    }

    public Map<String, Integer> getQuantidadeComprasPorCliente() {
        Map<String, Integer> quantidades = new HashMap<>();
        for (Cliente cliente : clientes.values()) {
            quantidades.put(cliente.getNome(), cliente.getQuantidadeCompras());
        }
        return quantidades;
    }

    public Optional<Produto> getProdutoMaisVendido() {
        return produtos.values()
                .stream()
                .max(Comparator.comparingInt(Produto::getTotalVendido));
    }

    public Optional<Produto> getProdutoMaiorFaturamento() {
        return produtos.values()
                .stream()
                .max(Comparator.comparingDouble(Produto::getFaturamento));
    }

    public Map<String, Integer> getQuantidadeTotalVendidaPorProduto() {
        Map<String, Integer> totais = new HashMap<>();
        for (Produto produto : produtos.values()) {
            totais.put(produto.getNome(), produto.getTotalVendido());
        }
        return totais;
    }

    public Map<String, Double> getFaturamentoTotalPorProduto() {
        Map<String, Double> totais = new HashMap<>();
        for (Produto produto : produtos.values()) {
            totais.put(produto.getNome(), produto.getFaturamento());
        }
        return totais;
    }
}

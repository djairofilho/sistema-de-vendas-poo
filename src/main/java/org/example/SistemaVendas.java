package org.example;

import java.util.ArrayList;
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
        double total = 0.0;
        for (Venda venda : vendas) {
            total += venda.getTotal();
        }
        return total;
    }

    public int getQuantidadeTotalItensVendidos() {
        int totalItens = 0;
        for (Venda venda : vendas) {
            totalItens += venda.getQuantidade();
        }
        return totalItens;
    }

    public double getValorMedioPorVenda() {
        if (vendas.isEmpty()) {
            return 0.0;
        }
        return getFaturamentoTotal() / vendas.size();
    }

    public Optional<Cliente> getClienteQueMaisGastou() {
        Cliente clienteComMaiorGasto = null;
        for (Cliente cliente : clientes.values()) {
            if (clienteComMaiorGasto == null || cliente.getTotalGasto() > clienteComMaiorGasto.getTotalGasto()) {
                clienteComMaiorGasto = cliente;
            }
        }
        return Optional.ofNullable(clienteComMaiorGasto);
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
        Produto produtoMaisVendido = null;
        for (Produto produto : produtos.values()) {
            if (produtoMaisVendido == null || produto.getTotalVendido() > produtoMaisVendido.getTotalVendido()) {
                produtoMaisVendido = produto;
            }
        }
        return Optional.ofNullable(produtoMaisVendido);
    }

    public Optional<Produto> getProdutoMaiorFaturamento() {
        Produto produtoMaiorFaturamento = null;
        for (Produto produto : produtos.values()) {
            if (produtoMaiorFaturamento == null || produto.getFaturamento() > produtoMaiorFaturamento.getFaturamento()) {
                produtoMaiorFaturamento = produto;
            }
        }
        return Optional.ofNullable(produtoMaiorFaturamento);
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

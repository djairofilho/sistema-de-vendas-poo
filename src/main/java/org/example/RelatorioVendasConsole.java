package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RelatorioVendasConsole {
    public void imprimirTodasAsVendas(SistemaVendas sistema) {
        System.out.println("=== TODAS AS VENDAS ===");
        for (Venda venda : sistema.getVendas()) {
            System.out.println(venda);
        }
        System.out.println();
    }

    public void imprimirEstatisticas(SistemaVendas sistema) {
        System.out.println("=== CALCULOS GERAIS ===");
        System.out.printf("Faturamento total: %.2f%n", sistema.getFaturamentoTotal());
        System.out.printf("Quantidade total de itens vendidos: %d%n", sistema.getQuantidadeTotalItensVendidos());
        System.out.printf("Valor medio por venda: %.2f%n%n", sistema.getValorMedioPorVenda());

        System.out.println("=== ESTATISTICAS POR CLIENTE ===");
        Optional<Cliente> clienteQueMaisGastou = sistema.getClienteQueMaisGastou();
        if (clienteQueMaisGastou.isPresent()) {
            Cliente cliente = clienteQueMaisGastou.get();
            System.out.printf("Cliente que mais gastou: %s (%.2f)%n", cliente.getNome(), cliente.getTotalGasto());
        } else {
            System.out.println("Cliente que mais gastou: N/A");
        }

        System.out.println("Total gasto por cliente:");
        imprimirMapaDoubleOrdenado(sistema.getTotalGastoPorCliente());

        System.out.println("Quantidade de compras por cliente:");
        imprimirMapaIntOrdenado(sistema.getQuantidadeComprasPorCliente());
        System.out.println();

        System.out.println("=== ESTATISTICAS POR PRODUTO ===");
        Optional<Produto> produtoMaisVendido = sistema.getProdutoMaisVendido();
        if (produtoMaisVendido.isPresent()) {
            Produto produto = produtoMaisVendido.get();
            System.out.printf("Produto mais vendido: %s (%d itens)%n", produto.getNome(), produto.getTotalVendido());
        } else {
            System.out.println("Produto mais vendido: N/A");
        }

        Optional<Produto> produtoMaiorFaturamento = sistema.getProdutoMaiorFaturamento();
        if (produtoMaiorFaturamento.isPresent()) {
            Produto produto = produtoMaiorFaturamento.get();
            System.out.printf("Produto com maior faturamento: %s (%.2f)%n", produto.getNome(), produto.getFaturamento());
        } else {
            System.out.println("Produto com maior faturamento: N/A");
        }

        System.out.println("Quantidade total vendida por produto:");
        imprimirMapaIntOrdenado(sistema.getQuantidadeTotalVendidaPorProduto());

        System.out.println("Faturamento total por produto:");
        imprimirMapaDoubleOrdenado(sistema.getFaturamentoTotalPorProduto());
    }

    private void imprimirMapaDoubleOrdenado(Map<String, Double> mapa) {
        List<String> chaves = new ArrayList<>(mapa.keySet());
        Collections.sort(chaves);
        for (String chave : chaves) {
            System.out.printf("- %s: %.2f%n", chave, mapa.get(chave));
        }
    }

    private void imprimirMapaIntOrdenado(Map<String, Integer> mapa) {
        List<String> chaves = new ArrayList<>(mapa.keySet());
        Collections.sort(chaves);
        for (String chave : chaves) {
            System.out.printf("- %s: %d%n", chave, mapa.get(chave));
        }
    }
}

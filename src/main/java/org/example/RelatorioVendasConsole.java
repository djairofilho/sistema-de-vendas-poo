package org.example;

import java.util.Comparator;
import java.util.Map;

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
        sistema.getClienteQueMaisGastou().ifPresent(cliente ->
                System.out.printf("Cliente que mais gastou: %s (%.2f)%n", cliente.getNome(), cliente.getTotalGasto()));
        if (sistema.getClienteQueMaisGastou().isEmpty()) {
            System.out.println("Cliente que mais gastou: N/A");
        }

        System.out.println("Total gasto por cliente:");
        imprimirMapaDoubleOrdenado(sistema.getTotalGastoPorCliente());

        System.out.println("Quantidade de compras por cliente:");
        imprimirMapaIntOrdenado(sistema.getQuantidadeComprasPorCliente());
        System.out.println();

        System.out.println("=== ESTATISTICAS POR PRODUTO ===");
        sistema.getProdutoMaisVendido().ifPresent(produto ->
                System.out.printf("Produto mais vendido: %s (%d itens)%n", produto.getNome(), produto.getTotalVendido()));
        if (sistema.getProdutoMaisVendido().isEmpty()) {
            System.out.println("Produto mais vendido: N/A");
        }

        sistema.getProdutoMaiorFaturamento().ifPresent(produto ->
                System.out.printf("Produto com maior faturamento: %s (%.2f)%n", produto.getNome(), produto.getFaturamento()));
        if (sistema.getProdutoMaiorFaturamento().isEmpty()) {
            System.out.println("Produto com maior faturamento: N/A");
        }

        System.out.println("Quantidade total vendida por produto:");
        imprimirMapaIntOrdenado(sistema.getQuantidadeTotalVendidaPorProduto());

        System.out.println("Faturamento total por produto:");
        imprimirMapaDoubleOrdenado(sistema.getFaturamentoTotalPorProduto());
    }

    private void imprimirMapaDoubleOrdenado(Map<String, Double> mapa) {
        mapa.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .forEach(entry -> System.out.printf("- %s: %.2f%n", entry.getKey(), entry.getValue()));
    }

    private void imprimirMapaIntOrdenado(Map<String, Integer> mapa) {
        mapa.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .forEach(entry -> System.out.printf("- %s: %d%n", entry.getKey(), entry.getValue()));
    }
}

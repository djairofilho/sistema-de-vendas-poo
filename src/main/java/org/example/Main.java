package org.example;

public class Main {
    public static void main(String[] args) {
        String caminhoArquivo = args.length > 0 ? args[0] : "vendas.csv";
        LeitorVendasCsv leitor = new LeitorVendasCsv();
        SistemaVendas sistema = new SistemaVendas();
        RelatorioVendasConsole relatorio = new RelatorioVendasConsole();

        try {
            sistema.carregarVendas(leitor.ler(caminhoArquivo));
            relatorio.imprimirTodasAsVendas(sistema);
            relatorio.imprimirEstatisticas(sistema);
        } catch (Exception e) {
            System.err.println("Erro ao executar sistema de vendas: " + e.getMessage());
        }
    }
}

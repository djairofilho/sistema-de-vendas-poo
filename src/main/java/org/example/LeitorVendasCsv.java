package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeitorVendasCsv {
    public List<Venda> ler(String caminhoArquivo) throws IOException {
        Path caminho = Path.of(caminhoArquivo);
        List<Venda> vendas = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(caminho)) {
            String linha = reader.readLine();

            if (linha == null) {
                return vendas;
            }

            while ((linha = reader.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }
                vendas.add(parseLinha(linha));
            }
        }

        return vendas;
    }

    private Venda parseLinha(String linha) {
        String[] partes = linha.split(",");

        if (partes.length != 6) {
            throw new IllegalArgumentException("Linha CSV invalida: " + linha);
        }

        int codigoVenda = Integer.parseInt(partes[0].trim());
        String cliente = partes[1].trim();
        String produto = partes[2].trim();
        int quantidade = Integer.parseInt(partes[3].trim());
        double precoUnitario = Double.parseDouble(partes[4].trim());
        LocalDate data = LocalDate.parse(partes[5].trim());

        return new Venda(codigoVenda, cliente, produto, quantidade, precoUnitario, data);
    }
}

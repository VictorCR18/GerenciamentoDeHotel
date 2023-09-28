package com.victorcr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Conversor {

  public static void converterParaJSON(
    String nomeArquivoCSV,
    String nomeArquivoJSON
  ) {
    List<Quarto> quartos = lerQuartosDoCSV(nomeArquivoCSV);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.writeValue(new File(nomeArquivoJSON), quartos);
      System.out.println("Dados convertidos para JSON com sucesso.");
    } catch (IOException e) {
      System.err.println("Erro ao converter para JSON: " + e.getMessage());
    }
  }

  public static void converterParaXML(
    String nomeArquivoCSV,
    String nomeArquivoXML
  ) {
    List<Quarto> quartos = lerQuartosDoCSV(nomeArquivoCSV);

    try {
      XmlMapper xmlMapper = new XmlMapper();
      xmlMapper.writeValue(new File(nomeArquivoXML), quartos);
      System.out.println("Dados convertidos para XML com sucesso.");
    } catch (IOException e) {
      System.err.println("Erro ao converter para XML: " + e.getMessage());
    }
  }

  public static List<Quarto> lerQuartosDoCSV(String nomeArquivo) {
    List<Quarto> quartos = new ArrayList<>();
    try (
      BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))
    ) {
      String linha;
      while ((linha = reader.readLine()) != null) {
        String[] partes = linha.split(",");
        if (partes.length == 5) {
          int numero = Integer.parseInt(partes[0]);
          String descricao = partes[1];
          String tipo = partes[2];
          double preco = Double.parseDouble(partes[3]);
          boolean disponivel = Boolean.parseBoolean(partes[4]);
          Quarto quarto = new Quarto(
            numero,
            descricao,
            tipo,
            preco,
            disponivel
          );
          quartos.add(quarto);
        }
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
    }
    return quartos;
  }
}

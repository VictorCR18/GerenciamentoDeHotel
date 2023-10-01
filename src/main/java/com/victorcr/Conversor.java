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

  // Método para converter um arquivo CSV em JSON
  public static void converterParaJSON(
    String nomeArquivoCSV,
    String nomeArquivoJSON
  ) {
    // Lê os dados do CSV e os armazena em uma lista de objetos Quarto
    List<Quarto> quartos = lerQuartosDoCSV(nomeArquivoCSV);

    try {
      // Cria um ObjectMapper para trabalhar com JSON
      ObjectMapper objectMapper = new ObjectMapper();

      // Converte a lista de objetos em JSON e escreve em um arquivo
      objectMapper.writeValue(new File(nomeArquivoJSON), quartos);

      System.out.println("Dados convertidos para JSON com sucesso.");
    } catch (IOException e) {
      System.err.println("Erro ao converter para JSON: " + e.getMessage());
    }
  }

  // Método para converter um arquivo CSV em XML
  public static void converterParaXML(
    String nomeArquivoCSV,
    String nomeArquivoXML
  ) {
    // Lê os dados do CSV e os armazena em uma lista de objetos Quarto
    List<Quarto> quartos = lerQuartosDoCSV(nomeArquivoCSV);

    try {
      // Cria um XmlMapper para trabalhar com XML
      XmlMapper xmlMapper = new XmlMapper();

      // Converte a lista de objetos em XML e escreve em um arquivo
      xmlMapper.writeValue(new File(nomeArquivoXML), quartos);

      System.out.println("Dados convertidos para XML com sucesso.");
    } catch (IOException e) {
      System.err.println("Erro ao converter para XML: " + e.getMessage());
    }
  }

  // Método para ler dados de um arquivo CSV e retornar uma lista de objetos Quarto
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

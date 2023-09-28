package com.victorcr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(
      "1 - Inserir quarto\n2 - Contar quantidade de quartos\n3 - Converter CSV para JSON e XML"
    );
    int key = scanner.nextInt();

    switch (key) {
      case 1:
        {
          Quarto.inserirQuarto();
          break;
        }
      case 2:
        {
          contarEntidadesCSV("src/main/java/com/victorcr/Arquivos/quartos.csv");
          break;
        }
      case 3:
        {
          Conversor.converterParaJSON(
            "src/main/java/com/victorcr/Arquivos/quartos.csv",
            "src/main/java/com/victorcr/Arquivos/quartos.json"
          );
          Conversor.converterParaXML(
            "src/main/java/com/victorcr/Arquivos/quartos.csv",
            "src/main/java/com/victorcr/Arquivos/quartos.xml"
          );
        }
      default:
        {
          System.out.println("opção inválida");
        }
    }

    scanner.close();
  }

  public static int contarEntidadesCSV(String nomeArquivo) {
    int contador = 0;
    try (
      BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))
    ) {
      String linha;
      while ((linha = reader.readLine()) != null) {
        contador++;
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
    }

    System.out.println("Quantidade de quartos: " + contador);
    return contador;
  }
}

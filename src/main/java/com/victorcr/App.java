package com.victorcr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int key = 1;
    while (key == 1) {
      System.out.println(
        "\n1 - Inserir quarto\n2 - Contar quantidade de quartos\n3 - Converter CSV para JSON e XML\n4 - Compactar arquivo CSV para ZIP\n5 - Descompactar ZIP para CSV\n6 - Mostrar hash SHA256 do arquivo CSV\n"
      );
      System.out.println("0 - Sair");

      System.out.print("Resposta: ");

      int op = scanner.nextInt();

      switch (op) {
        case 1:
          {
            Quarto.inserirQuarto();
            break;
          }
        case 2:
          {
            contarEntidadesCSV(
              "src/main/java/com/victorcr/Arquivos/quartos.csv"
            );
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
            break;
          }
        case 4:
          {
            Compactador.start();
            break;
          }
        case 5:
          {
            Descompactador.start();
            break;
          }
        case 6:
          {
            CalcularHashSHA256.calculadoraHashSHA256();
            break;
          }
        case 0:
          {
            key = 0; //0 para encerrar o programa
            scanner.close();
            break;
          }
        default:
          {
            System.out.println("Opção inválida");
          }
      }
    }
  }

  public static int contarEntidadesCSV(String nomeArquivo) {
    int contador = 0;
    try (
      BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))
    ) {
      while ((reader.readLine()) != null) {
        contador++;
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
    }

    System.out.println("Quantidade de quartos: " + contador);
    return contador;
  }
}

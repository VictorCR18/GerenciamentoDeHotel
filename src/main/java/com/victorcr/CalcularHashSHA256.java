package com.victorcr;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalcularHashSHA256 {

  public static void calculadoraHashSHA256() {
    // Nome do arquivo CSV para calcular o hash
    String arquivoCSV = "src/main/java/com/victorcr/Arquivos/quartos.csv";

    try {
      // Calcula o hash SHA-256 do arquivo
      String hashSHA256 = calcularHashSHA256(arquivoCSV);

      // Exibe o hash SHA-256 na tela
      System.out.println("Hash SHA-256 do arquivo: " + hashSHA256);
    } catch (NoSuchAlgorithmException | IOException e) {
      e.printStackTrace();
    }
  }

  // Método para calcular o hash SHA-256 de um arquivo
  public static String calcularHashSHA256(String arquivo)
    throws NoSuchAlgorithmException, IOException {
    // Cria uma instância de MessageDigest para o algoritmo SHA-256
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    // Abre o arquivo para leitura
    try (FileInputStream fis = new FileInputStream(arquivo)) {
      byte[] buffer = new byte[8192];
      int bytesRead;
      // Lê o arquivo em blocos e atualiza o MessageDigest com os bytes lidos
      while ((bytesRead = fis.read(buffer)) != -1) {
        md.update(buffer, 0, bytesRead);
      }
    }

    // Calcula o hash
    byte[] digest = md.digest();

    // Converte o resultado do hash em uma representação hexadecimal
    return bytesToHex(digest);
  }

  // Método auxiliar para converter bytes em uma representação hexadecimal
  private static String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}

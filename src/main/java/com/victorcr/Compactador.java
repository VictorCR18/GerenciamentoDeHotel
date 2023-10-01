package com.victorcr;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compactador {

  public static void start() {
    String arquivoCSV = "src/main/java/com/victorcr/Arquivos/quartos.csv"; // Nome do arquivo CSV
    String arquivoZIP = "src/main/java/com/victorcr/Arquivos/quartos.zip"; // Nome do arquivo ZIP de saída

    compactarCSVParaZIP(arquivoCSV, arquivoZIP);

    System.out.println("Compactação concluída.");
  }

  public static void compactarCSVParaZIP(String arquivoCSV, String arquivoZIP) {
    try (
      FileOutputStream fos = new FileOutputStream(arquivoZIP);
      ZipOutputStream zipOut = new ZipOutputStream(fos);
    ) {
      File arquivoParaCompactar = new File(arquivoCSV);
      FileInputStream fis = new FileInputStream(arquivoParaCompactar);

      // Define o nome do arquivo dentro do arquivo ZIP
      ZipEntry zipEntry = new ZipEntry(arquivoParaCompactar.getName());
      zipOut.putNextEntry(zipEntry);

      // Copia o conteúdo do arquivo CSV para o arquivo ZIP
      byte[] bytes = new byte[1024];
      int length;
      while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
      }

      // Fecha as streams
      zipOut.closeEntry();
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

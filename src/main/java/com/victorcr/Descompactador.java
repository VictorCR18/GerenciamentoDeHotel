package com.victorcr;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Descompactador {

  public static void start() {
    String arquivoZIP = "src/main/java/com/victorcr/Arquivos/quartos.zip"; // Nome do arquivo ZIP a ser descompactado
    String pastaDestino = "src/main/java/com/victorcr/Arquivos/"; // Pasta de destino para os arquivos descompactados

    descompactarZIP(arquivoZIP, pastaDestino);

    System.out.println("Descompactação concluída.");
  }

  public static void descompactarZIP(String arquivoZIP, String pastaDestino) {
    try (
      ZipInputStream zipIn = new ZipInputStream(new FileInputStream(arquivoZIP))
    ) {
      File destino = new File(pastaDestino);

      // Crie a pasta de destino se ela não existir
      if (!destino.exists()) {
        destino.mkdirs();
      }

      ZipEntry entrada;
      while ((entrada = zipIn.getNextEntry()) != null) {
        String nomeArquivo = entrada.getName();
        File arquivoDescompactado = new File(pastaDestino + nomeArquivo);

        // Certifique-se de que o caminho de destino seja seguro
        if (
          !arquivoDescompactado
            .toPath()
            .normalize()
            .startsWith(destino.toPath())
        ) {
          throw new IOException("Entrada ZIP maliciosa: " + nomeArquivo);
        }

        if (entrada.isDirectory()) {
          // Se for um diretório, crie-o
          arquivoDescompactado.mkdirs();
        } else {
          // Se for um arquivo, crie-o e copie os dados
          byte[] buffer = new byte[1024];
          try (
            FileOutputStream fos = new FileOutputStream(arquivoDescompactado)
          ) {
            int bytesRead;
            while ((bytesRead = zipIn.read(buffer)) != -1) {
              fos.write(buffer, 0, bytesRead);
            }
          }
        }

        zipIn.closeEntry();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

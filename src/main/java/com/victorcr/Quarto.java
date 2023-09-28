package com.victorcr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Quarto {

  private int numeroDoQuarto;
  private String descricao;
  private String tipoDeQuarto;
  private double precoDaDiaria;
  private boolean disponivel;

  public Quarto(
    int numeroDoQuarto,
    String descricao,
    String tipoDeQuarto,
    double precoDaDiaria,
    boolean disponivel
  ) {
    this.numeroDoQuarto = numeroDoQuarto;
    this.descricao = descricao;
    this.tipoDeQuarto = tipoDeQuarto;
    this.precoDaDiaria = precoDaDiaria;
    this.disponivel = disponivel;
  }

  public int getNumeroDoQuarto() {
    return numeroDoQuarto;
  }

  public void setNumeroDoQuarto(int numeroDoQuarto) {
    this.numeroDoQuarto = numeroDoQuarto;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getTipoDeQuarto() {
    return tipoDeQuarto;
  }

  public void setTipoDeQuarto(String tipoDeQuarto) {
    this.tipoDeQuarto = tipoDeQuarto;
  }

  public double getPrecoDaDiaria() {
    return precoDaDiaria;
  }

  public void setPrecoDaDiaria(double precoDaDiaria) {
    this.precoDaDiaria = precoDaDiaria;
  }

  public boolean isDisponivel() {
    return disponivel;
  }

  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }

  public static void inserirQuarto() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Número do Quarto: ");
    int numeroDoQuarto = scanner.nextInt();

    scanner.nextLine(); // Consumir a quebra de linha deixada pelo nextInt()

    System.out.print("Descricão: ");
    String descricao = scanner.nextLine();

    System.out.print("Tipo de Quarto: ");
    String tipoDeQuarto = scanner.nextLine();

    System.out.print("Preço da Diária: ");
    double precoDaDiaria = scanner.nextDouble();

    scanner.nextLine(); // Consumir a quebra de linha deixada pelo nextDouble()

    System.out.print("Disponível (true/false): ");
    boolean disponivel = scanner.nextBoolean();

    // Abrir o arquivo CSV em modo de anexação (append)
    try (
      BufferedWriter writer = new BufferedWriter(
        new FileWriter("src/main/java/com/victorcr/Arquivos/quartos.csv", true)
      )
    ) {
      // Escrever os dados no arquivo CSV
      writer.write(
        numeroDoQuarto +
        "," +
        descricao +
        "," +
        tipoDeQuarto +
        "," +
        precoDaDiaria +
        "," +
        disponivel
      );
      writer.newLine(); // Adicionar uma quebra de linha após cada entrada
      System.out.println(
        "Dados do quarto foram adicionados ao arquivo CSV com sucesso."
      );
    } catch (IOException e) {
      System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
    } finally {
      scanner.close();
    }
  }
}

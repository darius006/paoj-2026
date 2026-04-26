package com.pao.proiect.model;

public class Autor {
  private String numeComplet;
  private int anDebut;

  public Autor(String numeComplet, int anDebut) {
    this.numeComplet = numeComplet;
    this.anDebut = anDebut;
  }

  @Override
  public String toString() {
    return String.format(
      "Autor{nume='%s', anDebut=%d}",
      this.numeComplet, this.anDebut
    );
  }

}

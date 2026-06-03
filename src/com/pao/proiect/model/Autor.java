package com.pao.proiect.model;

public class Autor {
  private String numeComplet;

  public Autor(String numeComplet) {
    this.numeComplet = numeComplet;
  }

  public String getNumeComplet() {
    return numeComplet;
  }

  @Override
  public String toString() {
    return String.format(
      "Autor{nume='%s'}",
      this.numeComplet
    );
  }

}

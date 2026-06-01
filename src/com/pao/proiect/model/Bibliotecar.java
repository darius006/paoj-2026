package com.pao.proiect.model;

public class Bibliotecar extends Angajat {
  private int nrLimbiVorbite;

  public Bibliotecar(int id, String nume, int nrLimbiVorbite) {
    super(id, nume);
    this.nrLimbiVorbite = nrLimbiVorbite;
  }

  public int getSalariuNet() {
    return 2950 + (nrLimbiVorbite - 1) * 30;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(
      "\nBibliotecar, Nr limbi vorbite: %d\n",
      nrLimbiVorbite
    );
  }
}

package com.pao.proiect.model;

public abstract class Angajat {
  protected int idAngajat;
  protected String numeComplet;

  public Angajat(int idAngajat, String numeComplet) {
    this.idAngajat = idAngajat;
    this.numeComplet = numeComplet;
  }

  public abstract int getSalariuNet();

  public int getIdAngajat() {
    return idAngajat;
  }

  @Override
  public String toString() {
    return String.format(
      "Angajat{id='%d', nume='%s'}",
      idAngajat, numeComplet
    );
  }

  @Override
  public boolean equals(Object obj) {
    return this.idAngajat == ((Angajat)obj).getIdAngajat();
  }

  @Override
  public int hashCode() {
    return ((Integer)idAngajat).hashCode();
  }
}

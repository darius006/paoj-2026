package com.pao.laboratory05.angajati;

public class Angajat implements Comparable<Angajat> {
  private String nume;
  private Departament departament;
  private double salariu;

  public Angajat(String nume, Departament departament, double salariu) {
    this.nume = nume;
    this.departament = departament;
    this.salariu = salariu;
  }

  public String getNume() {
    return nume;
  }

  public Departament getDepartament() {
    return departament;
  }

  public double getSalariu() {
    return salariu;
  }

  @Override
  public String toString() {
    return String.format(
      "Angajat{nume='%s', departament='%s', salariu='%.2f'}",
      nume, departament.toString(), salariu
    );
  }

  @Override
  public int compareTo(Angajat o) {
    Double s1 = this.salariu, s2 = o.salariu;
    return -s1.compareTo(s2);
  }
}

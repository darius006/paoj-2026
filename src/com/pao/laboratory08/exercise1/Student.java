package com.pao.laboratory08.exercise1;

public class Student implements Cloneable {
  private String nume;
  private int varsta;
  private Adresa adresa;

  public Student(String nume, int varsta, Adresa adresa) {
    this.nume = nume;
    this.varsta = varsta;
    this.adresa = adresa;
  }

  public Adresa getAdresa() {
    return adresa;
  }
  public String getNume() {
    return nume;
  }
  public int getVarsta() {
    return varsta;
  }

  public void setAdresa(Adresa adresa) {
    this.adresa = adresa;
  }
  public void setNume(String nume) {
    this.nume = nume;
  }
  public void setVarsta(int varsta) {
    this.varsta = varsta;
  }

  @Override
  public String toString() {
    return String.format(
      "Student{nume='%s', varsta=%d, adresa=%s}",
      nume, varsta, adresa.toString()
    );
  }

  public Object shallowClone() throws CloneNotSupportedException {
    return (Student)super.clone();
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    Student clona = (Student) super.clone();
    clona.setAdresa((Adresa) this.adresa.clone());
    return clona;
  }
}

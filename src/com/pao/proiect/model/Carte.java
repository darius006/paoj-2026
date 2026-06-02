package com.pao.proiect.model;

public class Carte implements Comparable {
  private ISBN isbn;
  private Autor autor;
  private Sectiune sectiune;
  private String titlu;
  private int nrExemplare;

  public Carte(ISBN isbn, Autor autor, Sectiune sectiune, String titlu, int nrExemplare) {
    this.isbn = isbn;
    this.autor = autor;
    this.sectiune = sectiune;
    this.titlu = titlu;
    this.nrExemplare = nrExemplare;
  }

  public void setIsbn(ISBN isbn) {
    this.isbn = isbn;
  }

  public void setSectiune(Sectiune sectiune) {
    this.sectiune = sectiune;
  }

  public void setTitlu(String titlu) {
    this.titlu = titlu;
  }

  public ISBN getIsbn() {
    return isbn;
  }

  public void setNrExemplare(int nrExemplare) {
    this.nrExemplare = nrExemplare;
  }

  public Sectiune getSectiune() {
    return sectiune;
  }

  public String getTitlu() {
    return titlu;
  }

  public int getNrExemplare() {
    return nrExemplare;
  }

  public Autor getAutor() {
    return autor;
  }

  @Override
  public String toString() {
    return String.format(
      "Carte{isbn='%s', autor='%s', sectiune='%s', titlu='%s', nrExemplare=%d}",
      this.isbn, this.autor, this.sectiune, this.titlu, this.nrExemplare
    );
  }

  @Override
  public boolean equals(Object obj) {
    return this.isbn.equals(((Carte)obj).getIsbn());
  }

  @Override
  public int hashCode() {
    return isbn.hashCode();
  }

  @Override
  public int compareTo(Object o) {
    return this.titlu.compareTo(((Carte)o).getTitlu());
  }

}

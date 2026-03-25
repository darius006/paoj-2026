package com.pao.laboratory05.biblioteca;

public class Carte implements Comparable<Carte> {
  private String titlu;
  private String autor;
  private int an;
  private double rating;

  public Carte(String titlu, String autor, int an, double rating) {
    this.titlu = titlu;
    this.autor = autor;
    this.an = an;
    this.rating = rating;
  }

  public String getTitlu() {
    return titlu;
  }

  public String getAutor() {
    return autor;
  }

  public int getAn() {
    return an;
  }

  public double getRating() {
    return rating;
  }

  @Override
  public String toString() {
    return String.format(
      "Carte{titlu='%s', autor='%s', an='%d', rating='%.2f'}",
      titlu, autor, an, rating
    );
  }

  @Override
  public int compareTo(Carte o) {
    Double r1 = this.getRating(), r2 = o.getRating();
    return -r1.compareTo(r2);
  }
}

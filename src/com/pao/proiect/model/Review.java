package com.pao.proiect.model;

public class Review {
  private ISBN isbn;
  private String email;
  private int nrStele;

  public Review(ISBN isbn, String email, int nrStele) {
    this.isbn = isbn;
    this.email = email;
    this.nrStele = nrStele;
  }
  public ISBN getIsbn() {
    return isbn;
  }

  public String getEmail() {
    return email;
  }

  public int getNrStele() {
    return nrStele;
  }

  @Override
  public String toString() {
    return String.format(
      "Review{ISBN='%s', email='%s', nrStele=%d}",
      isbn, email, nrStele
    );
  }
}

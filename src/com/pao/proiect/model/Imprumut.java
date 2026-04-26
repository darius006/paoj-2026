package com.pao.proiect.model;

public class Imprumut {
  private ISBN isbn;
  private String email;
  private String dataInprumut;
  private String dataPredare;
  private String dataLimitaPredare;

  public Imprumut(ISBN isbn, String email, String dataImprumut, String dataPredare, String dataLimitaPredare) {
    this.isbn = isbn;
    this.email = email;
    this.dataInprumut = dataImprumut;
    this.dataPredare = dataPredare;
    this.dataLimitaPredare = dataLimitaPredare;
  }


  public ISBN getIsbn() {
    return isbn;
  }

  public String getEmail() {
    return email;
  }

  public void setDataPredare(String dataPredare) {
    this.dataPredare = dataPredare;
  }

  public String getDataPredare() {
    return dataPredare;
  }

  public String getDataLimitaPredare() {
    return dataLimitaPredare;
  }

  @Override
  public String toString() {
    return String.format(
      "Imprumut{isbn='%s', email='%s', dataImprumut='%s', dataPredare='%s', dataLimitaPredare='%s'}",
      isbn, email, dataInprumut, dataPredare, dataLimitaPredare
    );
  }

}

package com.pao.proiect.model;

import com.pao.proiect.exception.InvalidAgeException;

public class Sectiune {
  public String nume;
  public int ratingVarsta;

  public Sectiune(String nume, int ratingVarsta) {
    this.nume = nume;
    if (ratingVarsta < 1 || ratingVarsta > 18) {
      throw new InvalidAgeException("Rating-ul de varsta poate fi intre 1 si 18 ani.");
    }
    this.ratingVarsta = ratingVarsta;
  }

  @Override
  public String toString() {
    return String.format(
      "Sectiune{nume='%s', ratingVarsta=%d}",
      this.nume, this.ratingVarsta
    );
  }


}

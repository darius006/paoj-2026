package com.pao.laboratory03.exercise.model;

public enum Subject {
  PAOJ("Programare avansata pe obiecte in Java", 5),
  SGBD("Sisteme de gestiune a bazelor de date", 5),
  SO("Sisteme de operare", 4),
  RC("Retele de calculatoare", 4);
  private String fullName;
  private int credits;

  Subject(String fullName, int credits) {
    this.fullName = fullName;
    this.credits = credits;
  }

  public String getFullName() {
    return fullName;
  }

  public int getCredits() {
    return credits;
  }

  @Override
  public String toString() {
    return fullName + ", " + credits +  " credite.";
  }
}

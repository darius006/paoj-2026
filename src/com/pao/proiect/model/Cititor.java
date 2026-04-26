package com.pao.proiect.model;

import java.util.List;
import java.util.ArrayList;

public class Cititor {
  private String email;
  private String numeComplet;

  public Cititor(String email, String numeComplet) {
    this.email = email;
    this.numeComplet = numeComplet;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return String.format(
      "Cititor={email='%s', numeComplet='%s'}",
      email, numeComplet
    );
  }

  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }

  @Override
  public int hashCode() {
    return email.hashCode();
  }
}

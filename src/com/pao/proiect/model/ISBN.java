package com.pao.proiect.model;

import com.pao.proiect.exception.InvalidIsbnException;

public final class ISBN {
  private final String valoare;

  public ISBN(String valoare) {
    this.valoare = validareVal(valoare);
  }

  private String validareVal(String valoare) {
    String trim = valoare.replace("-", "");

    if (trim.length() == 10) {
      int sum = 0;
      for (int i = 0; i < 10; i++) {
        int num = Character.getNumericValue(trim.charAt(i));
        if (num == -1) {
          throw new InvalidIsbnException("ISBN-ul poate contine doar cifre si linii.");
        }
        sum += (10 - i) * num;
      }
      if (sum % 11 != 0) {
        throw new InvalidIsbnException("Checksum nevalid.");
      }
    }

    else if (trim.length() == 13) {
      int sum = 0;
      for (int i = 0; i < 13; i++) {
        int num = Character.getNumericValue(trim.charAt(i));
        if (num == -1) {
          throw new InvalidIsbnException("ISBN-ul poate contine doar cifre si linii.");
        }
        int weight = (i % 2 == 0) ? 1 : 3;
        sum += weight * num;
      }
      if (sum % 10 != 0) {
        throw new InvalidIsbnException("Checksum nevalid.");
      }
    }

    else {
      throw new InvalidIsbnException("ISBN-ul poate avea doar 10 sau 13 cifre.");
    }
    return valoare;
  }

  @Override
  public String toString() {
    return valoare;
  }

  @Override
  public boolean equals(Object obj) {
    String trim = valoare.replace("-", "");
    String trimObj = obj.toString().replace("-", "");
    return trim.equals(trimObj);
  }

  @Override
  public int hashCode() {
    return valoare.hashCode();
  }
}

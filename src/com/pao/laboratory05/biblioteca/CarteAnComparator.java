package com.pao.laboratory05.biblioteca;

import java.util.Comparator;

public class CarteAnComparator implements Comparator<Carte> {
  public int compare(Carte c1, Carte c2) {
    Integer a1 = c1.getAn(), a2 = c2.getAn();
    return a1.compareTo(a2);
  }
}

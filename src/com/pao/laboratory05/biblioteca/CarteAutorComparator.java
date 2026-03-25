package com.pao.laboratory05.biblioteca;

import java.util.Comparator;

public class CarteAutorComparator implements Comparator<Carte> {
  @Override
  public int compare(Carte o1, Carte o2) {
    String a1 = o1.getAutor(), a2 = o2.getAutor();
    return a1.compareTo(a2);
  }
}

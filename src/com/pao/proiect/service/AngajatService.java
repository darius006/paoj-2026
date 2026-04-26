package com.pao.proiect.service;

import java.util.ArrayList;
import java.util.List;

import com.pao.proiect.model.*;

public class AngajatService {
  private static final AngajatService instance = new AngajatService();

  private AngajatService() {}

  public static AngajatService getInstance() {
    return instance;
  }

  
  private List<Angajat> angajati = new ArrayList<Angajat>();

  public void addAngajat(Angajat c) {
    angajati.add(c);
  }

  public void removeCarte(int id) {
    for (int i = 0; i < angajati.size(); ++i) {
      if (angajati.get(i).getIdAngajat() == id) {
        angajati.remove(i);
      }
    }
  }

  public Angajat searchAngajat(int id) {
    for (Angajat c : angajati) {
      if (c.getIdAngajat() == id) {
        return c;
      }
    }
    return null;
  }

  public void listAngajat() {
    for (Angajat c : angajati) {
      System.out.println(c);
    }
  }
}

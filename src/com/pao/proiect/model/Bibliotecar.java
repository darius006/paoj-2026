package com.pao.proiect.model;

import java.util.List;
import java.util.ArrayList;

public class Bibliotecar extends Angajat {
  private List<String> subiecteExpertiza = new ArrayList<String>();
  private List<String> limbiVorbite = new ArrayList<String>();

  public Bibliotecar(int id, String nume, List<String> subiecte, List<String> limbi) {
    super(id, nume);
    this.subiecteExpertiza = subiecte;
    this.limbiVorbite = limbi;
  }

  public int getSalariuNet() {
    return 2950;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(
      "\nBibliotecar, Nr. subiecte expertiza: %d, Nr limbi vorbite: %d\n",
      subiecteExpertiza.size(), limbiVorbite.size()
    );
  }
}

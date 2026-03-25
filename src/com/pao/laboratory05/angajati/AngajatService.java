package com.pao.laboratory05.angajati;

import java.util.Arrays;

import com.pao.laboratory05.biblioteca.Carte;

public class AngajatService {
  public static final AngajatService INSTANCE = new AngajatService();
  private Angajat[] angajati;

  private AngajatService() {
    angajati = new Angajat[0];
  }

  public static AngajatService getInstance() {
    return INSTANCE;
  }

  public void addAngajat(Angajat angajat) {
    Angajat[] temp = new Angajat[angajati.length + 1];
    System.arraycopy(angajati, 0, temp, 0, angajati.length);
    temp[angajati.length] = angajat;
    angajati = temp;

    System.out.println("Angajat adaugat!");
  }

  public void printAll() {
    for (Angajat angajat : angajati) {
      System.out.println(angajat);
    }
  }

  public void listBySalary() {
    Angajat[] copy = angajati.clone();
    Arrays.sort(copy);
    
    for (Angajat angajat : copy) {
      System.out.println(angajat);
    }
  }

  public void findByDepartment(String numeDept) {
    boolean exista = false;
    for (Angajat angajat : angajati) {
      if (angajat.getDepartament().nume().equalsIgnoreCase(numeDept)) {
        exista = true;
        System.out.println(angajat);
      }
    }
    if (!exista) {
      System.out.println(String.format(
        "Niciun angajat în departamentul: %s",
        numeDept
      ));
    }
  }
}

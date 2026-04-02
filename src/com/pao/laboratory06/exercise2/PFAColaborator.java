package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends PersoanaFizica{
  private double cheltuieliLunare;
  public double calculeazaVenitNetAnual() {
    final double venBrutMin = 4050 * 12;
    double venNet = (venitLunar - cheltuieliLunare) * 12;

    double impozit = 0.1 * venNet;

    double CASS;
    if (venNet < 6 * venBrutMin) {
      CASS = 0.1 * 6 * venBrutMin;
    }
    else if (venNet < 72 * venBrutMin) {
      CASS = 0.1 * venNet;
    }
    else {
      CASS = 0.1 * 72 * venBrutMin;
    }

    double CAS;
    if (venNet < 12 * venBrutMin) {
      CAS = 0;
    }
    else if (venNet < 72 * venBrutMin) {
      CAS = 0.25 * 12 * venBrutMin;
    }
    else {
      CAS = 0.25 * 24 * venBrutMin;
    }

    return venNet - impozit - CAS - CASS;
  }

  public String tipContract() {
    return "PFA";
  }

  public void citeste(Scanner in) {
    nume = in.next();
    prenume = in.next();
    venitLunar = in.nextDouble();
    cheltuieliLunare = in.nextDouble();
  }

  public void afiseaza() {
    System.out.println(String.format(
      "%s: %s %s, venit net anual: %.2f lei",
      tipContract(), nume, prenume, calculeazaVenitNetAnual() 
    ));
  }
}

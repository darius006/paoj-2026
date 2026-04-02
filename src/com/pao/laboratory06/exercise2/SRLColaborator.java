package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class SRLColaborator extends PersoanaJuridica {
  private double cheltuieliLunare;
  public double calculeazaVenitNetAnual() {
    return (venitLunar - cheltuieliLunare) * 12 * 0.84;
  }

  public String tipContract() {
    return "SRL";
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

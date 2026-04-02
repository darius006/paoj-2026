package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends PersoanaFizica {
  private boolean bonus = false;
  public double calculeazaVenitNetAnual() {
    double ven = venitLunar * 0.55 * 12;
    if (bonus) {
      ven += 0.1 * ven;
    }
    return ven;
  }
  public String tipContract() {
    return "CIM";
  }
  public void citeste(Scanner in) {
    nume = in.next();
    prenume = in.next();
    venitLunar = in.nextDouble();
    String temp = in.next();
    if (temp.equals("DA")) {
      bonus = true;
    }
  }
  public void afiseaza() {
    System.out.println(String.format(
      "%s: %s %s, venit net anual: %.2f lei",
      tipContract(), nume, prenume, calculeazaVenitNetAnual() 
    ));
  }
  public boolean areBonus() {
    return bonus;
  }
}

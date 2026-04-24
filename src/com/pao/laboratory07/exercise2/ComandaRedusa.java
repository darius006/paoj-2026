package com.pao.laboratory07.exercise2;

import com.pao.laboratory07.exercise1.OrderState;

public final class ComandaRedusa extends Comanda {
  private int discountProcent;
  public double pretFinal() {
    float disc = discountProcent;
    return pret * (1 - disc / 100);
  }
  public String descriere() {
    return String.format(
      "DISCOUNTED: %s, pret: %.2f lei (-%d%%) [%s]",
      nume, pretFinal(), discountProcent, state.toString()
    );
  }
  public ComandaRedusa(String nume, double pret, int discount) {
    this.state = OrderState.PLACED;
    this.nume = nume;
    this.pret = pret;
    this.discountProcent = discount;
  }
}

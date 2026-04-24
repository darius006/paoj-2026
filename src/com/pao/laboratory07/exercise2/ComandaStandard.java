package com.pao.laboratory07.exercise2;

import com.pao.laboratory07.exercise1.OrderState;

public final class ComandaStandard extends Comanda {
  public double pretFinal() {
    return pret;
  }
  public String descriere() {
    return String.format(
      "STANDARD: %s, pret: %.2f lei [%s]",
      nume, pretFinal(), state.toString()
    );
  }
  public ComandaStandard(String nume, double pret) {
    this.state = OrderState.PLACED;
    this.nume = nume;
    this.pret = pret;
  }
}

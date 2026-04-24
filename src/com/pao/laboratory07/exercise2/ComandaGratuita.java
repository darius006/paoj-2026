package com.pao.laboratory07.exercise2;

import com.pao.laboratory07.exercise1.OrderState;

public final class ComandaGratuita extends Comanda {
  public double pretFinal() {
    return 0.0;
  }
  public String descriere() {
    return String.format(
      "GIFT: %s, gratuit [%s]",
      nume, state.toString()
    );
  }
  public ComandaGratuita(String nume) {
    this.state = OrderState.PLACED;
    this.nume = nume;
  }
}

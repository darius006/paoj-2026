package com.pao.laboratory06.exercise3;

public enum ConstanteFinanciare {
  TVA(0.19) {
    public double getVal() {
      return this.val;
    }
  },
  SALARIU_MINIM(4050) {
    public double getVal() {
      return this.val;
    }
  },
  COTA_IMPOZIT(0.1) {
    public double getVal() {
      return this.val;
    }
  };

  protected double val;

  private ConstanteFinanciare(double val) {
    this.val = val;
  }

  public abstract double getVal();
}

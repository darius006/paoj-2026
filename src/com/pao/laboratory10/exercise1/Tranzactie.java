package com.pao.laboratory10.exercise1;

public class Tranzactie implements Comparable {
  private int id;
  private double suma;
  private String data;
  private TipTranzactie tip;
 
  public Tranzactie(int id, double suma, String data, TipTranzactie tip) {
    this.id = id;
    this.suma = suma;
    this.data = data;
    this.tip = tip;
  }

  public void setData(String data) {
    this.data = data;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setSuma(double suma) {
    this.suma = suma;
  }

  public void setTip(TipTranzactie tip) {
    this.tip = tip;
  }

  public String getData() {
    return data;
  }

  public int getId() {
    return id;
  }

  public double getSuma() {
    return suma;
  }

  public TipTranzactie getTip() {
    return tip;
  }

  @Override
  public String toString() {
    return String.format(
      "[%d] %s %s: %.2f RON",
      id, data, String.valueOf(tip), suma
    );
  }

  @Override
  public int compareTo(Object o) {
    Double s1 = this.getSuma();
    Double s2 = ((Tranzactie)o).getSuma();
    return s1.compareTo(s2);
  }
}

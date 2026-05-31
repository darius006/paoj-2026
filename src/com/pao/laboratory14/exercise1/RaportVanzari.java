package com.pao.laboratory14.exercise1;

import java.util.HashMap;
import java.util.Map;

public final class RaportVanzari {
  private Map<TipBilet, Long> numarPerTip = new HashMap<>();
  private Map<TipBilet, Double> incasariPerTip = new HashMap<>();
  private double totalGlobal = 0;
  private double medieGlobala = 0;
  TipBilet tipCelMaiPopular = null;

  public RaportVanzari(
    Map<TipBilet, Long> numarPerTip, 
    Map<TipBilet, Double> incasariPerTip,
    double totalGlobal,
    double medieGlobala,
    TipBilet tipCelMaiPopular
  ) {
    this.incasariPerTip = incasariPerTip;
    this.numarPerTip = numarPerTip;
    this.totalGlobal = totalGlobal;
    this.medieGlobala = medieGlobala;
    this.tipCelMaiPopular = tipCelMaiPopular;
  }

  public Map<TipBilet, Long> getNumarPerTip() {
    return numarPerTip;
  }

  public Map<TipBilet, Double> getIncasariPerTip() {
    return incasariPerTip;
  }

  public double getTotalGlobal() {
    return totalGlobal;
  }

  public double getMedieGlobala() {
    return medieGlobala;
  }

  public TipBilet getTipCelMaiPopular() {
    return tipCelMaiPopular;
  }
}

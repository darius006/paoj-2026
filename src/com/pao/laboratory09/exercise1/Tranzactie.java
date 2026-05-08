package com.pao.laboratory09.exercise1;

import java.io.Serializable;

public class Tranzactie implements Serializable {
  private int id;
  private double suma;
  private String data;
  private String contSursa;
  private String contDestinatie;
  private TipTranzactie tip;
  private transient String note;
  private static final long serialVersionUID = 1L;

}

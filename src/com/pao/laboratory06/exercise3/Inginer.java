package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat 
implements PlataOnline, Comparable<Inginer> {
  public int compareTo(Inginer i) {
    return this.nume.compareTo(i.nume);
  }

  
}

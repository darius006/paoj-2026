package com.pao.proiect.model;

public class PersonalRaft extends Angajat {
  private Sectiune sectiune;

  public PersonalRaft(int id, String nume, Sectiune sectiune) {
    super(id, nume);
    this.sectiune = sectiune;
  }

  public int getSalariuNet() {
    return 2700;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(
      "\nPersonal raft, Sectiune: %s\n",
      sectiune
    );
  }
}

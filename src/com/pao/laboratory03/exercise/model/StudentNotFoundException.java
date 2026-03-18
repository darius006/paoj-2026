package com.pao.laboratory03.exercise.model;

public class StudentNotFoundException extends RuntimeException {
  public StudentNotFoundException() {
    super("Studentul nu a fost gasit.");
  }
}

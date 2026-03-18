package com.pao.laboratory03.exercise.model;

public class InvalidGradeException extends RuntimeException {
  public InvalidGradeException() {
    super("Nota nevalida.");
  }
}

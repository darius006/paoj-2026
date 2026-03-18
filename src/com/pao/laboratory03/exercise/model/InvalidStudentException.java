package com.pao.laboratory03.exercise.model;

public class InvalidStudentException extends RuntimeException{
  public InvalidStudentException() {
    super("Student nevalid.");
  }
}

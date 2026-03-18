package com.pao.laboratory03.exceptions;

public class InvalidAgeException extends RuntimeException {
  public InvalidAgeException() {
    super("Vârstă nevalidă.");
  }
}

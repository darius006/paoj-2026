package com.pao.laboratory03.exercise.model;

import java.util.Map;
import java.util.HashMap;

public class Student {
  private String name;
  private int age;
  private Map<Subject, Double> grades;

  public Student(String name, int age) {
    if (age < 18 || age > 60) {
      throw new InvalidStudentException();
    }
    this.name = name;
    this.age = age;
    this.grades = new HashMap<Subject, Double>();
  }

  public int getAge() {
    return age;
  }

  public Map<Subject, Double> getGrades() {
    return grades;
  }

  public String getName() {
    return name;
  }

  public void addGrade(Subject subject, Double grade) {
    if (grade < 1 || grade > 10) {
      throw new InvalidGradeException();
    }
    this.grades.put(subject, grade);
  }

  public Double getAverage() {
    Double s = 0.0;
    for (var grade : grades.values()) {
      s += grade;
    }
    if (grades.size() == 0) {
      return 0.0;
    }
    return s / grades.size();
  }

  @Override
  public String toString() {
    return String.format("Student{name='%s', age='%d', avg='%.2f'}", name, age, getAverage());
  }
}

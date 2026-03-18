package com.pao.laboratory03.exercise.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.lang.Double;

public class StudentService {
  private List<Student> students;
  private static final StudentService INSTANCE = new StudentService();
  private StudentService() {
    this.students = new ArrayList<Student>();
  }
  public static StudentService getInstance() {
    return INSTANCE;
  }

  public void addStudent(String name, int age) {
    for (Student st : students) {
      if (st.getName() == name) {
        throw new RuntimeException("Studentul deja exista.");
      }
    }
    students.add(new Student(name, age));
  }

  public Student findByName(String name) {
    for (Student st : students) {
      if (st.getName().equals(name)) {
        return st;
      }
    }
    throw new StudentNotFoundException();
  }

  public void addGrade(String studentName, Subject subject, double grade) {
    this.findByName(studentName).addGrade(subject, grade);
  }

  public void printAllStudents() {
    for (Student st : students) {
      System.out.println(st);
    }
  }

  public void printTopStudents() {
    List<Student> l = new ArrayList<Student>(students);
    l.sort(new Comparator<Student>() {
      public int compare(Student s1, Student s2) {
        return -s1.getAverage().compareTo(s2.getAverage());
      }
    });
    for (Student st : l) {
      System.out.println(st);
    }
  }

  public Map<Subject, Double> getAveragePerSubject() {
    Map<Subject, Double> map = new HashMap<Subject, Double>();
    int noSt = students.size();
    for (var subject : Subject.values()) {
      Double s = 0.0;
      for (Student st : students) {
        s += st.getGrades().getOrDefault(subject, 0.0);
      }
      map.put(subject, s / noSt);
    }
    return map;
  }
}

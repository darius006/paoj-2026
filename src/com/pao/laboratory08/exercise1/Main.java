package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
  // Calea către fișierul cu date — relativă la rădăcina proiectului
  private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

  public static void main(String[] args) throws Exception {
    List<Student> students = new ArrayList<Student>();
    BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
    String s;
    while ((s = br.readLine()) != null) {
      if (s.equals("\n")) {
        continue;
      }
      String[] spl = s.split(",");
      students.add(new Student(
        spl[0], 
        Integer.valueOf(spl[1]), 
        new Adresa(spl[2], spl[3])
      ));
    }
    br.close();

    Scanner scanner = new Scanner(System.in);

    String comanda = scanner.nextLine();
    scanner.close();

    String[] pars = comanda.split(" ");
    switch(pars[0]) {
      case "PRINT":
        for (Student student : students) {
          System.out.println(student);
        }
        break;
      case "SHALLOW":
        for (Student student : students) {
          if (student.getNume().equals(pars[1])) {
            Student student2 = (Student) student.shallowClone();
            student2.getAdresa().setOras("MODIFICAT");
            System.out.printf(
              "Original: %s\n",
              student.toString()
            );
            System.out.printf(
              "Clona: %s\n",
              student2.toString()
            );
          }
        }
        break;
      case "DEEP":
        for (Student student : students) {
          if (student.getNume().equals(pars[1])) {
            Student student2 = (Student) student.clone();
            student2.getAdresa().setOras("MODIFICAT");
            System.out.printf(
              "Original: %s\n",
              student.toString()
            );
            System.out.printf(
              "Clona: %s\n",
              student2.toString()
            );
          }
        }
        break;
      default:
        break;
    }
  }
}

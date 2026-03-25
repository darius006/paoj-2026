package com.pao.laboratory05.angajati;

import java.util.Scanner;
/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService as = AngajatService.getInstance();
        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");
            // citește opțiunea și execută acțiunea
            int opt = scanner.nextInt();
            scanner.nextLine();
            switch(opt) {
                case 1:
                    System.out.print("Introdu numele angajatului: ");
                    String nume = scanner.nextLine();
                    System.out.print("Introdu numele departamentului: ");
                    String dep = scanner.nextLine();
                    System.out.print("Introdu locatia departamentului: ");
                    String loc = scanner.nextLine();
                    System.out.print("Introdu salariul angajatului: ");
                    double sal = scanner.nextDouble();
                    as.addAngajat(new Angajat(nume, new Departament(dep, loc), sal));
                    break;
                case 2:
                    as.listBySalary();
                    break;
                case 3:
                    System.out.print("Introdu numele departamentului: ");
                    String numeDept = scanner.nextLine();
                    as.findByDepartment(numeDept);
                    break;
                case 0:
                    System.out.println("Cu bine!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Optiunea nu este valida!");
            }
        }
    }
}

package com.pao.proiect;

import java.util.Scanner;

import com.pao.proiect.service.*;
import com.pao.proiect.exception.*;
import com.pao.proiect.model.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void UI() {
    System.out.println("+-----------------------------------------------------+");
    System.out.println("Alege optiunea: ");
    System.out.println("1. Adauga o carte");
    System.out.println("2. Listeaza cartile alfabetic");
    System.out.println("3. Adauga un cititor");
    System.out.println("4. Imprumuta o carte");
    System.out.println("5. Returneaza o carte");
    System.out.println("6. Adauga un review");
    System.out.println("7. Afiseaza review-urile unui cititor");
    System.out.println("8. Afiseaza cartile neintoarse la timp pt. un cititor");
    System.out.println("9. Arata nr. mediu de stele pt. o carte");
    System.out.println("10. Adauga un angajat");
    System.out.println("11. Listeaza angajatii");
    System.out.println("0. Iesire");
    System.out.println("+-----------------------------------------------------+");
    System.out.print("Optiune aleasa: ");
  }

  public static ISBN safeIsbnRead(Scanner scanner) {
    ISBN isbn;
    try {
      isbn = new ISBN(scanner.nextLine());
    }
    catch (InvalidIsbnException iie) {
      System.out.println(iie.getMessage());
      return null;
    }
    return isbn;
  }

  public static void citireCarte(Scanner scanner) {
    CarteService cs = CarteService.getInstance();
    System.out.print("Introdu ISBN-ul cartii: ");
    ISBN isbn = safeIsbnRead(scanner);
    if (isbn == null) {
      return;
    }
    System.out.print("Introdu titlul cartii: ");
    String titlu = scanner.nextLine();
    System.out.print("Introdu numele complet al autorului: ");
    String numeAutor = scanner.nextLine();
    Autor autor = new Autor(numeAutor);
    scanner.nextLine();
    System.out.print("Introdu numele sectiunii: ");
    String numeSectiune = scanner.nextLine();
    System.out.print("Introdu rating-ul de varsta al sectiunii: ");
    int ratingVarsta = scanner.nextInt();
    scanner.nextLine();
    Sectiune sectiune;
    try {
      sectiune = new Sectiune(numeSectiune, ratingVarsta);
    }
    catch (InvalidAgeException iae) {
      System.out.println(iae.getMessage());
      return;
    }
    System.out.print("Introdu nr de exemplare: ");
    int nrExemplare = scanner.nextInt();
    scanner.nextLine();
    cs.addCarte(new Carte(isbn, autor, sectiune, titlu, nrExemplare));
    System.out.println("Adaugat carte!");
  }

  public static void listareCartiAlfabetic() {
    CarteService cs = CarteService.getInstance();
    cs.listCarte();
  }

  public static void adaugaCititor(Scanner scanner) {
    CititorService cs = CititorService.getInstance();
    System.out.print("Introdu email-ul cititorului: ");
    String email = scanner.nextLine();
    System.out.print("Introdu numele complet al cititorului: ");
    String numeComplet = scanner.nextLine();
    cs.addCititor(new Cititor(email, numeComplet));
  }

  public static void imprumutaCarte(Scanner scanner) {
    CititorService cits = CititorService.getInstance();
    CarteService cars = CarteService.getInstance();
    System.out.print("Introdu ISBN-ul cartii: ");
    ISBN isbn = safeIsbnRead(scanner);
    if (isbn == null) {
      return;
    }
    Carte carte = cars.searchCarte(isbn);
    if (carte == null) {
      System.out.println("Cartea nu este in sistem.");
      return;
    }
    if (carte.getNrExemplare() == 0) {
      System.out.println("Ne pare rau, nu mai sunt exemplare!");
      return;
    }
    System.out.print("Introdu email-ul cititorului: ");
    String email = scanner.nextLine();
    Cititor cititor = cits.searchCititor(email);
    if (cititor == null) {
      System.out.println("Cititorul nu este in sistem.");
      return;
    }
    System.out.println("Introdu data imprumutului in format ZZ/LL/AAAA:");
    String data = scanner.nextLine();
    System.out.println("Introdu data limita de predare in format ZZ/LL/AAAA");
    String predare = scanner.nextLine();
    Imprumut imp = new Imprumut(isbn, email, data, null, predare);

    cits.imprumutaCarte(cititor, imp);
    carte.setNrExemplare(carte.getNrExemplare() - 1);
    System.out.printf(
      "Nr exemplare ramase: %d\n",
      cars.searchCarte(isbn).getNrExemplare()
    );

    System.out.println("Imprumut realizat!");
  }

  public static void returneazaCarte(Scanner scanner) {
    CititorService cits = CititorService.getInstance();
    CarteService cars = CarteService.getInstance();
    System.out.print("Introdu ISBN-ul cartii: ");
    ISBN isbn = safeIsbnRead(scanner);
    if (isbn == null) {
      return;
    }
    Carte carte = cars.searchCarte(isbn);
    if (carte == null) {
      System.out.println("Cartea nu este in sistem.");
      return;
    }
    System.out.print("Introdu email-ul cititorului: ");
    String email = scanner.nextLine();
    Cititor cititor = cits.searchCititor(email);
    if (cititor == null) {
      System.out.println("Cititorul nu este in sistem.");
      return;
    }

    System.out.println("Introdu data predarii in format ZZ/LL/AAAA");
    String predare = scanner.nextLine();

    Imprumut imp = new Imprumut(isbn, email, null, predare, null);

    boolean returnata = cits.returneazaCarte(cititor, imp);

    if (!returnata) {
      System.out.println("Cititorul nu a imprumutat aceasta carte!");
      return;
    }

    carte.setNrExemplare(carte.getNrExemplare() + 1);
    System.out.printf(
      "Nr exemplare ramase: %d\n",
      cars.searchCarte(isbn).getNrExemplare()
    );

    System.out.println("Retur realizat!");
  }

  public static void adaugaReview(Scanner scanner) {
    CititorService cits = CititorService.getInstance();
    CarteService cars = CarteService.getInstance();
    System.out.print("Introdu ISBN-ul cartii: ");
    ISBN isbn = safeIsbnRead(scanner);
    if (isbn == null) {
      return;
    }
    Carte carte = cars.searchCarte(isbn);
    if (carte == null) {
      System.out.println("Cartea nu este in sistem.");
      return;
    }
    System.out.print("Introdu email-ul cititorului: ");
    String email = scanner.nextLine();
    Cititor cititor = cits.searchCititor(email);
    if (cititor == null) {
      System.out.println("Cititorul nu este in sistem.");
      return;
    }

    System.out.print("Introdu nr de stele: ");
    int nrStele = scanner.nextInt();
    scanner.nextLine();

    Review rev = new Review(isbn, email, nrStele);

    cits.addReview(cititor, rev);
    cars.addReview(carte, rev);

    System.out.println("Adaugat review!");

  }

  public static void listReviews(Scanner scanner) {
    CititorService cits = CititorService.getInstance();
    System.out.print("Introdu email-ul cititorului: ");
    String email = scanner.nextLine();
    Cititor cititor = cits.searchCititor(email);
    if (cititor == null) {
      System.out.println("Cititorul nu este in sistem.");
      return;
    }
    cits.listReviews(cititor);
  }

  public static void afiseazaIntarzieri(Scanner scanner) {
    CititorService cits = CititorService.getInstance();
    System.out.print("Introdu email-ul cititorului: ");
    String email = scanner.nextLine();
    Cititor cititor = cits.searchCititor(email);
    if (cititor == null) {
      System.out.println("Cititorul nu este in sistem.");
      return;
    }
    cits.afiseazaIntarzieri(cititor);
  }

  public static void meanReview(Scanner scanner) {
    CarteService cars = CarteService.getInstance();
    System.out.print("Introdu ISBN-ul cartii: ");
    ISBN isbn = safeIsbnRead(scanner);
    if (isbn == null) {
      return;
    }
    Carte carte = cars.searchCarte(isbn);
    System.out.println(carte);
    System.out.print("Medie nr stele: ");
    System.out.println(cars.meanReview(carte));
  }

  public static void adaugaAngajat(Scanner scanner) {
    AngajatService as = AngajatService.getInstance();
    System.out.print("Introdu tipul angajatului (b/pr): ");
    String tip = scanner.nextLine();
    if (!tip.equals("b") && !tip.equals("pr")) {
      System.out.println("Tip nevalid de personal.");
    }
    System.out.print("Introdu id-ul angajatului: ");
    int id = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Introdu numele angajatului: ");
    String nume = scanner.nextLine();
    if (tip.equals("b")) {
      System.out.print("Introdu nr. de limbi vorbite: ");
      int nrLimbi = scanner.nextInt();
      scanner.nextLine();
      as.addAngajat(new Bibliotecar(id, nume, nrLimbi));
    }
    else if (tip.equals("pr")) {
      System.out.println("Introdu numele sectiunii la care este asignat: ");
      String numeSectiune = scanner.nextLine();
      System.out.println("Introdu rating-ul de varsta al sectiunii: ");
      int ratingVarsta = scanner.nextInt();
      scanner.nextLine();
      
      Sectiune sectiune;
      try {
        sectiune = new Sectiune(numeSectiune, ratingVarsta);
      }
      catch (InvalidAgeException iae) {
        System.out.println(iae.getMessage());
        return;
      }
      as.addAngajat(new PersonalRaft(id, nume, sectiune));
    }
  }

  public static void afisareAngajati() {
    AngajatService as = AngajatService.getInstance();
    as.listAngajat();
  }
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;
    while (!exit) {
      UI();
      int opt = scanner.nextInt();
      scanner.nextLine();
      switch (opt) {
        case 1:
          citireCarte(scanner);
          break;
        case 2:
          listareCartiAlfabetic();
          break;
        case 3:
          adaugaCititor(scanner);
          break;
        case 4:
          imprumutaCarte(scanner);
          break;
        case 5:
          returneazaCarte(scanner);
          break;
        case 6:
          adaugaReview(scanner);
          break;
        case 7:
          listReviews(scanner);
          break;
        case 8:
          afiseazaIntarzieri(scanner);
          break;
        case 9:
          meanReview(scanner);
          break;
        case 10:
          adaugaAngajat(scanner);
          break;
        case 11:
          afisareAngajati();
          break;
        case 0:
          exit = true;
          System.out.println("Cu bine!");
          break;
        default:
          System.out.println("Optiunea nu este valida. Incearca din nou.");
          break;
      }
      System.out.println();
    }
    scanner.close();
  }
}

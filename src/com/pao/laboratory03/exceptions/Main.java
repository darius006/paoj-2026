package com.pao.laboratory03.exceptions;

import java.util.List;
import java.util.ArrayList;

/**
 * Exercițiul 3 — Excepții (checked, unchecked, custom)
 *
 * Creează în acest pachet (lângă Main.java) două clase de excepții custom,
 * apoi demonstrează-le aici.
 *
 * PASUL 1 — Creează InvalidAgeException.java (fișier separat):
 *   - Extinde RuntimeException (unchecked)
 *   - Constructor cu String message → apelează super(message)
 *
 * PASUL 2 — Creează DuplicateEntryException.java (fișier separat):
 *   - Extinde RuntimeException (unchecked)
 *   - Constructor cu String message → apelează super(message)
 *
 * PASUL 3 — În acest Main.java, implementează și demonstrează:
 *
 *   a) UNCHECKED EXCEPTIONS — NullPointerException, ArrayIndexOutOfBoundsException:
 *      - Creează o metodă riskyMethod() care aruncă NullPointerException
 *      - Prinde-o cu try-catch, afișează mesajul erorii
 *      - Adaugă un bloc finally care se execută mereu
 *
 *   b) CUSTOM EXCEPTIONS — InvalidAgeException, DuplicateEntryException:
 *      - Creează o metodă validateAge(int age) care aruncă InvalidAgeException
 *        dacă age < 0 sau age > 150
 *      - Creează o metodă addToList(List<String> list, String name) care aruncă
 *        DuplicateEntryException dacă name există deja în listă
 *      - Demonstrează ambele cu try-catch
 *
 *   c) MULTI-CATCH:
 *      - Prinde InvalidAgeException | DuplicateEntryException într-un singur catch
 *
 *   d) CATCH ORDERING:
 *      - Demonstrează că prinderea specifică (InvalidAgeException) trebuie
 *        să fie ÎNAINTE de cea generală (RuntimeException)
 *
 *   e) THROW vs THROWS:
 *      - Creează o metodă cu semnătura: void process(int age) throws InvalidAgeException
 *      - Apeleaz-o din main cu try-catch
 *
 * Output așteptat:
 *
 * === a) Unchecked — NullPointerException ===
 * Prins: Cannot invoke "String.length()" because "s" is null
 * Finally se execută mereu!
 *
 * === b) Custom exceptions ===
 * InvalidAgeException: Vârsta -5 nu este validă (0-150)
 * DuplicateEntryException: 'Ana' există deja în listă
 *
 * === c) Multi-catch ===
 * Excepție prinsă: Vârsta 200 nu este validă (0-150)
 *
 * === d) Catch ordering (specific → general) ===
 * InvalidAgeException prinsă specific: Vârsta -1 nu este validă (0-150)
 *
 * === e) Throw vs throws ===
 * Metoda process() a aruncat: Vârsta 999 nu este validă (0-150)
 */
public class Main {
    public static void riskyMethod() {
        throw new NullPointerException();
    }
    public static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException();
        }
    }
    public static void addToList(List<String> list, String name) {
        for (String el : list) {
            if (name == el) {
                throw new DuplicateEntryException();
            }
        }
        list.add(name);
    }
    public static void process(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException();
        }
    }
    public static void main(String[] args) {
        // TODO: implementează pașii de mai sus
        // Hint: creează mai întâi InvalidAgeException.java și DuplicateEntryException.java
        try {
            riskyMethod();
        }
        catch (NullPointerException exc) {
            System.out.println(exc.getMessage());
        }
        finally {
            System.out.println("Acest bloc se executa mereu.");
        }

        try {
            validateAge(151);
        }
        catch (InvalidAgeException exc) {
            System.out.println(exc.getMessage());
        }
        List<String> l = new ArrayList<String>();
        try {
            addToList(l, "string");
            addToList(l, "string");
        }
        catch (DuplicateEntryException exc) {
            System.out.println(exc.getMessage());
        }

        
        try {
            addToList(l, "string");
        }
        catch (DuplicateEntryException | InvalidAgeException exc) {
            System.out.println(exc.getMessage());
        }

        
        // VSCode nu ma lasa sa adaug blocul unreachable cu InvalidAgeException
        // deci l-am comentat
        try {
            validateAge(151);
        }
        catch (RuntimeException exc) {
            System.out.println("Exceptie runtime");
        }
        // catch (InvalidAgeException exc) {
        //     // acest cod nu va fi executat niciodata
        //     System.out.println(exc.getMessage());
        // }

        try {
            process(-1);
        }
        catch (InvalidAgeException exc) {
            System.out.println("process exception");
        }
    }
}


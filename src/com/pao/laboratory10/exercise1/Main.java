package com.pao.laboratory10.exercise1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Tranzactie> tranzactii = new LinkedList<Tranzactie>();
        Scanner scanner = new Scanner(System.in);
        String comanda;
        Iterator<Tranzactie> it;
        Tranzactie t;
        String[] parse;
        double nr;
        int cnt = 0;
        while (true) {
            try {
                comanda = scanner.nextLine();
            }
            catch (NoSuchElementException nsee) {
                break;
            }
            parse = comanda.split(" ");
            switch(parse[0]) {
                case "ENQUEUE":
                    tranzactii.addLast(new Tranzactie(
                        Integer.valueOf(parse[1]), 
                        Double.valueOf(parse[2]), 
                        parse[3], 
                        TipTranzactie.valueOf(parse[4])
                    ));
                    break;
                case "DEQUEUE":
                    try {
                        t = tranzactii.removeFirst();
                        System.out.printf("Procesat: %s\n", t);
                    }
                    catch (NoSuchElementException nsee) {
                        System.out.println("Coada goala.");
                    }
                    break;
                case "PUSH":
                    tranzactii.addFirst(new Tranzactie(
                        Integer.valueOf(parse[1]), 
                        Double.valueOf(parse[2]), 
                        parse[3], 
                        TipTranzactie.valueOf(parse[4])
                    ));
                    break;
                case "POP":
                    try {
                        t = tranzactii.removeFirst();
                        System.out.printf("Extras: %s\n", t);
                    }
                    catch (NoSuchElementException nsee) {
                        System.out.println("Coada goala.");
                    }
                    break;
                case "REMOVE_DEBIT":
                    it = tranzactii.iterator();
                    while(it.hasNext()) {
                        t = it.next();
                        if (t.getTip() == TipTranzactie.DEBIT) {
                            it.remove();
                            cnt++;
                        }
                    }
                    System.out.printf(
                        "Eliminat %d tranzactii DEBIT.\n",
                        cnt
                    );
                    break;
                case "REMOVE_BELOW":
                    it = tranzactii.iterator();
                    nr = Double.valueOf(parse[1]);
                    while(it.hasNext()) {
                        t = it.next();
                        if (t.getSuma() < nr) {
                            it.remove();
                            cnt++;
                        }
                    }
                    System.out.printf(
                        "Eliminat %d tranzactii sub %.2f RON.\n",
                        cnt, nr
                    );
                    break;
                case "PRINT":
                    for (Tranzactie k : tranzactii) {
                        System.out.println(k);
                    }
                    break;
                case "SIZE":
                    System.out.printf(
                        "Dimensiune coada: %d\n",
                        tranzactii.size()
                    );
                default:
                    break;
            }
        }

        scanner.close();
        // TODO: Implementează conform Readme.md
        //
        // Folosește LinkedList<Tranzactie> ca structură internă.
        // Citește comenzi din stdin până la EOF:
        //
        //   ENQUEUE id suma data tip   → addLast  (niciun output)
        //   DEQUEUE                    → removeFirst sau "Coada goala."
        //                                format: "Procesat: [id] data tip: suma RON"
        //   PUSH id suma data tip      → addFirst  (niciun output)
        //   POP                        → removeFirst sau "Coada goala."
        //                                format: "Extras: [id] data tip: suma RON"
        //   REMOVE_DEBIT               → Iterator.remove() pe toate DEBIT
        //                                afișează "Eliminat N tranzactii DEBIT."
        //   REMOVE_BELOW threshold     → Iterator.remove() pe suma < threshold
        //                                afișează "Eliminat N tranzactii sub threshold RON."
        //   PRINT                      → afișează toate, câte una pe linie
        //   SIZE                       → "Dimensiune coada: N"
        //
        // Format linie tranzacție: [id] data tip: suma RON
        //   Ex: [1] 2024-01-10 CREDIT: 500.00 RON
    }
}

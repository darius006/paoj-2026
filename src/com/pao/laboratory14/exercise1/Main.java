package com.pao.laboratory14.exercise1;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        List<Bilet> bilete = new ArrayList<Bilet>();
        RaportVanzari raport;
        for (int i = 0; i < n; ++i) {
            String linie = scanner.nextLine();
            // System.out.println(linie);
            String[] temp = linie.split(" ");
            int id = Integer.parseInt(temp[0]);
            String eveniment = temp[1];
            TipBilet tip = TipBilet.valueOf(temp[2]);
            double pret = Double.valueOf(temp[3]);
            bilete.add(new Bilet(id, eveniment, tip, pret));
        }
        String comanda = scanner.nextLine();
        TipBilet[] tipuri = TipBilet.values();
        Arrays.sort(tipuri, (TipBilet t1, TipBilet t2) -> 
        String.valueOf(t1).compareTo(String.valueOf(t2)));
        switch (comanda) {
            case "RAPORT_SIMPLU":
                raport = bilete.stream().collect(new ColectorRaportVanzari());
                for (TipBilet tip : tipuri) {
                    if (!raport.getNumarPerTip().containsKey(tip)) {
                        continue;
                    }
                    System.out.printf(
                        "%s: count=%d incasari=%.2f RON\n",
                        String.valueOf(tip), raport.getNumarPerTip().get(tip), raport.getIncasariPerTip().get(tip)
                    );
                }
                break;
            case "RAPORT_COMPLET":
                raport = bilete.stream().collect(new ColectorRaportVanzari());
                for (TipBilet tip : tipuri) {
                    if (!raport.getNumarPerTip().containsKey(tip)) {
                        continue;
                    }
                    System.out.printf(
                        "%s: count=%d incasari=%.2f RON\n",
                        String.valueOf(tip), raport.getNumarPerTip().get(tip), raport.getIncasariPerTip().get(tip)
                    );
                }
                System.out.println("---");
                System.out.printf(
                    "Total: %.2f RON\nMedie: %.2f RON\nCel mai popular: %s\n",
                    raport.getTotalGlobal(), raport.getMedieGlobala(), raport.getTipCelMaiPopular()
                );
                break;
        }
        scanner.close();
    }
}

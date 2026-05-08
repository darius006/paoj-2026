package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.Tranzactie;
import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Tranzactie> tranzactii = new LinkedList<Tranzactie>();
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < N; ++i) {
            String[] line = scanner.nextLine().split(" ");
            int id = Integer.parseInt(line[0]);
            double suma = Double.parseDouble(line[1]);
            String data = line[2];
            TipTranzactie tip = TipTranzactie.valueOf(line[3]);

            tranzactii.add(new Tranzactie(id, suma, data, tip));
        }


        String comanda;
        String[] parse;
        while (true) {
            try {
                comanda = scanner.nextLine();
            }
            catch (NoSuchElementException nsee) {
                break;
            }
            parse = comanda.split(" ");
            LinkedHashSet<Integer> ids = new LinkedHashSet<Integer>();
            TreeMap<String, double[]> raport = new TreeMap<String, double[]>();
            switch(parse[0]) {
                case "UNIQUE_IDS":
                    for (Tranzactie t : tranzactii) {
                        ids.add(t.getId());
                    }
                    System.out.printf(
                        "IDs unice (%d): ",
                        ids.size()
                    );
                    List<Integer> temp = new ArrayList<Integer>();
                    for (Integer id : ids) {
                        temp.add(id);
                    } 
                    System.out.print("[");
                    for (int i = 0; i < temp.size() - 1; ++i) {
                        System.out.printf(
                            "%d, ",
                            temp.get(i)
                        );
                    }
                    System.out.printf(
                        "%d]\n",
                        temp.get(temp.size() - 1)
                    );
                    break;
                case "MONTHLY_REPORT":
                    for (Tranzactie t : tranzactii) {
                        String s = t.getData().substring(0, 7);
                        raport.putIfAbsent(
                            s,
                            new double[]{0, 0}
                        );
                        double[] a = raport.get(s);
                        switch (t.getTip()) {
                            case DEBIT:
                                a = raport.get(s);
                                a[1] += t.getSuma();
                                raport.put(s, a);
                                break;
                            case CREDIT:
                                a = raport.get(s);
                                a[0] += t.getSuma();
                                raport.put(s, a);
                                break;
                        }
                    }
                    for (Map.Entry<String, double[]> entry : raport.entrySet()) {
                        System.out.printf(
                            "%s: CREDIT %.2f RON, DEBIT %.2f RON\n",
                            entry.getKey(), entry.getValue()[0], entry.getValue()[1]
                        );
                    }
                    break;
                case "TOP":
                    int n = Integer.parseInt(parse[1]);
                    LinkedList<Tranzactie> copie = tranzactii;
                    Collections.sort(copie);
                    System.out.printf(
                        "Top %d: \n",
                        n
                    );
                    for (Tranzactie t : copie) {
                        System.out.println(t);
                    }
                    break;
                case "SORT_ASC":
                    Collections.sort(tranzactii);
                    for (Tranzactie t : tranzactii) {
                        System.out.println(t);
                    }
                    break;
                case "SORT_DESC":
                    Collections.sort(tranzactii);
                    Collections.reverse(tranzactii);
                    for (Tranzactie t : tranzactii) {
                        System.out.println(t);
                    }
                    break;
                case "REVERSE":
                    Collections.reverse(tranzactii);
                    for (Tranzactie t : tranzactii) {
                        System.out.println(t);
                    }
                    break;
                case "MIN_MAX":
                    System.out.printf(
                        "MIN: %s\nMAX: %s\n",
                        Collections.min(tranzactii), Collections.max(tranzactii)
                    );
                    break;
                case "CME_DEMO":
                    try { 
                        for (Tranzactie t : tranzactii) 
                            tranzactii.remove(t); 
                    } 
                    catch (ConcurrentModificationException e) { 
                        System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata."); 
                    }
                    break;
                default:
                    break;
            }
        }

        scanner.close();
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data tip) — pot exista duplicate de id
        //    Stochează-le toate într-un ArrayList<Tranzactie> (cu duplicate, ordine inserare)
        //
        // 2. Procesează comenzile din stdin până la EOF:
        //
        //   UNIQUE_IDS      → LinkedHashSet<Integer> cu id-urile în ordinea primei apariții
        //                     afișează: "IDs unice (N): [1, 2, 3, ...]"
        //
        //   MONTHLY_REPORT  → TreeMap<String, ...> grupat pe yyyy-MM (substring 0-7 din data)
        //                     pentru fiecare lună, sumele CREDIT și DEBIT
        //                     format: "yyyy-MM: CREDIT X.XX RON, DEBIT Y.YY RON"
        //
        //   TOP n           → primele n tranzacții după suma descrescătoare (nu modifică lista)
        //                     afișează "Top n:" urmat de n linii
        //
        //   SORT_ASC        → Collections.sort cu suma crescătoare; afișează lista sortată
        //   SORT_DESC       → Collections.sort cu suma descrescătoare; afișează lista sortată
        //   REVERSE         → Collections.reverse; afișează lista
        //   MIN_MAX         → Collections.min/max după suma
        //                     "MIN: [id] data tip: suma RON"
        //                     "MAX: [id] data tip: suma RON"
        //
        //   CME_DEMO        → încearcă for(t : lista) lista.remove(t) în try-catch
        //                     afișează "ConcurrentModificationException prins: modificare in iteratie detectata."
        //
        // Format linie tranzacție: [id] data tip: suma RON
        //   Ex: [1] 2024-01-15 CREDIT: 1500.00 RON
    }
}

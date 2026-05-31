package com.pao.laboratory14.exercise1;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class ColectorRaportVanzari
implements Collector<Bilet,
Map<TipBilet, double[]>,
RaportVanzari> {
  @Override
  public Supplier<Map<TipBilet, double[]>> supplier() {
    return HashMap::new;
  }

  @Override
  public BiConsumer<Map<TipBilet, double[]>, Bilet> accumulator() {
    return (map, bilet) -> {
      map.putIfAbsent(bilet.getTip(), new double[2]);

      double[] val = map.get(bilet.getTip());

      val[0]++;
      val[1] += bilet.getPret();
    };
  }

  @Override
  public BinaryOperator<Map<TipBilet, double[]>> combiner() {
      return (left, right) -> {

      for (var entry : right.entrySet()) {

          left.putIfAbsent(entry.getKey(), new double[2]);

          left.get(entry.getKey())[0] += entry.getValue()[0];
          left.get(entry.getKey())[1] += entry.getValue()[1];
      }

      return left;
    };
  }

@Override
public Function<Map<TipBilet, double[]>, RaportVanzari> finisher() {
      return map -> {

          Map<TipBilet, Long> numar = new HashMap<>();
          Map<TipBilet, Double> incasari = new HashMap<>();

          double total = 0;
          long totalBilete = 0;

          for (var entry : map.entrySet()) {

              TipBilet tip = entry.getKey();

              long count = (long) entry.getValue()[0];
              double suma = entry.getValue()[1];

              numar.put(tip, count);
              incasari.put(tip, suma);

              total += suma;
              totalBilete += count;
          }

          double medie = totalBilete == 0
                  ? 0
                  : total / totalBilete;

          TipBilet popular = Arrays.stream(TipBilet.values())
                  .filter(numar::containsKey)
                  .max(Comparator.comparingLong(numar::get))
                  .orElse(null);

          return new RaportVanzari(
                  numar,
                  incasari,
                  total,
                  medie,
                  popular
          );
      };
  }

  @Override
  public Set<Characteristics> characteristics() {
      return Set.of();
  }
}

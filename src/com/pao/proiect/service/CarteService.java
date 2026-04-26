package com.pao.proiect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pao.proiect.model.*;

import java.util.HashMap;
import java.util.Collections;

public class CarteService {
  private static final CarteService instance = new CarteService();

  private CarteService() {}

  public static CarteService getInstance() {
    return instance;
  }

  private List<Carte> carti = new ArrayList<Carte>();
  private Map<Carte, List<Review>> reviews = new HashMap<Carte, List<Review>>();

  public void addCarte(Carte c) {
    carti.add(c);
  }

  public void removeCarte(ISBN isbn) {
    for (int i = 0; i < carti.size(); ++i) {
      if (carti.get(i).getIsbn() == isbn) {
        carti.remove(i);
      }
    }
  }

  public Carte searchCarte(ISBN isbn) {
    for (Carte c : carti) {
      if (c.getIsbn().equals(isbn)) {
        return c;
      }
    }
    return null;
  }

  public void listCarte() {
    List<Carte> copie = new ArrayList<Carte>(carti);
    Collections.sort(copie);
    for (Carte c : copie) {
      System.out.println(c);
    }
  }

  public void addReview(Carte carte, Review rev) {
    reviews.putIfAbsent(carte, new ArrayList<Review>());
    reviews.get(carte).add(rev);
  }

  public float meanReview(Carte carte) {
    reviews.putIfAbsent(carte, new ArrayList<Review>());
    float sum = 0;
    List<Review> lr = reviews.get(carte);
    for (Review r : lr) {
      sum += r.getNrStele();
    }
    sum /= (float)lr.size();
    return sum;
  }
}

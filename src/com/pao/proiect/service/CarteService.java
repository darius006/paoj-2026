package com.pao.proiect.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pao.proiect.model.Carte;
import com.pao.proiect.model.ISBN;
import com.pao.proiect.model.Review;
import com.pao.proiect.repository.CarteRepository;

public class CarteService {
  private static final CarteService instance = new CarteService();

  private CarteService() {}

  public static CarteService getInstance() {
    return instance;
  }

  private final CarteRepository carteRepository = new CarteRepository();
  private final Map<Carte, List<Review>> reviews = new HashMap<Carte, List<Review>>();

  public void addCarte(Carte c) {
    try {
      carteRepository.save(c);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void removeCarte(ISBN isbn) {
    try {
      carteRepository.delete(isbn.toString());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Carte searchCarte(ISBN isbn) {
    try {
      return carteRepository.findById(isbn.toString()).orElse(null);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void listCarte() {
    try {
      for (Carte c : carteRepository.findAll()) {
        System.out.println(c);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void addReview(Carte carte, Review rev) {
    reviews.putIfAbsent(carte, new ArrayList<Review>());
    reviews.get(carte).add(rev);
  }

  public float meanReview(Carte carte) {
    reviews.putIfAbsent(carte, new ArrayList<Review>());
    List<Review> lr = reviews.get(carte);
    if (lr.isEmpty()) {
      return 0;
    }
    float sum = 0;
    for (Review r : lr) {
      sum += r.getNrStele();
    }
    return sum / (float) lr.size();
  }

  public int adjustNrExemplare(ISBN isbn, int delta) {
    Carte carte = searchCarte(isbn);
    if (carte == null) {
      throw new IllegalArgumentException("Carte inexistenta: " + isbn);
    }
    carte.setNrExemplare(carte.getNrExemplare() + delta);
    try {
      carteRepository.update(carte);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return carte.getNrExemplare();
  }
}

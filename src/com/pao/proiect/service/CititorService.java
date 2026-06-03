package com.pao.proiect.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pao.proiect.model.Cititor;
import com.pao.proiect.model.Imprumut;
import com.pao.proiect.model.Review;
import com.pao.proiect.repository.CititorRepository;
import com.pao.proiect.repository.ImprumutRepository;

public class CititorService {
  private static final CititorService instance = new CititorService();

  private CititorService() {}

  public static CititorService getInstance() {
    return instance;
  }

  private final CititorRepository cititorRepository = new CititorRepository();
  private final ImprumutRepository imprumutRepository = new ImprumutRepository();
  private final Map<Cititor, List<Review>> reviews = new HashMap<Cititor, List<Review>>();

  public void addCititor(Cititor c) {
    try {
      cititorRepository.save(c);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean removeCititor(String email) {
    try {
      if (searchCititor(email) == null) {
        return false;
      }
      cititorRepository.delete(email);
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Cititor searchCititor(String email) {
    try {
      return cititorRepository.findById(email).orElse(null);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void listCititor() {
    try {
      for (Cititor c : cititorRepository.findAll()) {
        System.out.println(c);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void imprumutaCarte(Cititor cititor, Imprumut imp) {
    try {
      imprumutRepository.save(imp);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean returneazaCarte(Cititor cititor, Imprumut imp) {
    try {
      ImprumutRepository.Key key = new ImprumutRepository.Key(imp.getIsbn().toString(), cititor.getEmail());
      Optional<Imprumut> existing = imprumutRepository.findById(key);
      if (existing.isEmpty()) {
        return false;
      }
      Imprumut loan = existing.get();
      loan.setDataPredare(imp.getDataPredare());
      imprumutRepository.update(loan);
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void addReview(Cititor cititor, Review rev) {
    reviews.putIfAbsent(cititor, new ArrayList<Review>());
    reviews.get(cititor).add(rev);
  }

  public void listReviews(Cititor cititor) {
    reviews.putIfAbsent(cititor, new ArrayList<Review>());
    List<Review> rev = reviews.get(cititor);
    for (Review r : rev) {
      System.out.println(r);
    }
  }

  public void afiseazaIntarzieri(Cititor cititor) {
    try {
      List<Imprumut> imprumuturi = imprumutRepository.findAll();
      for (Imprumut im : imprumuturi) {
        if (!im.getEmail().equals(cititor.getEmail())) {
          continue;
        }
        if (im.getDataPredare() == null) {
          continue;
        }

        String[] temp = im.getDataPredare().split("/");
        int[] dataPredare = new int[]{
          Integer.valueOf(temp[0]),
          Integer.valueOf(temp[1]),
          Integer.valueOf(temp[2])
        };

        temp = im.getDataLimitaPredare().split("/");

        int[] dataLimitaPredare = new int[]{
          Integer.valueOf(temp[0]),
          Integer.valueOf(temp[1]),
          Integer.valueOf(temp[2])
        };

        if (dataPredare[2] < dataLimitaPredare[2]) {
          continue;
        } else if (
          dataPredare[2] == dataLimitaPredare[2] &&
          dataPredare[1] < dataLimitaPredare[1]
        ) {
          continue;
        } else if (
          dataPredare[2] == dataLimitaPredare[2] &&
          dataPredare[1] == dataLimitaPredare[1] && 
          dataPredare[0] < dataLimitaPredare[0]
        ) {
          continue;
        }

        System.out.println(im);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}

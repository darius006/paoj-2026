package com.pao.proiect.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pao.proiect.model.Carte;
import com.pao.proiect.model.Cititor;
import com.pao.proiect.model.Imprumut;
import com.pao.proiect.model.Review;

public class CititorService {
  private static final CititorService instance = new CititorService();

  private CititorService() {}

  public static CititorService getInstance() {
    return instance;
  }

  private  List<Cititor> cititori = new ArrayList<Cititor>();

  private  Map<Cititor, List<Review>> reviews = new HashMap<Cititor, List<Review>>();
  private  Map<Cititor, List<Imprumut>> imprumuturi = new HashMap<Cititor, List<Imprumut>>();

  public void addCititor(Cititor c) {
    cititori.add(c);
  }

  public boolean removeCititor(String email) {
    for (int i = 0; i < cititori.size(); ++i) {
      if (cititori.get(i).getEmail().equals(email)) {
        cititori.remove(i);
        return true;
      }
    }
    return false;
  }

  public Cititor searchCititor(String email) {
    for (Cititor c : cititori) {
      if (c.getEmail().equals(email)) {
        return c;
      }
    }
    return null;
  }

  public void listCititor() {
    for (Cititor c : cititori) {
      System.out.println(c);
    }
  }

  public void imprumutaCarte(Cititor cititor, Imprumut imp) {
    imprumuturi.putIfAbsent(cititor, new ArrayList<Imprumut>());
    imprumuturi.get(cititor).add(imp);
  }

  public boolean returneazaCarte(Cititor cititor, Imprumut imp) {
    imprumuturi.putIfAbsent(cititor, new ArrayList<Imprumut>());
    List<Imprumut> impr = imprumuturi.get(cititor);
    for (Imprumut im : impr) {
      if (im.getIsbn().equals(imp.getIsbn())) {
        im.setDataPredare(imp.getDataPredare());
        return true;
      }
    }
    return false;
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
    imprumuturi.putIfAbsent(cititor, new ArrayList<Imprumut>());
    List<Imprumut> impr = imprumuturi.get(cititor);
    for (Imprumut im : impr) {
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
      }

      else if (
        dataPredare[2] == dataLimitaPredare[2] &&
        dataPredare[1] < dataLimitaPredare[1]
      ) {
        continue;
      }

      else if (
        dataPredare[2] == dataLimitaPredare[2] &&
        dataPredare[1] == dataLimitaPredare[1] && 
        dataPredare[0] < dataLimitaPredare[0]
      ) {
        continue;
      }

      System.out.println(im);
    }
  }
}

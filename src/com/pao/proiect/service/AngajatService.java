package com.pao.proiect.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.pao.proiect.model.Angajat;
import com.pao.proiect.model.Bibliotecar;
import com.pao.proiect.model.PersonalRaft;
import com.pao.proiect.repository.AngajatRaftRepository;
import com.pao.proiect.repository.BibliotecarRepository;

public class AngajatService {
  private static final AngajatService instance = new AngajatService();

  private AngajatService() {}

  public static AngajatService getInstance() {
    return instance;
  }

  private final BibliotecarRepository bibliotecarRepository = new BibliotecarRepository();
  private final AngajatRaftRepository personalRaftRepository = new AngajatRaftRepository();

  public void addAngajat(Angajat c) {
    try {
      if (c instanceof Bibliotecar bibliotecar) {
        bibliotecarRepository.save(bibliotecar);
      } else if (c instanceof PersonalRaft personalRaft) {
        personalRaftRepository.save(personalRaft);
      } else {
        throw new IllegalArgumentException("Tip de angajat neacceptat: " + c.getClass().getName());
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void removeCarte(int id) {
    try {
      Optional<Bibliotecar> bibliotecar = bibliotecarRepository.findById(id);
      if (bibliotecar.isPresent()) {
        bibliotecarRepository.delete(id);
        return;
      }
      Optional<PersonalRaft> personalRaft = personalRaftRepository.findById(id);
      if (personalRaft.isPresent()) {
        personalRaftRepository.delete(id);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Angajat searchAngajat(int id) {
    try {
      Optional<Bibliotecar> bibliotecar = bibliotecarRepository.findById(id);
      if (bibliotecar.isPresent()) {
        return bibliotecar.get();
      }
      Optional<PersonalRaft> personalRaft = personalRaftRepository.findById(id);
      return personalRaft.orElse(null);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void listAngajat() {
    try {
      List<Bibliotecar> bibliotecari = bibliotecarRepository.findAll();
      for (Bibliotecar bibliotecar : bibliotecari) {
        System.out.println(bibliotecar);
      }
      List<PersonalRaft> personalRafti = personalRaftRepository.findAll();
      for (PersonalRaft personalRaft : personalRafti) {
        System.out.println(personalRaft);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}

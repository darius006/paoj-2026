package com.pao.laboratory14.exercise2;

import com.pao.laboratory14.exercise1.TipBilet;
import com.pao.laboratory14.exercise2.model.Eveniment;
import com.pao.laboratory14.exercise2.repository.EvenimentRepository;
import com.pao.laboratory14.exercise2.util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            EvenimentRepository repository = new EvenimentRepository(connection);
            repository.initSchema();

            try (Scanner scanner = new Scanner(System.in)) {
                while (scanner.hasNext()) {
                    String command = scanner.next();
                    switch (command) {
                        case "ADD":
                            handleAdd(scanner, repository);
                            break;
                        case "LIST":
                            handleList(repository);
                            break;
                        case "DELETE":
                            handleDelete(scanner, repository);
                            break;
                        case "COUNT":
                            handleCount(repository);
                            break;
                        default:
                            scanner.nextLine();
                            break;
                    }
                }
            }
        } catch (IOException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void handleAdd(Scanner scanner, EvenimentRepository repository) throws SQLException {
        String nume = scanner.next();
        String data = scanner.next();
        int capacitate = scanner.nextInt();
        String tipString = scanner.next();
        TipBilet tip = TipBilet.valueOf(tipString);
        Eveniment eveniment = new Eveniment(nume, data, capacitate, tip);
        repository.save(eveniment);
        System.out.printf(
            "Adaugat: [%d] %s%n", 
            eveniment.getId(), eveniment.getNume()
        );
    }

    private static void handleList(EvenimentRepository repository) throws SQLException {
        List<Eveniment> evenimente = repository.findAll();
        for (Eveniment eveniment : evenimente) {
            System.out.println(eveniment);
        }
    }

    private static void handleDelete(Scanner scanner, EvenimentRepository repository) throws SQLException {
        int id = scanner.nextInt();
        int deleted = repository.deleteImpl(id);
        if (deleted > 0) {
            System.out.printf("Sters: %d%n", id);
        } else {
            System.out.printf("Nu exista: %d%n", id);
        }
    }

    private static void handleCount(EvenimentRepository repository) throws SQLException {
        System.out.printf("Total: %d%n", repository.count());
    }
}

package com.pao.proiect.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pao.proiect.model.*;
import com.pao.proiect.util.DatabaseConnection;

public class CarteRepository implements Repository<Carte, String> {

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Carte mapRow(ResultSet rs) throws SQLException {
        String isbn_val = rs.getString("isbn");
        ISBN isbn = new ISBN(isbn_val);
        String titlu = rs.getString("titlu");
        String nume_autor = rs.getString("nume_autor");
        Autor a = new Autor(nume_autor);
        String nume_sectiune = rs.getString("nume_sectiune");
        int rating_varsta = rs.getInt("rating_varsta");
        Sectiune s = new Sectiune(nume_sectiune, rating_varsta);
        int nr_exemplare = rs.getInt("nr_exemplare");

        Carte c = new Carte(isbn, a, s, titlu, nr_exemplare);
        return c;
    }

    @Override
    public void save(Carte carte) throws SQLException {
        String sql = """
            INSERT INTO carte
            (isbn, titlu, nume_autor, nume_sectiune, rating_varsta, nr_exemplare)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, carte.getIsbn().toString());
            ps.setString(2, carte.getTitlu());
            ps.setString(3, carte.getAutor().getNumeComplet());
            ps.setString(4, carte.getSectiune().getNume());
            ps.setInt(5, carte.getSectiune().getRatingVarsta());
            ps.setInt(6, carte.getNrExemplare());

            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    public Optional<Carte> findById(String isbn) throws SQLException {
        String sql = """
            SELECT isbn, titlu, nume_autor,
                  nume_sectiune, rating_varsta, nr_exemplare
            FROM carte
            WHERE isbn = ?
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, isbn);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Carte> findAll() throws SQLException {
        String sql = """
            SELECT isbn, titlu, nume_autor,
                  nume_sectiune, rating_varsta, nr_exemplare
            FROM carte
            ORDER BY titlu
            """;

        List<Carte> list = new ArrayList<>();

        try (PreparedStatement ps = getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }

        return list;
    }

    @Override
    public void update(Carte carte) throws SQLException {
        String sql = """
            UPDATE carte
            SET titlu = ?,
                nume_autor = ?,
                nume_sectiune = ?,
                rating_varsta = ?,
                nr_exemplare = ?
            WHERE isbn = ?
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, carte.getTitlu());
            ps.setString(2, carte.getAutor().getNumeComplet());
            ps.setString(3, carte.getSectiune().getNume());
            ps.setInt(4, carte.getSectiune().getRatingVarsta());
            ps.setInt(5, carte.getNrExemplare());
            ps.setString(6, carte.getIsbn().toString());

            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    public void delete(String isbn) throws SQLException {
        String sql = "DELETE FROM carte WHERE isbn = ?";

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, isbn);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}

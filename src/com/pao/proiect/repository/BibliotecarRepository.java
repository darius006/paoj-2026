package com.pao.proiect.repository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pao.proiect.model.Bibliotecar;
import com.pao.proiect.util.DatabaseConnection;

public class BibliotecarRepository implements Repository<Bibliotecar, Integer> {

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private String getNumeComplet(Bibliotecar bibliotecar) {
        try {
            Field field = bibliotecar.getClass().getSuperclass().getDeclaredField("numeComplet");
            field.setAccessible(true);
            return (String) field.get(bibliotecar);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to read Bibliotecar name", e);
        }
    }

    private int getNrLimbiVorbite(Bibliotecar bibliotecar) {
        try {
            Field field = Bibliotecar.class.getDeclaredField("nrLimbiVorbite");
            field.setAccessible(true);
            return field.getInt(bibliotecar);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to read Bibliotecar languages count", e);
        }
    }

    private Bibliotecar mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nume = rs.getString("nume_complet");
        int nrLimbi = rs.getInt("nr_limbi_vorbite");
        return new Bibliotecar(id, nume, nrLimbi);
    }

    @Override
    public void save(Bibliotecar bibliotecar) throws SQLException {
        String angajatSql = "INSERT INTO angajat (id, nume_complet) VALUES (?, ?)";
        String bibliotecarSql = "INSERT INTO bibliotecar (id, nr_limbi_vorbite) VALUES (?, ?)";

        try (Connection conn = getConn();
             PreparedStatement psAngajat = conn.prepareStatement(angajatSql);
             PreparedStatement psBibliotecar = conn.prepareStatement(bibliotecarSql)) {
            psAngajat.setInt(1, bibliotecar.getIdAngajat());
            psAngajat.setString(2, getNumeComplet(bibliotecar));
            psAngajat.executeUpdate();

            psBibliotecar.setInt(1, bibliotecar.getIdAngajat());
            psBibliotecar.setInt(2, getNrLimbiVorbite(bibliotecar));
            psBibliotecar.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Bibliotecar> findById(Integer id) throws SQLException {
        String sql = """
            SELECT a.id, a.nume_complet, b.nr_limbi_vorbite
            FROM angajat a
            JOIN bibliotecar b ON a.id = b.id
            WHERE a.id = ?
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, id);
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
    public List<Bibliotecar> findAll() throws SQLException {
        String sql = """
            SELECT a.id, a.nume_complet, b.nr_limbi_vorbite
            FROM angajat a
            JOIN bibliotecar b ON a.id = b.id
            ORDER BY a.nume_complet
            """;

        List<Bibliotecar> list = new ArrayList<>();
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
    public void update(Bibliotecar bibliotecar) throws SQLException {
        String angajatSql = "UPDATE angajat SET nume_complet = ? WHERE id = ?";
        String bibliotecarSql = "UPDATE bibliotecar SET nr_limbi_vorbite = ? WHERE id = ?";

        try (Connection conn = getConn();
             PreparedStatement psAngajat = conn.prepareStatement(angajatSql);
             PreparedStatement psBibliotecar = conn.prepareStatement(bibliotecarSql)) {
            psAngajat.setString(1, getNumeComplet(bibliotecar));
            psAngajat.setInt(2, bibliotecar.getIdAngajat());
            psAngajat.executeUpdate();

            psBibliotecar.setInt(1, getNrLimbiVorbite(bibliotecar));
            psBibliotecar.setInt(2, bibliotecar.getIdAngajat());
            psBibliotecar.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String deleteBibliotecar = "DELETE FROM bibliotecar WHERE id = ?";
        String deleteAngajat = "DELETE FROM angajat WHERE id = ?";

        try (Connection conn = getConn();
             PreparedStatement psBibliotecar = conn.prepareStatement(deleteBibliotecar);
             PreparedStatement psAngajat = conn.prepareStatement(deleteAngajat)) {
            psBibliotecar.setInt(1, id);
            psBibliotecar.executeUpdate();

            psAngajat.setInt(1, id);
            psAngajat.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}

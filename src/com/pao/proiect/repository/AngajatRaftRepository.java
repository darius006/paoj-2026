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

import com.pao.proiect.model.PersonalRaft;
import com.pao.proiect.model.Sectiune;
import com.pao.proiect.util.DatabaseConnection;

public class AngajatRaftRepository implements Repository<PersonalRaft, Integer> {

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private String getNumeComplet(PersonalRaft personalRaft) {
        try {
            Field field = personalRaft.getClass().getSuperclass().getDeclaredField("numeComplet");
            field.setAccessible(true);
            return (String) field.get(personalRaft);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to read PersonalRaft name", e);
        }
    }

    private Sectiune getSectiune(PersonalRaft personalRaft) {
        try {
            Field field = PersonalRaft.class.getDeclaredField("sectiune");
            field.setAccessible(true);
            return (Sectiune) field.get(personalRaft);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to read PersonalRaft sectiune", e);
        }
    }

    private PersonalRaft mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nume = rs.getString("nume_complet");
        String numeSectiune = rs.getString("nume_sectiune");
        int ratingVarsta = rs.getInt("rating_varsta");
        Sectiune sectiune = new Sectiune(numeSectiune, ratingVarsta);
        return new PersonalRaft(id, nume, sectiune);
    }

    @Override
    public void save(PersonalRaft personalRaft) throws SQLException {
        String angajatSql = "INSERT INTO angajat (id, nume_complet) VALUES (?, ?)";
        String raftSql = "INSERT INTO personal_raft (id, nume_sectiune, rating_varsta) VALUES (?, ?, ?)";

        try (Connection conn = getConn();
             PreparedStatement psAngajat = conn.prepareStatement(angajatSql);
             PreparedStatement psRaft = conn.prepareStatement(raftSql)) {
            psAngajat.setInt(1, personalRaft.getIdAngajat());
            psAngajat.setString(2, getNumeComplet(personalRaft));
            psAngajat.executeUpdate();

            Sectiune sectiune = getSectiune(personalRaft);
            psRaft.setInt(1, personalRaft.getIdAngajat());
            psRaft.setString(2, sectiune.getNume());
            psRaft.setInt(3, sectiune.getRatingVarsta());
            psRaft.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<PersonalRaft> findById(Integer id) throws SQLException {
        String sql = """
            SELECT a.id, a.nume_complet, p.nume_sectiune, p.rating_varsta
            FROM angajat a
            JOIN personal_raft p ON a.id = p.id
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
    public List<PersonalRaft> findAll() throws SQLException {
        String sql = """
            SELECT a.id, a.nume_complet, p.nume_sectiune, p.rating_varsta
            FROM angajat a
            JOIN personal_raft p ON a.id = p.id
            ORDER BY a.nume_complet
            """;

        List<PersonalRaft> list = new ArrayList<>();
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
    public void update(PersonalRaft personalRaft) throws SQLException {
        String angajatSql = "UPDATE angajat SET nume_complet = ? WHERE id = ?";
        String raftSql = "UPDATE personal_raft SET nume_sectiune = ?, rating_varsta = ? WHERE id = ?";

        try (Connection conn = getConn();
             PreparedStatement psAngajat = conn.prepareStatement(angajatSql);
             PreparedStatement psRaft = conn.prepareStatement(raftSql)) {
            psAngajat.setString(1, getNumeComplet(personalRaft));
            psAngajat.setInt(2, personalRaft.getIdAngajat());
            psAngajat.executeUpdate();

            Sectiune sectiune = getSectiune(personalRaft);
            psRaft.setString(1, sectiune.getNume());
            psRaft.setInt(2, sectiune.getRatingVarsta());
            psRaft.setInt(3, personalRaft.getIdAngajat());
            psRaft.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String deleteRaft = "DELETE FROM personal_raft WHERE id = ?";
        String deleteAngajat = "DELETE FROM angajat WHERE id = ?";

        try (Connection conn = getConn();
             PreparedStatement psRaft = conn.prepareStatement(deleteRaft);
             PreparedStatement psAngajat = conn.prepareStatement(deleteAngajat)) {
            psRaft.setInt(1, id);
            psRaft.executeUpdate();

            psAngajat.setInt(1, id);
            psAngajat.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}

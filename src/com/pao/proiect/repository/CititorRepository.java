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

import com.pao.proiect.model.Cititor;
import com.pao.proiect.util.DatabaseConnection;

public class CititorRepository implements Repository<Cititor, String> {

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Cititor mapRow(ResultSet rs) throws SQLException {
        String email = rs.getString("email");
        String numeComplet = rs.getString("nume_complet");
        return new Cititor(email, numeComplet);
    }

    private String getNumeComplet(Cititor cititor) {
        try {
            Field field = Cititor.class.getDeclaredField("numeComplet");
            field.setAccessible(true);
            return (String) field.get(cititor);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to read Cititor name", e);
        }
    }

    @Override
    public void save(Cititor cititor) throws SQLException {
        String sql = """
            INSERT INTO cititor (email, nume_complet)
            VALUES (?, ?)
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, cititor.getEmail());
            ps.setString(2, getNumeComplet(cititor));
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Cititor> findById(String email) throws SQLException {
        String sql = """
            SELECT email, nume_complet
            FROM cititor
            WHERE email = ?
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, email);
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
    public List<Cititor> findAll() throws SQLException {
        String sql = """
            SELECT email, nume_complet
            FROM cititor
            ORDER BY nume_complet
            """;

        List<Cititor> list = new ArrayList<>();
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
    public void update(Cititor cititor) throws SQLException {
        String sql = """
            UPDATE cititor
            SET nume_complet = ?
            WHERE email = ?
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, getNumeComplet(cititor));
            ps.setString(2, cititor.getEmail());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(String email) throws SQLException {
        String sql = "DELETE FROM cititor WHERE email = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}

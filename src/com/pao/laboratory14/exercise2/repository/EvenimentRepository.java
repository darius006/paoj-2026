package com.pao.laboratory14.exercise2.repository;

import com.pao.laboratory14.exercise2.model.Eveniment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.pao.laboratory14.exercise1.TipBilet;

public class EvenimentRepository implements Repository<Eveniment, Integer> {
    private final Connection connection;

    public EvenimentRepository(Connection connection) {
        this.connection = connection;
    }

    public void initSchema() throws SQLException {
        String drop = "DROP TABLE IF EXISTS evenimente";
        String create = "CREATE TABLE IF NOT EXISTS evenimente (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nume TEXT NOT NULL, " +
                        "data TEXT NOT NULL, " +
                        "capacitate INTEGER, " +
                        "tip TEXT" +
                        ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);
            statement.executeUpdate(create);
        }
    }

    @Override
    public void save(Eveniment entity) throws SQLException {
        String sql = "INSERT INTO evenimente(nume, data, capacitate, tip) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, 1)) {
            ps.setString(1, entity.getNume());
            ps.setString(2, entity.getData());
            ps.setInt(3, entity.getCapacitate());
            ps.setString(4, entity.getTip().name());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setId(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Optional<Eveniment> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM evenimente WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Eveniment> findAll() throws SQLException {
        String sql = "SELECT * FROM evenimente ORDER BY id";
        List<Eveniment> evenimente = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                evenimente.add(mapRow(rs));
            }
        }
        return evenimente;
    }

    @Override
    public void update(Eveniment entity) throws SQLException {
        String sql = "UPDATE evenimente SET nume = ?, data = ?, capacitate = ?, tip = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getNume());
            ps.setString(2, entity.getData());
            ps.setInt(3, entity.getCapacitate());
            ps.setString(4, entity.getTip().name());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        deleteImpl(id);
    }

    public int deleteImpl(int id) throws SQLException {
        String sql = "DELETE FROM evenimente WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM evenimente";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private Eveniment mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nume = rs.getString("nume");
        String data = rs.getString("data");
        int capacitate = rs.getInt("capacitate");
        String tipValue = rs.getString("tip");
        return new Eveniment(id, nume, data, capacitate, TipBilet.valueOf(tipValue));
    }
}

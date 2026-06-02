package com.pao.proiect.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.pao.proiect.model.Imprumut;
import com.pao.proiect.model.ISBN;
import com.pao.proiect.util.DatabaseConnection;

public class ImprumutRepository implements Repository<Imprumut, ImprumutRepository.Key> {

    public static class Key {
        private final String isbn;
        private final String email;

        public Key(String isbn, String email) {
            this.isbn = isbn;
            this.email = email;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Key other = (Key) obj;
            return Objects.equals(isbn, other.isbn) && Objects.equals(email, other.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isbn, email);
        }
    }

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Imprumut mapRow(ResultSet rs) throws SQLException {
        ISBN isbn = new ISBN(rs.getString("isbn"));
        String email = rs.getString("email");
        String dataImprumut = rs.getString("data_imprumut");
        String dataPredare = rs.getString("data_predare");
        String dataLimitaPredare = rs.getString("data_limita_predare");
        return new Imprumut(isbn, email, dataImprumut, dataPredare, dataLimitaPredare);
    }

    @Override
    public void save(Imprumut imprumut) throws SQLException {
        String sql = """
            INSERT INTO imprumut (isbn, email, data_imprumut, data_predare, data_limita_predare)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, imprumut.getIsbn().toString());
            ps.setString(2, imprumut.getEmail());
            ps.setString(3, imprumut.getDataImprumut());
            ps.setString(4, imprumut.getDataPredare());
            ps.setString(5, imprumut.getDataLimitaPredare());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Imprumut> findById(Key key) throws SQLException {
        String sql = """
            SELECT isbn, email, data_imprumut, data_predare, data_limita_predare
            FROM imprumut
            WHERE isbn = ? AND email = ?
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, key.getIsbn());
            ps.setString(2, key.getEmail());
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
    public List<Imprumut> findAll() throws SQLException {
        String sql = """
            SELECT isbn, email, data_imprumut, data_predare, data_limita_predare
            FROM imprumut
            ORDER BY data_imprumut
            """;

        List<Imprumut> list = new ArrayList<>();
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
    public void update(Imprumut imprumut) throws SQLException {
        String sql = """
            UPDATE imprumut
            SET data_predare = ?, data_limita_predare = ?
            WHERE isbn = ? AND email = ?
            """;

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, imprumut.getDataPredare());
            ps.setString(2, imprumut.getDataLimitaPredare());
            ps.setString(3, imprumut.getIsbn().toString());
            ps.setString(4, imprumut.getEmail());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Key key) throws SQLException {
        String sql = "DELETE FROM imprumut WHERE isbn = ? AND email = ?";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, key.getIsbn());
            ps.setString(2, key.getEmail());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}

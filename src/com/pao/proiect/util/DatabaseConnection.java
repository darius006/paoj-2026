package com.pao.proiect.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws IOException, SQLException {
        Properties props = new Properties();
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (is == null) {
                throw new IOException("db.properties nu este in resources");
            }
            props.load(is);
        }
        String url  = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.password");

        this.connection = DriverManager.getConnection(url, user, pass);

        connection.createStatement().execute("PRAGMA foreign_keys = ON");
        ensureSchemaExists();
    }

    private void ensureSchemaExists() throws IOException, SQLException {
        try (var stmt = connection.createStatement();
             var rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='carte'") ) {
            if (!rs.next()) {
                executeSchema();
            }
        }
    }

    private void executeSchema() throws IOException, SQLException {
        Path schemaPath = Paths.get("src", "com", "pao", "proiect", "schema.sql");
        if (!Files.exists(schemaPath)) {
            throw new IOException("schema.sql not found at " + schemaPath.toAbsolutePath());
        }
        String sql = Files.readString(schemaPath);
        try (var stmt = connection.createStatement()) {
            for (String sqlStatement : sql.split(";")) {
                String trimmed = sqlStatement.trim();
                if (!trimmed.isBlank()) {
                    stmt.execute(trimmed);
                }
            }
        }
    }

    public static synchronized DatabaseConnection getInstance()
            throws IOException, SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
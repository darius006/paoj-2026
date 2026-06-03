package com.pao.proiect.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AuditService {
  private static final AuditService instance = new AuditService();
  private static final Path AUDIT_FILE = Paths.get("audit.csv");

  private final Lock lock = new ReentrantLock();

  private AuditService() {}

  public static AuditService getInstance() {
    return instance;
  }

  public void log(String action) {
    lock.lock();
    try (BufferedWriter writer = Files.newBufferedWriter(
        AUDIT_FILE,
        StandardOpenOption.CREATE,
        StandardOpenOption.APPEND)) {
      writer.write(action + "," + LocalDateTime.now().toString());
      writer.newLine();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    } finally {
      lock.unlock();
    }
  }
}

DROP TABLE IF EXISTS imprumut;
DROP TABLE IF EXISTS carte;
DROP TABLE IF EXISTS cititor;
DROP TABLE IF EXISTS bibliotecar;
DROP TABLE IF EXISTS personal_raft;
DROP TABLE IF EXISTS angajat;

CREATE TABLE carte (
  isbn VARCHAR(20) PRIMARY KEY,
  titlu VARCHAR(300) NOT NULL,
  nume_autor VARCHAR(200) NOT NULL,
  nume_sectiune VARCHAR(200) NOT NULL,
  rating_varsta TINYINT UNSIGNED NOT NULL,
  nr_exemplare BIGINT NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE cititor (
  email VARCHAR(200) PRIMARY KEY,
  nume_complet VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE imprumut (
  isbn VARCHAR(20),
  email VARCHAR(200),
  data_imprumut DATE,
  data_predare DATE,
  data_limita_predare DATE,

  PRIMARY KEY (isbn, email),

  FOREIGN KEY (isbn)
    REFERENCES carte(isbn),

  FOREIGN KEY (email)
    REFERENCES cititor(email)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE angajat (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nume_complet VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE bibliotecar (
  id BIGINT PRIMARY KEY,
  nr_limbi_vorbite BIGINT

  FOREIGN KEY (id)
    REFERENCES angajat(id)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE personal_raft (
  id BIGINT PRIMARY KEY,
  nume_sectiune VARCHAR(200)

  FOREIGN KEY (id)
    REFERENCES angajat(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


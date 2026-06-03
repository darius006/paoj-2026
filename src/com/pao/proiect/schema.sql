DROP TABLE IF EXISTS imprumut;
DROP TABLE IF EXISTS carte;
DROP TABLE IF EXISTS cititor;
DROP TABLE IF EXISTS bibliotecar;
DROP TABLE IF EXISTS personal_raft;
DROP TABLE IF EXISTS angajat;

CREATE TABLE carte (
  isbn TEXT PRIMARY KEY,
  titlu TEXT NOT NULL,
  nume_autor TEXT NOT NULL,
  nume_sectiune TEXT NOT NULL,
  rating_varsta INTEGER UNSIGNED NOT NULL,
  nr_exemplare INTEGER NOT NULL 
);

CREATE TABLE cititor (
  email TEXT PRIMARY KEY,
  nume_complet TEXT
);

CREATE TABLE imprumut (
  isbn TEXT,
  email TEXT,
  data_imprumut TEXT,
  data_predare TEXT,
  data_limita_predare TEXT,

  PRIMARY KEY (isbn, email),

  FOREIGN KEY (isbn)
    REFERENCES carte(isbn),

  FOREIGN KEY (email)
    REFERENCES cititor(email)

);

CREATE TABLE angajat (
  id INTEGER  PRIMARY KEY AUTOINCREMENT,
  nume_complet TEXT
);

CREATE TABLE bibliotecar (
  id INTEGER PRIMARY KEY,
  nr_limbi_vorbite INTEGER,

  FOREIGN KEY (id)
    REFERENCES angajat(id)
  
);

CREATE TABLE personal_raft (
  id INTEGER PRIMARY KEY,
  nume_sectiune TEXT,

  FOREIGN KEY (id)
    REFERENCES angajat(id)

);


-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON universidad_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS universidad_db;
USE universidad_db;

CREATE TABLE departamentos (
    id VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(50),
    presupuesto DOUBLE,
    fecha_fundacion DATE,
    es_oficial BOOLEAN
);

CREATE TABLE profesores (
    dni VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(50),
    especialidad ENUM('TITULAR', 'ASOCIADO', 'INVITADO'),
    salario DOUBLE,
    id_departamento VARCHAR(10),
    FOREIGN KEY (id_departamento) REFERENCES departamentos(id)
);

-- Inserts Departamentos
INSERT INTO departamentos VALUES ('DEP-MAT', 'Matemáticas', 50000.0, '1990-01-01', true);
INSERT INTO departamentos VALUES ('DEP-HIS', 'Historia', 30000.0, '1985-05-12', true);
INSERT INTO departamentos VALUES ('DEP-INF', 'Informática', 80000.0, '2005-10-20', true);
INSERT INTO departamentos VALUES ('DEP-BIO', 'Biología', 45000.0, '2010-03-15', false);
INSERT INTO departamentos VALUES ('DEP-FIS', 'Física', 60000.0, '1995-07-08', true);

-- Inserts Profesores
INSERT INTO profesores VALUES ('111A', 'Isaac Newton', 'TITULAR', 3500.0, 'DEP-FIS');
INSERT INTO profesores VALUES ('222B', 'Alan Turing', 'TITULAR', 4000.0, 'DEP-INF');
INSERT INTO profesores VALUES ('333C', 'Ada Lovelace', 'INVITADO', 4200.0, 'DEP-INF');
INSERT INTO profesores VALUES ('444D', 'Marie Curie', 'ASOCIADO', 3800.0, 'DEP-FIS');
INSERT INTO profesores VALUES ('555E', 'Pitágoras', 'TITULAR', 3000.0, 'DEP-MAT');
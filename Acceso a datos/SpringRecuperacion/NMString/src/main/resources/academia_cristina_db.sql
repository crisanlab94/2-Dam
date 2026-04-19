-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS academia_cristina_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON academia_cristina_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS academia_cristina_db;
CREATE DATABASE academia_cristina_db;
USE academia_cristina_db;

CREATE TABLE curso (
    codigo_curso VARCHAR(20) PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    nivel ENUM('BASICO', 'INTERMEDIO', 'AVANZADO'),
    fecha_inicio DATE
);

CREATE TABLE estudiante (
    dni VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    becado BOOLEAN DEFAULT FALSE
);

CREATE TABLE estudiante_curso (
    dni_estudiante VARCHAR(10),
    codigo_curso VARCHAR(20),
    PRIMARY KEY (dni_estudiante, codigo_curso),
    CONSTRAINT fk_ec_estudiante FOREIGN KEY (dni_estudiante) REFERENCES estudiante(dni),
    CONSTRAINT fk_ec_curso FOREIGN KEY (codigo_curso) REFERENCES curso(codigo_curso)
);

INSERT INTO curso VALUES ('JAVA-01', 'Master Java', 'AVANZADO', '2026-05-01');
INSERT INTO estudiante VALUES ('12345678Z', 'Cristina Pro', TRUE);
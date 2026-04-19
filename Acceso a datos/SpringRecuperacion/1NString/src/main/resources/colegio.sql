-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS colegio_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON colegio_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS colegio_db;
CREATE DATABASE colegio_db;
USE colegio_db;

CREATE TABLE colegio (
    codigo_centro VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    publico BOOLEAN DEFAULT TRUE,
    fecha_fundacion DATE
);

CREATE TABLE estudiante (
    dni VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    genero ENUM('MASCULINO', 'FEMENINO', 'OTRO'),
    fecha_ingreso DATE,
    codigo_colegio VARCHAR(20),
    CONSTRAINT fk_estudiante_colegio FOREIGN KEY (codigo_colegio) 
        REFERENCES colegio(codigo_centro) ON DELETE CASCADE
);

-- INSERTS DE PRUEBA
INSERT INTO colegio VALUES ('COL-001', 'IES Cervantes', TRUE, '1985-09-01');
INSERT INTO colegio VALUES ('COL-002', 'Colegio Británico', FALSE, '2005-03-15');

INSERT INTO estudiante VALUES ('11111111A', 'Ana García', TRUE, 'FEMENINO', '2023-09-10', 'COL-001');
INSERT INTO estudiante VALUES ('22222222B', 'Pepe López', TRUE, 'MASCULINO', '2024-01-08', 'COL-001');
INSERT INTO estudiante VALUES ('33333333C', 'Alex Smith', FALSE, 'OTRO', '2022-11-20', 'COL-002');
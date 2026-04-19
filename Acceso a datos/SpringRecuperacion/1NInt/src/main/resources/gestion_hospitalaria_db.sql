-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS gestion_hospitalaria_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON gestion_hospitalaria_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS gestion_hospitalaria_db;
CREATE DATABASE gestion_hospitalaria_db;
USE gestion_hospitalaria_db;

CREATE TABLE hospital (
    id INT PRIMARY KEY, -- ID Manual
    nombre VARCHAR(100) NOT NULL,
    publico BOOLEAN DEFAULT TRUE,
    fecha_apertura DATE
);

CREATE TABLE medico (
    id INT PRIMARY KEY, -- ID Manual
    nombre VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    especialidad ENUM('CARDIOLOGIA', 'PEDIATRIA', 'DERMATOLOGIA', 'PSIQUIATRIA'),
    fecha_contratacion DATE,
    id_hospital INT,
    CONSTRAINT fk_medico_hospital FOREIGN KEY (id_hospital) REFERENCES hospital(id)
);

-- INSERTS DE PRUEBA
INSERT INTO hospital VALUES (10, 'Hospital Clínico', TRUE, '1990-05-15');
INSERT INTO hospital VALUES (20, 'Clínica Quirón', FALSE, '2010-10-20');

INSERT INTO medico VALUES (501, 'Dr. House', TRUE, 'DERMATOLOGIA', '2023-01-01', 10);
INSERT INTO medico VALUES (502, 'Dra. Grey', TRUE, 'CARDIOLOGIA', '2024-02-15', 10);
INSERT INTO medico VALUES (503, 'Dr. Shaun', FALSE, 'PEDIATRIA', '2022-11-10', 20);
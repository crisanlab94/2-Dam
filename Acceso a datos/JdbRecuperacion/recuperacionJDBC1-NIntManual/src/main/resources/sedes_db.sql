-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON sedes_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS sedes_db;
USE sedes_db;

CREATE TABLE sedes (
    id INT PRIMARY KEY, -- Manual
    nombre VARCHAR(100),
    fecha_apertura DATE, -- LocalDate
    es_internacional BOOLEAN -- Boolean
);

CREATE TABLE salas (
    id INT PRIMARY KEY, -- Manual
    nombre VARCHAR(50),
    tipo_ambiente ENUM('OFICINA', 'REUNION', 'LABORATORIO'), -- Enum
    capacidad INT,
    id_sede INT,
    FOREIGN KEY (id_sede) REFERENCES sedes(id)
);

INSERT INTO sedes VALUES (10, 'Sede Central', '2010-01-01', true);
INSERT INTO sedes VALUES (20, 'Sede Norte', '2015-06-15', false);
INSERT INTO sedes VALUES (30, 'Sede Sur', '2018-09-20', false);
INSERT INTO sedes VALUES (40, 'Sede Este', '2020-03-10', true);
INSERT INTO sedes VALUES (50, 'Sede Oeste', '2022-11-05', false);

INSERT INTO salas VALUES (101, 'Sala de Juntas', 'REUNION', 20, 10);
INSERT INTO salas VALUES (102, 'Despacho Dirección', 'OFICINA', 5, 10);
INSERT INTO salas VALUES (201, 'Laboratorio I+D', 'LABORATORIO', 15, 20);
INSERT INTO salas VALUES (301, 'Sala Creativa', 'REUNION', 30, 30);
INSERT INTO salas VALUES (401, 'Zona Networking', 'REUNION', 50, 40);
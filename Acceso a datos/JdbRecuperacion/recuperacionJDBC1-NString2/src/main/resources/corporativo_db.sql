-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON corporativo_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS corporativo_db;
USE corporativo_db;

CREATE TABLE empresas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) UNIQUE,
    sector ENUM('TECNOLOGIA', 'SALUD', 'FINANZAS'), -- Enum
    fecha_creacion DATE, -- LocalDate
    es_multinacional BOOLEAN -- Boolean
);

CREATE TABLE empleados (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    salario DOUBLE,
    es_remoto BOOLEAN,
    id_empresa INT,
    FOREIGN KEY (id_empresa) REFERENCES empresas(id)
);

-- 5 Empresas
INSERT INTO empresas (nombre, sector, fecha_creacion, es_multinacional) VALUES 
('TechNova', 'TECNOLOGIA', '2010-05-20', true),
('HealthSafe', 'SALUD', '2015-11-10', false),
('GlobalBank', 'FINANZAS', '2000-01-01', true),
('BioGen', 'SALUD', '2020-03-15', false),
('FinTechX', 'TECNOLOGIA', '2022-08-12', true);

-- 5 Empleados
INSERT INTO empleados (nombre, salario, es_remoto, id_empresa) VALUES 
('Alice Johnson', 5000.0, true, 1),
('Bob Smith', 4500.0, false, 1),
('Charlie Davis', 6000.0, true, 3),
('Diana Prince', 3800.0, false, 2),
('Edward Norton', 4200.0, true, 5);
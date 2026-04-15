-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON gestionFlota.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS gestionFlota;
CREATE DATABASE gestionFlota;
USE gestionFlota;

-- Tabla Padre (1)
CREATE TABLE sandovalcristinaNave (
    id_nave INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    modelo VARCHAR(50)
);

-- Tabla Hija (N)
CREATE TABLE sandovalcristinaTripulante (
    id_tripulante INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    rango VARCHAR(50), -- 'Comandante', 'Piloto', 'Técnico'
    activo BOOLEAN DEFAULT TRUE,
    id_nave INT,
    FOREIGN KEY (id_nave) REFERENCES sandovalcristinaNave(id_nave) ON DELETE CASCADE
);

-- INSERTS DE PRUEBA
INSERT INTO sandovalcristinaNave VALUES (1, 'Halcón Milenario', 'YT-1300'), (2, 'X-Wing Red 5', 'T-65B');

INSERT INTO sandovalcristinaTripulante (nombre, rango, activo, id_nave) VALUES 
('Han Solo', 'Comandante', TRUE, 1),
('Chewbacca', 'Piloto', TRUE, 1),
('Luke Skywalker', 'Comandante', TRUE, 2),
('R2-D2', 'Técnico', TRUE, 2),
('Wedge Antilles', 'Piloto', FALSE, 2);
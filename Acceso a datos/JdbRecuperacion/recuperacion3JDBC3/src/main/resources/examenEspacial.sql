-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON examenEspacial.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS examenEspacial;
USE examenEspacial;

-- Tabla 1: Astronautas (La "Padre")
CREATE TABLE IF NOT EXISTS sandovalcristinaAstronauta (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rango ENUM('CADETE', 'PILOTO', 'COMANDANTE') DEFAULT 'CADETE',
    horas_vuelo INT DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla 2: Misiones (La "Hija")
CREATE TABLE IF NOT EXISTS sandovalcristinaMision (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_astronauta INT,
    nombre_nave VARCHAR(50) NOT NULL,
    fecha_lanzamiento DATE NOT NULL,          -- Solo Fecha (LocalDate)
    ultima_conexion DATETIME,                 -- Fecha y Hora (LocalDateTime)
    combustible_extra BOOLEAN DEFAULT FALSE,  -- Boolean
    estado ENUM('EXITO', 'FALLO', 'EN_CURSO') NOT NULL,
    CONSTRAINT fk_astronauta FOREIGN KEY (id_astronauta) 
        REFERENCES sandovalcristinaAstronauta(id)
);

-- 1. Insertar Astronautas (La base para las misiones)
-- El ID es manual según tu esquema
INSERT INTO sandovalcristinaAstronauta (id, nombre, rango, horas_vuelo, activo) VALUES
(1, 'Neil Armstrong', 'COMANDANTE', 500, TRUE),
(2, 'Buzz Aldrin', 'PILOTO', 450, TRUE),
(3, 'Michael Collins', 'PILOTO', 400, TRUE),
(4, 'Sally Ride', 'CADETE', 120, TRUE),
(5, 'Valentina Tereshkova', 'COMANDANTE', 600, FALSE); -- Un astronauta retirado

-- 2. Insertar Misiones
-- El ID es AUTO_INCREMENT, así que no lo incluimos
INSERT INTO sandovalcristinaMision (id_astronauta, nombre_nave, fecha_lanzamiento, ultima_conexion, combustible_extra, estado) VALUES
(1, 'Apollo 11', '1969-07-16', '1969-07-24 18:50:00', TRUE, 'EXITO'),
(2, 'Gemini 12', '1966-11-11', '1966-11-15 14:21:04', FALSE, 'EXITO'),
(4, 'Challenger STS-7', '1983-06-18', '1983-06-24 07:40:00', TRUE, 'EXITO'),
(1, 'Artemis I', '2026-04-01', '2026-04-10 10:00:00', FALSE, 'EN_CURSO'),
(3, 'Skylab 3', '1973-07-28', '1973-09-25 22:10:00', FALSE, 'FALLO');

-- Ver todos los astronautas y sus misiones (JOIN)
SELECT a.nombre, a.rango, m.nombre_nave, m.estado, m.fecha_lanzamiento
FROM sandovalcristinaAstronauta a
LEFT JOIN sandovalcristinaMision m ON a.id = m.id_astronauta;

-- Ver cuántas misiones tiene cada astronauta (Útil para la validación de "máximo 3")
SELECT a.nombre, COUNT(m.id) as total_misiones
FROM sandovalcristinaAstronauta a
LEFT JOIN sandovalcristinaMision m ON a.id = m.id_astronauta
GROUP BY a.id;
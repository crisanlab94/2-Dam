-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON sandovalcristinaAjedrez.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

-- Creación de la base de datos solicitada [cite: 100]
CREATE DATABASE IF NOT EXISTS jdbcMySqlAcceso;
USE jdbcMySqlAcceso;

-- IMPORTANTE: Sustituye 'ApellidoNombre' por tus datos reales [cite: 67]
CREATE TABLE IF NOT EXISTS sandovalcristinaJugador (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    elo_puntos INT DEFAULT 1200 -- Puntuación inicial estándar en ajedrez
);

CREATE TABLE IF NOT EXISTS sandovalcristinaPartida (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_blancas INT, -- Foreign Key hacia Jugador 
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    resultado ENUM('GANA_BLANCAS', 'GANA_NEGRAS', 'TABLAS') NOT NULL,
    CONSTRAINT fk_jugador_blancas FOREIGN KEY (id_blancas) 
        REFERENCES sandovalcristinaJugador(id)
);



-- 1. Insertar los 4 Jugadores requeridos 
INSERT INTO sandovalcristinaJugador (id, nombre, email, elo_puntos) VALUES 
(1, 'Magnus Carlsen', 'magnus@chess.com', 2850),
(2, 'Hikaru Nakamura', 'hikaru@chess.com', 2800),
(3, 'Fabiano Caruana', 'fabiano@chess.com', 2780),
(4, 'Ian Nepomniachtchi', 'ian@chess.com', 2770);

-- 2. Insertar 3 Partidas iniciales (Máximo permitido es 5 antes de error) 
-- Nota: id_blancas debe existir en la tabla Jugador (Foreign Key) [cite: 22]
INSERT INTO sandovalcristinaPartida (id_blancas, fecha, resultado) VALUES 
(1, '2026-04-10 10:00:00', 'GANA_BLANCAS'),
(2, '2026-04-10 11:30:00', 'TABLAS'),
(3, '2026-04-10 12:45:00', 'GANA_NEGRAS');
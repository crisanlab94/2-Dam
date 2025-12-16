CREATE DATABASE jdbcMySqlAcceso;
USE jdbcMySqlAcceso;

-- 1. LIMPIEZA: Borramos tablas antiguas para evitar errores
DROP TABLE IF EXISTS partidas;
DROP TABLE IF EXISTS jugadores;
DROP TABLE IF EXISTS SandovalCristinaPartida;
DROP TABLE IF EXISTS SandovalCristinaJugador;

-- 2. CREACIÓN: Tablas nuevas con tu Apellido y Nombre

-- Tabla JUGADOR
CREATE TABLE SandovalCristinaJugador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    nick VARCHAR(50), 
    puntosTotales INT DEFAULT 0
);

-- Tabla PARTIDA
CREATE TABLE SandovalCristinaPartida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    narrador_id INT,
    torneo_id INT,
    fecha DATE NOT NULL,
    resultado ENUM('TODOS', 'NADIE', 'ALGUNOS', 'POCOS') NOT NULL,
    FOREIGN KEY (narrador_id) REFERENCES SandovalCristinaJugador(id)
);
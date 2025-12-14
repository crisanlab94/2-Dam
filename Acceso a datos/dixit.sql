CREATE DATABASE jdbcMySqlAcceso;

CREATE TABLE jdbcMySqlAcceso.jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100)  NOT NULL,
    puntosTotales INT DEFAULT 0
);

CREATE TABLE jdbcMySqlAcceso.partidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    torneo_id INT,
    narrador_id INT,
    fecha DATE NOT NULL,
    resultado ENUM('TODOS', 'NADIE', 'ALGUNOS') NOT NULL,
    FOREIGN KEY (narrador_id) REFERENCES jugadores(id)
);

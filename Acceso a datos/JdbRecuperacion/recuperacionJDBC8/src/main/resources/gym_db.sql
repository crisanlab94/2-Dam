-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON gym_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS gym_db;
USE gym_db;

-- Tabla Socios (Padre)
CREATE TABLE SandovalCristinaSocio (
    id_socio INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    tipo_cuota ENUM('BASIC', 'PREMIUM', 'VIP'),
    esta_activo BOOLEAN DEFAULT TRUE
);

-- Tabla Entrenamientos (Hijo)
CREATE TABLE SandovalCristinaEntrenamiento (
    id_entreno INT PRIMARY KEY AUTO_INCREMENT,
    id_socio INT,
    fecha DATE,
    tipo_actividad ENUM('CARDIO', 'FUERZA', 'YOGA'),
    duracion_min INT,
    CONSTRAINT fk_socio_entreno FOREIGN KEY (id_socio) 
        REFERENCES SandovalCristinaSocio(id_socio) ON DELETE CASCADE
);

-- Inserts
INSERT INTO SandovalCristinaSocio VALUES (1, 'Cris Sandoval', 'cris@gym.com', 'VIP', true);
INSERT INTO SandovalCristinaSocio VALUES (2, 'Pepe Perez', 'pepe@gym.com', 'BASIC', true);

INSERT INTO SandovalCristinaEntrenamiento (id_socio, fecha, tipo_actividad, duracion_min) 
VALUES (1, '2024-05-10', 'FUERZA', 90), (1, '2024-05-12', 'CARDIO', 45), (2, '2024-05-11', 'YOGA', 60);
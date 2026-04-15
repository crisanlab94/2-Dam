-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON agenciaSeguridad.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
-- Reseteo para volver a probar
-- drop database agenciaSeguridad; 
CREATE DATABASE IF NOT EXISTS agenciaSeguridad;
USE agenciaSeguridad;

CREATE TABLE IF NOT EXISTS sandovalcristinaAstronauta (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS sandovalcristinaSeguridad (
    id_astronauta INT PRIMARY KEY, -- Clave primaria y foránea a la vez (1:1)
    pin_acceso INT NOT NULL,
    nivel_acceso VARCHAR(20),
    FOREIGN KEY (id_astronauta) REFERENCES sandovalcristinaAstronauta(id)
);

-- Inserts de prueba
INSERT INTO sandovalcristinaAstronauta (id, nombre) VALUES (1, 'Neil Armstrong'), (2, 'Buzz Aldrin');
INSERT INTO sandovalcristinaSeguridad (id_astronauta, pin_acceso, nivel_acceso) VALUES (1, 1234, 'TOP_SECRET'), (2, 5678, 'RESTRINGIDO');
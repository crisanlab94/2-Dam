
CREATE DATABASE IF NOT EXISTS clinicaCristina;

CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

GRANT ALL PRIVILEGES ON clinicaCristina.* TO 'usuario2'@'%';
FLUSH PRIVILEGES;
USE clinicaCristina;

CREATE TABLE clinicaCristina.paciente (
    id_paciente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    dni VARCHAR(10) UNIQUE NOT NULL
);
INSERT INTO cpaciente (nombre, dni) VALUES 
('Juan Pérez', '12345678A'),
('María López', '23456789B'),
('Carlos Sánchez', '34567890C'),
('Ana González', '45678901D'),
('Pedro Martínez', '56789012E');
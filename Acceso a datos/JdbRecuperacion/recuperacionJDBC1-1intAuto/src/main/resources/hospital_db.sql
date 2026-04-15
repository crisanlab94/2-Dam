-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON hospital_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

CREATE TABLE medicos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    especialidad ENUM('CARDIOLOGIA', 'CIRUGIA', 'PEDIATRIA'),
    email VARCHAR(100) UNIQUE,
    fecha_alta DATE,
    esta_activo BOOLEAN
);

CREATE TABLE consultorios (
    medico_id INT PRIMARY KEY,
    planta INT,
    coste_mantenimiento DOUBLE,
    ultima_inspeccion DATETIME,
    FOREIGN KEY (medico_id) REFERENCES medicos(id)
);

INSERT INTO medicos (nombre, especialidad, email, fecha_alta, esta_activo) VALUES 
('Dr. House', 'CARDIOLOGIA', 'house@hospital.com', '2024-01-10', true),
('Dra. Grey', 'CIRUGIA', 'grey@hospital.com', '2024-02-15', true),
('Dr. Shaun', 'PEDIATRIA', 'shaun@hospital.com', '2024-03-05', true),
('Dra. Cuddy', 'CARDIOLOGIA', 'cuddy@hospital.com', '2024-03-20', false),
('Dr. Wilson', 'CIRUGIA', 'wilson@hospital.com', '2024-04-12', true);

INSERT INTO consultorios VALUES 
(1, 3, 550.50, '2024-04-01 10:00:00'), 
(2, 1, 320.00, '2024-03-15 09:30:00'), 
(3, 2, 280.75, '2024-03-20 12:00:00'), 
(4, 3, 490.00, '2024-02-10 08:45:00'), 
(5, 4, 150.00, '2024-04-10 16:20:00');
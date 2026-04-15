-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON smarthome_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS smarthome_db;
USE smarthome_db;

CREATE TABLE dispositivos (
    id VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(50),
    tipo ENUM('SENSOR', 'CAMARA', 'LUZ'),
    fecha_instalacion DATE,
    activo BOOLEAN
);

CREATE TABLE detalles (
    id_dispositivo VARCHAR(10) PRIMARY KEY,
    consumo_doble DOUBLE,
    version_firmware VARCHAR(10),
    FOREIGN KEY (id_dispositivo) REFERENCES dispositivos(id)
);

INSERT INTO dispositivos VALUES 
('D01', 'Termostato', 'SENSOR', '2024-01-01', true),
('D02', 'Luz Salon', 'LUZ', '2024-02-15', false),
('D03', 'Camara Ext', 'CAMARA', '2024-03-10', true),
('D04', 'Sensor Hum', 'SENSOR', '2024-03-12', true),
('D05', 'Luz Cocina', 'LUZ', '2024-04-01', true);

INSERT INTO detalles VALUES 
('D01', 1.5, 'v1.0'), ('D02', 10.2, 'v2.1'), ('D03', 15.0, 'v3.0'), 
('D04', 0.5, 'v1.1'), ('D05', 8.5, 'v2.2');
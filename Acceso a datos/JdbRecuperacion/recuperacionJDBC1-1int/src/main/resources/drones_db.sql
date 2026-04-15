-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON drones_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS drones_db;
USE drones_db;

CREATE TABLE drones (
    id INT PRIMARY KEY, -- Manual
    modelo VARCHAR(50),
    categoria ENUM('RECONOCIMIENTO', 'CARGA', 'DEFENSA'),
    ultima_mision DATETIME,
    nivel_bateria INT,
    necesita_reparacion BOOLEAN
);

CREATE TABLE componentes (
    id_drone INT PRIMARY KEY, -- Manual (FK de drones)
    peso_gramos DOUBLE,
    material VARCHAR(30),
    gps_activo BOOLEAN,
    FOREIGN KEY (id_drone) REFERENCES drones(id)
);

INSERT INTO drones VALUES 
(101, 'SkyHunter X1', 'RECONOCIMIENTO', '2024-04-01 10:00:00', 85, false),
(102, 'CargoLifter V2', 'CARGA', '2024-03-15 08:30:00', 40, true),
(103, 'ShadowSentinel', 'DEFENSA', '2024-04-10 22:15:00', 95, false),
(104, 'SwiftEye', 'RECONOCIMIENTO', '2024-04-12 14:00:00', 15, false),
(105, 'TitanCarrier', 'CARGA', '2024-02-20 12:00:00', 60, true);

INSERT INTO componentes VALUES 
(101, 450.5, 'Fibra de Carbono', true),
(102, 2500.0, 'Aluminio Reforzado', false),
(103, 1200.0, 'Titanio', true),
(104, 300.2, 'Plástico ABS', true),
(105, 5000.0, 'Acero Inoxidable', false);
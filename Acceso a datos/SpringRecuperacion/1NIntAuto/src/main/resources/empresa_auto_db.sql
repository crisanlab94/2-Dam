-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS empresa_auto_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON empresa_auto_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS empresa_auto_db;
CREATE DATABASE empresa_auto_db;
USE empresa_auto_db;

CREATE TABLE empresa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    sede_central VARCHAR(100),
    facturacion_millones DOUBLE,
    es_multinacional BOOLEAN DEFAULT FALSE,
    fecha_apertura DATE
);

CREATE TABLE empleado (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    salario DOUBLE,
    activo BOOLEAN DEFAULT TRUE,
    puesto ENUM('JUNIOR', 'SENIOR', 'MANAGER', 'DIRECTOR'),
    fecha_contratacion DATE,
    id_empresa INT,
    CONSTRAINT fk_empleado_empresa FOREIGN KEY (id_empresa) REFERENCES empresa(id)
);

-- INSERTS DE PRUEBA
INSERT INTO empresa (nombre, sede_central, facturacion_millones, es_multinacional, fecha_apertura) 
VALUES ('Tech Nova', 'Madrid', 150.5, TRUE, '2010-05-20');

INSERT INTO empleado (nombre, salario, activo, puesto, fecha_contratacion, id_empresa) 
VALUES ('Cristina Dev', 3500.0, TRUE, 'MANAGER', '2023-01-15', 1);
INSERT INTO empleado (nombre, salario, activo, puesto, fecha_contratacion, id_empresa) 
VALUES ('Juan Code', 2200.0, TRUE, 'JUNIOR', '2024-02-10', 1);
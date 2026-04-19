-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS club_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON club_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

-- Borramos la base de datos si existe y la creamos de cero
DROP DATABASE IF EXISTS club_db;
CREATE DATABASE club_db;
USE club_db;

-- Tabla Socio con DNI como String manual
CREATE TABLE socio (
    dni VARCHAR(9) PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    activo BOOLEAN DEFAULT TRUE,
    genero ENUM('MASCULINO', 'FEMENINO', 'OTRO') NOT NULL
);

-- Tabla Carnet con Código de Barras como String manual
CREATE TABLE carnet (
    codigo_barras VARCHAR(20) PRIMARY KEY, 
    fecha_emision DATETIME,
    cuota_mensual DOUBLE,
    dni_socio VARCHAR(9) UNIQUE NOT NULL, -- FK para la relación 1:1
    CONSTRAINT fk_carnet_socio FOREIGN KEY (dni_socio) REFERENCES socio(dni)
);
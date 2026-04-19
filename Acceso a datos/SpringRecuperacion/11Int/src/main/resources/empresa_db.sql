-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS empresa_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON empresa_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS empresa_db;
CREATE DATABASE empresa_db;
USE empresa_db;

-- Tabla Director: ID manual INT
CREATE TABLE director (
    id INT PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL,
    sueldo DOUBLE,
    fecha_nombramiento DATE
);

-- Tabla Departamento: ID manual INT y FK 1:1
CREATE TABLE departamento (
    id INT PRIMARY KEY, 
    nombre_depto VARCHAR(100) NOT NULL,
    presupuesto DOUBLE,
    id_director INT UNIQUE NOT NULL, 
    CONSTRAINT fk_depto_director FOREIGN KEY (id_director) REFERENCES director(id)
);

-- INSERTS: Datos de prueba
INSERT INTO director (id, nombre, sueldo, fecha_nombramiento) VALUES (1, 'Marta Sánchez', 4500.0, '2025-01-15');
INSERT INTO director (id, nombre, sueldo, fecha_nombramiento) VALUES (2, 'Pedro Gómez', 3800.0, '2025-02-20');

INSERT INTO departamento (id, nombre_depto, presupuesto, id_director) VALUES (10, 'Finanzas', 500000.0, 1);
INSERT INTO departamento (id, nombre_depto, presupuesto, id_director) VALUES (20, 'Ventas', 300000.0, 2);
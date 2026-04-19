-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS salud_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON salud_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

-- Seleccionamos la base de datos 
USE salud_db;

CREATE DATABASE IF NOT EXISTS salud_db;
USE salud_db;

CREATE TABLE paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    asegurado BOOLEAN DEFAULT FALSE,
    genero ENUM('MASCULINO', 'FEMENINO', 'OTRO') NOT NULL
);

-- Inserts para que el findById y otros tengan qué buscar
INSERT INTO paciente (nombre, fecha_nacimiento, asegurado, genero) VALUES 
('Cristina Garcia', '1995-05-20', 1, 'FEMENINO'),
('Juan Perez', '1988-10-15', 0, 'MASCULINO');
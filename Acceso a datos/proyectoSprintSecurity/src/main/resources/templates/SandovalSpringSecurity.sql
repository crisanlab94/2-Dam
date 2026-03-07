-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS SandovalSpring;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON SandovalSpring.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

-- Seleccionamos la base de datos 
USE SandovalSpring;

-- Las tablas se generan automáticamente mediante Hibernate al arrancar la aplicación.
-- Una vez arrancada, se puede ejecutar la siguiente línea para tener un usuario administrador:

INSERT INTO usuarios (username, password, role) 
VALUES ('admin', '$2a$12$VSpzTv.teacQL4QtxUDCgOQK8nG9PuxNwiuC79WTMxCdZVuAotAVW', 'ROLE_ADMIN');
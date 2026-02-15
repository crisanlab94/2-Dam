-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS springboot;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON springboot.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

-- Seleccionamos la base de datos 
USE springboot;
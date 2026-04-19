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

<<<<<<< HEAD

INSERT INTO usuarios (username, password, role) 
VALUES ('admin', '$2a$12$VSpzTv.teacQL4QtxUDCgOQK8nG9PuxNwiuC79WTMxCdZVuAotAVW', 'ROLE_ADMIN');
=======
INSERT INTO usuarios (username, password, role) 
VALUES ('admin', '$2a$12$Ct5OjHgKIQNvV8zW3.UA7.Q2OvMfuXTG7hdX3xpsuibqDJnWDQwce', 'ROLE_ADMIN');
>>>>>>> 6768c0b (07-03-2026)

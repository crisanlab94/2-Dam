create database proyectoCristina;
use proyectoCristina;
CREATE USER 'usuario2'@'%' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON proyectoCristina.* TO 'usuario2'@'%';
-- Usamos el plugin moderno (caching_sha2_password) que es el que viene por defecto
ALTER USER 'usuario2'@'%' IDENTIFIED WITH caching_sha2_password BY 'usuario';

-- Aseguramos que los cambios se apliquen
FLUSH PRIVILEGES;
Drop database proyectoCristina;



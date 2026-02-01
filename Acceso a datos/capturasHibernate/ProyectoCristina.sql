create database proyectoCristina;
use proyectoCristina;
ALTER USER 'usuario2'@'%' IDENTIFIED WITH caching_sha2_password BY 'usuario';
GRANT ALL PRIVILEGES ON proyectoCristina.* TO 'usuario2'@'%';
FLUSH PRIVILEGES;




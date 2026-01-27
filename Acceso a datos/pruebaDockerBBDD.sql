SELECT user, host FROM mysql.user;

create database pruebaDocker;
use pruebaDocker;


GRANT ALL PRIVILEGES ON pruebaDocker.* TO 'usuario2'@'%';
FLUSH PRIVILEGES;
CREATE TABLE persona (
    dni VARCHAR(15) PRIMARY KEY,
    nombreApellido VARCHAR(100) NOT NULL,
    edad INT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    fechaNacimiento DATE NOT NULL,
    telefono VARCHAR(15) NULL
);



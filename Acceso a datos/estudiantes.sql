CREATE DATABASE estudiantes;


CREATE TABLE ciudad (
 id_ciudad INT AUTO_INCREMENT PRIMARY KEY,
 direccion VARCHAR(50) NOT NULL,
 codigo_postal INT,
 calle varchar(50),
 numero int
 );

CREATE TABLE estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    ciudad_id int,
    puntuaciones_id int,
    FOREIGN KEY (ciudad_id) REFERENCES ciudad(id_ciudad)
);

CREATE TABLE puntuaciones (
id_puntuaciones INT AUTO_INCREMENT PRIMARY KEY,
puntuacion float,
tipo ENUM('EXAM','QUIZ','HOMEWORK'),
id_estudiante int,
FOREIGN KEY (id_estudiante) REFERENCES estudiante(id)
);

ALTER TABLE estudiante
ADD COLUMN nota_media INT AFTER nombre;

ALTER TABLE estudiante
MODIFY COLUMN nota_media FLOAT;


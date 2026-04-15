-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON academiaEspacial.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

-- Reseteo para volver a probar
-- drop database academiaEspacial; 
CREATE DATABASE IF NOT EXISTS academiaEspacial;
USE academiaEspacial;

CREATE TABLE IF NOT EXISTS sandovalcristinaEstudiante (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nivel ENUM('BASICO', 'INTERMEDIO', 'AVANZADO')
);

CREATE TABLE IF NOT EXISTS sandovalcristinaCurso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    fecha_inicio DATE
);

-- ESTA ES LA CLAVE: Tabla intermedia para la relación N:M
CREATE TABLE IF NOT EXISTS sandovalcristinaMatricula (
    id_curso INT,
    id_estudiante INT,
    fecha_inscripcion DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_curso, id_estudiante),
    FOREIGN KEY (id_curso) REFERENCES sandovalcristinaCurso(id),
    FOREIGN KEY (id_estudiante) REFERENCES sandovalcristinaEstudiante(id)
);

-- 1. Insertar Estudiantes
-- El ID es manual según tu esquema
INSERT INTO sandovalcristinaEstudiante (id, nombre, nivel) VALUES
(1, 'Luke Skywalker', 'BASICO'),
(2, 'Leia Organa', 'INTERMEDIO'),
(3, 'Han Solo', 'BASICO'),
(4, 'Ahsoka Tano', 'AVANZADO'),
(5, 'Din Djarin', 'INTERMEDIO');

-- 2. Insertar Cursos
-- El ID es AUTO_INCREMENT
INSERT INTO sandovalcristinaCurso (titulo, fecha_inicio) VALUES
('Navegación Hiperespacial', '2026-05-01'),
('Mantenimiento de Droides', '2026-06-15'),
('Estrategia de Combate Estelar', '2026-09-10');

-- 3. Insertar Matrículas (Relación N:M)
-- Aquí vinculamos los IDs de las tablas anteriores
-- Curso 1 (Navegación) tiene a Luke, Leia y Han
INSERT INTO sandovalcristinaMatricula (id_curso, id_estudiante) VALUES (1, 1), (1, 2), (1, 3);

-- Curso 2 (Droides) tiene a Luke y Ahsoka
INSERT INTO sandovalcristinaMatricula (id_curso, id_estudiante) VALUES (2, 1), (2, 4);

-- Curso 3 (Estrategia) tiene a Leia, Ahsoka y Din Djarin
INSERT INTO sandovalcristinaMatricula (id_curso, id_estudiante) VALUES (3, 2), (3, 4), (3, 5);
-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS gestion_proyectos_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON gestion_proyectos_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS gestion_proyectos_db;
CREATE DATABASE gestion_proyectos_db;
USE gestion_proyectos_db;

CREATE TABLE empleado (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    salario DOUBLE
);

CREATE TABLE proyecto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    prioridad_alta BOOLEAN DEFAULT FALSE,
    estado ENUM('PLANIFICACION', 'DESARROLLO', 'FINALIZADO'),
    fecha_entrega DATE
);

-- Tabla Intermedia (Hibernate la gestionará)
CREATE TABLE empleado_proyecto (
    id_empleado INT,
    id_proyecto INT,
    PRIMARY KEY (id_empleado, id_proyecto),
    CONSTRAINT fk_ep_empleado FOREIGN KEY (id_empleado) REFERENCES empleado(id),
    CONSTRAINT fk_ep_proyecto FOREIGN KEY (id_proyecto) REFERENCES proyecto(id)
);

-- INSERTS DE PRUEBA
INSERT INTO empleado (nombre, activo, salario) VALUES ('Cristina Pro', TRUE, 4500.0);
INSERT INTO empleado (nombre, activo, salario) VALUES ('Juan Senior', TRUE, 3800.0);

INSERT INTO proyecto (titulo, prioridad_alta, estado, fecha_entrega) 
VALUES ('Sistema IA', TRUE, 'DESARROLLO', '2026-12-01');
INSERT INTO proyecto (titulo, prioridad_alta, estado, fecha_entrega) 
VALUES ('Web Corporativa', FALSE, 'PLANIFICACION', '2027-03-15');

-- Asignación inicial (Cristina en ambos, Juan solo en el Sistema IA)
INSERT INTO empleado_proyecto VALUES (1, 1), (1, 2), (2, 1);
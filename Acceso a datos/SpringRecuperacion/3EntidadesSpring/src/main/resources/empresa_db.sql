-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS empresa_db;

-- Creamos el usuario con su contraseña 
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario';

-- Le damos todos los permisos sobre la nueva base de datos
GRANT ALL PRIVILEGES ON empresa_db.* TO 'usuario2'@'%';

-- Aplicamos los cambios
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS empresa_db;
CREATE DATABASE empresa_db;
USE empresa_db;

CREATE TABLE departamento (
    id_dept BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE proyecto (
    id_proy BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    presupuesto DOUBLE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE empleado (
    id_emp BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    salario DOUBLE NOT NULL,
    becado BOOLEAN NOT NULL DEFAULT FALSE,
    cargo ENUM('JUNIOR', 'SENIOR', 'MANAGER') NOT NULL,
    fecha_contratacion DATE NOT NULL,
    id_dept BIGINT,
    CONSTRAINT fk_empleado_dept FOREIGN KEY (id_dept) REFERENCES departamento(id_dept)
);

CREATE TABLE empleado_proyecto (
    id_emp BIGINT NOT NULL,
    id_proy BIGINT NOT NULL,
    PRIMARY KEY (id_emp, id_proy),
    CONSTRAINT fk_ep_empleado FOREIGN KEY (id_emp) REFERENCES empleado(id_emp),
    CONSTRAINT fk_ep_proyecto FOREIGN KEY (id_proy) REFERENCES proyecto(id_proy)
);

-- Inserts iniciales
INSERT INTO departamento (nombre) VALUES ('Desarrollo'), ('RRHH');
INSERT INTO proyecto (nombre, presupuesto, activo) VALUES ('App Bancaria', 50000.0, true);
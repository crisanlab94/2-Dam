-- Creación del usuario que pide la rúbrica
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Darle todos los permisos sobre tu base de datos
GRANT ALL PRIVILEGES ON netplis_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS netplis_db;
USE netplis_db;

-- TABLA PADRE: Usuarios
CREATE TABLE SandovalCristinaUsuario (
    id_usuario VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    plan_activo ENUM('MENSUAL', 'ANUAL', 'VIP') NOT NULL,
    puntos_fidelidad INT DEFAULT 0,
    notificaciones_push BOOLEAN DEFAULT TRUE,
    dispositivo_principal VARCHAR(30)
);

-- TABLA HIJA: Reproducciones
CREATE TABLE SandovalCristinaReproduccion (
    id_repro INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario VARCHAR(10),
    fecha DATE NOT NULL,
    tipo_contenido ENUM('PELICULA', 'SERIE', 'DOCUMENTAL') NOT NULL,
    minutos_vistos INT,
    CONSTRAINT fk_usuario_repro FOREIGN KEY (id_usuario) 
        REFERENCES SandovalCristinaUsuario(id_usuario) ON DELETE CASCADE
);

-- INSERTS PARA PRUEBAS
INSERT INTO SandovalCristinaUsuario VALUES ('usr01', 'Ana Lopez', 'ana@mail.com', 'VIP', 120, true, 'android14');
INSERT INTO SandovalCristinaUsuario VALUES ('usr02', 'Berto Ruiz', 'berto@mail.com', 'MENSUAL', 45, false, 'android14');
INSERT INTO SandovalCristinaUsuario VALUES ('usr03', 'Carlos Gil', 'carlos@mail.com', 'ANUAL', 90, true, 'ios17');
INSERT INTO SandovalCristinaUsuario VALUES ('usr04', 'Dora Mar', 'dora@mail.com', 'MENSUAL', 10, false, 'android14');
INSERT INTO SandovalCristinaUsuario VALUES ('usr05', 'Elena Sanz', 'elena@mail.com', 'MENSUAL', 30, false, 'android14');

INSERT INTO SandovalCristinaReproduccion (id_usuario, fecha, tipo_contenido, minutos_vistos) VALUES 
('usr01', '2024-04-01', 'PELICULA', 125),
('usr01', '2024-04-02', 'SERIE', 45),
('usr02', '2024-04-01', 'DOCUMENTAL', 60);
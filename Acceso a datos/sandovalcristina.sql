-- Re-crear base de datos
DROP DATABASE IF EXISTS sandovalcristina;
CREATE DATABASE sandovalcristina;
USE sandovalcristina;

-- Tabla usuarios
CREATE TABLE sandovalcristina.usuarios (
    id VARCHAR(10) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    plan_activo ENUM('FREE','MENSUAL','TRIMESTRAL','ANUAL','VIP') NOT NULL,
    dispositivo VARCHAR(50) NOT NULL
);

-- Tabla preferencias (propagando id de usuario)
CREATE TABLE sandovalcristina.preferencias (
    usuario_id VARCHAR(10) PRIMARY KEY,
    tema_oscuro VARCHAR NOT NULL,
    idioma VARCHAR(5) NOT NULL,
    notificaciones_push VARCHAR NOT NULL,
    limite_datos_moviles VARCHAR NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Inserts usuarios
INSERT INTO usuarios (id, username, email, plan_activo, dispositivo) VALUES
('usr001','User001','user001@fitdroid.com','MENSUAL','android14'),
('usr002','User002','user002@fitdroid.com','MENSUAL','android11'),
('usr003','User003','user003@fitdroid.com','TRIMESTRAL','android12'),
('usr004','User004','user004@fitdroid.com','ANUAL','iphone16'),
('usr005','User005','user005@fitdroid.com','MENSUAL','android14'),
('usr006','User006','user006@fitdroid.com','FREE','android13'),
('usr007','User007','user007@fitdroid.com','MENSUAL','android11'),
('usr008','User008','user008@fitdroid.com','TRIMESTRAL','android11'),
('usr009','User009','user009@fitdroid.com','ANUAL','android12'),
('usr010','User010','user010@fitdroid.com','VIP','android13'),
('usr011','User011','user011@fitdroid.com','FREE','android13'),
('usr012','User012','user012@fitdroid.com','MENSUAL','iphone17'),
('usr013','User013','user013@fitdroid.com','TRIMESTRAL','android12'),
('usr014','User014','user014@fitdroid.com','ANUAL','android11'),
('usr015','User015','user015@fitdroid.com','VIP','android13'),
('usr016','User016','user016@fitdroid.com','FREE','iphone16'),
('usr017','User017','user017@fitdroid.com','MENSUAL','android13'),
('usr018','User018','user018@fitdroid.com','TRIMESTRAL','iphone16'),
('usr019','User019','user019@fitdroid.com','ANUAL','android11'),
('usr020','User020','user020@fitdroid.com','VIP','iphone17'),
('usr021','User021','user021@fitdroid.com','FREE','android11'),
('usr022','User022','user022@fitdroid.com','MENSUAL','iphone17'),
('usr023','User023','user023@fitdroid.com','MENSUAL','android14'),
('usr024','User024','user024@fitdroid.com','ANUAL','android13'),
('usr025','User025','user025@fitdroid.com','VIP','iphone15'),
('usr026','User026','user026@fitdroid.com','FREE','iphone14'),
('usr027','User027','user027@fitdroid.com','MENSUAL','iphone17'),
('usr028','User028','user028@fitdroid.com','TRIMESTRAL','android13'),
('usr029','User029','user029@fitdroid.com','ANUAL','android14'),
('usr030','User030','user030@fitdroid.com','VIP','android11'),
('usr031','User031','user031@fitdroid.com','FREE','iphone15'),
('usr032','User032','user032@fitdroid.com','MENSUAL','android12'),
('usr033','User033','user033@fitdroid.com','TRIMESTRAL','android14'),
('usr034','User034','user034@fitdroid.com','ANUAL','iphone15'),
('usr035','User035','user035@fitdroid.com','VIP','iphone17'),
('usr036','User036','user036@fitdroid.com','FREE','android13'),
('usr037','User037','user037@fitdroid.com','MENSUAL','android13'),
('usr038','User038','user038@fitdroid.com','TRIMESTRAL','iphone14'),
('usr039','User039','user039@fitdroid.com','ANUAL','android14'),
('usr040','User040','user040@fitdroid.com','VIP','android14');

-- Inserts preferencias
INSERT INTO preferencias (usuario_id, tema_oscuro, idioma, notificaciones_push, limite_datos_moviles) VALUES
('usr001',true,'ES',false,false),
('usr002',false,'ES',true,false),
('usr003',true,'ES',false,true),
('usr004',false,'ES',true,false),
('usr005',true,'ES',true,false),
('usr006',false,'ES',true,true),
('usr007',true,'ES',false,false),
('usr008',false,'ES',true,false),
('usr009',true,'ES',false,true),
('usr010',false,'ES',true,false),
('usr011',true,'ES',false,false),
('usr012',false,'ES',true,true),
('usr013',true,'ES',false,false),
('usr014',false,'ES',true,false),
('usr015',true,'ES',false,true),
('usr016',false,'ES',true,false),
('usr017',true,'ES',false,false),
('usr018',false,'ES',true,true),
('usr019',true,'ES',false,false),
('usr020',false,'ES',true,false),
('usr021',true,'ES',false,true),
('usr022',false,'ES',true,false),
('usr023',true,'ES',true,false),
('usr024',false,'ES',true,true),
('usr025',true,'ES',false,false),
('usr026',false,'ES',true,false),
('usr027',true,'ES',false,true),
('usr028',false,'ES',true,false),
('usr029',true,'ES',false,false),
('usr030',false,'ES',true,true),
('usr031',true,'ES',false,false),
('usr032',false,'ES',true,false),
('usr033',true,'ES',true,true),
('usr034',false,'ES',true,false),
('usr035',true,'ES',false,false),
('usr036',false,'ES',true,true),
('usr037',true,'ES',false,false),
('usr038',false,'ES',true,false),
('usr039',true,'ES',false,true),
('usr040',false,'ES',true,false);

SELECT p.*,u.username,u.email,u.plan_activo,u.dispositivo 
				from preferencias p 
				join usuarios u on p.usuario_id = u.id;
                
SELECT u.*,u.username,u.email,u.plan_activo,u.dispositivo 
				from usuarios u 
				join preferencias p on u.id =p.usuario_id 
                
                
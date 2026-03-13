DROP TYPE t_grupoMusical FORCE;
DROP TABLE tabla_gruposMusicales;
DROP TYPE t_emails FORCE; 
DROP TYPE t_concierto FORCE;
DROP TABLE tabla_concierto FORCE;

CREATE OR REPLACE TYPE emails AS VARRAY(5) OF VARCHAR2(30);
/

CREATE OR REPLACE TYPE t_grupoMusical AS OBJECT (
    codigo VARCHAR2(8),
    nombre VARCHAR2(20),
    numeroIntegrantes NUMBER,
    emailsGrupo emails
);
/

CREATE OR REPLACE TYPE BODY t_grupoMusical AS
    CONSTRUCTOR FUNCTION t_grupoMusical(p_codigo VARCHAR2,p_nombre VARCHAR2, p_numeroIntegrantes NUMBER,emailsGrupo emails) RETURN SELF AS RESULT IS
    BEGIN
        SELF.codigo := P_codigo; 
        SELF.nombre := p_nombre;
        SELF.numeroIntegrantes := p_numeroIntegrantes;
        SELF.emailsGrupo := emailsGrupo;
        RETURN; 
    END;
END;
/

CREATE TABLE tabla_gruposMusicales OF t_grupoMusical (
    codigo NOT NULL PRIMARY KEY,
    nombre NOT NULL
);


INSERT INTO tabla_gruposMusicales (codigo, nombre, numeroIntegrantes, emailsGrupo)
VALUES ('GRP001', 'Vetusta Morla', 6, emails('contacto@vetustamorla.com',
'contacto1@vetustamorla.com'));
INSERT INTO tabla_gruposMusicales (codigo, nombre, numeroIntegrantes, emailsGrupo)
VALUES ('GRP002', 'Izal', 5, emails('info@izalband.com'));
INSERT INTO tabla_gruposMusicales (codigo, nombre, numeroIntegrantes, emailsGrupo)
VALUES ('GRP003', 'Love of Lesbian', 5, emails('contacto@loveoflesbian.com',
'manager@loveoflesbian.com'));
COMMIT;

CREATE OR REPLACE TYPE t_concierto AS OBJECT (
    idConcierto VARCHAR2(2),
    fecha DATE,
    descripcion varchar2(100),
    escenario varchar2(30),
    grupo t_grupoMusical
    --MEMBER PROCEDURE set_fecha(nueva_fecha DATE)
);
/

CREATE TABLE tabla_concierto OF t_concierto (
    idConcierto NOT NULL PRIMARY KEY
  
);
/

CREATE OR REPLACE TYPE BODY t_concierto AS
    CONSTRUCTOR FUNCTION t_concierto(p_idConcierto VARCHAR2,p_fecha DATE, p_descripcion VARCHAR2,p_escenario VARCHAR2,grupo t_grupoMusical) RETURN SELF AS RESULT IS
    BEGIN
        SELF.idConcierto := P_idConcierto; 
        SELF.fecha := p_fecha;
        SELF.descripcion := p_descripcion;
        SELF.escenario := escenario;
        SELF.grupo := grupo;
        RETURN; 
    END;
END;
/
INSERT INTO tabla_concierto (idConcierto, fecha, descripcion,escenario, grupo)
VALUES (1, TO_DATE('2024-06-15', 'YYYY-MM-DD'), 'Gran apertura con Vetusta Morla','PRINCIPAL',
t_grupoMusical('GRP001', 'Vetusta Morla', 6, t_emails('contacto@vetustamorla.com',
'contacto1@vetustamorla.com')),
);

INSERT INTO tabla_concierto (idConcierto, fecha, descripcion, grupo, escenario)
VALUES (2, TO_DATE('2024-06-16', 'YYYY-MM-DD'), 'Noche indie con Izal',
t_grupoMusical('GRP002', 'Izal', 5, t_emails('info@izalband.com')),
'ESCENARIO SECUNDARIO');
COMMIT; -- Guardamos los cambios

CREATE OR REPLACE TYPE reserva AS OBJECT (
    idReserva VARCHAR2(2),
    fechaReserva DATE,
    concierto t_concierto
    
);
/

CREATE TABLE tabla_reserva OF reserva (
    idReserva NOT NULL PRIMARY KEY
  
);
/


BEGIN
INSERT INTO reservas (idReserva,fechaReserva, concierto)
VALUES (2,
TO_DATE('2024-05-20', 'YYYY-MM-DD'),
t_concierto(1, TO_DATE('2024-06-15', 'YYYY-MM-DD'), 'Gran apertura con Vetusta
Morla', t_grupoMusical('GRP001', 'Vetusta Morla', 6, t_emails('contacto@vetustamorla.com',
'contacto1@vetustamorla.com')),
'PRINCIPAL')
);
INSERT INTO reservas (idReserva,fechaReserva, concierto)
VALUES (3,
TO_DATE('2024-05-21', 'YYYY-MM-DD'),
t_concierto(2, TO_DATE('2024-06-16', 'YYYY-MM-DD'), 'Noche indie con Izal',
t_grupoMusical('GRP002', 'Izal', 5, t_emails('info@izalband.com')),
'ESCENARIO SECUNDARIO')
);
COMMIT;
END;
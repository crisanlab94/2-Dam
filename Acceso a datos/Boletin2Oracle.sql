DROP TABLE tabla_reparacion;
DROP TYPE t_reparacion FORCE;
DROP TYPE t_cliente FORCE;
DROP TYPE t_vehiculo FORCE;
DROP TYPE telefonos FORCE;
DROP TYPE t_direccion FORCE;
DROP SEQUENCE cliente_sec;
/

CREATE OR REPLACE TYPE t_direccion AS OBJECT (
    ciudad VARCHAR2(50),
    calle VARCHAR2(100),
    numero VARCHAR2(50),
    codigo_postal VARCHAR2(10),
    MEMBER FUNCTION getDireccion RETURN VARCHAR2
);
/

CREATE OR REPLACE TYPE BODY t_direccion AS
    MEMBER FUNCTION getDireccion RETURN VARCHAR2 IS
    BEGIN
        -- Retorna: ciudad, calle, numero (Codigo Postal)
        RETURN self.ciudad || ', ' || self.calle || ', ' || self.numero || ' (' || self.codigo_postal || ')';
    END;
END;
/

CREATE SEQUENCE cliente_sec
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
MINVALUE 1
NOCYCLE;
/
  -- Almacenar 3 numeros de teelfono
CREATE OR REPLACE TYPE telefonos AS VARRAY(3) OF VARCHAR2(14);
/

CREATE OR REPLACE TYPE t_cliente AS OBJECT (
    cod NUMBER,
    dni VARCHAR2(9),
    nombre VARCHAR2(100),
    domicilio t_direccion,
    tlfs telefonos,
    MEMBER FUNCTION getTelefonos RETURN VARCHAR2,
    CONSTRUCTOR FUNCTION t_cliente(cod NUMBER, dni VARCHAR2, nombre VARCHAR2, domicilio t_direccion, tlfs telefonos) RETURN SELF AS RESULT,
    CONSTRUCTOR FUNCTION t_cliente(dni VARCHAR2, nombre VARCHAR2, domicilio t_direccion, tlfs telefonos) RETURN SELF AS RESULT
);
/

CREATE OR REPLACE TYPE BODY t_cliente AS
    MEMBER FUNCTION getTelefonos RETURN VARCHAR2 IS
        auxVariable VARCHAR2(100) := '[';
    BEGIN
        IF self.tlfs IS NOT NULL THEN
            FOR i IN 1..self.tlfs.COUNT LOOP
                auxVariable := auxVariable || self.tlfs(i);
                IF i < self.tlfs.COUNT THEN
                    auxVariable := auxVariable || ',';
                END IF;
            END LOOP;
        END IF;
        auxVariable := auxVariable || ']';
        RETURN auxVariable;
    END;

    -- Constructor con todos los atributos
    CONSTRUCTOR FUNCTION t_cliente(cod NUMBER, dni VARCHAR2, nombre VARCHAR2, domicilio t_direccion, tlfs telefonos) RETURN SELF AS RESULT IS
    BEGIN
        self.cod := cod;
        self.dni := dni;
        self.nombre := nombre;
        self.domicilio := domicilio;
        self.tlfs := tlfs;
        RETURN;
    END;

    -- Constructor que usa la secuencia para el código
    CONSTRUCTOR FUNCTION t_cliente(dni VARCHAR2, nombre VARCHAR2, domicilio t_direccion, tlfs telefonos) RETURN SELF AS RESULT IS
    BEGIN
        self.cod := cliente_sec.NEXTVAL; -- Usa la secuencia 
        self.dni := dni;
        self.nombre := nombre;
        self.domicilio := domicilio;
        self.tlfs := tlfs;
        RETURN;
    END;
END;
/

CREATE OR REPLACE TYPE t_vehiculo AS OBJECT (
    bastidor VARCHAR2(50),
    matricula VARCHAR2(10),
    marca VARCHAR2(30)
);
/

CREATE OR REPLACE TYPE t_reparacion AS OBJECT (
    coche t_vehiculo,
    fecha_ingreso DATE,
    fecha_salida DATE,
    reparaciones VARCHAR2(500),
    cliente t_cliente,
    STATIC PROCEDURE INSERTA_TABLA_REPARACION(v t_vehiculo, fi DATE, fs DATE, rep VARCHAR2, c t_cliente),
    STATIC PROCEDURE INSERTA_TABLA_REPARACION(obj_rep t_reparacion)
);
/

CREATE TABLE tabla_reparacion OF t_reparacion (
    coche NOT NULL,
    fecha_ingreso NOT NULL,
    cliente NOT NULL
);
/

CREATE OR REPLACE TYPE BODY t_reparacion AS
    STATIC PROCEDURE INSERTA_TABLA_REPARACION(v t_vehiculo, fi DATE, fs DATE, rep VARCHAR2, c t_cliente) IS
    BEGIN
        INSERT INTO tabla_reparacion VALUES (t_reparacion(v, fi, fs, rep, c));
    END;

    STATIC PROCEDURE INSERTA_TABLA_REPARACION(obj_rep t_reparacion) IS
    BEGIN
        t_reparacion.INSERTA_TABLA_REPARACION(obj_rep.coche, obj_rep.fecha_ingreso, obj_rep.fecha_salida, obj_rep.reparaciones, obj_rep.cliente);
    END;
END;
/

SET SERVEROUTPUT ON;

DECLARE
    -- Prueba de constructores de t_cliente
    clinull t_cliente := NEW t_cliente('dni', 'nombre', null, null); 
    cli0    t_cliente := NEW t_cliente('dni', 'nombre', null, new telefonos()); 
    clil    t_cliente := NEW t_cliente('dni', 'nombre', null, new telefonos('1111')); 
    cli2    t_cliente := NEW t_cliente('dni', 'nombre', null, new telefonos('1111','2222')); 

    -- Creación del objeto reparacion1
    reparacion1 t_reparacion := new t_reparacion(
        t_vehiculo('muchos numeros', '2222', 'Audi'), 
        TO_DATE('10/01/2023', 'DD/MM/YYYY'), 
        TO_DATE('15/01/2023', 'DD/MM/YYYY'), 
        'Rueda pinchada', 
        t_cliente(
            cliente_sec.NEXTVAL, '111111A', 'Ana', 
            t_direccion('Sevilla', 'C/ la otra', 'Portal D, 1 Plata, A', '11111'), 
            telefonos('1111') -- Nota: el boletín dice telefonos(null) pero esto genera error si no hay constructor específico para null, mejor poner un valor 
        )
    );

BEGIN
    -- Borrado de la tabla para empezar de cero 
    DELETE FROM tabla_reparacion; 

    -- Mostrar teléfonos usando el método getTelefonos() 
    dbms_output.put_line('Telefonos clinull: ' || clinull.getTelefonos()); 
    dbms_output.put_line('Telefonos cli0:    ' || cli0.getTelefonos()); 
    dbms_output.put_line('Telefonos clil:    ' || clil.getTelefonos()); 
    dbms_output.put_line('Telefonos cli2:    ' || cli2.getTelefonos()); 

    -- 1º Inserta usando el método de atributos individuales 
    t_reparacion.INSERTA_TABLA_REPARACION (
        t_vehiculo('No se el bastior', '1111', 'Mercedes'), 
        TO_DATE('10/01/2023', 'DD/MM/YYYY'), 
        TO_DATE('15/01/2023', 'DD/MM/YYYY'), 
        'Cambio de aceite', 
        t_cliente(
            cliente_sec.NEXTVAL, '49494949A', 'Pablo', 
            t_direccion('Sevilla', 'C/ la mia', '1A', ' '), 
            telefonos('1111') 
        )
    );

    -- 2º Inserta usando el objeto reparacion1 definido arriba 
    t_reparacion.INSERTA_TABLA_REPARACION (reparacion1); 

    COMMIT; 
    DBMS_OUTPUT.PUT_LINE('----- FINALIZADO CON EXITO -----'); 

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(' ERROR----- '); 
        DBMS_OUTPUT.put_line('Error: ' || TO_CHAR(SQLCODE)); 
        DBMS_OUTPUT.put_line(SQLERRM); 
        ROLLBACK; 
END;
/

SELECT 
    r.coche.marca AS marca_coche, 
    r.coche.matricula AS matricula,
    r.cliente.nombre AS nombre_cliente,
    r.cliente.domicilio.getDireccion() AS direccion_cliente,
    r.cliente.getTelefonos() AS telefonos,
    r.reparaciones AS descripcion_averia
FROM tabla_reparacion r;
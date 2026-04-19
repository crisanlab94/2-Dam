-- Borrado forzoso para limpiar la memoria
DROP TABLE TABLA_CLIENTES_SMART;
DROP TYPE t_cliente_smart FORCE;
DROP TYPE t_persona FORCE;
DROP TYPE V_DISPOSITIVOS FORCE;
DROP TYPE t_dispositivo FORCE;
DROP TYPE t_direccion FORCE; 
DROP SEQUENCE sec_disp;

CREATE OR REPLACE TYPE t_direccion AS OBJECT (
    CIUDAD VARCHAR2(20),
    CALLE VARCHAR2(20),
    NUMERO NUMBER,
    MEMBER FUNCTION getDireccion RETURN VARCHAR2 
);
/

CREATE OR REPLACE TYPE BODY t_direccion AS
    MEMBER FUNCTION getDireccion RETURN VARCHAR2 IS
    BEGIN
        RETURN SELF.ciudad || ', ' || SELF.calle || ', ' || SELF.numero;
    END;
END;
/

CREATE SEQUENCE sec_disp
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
MINVALUE 1
NOCYCLE;
/

CREATE OR REPLACE TYPE t_dispositivo AS OBJECT (
    ID NUMBER,
    NOMBRE VARCHAR2(50),
    CONSUMO NUMBER,
    CONSTRUCTOR FUNCTION t_dispositivo(p_NOMBRE VARCHAR2, p_CONSUMO NUMBER) RETURN SELF AS RESULT 
);
/

CREATE OR REPLACE TYPE BODY t_dispositivo AS
    CONSTRUCTOR FUNCTION t_dispositivo(p_NOMBRE VARCHAR2, p_CONSUMO NUMBER) RETURN SELF AS RESULT IS
    BEGIN
        SELF.ID := sec_disp.NEXTVAL; 
        SELF.NOMBRE := p_NOMBRE;
        -- Lógica de consumo negativo
        IF p_consumo < 0 THEN SELF.consumo := 0; 
        ELSE SELF.consumo := p_consumo; 
        END IF;
        RETURN; 
    END;
END;
/

CREATE OR REPLACE TYPE V_DISPOSITIVOS AS VARRAY(10) OF t_dispositivo;
/

--Herencia
CREATE OR REPLACE TYPE t_persona AS OBJECT (
    DNI VARCHAR2(9),
    NOMBRE VARCHAR2(50)
) NOT FINAL; --Siempre fuera
  
/

CREATE OR REPLACE TYPE t_cliente_smart UNDER t_persona (
    DOMICILIO t_direccion,
    mis_dispositivos V_DISPOSITIVOS,
    MEMBER FUNCTION calcularConsumoTotal RETURN NUMBER 
); 
/

CREATE TABLE TABLA_CLIENTES_SMART OF t_cliente_smart (
    DNI PRIMARY KEY
);
/

-- CUERPO de t_cliente_smart 
CREATE OR REPLACE TYPE BODY t_cliente_smart AS
    MEMBER FUNCTION calcularConsumoTotal RETURN NUMBER IS
        v_total NUMBER := 0;
    BEGIN
        -- Recorremos el VARRAY usando .COUNT 
        FOR i IN 1..SELF.mis_dispositivos.COUNT LOOP
            -- Sumamos el consumo de cada dispositivo de la lista 
            v_total := v_total + SELF.mis_dispositivos(i).CONSUMO;
        END LOOP;
        RETURN v_total;
    END;
END;
/

SET SERVEROUTPUT ON;

DECLARE
    -- Declaramos una variable para la dirección
    v_dir t_direccion := t_direccion('Sevilla', 'Calle Sierpes', 10);
    
    -- Declaramos la colección de dispositivos
    v_disps v_dispositivos := v_dispositivos(
        t_dispositivo('Bombilla Salón', 10),
        t_dispositivo('Aire Acondicionado', 1500),
        t_dispositivo('Altavoz', -5) 
    );
    
    -- Instanciamos el cliente (DNI, Nombre, Dirección, Dispositivos)
    v_cli t_cliente_smart := t_cliente_smart('12345678Z', 'Cristina', v_dir, v_disps);

BEGIN
    -- Limpiamos la tabla para la prueba
    DELETE FROM TABLA_CLIENTES_SMART;

    -- Insertamos el objeto cliente 
    INSERT INTO TABLA_CLIENTES_SMART VALUES (v_cli);
    
    -- Mostramos los resultados
    DBMS_OUTPUT.PUT_LINE('--- DATOS DEL CLIENTE ---');
    DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_cli.NOMBRE);
    DBMS_OUTPUT.PUT_LINE('Ubicación: ' || v_cli.DOMICILIO.getDireccion());
    
    -- Probamos  método de cálculo de consumo 
    DBMS_OUTPUT.PUT_LINE('Consumo Total Calculado: ' || v_cli.calcularConsumoTotal() || ' W');
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('--- PRUEBA FINALIZADA CON ÉXITO ---');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error detectado: ' || SQLERRM);
        ROLLBACK;
END;
/

--Muestra cuánto consume cada dispositivo de Cristina por separado
--c es tabla_clientes_smart una tabla
--d es mis_dispositivos un varray
SELECT c.NOMBRE, d.NOMBRE AS DISPOSITIVO, d.CONSUMO
FROM TABLA_CLIENTES_SMART c, TABLE(c.mis_dispositivos) d
WHERE c.DNI = '12345678Z';

--solo la ciudad de todos los clientes registrados
SELECT c.NOMBRE, c.DOMICILIO.CIUDAD 
FROM TABLA_CLIENTES_SMART c;

--ciudad y calle de todos los clientes
SELECT c.NOMBRE, c.DOMICILIO.CIUDAD, c.DOMICILIO.CALLE 
FROM TABLA_CLIENTES_SMART c;

--ciudad y calle del cleinte que yo quiera
SELECT c.NOMBRE, c.DOMICILIO.CIUDAD, c.DOMICILIO.CALLE 
FROM TABLA_CLIENTES_SMART c
WHERE c.DNI = '12345678Z';

--Consumo total
--cuando llamas a un método de un objeto dentro de una consulta SQL, es obligatorio usar el alias de la tabla (la c.)
SELECT c.NOMBRE, c.calcularConsumoTotal() AS WATTS_TOTALES 
FROM TABLA_CLIENTES_SMART c;
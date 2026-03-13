CREATE OR REPLACE TYPE t_concierto AS OBJECT (
    idConcierto VARCHAR2,
    fecha DATE,
    descripcion varchar2,
    escenario varchar2,
    grupo varchar2 
);
/
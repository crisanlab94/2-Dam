// 1. Seleccionamos la base de datos
use('StudyMatchKey');

// 2. CREAMOS EL ÍNDICE 
db.getCollection('estudiantes').createIndex(
  { "Id_Estudiante": 1 }, 
  { unique: true }
);

// 3. Insertamos los 30 estudiantes
db.getCollection('estudiantes').insertMany([
  {
    "Id_Estudiante": "E001",
    "Nombre": "Juan García",
    "Fecha_De_Nacimiento": "2005-01-10",
    "Email": "juan.garcia@uni1.com",
    "Edad": 19,
    "Nota": 7.5,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad Central",
      "Direccion": "Campus Norte, Madrid"
    },
    "Asignaturas": [
      { "Nombre": "Programación I", "Codigo": "UNI1-P1", "Profesor": "Dr. Alan Turing" },
      { "Nombre": "Cálculo", "Codigo": "UNI1-C1", "Profesor": "Dra. Ada Lovelace" }
    ]
  },
  {
    "Id_Estudiante": "E002",
    "Nombre": "Maria Lopez",
    "Fecha_De_Nacimiento": "2005-03-15",
    "Email": "maria.lopez@uni1.com",
    "Edad": 19,
    "Nota": 8.2,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad Central",
      "Direccion": "Campus Norte, Madrid"
    },
    "Asignaturas": [
      { "Nombre": "Programación I", "Codigo": "UNI1-P1", "Profesor": "Dr. Alan Turing" },
      { "Nombre": "Cálculo", "Codigo": "UNI1-C1", "Profesor": "Dra. Ada Lovelace" }
    ]
  },
  {
    "Id_Estudiante": "E003",
    "Nombre": "Carlos Ruiz",
    "Fecha_De_Nacimiento": "2004-06-20",
    "Email": "carlos.ruiz@uni1.com",
    "Edad": 20,
    "Nota": 6.8,
    "turnoMañana": false,
    "Curso": "SEGUNDO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad Central",
      "Direccion": "Campus Norte, Madrid"
    },
    "Asignaturas": [
      { "Nombre": "Estructura de Datos", "Codigo": "UNI1-ED", "Profesor": "Dr. Niklaus Wirth" },
      { "Nombre": "Álgebra", "Codigo": "UNI1-ALG", "Profesor": "Dr. George Boole" }
    ]
  },
  {
    "Id_Estudiante": "E004",
    "Nombre": "Laura Diaz",
    "Fecha_De_Nacimiento": "2004-02-10",
    "Email": "laura.diaz@uni1.com",
    "Edad": 20,
    "Nota": 9.0,
    "turnoMañana": false,
    "Curso": "SEGUNDO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad Central",
      "Direccion": "Campus Norte, Madrid"
    },
    "Asignaturas": [
      { "Nombre": "Estructura de Datos", "Codigo": "UNI1-ED", "Profesor": "Dr. Niklaus Wirth" },
      { "Nombre": "Álgebra", "Codigo": "UNI1-ALG", "Profesor": "Dr. George Boole" }
    ]
  },
  {
    "Id_Estudiante": "E005",
    "Nombre": "Pedro Sanchez",
    "Fecha_De_Nacimiento": "2005-08-05",
    "Email": "pedro.s@uni2.com",
    "Edad": 19,
    "Nota": 5.5,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad del Norte",
      "Direccion": "Av. Diagonal, Barcelona"
    },
    "Asignaturas": [
      { "Nombre": "Programación I", "Codigo": "UNI2-P1", "Profesor": "Dra. Marie Curie" },
      { "Nombre": "Física", "Codigo": "UNI2-F1", "Profesor": "Dr. Albert Einstein" }
    ]
  },
  {
    "Id_Estudiante": "E006",
    "Nombre": "Sofia Martin",
    "Fecha_De_Nacimiento": "2005-09-12",
    "Email": "sofia.m@uni2.com",
    "Edad": 19,
    "Nota": 8.8,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad del Norte",
      "Direccion": "Av. Diagonal, Barcelona"
    },
    "Asignaturas": [
      { "Nombre": "Programación I", "Codigo": "UNI2-P1", "Profesor": "Dra. Marie Curie" },
      { "Nombre": "Física", "Codigo": "UNI2-F1", "Profesor": "Dr. Albert Einstein" }
    ]
  },
  {
    "Id_Estudiante": "E007",
    "Nombre": "Diego Fernandez",
    "Fecha_De_Nacimiento": "2005-12-01",
    "Email": "diego.f@uni2.com",
    "Edad": 19,
    "Nota": 7.0,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad del Norte",
      "Direccion": "Av. Diagonal, Barcelona"
    },
    "Asignaturas": [
      { "Nombre": "Programación I", "Codigo": "UNI2-P1", "Profesor": "Dra. Marie Curie" },
      { "Nombre": "Física", "Codigo": "UNI2-F1", "Profesor": "Dr. Albert Einstein" }
    ]
  },
  {
    "Id_Estudiante": "E008",
    "Nombre": "Lucia Gomez",
    "Fecha_De_Nacimiento": "2003-04-20",
    "Email": "lucia.g@uni3.com",
    "Edad": 21,
    "Nota": 9.5,
    "turnoMañana": true,
    "Curso": "TERCERO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad Politécnica",
      "Direccion": "Camino de Vera, Valencia"
    },
    "Asignaturas": [
      { "Nombre": "Robótica", "Codigo": "UNI3-ROB", "Profesor": "Dr. Nikola Tesla" },
      { "Nombre": "Electrónica", "Codigo": "UNI3-ELE", "Profesor": "Dr. Thomas Edison" }
    ]
  },
  {
    "Id_Estudiante": "E009",
    "Nombre": "Miguel Angel",
    "Fecha_De_Nacimiento": "2003-05-22",
    "Email": "miguel.a@uni3.com",
    "Edad": 21,
    "Nota": 6.0,
    "turnoMañana": true,
    "Curso": "TERCERO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad Politécnica",
      "Direccion": "Camino de Vera, Valencia"
    },
    "Asignaturas": [
      { "Nombre": "Robótica", "Codigo": "UNI3-ROB", "Profesor": "Dr. Nikola Tesla" },
      { "Nombre": "Electrónica", "Codigo": "UNI3-ELE", "Profesor": "Dr. Thomas Edison" }
    ]
  },
  {
    "Id_Estudiante": "E010",
    "Nombre": "Elena Blanco",
    "Fecha_De_Nacimiento": "2003-07-30",
    "Email": "elena.b@uni3.com",
    "Edad": 21,
    "Nota": 8.0,
    "turnoMañana": true,
    "Curso": "TERCERO",
    "Entidad": {
      "Tipo": "Universidad",
      "Nombre": "Universidad Politécnica",
      "Direccion": "Camino de Vera, Valencia"
    },
    "Asignaturas": [
      { "Nombre": "Robótica", "Codigo": "UNI3-ROB", "Profesor": "Dr. Nikola Tesla" },
      { "Nombre": "Electrónica", "Codigo": "UNI3-ELE", "Profesor": "Dr. Thomas Edison" }
    ]
  },
  {
    "Id_Estudiante": "E011",
    "Nombre": "Pablo Cruz",
    "Fecha_De_Nacimiento": "2008-05-05",
    "Email": "pablo.cruz@col1.com",
    "Edad": 16,
    "Nota": 8.5,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio San José",
      "Direccion": "Calle Mayor 1"
    },
    "Asignaturas": [
      { "Nombre": "Lengua", "Codigo": "COL1-L1", "Profesor": "Sr. Federico Lorca" },
      { "Nombre": "Historia", "Codigo": "COL1-H1", "Profesor": "Sra. Isabel Católica" }
    ]
  },
  {
    "Id_Estudiante": "E012",
    "Nombre": "Sara Mendez",
    "Fecha_De_Nacimiento": "2008-02-14",
    "Email": "sara.m@col1.com",
    "Edad": 16,
    "Nota": 9.2,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio San José",
      "Direccion": "Calle Mayor 1"
    },
    "Asignaturas": [
      { "Nombre": "Lengua", "Codigo": "COL1-L1", "Profesor": "Sr. Federico Lorca" },
      { "Nombre": "Historia", "Codigo": "COL1-H1", "Profesor": "Sra. Isabel Católica" }
    ]
  },
  {
    "Id_Estudiante": "E013",
    "Nombre": "Javier Gil",
    "Fecha_De_Nacimiento": "2008-11-11",
    "Email": "javier.g@col1.com",
    "Edad": 16,
    "Nota": 5.0,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio San José",
      "Direccion": "Calle Mayor 1"
    },
    "Asignaturas": [
      { "Nombre": "Lengua", "Codigo": "COL1-L1", "Profesor": "Sr. Federico Lorca" },
      { "Nombre": "Historia", "Codigo": "COL1-H1", "Profesor": "Sra. Isabel Católica" }
    ]
  },
  {
    "Id_Estudiante": "E014",
    "Nombre": "Raquel Pardo",
    "Fecha_De_Nacimiento": "2007-06-01",
    "Email": "raquel.p@col2.com",
    "Edad": 17,
    "Nota": 7.8,
    "turnoMañana": false,
    "Curso": "SEGUNDO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio Las Encinas",
      "Direccion": "Calle Bosque 5"
    },
    "Asignaturas": [
      { "Nombre": "Arte", "Codigo": "COL2-A2", "Profesor": "Sr. Pablo Picasso" },
      { "Nombre": "Biología", "Codigo": "COL2-B2", "Profesor": "Sr. Ramón y Cajal" }
    ]
  },
  {
    "Id_Estudiante": "E015",
    "Nombre": "Victor Sanz",
    "Fecha_De_Nacimiento": "2007-08-20",
    "Email": "victor.s@col2.com",
    "Edad": 17,
    "Nota": 6.5,
    "turnoMañana": false,
    "Curso": "SEGUNDO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio Las Encinas",
      "Direccion": "Calle Bosque 5"
    },
    "Asignaturas": [
      { "Nombre": "Arte", "Codigo": "COL2-A2", "Profesor": "Sr. Pablo Picasso" },
      { "Nombre": "Biología", "Codigo": "COL2-B2", "Profesor": "Sr. Ramón y Cajal" }
    ]
  },
  {
    "Id_Estudiante": "E016",
    "Nombre": "Nuria Vega",
    "Fecha_De_Nacimiento": "2007-10-30",
    "Email": "nuria.v@col2.com",
    "Edad": 17,
    "Nota": 8.0,
    "turnoMañana": false,
    "Curso": "SEGUNDO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio Las Encinas",
      "Direccion": "Calle Bosque 5"
    },
    "Asignaturas": [
      { "Nombre": "Arte", "Codigo": "COL2-A2", "Profesor": "Sr. Pablo Picasso" },
      { "Nombre": "Biología", "Codigo": "COL2-B2", "Profesor": "Sr. Ramón y Cajal" }
    ]
  },
  {
    "Id_Estudiante": "E017",
    "Nombre": "Andres Iniesta",
    "Fecha_De_Nacimiento": "2006-05-11",
    "Email": "andres.i@col3.com",
    "Edad": 18,
    "Nota": 9.9,
    "turnoMañana": true,
    "Curso": "TERCERO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio El Pilar",
      "Direccion": "Calle Goya 10"
    },
    "Asignaturas": [
      { "Nombre": "Literatura", "Codigo": "COL3-L3", "Profesor": "Sr. Antonio Machado" },
      { "Nombre": "Filosofía", "Codigo": "COL3-F3", "Profesor": "Sr. Ortega y Gasset" }
    ]
  },
  {
    "Id_Estudiante": "E018",
    "Nombre": "Iker Casillas",
    "Fecha_De_Nacimiento": "2006-05-20",
    "Email": "iker.c@col3.com",
    "Edad": 18,
    "Nota": 7.2,
    "turnoMañana": true,
    "Curso": "TERCERO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio El Pilar",
      "Direccion": "Calle Goya 10"
    },
    "Asignaturas": [
      { "Nombre": "Literatura", "Codigo": "COL3-L3", "Profesor": "Sr. Antonio Machado" },
      { "Nombre": "Filosofía", "Codigo": "COL3-F3", "Profesor": "Sr. Ortega y Gasset" }
    ]
  },
  {
    "Id_Estudiante": "E019",
    "Nombre": "Sergio Ramos",
    "Fecha_De_Nacimiento": "2005-03-30",
    "Email": "sergio.r@col4.com",
    "Edad": 19,
    "Nota": 5.0,
    "turnoMañana": true,
    "Curso": "CUARTO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio Santa María",
      "Direccion": "Calle Paz 8"
    },
    "Asignaturas": [
      { "Nombre": "Química", "Codigo": "COL4-Q4", "Profesor": "Sr. Dmitri Mendeléyev" },
      { "Nombre": "Inglés", "Codigo": "COL4-I4", "Profesor": "Mr. William Shakespeare" }
    ]
  },
  {
    "Id_Estudiante": "E020",
    "Nombre": "Gerard Pique",
    "Fecha_De_Nacimiento": "2005-02-02",
    "Email": "gerard.p@col4.com",
    "Edad": 19,
    "Nota": 6.5,
    "turnoMañana": true,
    "Curso": "CUARTO",
    "Entidad": {
      "Tipo": "Colegio",
      "Nombre": "Colegio Santa María",
      "Direccion": "Calle Paz 8"
    },
    "Asignaturas": [
      { "Nombre": "Química", "Codigo": "COL4-Q4", "Profesor": "Sr. Dmitri Mendeléyev" },
      { "Nombre": "Inglés", "Codigo": "COL4-I4", "Profesor": "Mr. William Shakespeare" }
    ]
  },
  {
    "Id_Estudiante": "E021",
    "Nombre": "Isaac Newton",
    "Fecha_De_Nacimiento": "2008-01-04",
    "Email": "isaac.n@inst1.com",
    "Edad": 16,
    "Nota": 10.0,
    "turnoMañana": false,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Tecnológico",
      "Direccion": "Av. Ciencia 1"
    },
    "Asignaturas": [
      { "Nombre": "Tecnología", "Codigo": "INS1-T1", "Profesor": "Sr. Steve Jobs" },
      { "Nombre": "Informática", "Codigo": "INS1-INF", "Profesor": "Sr. Bill Gates" }
    ]
  },
  {
    "Id_Estudiante": "E022",
    "Nombre": "Albert Rivera",
    "Fecha_De_Nacimiento": "2008-03-03",
    "Email": "albert.r@inst1.com",
    "Edad": 16,
    "Nota": 6.0,
    "turnoMañana": false,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Tecnológico",
      "Direccion": "Av. Ciencia 1"
    },
    "Asignaturas": [
      { "Nombre": "Tecnología", "Codigo": "INS1-T1", "Profesor": "Sr. Steve Jobs" },
      { "Nombre": "Informática", "Codigo": "INS1-INF", "Profesor": "Sr. Bill Gates" }
    ]
  },
  {
    "Id_Estudiante": "E023",
    "Nombre": "Ines Arrimadas",
    "Fecha_De_Nacimiento": "2008-04-04",
    "Email": "ines.a@inst1.com",
    "Edad": 16,
    "Nota": 7.5,
    "turnoMañana": false,
    "Curso": "PRIMERO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Tecnológico",
      "Direccion": "Av. Ciencia 1"
    },
    "Asignaturas": [
      { "Nombre": "Tecnología", "Codigo": "INS1-T1", "Profesor": "Sr. Steve Jobs" },
      { "Nombre": "Informática", "Codigo": "INS1-INF", "Profesor": "Sr. Bill Gates" }
    ]
  },
  {
    "Id_Estudiante": "E024",
    "Nombre": "Fernando Alonso",
    "Fecha_De_Nacimiento": "2007-07-29",
    "Email": "fernando.a@inst2.com",
    "Edad": 17,
    "Nota": 9.0,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Severo Ochoa",
      "Direccion": "Calle Nobel 2"
    },
    "Asignaturas": [
      { "Nombre": "Biología Molecular", "Codigo": "INS2-BM", "Profesor": "Sr. Charles Darwin" },
      { "Nombre": "Química Orgánica", "Codigo": "INS2-QO", "Profesor": "Sr. Louis Pasteur" }
    ]
  },
  {
    "Id_Estudiante": "E025",
    "Nombre": "Carlos Sainz",
    "Fecha_De_Nacimiento": "2007-09-01",
    "Email": "carlos.s@inst2.com",
    "Edad": 17,
    "Nota": 8.5,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Severo Ochoa",
      "Direccion": "Calle Nobel 2"
    },
    "Asignaturas": [
      { "Nombre": "Biología Molecular", "Codigo": "INS2-BM", "Profesor": "Sr. Charles Darwin" },
      { "Nombre": "Química Orgánica", "Codigo": "INS2-QO", "Profesor": "Sr. Louis Pasteur" }
    ]
  },
  {
    "Id_Estudiante": "E026",
    "Nombre": "Marc Marquez",
    "Fecha_De_Nacimiento": "2007-02-17",
    "Email": "marc.m@inst2.com",
    "Edad": 17,
    "Nota": 5.5,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Severo Ochoa",
      "Direccion": "Calle Nobel 2"
    },
    "Asignaturas": [
      { "Nombre": "Biología Molecular", "Codigo": "INS2-BM", "Profesor": "Sr. Charles Darwin" },
      { "Nombre": "Química Orgánica", "Codigo": "INS2-QO", "Profesor": "Sr. Louis Pasteur" }
    ]
  },
  {
    "Id_Estudiante": "E027",
    "Nombre": "Rafael Nadal",
    "Fecha_De_Nacimiento": "2006-06-03",
    "Email": "rafa.n@inst3.com",
    "Edad": 18,
    "Nota": 10.0,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Goya",
      "Direccion": "Calle Arte 9"
    },
    "Asignaturas": [
      { "Nombre": "Dibujo Técnico", "Codigo": "INS3-DT", "Profesor": "Sr. Leonardo Da Vinci" },
      { "Nombre": "Historia del Arte", "Codigo": "INS3-HA", "Profesor": "Sr. Miguel Ángel" }
    ]
  },
  {
    "Id_Estudiante": "E028",
    "Nombre": "Novak Djokovic",
    "Fecha_De_Nacimiento": "2006-05-22",
    "Email": "novak.d@inst3.com",
    "Edad": 18,
    "Nota": 8.0,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Goya",
      "Direccion": "Calle Arte 9"
    },
    "Asignaturas": [
      { "Nombre": "Dibujo Técnico", "Codigo": "INS3-DT", "Profesor": "Sr. Leonardo Da Vinci" },
      { "Nombre": "Historia del Arte", "Codigo": "INS3-HA", "Profesor": "Sr. Miguel Ángel" }
    ]
  },
  {
    "Id_Estudiante": "E029",
    "Nombre": "Roger Federer",
    "Fecha_De_Nacimiento": "2005-08-08",
    "Email": "roger.f@inst4.com",
    "Edad": 19,
    "Nota": 9.5,
    "turnoMañana": true,
    "Curso": "CUARTO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Cervantes",
      "Direccion": "Calle Letras 4"
    },
    "Asignaturas": [
      { "Nombre": "Música", "Codigo": "INS4-MUS", "Profesor": "Sr. Ludwig van Beethoven" },
      { "Nombre": "Latín", "Codigo": "INS4-LAT", "Profesor": "Sr. Julio César" }
    ]
  },
  {
    "Id_Estudiante": "E030",
    "Nombre": "Serena Williams",
    "Fecha_De_Nacimiento": "2005-09-26",
    "Email": "serena.w@inst4.com",
    "Edad": 19,
    "Nota": 7.0,
    "turnoMañana": true,
    "Curso": "CUARTO",
    "Entidad": {
      "Tipo": "Instituto",
      "Nombre": "IES Cervantes",
      "Direccion": "Calle Letras 4"
    },
    "Asignaturas": [
      { "Nombre": "Música", "Codigo": "INS4-MUS", "Profesor": "Sr. Ludwig van Beethoven" },
      { "Nombre": "Latín", "Codigo": "INS4-LAT", "Profesor": "Sr. Julio César" }
    ]
  }
]);




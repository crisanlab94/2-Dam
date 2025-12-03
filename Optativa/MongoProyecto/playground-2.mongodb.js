//Creo la base de datos
use("StudyMatchKey");
//Creo la coleccion
db.createCollection("estudiantes");


db.estudiantes.insertMany(
    [
  {
    "Id_Estudiante": "E001",
    "Nombre": "Cristina Sandoval",
    "Fecha_De_Nacimiento": "2005-04-15",
    "Email": "cristina.sandoval@example.com",
    "Edad": 18,
    "Nota": 9.5,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad del Norte", "Direccion": "Calle Ficticia 123, Ciudad A" },
    "Asignaturas": [
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Ana Gómez" },
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Luis García" }
    ]
  },
  {
    "Id_Estudiante": "E002",
    "Nombre": "Laura Torres",
    "Fecha_De_Nacimiento": "2005-02-20",
    "Email": "laura.torres@example.com",
    "Edad": 18,
    "Nota": 8.7,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad del Norte", "Direccion": "Calle Ficticia 123, Ciudad A" },
    "Asignaturas": [
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" },
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "María Torres" },
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Luis García" }
    ]
  },
  {
    "Id_Estudiante": "E003",
    "Nombre": "María Fernández",
    "Fecha_De_Nacimiento": "2005-03-14",
    "Email": "maria.fernandez@example.com",
    "Edad": 18,
    "Nota": 8.9,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad del Norte", "Direccion": "Calle Ficticia 123, Ciudad A" },
    "Asignaturas": [
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" },
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "Lucía Moreno" },
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Luis García" }
    ]
  },
  {
    "Id_Estudiante": "E004",
    "Nombre": "Alejandro Ruiz",
    "Fecha_De_Nacimiento": "2004-12-02",
    "Email": "alejandro.ruiz@example.com",
    "Edad": 19,
    "Nota": 7.9,
    "turnoMañana": false,
    "Curso": "SEGUNDO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad del Norte", "Direccion": "Calle Ficticia 123, Ciudad A" },
    "Asignaturas": [
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Ana Gómez" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" }
    ]
  },
  {
    "Id_Estudiante": "E005",
    "Nombre": "Sofía Ramírez",
    "Fecha_De_Nacimiento": "2005-11-05",
    "Email": "sofia.ramirez@example.com",
    "Edad": 18,
    "Nota": 9.1,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad del Norte", "Direccion": "Calle Ficticia 123, Ciudad A" },
    "Asignaturas": [
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" },
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "María Torres" }
    ]
  },
  {
    "Id_Estudiante": "E006",
    "Nombre": "Fernando Ruiz",
    "Fecha_De_Nacimiento": "2004-11-02",
    "Email": "fernando.ruiz@example.com",
    "Edad": 19,
    "Nota": 7.3,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad del Norte", "Direccion": "Calle Ficticia 123, Ciudad A" },
    "Asignaturas": [
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Roberto Jiménez" },
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "Lucía Moreno" }
    ]
  },
  {
    "Id_Estudiante": "E007",
    "Nombre": "Javier Gómez",
    "Fecha_De_Nacimiento": "2004-09-30",
    "Email": "javier.gomez@example.com",
    "Edad": 19,
    "Nota": 7.5,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Central", "Direccion": "Avenida Central 45, Ciudad B" },
    "Asignaturas": [
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Luis García" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Ana Gómez" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Pedro Sánchez" }
    ]
  },
  {
    "Id_Estudiante": "E008",
    "Nombre": "Daniel Pérez",
    "Fecha_De_Nacimiento": "2004-10-12",
    "Email": "daniel.perez@example.com",
    "Edad": 19,
    "Nota": 8.2,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Central", "Direccion": "Avenida Central 45, Ciudad B" },
    "Asignaturas": [
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Luis García" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Pedro Sánchez" }
    ]
  },
  {
    "Id_Estudiante": "E009",
    "Nombre": "Mario Herrera",
    "Fecha_De_Nacimiento": "2004-04-09",
    "Email": "mario.herrera@example.com",
    "Edad": 19,
    "Nota": 8.1,
    "turnoMañana": true,
    "Curso": "TERCERO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Central", "Direccion": "Avenida Central 45, Ciudad B" },
    "Asignaturas": [
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Luis García" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Ana Gómez" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Pedro Sánchez" }
    ]
  },
  {
    "Id_Estudiante": "E010",
    "Nombre": "Miguel López",
    "Fecha_De_Nacimiento": "2004-07-08",
    "Email": "miguel.lopez@example.com",
    "Edad": 19,
    "Nota": 8.7,
    "turnoMañana": false,
    "Curso": "CUARTO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad Central", "Direccion": "Avenida Principal 50, Ciudad D" },
    "Asignaturas": [
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Roberto Jiménez" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Ana Gómez" }
    ]
  },
  {
    "Id_Estudiante": "E011",
    "Nombre": "Lucía Sánchez",
    "Fecha_De_Nacimiento": "2005-08-19",
    "Email": "lucia.sanchez@example.com",
    "Edad": 18,
    "Nota": 9.3,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Instituto", "Nombre": "Instituto Sur", "Direccion": "Calle Sur 18, Ciudad I" },
    "Asignaturas": [
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Carlos Méndez" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Laura Paredes" }
    ]
  },
  {
    "Id_Estudiante": "E012",
    "Nombre": "Pablo Cruz",
    "Fecha_De_Nacimiento": "2004-05-05",
    "Email": "pablo.cruz@example.com",
    "Edad": 19,
    "Nota": 8.5,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad Oeste", "Direccion": "Avenida Oeste 22, Ciudad M" },
    "Asignaturas": [
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Ana Gómez" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Roberto Jiménez" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" }
    ]
  },
  {
    "Id_Estudiante": "E013",
    "Nombre": "Clara Navarro",
    "Fecha_De_Nacimiento": "2005-12-30",
    "Email": "clara.navarro@example.com",
    "Edad": 18,
    "Nota": 9.0,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Verde", "Direccion": "Calle Verde 14, Ciudad N" },
    "Asignaturas": [
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" }
    ]
  },
  {
    "Id_Estudiante": "E014",
    "Nombre": "Sergio Herrera",
    "Fecha_De_Nacimiento": "2004-03-17",
    "Email": "sergio.herrera@example.com",
    "Edad": 19,
    "Nota": 6.9,
    "turnoMañana": true,
    "Curso": "CUARTO",
    "Entidad": { "Tipo": "Instituto", "Nombre": "Instituto Central", "Direccion": "Avenida Central 30, Ciudad O" },
    "Asignaturas": [
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Carlos Méndez" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Laura Paredes" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Ana Gómez" }
    ]
  },
  {
    "Id_Estudiante": "E015",
    "Nombre": "Elena Martín",
    "Fecha_De_Nacimiento": "2005-06-27",
    "Email": "elena.martin@example.com",
    "Edad": 18,
    "Nota": 8.4,
    "turnoMañana": false,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad Sur", "Direccion": "Calle Sur 9, Ciudad P" },
    "Asignaturas": [
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" },
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" },
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" }
    ]
  },
  {
    "Id_Estudiante": "E016",
    "Nombre": "Isabel Moreno",
    "Fecha_De_Nacimiento": "2005-01-09",
    "Email": "isabel.moreno@example.com",
    "Edad": 18,
    "Nota": 9.4,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": { "Tipo": "Instituto", "Nombre": "Instituto Este", "Direccion": "Calle Este 8, Ciudad R" },
    "Asignaturas": [
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Laura Paredes" }
    ]
  },
  {
    "Id_Estudiante": "E017",
    "Nombre": "Óscar Blanco",
    "Fecha_De_Nacimiento": "2004-08-14",
    "Email": "oscar.blanco@example.com",
    "Edad": 19,
    "Nota": 7.7,
    "turnoMañana": true,
    "Curso": "CUARTO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad Norte", "Direccion": "Avenida Norte 7, Ciudad S" },
    "Asignaturas": [
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Roberto Jiménez" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" }
    ]
  },
  {
    "Id_Estudiante": "E018",
    "Nombre": "Patricia Cruz",
    "Fecha_De_Nacimiento": "2005-05-30",
    "Email": "patricia.cruz@example.com",
    "Edad": 18,
    "Nota": 8.8,
    "turnoMañana": false,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Sur", "Direccion": "Calle Sur 11, Ciudad T" },
    "Asignaturas": [
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "Luis Pérez" },
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Laura Paredes" }
    ]
  },
  {
    "Id_Estudiante": "E019",
    "Nombre": "Raúl Navarro",
    "Fecha_De_Nacimiento": "2004-02-28",
    "Email": "raul.navarro@example.com",
    "Edad": 19,
    "Nota": 7.2,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": { "Tipo": "Instituto", "Nombre": "Instituto Norte", "Direccion": "Calle Norte 5, Ciudad U" },
    "Asignaturas": [
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Laura Paredes" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Carlos Méndez" }
    ]
  },
  {
    "Id_Estudiante": "E020",
    "Nombre": "Silvia Ruiz",
    "Fecha_De_Nacimiento": "2005-10-17",
    "Email": "silvia.ruiz@example.com",
    "Edad": 18,
    "Nota": 9.6,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad del Oeste", "Direccion": "Avenida Oeste 18, Ciudad V" },
    "Asignaturas": [
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" },
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "Lucía Moreno" }
    ]
  },
  {
    "Id_Estudiante": "E021",
    "Nombre": "Mario Herrera",
    "Fecha_De_Nacimiento": "2004-04-09",
    "Email": "mario.herrera@example.com",
    "Edad": 19,
    "Nota": 8.1,
    "turnoMañana": true,
    "Curso": "CUARTO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Nuevo", "Direccion": "Calle Nueva 2, Ciudad W" },
    "Asignaturas": [
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Carlos Méndez" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Laura Paredes" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Pedro Sánchez" }
    ]
  },
  {
    "Id_Estudiante": "E022",
    "Nombre": "Elisa Vega",
    "Fecha_De_Nacimiento": "2005-07-23",
    "Email": "elisa.vega@example.com",
    "Edad": 18,
    "Nota": 9.2,
    "turnoMañana": false,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Instituto", "Nombre": "Instituto Sur", "Direccion": "Calle Sur 25, Ciudad X" },
    "Asignaturas": [
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" },
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "Lucía Moreno" }
    ]
  },
  {
    "Id_Estudiante": "E023",
    "Nombre": "Tomás Medina",
    "Fecha_De_Nacimiento": "2004-11-15",
    "Email": "tomas.medina@example.com",
    "Edad": 19,
    "Nota": 7.4,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad Norte", "Direccion": "Avenida Norte 14, Ciudad Y" },
    "Asignaturas": [
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Juan Pérez" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Roberto Jiménez" }
    ]
  },
  {
    "Id_Estudiante": "E024",
    "Nombre": "Marta Blanco",
    "Fecha_De_Nacimiento": "2005-03-03",
    "Email": "marta.blanco@example.com",
    "Edad": 18,
    "Nota": 8.6,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Este", "Direccion": "Calle Este 9, Ciudad Z" },
    "Asignaturas": [
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" },
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Laura Paredes" }
    ]
  },
  {
    "Id_Estudiante": "E025",
    "Nombre": "Iván Ortega",
    "Fecha_De_Nacimiento": "2004-05-19",
    "Email": "ivan.ortega@example.com",
    "Edad": 19,
    "Nota": 7.1,
    "turnoMañana": true,
    "Curso": "CUARTO",
    "Entidad": { "Tipo": "Instituto", "Nombre": "Instituto Oeste", "Direccion": "Avenida Oeste 4, Ciudad AA" },
    "Asignaturas": [
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Carlos Méndez" },
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Laura Paredes" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" }
    ]
  },
  {
    "Id_Estudiante": "E026",
    "Nombre": "Nuria Castillo",
    "Fecha_De_Nacimiento": "2005-12-01",
    "Email": "nuria.castillo@example.com",
    "Edad": 18,
    "Nota": 9.7,
    "turnoMañana": false,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad Sur", "Direccion": "Calle Sur 18, Ciudad BB" },
    "Asignaturas": [
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "Lucía Moreno" },
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" }
    ]
  },
  {
    "Id_Estudiante": "E027",
    "Nombre": "Alberto Fuentes",
    "Fecha_De_Nacimiento": "2004-09-07",
    "Email": "alberto.fuentes@example.com",
    "Edad": 19,
    "Nota": 8.3,
    "turnoMañana": true,
    "Curso": "SEGUNDO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Norte", "Direccion": "Calle Norte 30, Ciudad CC" },
    "Asignaturas": [
      { "Nombre": "Matemáticas", "Codigo": "MAT101", "Profesor": "Pedro Sánchez" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Laura Paredes" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" }
    ]
  },
  {
    "Id_Estudiante": "E028",
    "Nombre": "Beatriz Muñoz",
    "Fecha_De_Nacimiento": "2005-02-25",
    "Email": "beatriz.munoz@example.com",
    "Edad": 18,
    "Nota": 9.0,
    "turnoMañana": false,
    "Curso": "TERCERO",
    "Entidad": { "Tipo": "Instituto", "Nombre": "Instituto Central", "Direccion": "Avenida Central 15, Ciudad DD" },
    "Asignaturas": [
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" },
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "Lucía Moreno" }
    ]
  },
  {
    "Id_Estudiante": "E029",
    "Nombre": "David Morales",
    "Fecha_De_Nacimiento": "2004-06-22",
    "Email": "david.morales@example.com",
    "Edad": 19,
    "Nota": 8.0,
    "turnoMañana": false,
    "Curso": "CUARTO",
    "Entidad": { "Tipo": "Colegio", "Nombre": "Colegio Central", "Direccion": "Calle Central 5, Ciudad H" },
    "Asignaturas": [
      { "Nombre": "Biología", "Codigo": "BIO101", "Profesor": "Clara Jiménez" },
      { "Nombre": "Química", "Codigo": "QUI101", "Profesor": "Marta López" },
      { "Nombre": "Física", "Codigo": "FIS101", "Profesor": "Carlos Méndez" }
    ]
  },
  {
    "Id_Estudiante": "E030",
    "Nombre": "Beatriz Muñoz",
    "Fecha_De_Nacimiento": "2005-02-25",
    "Email": "beatriz.munoz2@example.com",
    "Edad": 18,
    "Nota": 9.0,
    "turnoMañana": true,
    "Curso": "PRIMERO",
    "Entidad": { "Tipo": "Universidad", "Nombre": "Universidad Este", "Direccion": "Avenida Este 3, Ciudad EE" },
    "Asignaturas": [
      { "Nombre": "Inglés", "Codigo": "ING101", "Profesor": "Lucía Moreno" },
      { "Nombre": "Historia", "Codigo": "HIS150", "Profesor": "Ana Gómez" },
      { "Nombre": "Programación", "Codigo": "PROG201", "Profesor": "Laura Paredes" }
    ]
  }
]

);

//Creo el indice donde digo que id_estudiante es unico
db.estudiantes.createIndex(
  { "Id_Estudiante": 1 },
  { unique: true }
);


// Find a document in a collection.
db.getCollection("estudiantes").findOne({

});

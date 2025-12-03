
// Create a new database.
use("StudyMatchKey");

// Create a new collection.
db.createCollection("estudiantes");

db.estudiantes.insertOne(
    {
        "Id_Estudiante": "S12345",
        "Nombre": "Cristina Sandoval",
        "Fecha_De_Nacimiento": "1994-06-16",
        "Email": "cristina.sandoval@example.com",
        "Edad": 31,
        "Nota": 8.5,
        "turnoMañana": true,
        "Entidad": {
            "Tipo": "Universidad",
            "Nombre": "Universidad de Sevilla",
            "Direccion": "Calle Ficticia 123, Ciudad"
        },
        "Asignaturas": [
            {
                "Nombre": "Matemáticas",
                "Codigo": "MAT101",
                "Profesor": "Juan Pérez"
            },
            {
                "Nombre": "Programación",
                "Codigo": "PROG201",
                "Profesor": "Ana Gómez"
            },
            {
                "Nombre": "Historia",
                "Codigo": "HIS150",
                "Profesor": "Laura Torres"
            }
        ]
    }
);

db.estudiantes.deleteOne({ "Id_Estudiante": "S12345" });
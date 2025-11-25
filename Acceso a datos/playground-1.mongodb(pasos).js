//Crear base de datos
use("cristina");

//Crear coleccion
db.createCollection("empleados");

//Trabajar con mi coleccion e inserte lo que yo le pase
//con .insertMany inserto más de uno[]
db.empleados.insertOne(
    /*{
      "_id": 12345,
      "nombre": "Pablo Pérez",
      "cargo": "Analista de Datos",
      "departamento": "Tecnología",
      "salario": 42000,
      "fecha_contratacion": "2022-05-10",
      "email": "pablo.perez@empresa.com",
      "activo": true
    },*/

    {
        "movie": "The Haunting",
        "year": 1999,
        "release_date": "1999-07-23",
        "director": "Jan de Bont",
        "character": "Luke Sanderson",
        "movie_duration": "01:52:37",
        "poster": "https://images.ctfassets.net/bs8ntwkklfua/6Zu7ux0JYrUWk0UC8vxPtj/d0da8bfbad670655779a9921ac514759/The_Haunting_Poster.jpg",
        "video":
        {
            "1080p": "https://videos.ctfassets.net/bs8ntwkklfua/01C128tlGJ79vtlFEPc7V9/184759a9ca6fb1ab57114977afccb2ff/The_Haunting_Wow_4_1080p.mp4",
            "360p": "https://videos.ctfassets.net/bs8ntwkklfua/6bwt57tdhBCbhsXZ4i0pce/7f6debc7e52f80c78a618f19c8087985/The_Haunting_Wow_4_360p.mp4"
        }
    }
);

//Ver contenido de mi coleccion equivalente a select *
db.empleados.find();

//ver el contenido de uno en concreto  .count() saber cantidad
db.empleados.find({ 'cargo': 'Analista de Datos' }).count();

//ver solo cierto contenido
db.empleados.find({}, { 'nombre': 1, 'cargo': 1, '_id': 0 })

//asi no sale el id
db.empleados.find({ _id: '12345' });

//para hacer una busqueda por id generado por la base de datos
db.empleados.find({ _id: ObjectId("691c4db0d7061cd9efb77df4") });

//
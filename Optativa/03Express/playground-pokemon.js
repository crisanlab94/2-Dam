//Crear base de datos
use("pokemon");

//Crear coleccion
db.createCollection("pokemon");

db.pokemon.insertMany([
    {

        nombre: "Caterpie",
        tipo: "Bicho",
        descripcion: "Es lamentable"
    },
    {

        nombre: "Weedle",
        tipo: "Bicho",
        descripcion: "También es lamentable"
    },
    {

        nombre: "Magikarp",
        tipo: "Agua",
        descripcion: "Qué cosa más mala"
    }
]);
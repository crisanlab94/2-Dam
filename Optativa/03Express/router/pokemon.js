const express = require('express');
const router = express.Router();
const Pokemon = require('../models/pokemon');

router.get('/', async (req, res) => {
    try {
        //Le pondremos arrayPokemonDB para diferenciar
        //los datos que vienen de la base de datos
        //con respecto al arrayPokemon que tenemos EN LA VISTA
        const arrayPokemonDB = await Pokemon.find();
        console.log(arrayPokemonDB);
        res.render("pokemon", {
            arrayPokemon: arrayPokemonDB
        })
    } catch (error) {
        console.error(error)
    }
})

router.get('/crear', (req, res) => {
    res.render('crear')
})


router.post('/', async (req, res) => {
    const body = req.body //Gracias al body parser, de esta forma
    //podremos recuperar todo lo que viene del body
    console.log(body) //Para comprobarlo por pantalla
    try {
        const pokemonDB = new Pokemon(body) //Creamos un nuevo Pokemon, gracias al modelo
        await pokemonDB.save() //Lo guardamos con .save(), gracias a Mongoose
        res.redirect('/pokemon') //Volvemos al listado
    } catch (error) {
        console.log('error', error)
    }
})



router.get('/:id', async (req, res) => { //El id vendrá por el GET (barra de direcciones)
    const id = req.params.id //Recordemos que en la plantilla "pokemon.ejs" le pusimos
    //a este campo pokemon.id, por eso lo llamados con params.id
    try {
        const pokemonDB = await Pokemon.findOne({ _id: id }) //_id porque así lo indica Mongo
        //Esta variable “Pokemon” está definida arriba con el “require”
        //Buscamos con Mongoose un único documento que coincida con el id indicado
        console.log(pokemonDB) //Para probarlo por consola
        res.render('detalle', { //Para mostrar el objeto en la vista "detalle", que tenemos que crear
            pokemon: pokemonDB,
            error: false
        })
    } catch (error) { //Si el id indicado no se encuentra
        console.log('Se ha producido un error', error)
        res.render('detalle', { //Mostraremos el error en la vista "detalle"
            error: true,
            mensaje: 'Pokemon no encontrado!'
        })
    }
})



module.exports = router;
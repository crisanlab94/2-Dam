const express = require('express');
const router = express.Router();

const Pokemon = require('../models/pokemon');

//  OBTENER TODOS LOS POKÉMON (GET /pokemon)
router.get('/', async (req, res) => {
    try {
        const arrayPokemonDB = await Pokemon.find();
        // Devolvemos JSON para que Angular lo reciba bien
        res.json(arrayPokemonDB);
    } catch (error) {
        console.error("Error en GET:", error);
        res.status(500).json({ mensaje: 'Error al obtener datos' });
    }
});

// CREAR UN POKÉMON (POST /pokemon)
router.post('/', async (req, res) => {
    const body = req.body;
    try {
        const pokemonDB = new Pokemon(body);
        await pokemonDB.save();
        res.status(201).json(pokemonDB);
    } catch (error) {
        console.log('Error en POST:', error);
        res.status(400).json({ mensaje: 'Error al guardar' });
    }
});

// ELIMINAR Y ACTUALIZAR 
router.delete('/:id', async (req, res) => {
    try {
        // Eliminamos el Pokémon 
        const pokemonDB = await Pokemon.findByIdAndDelete(req.params.id);

        // Comprobamos cuántos quedan en la colección 
        const contador = await Pokemon.countDocuments();

        // Si no queda ninguno, ejecutamos la regla especial de reinicio 
        if (contador === 0) {
            console.log("Pokedex vacía. Reiniciando contador a 001...");

        }

        res.json({
            estado: !!pokemonDB,
            totalRestante: contador
        });
    } catch (error) {
        res.status(500).send(error);
    }
});

router.put('/:id', async (req, res) => {
    try {
        const pokemonDB = await Pokemon.findByIdAndUpdate(req.params.id, req.body, { new: true });
        res.json({ estado: true, pokemon: pokemonDB });
    } catch (error) {
        res.status(500).send(error);
    }
});

module.exports = router;
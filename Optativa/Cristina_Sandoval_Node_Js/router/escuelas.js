const express = require('express')
const router = express.Router();

router.get('/', (req, res) => {
    res.render("escuelas", {
        arrayEscuelas: [
            { id: 'es01', nombre: 'Escuela 1', Ubicacion: 'sevilla', descripcion: 'Es una escuela montesori' },
            { id: 'es02', nombre: 'Escuela 2', Ubicacion: 'Huelva', descripcion: 'Es una escuela montesori' },
            { id: 'es03', nombre: 'Escuela 3', Ubicacion: 'Malaga', descripcion: 'Es una escuela pública' },
            { id: 'es04', nombre: 'Escuela 4', Ubicacion: 'Jaen', descripcion: 'Es una escuela pública' },
            { id: 'es05', nombre: 'Escuela 5', Ubicacion: 'Cordoba', descripcion: 'Es una escuela privada' },
            { id: 'es06', nombre: 'Escuela 6', Ubicacion: 'Granada', descripcion: 'Es una escuela privada' }
        ]
    })
})


module.exports = router;
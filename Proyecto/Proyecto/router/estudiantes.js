const express = require('express');
const router = express.Router();


const Estudiante = require('../models/estudiantes');


router.get('/', async (req, res) => {
    try {
        const arrayEstudianteDB = await Estudiante.find();

        res.render("estudiantes", {

            arrayEstudiantes: arrayEstudianteDB
        })
    } catch (error) {
        console.error(error)
    }
})


router.get('/crear', (req, res) => {
    res.render('crear')
})


router.post('/', async (req, res) => {
    const body = req.body
    try {
        const estudianteDB = new Estudiante(body)
        await estudianteDB.save()
        res.redirect('/estudiantes')
    } catch (error) {
        console.log('error', error)
    }
})


router.get('/:id', async (req, res) => {
    const id = req.params.id
    try {
        const estudianteDB = await Estudiante.findOne({ _id: id })

        res.render('detalle', {
            estudiante: estudianteDB,
            error: false
        })
    } catch (error) {
        console.log('Se ha producido un error', error)
        res.render('detalle', {
            error: true,
            mensaje: 'Estudiante no encontrado!'
        })
    }
})


router.delete('/:id', async (req, res) => {
    const id = req.params.id;
    try {

        const estudianteDB = await Estudiante.findByIdAndDelete({ _id: id });

        if (!estudianteDB) {
            res.json({
                estado: false,
                mensaje: 'No se puede eliminar el Estudiante.'
            })
        } else {
            res.json({
                estado: true,
                mensaje: 'Estudiante eliminado.'
            })
        }
    } catch (error) {
        console.log(error)
        res.json({ estado: false, mensaje: 'Error al eliminar' })
    }
})

// 6. RUTA EDITAR (PUT)
router.put('/:id', async (req, res) => {
    const id = req.params.id;
    const body = req.body;

    try {
        const estudianteDB = await Estudiante.findByIdAndUpdate(
            id, body, { useFindAndModify: false }
        )

        res.json({
            estado: true,
            mensaje: 'Estudiante editado'
        })
    } catch (error) {
        console.log(error)
        res.json({
            estado: false,
            mensaje: 'Problema al editar el Estudiante'
        })
    }
})

module.exports = router;
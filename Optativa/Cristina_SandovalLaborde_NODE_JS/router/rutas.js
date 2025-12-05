const express = require('express');
const path = require('path');
const router = express.Router();

// Ruta principal (index.ejs)
router.get('/', (req, res) => {
    res.render("index", { titulo: "Prueba Evaluable" });
});

// Contacto -> HTML plano
router.get('/contacto', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'html', 'contacto.html'));
});

module.exports = router;

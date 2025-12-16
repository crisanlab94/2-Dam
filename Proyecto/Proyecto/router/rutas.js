var express = require('express');
var router = express.Router();

router.get('/', (req, res) => {
    res.render("index", { titulo: "mi titulo dinámico" })
})

router.get('/contacto', (req, res) => {
    res.render("contacto", { tituloContacto: "Estamos en contacto de manera dinámica!!" })
})
// Por último, vamos a exportarlo:
module.exports = router;
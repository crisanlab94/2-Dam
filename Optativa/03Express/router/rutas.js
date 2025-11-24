var express = require('express');
var router = express.Router();

/*
//middleware that is specific to this router
router.use(function timeLog(req, res, next) {
    console.log('Time: ' , Date.now());
});

//define the home page route
router.get('/', function(req, res){
    res.send('Birds home page');
});
//define the about route
router.get('about', function(req, res){
    res.send('About birds');
});
*/

// Ahora, CORTAMOS del fichero principal 01-express.js
// las dos rutas que tenemos: la principal ( / ) y la 
// de contactos ( /contaco )
// Importante que ya no usaremos el app.get(...), ahora
//vamos a utilizar las rutas, por lo que deberemos poner:
router.get('/', (req, res) => {
    res.render("index", { titulo: "mi titulo dinámico" })
})

router.get('/contacto', (req, res) => {
    res.render("contacto", { tituloContacto: "Estamos en contacto de manera dinámica!!" })
})
// Por último, vamos a exportarlo:
module.exports = router;
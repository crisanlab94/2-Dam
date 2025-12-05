const express = require('express')
const router = express.Router();

router.get('/', (req, res) => {
    res.render("peques", {
        arrayPeques: [
            { id: 'p01', nombre: 'Maria', Escuela: 'Escuela 1', edad: '4 años' },
            { id: 'p02', nombre: 'Juan', Escuela: 'Escuela 2', edad: '3 años' },
            { id: 'p03', nombre: 'Ana', Escuela: 'Escuela 3', edad: '5 años' },
            { id: 'p04', nombre: 'Laura', Escuela: 'Escuela 4', edad: '3 años' },
            { id: 'p05', nombre: 'Daniel', Escuela: 'Escuela 5', edad: '4 años' },
            { id: 'p06', nombre: 'Antonio', Escuela: 'Escuela 6', edad: '4 años' },
            { id: 'p07', nombre: 'Adrian', Escuela: 'Escuela 1', edad: '5 años' },
            { id: 'p08', nombre: 'Cristina', Escuela: 'Escuela 3', edad: '4 años' },
            { id: 'p09', nombre: 'Carlos', Escuela: 'Escuela 2', edad: '5 años' },
            { id: 'p10', nombre: 'Lidia', Escuela: 'Escuela 4', edad: '4 años' },
            { id: 'p11', nombre: 'Enrique', Escuela: 'Escuela 5', edad: '3 años' },
        ]
    })
})


module.exports = router;
const express = require('express');
const router = express.Router();

// En una arquitectura con Angular, el "/" suele devolver un mensaje de API 
// o simplemente no usarse, ya que Angular carga su propio index.html
router.get('/health', (req, res) => {
    res.json({ estado: true, mensaje: 'Servidor de StudyMatch funcionando correctamente' });
});

module.exports = router;
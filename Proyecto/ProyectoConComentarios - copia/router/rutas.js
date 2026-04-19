const express = require('express');
const router = express.Router();


router.get('/health', (req, res) => {
    res.json({ estado: true, mensaje: 'Servidor de StudyMatch funcionando correctamente' });
});

module.exports = router;
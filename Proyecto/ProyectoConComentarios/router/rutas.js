const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {
    res.render('index', { tituloWeb: 'StudyMatchKey - Inicio' });
});

module.exports = router;
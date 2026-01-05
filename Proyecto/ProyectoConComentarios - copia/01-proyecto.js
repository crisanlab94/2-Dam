require('dotenv').config();
const express = require('express');
const app = express();
const session = require('express-session');
const path = require('path');
const mongoose = require('mongoose');
const methodOverride = require('method-override');

// 1. CONEXIÓN A MONGODB ATLAS
const uri = `mongodb+srv://${process.env.USER}:${process.env.PASSWORD}@cristina.rxmgmup.mongodb.net/${process.env.DBNAME}?retryWrites=true&w=majority`;

mongoose.connect(uri)
    .then(() => console.log('✅ Base de datos conectada'))
    .catch(e => console.log('❌ Error de conexión:', e));

// 2. MIDDLEWARES
app.use(express.urlencoded({ extended: true })); 
app.use(express.json());
app.use(methodOverride('_method'));

app.use(session({
    secret: 'StudyMatchKey_2025',
    resave: false,
    saveUninitialized: false
}));

app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// ESTÁTICOS: Esto es vital para que el CSS cargue siempre
app.use(express.static(path.join(__dirname, 'public')));

// Variables globales para evitar errores en las plantillas
app.use((req, res, next) => {
    res.locals.usuarioId = req.session.usuarioId || null;
    res.locals.rol = req.session.rol || null;
    next();
});

// 3. RUTAS
app.use('/', require('./router/rutas')); 
app.use('/', require('./router/estudiantes')); // Cargamos el router en la raíz

// 4. ERROR 404
app.use((req, res, next) => {
    res.status(404).render("404", {
        tituloWeb: "Página no encontrada"
    });
});

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => console.log(`🚀 Servidor en http://localhost:${PORT}`));
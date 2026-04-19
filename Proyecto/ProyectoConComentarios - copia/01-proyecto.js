require('dotenv').config();
const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
const session = require('express-session');
const path = require('path');
const methodOverride = require('method-override');

const app = express();

// --- 1. CONEXIÓN A MONGODB ATLAS ---
const uri = `mongodb+srv://${process.env.USER}:${process.env.PASSWORD}@cristina.rxmgmup.mongodb.net/${process.env.DBNAME}?retryWrites=true&w=majority`;

mongoose.connect(uri)
    .then(() => console.log('✅ MongoDB Atlas: Conectada con éxito'))
    .catch(e => console.log('❌ Error de conexión:', e));

// --- 2. CONFIGURACIÓN DE CORS (Crucial para Angular) ---
app.use(cors({
    origin: 'http://localhost:4200', // El puerto de tu Angular
    credentials: true,               // Permite el envío de cookies/sesiones
    methods: ['GET', 'POST', 'PUT', 'DELETE', 'OPTIONS'],
    allowedHeaders: ['Content-Type', 'Authorization']
}));

// --- 3. MIDDLEWARES DE PARSEO ---
app.use(express.json()); 
app.use(express.urlencoded({ extended: true })); 
app.use(methodOverride('_method'));

// --- 4. SESIONES ---
app.use(session({
    secret: process.env.SESSION_SECRET || 'StudyMatchKey_2026',
    resave: false,
    saveUninitialized: false,
    cookie: {
        secure: false, 
        httpOnly: true,
        sameSite: 'lax' 
    }
}));

// --- 5. MOTOR DE PLANTILLAS (Para las vistas que aún sean EJS) ---
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));
app.use(express.static(path.join(__dirname, 'public')));

// Variables globales para EJS
app.use((req, res, next) => {
    res.locals.usuarioId = req.session.usuarioId || null;
    res.locals.rol = req.session.rol || null;
    next();
});

// --- 6. RUTAS ---

app.use('/api', require('./router/rutas')); 
app.use('/api/estudiantes', require('./router/estudiantes'));

// --- 7. MANEJO DE ERRORES ---
app.use((req, res) => {
    res.status(404).json({
        estado: false,
        mensaje: "Recurso no encontrado (API 404)"
    });
});

// --- 8. SERVIDOR ---
const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
    console.log(`🚀 Servidor backend corriendo en: http://localhost:${PORT}`);
    console.log(`🌍 Aceptando peticiones desde: http://localhost:4200`);
});
const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
const path = require('path');
require('dotenv').config();

const app = express();

// --- CONFIGURACIÓN DE MIDDLEWARES ---
app.use(cors());

// Express ya incluye los parsers para JSON y formularios
app.use(express.urlencoded({ extended: false }));
app.use(express.json());

// Puerto del servidor (según la configuración de tu entorno)
const port = process.env.PORT || 4000;

// Configuración de motor de plantillas y archivos estáticos
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));
app.use(express.static(path.join(__dirname, 'public')));

// --- CONEXIÓN A MONGODB ---
const uri = `mongodb+srv://${process.env.USER}:${process.env.PASSWORD}@cristina.rxmgmup.mongodb.net/${process.env.DBNAME}?retryWrites=true&w=majority`;

mongoose.connect(uri)
  .then(() => console.log('Conectado a MongoDB en el cluster de Cristina'))
  .catch(e => console.log('Error de conexión: ', e));

// --- LLAMADAS A LAS RUTAS ---

app.use('/', require('./router/rutas'));
app.use('/pokemon', require('./router/pokemon'));

// --- RUTAS DE PRUEBA / VISTAS EJS ---
app.get('/pruebas', (req, res) => {
  res.render('pruebas', { titulo: 'mi titulo dinámico', Descripcion: 'Esto es una descripcion' });
});

app.get('/pruebas2', (req, res) => {
  res.render('pruebas2', { titulo: 'mi titulo dinámico 2', Descripcion: 'Esto es una descripcion 2' });
});

app.get('/contacto', (req, res) => {
  res.send('Estas en contactos');
});

// --- MANEJO DE ERROR 404 ---

app.use((req, res) => {
  res.status(404).sendFile(path.join(__dirname, "public", "html", "404.html"));
});

app.listen(port, () => {
  console.log(`Servidor de la Pokedex escuchando en el puerto ${port}`);
});
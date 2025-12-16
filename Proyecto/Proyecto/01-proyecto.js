const express = require('express');
const app = express();

require('dotenv').config();

const port = process.env.PORT || 3000;

// --- CONFIGURACIÓN DE DATOS (IMPORTANTE PARA EDITAR) ---
// Usamos el sistema nativo de Express (sin body-parser externo)
// extended: true permite objetos anidados y evita ciertos errores
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// --- VISTAS Y ESTÁTICOS ---
app.set('view engine', 'ejs');
// La carpeta views NO debe ser estática (publica), solo la public
// app.use('views', express.static(__dirname + '/views')); <--- ESTO SOBRABA
app.use(express.static(__dirname + '/public'));

// --- BASE DE DATOS ---
const mongoose = require('mongoose');
const uri = `mongodb+srv://${process.env.USER}:${process.env.PASSWORD}@cristina.rxmgmup.mongodb.net/${process.env.DBNAME}?retryWrites=true&w=majority`;

mongoose.connect(uri)
    .then(() => console.log('Conectado a MongoDB en el cluster de Cristina'))
    .catch(e => console.log('Error de conexión: ', e));

// --- RUTAS ---
app.use('/', require('./router/rutas'));
app.use('/estudiantes', require('./router/estudiantes'));

// --- 404 ---
app.use((req, res) => {
    res.status(404).sendFile(__dirname + "/public/html/404.html")
})

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})
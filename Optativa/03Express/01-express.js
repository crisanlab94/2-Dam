const express = require('express')
const bodyParser = require('body-parser')
const app = express()

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

require('dotenv').config()

const port = process.env.PORT || 3000

app.set('view engine', 'ejs')
app.use('views', express.static(__dirname + '/views'))
app.use(express.static(__dirname + '/public'));

//Llamadas a las rutas:
app.use('/', require('./router/rutas'));
app.use('/pokemon', require('./router/pokemon'));

const mongoose = require('mongoose');

// --- TUS VARIABLES (Credenciales) ---
// Asegúrate de que este usuario y contraseña son los correctos para entrar en ese cluster


// --- LA URL ADAPTADA ---
// Fíjate que aquí usamos la dirección 'cristina.rxmgmup.mongodb.net' que pediste
const uri = `mongodb+srv://${process.env.USER}:${process.env.PASSWORD}@cristina.rxmgmup.mongodb.net/${process.env.DBNAME}?retryWrites=true&w=majority`;

// --- CONEXIÓN ---
mongoose.connect(uri)
  .then(() => console.log('Conectado a MongoDB en el cluster de Cristina '))
  .catch(e => console.log('Error de conexión: ', e));

app.get('/pruebas', (req, res) => {
  //console.log(__dirname) //ruta donde estamos
  //res.send('Ya somos unos crack en Node+Express')
  //pasa un json con un titulo
  res.render('pruebas', { titulo: 'mi titulo dinámico', Descripcion: 'Esto es una descripcion' })
})

app.get('/pruebas2', (req, res) => {
  //console.log(__dirname) //ruta donde estamos
  //res.send('Ya somos unos crack en Node+Express')
  //pasa un json con un titulo
  res.render('pruebas2', { titulo: 'mi titulo dinámico 2', Descripcion: 'Esto es una descripcion 2' })
})

app.get('/contacto', (req, res) => {
  console.log(__dirname + '/public')
  res.send('Estas en contactos')
})


app.use((req, res) => {
  res.status(404).sendFile(__dirname + "/public/html/404.html")
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})


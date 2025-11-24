const express = require('express')
const app = express()
const port = 3000

app.set('view engine', 'ejs')
app.use('views', express.static(__dirname + '/views'))
app.use(express.static(__dirname + '/public'));

//Llamadas a las rutas:
app.use('/', require('./router/rutas'));
app.use('/pokemon', require('./router/pokemon'));

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


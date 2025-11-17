const express = require('express')
const app = express()
const port = 3000

app.use(express.static(__dirname +'/public'));

app.get('/', (req, res) => {
  //console.log(__dirname) //ruta donde estamos
  res.send('Ya somos unos crack en Node+Express')
})

app.get('/contacto', (req, res) => {
  console.log(__dirname + '/public')
  res.send('Estas en contactos')
})


app.use((req,res) => {
  res.status(404).sendFile(__dirname + "/public/html/404.html")
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})


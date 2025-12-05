const express = require('express');
const path = require('path');
const app = express();
const port = 3000;

// Motor EJS
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Archivos estáticos
app.use(express.static(path.join(__dirname, 'public')));

// Rutas externas
app.use('/', require('./router/rutas'));
app.use('/escuelas', require('./router/escuelas'));
app.use('/peques', require('./router/peques'));

// HTML plano
app.get('/product', (req, res) => {
  res.sendFile(path.join(__dirname, 'html', 'contacto.html'));
});


app.use((req, res) => {
  res.status(404).sendFile(path.join(__dirname, 'html', 'quieneSomos.html'));
});

app.use((req, res) => {
  res.status(404).sendFile(path.join(__dirname, 'html', 'nuestrasEscuelas.html'));
});

app.listen(port, () => {
  console.log(`Servidor funcionando en http://localhost:${port}`);
});

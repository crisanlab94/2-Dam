var http = require('http').createServer(webServer),
    form = require('fs').readFileSync('form.html'),
    querystring = require('querystring')
util = require('util'),
    dataString = ''
    ;

function webServer(req, res) {
    if (req.method == 'GET') {
        res.writeHead(200, { 'Content-Type': 'text/html' })
        res.end(form)
    }

    if (req.method == 'POST') {
        req
            .on('data', function (data) { //Mientras haya datos, ejecutaremos la siguiente Callback
                dataString += data //Que concatenará el dato en la variable dataString
            })
            .on('end', function () { //Cuando terminen los datos, ejecutarermos la siguiente Callback
                //Declaramos una variable de texto
                //Texto concatenado con el valor de la variable ${dataString}
                var dataObjetc = querystring.parse(dataString),
                dataJSON=util.inspect(dataObjetc),
                templateString= `Los datos que enviaste por Post como string son ${dataString}
                Los datos que enviaste por Post como JSON son ${dataJSON}
                `
                
                console.log(templateString) //Lo mostramos en el terminal
                res.end(templateString) //Es lo que enviará al navegador web
            })
    }



}

http.listen(3000)

console.log("Servidor corriendo en http://localhost:3000/")



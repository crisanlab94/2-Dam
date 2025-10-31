var EventEmitter = require('events').EventEmitter,
    pub=new EventEmitter()
pub
//con .on se repetirá X veces
    .on('myevent',function (message) {
        console.log(message)
    })
//.once solo 1 vez
    .once('myevent', function (message) {
        console.log('Se emite la primera vez: ' + message)
    })

//emitir el evento
pub.emit('myevent', 'Soy un emisor de eventos')
pub.emit('myevent', 'Volviendo a emitir')

pub.emit('myevent', 'Volviendo a emitir por tercera vez')
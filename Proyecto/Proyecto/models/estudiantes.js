const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const estudianteSchema = new Schema({
    id_entidad: Schema.Types.ObjectId,
    nombre: String,
    fecha_nacimiento: Date,
    email: String,
    clave: String,
    fecha_registro: {
        type: Date,
        default: Date.now
    }
})

//Creamos el modelo
const Estudiante = mongoose.model('estudiante', estudianteSchema, "estudiante");

module.exports = Estudiante;
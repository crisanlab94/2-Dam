const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const estudianteSchema = new Schema({
    id_estudiante: { type: String }, 
    nombre: String,
    fecha_nacimiento: Date,
    email: { type: String, unique: true },
    telefono: { type: String, unique: true },
    tipo_entidad: String,
    modalidad: String,
    curso: String,
    asignaturas: [String],
    clave: String,

    plan_estudio: [{
        nombre_asignatura: String,
        metodo_sugerido: String,
        justificacion: String
    }],

    tareas: [{
        titulo: String,
        tipo: String, 
        fecha: Date,
        hora: String,
        completada: { type: Boolean, default: false },
        mensaje_personalizado: { type: String, default: "" }
    }],

    archivos: [{
        nombre_archivo: String,
        tipo_de_archivo: String,
        ruta_almacenamiento: String,
        fecha_subida: { type: Date, default: Date.now }
    }],

    // CAMBIO: Campo necesario para que aparezcan en el perfil
    notificaciones: [{
        tipo: String, // 'examen', 'entrega', 'deberes'
        texto: String,
        fecha: { type: Date, default: Date.now }
    }],

    fecha_registro: { type: Date, default: Date.now }
});

module.exports = mongoose.model('estudiante', estudianteSchema, "estudiante");
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const estudianteSchema = new Schema({
    // Identificador personalizado (001, 002...)
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

    // Resultado de cuestionarios
    plan_estudio: [{
        nombre_asignatura: String,
        metodo_sugerido: String,
        justificacion: String
    }],

    // CRUD de tareas y calendario (Tiene notificaciones personalizadas  mensaje_personalizado)
    tareas: [{
        titulo: String,
        tipo: String, // examen, entrega, deberes, aviso
        fecha: Date,
        hora: String,
        completada: { type: Boolean, default: false },
        mensaje_personalizado: { type: String, default: "" }
    }],

    // Gestión de materiales
    archivos: [{
        nombre_archivo: String,      // Nombre original del archivo
        tipo_de_archivo: String,     // Tipo (PDF, JPG, etc.)
        ruta_almacenamiento: String, // Nombre generado por Multer
        fecha_subida: { 
            type: Date, 
            default: Date.now 
        }
    }],

    fecha_registro: { type: Date, default: Date.now }
});

module.exports = mongoose.model('estudiante', estudianteSchema, "estudiante");
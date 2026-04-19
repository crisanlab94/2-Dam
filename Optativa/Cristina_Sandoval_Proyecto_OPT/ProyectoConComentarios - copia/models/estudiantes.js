const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const estudianteSchema = new Schema({
    // ID incremental gestionado por el contador (001, 002...)
    id_estudiante: { type: String },

    // Datos Personales y de Contacto
    nombre: { type: String, required: true },
    email: { type: String, unique: true, required: true },
    telefono: { type: String, unique: true },
    fecha_nacimiento: { type: Date },
    clave: { type: String, required: true },

    // Información de la Institución (Campos añadidos)
    tipo_entidad: { type: String },      // Instituto, Universidad, etc.
    nombre_entidad: { type: String },    // Ej: IES Velázquez
    ubicacion: { type: String },         // Ej: Sevilla, España

    // Información Académica
    modalidad: { type: String },         // Ciencias, Humanidades, etc.
    curso: { type: String },             // 1º Bachillerato, 3º ESO, etc.
    asignaturas: [String],               // Lista de nombres de asignaturas

    // Resultado de cuestionarios IA
    plan_estudio: [{
        nombre_asignatura: String,
        metodo_sugerido: String,
        justificacion: String
    }],

    // CRUD de tareas y calendario
    tareas: [{
        titulo: String,
        tipo: String, // examen, entrega, deberes, aviso
        fecha: Date,
        hora: String,
        completada: { type: Boolean, default: false },
        mensaje_personalizado: { type: String, default: "" }
    }],

    // Gestión de Materiales (Subidos por el alumno)
    archivos: [{
        nombre_archivo: String,      // Nombre original
        tipo_de_archivo: String,     // Mimetype (image/png, application/pdf)
        ruta_almacenamiento: String, // Nombre generado por Multer
        fecha_subida: {
            type: Date,
            default: Date.now
        }
    }],
    foto: { type: String, default: "" },
    fecha_registro: { type: Date, default: Date.now }
});

// Exportamos el modelo asegurando que use la colección "estudiante"
module.exports = mongoose.model('estudiante', estudianteSchema, "estudiante");
const express = require('express');
const router = express.Router();
const Estudiante = require('../models/estudiantes');
const Contador = require('../models/contador');
const multer = require('multer');
const path = require('path');
const fs = require('fs');
const pdf = require('pdf-parse'); 
const axios = require('axios');
const { OpenAI } = require('openai');

// CONFIGURACIÓN DE SEGURIDAD (Variables de Entorno)
require('dotenv').config();

const openai = new OpenAI({
    apiKey: process.env.OPENAI_API_KEY, 
});


// CONFIGURACIÓN DE MULTER (MATERIALES)

const storage = multer.diskStorage({
    destination: (req, file, cb) => {
        const dir = 'public/uploads/materiales';
        if (!fs.existsSync(dir)){
            fs.mkdirSync(dir, { recursive: true });
        }
        cb(null, dir);
    },
    filename: (req, file, cb) => {
        const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1E9);
        cb(null, uniqueSuffix + '-' + file.originalname);
    }
});

const upload = multer({ 
    storage: storage,
    limits: { fileSize: 10 * 1024 * 1024 } 
});

// Middleware de seguridad
const isAuthenticated = (req, res, next) => {
    if (req.session.usuarioId || req.session.rol === 'admin') return next();
    res.redirect('/estudiantes/login'); 
};

// Calcula el progreso de tareas de la semana actual
 
function obtenerProgresoSemanal(tareas) {
    if (!tareas || tareas.length === 0) return 0;
    const hoy = new Date();
    const lunes = new Date(hoy);
    const diaSemana = hoy.getDay(); 
    const diferencia = hoy.getDate() - diaSemana + (diaSemana === 0 ? -6 : 1);
    lunes.setDate(diferencia);
    lunes.setHours(0, 0, 0, 0);
    const domingo = new Date(lunes);
    domingo.setDate(lunes.getDate() + 6);
    domingo.setHours(23, 59, 59, 999);

    const tareasSemana = tareas.filter(t => {
        const fechaTarea = new Date(t.fecha);
        return fechaTarea >= lunes && fechaTarea <= domingo;
    });

    if (tareasSemana.length === 0) return 0;
    const completadas = tareasSemana.filter(t => t.completada === true).length;
    return Math.round((completadas / tareasSemana.length) * 100);
}

// Administración

router.get('/estudiantes/login-admin', (req, res) => {
    res.render('loginAdmin', { mensaje: null, tituloWeb: 'Acceso Administrador' });
});

router.post('/estudiantes/login-admin', async (req, res) => {
    const { email, clave } = req.body;
    if (email === 'admin@gmail.com' && clave === 'Admin31@') {
        req.session.rol = 'admin';
        req.session.usuarioId = 'ADMIN_SESSION';
        return res.redirect('/estudiantes'); 
    } else {
        res.render('loginAdmin', { 
            mensaje: 'Credenciales de administrador incorrectas', 
            tituloWeb: 'Acceso Administrador' 
        });
    }
});

router.get('/estudiantes', isAuthenticated, async (req, res) => {
    try {
        if (req.session.rol !== 'admin') return res.redirect('/estudiantes/login');
        const arrayEstudiantes = await Estudiante.find();
        res.render('estudiantes', { arrayEstudiantes, tituloWeb: 'Gestión de Estudiantes' });
    } catch (error) { 
        res.status(500).send('Error al cargar la tabla de gestión'); 
    }
});

router.get('/estudiantes/editar/:id', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findOne({ id_estudiante: req.params.id });
        if (estudiante) {
            res.render('detalle', { estudiante, error: false, tituloWeb: 'Detalle' });
        } else {
            res.render('detalle', { error: true, mensaje: 'Estudiante no encontrado', tituloWeb: 'Error' });
        }
    } catch (error) { 
        res.render('detalle', { error: true, mensaje: 'ID no válido', tituloWeb: 'Error' }); 
    }
});

router.put('/estudiantes/:id', isAuthenticated, async (req, res) => {
    try {
        await Estudiante.findOneAndUpdate({ id_estudiante: req.params.id }, req.body);
        res.json({ estado: true });
    } catch (error) { res.json({ estado: false }); }
});

router.delete('/estudiantes/:id', isAuthenticated, async (req, res) => {
    try {
        await Estudiante.findOneAndDelete({ id_estudiante: req.params.id });
        const total = await Estudiante.countDocuments();
        if (total === 0) await Contador.findOneAndUpdate({ id: 'estudianteId' }, { seq: 0 });
        res.json({ estado: true });
    } catch (error) { res.json({ estado: false }); }
});

// Estudiantes

router.get('/estudiantes/login', (req, res) => {
    res.render('login', { mensaje: null, tituloWeb: 'Login' });
});

router.post('/estudiantes/login', async (req, res) => {
    const { email, clave } = req.body;
    try {
        const user = await Estudiante.findOne({ email, clave }); 
        if (user) {
            req.session.usuarioId = user._id;
            res.redirect('/estudiantes/mi-perfil/ver'); 
        } else { 
            res.render('login', { mensaje: 'Datos incorrectos', tituloWeb: 'Login' }); 
        }
    } catch (e) { res.render('login', { mensaje: 'Error', tituloWeb: 'Login' }); }
});

router.get('/estudiantes/crear', (req, res) => {
    res.render('crear', { tituloWeb: 'Registro' });
});

router.post('/estudiantes', async (req, res) => {
    try {
        const contador = await Contador.findOneAndUpdate({ id: 'estudianteId' }, { $inc: { seq: 1 } }, { new: true, upsert: true });
        const idFormateado = contador.seq.toString().padStart(3, '0');
        const nuevoEstudiante = new Estudiante({ ...req.body, id_estudiante: idFormateado }); 
        await nuevoEstudiante.save();
        res.json({ estado: true });
    } catch (error) { res.json({ estado: false }); }
});

router.get('/estudiantes/mi-perfil/ver', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        const progreso = obtenerProgresoSemanal(estudiante.tareas);
        const hora = new Date().getHours();
        let saludo = (hora >= 6 && hora < 12) ? "¡Buenos días" : (hora >= 12 && hora < 20) ? "¡Buenas tardes" : "¡Buenas noches";
        const hoy = new Date();
        hoy.setHours(0, 0, 0, 0);
        const notificaciones = [];

        if (estudiante.tareas && estudiante.tareas.length > 0) {
            estudiante.tareas.forEach(tarea => {
                const fechaTarea = new Date(tarea.fecha);
                fechaTarea.setHours(0, 0, 0, 0);
                const diffTime = fechaTarea.getTime() - hoy.getTime();
                const diffDays = Math.round(diffTime / (1000 * 60 * 60 * 24));
                if (diffDays > 0 && diffDays <= 3) {
                    const textoDias = diffDays === 1 ? '1 día' : `${diffDays} días`;
                    const tituloUpp = tarea.titulo.toUpperCase();
                    let mensajeFinal = "";
                    if (tarea.mensaje_personalizado && tarea.mensaje_personalizado.trim() !== "") {
                        mensajeFinal = `${tituloUpp}: ${tarea.mensaje_personalizado} (Faltan ${textoDias})`;
                    } else {
                        if (tarea.tipo === 'examen') mensajeFinal = `Recuerda, en ${textoDias} tienes examen de "${tituloUpp}".`;
                        else if (tarea.tipo === 'entrega' && !tarea.completada) mensajeFinal = `En ${textoDias} se entrega "${tituloUpp}".`;
                        else if (tarea.tipo === 'deberes' && !tarea.completada) mensajeFinal = `Deberes de "${tituloUpp}" en ${textoDias}.`;
                        else if (tarea.tipo === 'aviso') mensajeFinal = `Recordatorio: ${tituloUpp} en ${textoDias}.`;
                    }
                    if (mensajeFinal !== "") notificaciones.push({ texto: mensajeFinal, tipo: tarea.tipo });
                }
            });
        }
        const frases = ["La constancia vence a la inteligencia.", "Sigue adelante.", "Tu esfuerzo valdrá la pena."];
        const fraseMotivacional = frases[Math.floor(Math.random() * frases.length)];
        res.render('perfil', { estudiante, progreso, saludo, fraseMotivacional, notificaciones, tituloWeb: 'Mi Panel' });
    } catch (error) { res.redirect('/estudiantes/login'); }
});

// Test y métodos

router.get('/estudiantes/mi-perfil/test', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        res.render('test', { estudiante, tituloWeb: 'Test de Estudio' });
    } catch (error) { res.redirect('/estudiantes/mi-perfil/ver'); }
});

router.put('/estudiantes/guardar-test/:id', isAuthenticated, async (req, res) => {
    try {
        const { asignatura, metodo, apoyos, razon } = req.body;
        await Estudiante.findByIdAndUpdate(req.params.id, {
            $push: { plan_estudio: { nombre_asignatura: asignatura, metodo_sugerido: metodo + (apoyos ? " + " + apoyos : ""), justificacion: razon }}
        }); 
        res.json({ estado: true });
    } catch (e) { res.json({ estado: false }); }
});

router.get('/estudiantes/mi-perfil/mis-metodos', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        res.render('misMetodos', { estudiante, tituloWeb: 'Mis Métodos' });
    } catch (error) { res.redirect('/estudiantes/mi-perfil/ver'); }
});

router.delete('/estudiantes/eliminar-metodo/:metodoId', isAuthenticated, async (req, res) => {
    try {
        const metodoId = req.params.metodoId;
        const usuarioId = req.session.usuarioId; // ID del estudiante logueado

        const estudianteActualizado = await Estudiante.findByIdAndUpdate(
            usuarioId,
            { $pull: { plan_estudio: { _id: metodoId } } }, // Sacamos el método del array
            { new: true }
        );

        if (estudianteActualizado) {
            res.json({ estado: true, mensaje: 'Método eliminado correctamente' });
        } else {
            res.json({ estado: false, mensaje: 'No se pudo encontrar el registro' });
        }
    } catch (error) {
        console.error(error);
        res.json({ estado: false, mensaje: 'Error al eliminar el método' });
    }
});

// Calendario

router.get('/estudiantes/mi-perfil/calendario', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        res.render('calendario', { estudiante, progreso: obtenerProgresoSemanal(estudiante.tareas), tituloWeb: 'Calendario' });
    } catch (error) { res.redirect('/estudiantes/mi-perfil/ver'); }
});

router.post('/estudiantes/aniadir-tarea/:id', isAuthenticated, async (req, res) => {
    try {
        await Estudiante.findByIdAndUpdate(req.params.id, { $push: { tareas: { ...req.body, completada: false } } });
        res.json({ estado: true });
    } catch (e) { res.json({ estado: false }); }
});

router.put('/estudiantes/toggle-tarea/:tareaId', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        const tarea = estudiante.tareas.id(req.params.tareaId);
        await Estudiante.updateOne({ _id: req.session.usuarioId, "tareas._id": req.params.tareaId }, { $set: { "tareas.$.completada": !tarea.completada } });
        res.json({ estado: true });
    } catch (e) { res.json({ estado: false }); }
});

router.get('/estudiantes/mi-perfil/mis-eventos', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        res.render('misEventos', { estudiante, tituloWeb: 'Mis Eventos' });
    } catch (error) { res.redirect('/estudiantes/mi-perfil/ver'); }
});
// ACTUALIZAR UNA TAREA ESPECÍFICA
router.put('/estudiantes/editar-tarea/:tareaId', isAuthenticated, async (req, res) => {
    try {
        const tareaId = req.params.tareaId;
        const usuarioId = req.session.usuarioId; 
        const { titulo, mensaje_personalizado, fecha, hora } = req.body;

        
        const resultado = await Estudiante.updateOne(
            { _id: usuarioId, "tareas._id": tareaId },
            { 
                $set: { 
                    "tareas.$.titulo": titulo,
                    "tareas.$.mensaje_personalizado": mensaje_personalizado,
                    "tareas.$.fecha": new Date(fecha),
                    "tareas.$.hora": hora
                } 
            }
        );

        if (resultado.modifiedCount > 0) {
            res.json({ estado: true, mensaje: 'Evento actualizado con éxito' });
        } else {
            res.json({ estado: false, mensaje: 'No se encontraron cambios para aplicar' });
        }
    } catch (error) {
        console.error("Error al actualizar tarea:", error);
        res.status(500).json({ estado: false, mensaje: 'Error interno del servidor' });
    }
});
router.delete('/estudiantes/eliminar-tarea/:tareaId', isAuthenticated, async (req, res) => {
    try {
        const tareaId = req.params.tareaId;
        const usuarioId = req.session.usuarioId;

      
        const estudianteActualizado = await Estudiante.findByIdAndUpdate(
            usuarioId,
            { $pull: { tareas: { _id: tareaId } } },
            { new: true }
        );

        if (estudianteActualizado) {
            res.json({ estado: true, mensaje: 'Evento eliminado' });
        } else {
            res.json({ estado: false, mensaje: 'No se encontró el estudiante' });
        }
    } catch (error) {
        console.error(error);
        res.json({ estado: false, mensaje: 'Error en el servidor' });
    }
});

// Gestión de materiales


router.get('/estudiantes/mi-perfil/misMateriales', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        res.render('misMateriales', { estudiante, tituloWeb: 'Mis Materiales' });
    } catch (error) { res.redirect('/estudiantes/mi-perfil/ver'); }
});

router.post('/estudiantes/subir-material', isAuthenticated, upload.single('archivoMaterial'), async (req, res) => {
    try {
        if (!req.file) return res.json({ estado: false });
        const nuevoMat = { nombre_archivo: req.file.originalname, tipo_de_archivo: req.file.mimetype, ruta_almacenamiento: req.file.filename };
        await Estudiante.findByIdAndUpdate(req.session.usuarioId, { $push: { archivos: nuevoMat } });
        res.json({ estado: true });
    } catch (error) { res.json({ estado: false }); }
});

router.delete('/estudiantes/eliminar-material/:id', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        const archivo = estudiante.archivos.id(req.params.id);
        if (archivo) {
            const rutaFisica = path.join(__dirname, '../public/uploads/materiales', archivo.ruta_almacenamiento);
            if (fs.existsSync(rutaFisica)) fs.unlinkSync(rutaFisica);
            await Estudiante.findByIdAndUpdate(req.session.usuarioId, { $pull: { archivos: { _id: req.params.id } } });
            res.json({ estado: true });
        } else res.json({ estado: false });
    } catch (error) { res.json({ estado: false }); }
});


// Asistente IA


router.get('/estudiantes/mi-perfil/asistente', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        res.render('crearRecurso', { estudiante, tituloWeb: 'Asistente IA' });
    } catch (error) { res.redirect('/estudiantes/mi-perfil/ver'); }
});

// 1. Iniciar guia IA: IA pregunta por materiales y dicta solo el Paso 1
router.post('/estudiantes/ia/iniciar-guia', isAuthenticated, async (req, res) => {
    const { asignaturaId, tema } = req.body;
    let nombreEst = "Estudiante";
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        nombreEst = estudiante.nombre;
        const plan = estudiante.plan_estudio.id(asignaturaId);
        const metodo = plan ? plan.metodo_sugerido : "General";
        const listaArchivos = estudiante.archivos.map(a => a.nombre_archivo).join(", ");

        const promptSystem = `Eres un tutor pedagógico minimalista.
        Alumno: ${nombreEst}. Tema: "${tema}". Método: "${metodo}".
        REGLAS:
        1. Saluda brevemente.
        2. Menciona sus archivos: [${listaArchivos || 'No tiene'}]. Pregunta si usará alguno.
        3. Explica SOLAMENTE el Paso 1 del método "${metodo}" para este tema. No des más pasos.
        4. Sé muy breve y directo.`;

        const completion = await openai.chat.completions.create({
            model: "gpt-4o-mini",
            messages: [{ role: "system", content: promptSystem }, { role: "user", content: "Empieza." }]
        });
        res.json({ estado: true, mensaje: completion.choices[0].message.content, metodoUsado: metodo });
    } catch (error) {
        res.json({ estado: false });
    }
});

// 2. Generar contenido del recurso creado por el alumno
router.post('/estudiantes/ia/generar-paso', isAuthenticated, async (req, res) => {
    const { textoAlumno, paso, tema, metodo, nombreArchivo } = req.body;
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        let contextoPDF = "";
        if (nombreArchivo) {
            const archivo = estudiante.archivos.find(a => a.nombre_archivo === nombreArchivo);
            if (archivo) {
                const ruta = path.join(__dirname, '../public/uploads/materiales', archivo.ruta_almacenamiento);
                const dataBuffer = fs.readFileSync(ruta);
                const data = await pdf(dataBuffer);
                contextoPDF = `\n[APUNTES: ${data.text.substring(0, 1000)}]`;
            }
        }
        const prompt = `Actúa como redactor profesional. Estamos en el PASO ${paso} de "${metodo}" sobre "${tema}".
        El alumno aporta: "${textoAlumno}". ${contextoPDF}
        Redacta el contenido definitivo para este paso. Solo devuelve el contenido redactado, nada más.`;

        const completion = await openai.chat.completions.create({
            model: "gpt-4o-mini",
            messages: [{ role: "system", content: prompt }]
        });
        res.json({ estado: true, contenido: completion.choices[0].message.content });
    } catch (error) { res.json({ estado: false }); }
});

// 3. chat de guia: El diálogo que no interfiere con la creación, solo ayuda en caso de que sea necesario
router.post('/estudiantes/ia/chat', isAuthenticated, async (req, res) => {
    const { mensajeAlumno, asignaturaId, tema, pasoActual } = req.body;
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        const plan = estudiante.plan_estudio.id(asignaturaId);
        const metodo = plan ? plan.metodo_sugerido : "General";
        const prompt = `Tutor de ${estudiante.nombre}. Estamos en Paso ${pasoActual} de ${metodo}.
        Responde en máximo 2 frases. Guía al alumno para que escriba en su zona de trabajo.`;

        const completion = await openai.chat.completions.create({
            model: "gpt-4o-mini",
            messages: [{ role: "system", content: prompt }, { role: "user", content: mensajeAlumno }]
        });
        res.json({ estado: true, mensaje: completion.choices[0].message.content });
    } catch (error) { res.json({ estado: false }); }
});

// 4. Guardar recurso creado
router.post('/estudiantes/ia/guardar-final', isAuthenticated, async (req, res) => {
    const { titulo, contenidoTotal } = req.body;
    try {
        const nombreFisico = `Recurso_IA_${Date.now()}.txt`;
        const ruta = path.join(__dirname, '../public/uploads/materiales', nombreFisico);
        fs.writeFileSync(ruta, contenidoTotal);
        await Estudiante.findByIdAndUpdate(req.session.usuarioId, {
            $push: { archivos: {
                nombre_archivo: titulo + " (Final IA)",
                tipo_de_archivo: "text/plain",
                ruta_almacenamiento: nombreFisico
            }}
        });
        res.json({ estado: true });
    } catch (error) { res.json({ estado: false }); }
});

router.get('/logout', (req, res) => { req.session.destroy(() => res.redirect('/')); });

module.exports = router;
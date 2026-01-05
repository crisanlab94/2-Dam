const express = require('express');
const router = express.Router();
const Estudiante = require('../models/estudiantes');
const Contador = require('../models/contador');
const multer = require('multer');
const path = require('path');
const fs = require('fs');
const pdf = require('pdf-parse'); 
const axios = require('axios');

// ==========================================
// CONFIGURACIÓN DE MULTER
// ==========================================
const storage = multer.diskStorage({
    destination: (req, file, cb) => { cb(null, 'public/uploads/materiales'); },
    filename: (req, file, cb) => {
        const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1E9);
        cb(null, uniqueSuffix + '-' + file.originalname);
    }
});

const upload = multer({ 
    storage: storage,
    limits: { fileSize: 10 * 1024 * 1024 } 
});

// ==========================================
// MIDDLEWARES Y HELPERS
// ==========================================
const isAuthenticated = (req, res, next) => {
    if (req.session.usuarioId || req.session.rol === 'admin') return next();
    res.redirect('/estudiantes/login'); 
};

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

// ==========================================
// RUTAS DE LOGIN Y PERFIL (CON NOTIFICACIONES)
// ==========================================

router.get('/estudiantes/mi-perfil/ver', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        const progreso = obtenerProgresoSemanal(estudiante.tareas);
        
        const hora = new Date().getHours();
        let saludo = (hora >= 6 && hora < 12) ? "¡Buenos días" : (hora >= 12 && hora < 20) ? "¡Buenas tardes" : "¡Buenas noches";
        const frases = ["Cree en ti mism@.", "El éxito es la suma de pequeños esfuerzos."];

        // LÓGICA DE NOTIFICACIONES (SISTEMA + PERSONALIZADAS)
        let listaNotificaciones = [];

        // 1. Añadir notificaciones generales de la base de datos (si existen)
        if (estudiante.notificaciones && estudiante.notificaciones.length > 0) {
            listaNotificaciones = [...estudiante.notificaciones];
        } else {
            // Notificación por defecto si el panel está vacío
            listaNotificaciones.push({ 
                tipo: 'entrega', 
                texto: '¡Bienvenid@! Recuerda realizar tu test para personalizar tu tutoría IA.' 
            });
        }

        // 2. Añadir tareas con mensajes personalizados del alumno (Prioridad Visual)
        const avisosAlumno = estudiante.tareas.filter(t => !t.completada && t.mensaje_personalizado);
        avisosAlumno.forEach(t => {
            listaNotificaciones.push({
                tipo: t.tipo, // Esto activa los colores: examen(rojo), entrega(azul), deberes(amarillo)
                texto: t.mensaje_personalizado
            });
        });

        res.render('perfil', { 
            estudiante, 
            progreso, 
            saludo, 
            notificaciones: listaNotificaciones, 
            fraseMotivacional: frases[Math.floor(Math.random() * frases.length)],
            tituloWeb: 'Mi Panel de Control' 
        });
    } catch (error) { res.redirect('/estudiantes/login'); }
});

// ==========================================
// SECCIÓN: TUTOR IA (MISTRAL-7B REAL)
// ==========================================

router.post('/estudiantes/ia/chat', isAuthenticated, async (req, res) => {
    try {
        const { mensajeAlumno, asignaturaId, tema } = req.body;
        const estudiante = await Estudiante.findById(req.session.usuarioId);
        const plan = estudiante.plan_estudio.id(asignaturaId);
        const metodo = plan ? plan.metodo_sugerido : "General";

        let contextoExtra = "";
        const msgLow = mensajeAlumno.toLowerCase();
        const archivo = estudiante.archivos.find(a => msgLow.includes(a.nombre_archivo.toLowerCase()));

        if (archivo) {
            const ruta = path.join(__dirname, '../public/uploads/materiales', archivo.ruta_almacenamiento);
            if (fs.existsSync(ruta)) {
                const dataBuffer = fs.readFileSync(ruta);
                const data = await pdf(dataBuffer);
                contextoExtra = `\nContenido de tus apuntes: ${data.text.substring(0, 800)}`;
            }
        }

        const responseIA = await axios.post('https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2', {
            inputs: `<s>[INST] Tutor pedagógico profesional. Alumno: ${estudiante.nombre}. Tema: ${tema}. Método: ${metodo}.${contextoExtra}
            Pregunta: "${mensajeAlumno}". 
            Misión: Guíale paso a paso para completar su material de estudio. Responde en español. [/INST]`
        }, { timeout: 25000 });

        const respuestaIA = responseIA.data[0].generated_text.split('[/INST]')[1].trim();
        res.json({ estado: true, mensaje: respuestaIA, archivoDetectado: !!archivo });
    } catch (error) {
        res.json({ estado: true, mensaje: "Sigamos trabajando en tu recurso. ¿Qué parte quieres desarrollar ahora?" });
    }
});

// ==========================================
// SECCIÓN: MATERIALES, TEST Y CALENDARIO
// ==========================================

router.post('/estudiantes/subir-material', isAuthenticated, upload.single('archivoMaterial'), async (req, res) => {
    try {
        const nuevoMat = { nombre_archivo: req.file.originalname, tipo_de_archivo: req.file.mimetype, ruta_almacenamiento: req.file.filename };
        await Estudiante.findByIdAndUpdate(req.session.usuarioId, { $push: { archivos: nuevoMat } });
        res.json({ estado: true, mensaje: "Material subido con éxito." });
    } catch (error) { res.json({ estado: false }); }
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

router.get('/estudiantes/mi-perfil/mis-eventos', isAuthenticated, async (req, res) => {
    const estudiante = await Estudiante.findById(req.session.usuarioId);
    res.render('misEventos', { estudiante, tituloWeb: 'Mis Eventos' });
});

router.get('/estudiantes/mi-perfil/misMateriales', isAuthenticated, async (req, res) => {
    const estudiante = await Estudiante.findById(req.session.usuarioId);
    res.render('misMateriales', { estudiante, tituloWeb: 'Mis Materiales' });
});

router.get('/logout', (req, res) => { req.session.destroy(() => res.redirect('/')); });

module.exports = router;
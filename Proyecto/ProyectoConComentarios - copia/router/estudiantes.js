const express = require('express');
const router = express.Router();
const Estudiante = require('../models/estudiantes');
const Contador = require('../models/contador');

// --- LISTA DE FRASES MOTIVACIONALES ---
const frases = [
    "El éxito es la suma de pequeños esfuerzos repetidos día tras día.",
    "No te detengas hasta que te sientas orgullos@.",
    "La disciplina es el puente entre tus metas y tus logros.",
    "Tu único límite eres tú mism@.",
    "Cada día es una oportunidad para ser mejor que ayer.",
    "El estudio de hoy es el éxito de mañana.",
    "No cuentes los días, haz que los días cuenten.",
    "La constancia es la clave del éxito académico."
];

// --- MIDDLEWARE DE SEGURIDAD ---
const isAuthenticated = (req, res, next) => {
    if (req.session.usuarioId) return next();
    res.status(401).json({ estado: false, mensaje: 'Sesión no autorizada' });
};

// ==========================================
// 1. SECCIÓN: DASHBOARD DINÁMICO (USUARIO)
// ==========================================

router.get('/dashboard-info', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findById(req.session.usuarioId);

        if (!estudiante) {
            return res.status(404).json({ estado: false, mensaje: 'Estudiante no encontrado' });
        }

        // --- CÁLCULO DE PROGRESO REAL ---
        let porcentajeProgreso = 0;
        const tareas = estudiante.tareas || [];
        if (tareas.length > 0) {
            const completadas = tareas.filter(t => t.completada === true).length;
            porcentajeProgreso = Math.round((completadas / tareas.length) * 100);
        }

        // --- NOTIFICACIONES REALES ---
        const notificacionesReales = tareas
            .filter(t => !t.completada && (t.tipo === 'examen' || t.tipo === 'entrega'))
            .map(t => ({
                tipo: t.tipo,
                texto: `${t.titulo} para el ${new Date(t.fecha).toLocaleDateString('es-ES')}`
            }));

        // --- SELECCIÓN DE FRASE ALEATORIA ---
        const fraseAleatoria = frases[Math.floor(Math.random() * frases.length)];

        res.json({
            estado: true,
            estudiante: estudiante, // <--- Enviamos el objeto completo de la base de datos
            progreso: porcentajeProgreso,
            fraseMotivacional: fraseAleatoria,
            notificaciones: notificacionesReales
        });

    } catch (error) {
        console.error("Error en Dashboard:", error);
        res.status(500).json({ estado: false, mensaje: 'Error al obtener datos reales' });
    }
});

// ==========================================
// 2. SECCIÓN: AUTENTICACIÓN (LOGIN/LOGOUT)
// ==========================================

// Login para Estudiantes
router.post('/login', async (req, res) => {
    const { email, clave } = req.body;
    try {
        const user = await Estudiante.findOne({ email, clave });
        if (user) {
            req.session.usuarioId = user._id;
            res.json({ estado: true, usuarioId: user._id, nombre: user.nombre });
        } else {
            res.json({ estado: false, mensaje: 'Credenciales incorrectas' });
        }
    } catch (e) { res.status(500).json({ estado: false, mensaje: 'Error en el servidor' }); }
});

// Login para Administrador
router.post('/login-admin', async (req, res) => {
    const { email, clave } = req.body;
    if (email === 'admin@gmail.com' && clave === 'Admin31@') {
        req.session.usuarioId = 'ADMIN_SESSION'; // Identificador para el middleware
        req.session.rol = 'admin';
        res.json({ estado: true });
    } else {
        res.json({ estado: false, mensaje: 'Credenciales de administrador incorrectas' });
    }
});

// Logout
router.get('/logout', (req, res) => {
    req.session.destroy(() => res.json({ estado: true }));
});

// ==========================================
// 3. SECCIÓN: GESTIÓN DE ESTUDIANTES (CRUD)
// ==========================================

// Obtener todos los estudiantes (Admin)
router.get('/', async (req, res) => {
    try {
        const arrayEstudiantes = await Estudiante.find();
        res.json(arrayEstudiantes);
    } catch (error) { res.status(500).json({ estado: false }); }
});

// Registro de nuevo estudiante con ID incremental (001, 002...)
router.post('/', async (req, res) => {
    try {
        const contador = await Contador.findOneAndUpdate(
            { id: 'estudianteId' },
            { $inc: { seq: 1 } },
            { new: true, upsert: true }
        );
        const idFormateado = contador.seq.toString().padStart(3, '0');
        const nuevo = new Estudiante({ ...req.body, id_estudiante: idFormateado });
        await nuevo.save();
        res.json({ estado: true });
    } catch (error) { res.status(500).json({ estado: false, error: error.message }); }
});

// Actualizar estudiante
router.put('/:id', async (req, res) => {
    try {
        await Estudiante.findByIdAndUpdate(
            req.params.id,
            req.body,
            { new: true, runValidators: true }
        );
        res.json({ estado: true, mensaje: 'Estudiante actualizado' });
    } catch (error) {
        res.status(500).json({ estado: false, error: error.message });
    }
});

// Eliminar estudiante y reiniciar contador si la base de datos queda vacía
router.delete('/:id', async (req, res) => {
    try {
        await Estudiante.findByIdAndDelete(req.params.id);
        const total = await Estudiante.countDocuments();
        if (total === 0) {
            await Contador.findOneAndUpdate({ id: 'estudianteId' }, { seq: 0 });
        }
        res.json({ estado: true, mensaje: 'Estudiante eliminado' });
    } catch (error) {
        res.status(500).json({ estado: false, error: error.message });
    }
});


// 4. SECCIÓN: GESTIÓN DE TAREAS INDIVIDUALES
// ==========================================

// Añadir una nueva tarea al array del estudiante
router.post('/aniadir-tarea', isAuthenticated, async (req, res) => {
    try {
        const { titulo, tipo, fecha, hora, mensaje_personalizado } = req.body;
        // Usamos req.session.usuarioId directamente
        await Estudiante.findByIdAndUpdate(req.session.usuarioId, {
            $push: {
                tareas: {
                    titulo, tipo, fecha, hora,
                    mensaje_personalizado: mensaje_personalizado || "",
                    completada: false
                }
            }
        });
        res.json({ estado: true, mensaje: 'Tarea añadida con éxito' });
    } catch (error) {
        res.status(500).json({ estado: false, error: error.message });
    }
});

// Editar una tarea específica
router.put('/editar-tarea/:tareaId', isAuthenticated, async (req, res) => {
    try {
        const { titulo, fecha, hora, mensaje_personalizado } = req.body;

        // Buscamos al estudiante que tiene esa tarea
        const estudiante = await Estudiante.findOne({ "tareas._id": req.params.tareaId });
        if (!estudiante) return res.status(404).json({ estado: false, mensaje: 'Tarea no encontrada' });

        // Localizamos la tarea dentro del array y actualizamos sus campos
        const tarea = estudiante.tareas.id(req.params.tareaId);
        tarea.titulo = titulo;
        tarea.fecha = fecha;
        tarea.hora = hora;
        tarea.mensaje_personalizado = mensaje_personalizado;

        await estudiante.save();
        res.json({ estado: true, mensaje: 'Tarea actualizada' });
    } catch (error) {
        res.status(500).json({ estado: false, error: error.message });
    }
});

// Eliminar una tarea específica
router.delete('/eliminar-tarea/:tareaId', isAuthenticated, async (req, res) => {
    try {
        // Usamos $pull para quitar el objeto del array que coincida con el ID
        await Estudiante.findOneAndUpdate(
            { "tareas._id": req.params.tareaId },
            { $pull: { tareas: { _id: req.params.tareaId } } }
        );
        res.json({ estado: true, mensaje: 'Tarea eliminada' });
    } catch (error) {
        res.status(500).json({ estado: false, error: error.message });
    }
});

// Cambiar estado de una tarea (Check/Uncheck en el calendario)
router.put('/toggle-tarea/:tareaId', isAuthenticated, async (req, res) => {
    try {
        const estudiante = await Estudiante.findOne({ "tareas._id": req.params.tareaId });
        const tarea = estudiante.tareas.id(req.params.tareaId);
        tarea.completada = !tarea.completada;
        await estudiante.save();
        res.json({ estado: true });
    } catch (e) { res.status(500).json({ estado: false }); }
});

module.exports = router;
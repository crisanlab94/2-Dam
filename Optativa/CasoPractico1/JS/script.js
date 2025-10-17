document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formulario').addEventListener('submit', function(e) {
        e.preventDefault();
        let valido = true;

        // Nombre
        let nombre = document.getElementById('nombre').value.trim();
        let nombreRegex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]{3,20}$/;
        if (!nombreRegex.test(nombre)) {
            document.getElementById('nombre-error').textContent = 'Solo letras y espacios (3-20 caracteres)';
            valido = false;
        } else { document.getElementById('nombre-error').textContent = ''; }

        // Apellidos
        let apellidos = document.getElementById('apellidos').value.trim();
        let apellidosRegex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]{3,30}$/;
        if (!apellidosRegex.test(apellidos)) {
            document.getElementById('apellidos-error').textContent = 'Solo letras y espacios (3-30 caracteres)';
            valido = false;
        } else { document.getElementById('apellidos-error').textContent = ''; }

        // DNI
        let dni = document.getElementById('dni').value.trim();
        let dniRegex = /^\d{8}[A-Za-z]$/;
        if (!dniRegex.test(dni)) {
            document.getElementById('dni-error').textContent = 'DNI inválido (8 números + letra)';
            valido = false;
        } else { document.getElementById('dni-error').textContent = ''; }

        // Fecha
        let fecha = document.getElementById('fecha').value;
        if (!fecha || new Date(fecha) >= new Date()) {
            document.getElementById('fecha-error').textContent = 'Fecha inválida';
            valido = false;
        } else { document.getElementById('fecha-error').textContent = ''; }

        // Dirección
        let direccion = document.getElementById('direccion').value.trim();
        if (direccion.length < 5) {
            document.getElementById('direccion-error').textContent = 'Dirección demasiado corta';
            valido = false;
        } else { document.getElementById('direccion-error').textContent = ''; }

        // Código postal
        let codigo = document.getElementById('codigo').value.trim();
        let codigoRegex = /^\d{5}$/;
        if (!codigoRegex.test(codigo)) {
            document.getElementById('codigo-error').textContent = 'Código postal inválido (5 números)';
            valido = false;
        } else { document.getElementById('codigo-error').textContent = ''; }

        // Teléfono
        let telefono = document.getElementById('telefono').value.trim();
        let telefonoRegex = /^\d{9}$/;
        if (!telefonoRegex.test(telefono)) {
            document.getElementById('telefono-error').textContent = 'Teléfono inválido (9 números)';
            valido = false;
        } else { document.getElementById('telefono-error').textContent = ''; }

        // Email
        let email = document.getElementById('email').value.trim();
        let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            document.getElementById('email-error').textContent = 'Correo inválido';
            valido = false;
        } else { document.getElementById('email-error').textContent = ''; }

        // Contraseña
        let contraseña = document.getElementById('contraseña').value;
        let contraseñaRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{6,10}$/;
        if (!contraseñaRegex.test(contraseña)) {
            document.getElementById('contraseña-error').textContent = 'Contraseña inválida (6-10 caracteres, 1 mayúscula, 1 número, 1 símbolo)';
            valido = false;
        } else { document.getElementById('contraseña-error').textContent = ''; }

        // Género
        let genero = document.getElementById('genero').value;
        if (!genero) {
            document.getElementById('genero-error').textContent = 'Seleccione un género';
            valido = false;
        } else { document.getElementById('genero-error').textContent = ''; }

        // Términos
        if (!document.getElementById('terminos').checked) {
            document.getElementById('terminos-error').textContent = 'Debe aceptar los términos y condiciones';
            valido = false;
        } else { document.getElementById('terminos-error').textContent = ''; }

        
        if (valido) {
            this.submit();
        } else {
            console.log("Formulario con errores, no se envía");
        }
    });
});

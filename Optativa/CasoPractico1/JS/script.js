document.addEventListener('DOMContentLoaded', function () {
    const formulario = document.getElementById('formulario');

    function validarNombre() {
        const nombreInput = document.getElementById('nombre');
        const errorSpan = document.getElementById('nombre-error');
        const valor = nombreInput.value.trim();
        const regex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]{3,20}$/;
        if (!regex.test(valor)) {
            errorSpan.textContent = 'Solo letras y espacios (3-20 caracteres)';
            nombreInput.classList.add('invalid');
            nombreInput.classList.remove('valid');
            return false;
        } else {
            errorSpan.textContent = '';
            nombreInput.classList.add('valid');
            nombreInput.classList.remove('invalid');
            return true;
        }
    }

    function validarApellidos() {
        const input = document.getElementById('apellidos');
        const error = document.getElementById('apellidos-error');
        const val = input.value.trim();
        const regex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]{3,30}$/;
        if (!regex.test(val)) {
            error.textContent = 'Solo letras y espacios (3-30 caracteres)';
            input.classList.add('invalid');
            input.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            input.classList.add('valid');
            input.classList.remove('invalid');
            return true;
        }
    }

    function validarDNI() {
        const input = document.getElementById('dni');
        const error = document.getElementById('dni-error');
        const val = input.value.trim();
        const regex = /^\d{8}[A-Za-z]$/;
        if (!regex.test(val)) {
            error.textContent = 'DNI inválido (8 números + letra)';
            input.classList.add('invalid');
            input.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            input.classList.add('valid');
            input.classList.remove('invalid');
            return true;
        }
    }

    function validarFecha() {
        const input = document.getElementById('fecha');
        const error = document.getElementById('fecha-error');
        const val = input.value;
        const hoy = new Date();
        const fechaNac = new Date(val);
        if (!val || fechaNac >= hoy) {
            error.textContent = 'Fecha inválida (no puede ser posterior al día de hoy)';
            input.classList.add('invalid');
            input.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            input.classList.add('valid');
            input.classList.remove('invalid');
            return true;
        }
    }

    function validarDireccion() {
        const input = document.getElementById('direccion');
        const error = document.getElementById('direccion-error');
        const val = input.value.trim();
        if (val.length < 5) {
            error.textContent = 'Dirección demasiado corta';
            input.classList.add('invalid');
            input.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            input.classList.add('valid');
            input.classList.remove('invalid');
            return true;
        }
    }

    function validarCodigo() {
        const input = document.getElementById('codigo');
        const error = document.getElementById('codigo-error');
        const val = input.value.trim();
        const regex = /^\d{5}$/;
        if (!regex.test(val)) {
            error.textContent = 'Código postal inválido (5 números)';
            input.classList.add('invalid');
            input.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            input.classList.add('valid');
            input.classList.remove('invalid');
            return true;
        }
    }

    function validarTelefono() {
        const input = document.getElementById('telefono');
        const error = document.getElementById('telefono-error');
        const val = input.value.trim();
        const regex = /^\d{9}$/;
        if (!regex.test(val)) {
            error.textContent = 'Teléfono inválido (9 números)';
            input.classList.add('invalid');
            input.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            input.classList.add('valid');
            input.classList.remove('invalid');
            return true;
        }
    }

    function validarEmail() {
        const input = document.getElementById('email');
        const error = document.getElementById('email-error');
        const val = input.value.trim();
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!regex.test(val)) {
            error.textContent = 'Correo inválido';
            input.classList.add('invalid');
            input.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            input.classList.add('valid');
            input.classList.remove('invalid');
            return true;
        }
    }

    function validarContraseña() {
        const input = document.getElementById('contraseña');
        const error = document.getElementById('contraseña-error');
        const val = input.value;
        const errores = [];
        if (val.length < 6 || val.length > 10) errores.push("debe tener entre 6 y 10 caracteres");
        if (!/[A-Z]/.test(val)) errores.push("falta una mayúscula");
        if (!/\d/.test(val)) errores.push("falta un número");
        if (!/[!@#$%^&*]/.test(val)) errores.push("falta un símbolo (!@#$%^&*)");
        if (errores.length > 0) {
            error.textContent = "Contraseña inválida: " + errores.join(", ");
            input.classList.add('invalid');
            input.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            input.classList.add('valid');
            input.classList.remove('invalid');
            return true;
        }
    }

    function validarGenero() {
        const select = document.getElementById('genero');
        const error = document.getElementById('genero-error');
        if (!select.value) {
            error.textContent = 'Seleccione un género';
            select.classList.add('invalid');
            select.classList.remove('valid');
            return false;
        } else {
            error.textContent = '';
            select.classList.add('valid');
            select.classList.remove('invalid');
            return true;
        }
    }

    function validarTerminos() {
        const checkbox = document.getElementById('terminos');
        const error = document.getElementById('terminos-error');
        if (!checkbox.checked) {
            error.textContent = 'Debe aceptar los términos y condiciones';
            return false;
        } else {
            error.textContent = '';
            return true;
        }
    }

    document.getElementById('mostrar-contraseña').addEventListener('change', function() {
        const contraseñaInput = document.getElementById('contraseña');
        contraseñaInput.type = this.checked ? 'text' : 'password';
    });

    function añadirValidacion(id, validador) {
        const campo = document.getElementById(id);
        campo.addEventListener('input', validador);
        campo.addEventListener('blur', validador);
    }

    añadirValidacion('nombre', validarNombre);
    añadirValidacion('apellidos', validarApellidos);
    añadirValidacion('dni', validarDNI);
    añadirValidacion('fecha', validarFecha);
    añadirValidacion('direccion', validarDireccion);
    añadirValidacion('codigo', validarCodigo);
    añadirValidacion('telefono', validarTelefono);
    añadirValidacion('email', validarEmail);
    añadirValidacion('contraseña', validarContraseña);
    document.getElementById('genero').addEventListener('change', validarGenero);
    document.getElementById('terminos').addEventListener('change', validarTerminos);

    formulario.addEventListener('submit', function (e) {
        e.preventDefault();
        const validaciones = [
            validarNombre(),
            validarApellidos(),
            validarDNI(),
            validarFecha(),
            validarDireccion(),
            validarCodigo(),
            validarTelefono(),
            validarEmail(),
            validarContraseña(),
            validarGenero(),
            validarTerminos()
        ];
        if (validaciones.every(v => v === true)) {
            alert('Formulario enviado correctamente.');
            this.reset();
            const inputs = this.querySelectorAll('input, select');
            inputs.forEach(input => input.classList.remove('valid', 'invalid'));
            const errores = this.querySelectorAll('.error');
            errores.forEach(span => span.textContent = '');
        }
    });
});

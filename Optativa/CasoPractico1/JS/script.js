// Validar el nombre
let nombre = document.getElementById('nombre').value;
let nombreRegex = ^[a-zA-Z]{3,20}$;




// Validar el correo electrónico
let email = document.getElementById('email').value;
let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
if (!emailRegex.test(email)) {
    document.getElementById('errorEmail').textContent = 'Ingrese un correo electrónico válido';
    valido = false;
}

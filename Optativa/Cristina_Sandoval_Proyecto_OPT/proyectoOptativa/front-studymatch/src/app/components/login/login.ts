import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { EstudianteService } from '../../services/estudiante';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.html'
})
export class LoginComponent {
  private service = inject(EstudianteService);
  private router = inject(Router);


  email: string = '';
  clave: string = '';

  mensajeError: string | null = null;
  verClave: boolean = false;

  acceder(): void {
    this.mensajeError = null;

    this.service.login(this.email, this.clave).subscribe({
      next: (res) => {
        if (res.estado) {

          this.router.navigate(['/mi-perfil']);
        } else {
          this.mensajeError = 'Correo o contraseña incorrectos';
        }
      },
      error: (err: any) => {
        this.mensajeError = 'Error al conectar con el servidor';
        console.error(err);
      }
    });
  }

  togglePassword(): void {
    this.verClave = !this.verClave;
  }
}
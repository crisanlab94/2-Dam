import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { EstudianteService } from '../../services/estudiante';

@Component({
  selector: 'app-login-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login-admin.html'
})
export class LoginAdminComponent {
  private service = inject(EstudianteService);
  private router = inject(Router);

  email: string = '';
  clave: string = '';
  mensaje: string | null = null;
  verClave: boolean = false;

  accederAdmin() {
    this.service.loginAdmin(this.email, this.clave).subscribe({
      next: (res) => {
        if (res.estado) {
          this.router.navigate(['/admin']);
        } else {
          this.mensaje = res.mensaje;
        }
      },
      error: () => this.mensaje = 'Error de conexión con el servidor'
    });
  }

  togglePassword() {
    this.verClave = !this.verClave;
  }
}
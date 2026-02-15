import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { EstudianteService } from '../../services/estudiante';

@Component({
  selector: 'app-login-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login-admin.html',
  styleUrls: ['./login-admin.css']
})
export class LoginAdmin {
  private service = inject(EstudianteService);
  private router = inject(Router);

  public email: string = '';
  public clave: string = '';
  public mensaje: string = ''; // Coincide con tu {{ mensaje }}
  public verClave: boolean = false; // Coincide con tu [type]

  togglePassword(): void {
    this.verClave = !this.verClave;
  }

  accederAdmin(): void {
    this.mensaje = '';
    
    this.service.loginAdmin(this.email, this.clave).subscribe({
      next: (res: any) => {
        if (res.estado) {
          this.router.navigate(['/admin']);
        } else {
          this.mensaje = res.mensaje || 'Credenciales incorrectas';
        }
      },
      error: (err: any) => {
        this.mensaje = 'Error de conexión con el servidor';
      }
    });
  }
}
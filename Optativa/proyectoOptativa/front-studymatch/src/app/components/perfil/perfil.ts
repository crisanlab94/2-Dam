import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EstudianteService } from '../../services/estudiante';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './perfil.html',
  styleUrls: ['./perfil.css']
})
export class Perfil implements OnInit {
  private service = inject(EstudianteService);
  private router = inject(Router);

  public student: any = null;
  public mensajeSoporte: string = '';
  public enviando: boolean = false;

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos() {
    this.service.getDatosDashboard().subscribe({
      next: (res: any) => {
        // 🚀 ESTO BLOQUEARÁ LA PÁGINA PARA QUE PUEDAS VER LA CONSOLA
        console.log("DEBUG PERFIL:", res);
        
        if (res.estado) {
          this.student = res.estudiante;
        } else {
          alert("El servidor devolvió estado: false. Revisa la consola.");
          this.router.navigate(['/login']);
        }
      },
      error: (err) => {
        alert("Error de conexión. Mira la consola (F12)");
        console.error(err);
      }
    });
  }

  enviarASoporte() {
    if (!this.mensajeSoporte.trim()) return;
    this.enviando = true;
    setTimeout(() => {
      alert('✅ Mensaje enviado a soporte.');
      this.mensajeSoporte = '';
      this.enviando = false;
    }, 1000);
  }
}
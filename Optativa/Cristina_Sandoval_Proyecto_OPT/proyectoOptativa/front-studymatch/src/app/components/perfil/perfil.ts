import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
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
  private cdr = inject(ChangeDetectorRef);

  public student: any = null;
  public cargando: boolean = true;
  public mensajeSoporte: string = '';
  public enviando: boolean = false;


  public notificacionSoporte: boolean = false;
  public verClave: boolean = false;

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos() {
    this.service.getDatosDashboard().subscribe({
      next: (res: any) => {
        if (res.estado) {
          this.student = res.estudiante;

          if (this.student.fecha_nacimiento) {
            this.student.fecha_nacimiento = this.student.fecha_nacimiento.split('T')[0];
          }
        } else {
          this.router.navigate(['/login']);
        }
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => { this.cargando = false; }
    });
  }

  cambiarFoto(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64 = reader.result as string;
        this.student.foto = base64;
        this.actualizarPerfil(false);
      };
      reader.readAsDataURL(file);
    }
  }

  actualizarPerfil(mostrarAlerta: boolean = true) {
    if (!this.student || !this.student._id) return;
    const { _id, ...datosNuevos } = this.student;

    this.service.actualizarEstudiante(_id, datosNuevos).subscribe({
      next: (res: any) => {
        if (res.estado && mostrarAlerta) {
          alert('✅ Perfil actualizado correctamente.');
        }
      }
    });
  }

  enviarASoporte() {
    if (!this.mensajeSoporte.trim()) return;

    this.enviando = true;
    this.notificacionSoporte = false;


    this.cdr.detectChanges();

    setTimeout(() => {
      this.enviando = false;
      this.notificacionSoporte = true;
      this.mensajeSoporte = '';


      this.cdr.markForCheck();
      this.cdr.detectChanges();

      setTimeout(() => {
        this.notificacionSoporte = false;
        this.cdr.detectChanges();
      }, 3000);
    }, 1000);
  }
}
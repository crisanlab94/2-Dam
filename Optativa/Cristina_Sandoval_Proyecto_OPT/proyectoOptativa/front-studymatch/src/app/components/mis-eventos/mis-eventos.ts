import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { EstudianteService } from '../../services/estudiante';

@Component({
  selector: 'app-mis-eventos',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './mis-eventos.html',
  styleUrls: ['./mis-eventos.css']
})
export class MisEventosComponent implements OnInit {
  private service = inject(EstudianteService);
  private cdr = inject(ChangeDetectorRef);

  public student: any = null;
  public tareasFiltradas: any[] = [];
  public filtroActual: string = 'todas';

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos() {
    this.service.getDatosDashboard().subscribe({
      next: (res: any) => {
        if (res.estado) {
          this.student = res.estudiante;
          this.aplicarFiltro(this.filtroActual);
        }
        this.cdr.detectChanges();
      }
    });
  }

  // Cálculo de progreso para la barra superior
  get porcentajeProgreso(): number {
    if (!this.student?.tareas?.length) return 0;
    const completadas = this.student.tareas.filter((t: any) => t.completada).length;
    return Math.round((completadas / this.student.tareas.length) * 100);
  }

  aplicarFiltro(tipo: string) {
    this.filtroActual = tipo;
    if (!this.student?.tareas) return;

    if (tipo === 'todas') {
      this.tareasFiltradas = this.student.tareas;
    } else if (tipo === 'pendientes') {
      this.tareasFiltradas = this.student.tareas.filter((t: any) => !t.completada);
    } else if (tipo === 'completadas') {
      this.tareasFiltradas = this.student.tareas.filter((t: any) => t.completada);
    }
  }

  toggleTarea(tarea: any) {
    // Cambiamos el estado localmente
    tarea.completada = !tarea.completada;

    // Guardamos el cambio en la base de datos usando el servicio
    this.service.actualizarEstudiante(this.student._id, { tareas: this.student.tareas }).subscribe({
      next: () => {
        this.aplicarFiltro(this.filtroActual);
        this.cdr.detectChanges();
      }
    });
  }

  eliminarTarea(id: string) {
    if (confirm('¿Estás seguro de que quieres eliminar este evento?')) {
      const nuevasTareas = this.student.tareas.filter((t: any) => t._id !== id);
      this.service.actualizarEstudiante(this.student._id, { tareas: nuevasTareas }).subscribe({
        next: () => this.cargarDatos()
      });
    }
  }

  // Lógica para las etiquetas de "Faltan X días" o "¡HOY!"
  getDiasFaltantes(fecha: any): { texto: string, clase: string } | null {
    const fechaT = new Date(fecha);
    const hoy = new Date();
    hoy.setHours(0, 0, 0, 0);
    const diff = fechaT.getTime() - hoy.getTime();
    const dias = Math.ceil(diff / (1000 * 60 * 60 * 24));

    if (dias === 0) return { texto: '¡HOY!', clase: 'bg-danger pulse' };
    if (dias === 1) return { texto: 'Mañana', clase: 'bg-warning text-dark' };
    if (dias > 1 && dias <= 7) return { texto: `Faltan ${dias} días`, clase: 'bg-light text-primary border border-primary' };
    return null;
  }
}
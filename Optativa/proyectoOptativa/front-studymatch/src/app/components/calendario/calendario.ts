import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { EstudianteService } from '../../services/estudiante';

@Component({
  selector: 'app-calendario',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './calendario.html',
  styleUrls: ['./calendario.css']
})
export class CalendarioComponent implements OnInit {
  private router = inject(Router);
  private service = inject(EstudianteService);
  private cdr = inject(ChangeDetectorRef);

  public diasMes: any[] = [];
  public student: any = null;
  public mesActual: Date = new Date();

  ngOnInit(): void {
    this.generarCalendario();
    this.cargarTareas();
  }

  // Carga las tareas del backend para marcarlas en los días
  cargarTareas() {
    this.service.getDatosDashboard().subscribe({
      next: (res: any) => {
        if (res.estado) {
          this.student = res.estudiante;
          this.marcarTareasEnCalendario();
          this.cdr.detectChanges(); // Forzamos actualización visual
        }
      }
    });
  }

  // Genera la cuadrícula de días del mes actual
  generarCalendario() {
    const año = this.mesActual.getFullYear();
    const mes = this.mesActual.getMonth();

    // Ajuste para que la semana empiece en Lunes
    const primerDiaSemana = new Date(año, mes, 1).getDay();
    const offset = primerDiaSemana === 0 ? 6 : primerDiaSemana - 1;

    const diasEnMes = new Date(año, mes + 1, 0).getDate();

    this.diasMes = [];

    // Rellenar huecos vacíos antes del día 1
    for (let i = 0; i < offset; i++) {
      this.diasMes.push({ dia: null });
    }

    // Rellenar días reales
    for (let i = 1; i <= diasEnMes; i++) {
      const fechaStr = `${año}-${(mes + 1).toString().padStart(2, '0')}-${i.toString().padStart(2, '0')}`;
      this.diasMes.push({
        dia: i,
        fecha: fechaStr,
        tareas: []
      });
    }
  }

  // Cruza los datos de las tareas con los días generados
  marcarTareasEnCalendario() {
    if (!this.student?.tareas) return;

    this.diasMes.forEach(d => {
      if (d.dia) {
        // Filtramos tareas que coincidan con la fecha (YYYY-MM-DD)
        d.tareas = this.student.tareas.filter((t: any) => {
          const fechaTarea = t.fecha.split('T')[0];
          return fechaTarea === d.fecha;
        });
      }
    });
  }

  // 🚀 FUNCIÓN CORREGIDA (Sin 'ñ')
  irAAnadir(fecha: string) {
    this.router.navigate(['/add-evento'], { queryParams: { fecha: fecha } });
  }
}
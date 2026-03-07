import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EstudianteService } from '../../services/estudiante';

// --- FULLCALENDAR ---
import { FullCalendarModule } from '@fullcalendar/angular';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';

import localeEs from '@angular/common/locales/es';
registerLocaleData(localeEs, 'es');

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, FullCalendarModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class DashboardComponent implements OnInit {
  private service = inject(EstudianteService);
  private router = inject(Router);
  private cdr = inject(ChangeDetectorRef);

  public datos: any = null;
  public saludo: string = '';
  public fechaActual: Date = new Date();

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, interactionPlugin],
    locale: 'es',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth'
    },
    height: 'auto',
    events: [],
    dateClick: this.handleDateClick.bind(this),
  };

  ngOnInit(): void {
    this.definirSaludo();
    this.cargarDashboard();
  }

  cargarDashboard(): void {
    this.service.getDatosDashboard().subscribe({
      next: (res: any) => {
        if (res && res.estado) {
          this.datos = { ...res };
          if (res.estudiante && res.estudiante.tareas) {
            this.mapearTareas(res.estudiante.tareas);
          }
          this.cdr.detectChanges();
        } else {
          this.router.navigate(['/login']);
        }
      },
      error: (err: any) => {
        if (err.status === 401) this.router.navigate(['/login']);
      }
    });
  }

  /**
   * Mapeo de colores personalizado según tu petición
   */
  mapearTareas(tareas: any[]): void {
    this.calendarOptions.events = tareas.map(t => {
      let colorEvento = '#0d6efd'; // Por defecto azul

      // 1. PRIORIDAD MÁXIMA: Si está completada, siempre es verde
      if (t.completada) {
        colorEvento = '#28a745'; // 🟢 VERDE
      } else {
        // 2. Si no está completada, aplicamos color por tipo
        switch (t.tipo.toLowerCase()) {
          case 'examen':
            colorEvento = '#dc3545'; // 🔴 ROJO
            break;
          case 'trabajo':
            colorEvento = '#0d6efd'; // 🔵 AZUL
            break;
          case 'deberes':
            colorEvento = '#ffc107'; // 🟡 AMARILLO
            break;
          case 'aviso':
          case 'avisos':
            colorEvento = '#6c757d'; // 🔘 GRIS
            break;
        }
      }

      return {
        id: t._id,
        title: (t.completada ? '✅ ' : '') + t.titulo,
        start: t.fecha ? t.fecha.split('T')[0] : new Date().toISOString().split('T')[0],
        color: colorEvento,
        allDay: true,
        textColor: t.tipo === 'deberes' && !t.completada ? '#000' : '#fff' // Texto negro en amarillo para leer mejor
      };
    });
  }

  handleDateClick(arg: any): void {
    this.router.navigate(['/add-evento'], {
      queryParams: { fecha: arg.dateStr }
    });
  }

  definirSaludo(): void {
    const hora = new Date().getHours();
    if (hora >= 6 && hora < 13) this.saludo = 'Buenos Días';
    else if (hora >= 13 && hora < 20) this.saludo = 'Buenas Tardes';
    else this.saludo = 'Buenas Noches';
  }

  getIconoSaludo(): string {
    return this.saludo.includes('Días') ? '☀️' : (this.saludo.includes('Tardes') ? '🌤️' : '🌙');
  }

  logout(): void {
    this.router.navigate(['/login']);
  }
}
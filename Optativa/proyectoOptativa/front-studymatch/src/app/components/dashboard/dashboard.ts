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
  private cdr = inject(ChangeDetectorRef); // 🚀 Inyectamos el detector de cambios

  public datos: any = null;
  public saludo: string = '';
  public fechaActual: Date = new Date();

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, interactionPlugin],
    locale: 'es',
    headerToolbar: { left: 'prev,next today', center: 'title', right: 'dayGridMonth' },
    height: 'auto',
    events: []
  };

  ngOnInit(): void {
    this.definirSaludo();
    this.cargarDashboard();
  }

  cargarDashboard(): void {
    this.service.getDatosDashboard().subscribe({
      next: (res: any) => {
        console.log('📦 Respuesta recibida:', res);
        if (res && res.estado) {
          // Asignamos los datos con una copia para asegurar que Angular lo detecte
          this.datos = { ...res };
          
          if (res.estudiante && res.estudiante.tareas) {
            this.mapearTareas(res.estudiante.tareas);
          }

          // 🚀 FORZAMOS A ANGULAR A DIBUJAR
          this.cdr.detectChanges(); 
          console.log('✅ Vista actualizada con éxito');
        } else {
          this.router.navigate(['/login']);
        }
      },
      error: (err: any) => {
        console.error('❌ Error API:', err);
        if (err.status === 401) this.router.navigate(['/login']);
      }
    });
  }

  mapearTareas(tareas: any[]): void {
    this.calendarOptions.events = tareas.map(t => ({
      id: t._id,
      title: t.completada ? '✅ ' + t.titulo : t.titulo,
      start: t.fecha ? t.fecha.split('T')[0] : new Date().toISOString().split('T')[0],
      color: t.completada ? '#28a745' : (t.tipo === 'examen' ? '#dc3545' : '#0d6efd')
    }));
  }

  definirSaludo(): void {
    const hora = new Date().getHours();
    if (hora >= 6 && hora < 13) this.saludo = 'Buenos Días';
    else if (hora >= 13 && hora < 20) this.saludo = 'Buenas Tardes';
    else this.saludo = 'Buenas Noches';
  }

  getIconoSaludo(): string {
    if (this.saludo.includes('Días')) return '☀️';
    if (this.saludo.includes('Tardes')) return '🌤️';
    return '🌙';
  }

  logout(): void {
    this.router.navigate(['/login']);
  }
}
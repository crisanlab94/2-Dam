import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { EstudianteService, Estudiante } from '../../services/estudiante';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './admin.html',
  styleUrls: ['./admin.css']
})
export class AdminPanel implements OnInit {
  private service = inject(EstudianteService);
  private cdr = inject(ChangeDetectorRef);
  private router = inject(Router);

  public estudiantes: Estudiante[] = [];
  public filtro: string = '';
  public estudianteEditando: any = null;
  public asignaturas_extra: string = '';

  public cursosDisponibles: string[] = [];
  public asignaturasSugeridas: string[] = [];

  public datosAcademicos: any = {
    "Colegio": {
      "5º Primaria": ["Lengua Castellana", "Matemáticas", "Ciencias Naturaleza", "Ciencias Sociales", "Inglés", "Educación Física", "Religión", "Valores"],
      "6º Primaria": ["Lengua Castellana", "Matemáticas", "Ciencias Naturaleza", "Ciencias Sociales", "Inglés", "Educación Física", "Religión", "Valores Cívicos"]
    },
    "Instituto": {
      "1º ESO": ["Lengua", "Matemáticas", "Inglés", "Biología", "Geografía", "Educación Física", "Música", "Plástica"],
      "2º ESO": ["Lengua", "Matemáticas", "Inglés", "Física y Química", "Geografía", "Educación Física", "Tecnología"],
      "3º ESO": ["Lengua", "Matemáticas", "Inglés", "Biología", "Física y Química", "Geografía", "Educación Física"],
      "4º ESO": ["Lengua", "Inglés", "Geografía", "Matemáticas", "Educación Física", "Biología", "Física", "Economía", "Latín"],
      "1º Bachillerato": {
        "Comunes": ["Lengua I", "Inglés I", "Filosofía", "Educación Física"],
        "Ciencias": ["Matemáticas I", "Biología y Geología", "Física y Química", "Dibujo Técnico I"],
        "Sociales": ["Matemáticas Aplicadas I", "Economía", "Historia Contemporánea"],
        "Artes": ["Dibujo Artístico I", "Cultura Audiovisual", "Volumen"]
      },
      "2º Bachillerato": {
        "Comunes": ["Lengua II", "Inglés II", "Historia de España", "Filosofía II"],
        "Ciencias": ["Matemáticas II", "Biología", "Física", "Química"],
        "Sociales": ["Matemáticas Aplicadas II", "Economía de Empresa", "Geografía"],
        "Artes": ["Dibujo Artístico II", "Diseño", "Cultura Audiovisual II"]
      }
    }
  };

  ngOnInit(): void {
    this.obtenerLista();
  }

  obtenerLista(): void {
    this.service.getEstudiantes().subscribe({
      next: (res: any) => {
        this.estudiantes = res;
        this.cdr.detectChanges();
      }
    });
  }

  get estudiantesFiltrados() {
    if (!this.filtro) return this.estudiantes;
    const b = this.filtro.toLowerCase().trim();
    return this.estudiantes.filter(e =>
      e.nombre?.toLowerCase().includes(b) ||
      e.telefono?.toLowerCase().includes(b) ||
      e.nombre_entidad?.toLowerCase().includes(b) ||
      e.curso?.toLowerCase().includes(b)
    );
  }

  prepararEdicion(est: any): void {
    this.estudianteEditando = { ...est };
    if (this.estudianteEditando.fecha_nacimiento) {
      this.estudianteEditando.fecha_nacimiento = this.estudianteEditando.fecha_nacimiento.split('T')[0];
    }
    if (this.estudianteEditando.tipo_entidad) {
      this.cursosDisponibles = Object.keys(this.datosAcademicos[this.estudianteEditando.tipo_entidad] || {});
      this.actualizarSugerencias();
      const manuales = this.estudianteEditando.asignaturas.filter((a: string) => !this.asignaturasSugeridas.includes(a));
      this.asignaturas_extra = manuales.join(', ');
    }
  }

  cambioEntidad() {
    this.estudianteEditando.curso = '';
    this.asignaturasSugeridas = [];
    this.asignaturas_extra = '';
    if (this.estudianteEditando.tipo_entidad && this.datosAcademicos[this.estudianteEditando.tipo_entidad]) {
      this.cursosDisponibles = Object.keys(this.datosAcademicos[this.estudianteEditando.tipo_entidad]);
    }
  }

  actualizarSugerencias() {
    const { tipo_entidad, curso, modalidad } = this.estudianteEditando;
    if (!tipo_entidad || !curso || !this.datosAcademicos[tipo_entidad]) return;
    let lista: string[] = [];
    const nodo = this.datosAcademicos[tipo_entidad][curso];
    if (tipo_entidad === "Instituto" && curso.includes("Bachillerato") && nodo) {
      const comunes = nodo["Comunes"] || [];
      const especificas = nodo[modalidad] || [];
      lista = [...comunes, ...especificas];
    } else { lista = nodo || []; }
    this.asignaturasSugeridas = lista;
  }

  toggleAsignatura(asig: string) {
    if (!this.estudianteEditando.asignaturas) this.estudianteEditando.asignaturas = [];
    const index = this.estudianteEditando.asignaturas.indexOf(asig);
    if (index > -1) this.estudianteEditando.asignaturas.splice(index, 1);
    else this.estudianteEditando.asignaturas.push(asig);
  }

  guardarCambios(): void {
    const extras = this.asignaturas_extra.split(',').map(s => s.trim()).filter(s => s !== '');
    const seleccionadasBotones = this.estudianteEditando.asignaturas.filter((a: string) => this.asignaturasSugeridas.includes(a));
    this.estudianteEditando.asignaturas = [...new Set([...seleccionadasBotones, ...extras])];
    const { _id, ...datosNuevos } = this.estudianteEditando;
    this.service.actualizarEstudiante(_id, datosNuevos).subscribe({
      next: (res: any) => {
        if (res.estado) { this.obtenerLista(); this.estudianteEditando = null; alert('Cambios guardados.'); }
      }
    });
  }

  borrar(id: string | undefined): void {
    if (!id || !confirm('¿Estas seguro que quieres eliminar este estudiante?')) return;
    this.service.eliminarEstudiante(id).subscribe({ next: () => this.obtenerLista() });
  }
}
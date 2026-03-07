import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EstudianteService, Estudiante } from '../../services/estudiante';

@Component({
  selector: 'app-estudiante',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './estudiante.html',
  styleUrls: ['./estudiante.css']
})
export class EstudianteComponent {
  private service = inject(EstudianteService);
  private router = inject(Router);

  confirmar_clave: string = '';
  asignaturas_extra: string = '';
  cursosDisponibles: string[] = [];
  asignaturasSugeridas: string[] = [];
  asignaturasSeleccionadas: string[] = [];


  public verClave: boolean = false;
  public verClaveConfirm: boolean = false;

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

  estudiante: Estudiante = {
    nombre: '', email: '', telefono: '', fecha_nacimiento: '',
    tipo_entidad: '', nombre_entidad: '', ubicacion: '', curso: '',
    modalidad: 'Ciencias', asignaturas: [], clave: '', fecha_registro: new Date()
  };

  togglePassword(): void { this.verClave = !this.verClave; }
  togglePasswordConfirm(): void { this.verClaveConfirm = !this.verClaveConfirm; }

  cambioEntidad() {
    this.estudiante.curso = '';
    this.asignaturasSugeridas = [];
    this.asignaturasSeleccionadas = [];
    if (this.estudiante.tipo_entidad && this.datosAcademicos[this.estudiante.tipo_entidad]) {
      this.cursosDisponibles = Object.keys(this.datosAcademicos[this.estudiante.tipo_entidad]);
    }
  }

  actualizarSugerencias() {
    const { tipo_entidad, curso, modalidad } = this.estudiante;
    if (!tipo_entidad || !curso) return;
    let lista: string[] = [];
    if (tipo_entidad === "Instituto" && curso.includes("Bachillerato")) {
      const comunes = this.datosAcademicos["Instituto"][curso]["Comunes"] || [];
      const especificas = this.datosAcademicos["Instituto"][curso][modalidad] || [];
      lista = [...comunes, ...especificas];
    } else {
      lista = this.datosAcademicos[tipo_entidad][curso] || [];
    }
    this.asignaturasSugeridas = lista;
    this.asignaturasSeleccionadas = [...lista];
  }

  toggleAsignatura(asig: string) {
    const idx = this.asignaturasSeleccionadas.indexOf(asig);
    if (idx > -1) this.asignaturasSeleccionadas.splice(idx, 1);
    else this.asignaturasSeleccionadas.push(asig);
  }

  checkMayuscula() { return /[A-Z]/.test(this.estudiante.clave); }
  checkNumero() { return /\d/.test(this.estudiante.clave); }
  checkSimbolo() { return /[@$!%*?&]/.test(this.estudiante.clave); }
  checkLongitud() { return this.estudiante.clave.length >= 6; }

  formularioValido(): boolean {
    const e = this.estudiante;
    return (e.nombre.trim().length >= 5 && /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(e.email) &&
      e.fecha_nacimiento !== '' && e.tipo_entidad !== '' && e.curso !== '' &&
      this.checkMayuscula() && this.checkNumero() && this.checkSimbolo() &&
      this.checkLongitud() && e.clave === this.confirmar_clave);
  }

  crear(): void {
    const extras = this.asignaturas_extra.split(',').map(s => s.trim()).filter(s => s !== '');
    this.estudiante.asignaturas = [...this.asignaturasSeleccionadas, ...extras];
    this.service.crearEstudiante(this.estudiante).subscribe({
      next: () => { alert('✅ Registro completado'); this.router.navigate(['/login']); }
    });
  }
}
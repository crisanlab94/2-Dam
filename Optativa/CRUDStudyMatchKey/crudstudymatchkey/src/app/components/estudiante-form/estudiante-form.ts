import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { EstudianteService, Estudiante } from '../../services/estudiante.service';

@Component({
  selector: 'app-estudiante-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './estudiante-form.html',
  styleUrls: ['./estudiante-form.css']
})
export class EstudianteFormComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private estudianteService = inject(EstudianteService);

  // Objeto que almacena los datos vinculados al formulario
  estudiante: any = {
    id_entidad: '',
    nombre: '',
    fecha_nacimiento: '',
    email: '',
    telefono: '',
    tipo_entidad: 'Instituto',
    curso: '',
    modalidad: '',
    asignaturas: [],
    clave: '',
    fecha_registro: new Date()
  };

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.estudianteService.getEstudiante(id).subscribe({
        next: (data: Estudiante) => {
          this.estudiante = data;
        },
        error: (err) => console.error('Error al cargar datos:', err)
      });
    }
  }

  // Lógica para transformar el string de entrada en array (como en tu manual)
  onAsignaturasChange(valor: string) {
    this.estudiante.asignaturas = valor.split(',').map((s: string) => s.trim());
  }

  actualizar(): void {
    // REQUISITO: El botón de actualizar no realiza acciones reales de persistencia
    console.log('Actualización simulada para:', this.estudiante.nombre);
    this.router.navigate(['/estudiantes']);
  }

  eliminar(): void {
    // REQUISITO: El botón de eliminar no realiza acciones reales de persistencia
    if (confirm("¿Deseas eliminar definitivamente este perfil de estudiante?")) {
      console.log('Eliminación simulada bloqueada por requerimiento.');
      this.router.navigate(['/estudiantes']);
    }
  }
}
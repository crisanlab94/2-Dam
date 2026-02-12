import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { EstudianteService, Estudiante } from '../../services/estudiante.service';

@Component({
  selector: 'app-estudiante',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './estudiante.html',
  styleUrls: ['./estudiante.css']
})
export class EstudianteComponent implements OnInit {

  listaEstudiantes: Estudiante[] = [];

  
  private estudianteService = inject(EstudianteService);

  ngOnInit(): void {

    this.obtenerEstudiantes();
  }

  obtenerEstudiantes(): void {
    this.estudianteService.getEstudiantes().subscribe({
      next: (resultado: Estudiante[]) => {
        this.listaEstudiantes = resultado;
      },
      error: (err: any) => {
        console.error('Error al conectar con el servidor:', err);
      }
    });
  }
}
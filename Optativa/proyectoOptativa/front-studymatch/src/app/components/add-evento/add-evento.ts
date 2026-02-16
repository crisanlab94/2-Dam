import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { EstudianteService } from '../../services/estudiante';

@Component({
  selector: 'app-add-evento',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './add-evento.html',
  styleUrls: ['./add-evento.css']
})
export class AddEventoComponent implements OnInit {
  private service = inject(EstudianteService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  public studentId: string = '';
  public enviando: boolean = false;

  public nuevaTarea: any = {
    titulo: '',
    tipo: 'examen',
    fecha: '',
    hora: '',
    mensaje_personalizado: '',
    completada: false
  };

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      if (params['fecha']) {
        this.nuevaTarea.fecha = params['fecha'];
      }
    });


    this.service.getDatosDashboard().subscribe({
      next: (res: any) => {
        if (res.estado) {
          this.studentId = res.estudiante._id;
        }
      }
    });
  }

  guardar(): void {
    if (this.enviando) return;
    this.enviando = true;


    this.service.aniadirTarea(this.nuevaTarea).subscribe({
      next: (res: any) => {
        if (res.estado) {
          this.router.navigate(['/mis-eventos']);
        }
        this.enviando = false;
      },
      error: (err) => {
        this.enviando = false;
        console.error("Error:", err);
      }
    });
  }
}
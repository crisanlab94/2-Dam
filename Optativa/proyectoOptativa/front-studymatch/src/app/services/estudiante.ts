import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Estudiante {
  _id?: string;
  id_estudiante?: string;
  nombre: string;
  fecha_nacimiento: any;
  email: string;
  telefono: string;
  tipo_entidad: string;
  nombre_entidad: string;
  ubicacion: string;
  modalidad: string;
  curso: string;
  asignaturas: string[];
  foto?: string;
  clave: string;
  fecha_registro: Date;
}

@Injectable({
  providedIn: 'root'
})
export class EstudianteService {
  private http = inject(HttpClient);
  private API_URL = 'http://localhost:4000/api/estudiantes';

  // --- 1. AUTENTICACIÓN ---
  login(email: string, clave: string): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/login`, { email, clave }, { withCredentials: true });
  }

  loginAdmin(email: string, clave: string): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/login-admin`, { email, clave }, { withCredentials: true });
  }

  // --- 2. INFORMACIÓN DEL PERFIL / DASHBOARD ---
  getDatosDashboard(): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/dashboard-info`, { withCredentials: true });
  }

  // --- 3. CRUD DE ESTUDIANTES (PARA EL ADMIN) ---
  getEstudiantes(): Observable<Estudiante[]> {
    return this.http.get<Estudiante[]>(this.API_URL, { withCredentials: true });
  }

  crearEstudiante(estudiante: Estudiante): Observable<any> {
    return this.http.post<any>(this.API_URL, estudiante);
  }

  actualizarEstudiante(id: string, datos: any): Observable<any> {
    return this.http.put(`${this.API_URL}/${id}`, datos, { withCredentials: true });
  }

  eliminarEstudiante(id: string): Observable<any> {
    return this.http.delete(`${this.API_URL}/${id}`, { withCredentials: true });
  }

  // --- 4. GESTIÓN DE TAREAS / EVENTOS (NUEVO) ---

  // Añade una tarea al array del estudiante logueado
  aniadirTarea(tarea: any): Observable<any> {
    // Eliminamos el ${idEstudiante} de la URL
    return this.http.post<any>(`${this.API_URL}/aniadir-tarea`, tarea, { withCredentials: true });
  }

  // Cambia el estado (completada/pendiente) de una tarea específica
  toggleTarea(tareaId: string): Observable<any> {
    return this.http.put<any>(`${this.API_URL}/toggle-tarea/${tareaId}`, {}, { withCredentials: true });
  }

  // Edita los campos de una tarea específica
  editarTarea(tareaId: string, datosTarea: any): Observable<any> {
    return this.http.put<any>(`${this.API_URL}/editar-tarea/${tareaId}`, datosTarea, { withCredentials: true });
  }

  // Elimina una tarea específica del array
  eliminarTarea(tareaId: string): Observable<any> {
    return this.http.delete<any>(`${this.API_URL}/eliminar-tarea/${tareaId}`, { withCredentials: true });
  }
}
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// 🚀 VITAL: El "export" antes de "interface" para que los componentes puedan usarlo
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
  clave: string;
  fecha_registro: Date;
}

@Injectable({
  providedIn: 'root'
})
export class EstudianteService {
  private http = inject(HttpClient);
  // URL unificada con el prefijo /api de tu Node
  private API_URL = 'http://localhost:4000/api/estudiantes';

  // --- LOGIN ---
  login(email: string, clave: string): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/login`, { email, clave }, { withCredentials: true });
  }

  loginAdmin(email: string, clave: string): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/login-admin`, { email, clave }, { withCredentials: true });
  }

  // --- DASHBOARD (DATOS REALES) ---
  getDatosDashboard(): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/dashboard-info`, { withCredentials: true });
  }

  // --- CRUD GESTIÓN ---
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

  toggleTarea(id: string): Observable<any> {
    return this.http.put<any>(`${this.API_URL}/toggle-tarea/${id}`, {}, { withCredentials: true });
  }
}
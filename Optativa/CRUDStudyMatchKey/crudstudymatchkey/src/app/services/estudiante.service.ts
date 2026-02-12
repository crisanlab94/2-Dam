import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Estudiante {
    _id?: string;
    id_estudiante: string; // Coincide con tu esquema de Node
    nombre: string;
    fecha_nacimiento: Date;
    email: string;
    telefono: string;
    tipo_entidad: string;
    modalidad: string;
    curso: string;
    asignaturas: string[];
    clave: string;
    plan_estudio?: any[];
    tareas?: any[];
    archivos?: any[];
    fecha_registro: Date;
}

@Injectable({
    providedIn: 'root'
})
export class EstudianteService {
    private API_URL = 'http://localhost:3000/estudiantes';

    constructor(private http: HttpClient) { }

    // Obtener lista completa
    getEstudiantes(): Observable<Estudiante[]> {
        return this.http.get<Estudiante[]>(this.API_URL);
    }

    // Obtener un solo estudiante por su ID de MongoDB
    getEstudiante(id: string): Observable<Estudiante> {
        return this.http.get<Estudiante>(`${this.API_URL}/${id}`);
    }
}
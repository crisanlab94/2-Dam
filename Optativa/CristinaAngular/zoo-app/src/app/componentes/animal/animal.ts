import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Animal } from '../../models/animal';

@Component({
  selector: 'app-animal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './animal.html',
  styleUrl: './animal.css'
})
export class AnimalComponent {
  animales: Animal[] = [
    { id_entidad: '001', nombre: 'Simba', fecha_nacimiento: new Date('2020-05-15'), email: 'simba@zoo.com', clave: 'pride123', fecha_registro: new Date() },
    { id_entidad: '002', nombre: 'Dumbo', fecha_nacimiento: new Date('2021-08-20'), email: 'dumbo@zoo.com', clave: 'flyhigh', fecha_registro: new Date() },
    { id_entidad: '003', nombre: 'Baloo', fecha_nacimiento: new Date('2019-03-10'), email: 'baloo@zoo.com', clave: 'bearnece', fecha_registro: new Date() },
    { id_entidad: '004', nombre: 'Bambi', fecha_nacimiento: new Date('2022-11-05'), email: 'bambi@zoo.com', clave: 'forest04', fecha_registro: new Date() },
    { id_entidad: '005', nombre: 'Shere Khan', fecha_nacimiento: new Date('2018-07-22'), email: 'skhan@zoo.com', clave: 'tiger99', fecha_registro: new Date() },
    { id_entidad: '006', nombre: 'Bagheera', fecha_nacimiento: new Date('2019-12-12'), email: 'baghe@zoo.com', clave: 'pantherX', fecha_registro: new Date() },
    { id_entidad: '007', nombre: 'King Louie', fecha_nacimiento: new Date('2017-01-30'), email: 'louie@zoo.com', clave: 'monk3y', fecha_registro: new Date() },
    { id_entidad: '008', nombre: 'Kaa', fecha_nacimiento: new Date('2020-04-02'), email: 'kaa@zoo.com', clave: 'trustme', fecha_registro: new Date() },
    { id_entidad: '009', nombre: 'Hathi', fecha_nacimiento: new Date('2015-09-18'), email: 'hathi@zoo.com', clave: 'eleph09', fecha_registro: new Date() },
    { id_entidad: '010', nombre: 'Pumba', fecha_nacimiento: new Date('2021-02-28'), email: 'pumba@zoo.com', clave: 'hakuna', fecha_registro: new Date() }
  ];

  nuevo: Animal = this.limpiar();
  editando: boolean = false; // Controla si estamos editando o creando [cite: 2026-01-29]

  limpiar(): Animal {
    // Estructura del esquema según modelo oficial [cite: 2025-12-15]
    return { id_entidad: '', nombre: '', fecha_nacimiento: new Date(), email: '', clave: '', fecha_registro: new Date() };
  }

  // Se encarga de guardar tanto si es nuevo como si es edición
  guardar() {
    if (this.editando) {
      const index = this.animales.findIndex(a => a.id_entidad === this.nuevo.id_entidad);
      if (index !== -1) {
        this.animales[index] = { ...this.nuevo };
      }
      this.editando = false;
    } else {
      // Regla de negocio: Reiniciar contador si está vacío [cite: 2025-12-26]
      if (this.animales.length === 0) {
        this.nuevo.id_entidad = '001';
      } else {
        const ultimoId = parseInt(this.animales[this.animales.length - 1].id_entidad);
        this.nuevo.id_entidad = (ultimoId + 1).toString().padStart(3, '0');
      }
      this.animales.push({ ...this.nuevo });
    }
    this.nuevo = this.limpiar();
  }

  // Carga el animal en el formulario
  actualizar(animal: Animal) {
    this.nuevo = { ...animal }; // Copia para no modificar la tabla en tiempo real
    this.editando = true;
  }

  // Borra con confirmación
  eliminar(id: string) {
    if (confirm('¿Estás seguro de que quieres eliminar este animal?')) {
      this.animales = this.animales.filter(a => a.id_entidad !== id);
    }
  }

  cancelar() {
    this.nuevo = this.limpiar();
    this.editando = false;
  }
}
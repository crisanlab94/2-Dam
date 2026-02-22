import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Vehiculo } from '../../models/vehiculo';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-vehiculos',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './vehiculos.html',
  styleUrl: './vehiculos.css',
})
export class Vehiculos {
  vehiculos: Vehiculo[] = [
    { identificacion: '001', modelo: 'Kia', kilometros: 70000, fecha_registro: new Date('2015-05-15'), propietarios: 'Juan García Gómez', tipo_combustible: 'Gasolina', ultima_itv: new Date('2025-04-15'), precio: 15000 },
    { identificacion: '002', modelo: 'Opel', kilometros: 80000, fecha_registro: new Date('2018-03-10'), propietarios: 'Maria López Ruiz', tipo_combustible: 'Diesel', ultima_itv: new Date('2026-01-15'), precio: 7000 },
    { identificacion: '003', modelo: 'Renault', kilometros: 2000, fecha_registro: new Date('2025-11-10'), propietarios: 'Daniel Guitierre Ramirez', tipo_combustible: 'Gasolina', ultima_itv: new Date(), precio: 17000 }
  ];

  nuevo: Vehiculo = this.limpiar();
  editando: boolean = false;

  limpiar(): Vehiculo {

    return { identificacion: '', modelo: '', kilometros: 0, fecha_registro: new Date(), propietarios: '', tipo_combustible: '', ultima_itv: new Date(), precio: 0 };
  }


  guardar() {
    if (this.editando) {
      const index = this.vehiculos.findIndex(a => a.identificacion === this.nuevo.identificacion);
      if (index !== -1) {
        this.vehiculos[index] = { ...this.nuevo };
      }
      this.editando = false;
    } else {

      if (this.vehiculos.length === 0) {
        this.nuevo.identificacion = '001';
      } else {
        const ultimoId = parseInt(this.vehiculos[this.vehiculos.length - 1].identificacion);
        this.nuevo.identificacion = (ultimoId + 1).toString().padStart(3, '0');
      }
      this.vehiculos.push({ ...this.nuevo });
    }
    this.nuevo = this.limpiar();
  }


  actualizar(vehiculo: Vehiculo) {
    this.nuevo = { ...vehiculo };
    this.editando = true;
  }


  eliminar(id: string) {
    if (confirm('¿Estás seguro de que quieres eliminar este vehiculo?')) {
      this.vehiculos = this.vehiculos.filter(a => a.identificacion !== id);
    }
  }

  cancelar() {
    this.nuevo = this.limpiar();
    this.editando = false;
  }

}

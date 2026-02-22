import { Component } from '@angular/core';
// 1. Asegúrate de importar el componente que creaste
import { AnimalComponent } from './componentes/animal/animal';

@Component({
  selector: 'app-root',
  standalone: true,
  // 2. Debes incluirlo en los imports del componente raíz
  imports: [AnimalComponent],
  // 3. Debes poner la etiqueta en el template
  template: `<app-animal></app-animal>`
})
export class App { }
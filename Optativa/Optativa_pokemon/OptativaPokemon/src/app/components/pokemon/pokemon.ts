import { Component } from '@angular/core';

@Component({
  selector: 'app-pokemon',
  standalone: true,
  imports: [],
  templateUrl: './pokemon.html',
  styleUrl: './pokemon.css',
})
export class Pokemon {
listaPokemon = [
    { nombre: "Caterpie", tipo: "Bicho", descripcion: "Es lamentable" },
    { nombre: "Weedle", tipo: "Bicho", descripcion: "También es lamentable" },
    { nombre: "Magikarp", tipo: "Agua", descripcion: "Qué cosa más mala" }
  ];
}

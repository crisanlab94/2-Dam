import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PokemonService, PokemonData } from '../../services/pokemon.service';

@Component({
    selector: 'app-pokemon-form',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './pokemon-form.html',
})
export class PokemonForm implements OnInit {
    nuevoNombre: string = '';
    nuevoTipo: string = '';
    nuevaDescripcion: string = '';
    idEditar: string | null = null; // Para saber si estamos editando

    private pokemonService = inject(PokemonService);
    private router = inject(Router);
    private route = inject(ActivatedRoute); // Para leer el ID de la URL

    ngOnInit(): void {
        // Comprobamos si hay un ID en la URL
        this.idEditar = this.route.snapshot.paramMap.get('id');

        if (this.idEditar) {
            // Si existe, pedimos los datos a Node.js para rellenar el formulario
            this.pokemonService.getPokemonById(this.idEditar).subscribe({
                next: (p) => {
                    this.nuevoNombre = p.nombre;
                    this.nuevoTipo = p.tipo;
                    this.nuevaDescripcion = p.descripcion;
                },
                error: (e) => console.error('Error al cargar datos:', e)
            });
        }
    }

    guardar(): void {
        const datos: PokemonData = {
            nombre: this.nuevoNombre,
            tipo: this.nuevoTipo,
            descripcion: this.nuevaDescripcion
        };


        if (this.idEditar) {
            // MODO EDICIÓN
            this.pokemonService.actualizarPokemon(this.idEditar, datos).subscribe({
                next: () => this.router.navigate(['/pokemon']),
                error: (e) => console.error(e)
            });
        } else {
            // MODO CREACIÓN
            this.pokemonService.postPokemon(datos).subscribe({
                next: () => this.router.navigate(['/pokemon']),
                error: (e) => console.error(e)
            });
        }
    }

    cancelar(): void {
        this.router.navigate(['/pokemon']);
    }
}
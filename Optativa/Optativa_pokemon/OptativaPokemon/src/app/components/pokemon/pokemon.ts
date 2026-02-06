import { Component, OnInit, inject, PLATFORM_ID, ChangeDetectorRef } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common'; // Importamos estas utilidades
import { Router } from '@angular/router';
import { PokemonService, PokemonData } from '../../services/pokemon.service';

@Component({
  selector: 'app-pokemon',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pokemon.html',
  styleUrl: './pokemon.css',
})
export class Pokemon implements OnInit {
  listaPokemon: PokemonData[] = [];

  // Inyectamos el ID de la plataforma para evitar fallos de carga inicial
  private platformId = inject(PLATFORM_ID);
  private pokemonService = inject(PokemonService);
  private router = inject(Router);
  private cd = inject(ChangeDetectorRef);

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.obtenerPokemons();
    }
  }

  obtenerPokemons(): void {
    this.pokemonService.getPokemons().subscribe({
      next: (datos) => {
        // 1. Forzamos una nueva referencia del array
        this.listaPokemon = [...datos];
        console.log("¡Datos recibidos!", this.listaPokemon);

        // 2. OBLIGAMOS a la interfaz a refrescarse
        this.cd.detectChanges();
      },
      error: (e) => console.error("Error:", e)
    });
  }

  irAAgregar(): void {
    this.router.navigate(['/nuevo']);
  }

  irAEditar(id: string | undefined): void {
    if (id) this.router.navigate(['/editar', id]);
  }

  borrarPokemon(id: string | undefined): void {
    if (id && confirm('¿Eliminar este Pokémon?')) {
      this.pokemonService.eliminarPokemon(id).subscribe({
        next: () => this.obtenerPokemons(),
        error: (e) => console.error(e)
      });
    }
  }
}
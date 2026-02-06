import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PokemonData {
  _id?: string;
  nombre: string;
  tipo: string;
  descripcion: string;
}

@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  private http = inject(HttpClient);
  private urlPokemon = 'http://localhost:4000/pokemon';

  //  Obtener los Pokémon (Read)
  getPokemons(): Observable<PokemonData[]> {
    return this.http.get<PokemonData[]>(this.urlPokemon);
  }


  postPokemon(data: PokemonData): Observable<PokemonData> {

    return this.http.post<PokemonData>(this.urlPokemon, data);
  }

  // Obtener un solo registro para editar
  getPokemonById(id: string): Observable<PokemonData> {
    return this.http.get<PokemonData>(`${this.urlPokemon}/${id}`);
  }

  // Actualizar registro completo
  actualizarPokemon(id: string, data: PokemonData): Observable<any> {
    return this.http.put(`${this.urlPokemon}/${id}`, data);
  }

  eliminarPokemon(id: string): Observable<any> {
    return this.http.delete(`${this.urlPokemon}/${id}`);
  }
}

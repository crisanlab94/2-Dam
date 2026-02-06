import { Routes } from '@angular/router';
import { Pokemon } from './components/pokemon/pokemon';
import { PokemonForm } from './components/pokemon-form/pokemon-form';

export const routes: Routes = [
    { path: '', redirectTo: 'pokemon', pathMatch: 'full' },
    { path: 'pokemon', component: Pokemon },
    { path: 'nuevo', component: PokemonForm },
    { path: 'editar/:id', component: PokemonForm }
];
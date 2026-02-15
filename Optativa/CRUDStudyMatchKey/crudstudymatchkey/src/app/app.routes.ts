import { Routes } from '@angular/router';
// Quitamos el ".component" del final de la ruta porque tu archivo no lo tiene
import { EstudianteComponent } from './components/estudiante/estudiante'; 

export const routes: Routes = [
  { path: 'estudiante', component: EstudianteComponent },
  { path: '', redirectTo: '/estudiante', pathMatch: 'full' }
];
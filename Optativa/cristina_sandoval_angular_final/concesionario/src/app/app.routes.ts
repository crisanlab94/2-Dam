import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Vehiculos } from './components/vehiculos/vehiculos';
import { Contacto } from './components/contacto/contacto';
import { Informacion } from './components/informacion/informacion';

export const routes: Routes = [
    { path: '', component: Login },
    { path: 'vehiculos', component: Vehiculos },
    { path: 'informacion', component: Informacion },
    { path: 'contacto', component: Contacto },
    { path: '**', redirectTo: '' }
];

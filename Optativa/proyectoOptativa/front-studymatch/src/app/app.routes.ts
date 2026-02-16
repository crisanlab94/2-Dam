import { Routes } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio';
import { LoginComponent } from './components/login/login';
import { EstudianteComponent } from './components/estudiante/estudiante';
import { AdminPanel } from './components/admin/admin';
import { DashboardComponent } from './components/dashboard/dashboard';
import { LoginAdmin } from './components/login-admin/login-admin';
import { Perfil } from './components/perfil/perfil';
import { MisEventosComponent } from './components/mis-eventos/mis-eventos';
import { AddEventoComponent } from './components/add-evento/add-evento';
import { CalendarioComponent } from './components/calendario/calendario'; // 👈 Añade esta línea

export const routes: Routes = [
  { path: '', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: 'estudiante', component: EstudianteComponent },
  { path: 'registro', component: EstudianteComponent },
  { path: 'mi-perfil', component: DashboardComponent }, // Tu panel principal
  { path: 'login-admin', component: LoginAdmin },
  { path: 'admin', component: AdminPanel },
  { path: 'perfil', component: Perfil },
  { path: 'mis-eventos', component: MisEventosComponent },
  { path: 'add-evento', component: AddEventoComponent },
  { path: 'calendario', component: CalendarioComponent }, // 👈 Añade esta ruta
  { path: '**', redirectTo: '' }
];
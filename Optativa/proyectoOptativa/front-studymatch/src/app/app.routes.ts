import { Routes } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio';
import { LoginComponent } from './components/login/login';
import { EstudianteComponent } from './components/estudiante/estudiante';
import { AdminPanel } from './components/admin/admin';
import { DashboardComponent } from './components/dashboard/dashboard'; 

// 🚀 IMPORTA LA CLASE DEL LOGIN DE ADMIN
// Asegúrate de que el archivo se llame login-admin.ts y la clase sea LoginAdmin
import { LoginAdmin } from './components/login-admin/login-admin';
import { Perfil } from './components/perfil/perfil';

export const routes: Routes = [
  { path: '', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: EstudianteComponent },
  { path: 'mi-perfil', component: DashboardComponent },
  
  { path: 'login-admin', component: LoginAdmin },
  
  
  { path: 'admin', component: AdminPanel }, 
   { path: 'perfil', component: Perfil },
  { path: '**', redirectTo: '' }

];
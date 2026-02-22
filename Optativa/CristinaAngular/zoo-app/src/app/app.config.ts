import { ApplicationConfig, provideZonelessChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    // Esto elimina el error NG0908 de Zone.js de forma definitiva
    provideZonelessChangeDetection(),
    provideRouter(routes)
  ]
};
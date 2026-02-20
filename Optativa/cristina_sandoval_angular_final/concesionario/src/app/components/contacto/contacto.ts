import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contacto',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './contacto.html',
  styleUrl: './contacto.css',
})
export class Contacto {
  private cdr = inject(ChangeDetectorRef);
  public mensajeSoporte: string = '';
  public enviando: boolean = false;


  public notificacionSoporte: boolean = false;

  enviarASoporte() {
    if (!this.mensajeSoporte.trim()) return;

    this.enviando = true;
    this.notificacionSoporte = false;


    this.cdr.detectChanges();

    setTimeout(() => {
      this.enviando = false;
      this.notificacionSoporte = true;
      this.mensajeSoporte = '';


      this.cdr.markForCheck();
      this.cdr.detectChanges();

      setTimeout(() => {
        this.notificacionSoporte = false;
        this.cdr.detectChanges();
      }, 3000);
    }, 1000);
  }
}

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EstudianteComponent } from './estudiante';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { provideRouter } from '@angular/router';
import { FormsModule } from '@angular/forms';

describe('EstudianteComponent', () => {
  let component: EstudianteComponent;
  let fixture: ComponentFixture<EstudianteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      // Importamos el componente Standalone y el módulo de formularios
      imports: [EstudianteComponent, FormsModule],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        provideRouter([])
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(EstudianteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('debería crear el componente de registro de StudyMatchKey', () => {
    expect(component).toBeTruthy();
  });

  it('debería inicializar el objeto estudiante con valores vacíos', () => {
    expect(component.estudiante.nombre).toBe('');
    expect(component.estudiante.tipo_entidad).toBe('');
  });

  it('debería dar formulario inválido si los campos están vacíos', () => {
    // Cambiamos toBeFalse() por toBe(false) para evitar el error de tipos
    expect(component.formularioValido()).toBe(false);
  });

  it('debería validar que la contraseña cumple los requisitos mínimos', () => {
    component.estudiante.clave = '123';
    // Comprobamos que la función de longitud detecta que es corta
    expect(component.checkLongitud()).toBe(false);
  });
});
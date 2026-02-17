import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Animales } from './animales';

describe('Animales', () => {
  let component: Animales;
  let fixture: ComponentFixture<Animales>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Animales]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Animales);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

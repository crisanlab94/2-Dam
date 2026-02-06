import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonCreate } from './pokemon-create';

describe('PokemonCreate', () => {
  let component: PokemonCreate;
  let fixture: ComponentFixture<PokemonCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PokemonCreate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PokemonCreate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

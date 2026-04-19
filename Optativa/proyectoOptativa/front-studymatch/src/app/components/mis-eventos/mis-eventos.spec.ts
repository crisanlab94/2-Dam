import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MisEventosComponent } from './mis-eventos';

describe('MisEventos', () => {
  let component: MisEventosComponent;
  let fixture: ComponentFixture<MisEventosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MisEventosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MisEventosComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEvento } from './add-evento';

describe('AddEvento', () => {
  let component: AddEvento;
  let fixture: ComponentFixture<AddEvento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddEvento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEvento);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
